package jp.co.reggie.oldeal.controller;

import java.util.List;
import java.util.Optional;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jp.co.reggie.oldeal.common.Constants;
import jp.co.reggie.oldeal.common.CustomMessage;
import jp.co.reggie.oldeal.entity.Employee;
import jp.co.reggie.oldeal.mapper.EmployeeRepository;
import jp.co.reggie.oldeal.utils.PaginationImpl;
import jp.co.reggie.oldeal.utils.Reggie;
import jp.co.reggie.oldeal.utils.StringUtils;
import lombok.extern.slf4j.Slf4j;

/**
 * 員工管理控制器
 *
 * @author Administrator
 */
@Slf4j
@RestController
@RequestMapping("/employee")
public class EmployeeController {

	@Resource
	private EmployeeRepository employeeRepository;

	/**
	 * 員工登錄
	 *
	 * @param request  請求
	 * @param employee 員工信息對象
	 * @return R.success(實體類對象)
	 */
	@PostMapping("/login")
	public Reggie<Employee> login(final HttpServletRequest request, @RequestBody final Employee employee) {
		// 將頁面提交的密碼進行MD5加密；
		final String password = DigestUtils.md5DigestAsHex(employee.getPassword().getBytes()).toUpperCase();
		// 根據頁面提交的用戸名查詢數據庫；
		final Employee employee2 = new Employee();
		employee2.setUsername(employee.getUsername());
		final ExampleMatcher matcher = ExampleMatcher.matching().withStringMatcher(ExampleMatcher.StringMatcher.EXACT)
				.withMatcher(employee.getUsername(), ExampleMatcher.GenericPropertyMatchers.exact());
		final Example<Employee> example = Example.of(employee2, matcher);
		// 獲取Optional對象；
		final Optional<Employee> aEmployee = this.employeeRepository.findOne(example);
		// 如果沒有查詢到或者密碼錯誤則返回登錄失敗；
		if (aEmployee.isEmpty() || StringUtils.isNotEqual(password, aEmployee.get().getPassword())) {
			return Reggie.error(Constants.LOGIN_FAILED);
		}
		// 查看用戸狀態，如果已被禁用，則返回賬號已禁用；
		if (aEmployee.get().getStatus() == 0) {
			return Reggie.error(Constants.FORBIDDEN);
		}
		// 登錄成功，將員工ID存入Session並返回登錄成功；
		request.getSession().setAttribute(Constants.getEntityName(employee), aEmployee.get().getId());
		return Reggie.success(aEmployee.get());
	}

	/**
	 * 員工退出登錄
	 *
	 * @param request 請求
	 * @return R.success(退出登錄的信息)
	 */
	@PostMapping("/logout")
	public Reggie<String> logout(final HttpServletRequest request) {
		// 清除Session中保存的當前登錄員工的ID；
		request.getSession().removeAttribute(Constants.getEntityName(new Employee()));
		return Reggie.success(CustomMessage.SRP007);
	}

	/**
	 * 保存新增員工
	 *
	 * @param employee 實體類對象
	 * @return R.success(成功增加員工的信息)
	 */
	@PostMapping
	public Reggie<String> save(@RequestBody final Employee employee) {
		log.info("員工信息：{}", employee.toString());
		// 設置初始密碼，需進行MD5加密；
		employee.setPassword(DigestUtils.md5DigestAsHex(Constants.PRIMARY_CODE.getBytes()).toUpperCase());
		this.employeeRepository.save(employee);
		return Reggie.success(CustomMessage.SRP006);
	}

	/**
	 * 員工信息分頁查詢
	 *
	 * @param pageNum  頁碼
	 * @param pageSize 頁面大小
	 * @param keyword  檢索文
	 * @return R.success(分頁信息)
	 */
	@GetMapping("/page")
	public Reggie<Page<Employee>> pagination(@RequestParam("pageNum") final Integer pageNum,
			@RequestParam("pageSize") final Integer pageSize,
			@RequestParam(name = "name", required = false) final String keyword) {
		final PageRequest pageRequest = PageRequest.of(pageNum, pageSize);
		Page<Employee> pageInfo;
		if (StringUtils.isNotEmpty(keyword)) {
			pageInfo = this.employeeRepository.getByNames(keyword, pageRequest);
		} else {
			pageInfo = this.employeeRepository.getAll(pageRequest);
		}
		final List<Employee> employees = pageInfo.getContent();
		log.info(String.valueOf(employees.isEmpty()));
		final PaginationImpl<Employee> pages = new PaginationImpl<>(employees);
		return Reggie.success(pages);
	}

	/**
	 * 根據ID修改員工信息
	 *
	 * @param employee 實體類對象
	 * @return R.success(成功修改員工的信息)
	 */
	@PutMapping
	public Reggie<String> update(@RequestBody final Employee employee) {
		this.employeeRepository.saveAndFlush(employee);
		return Reggie.success(CustomMessage.SRP008);
	}

	/**
	 * 根據ID查詢員工信息
	 *
	 * @param id 員工ID
	 * @return R.success(查詢到的員工的信息)
	 */
	@GetMapping("/{id}")
	public Reggie<Employee> getById(@PathVariable final Long id) {
		log.info("根據ID查詢員工信息...");
		final Employee employee = this.employeeRepository.getById(id);
		// 如果沒有相對應的結果，則返回錯誤信息；
		if (employee == null) {
			return Reggie.error(Constants.NO_CONSEQUENCE);
		}
		return Reggie.success(employee);
	}
}
