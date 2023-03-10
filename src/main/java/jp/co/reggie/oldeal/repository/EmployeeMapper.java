package jp.co.reggie.oldeal.repository;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import jp.co.reggie.newdeal.entity.Employee;

/**
 * 員工管理實體類
 *
 * @author Administrator
 * @date 2022-11-28
 */
@Mapper
public interface EmployeeMapper extends BaseMapper<Employee> {

	/**
	 * 根據所提供的用戸名進行查詢
	 *
	 * @param userName 用戸名
	 * @return Employee
	 */
	Employee selectByUserName(@Param("username") String userName);
}
