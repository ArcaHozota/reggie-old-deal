package jp.co.reggie.oldeal.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import jp.co.reggie.newdeal.entity.Category;

/**
 * 分類管理實體類接口
 *
 * @author Administrator
 * @date 2022-11-19
 */
@Mapper
public interface CategoryMapper extends BaseMapper<Category> {

	/**
	 * 根據類型查詢數據
	 *
	 * @param categoryType 類型
	 * @return List<Category>
	 */
	List<Category> selectByType(@Param("type") Integer categoryType);
}
