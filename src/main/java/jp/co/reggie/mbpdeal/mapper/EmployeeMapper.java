package jp.co.reggie.mbpdeal.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import jp.co.reggie.mbpdeal.entity.Employee;

/**
 * 員工管理實體類
 *
 * @author Administrator
 * @date 2022-11-28
 */
@Mapper
public interface EmployeeMapper extends BaseMapper<Employee> {
}
