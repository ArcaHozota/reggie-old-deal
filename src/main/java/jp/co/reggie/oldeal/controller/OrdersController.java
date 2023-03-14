package jp.co.reggie.oldeal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jp.co.reggie.oldeal.service.IOrdersService;
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
}
