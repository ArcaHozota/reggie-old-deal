package jp.co.reggie.oldeal.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import jp.co.reggie.newdeal.entity.Dish;

/**
 * 菜品實體類接口
 *
 * @author Administrator
 * @date 2022-11-19
 */
@Mapper
public interface DishMapper extends BaseMapper<Dish> {

	/**
	 * 根據菜品ID集合批量停發售
	 *
	 * @param dishList 菜品ID集合
	 */
	void bulkUpdateByIds(@Param("list") List<Long> dishList, @Param("status") String status,
			@Param("updateTime") LocalDateTime upTime, @Param("updateUser") Long upId);
}
