package jp.co.reggie.mbpdeal.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import jp.co.reggie.mbpdeal.common.Constants;
import jp.co.reggie.mbpdeal.common.CommonMessages;
import jp.co.reggie.mbpdeal.entity.Employee;
import jp.co.reggie.mbpdeal.service.EmployeeService;
import jp.co.reggie.mbpdeal.utils.Reggie;
import jp.co.reggie.mbpdeal.utils.StringUtils;
import lombok.extern.slf4j.Slf4j;

/**
 * 員工管理控制器
 *
 * @author ArcaHozota
 * @since 1.00beta
 */
@Slf4j
@RestController
@RequestMapping("/employee")
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;

	/**
	 * 根據ID查詢員工信息
	 *
	 * @param id 員工ID
	 * @return R.success(查詢到的員工的信息)
	 */
	@GetMapping("/{id}")
	public Reggie<Employee> getById(@PathVariable final Long id) {
		log.info("根據ID查詢員工信息...");
		final Employee employee = this.employeeService.getById(id);
		// 如果沒有相對應的結果，則返回錯誤信息；
		if (employee == null) {
			return Reggie.error(CommonMessages.ERP019);
		}
		return Reggie.success(employee);
	}

	/**
	 * 員工登錄
	 *
	 * @param request  請求
	 * @param employee 員工信息對象
	 * @return R.success(實體類對象)
	 */
	@PostMapping("/login")
	public Reggie<Employee> login(final HttpServletRequest request, @RequestBody final Employee employee) {
		// 進行登錄處理；
		final Employee aEmployee = this.employeeService.login(employee);
		// 登錄成功，將員工ID存入Session並返回登錄成功；
		request.getSession().setAttribute("employee", aEmployee.getId());
		return Reggie.success(aEmployee);
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
		request.getSession().removeAttribute("employee");
		return Reggie.success(CommonMessages.SRP007);
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
		final Page<Employee> pageInfo = this.employeeService.pagination(pageNum, pageSize, keyword);
		return Reggie.success(pageInfo);
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
		employee.setStatus(Constants.STATUS_VALID);
		final String genderString = StringUtils.isNotEqual("1", employee.getGender()) ? "Female" : "Male";
		employee.setGender(genderString);
		this.employeeService.save(employee);
		return Reggie.success(CommonMessages.SRP006);
	}

	/**
	 * 根據ID修改員工信息
	 *
	 * @param employee 實體類對象
	 * @return R.success(成功修改員工的信息)
	 */
	@PutMapping
	public Reggie<String> update(@RequestBody final Employee employee) {
		this.employeeService.updateById(employee);
		return Reggie.success(CommonMessages.SRP008);
	}
}
