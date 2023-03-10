package jp.co.reggie.oldeal.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import jp.co.reggie.oldeal.entity.DishFlavor;

/**
 * 菜品口味實體類接口
 *
 * @author Administrator
 * @date 2022-11-23
 */
@Mapper
public interface DishFlavorMapper extends BaseMapper<DishFlavor> {
}
