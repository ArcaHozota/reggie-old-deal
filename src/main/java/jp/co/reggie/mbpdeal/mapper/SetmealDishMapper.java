package jp.co.reggie.mbpdeal.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import jp.co.reggie.mbpdeal.entity.SetmealDish;

/**
 * 套餐與菜品關係實體類接口
 *
 * @author Administrator
 * @date 2022-11-29
 */
@Mapper
public interface SetmealDishMapper extends BaseMapper<SetmealDish> {
}
