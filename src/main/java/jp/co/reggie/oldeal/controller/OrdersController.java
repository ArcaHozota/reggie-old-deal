package jp.co.reggie.oldeal.controller;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import jp.co.reggie.oldeal.entity.Orders;
import jp.co.reggie.oldeal.service.IOrdersService;
import jp.co.reggie.oldeal.utils.Reggie;
import lombok.extern.slf4j.Slf4j;

/**
 * 訂單管理控制器
 *
 * @author Administrator
 */
@Slf4j
@RestController
@RequestMapping("/order")
public class OrdersController {

	@Autowired
	private IOrdersService ordersService;

	public Reggie<Page<Orders>> pagination(@RequestParam("pageNum") final Integer pageNum,
			@RequestParam("pageSize") final Integer pageSize,
			@RequestParam(value = "number", required = false) final Long orderId,
			@RequestParam(value = "beginTime", required = false) final LocalDateTime beginTime,
			@RequestParam(value = "terminalTime", required = false) final LocalDateTime terminalTime) {
		final Page<Orders> pageInfo = this.ordersService.pagination(pageNum, pageSize, orderId, beginTime,
				terminalTime);
		return Reggie.success(pageInfo);
	}
}
