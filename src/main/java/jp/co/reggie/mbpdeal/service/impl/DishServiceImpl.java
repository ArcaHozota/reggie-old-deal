package jp.co.reggie.mbpdeal.service.impl;

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

import jp.co.reggie.mbpdeal.common.Constants;
import jp.co.reggie.mbpdeal.common.ReggieException;
import jp.co.reggie.mbpdeal.common.CommonMessages;
import jp.co.reggie.mbpdeal.dto.DishDto;
import jp.co.reggie.mbpdeal.entity.Category;
import jp.co.reggie.mbpdeal.entity.Dish;
import jp.co.reggie.mbpdeal.entity.DishFlavour;
import jp.co.reggie.mbpdeal.mapper.CategoryMapper;
import jp.co.reggie.mbpdeal.mapper.DishFlavourMapper;
import jp.co.reggie.mbpdeal.mapper.DishMapper;
import jp.co.reggie.mbpdeal.service.DishService;
import jp.co.reggie.mbpdeal.utils.StringUtils;

/**
 * 菜品服務類
 *
 * @author ArcaHozota
 * @since 1.71
 */
@Service
@Transactional(rollbackFor = PSQLException.class)
public class DishServiceImpl extends ServiceImpl<DishMapper, Dish> implements DishService {

	/**
	 * 分類管理接口類
	 */
	@Autowired
	private CategoryMapper categoryMapper;

	/**
	 * 菜品口味接口類
	 */
	@Autowired
	private DishFlavourMapper dishFlavourMapper;

	@Override
	public void batchUpdateByIds(final String status, final List<Long> dishIdList) {
		if (StringUtils.isEqual("0", status)) {
			dishIdList.forEach(item -> {
				final Dish dish = this.getBaseMapper().selectById(item);
				dish.setStatus(Constants.STATUS_VALID);
				this.getBaseMapper().updateById(dish);
			});
		} else if (StringUtils.isEqual("1", status)) {
			dishIdList.forEach(item -> {
				final Dish dish = this.getBaseMapper().selectById(item);
				dish.setStatus(Constants.STATUS_FORBIDDEN);
				this.getBaseMapper().updateById(dish);
			});
		} else {
			throw new ReggieException(CommonMessages.ERP017);
		}
	}

	@Override
	public List<DishDto> findListByCategoryId(final Long categoryId) {
		// 創建條件構造器；
		final LambdaQueryWrapper<Dish> queryWrapper = Wrappers.lambdaQuery(new Dish());
		// 添加搜索條件；
		queryWrapper.eq(Dish::getCategoryId, categoryId);
		queryWrapper.eq(Dish::getStatus, Constants.STATUS_VALID);
		// 添加排序條件；
		queryWrapper.orderByAsc(Dish::getSort).orderByDesc(Dish::getUpdatedTime);
		// 查詢菜品信息；
		final List<Dish> list = this.list(queryWrapper);
		// 獲取菜品及口味數據傳輸類；
		return list.stream().map(item -> {
			// 聲明菜品及口味數據傳輸類對象；
			final DishDto dishDto = new DishDto();
			// 拷貝除分類ID以外的屬性；
			BeanUtils.copyProperties(item, dishDto);
			// 獲取分類ID並根據ID查詢分類對象；
			final Category category = this.categoryMapper.selectById(item.getCategoryId());
			if (category != null) {
				// 獲取分類名稱；
				final String categoryName = category.getName();
				// 存儲於DTO對象中並返回；
				dishDto.setCategoryName(categoryName);
			}
			// 當前菜品的ID；
			final Long dishId = item.getId();
			// 創建條件構造器；
			final LambdaQueryWrapper<DishFlavour> queryWrapper2 = Wrappers.lambdaQuery();
			queryWrapper2.eq(DishFlavour::getDishId, dishId);
			// 檢索口味信息；
			final List<DishFlavour> flavors = this.dishFlavourMapper.selectList(queryWrapper2);
			dishDto.setFlavors(flavors);
			return dishDto;
		}).collect(Collectors.toList());
	}

	@Override
	public DishDto getByIdWithFlavour(final Long id) {
		// 查詢菜品的基本信息；
		final Dish dish = this.getById(id);
		// 查詢當前菜品所對應的口味信息；
		final LambdaQueryWrapper<DishFlavour> queryWrapper = new LambdaQueryWrapper<>();
		queryWrapper.eq(DishFlavour::getDishId, dish.getId());
		// 獲取菜品口味列表；
		final List<DishFlavour> flavors = this.dishFlavourMapper.selectList(queryWrapper);
		// 聲明一個菜品及口味數據傳輸類對象並拷貝屬性；
		final DishDto dishDto = new DishDto();
		BeanUtils.copyProperties(dish, dishDto);
		dishDto.setFlavors(flavors);
		return dishDto;
	}

	@Override
	public Page<DishDto> pagination(final Integer pageNum, final Integer pageSize, final String keyword) {
		// 聲明分頁構造器對象；
		final Page<Dish> pageInfo = Page.of(pageNum, pageSize);
		// 創建條件構造器；
		final LambdaQueryWrapper<Dish> queryWrapper = new LambdaQueryWrapper<>();
		// 添加過濾條件；
		queryWrapper.like(Dish::getName, StringUtils.toHankaku(keyword));
		// 添加排序條件；
		queryWrapper.orderByDesc(Dish::getUpdatedTime);
		// 執行分頁查詢；
		final Page<Dish> dishPage = this.getBaseMapper().selectPage(pageInfo, queryWrapper);
		// 獲取分頁數據；
		final List<DishDto> records = dishPage.getRecords().stream().map(item -> {
			// 聲明菜品及口味數據傳輸類對象；
			final DishDto dishDto = new DishDto();
			// 拷貝除分類ID以外的屬性；
			BeanUtils.copyProperties(item, dishDto);
			// 獲取分類ID；
			final Long categoryId = item.getCategoryId();
			// 根據ID查詢分類對象；
			final Category category = this.categoryMapper.selectById(categoryId);
			if (category != null) {
				// 獲取分類名稱並存儲於DTO對象中；
				dishDto.setCategoryName(category.getName());
			}
			return dishDto;
		}).collect(Collectors.toList());
		// 聲明數據傳輸類分頁構造對象；
		final Page<DishDto> dtoPage = new Page<>();
		// 對象拷貝；
		BeanUtils.copyProperties(dishPage, dtoPage, "records");
		// 設置分頁數據於構造器中並返回；
		dtoPage.setRecords(records);
		return dtoPage;
	}

	@Override
	public void saveWithFlavour(final DishDto dishDto) {
		// 聲明菜品實體類；
		final Dish dish = new Dish();
		// 拷貝相關屬性；
		BeanUtils.copyProperties(dishDto, dish, "flavours", "copy", "categoryName");
		// 設置基礎順序；
		dish.setSort(0);
		// 設置邏輯刪除標志；
		dish.setDeleteFlg("visible");
		// 保存菜品的基本信息到菜品表；
		this.getBaseMapper().insert(dish);
		// 獲取菜品口味的集合並將菜品ID設置到口味集合中；
		dishDto.getFlavors().forEach(item -> {
			// 獲取菜品ID；
			item.setDishId(dish.getId());
			// 設置邏輯刪除標志；
			item.setDeleteFlg("visible");
			// 保存菜品的口味數據到口味表；
			this.dishFlavourMapper.insert(item);
		});
	}

	@Override
	public void updateWithFlavour(final DishDto dishDto) {
		// 聲明菜品實體類；
		final Dish dish = new Dish();
		// 拷貝相關屬性；
		BeanUtils.copyProperties(dishDto, dish, "flavours", "copy", "categoryName");
		// 更新菜品信息；
		this.getBaseMapper().updateById(dish);
		// 清理當前菜品所對應的口味信息；
		final LambdaQueryWrapper<DishFlavour> queryWrapper = new LambdaQueryWrapper<>();
		queryWrapper.eq(DishFlavour::getDishId, dish.getId());
		this.dishFlavourMapper.delete(queryWrapper);
		// 添加當前菜品的口味數據並將菜品ID設置到口味集合中；
		dishDto.getFlavors().forEach(item -> {
			item.setDishId(dish.getId());
			item.setDeleteFlg(Constants.LOGIC_FLAG);
			this.dishFlavourMapper.insert(item);
		});
	}
}
