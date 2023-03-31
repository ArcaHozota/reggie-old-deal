package jp.co.reggie.oldeal.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;

import jp.co.reggie.oldeal.common.CustomMessages;
import jp.co.reggie.oldeal.entity.ShoppingCart;
import jp.co.reggie.oldeal.handler.BaseContext;
import jp.co.reggie.oldeal.service.ShoppingCartService;
import jp.co.reggie.oldeal.utils.Reggie;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/shoppingCart")
public class ShoppingCartController {

	@Autowired
	private ShoppingCartService shoppingCartService;

	/**
	 * 購物車新增
	 *
	 * @param shoppingCart 實體類對象
	 * @return R.success(實體類對象)
	 */
	@PostMapping("/add")
	public Reggie<ShoppingCart> add(@RequestBody final ShoppingCart shoppingCart) {
		log.info("購物車數據:{}", shoppingCart);
		// 設置用戸ID；
		final Long currentId = BaseContext.getCurrentId();
		shoppingCart.setUserId(currentId);
		final LambdaQueryWrapper<ShoppingCart> queryWrapper = Wrappers.lambdaQuery(new ShoppingCart());
		queryWrapper.eq(ShoppingCart::getUserId, currentId);
		// 查詢當前菜品或套餐是否已經存在於購物車中；
		final Long dishId = shoppingCart.getDishId();
		if (dishId != null) {
			queryWrapper.eq(ShoppingCart::getDishId, dishId);
		} else {
			queryWrapper.eq(ShoppingCart::getSetmealId, shoppingCart.getSetmealId());
		}
		ShoppingCart shoppingCart2 = this.shoppingCartService.getOne(queryWrapper);
		if (shoppingCart2 != null) {
			shoppingCart2.setNumber(shoppingCart2.getNumber() + 1);
			this.shoppingCartService.updateById(shoppingCart2);
		} else {
			shoppingCart.setNumber(1);
			this.shoppingCartService.save(shoppingCart);
			shoppingCart2 = shoppingCart;
		}
		return Reggie.success(shoppingCart2);
	}

	/**
	 * 查看購物車
	 *
	 * @return R.success(list)
	 */
	@GetMapping("/list")
	public Reggie<List<ShoppingCart>> list() {
		log.info("查看購物車...");
		final LambdaQueryWrapper<ShoppingCart> queryWrapper = Wrappers.lambdaQuery(new ShoppingCart());
		queryWrapper.eq(ShoppingCart::getUserId, BaseContext.getCurrentId());
		queryWrapper.orderByAsc(ShoppingCart::getCreateTime);
		final List<ShoppingCart> list = this.shoppingCartService.list(queryWrapper);
		return Reggie.success(list);
	}

	/**
	 * 清空購物車
	 *
	 * @return R.success(list)
	 */
	@DeleteMapping("/clean")
	public Reggie<String> clean() {
		log.info("清空購物車...");
		final LambdaQueryWrapper<ShoppingCart> queryWrapper = Wrappers.lambdaQuery(new ShoppingCart());
		queryWrapper.eq(ShoppingCart::getUserId, BaseContext.getCurrentId());
		this.shoppingCartService.remove(queryWrapper);
		return Reggie.success(CustomMessages.SRP018);
	}
}
