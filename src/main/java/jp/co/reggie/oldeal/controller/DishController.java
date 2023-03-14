package jp.co.reggie.oldeal.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import jp.co.reggie.oldeal.common.CustomMessages;
import jp.co.reggie.oldeal.dto.DishDto;
import jp.co.reggie.oldeal.entity.Dish;
import jp.co.reggie.oldeal.service.DishService;
import jp.co.reggie.oldeal.utils.Reggie;
import lombok.extern.slf4j.Slf4j;

/**
 * 菜品管理控制器
 *
 * @author Administrator
 */
@Slf4j
@RestController
@RequestMapping("/dish")
public class DishController {

	@Autowired
	private DishService dishService;

	/**
	 * 新增菜品
	 *
	 * @param dishDto 數據傳輸類對象
	 * @return R.success(成功新增菜品的信息)
	 */
	@PostMapping
	public Reggie<String> save(@RequestBody final DishDto dishDto) {
		log.info("新增菜品：{}" + dishDto.toString());
		this.dishService.saveWithFlavour(dishDto);
		return Reggie.success(CustomMessages.SRP004);
	}

	/**
	 * 菜品信息分頁查詢
	 *
	 * @param pageNum  頁碼
	 * @param pageSize 頁面大小
	 * @return R.success(分頁信息)
	 */
	@GetMapping("/page")
	public Reggie<Page<DishDto>> pagination(@RequestParam("pageNum") final Integer pageNum,
			@RequestParam("pageSize") final Integer pageSize,
			@RequestParam(value = "name", required = false) final String keyword) {
		final Page<DishDto> pageInfo = this.dishService.pagination(pageNum, pageSize, keyword);
		return Reggie.success(pageInfo);
	}

	/**
	 * 根據ID顯示菜品信息
	 *
	 * @param id 菜品ID
	 * @return R.success(菜品信息)
	 */
	@GetMapping("/{id}")
	public Reggie<DishDto> getDishInfo(@PathVariable final Long id) {
		// 根據ID查詢菜品信息以及對應的口味信息；
		final DishDto dishWithFlavors = this.dishService.getByIdWithFlavour(id);
		return Reggie.success(dishWithFlavors);
	}

	/**
	 * 修改菜品信息
	 *
	 * @param dishDto 數據傳輸類對象
	 * @return R.success(菜品更新成功的信息)
	 */
	@PutMapping
	public Reggie<String> update(@RequestBody final DishDto dishDto) {
		log.info(dishDto.toString());
		this.dishService.updateWithFlavour(dishDto);
		return Reggie.success(CustomMessages.SRP005);
	}

	/**
	 * 回顯菜品表單數據
	 *
	 * @param dish 實體類對象
	 * @return R.success(菜品信息)
	 */
	@GetMapping("/list")
	public Reggie<List<Dish>> list(@RequestBody final Dish dish) {
		final List<Dish> list = this.dishService.findList(dish);
		return Reggie.success(list);
	}

	/**
	 * 修改菜品在售狀態
	 *
	 * @param dishState 菜品狀態
	 * @return R.success(修改成功信息)
	 */
	@PostMapping("/status/{status}")
	public Reggie<String> changeStatus(@PathVariable final String status, @RequestParam("ids") final Long[] ids) {
		this.dishService.batchUpdateByIds(status, Arrays.asList(ids));
		return Reggie.success(CustomMessages.SRP016);
	}
}
