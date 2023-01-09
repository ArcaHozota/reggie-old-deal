package jp.co.reggie.oldeal.repository;

import jp.co.reggie.oldeal.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 員工管理實體類接口
 *
 * @author Administrator
 */
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}