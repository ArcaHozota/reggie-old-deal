package jp.co.reggie.mbpdeal.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import jp.co.reggie.mbpdeal.common.CustomException;
import jp.co.reggie.mbpdeal.common.CustomMessages;
import jp.co.reggie.mbpdeal.entity.Category;
import jp.co.reggie.mbpdeal.entity.Dish;
import jp.co.reggie.mbpdeal.entity.Setmeal;
import jp.co.reggie.mbpdeal.mapper.CategoryMapper;
import jp.co.reggie.mbpdeal.service.CategoryService;
import jp.co.reggie.mbpdeal.service.DishService;
import jp.co.reggie.mbpdeal.service.SetmealService;
import oracle.jdbc.driver.OracleSQLException;

/**
 * @author Administrator
 * @date 2022-11-19
 */
@Service
@Transactional(rollbackFor = OracleSQLException.class)
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {

	/**
	 * 菜品服務類；
	 */
	@Autowired
	private DishService dishService;

	/**
	 * 套餐服務類；
	 */
	@Autowired
	private SetmealService setmealService;

	/**
	 * 根據ID刪除分類
	 *
	 * @param id 分類ID
	 */
	@Override
	public void remove(final Long id) {
		final LambdaQueryWrapper<Dish> dishQueryWrapper = new LambdaQueryWrapper<>();
		// 添加查詢條件，根據ID進行查詢；
		dishQueryWrapper.eq(Dish::getCategoryId, id);
		final long count1 = this.dishService.count(dishQueryWrapper);
		// 查詢當前分類是否已經關聯了菜品，如果已經關聯抛出一個異常；
		if (count1 > 0) {
			throw new CustomException(CustomMessages.ERP009);
		}
		final LambdaQueryWrapper<Setmeal> setMealQueryWrapper = new LambdaQueryWrapper<>();
		// 添加查詢條件，根據ID進行查詢；
		setMealQueryWrapper.eq(Setmeal::getCategoryId, id);
		final long count2 = this.setmealService.count(setMealQueryWrapper);
		// 查詢當前分類是否已經關聯了套餐，如果已經關聯抛出一個異常；
		if (count2 > 0) {
			throw new CustomException(CustomMessages.ERP009);
		}
		// 正常刪除分類；
		super.removeById(id);
	}

	/**
	 * 根據類型查詢數據
	 *
	 * @param categoryType 類型
	 * @return List<Category>
	 */
	@Override
	public List<Category> findByType(final Integer categoryType) {
		final LambdaQueryWrapper<Category> queryWrapper = new LambdaQueryWrapper<>();
		queryWrapper.eq(categoryType != null, Category::getType, categoryType);
		return super.getBaseMapper().selectList(queryWrapper);
	}

	/**
	 * 查詢分頁數據
	 *
	 * @param pageNum
	 * @param pageSize
	 * @return Page<Category>
	 */
	@Override
	public Page<Category> pagination(final Integer pageNum, final Integer pageSize) {
		// 聲明分頁構造器；
		final Page<Category> pageInfo = Page.of(pageNum, pageSize);
		// 聲明條件構造器；
		final LambdaQueryWrapper<Category> queryWrapper = new LambdaQueryWrapper<>();
		// 添加排序條件，根據sort進行排序；
		queryWrapper.orderByAsc(Category::getSort);
		// 添加排序條件，根據updatedTime進行降序排序；
		queryWrapper.orderByDesc(Category::getUpdatedTime);
		// 執行查詢；
		return super.getBaseMapper().selectPage(pageInfo, queryWrapper);
	}
}
