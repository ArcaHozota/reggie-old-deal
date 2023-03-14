package jp.co.reggie.oldeal.controller;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import jp.co.reggie.oldeal.common.CustomMessages;
import jp.co.reggie.oldeal.dto.SetmealDto;
import jp.co.reggie.oldeal.service.SetmealService;
import jp.co.reggie.oldeal.utils.Reggie;
import lombok.extern.slf4j.Slf4j;

/**
 * 套餐管理控制器
 *
 * @author Administrator
 * @date 2022-11-29
 */
@Slf4j
@RestController
@RequestMapping("/setmeal")
public class SetmealController {

	@Autowired
	private SetmealService setmealService;

	/**
	 * 新增套餐
	 *
	 * @param setmealDto 數據傳輸類
	 * @return R.success(新增成功的信息)
	 */
	@PostMapping
	public Reggie<String> save(@RequestBody final SetmealDto setmealDto) {
		log.info("套餐信息：{}", setmealDto);
		// 儲存套餐；
		this.setmealService.saveWithDish(setmealDto);
		return Reggie.success(CustomMessages.SRP010);
	}

	/**
	 * 刪除套餐
	 *
	 * @param ids 套餐ID的集合
	 * @return R.success(新增成功的信息)
	 */
	@DeleteMapping
	public Reggie<String> delete(@RequestParam("ids") final List<Long> ids) {
		log.info("套餐ID：{}", ids);
		this.setmealService.removeWithDish(ids);
		return Reggie.success(CustomMessages.SRP011);
	}

	/**
	 * 分頁信息顯示
	 *
	 * @param pageNum  頁碼
	 * @param pageSize 頁面大小
	 * @param name     檢索文
	 * @return R.success(分頁信息)
	 */
	@GetMapping("/page")
	public Reggie<Page<SetmealDto>> pagination(@Param("pageNum") final Integer pageNum,
			@Param("pageSize") final Integer pageSize, @Param("name") final String keyword) {
		final Page<SetmealDto> pageInfo = this.setmealService.pagination(pageNum, pageSize, keyword);
		return Reggie.success(pageInfo);
	}
}
