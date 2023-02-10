package jp.co.reggie.oldeal.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import jp.co.reggie.oldeal.entity.Employee;

/**
 * 員工管理實體類接口
 *
 * @author Administrator
 */
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

	Page<Employee> getByNames(@Param("keyword") String keyword, Pageable pageable);
}