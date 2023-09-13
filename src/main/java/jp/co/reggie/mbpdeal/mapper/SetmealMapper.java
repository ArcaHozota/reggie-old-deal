package jp.co.reggie.mbpdeal.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import jp.co.reggie.mbpdeal.entity.Setmeal;

/**
 * 套餐實體類接口
 *
 * @author Administrator
 * @date 2022-11-19
 */
@Mapper
public interface SetmealMapper extends BaseMapper<Setmeal> {

	/**
	 * 根據ID批量更新套餐狀態
	 *
	 * @param status   狀態
	 * @param smIdList 套餐ID集合
	 */
	void batchUpdateByIds(@Param("status") String status, @Param("smIdList") List<Long> smIdList);
}
