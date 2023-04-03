package jp.co.reggie.oldeal.service.impl;

import java.util.List;
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

import jp.co.reggie.oldeal.common.CustomException;
import jp.co.reggie.oldeal.common.CustomMessages;
import jp.co.reggie.oldeal.dto.SetmealDto;
import jp.co.reggie.oldeal.entity.Category;
import jp.co.reggie.oldeal.entity.Setmeal;
import jp.co.reggie.oldeal.entity.SetmealDish;
import jp.co.reggie.oldeal.mapper.CategoryMapper;
import jp.co.reggie.oldeal.mapper.SetmealMapper;
import jp.co.reggie.oldeal.service.SetmealDishService;
import jp.co.reggie.oldeal.service.SetmealService;
import jp.co.reggie.oldeal.utils.StringUtils;

/**
 * @author Administrator
 * @date 2022-11-19
 */
@Service
public class SetmealServiceImpl extends ServiceImpl<SetmealMapper, Setmeal> implements SetmealService {

	/**
	 * 分類管理實體類接口
	 */
	@Autowired
	private CategoryMapper categoryMapper;

	/**
	 * 套餐與菜品服務類
	 */
	@Autowired
	private SetmealDishService setmealDishService;

	/**
	 * 新增套餐同時保存套餐和菜品的關聯關係
	 *
	 * @param setmealDto 數據傳輸類
	 */
	@Transactional(rollbackFor = PSQLException.class)
	@Override
	public void saveWithDish(final SetmealDto setmealDto) {
		// 保存套餐的基本信息；
		this.save(setmealDto);
		// 獲取套餐菜品關聯集合；
		List<SetmealDish> setmealDishes = setmealDto.getSetmealDishes();
		// 獲取菜品ID並插入集合；
		setmealDishes = setmealDishes.stream().map((item) -> {
			item.setDishId(setmealDto.getId());
			return item;
		}).collect(Collectors.toList());
		// 保存套餐和菜品的關聯關係；
		this.setmealDishService.saveBatch(setmealDishes);
	}

	/**
	 * 刪除套餐同時刪除套餐和菜品的關聯關係
	 *
	 * @param ids 套餐ID的集合
	 */
	@Transactional(rollbackFor = PSQLException.class)
	@Override
	public void removeWithDish(final List<Long> ids) {
		// 查詢套餐狀態以確認是否可以刪除；
		final LambdaQueryWrapper<Setmeal> queryWrapper = new LambdaQueryWrapper<>();
		queryWrapper.in(Setmeal::getId, ids);
		queryWrapper.eq(Setmeal::getStatus, 1);
		final long count = this.count(queryWrapper);
		if (count > 0) {
			// 如果無法刪除，則抛出異常；
			throw new CustomException(CustomMessages.ERP012);
		}
		// 刪除套餐表中的數據；
		this.removeByIds(ids);
		// 刪除套餐口味表中的數據；
		final LambdaQueryWrapper<SetmealDish> lambdaQueryWrapper = new LambdaQueryWrapper<>();
		lambdaQueryWrapper.in(SetmealDish::getSetmealId, ids);
		this.setmealDishService.remove(lambdaQueryWrapper);
	}

	/**
	 * 進行分頁
	 *
	 * @param pageNum  頁碼
	 * @param pageSize 頁面大小
	 * @param keyword  檢索文
	 * @return Page<SetmealDto>
	 */
	@Override
	public Page<SetmealDto> pagination(final Integer pageNum, final Integer pageSize, final String keyword) {
		// 聲明分頁構造器；
		Page<Setmeal> pageInfo = Page.of(pageNum, pageSize);
		// 聲明條件構造器；
		final LambdaQueryWrapper<Setmeal> queryWrapper = Wrappers.lambdaQuery(new Setmeal());
		// 添加查詢條件，根據檢索文進行模糊查詢；
		queryWrapper.like(StringUtils.isNotEmpty(keyword), Setmeal::getName, keyword);
		// 添加排序條件；
		queryWrapper.orderByDesc(Setmeal::getUpdateTime);
		// 執行查詢；
		pageInfo = this.page(pageInfo, queryWrapper);
		// 聲明數據傳輸類分頁構造器；
		final Page<SetmealDto> dtoPage = new Page<>();
		// 拷貝屬性；
		BeanUtils.copyProperties(pageInfo, dtoPage, "records");
		// 獲取數據傳輸類分頁；
		final List<SetmealDto> records = pageInfo.getRecords().stream().map((item) -> {
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
}
