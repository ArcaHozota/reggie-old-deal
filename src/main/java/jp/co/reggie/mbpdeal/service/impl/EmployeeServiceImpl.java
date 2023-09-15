package jp.co.reggie.mbpdeal.service.impl;

import org.postgresql.util.PSQLException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import jp.co.reggie.mbpdeal.common.Constants;
import jp.co.reggie.mbpdeal.common.CustomException;
import jp.co.reggie.mbpdeal.entity.Employee;
import jp.co.reggie.mbpdeal.mapper.EmployeeMapper;
import jp.co.reggie.mbpdeal.service.EmployeeService;
import jp.co.reggie.mbpdeal.utils.StringUtils;

/**
 * @author Administrator
 * @date 2022-11-09
 */
@Service
@Transactional(rollbackFor = PSQLException.class)
public class EmployeeServiceImpl extends ServiceImpl<EmployeeMapper, Employee> implements EmployeeService {

	/**
	 * 員工登錄處理
	 *
	 * @param employee 員工信息對象
	 * @return Employee
	 */
	@Override
	public Employee login(final Employee employee) {
		// 將頁面提交的密碼進行MD5加密；
		final String password = DigestUtils.md5DigestAsHex(employee.getPassword().getBytes()).toUpperCase();
		// 根據頁面提交的用戸名查詢數據庫；
		final LambdaQueryWrapper<Employee> queryWrapper = new LambdaQueryWrapper<>();
		queryWrapper.eq(Employee::getUsername, employee.getUsername());
		final Employee aEmployee = super.getBaseMapper().selectOne(queryWrapper);
		// 如果沒有查詢到或者密碼錯誤則返回登錄失敗；
		if (aEmployee == null || StringUtils.isNotEqual(password, aEmployee.getPassword())) {
			throw new CustomException(Constants.NOT_LOGIN);
		}
		// 查看用戸狀態，如果已被禁用，則返回賬號已禁用；
		if (aEmployee.getStatus() == 0) {
			throw new CustomException(Constants.FORBIDDEN);
		}
		return aEmployee;
	}

	/**
	 * 分頁查詢
	 *
	 * @param pageNum  頁碼
	 * @param pageSize 頁面大小
	 * @param keyword  檢索文
	 * @return Page<Employee>
	 */
	@Override
	public Page<Employee> pagination(final Integer pageNum, final Integer pageSize, final String keyword) {
		final Page<Employee> pageInfo = Page.of(pageNum, pageSize);
		final LambdaQueryWrapper<Employee> queryWrapper = new LambdaQueryWrapper<>();
		queryWrapper.like(Employee::getName, StringUtils.toHankaku(keyword));
		queryWrapper.orderByDesc(Employee::getUpdatedTime);
		return super.getBaseMapper().selectPage(pageInfo, queryWrapper);
	}
}
