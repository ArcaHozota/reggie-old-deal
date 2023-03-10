package jp.co.reggie.oldeal.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import jp.co.reggie.newdeal.entity.DishFlavor;

/**
 * 菜品口味實體類接口
 *
 * @author Administrator
 * @date 2022-11-23
 */
@Mapper
public interface DishFlavorMapper extends BaseMapper<DishFlavor> {

	/**
	 * 根據菜品ID查詢口味信息
	 *
	 * @param dishId 菜品ID
	 * @return 菜品口味列表
	 */
	List<DishFlavor> getFlavoursById(@Param("dishId") Long dishId);
}
