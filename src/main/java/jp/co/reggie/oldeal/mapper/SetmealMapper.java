package jp.co.reggie.oldeal.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import jp.co.reggie.newdeal.entity.Setmeal;

/**
 * 套餐實體類接口
 *
 * @author Administrator
 * @date 2022-11-19
 */
@Mapper
public interface SetmealMapper extends BaseMapper<Setmeal> {
}
