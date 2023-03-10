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
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import jp.co.reggie.oldeal.common.CustomException;
import jp.co.reggie.oldeal.common.CustomMessage;
import jp.co.reggie.oldeal.dto.DishDto;
import jp.co.reggie.oldeal.entity.Dish;
import jp.co.reggie.oldeal.entity.DishFlavor;
import jp.co.reggie.oldeal.mapper.DishMapper;
import jp.co.reggie.oldeal.service.DishFlavorService;
import jp.co.reggie.oldeal.service.DishService;
import jp.co.reggie.oldeal.utils.StringUtils;

/**
 * @author Administrator
 * @date 2022-11-19
 */
@Service
public class DishServiceImpl extends ServiceImpl<DishMapper, Dish> implements DishService {

	/**
	 * 菜品數據接口類
	 */
	@Autowired
	private DishMapper dishMapper;

	/**
	 * 菜品口味服務類
	 */
	@Autowired
	private DishFlavorService dishFlavorService;

	/**
	 * 新增菜品，同時插入菜品所對應的口味數據
	 *
	 * @param dishDto 菜品及口味數據傳輸類
	 */
	@Transactional(rollbackFor = PSQLException.class)
	@Override
	public void saveWithFlavour(final DishDto dishDto) {
		// 保存菜品的基本信息到菜品表；
		this.save(dishDto);
		// 獲取菜品口味的集合並將菜品ID設置到口味集合中；
		final List<DishFlavor> flavors = dishDto.getFlavors().stream().map((item) -> {
			item.setDishId(dishDto.getId());
			return item;
		}).collect(Collectors.toList());
		// 保存 菜品的口味數據到口味表；
		this.dishFlavorService.saveBatch(flavors);
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
		final LambdaQueryWrapper<DishFlavor> queryWrapper = new LambdaQueryWrapper<>();
		queryWrapper.eq(DishFlavor::getId, dish.getId());
		// 獲取菜品口味列表；
		final List<DishFlavor> flavors = this.dishFlavorService.list(queryWrapper);
		// 聲明一個菜品及口味數據傳輸類對象並拷貝屬性；
		final DishDto dishDto = new DishDto();
		BeanUtils.copyProperties(dish, dishDto);
		dishDto.setFlavors(flavors);
		return dishDto;
	}

	/**
	 * 根據菜品集合批量停發售
	 *
	 * @param status   在售狀態
	 * @param dishList 菜品集合
	 */
	@Override
	public void batchUpdateByIds(final String status, final List<Long> dishIdList) {
		if (StringUtils.isEqual("ea", status)) {
			dishIdList.forEach(item -> {
				final Dish dish = this.dishMapper.selectById(item);
				dish.setStatus("ec");
				this.dishMapper.updateById(dish);
			});
		} else if (StringUtils.isEqual("ec", status)) {
			dishIdList.forEach(item -> {
				final Dish dish = this.dishMapper.selectById(item);
				dish.setStatus("ea");
				this.dishMapper.updateById(dish);
			});
		} else {
			throw new CustomException(CustomMessage.ERP017);
		}
	}

	/**
	 * 修改菜品信息並同時插入菜品所對應的口味數據
	 *
	 * @param dishDto 菜品及口味數據傳輸類
	 */
	@Transactional(rollbackFor = PSQLException.class)
	@Override
	public void updateWithFlavour(final DishDto dishDto) {
		// 更新菜品信息；
		this.updateById(dishDto);
		// 清理當前菜品所對應的口味信息；
		final LambdaQueryWrapper<DishFlavor> queryWrapper = new LambdaQueryWrapper<>();
		queryWrapper.eq(DishFlavor::getId, dishDto.getId());
		this.dishFlavorService.remove(queryWrapper);
		// 添加當前菜品的口味數據；
		List<DishFlavor> flavors = dishDto.getFlavors();
		// 將菜品ID設置到口味集合中；
		flavors = flavors.stream().map((item) -> {
			item.setDishId(dishDto.getId());
			return item;
		}).collect(Collectors.toList());
		this.dishFlavorService.saveBatch(flavors);
	}

	/**
	 * 回顯菜品表單數據
	 *
	 * @param dish 實體類對象
	 * @return List<Dish>
	 */
	@Override
	public List<Dish> findList(final Dish dish) {
		// 創建條件構造器；
		final LambdaQueryWrapper<Dish> queryWrapper = Wrappers.lambdaQuery(new Dish());
		// 添加搜索條件；
		queryWrapper.eq(dish.getCategoryId() != null, Dish::getCategoryId, dish.getCategoryId());
		queryWrapper.eq(Dish::getStatus, "ea");
		// 添加排序條件；
		queryWrapper.orderByAsc(Dish::getSort).orderByDesc(Dish::getUpdateTime);
		// 查詢菜品信息並返回；
		return this.dishMapper.selectList(queryWrapper);
	}
}
