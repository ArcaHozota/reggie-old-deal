package jp.co.reggie.mbpdeal.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

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

	/**
	 * 根據套餐ID批量執行刪除
	 *
	 * @param smIdList 套餐ID集合
	 */
	void batchRemoveBySmIds(@Param("smIdList") List<Long> smIdList);
}
