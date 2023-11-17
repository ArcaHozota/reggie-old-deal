package jp.co.reggie.mbpdeal.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import org.postgresql.util.PSQLException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import jp.co.reggie.mbpdeal.common.CommonMessages;
import jp.co.reggie.mbpdeal.common.Constants;
import jp.co.reggie.mbpdeal.common.ReggieException;
import jp.co.reggie.mbpdeal.dto.SetmealDto;
import jp.co.reggie.mbpdeal.entity.Category;
import jp.co.reggie.mbpdeal.entity.Setmeal;
import jp.co.reggie.mbpdeal.entity.SetmealDish;
import jp.co.reggie.mbpdeal.handler.BaseContext;
import jp.co.reggie.mbpdeal.mapper.CategoryMapper;
import jp.co.reggie.mbpdeal.mapper.SetmealDishMapper;
import jp.co.reggie.mbpdeal.mapper.SetmealMapper;
import jp.co.reggie.mbpdeal.service.SetmealService;
import jp.co.reggie.mbpdeal.utils.StringUtils;

/**
 * 套餐服務類
 *
 * @author ArcaHozota
 * @since 1.71
 */
@Service
@Transactional(rollbackFor = PSQLException.class)
public class SetmealServiceImpl extends ServiceImpl<SetmealMapper, Setmeal> implements SetmealService {

	private static final Random RANDOM = new Random();

	/**
	 * 分類管理接口類
	 */
	@Autowired
	private CategoryMapper categoryMapper;

	/**
	 * 套餐與菜品接口類
	 */
	@Autowired
	private SetmealDishMapper setmealDishMapper;

	@Override
	public void batchUpdateByIds(final String status, final List<Long> smIdList) {
		Integer newStatus;
		if (StringUtils.isEqual("0", status)) {
			newStatus = Constants.STATUS_VALID;
		} else if (StringUtils.isEqual("1", status)) {
			newStatus = Constants.STATUS_FORBIDDEN;
		} else {
			throw new ReggieException(CommonMessages.ERP022);
		}
		final LocalDateTime now = LocalDateTime.now();
		final Long currentId = BaseContext.getCurrentId();
		this.getBaseMapper().batchUpdateByIds(newStatus, currentId, now, smIdList);
	}

	@Override
	public SetmealDto getByIdWithDish(final Long id) {
		final Setmeal setmeal = this.getById(id);
		final LambdaQueryWrapper<SetmealDish> queryWrapper = Wrappers.lambdaQuery();
		queryWrapper.eq(SetmealDish::getSetmealId, id);
		final List<SetmealDish> setmealDishes = this.setmealDishMapper.selectList(queryWrapper);
		final SetmealDto setmealDto = new SetmealDto();
		BeanUtils.copyProperties(setmeal, setmealDto);
		setmealDto.setSetmealDishes(setmealDishes);
		return setmealDto;
	}

	@Override
	public Page<SetmealDto> pagination(final Integer pageNum, final Integer pageSize, final String keyword) {
		// 聲明分頁構造器；
		Page<Setmeal> pageInfo = Page.of(pageNum, pageSize);
		// 聲明條件構造器；
		final LambdaQueryWrapper<Setmeal> queryWrapper = Wrappers.lambdaQuery(new Setmeal());
		// 添加查詢條件，根據檢索文進行模糊查詢；
		queryWrapper.like(Setmeal::getName, StringUtils.toHankaku(keyword));
		// 添加排序條件；
		queryWrapper.orderByDesc(Setmeal::getUpdatedTime);
		// 執行查詢；
		pageInfo = this.page(pageInfo, queryWrapper);
		// 聲明數據傳輸類分頁構造器；
		final Page<SetmealDto> dtoPage = new Page<>();
		// 拷貝屬性；
		BeanUtils.copyProperties(pageInfo, dtoPage, "records");
		// 獲取數據傳輸類分頁；
		final List<SetmealDto> records = pageInfo.getRecords().stream().map(item -> {
			// 聲明套餐數據傳輸類；
			final SetmealDto setmealDto = new SetmealDto();
			// 對象拷貝；
			BeanUtils.copyProperties(item, setmealDto);
			// 獲取分類ID；
			final Long categoryId = item.getCategoryId();
			// 根據分類ID獲取分類對象；
			final Category category = this.categoryMapper.selectById(categoryId);
			// 分類對象存在；
			if (category != null) {
				// 獲取分類名稱並設置到數據傳輸類中；
				final String categoryName = category.getName();
				setmealDto.setCategoryName(categoryName);
			}
			return setmealDto;
		}).collect(Collectors.toList());
		// 設置分頁數據於構造器中並返回；
		dtoPage.setRecords(records);
		return dtoPage;
	}

	@Override
	@Transactional(rollbackFor = PSQLException.class)
	public void removeWithDish(final List<Long> ids) {
		// 查詢套餐狀態以確認是否可以刪除；
		final LambdaQueryWrapper<Setmeal> queryWrapper = new LambdaQueryWrapper<>();
		queryWrapper.in(Setmeal::getId, ids);
		queryWrapper.eq(Setmeal::getStatus, 1);
		final long count = this.count(queryWrapper);
		if (count > 0) {
			// 如果無法刪除，則抛出異常；
			throw new ReggieException(CommonMessages.ERP012);
		}
		// 刪除套餐表中的數據；
		this.removeByIds(ids);
		// 刪除套餐口味表中的數據；
		this.setmealDishMapper.batchRemoveBySmIds(ids);
	}

	@Override
	public void saveWithDish(final SetmealDto setmealDto) {
		// 聲明套餐實體類；
		final Setmeal setmeal = new Setmeal();
		// 拷貝屬性；
		BeanUtils.copyProperties(setmealDto, setmeal, "categoryName", "setmealDishes");
		// 設置邏輯刪除字段；
		setmeal.setDeleteFlg(Constants.LOGIC_FLAG);
		// 保存套餐的基本信息；
		super.getBaseMapper().insert(setmeal);
		// 獲取套餐菜品關聯集合；
		final List<SetmealDish> setmealDishes = setmealDto.getSetmealDishes();
		// 獲取套餐ID並插入集合；
		setmealDishes.forEach(item -> {
			item.setSetmealId(setmeal.getId());
			item.setSort(RANDOM.nextInt(setmealDishes.size()));
			this.setmealDishMapper.insert(item);
		});
	}

	@Override
	public void updateWithDish(final SetmealDto setmealDto) {
		// 聲明套餐實體類；
		final Setmeal setmeal = new Setmeal();
		// 拷貝屬性；
		BeanUtils.copyProperties(setmealDto, setmeal, "categoryName", "setmealDishes");
		// 更新套餐的基本信息；
		this.updateById(setmeal);
		// 獲取套餐菜品關聯集合；
		final List<SetmealDish> setmealDishes = setmealDto.getSetmealDishes();
		// 更新套餐和菜品的關聯關係；
		setmealDishes.forEach(item -> {
			final LambdaQueryWrapper<SetmealDish> queryWrapper = Wrappers.lambdaQuery();
			queryWrapper.eq(SetmealDish::getDishId, item.getDishId());
			queryWrapper.eq(SetmealDish::getSetmealId, setmealDto.getId());
			final SetmealDish setmealDish = this.setmealDishMapper.selectOne(queryWrapper);
			this.setmealDishMapper.updateById(setmealDish);
		});
	}

}
