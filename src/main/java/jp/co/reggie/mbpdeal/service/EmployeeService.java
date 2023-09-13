package jp.co.reggie.mbpdeal.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

import jp.co.reggie.mbpdeal.entity.Employee;

/**
 * @author Administrator
 */
public interface EmployeeService extends IService<Employee> {

	/**
	 * 登錄處理
	 *
	 * @param employee 員工信息對象
	 * @return Employee
	 */
	Employee login(Employee employee);

	/**
	 * 分頁查詢
	 *
	 * @param pageNum  頁碼
	 * @param pageSize 頁面大小
	 * @param keyword  檢索文
	 * @return Page<Employee>
	 */
	Page<Employee> pagination(Integer pageNum, Integer pageSize, String keyword);
}
