package jp.co.reggie.mbpdeal.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import jp.co.reggie.mbpdeal.entity.Dish;

/**
 * 菜品實體類接口
 *
 * @author Administrator
 * @date 2022-11-19
 */
@Mapper
public interface DishMapper extends BaseMapper<Dish> {
}
