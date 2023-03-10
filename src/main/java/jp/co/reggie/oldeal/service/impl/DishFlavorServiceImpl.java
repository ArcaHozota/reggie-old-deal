package jp.co.reggie.oldeal.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import jp.co.reggie.oldeal.entity.DishFlavor;
import jp.co.reggie.oldeal.mapper.DishFlavorMapper;
import jp.co.reggie.oldeal.service.DishFlavorService;

/**
 * @author Administrator
 * @date 2022-11-23
 */
@Service
public class DishFlavorServiceImpl extends ServiceImpl<DishFlavorMapper, DishFlavor> implements DishFlavorService {

	@Resource
	private DishFlavorMapper dishFlavorMapper;

	/**
	 * 根據菜品ID查詢口味信息
	 *
	 * @param dishId 菜品ID
	 * @return 菜品口味列表
	 */
	@Override
	public List<DishFlavor> getFlavours(final Long dishId) {
		return this.dishFlavorMapper.getFlavoursById(dishId);
	}
}
