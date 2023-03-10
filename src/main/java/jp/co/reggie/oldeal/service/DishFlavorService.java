package jp.co.reggie.oldeal.service;

import java.util.List;

import com.baomidou.mybatisplus.extension.service.IService;

import jp.co.reggie.oldeal.entity.DishFlavor;

/**
 * @author Administrator
 */
public interface DishFlavorService extends IService<DishFlavor> {

	/**
	 * 根據菜品ID查詢口味信息
	 *
	 * @param dishId 菜品ID
	 * @return 菜品口味列表
	 */
	List<DishFlavor> getFlavours(Long dishId);
}
