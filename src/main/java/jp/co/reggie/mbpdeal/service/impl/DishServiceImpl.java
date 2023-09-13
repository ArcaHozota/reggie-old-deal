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

import jp.co.reggie.mbpdeal.common.CustomException;
import jp.co.reggie.mbpdeal.common.CustomMessages;
import jp.co.reggie.mbpdeal.dto.DishDto;
import jp.co.reggie.mbpdeal.entity.Category;
import jp.co.reggie.mbpdeal.entity.Dish;
import jp.co.reggie.mbpdeal.entity.DishFlavour;
import jp.co.reggie.mbpdeal.mapper.CategoryMapper;
import jp.co.reggie.mbpdeal.mapper.DishMapper;
import jp.co.reggie.mbpdeal.service.DishFlavourService;
import jp.co.reggie.mbpdeal.service.DishService;
import jp.co.reggie.mbpdeal.utils.StringUtils;

/**
 * @author Administrator
 * @date 2022-11-19
 */
@Service
@Transactional(rollbackFor = PSQLException.class)
public class DishServiceImpl extends ServiceImpl<DishMapper, Dish> implements DishService {

	/**
	 * 菜品數據接口類
	 */
	@Autowired
	private DishMapper dishMapper;

	/**
	 * 分類管理接口類
	 */
	@Autowired
	private CategoryMapper categoryMapper;

	/**
	 * 菜品口味服務類
	 */
	@Autowired
	private DishFlavourService dishFlavourService;

	/**
	 * 新增菜品，同時插入菜品所對應的口味數據
	 *
	 * @param dishDto 菜品及口味數據傳輸類
	 */
	@Override
	public void saveWithFlavour(final DishDto dishDto) {
		// 保存菜品的基本信息到菜品表；
		this.save(dishDto);
		// 獲取菜品口味的集合並將菜品ID設置到口味集合中；
		final List<DishFlavour> flavors = dishDto.getFlavours().stream().map(item -> {
			item.setDishId(dishDto.getId());
			return item;
		}).collect(Collectors.toList());
		// 保存 菜品的口味數據到口味表；
		this.dishFlavourService.saveBatch(flavors);
	}

	/**
	 * 根據ID查詢菜品信息以及對應的口味信息
	 *
	 * @param id 菜品ID
	 * @return dishDto 菜品及口味數據傳輸類
	 */
	@Override
	public DishDto getByIdWithFlavour(final Long id) {
		// 查詢菜品的基本信息；
		final Dish dish = this.getById(id);
		// 查詢當前菜品所對應的口味信息；
		final LambdaQueryWrapper<DishFlavour> queryWrapper = new LambdaQueryWrapper<>();
		queryWrapper.eq(DishFlavour::getId, dish.getId());
		// 獲取菜品口味列表；
		final List<DishFlavour> flavors = this.dishFlavourService.list(queryWrapper);
		// 聲明一個菜品及口味數據傳輸類對象並拷貝屬性；
		final DishDto dishDto = new DishDto();
		BeanUtils.copyProperties(dish, dishDto);
		dishDto.setFlavours(flavors);
		return dishDto;
	}

	/**
	 * 根據菜品集合批量停發售
	 *
	 * @param status     在售狀態
	 * @param dishIdList 菜品集合
	 */
	@Override
	public void batchUpdateByIds(final String status, final List<Long> dishIdList) {
		if (StringUtils.isEqual("0", status)) {
			dishIdList.forEach(item -> {
				final Dish dish = this.dishMapper.selectById(item);
				dish.setStatus("1");
				this.dishMapper.updateById(dish);
			});
		} else if (StringUtils.isEqual("1", status)) {
			dishIdList.forEach(item -> {
				final Dish dish = this.dishMapper.selectById(item);
				dish.setStatus("0");
				this.dishMapper.updateById(dish);
			});
		} else {
			throw new CustomException(CustomMessages.ERP017);
		}
	}

	/**
	 * 修改菜品信息並同時插入菜品所對應的口味數據
	 *
	 * @param dishDto 菜品及口味數據傳輸類
	 */
	@Override
	public void updateWithFlavour(final DishDto dishDto) {
		// 更新菜品信息；
		this.updateById(dishDto);
		// 清理當前菜品所對應的口味信息；
		final LambdaQueryWrapper<DishFlavour> queryWrapper = new LambdaQueryWrapper<>();
		queryWrapper.eq(DishFlavour::getId, dishDto.getId());
		this.dishFlavourService.remove(queryWrapper);
		// 添加當前菜品的口味數據；
		List<DishFlavour> flavors = dishDto.getFlavours();
		// 將菜品ID設置到口味集合中；
		flavors = flavors.stream().map(item -> {
			item.setDishId(dishDto.getId());
			return item;
		}).collect(Collectors.toList());
		this.dishFlavourService.saveBatch(flavors);
	}

	/**
	 * 回顯菜品表單數據
	 *
	 * @param dish 實體類對象
	 * @return List<Dish>
	 */
	@Override
	public List<DishDto> findList(final Dish dish) {
		// 創建條件構造器；
		final LambdaQueryWrapper<Dish> queryWrapper = Wrappers.lambdaQuery(new Dish());
		// 添加搜索條件；
		queryWrapper.eq(dish.getCategoryId() != null, Dish::getCategoryId, dish.getCategoryId());
		queryWrapper.eq(Dish::getStatus, "ea");
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
			// 獲取分類ID；
			final Long categoryId = item.getCategoryId();
			// 根據ID查詢分類對象；
			final Category category = this.categoryMapper.selectById(categoryId);
			if (category != null) {
				// 獲取分類名稱；
				final String categoryName = category.getName();
				// 存儲於DTO對象中並返回；
				dishDto.setCategoryName(categoryName);
			}
			// 當前菜品的ID；
			final Long dishId = item.getId();
			// 創建條件構造器；
			final LambdaQueryWrapper<DishFlavour> queryWrapper2 = Wrappers.lambdaQuery(new DishFlavour());
			queryWrapper2.eq(DishFlavour::getDishId, dishId);
			// 檢索口味信息；
			final List<DishFlavour> flavors = this.dishFlavourService.list(queryWrapper2);
			dishDto.setFlavours(flavors);
			return dishDto;
		}).collect(Collectors.toList());
	}

	/**
	 * 查詢分頁數據
	 *
	 * @param pageNum  頁碼
	 * @param pageSize 頁面大小
	 * @param keyword  檢索關鍵詞
	 * @return Page<DishDto>
	 */
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
		final Page<Dish> dishPage = this.dishMapper.selectPage(pageInfo, queryWrapper);
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
}
