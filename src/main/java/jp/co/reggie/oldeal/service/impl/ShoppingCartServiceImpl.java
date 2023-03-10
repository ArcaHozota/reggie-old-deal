package jp.co.reggie.oldeal.service.impl;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import jp.co.reggie.oldeal.entity.ShoppingCart;
import jp.co.reggie.oldeal.mapper.ShoppingCartMapper;
import jp.co.reggie.oldeal.service.ShoppingCartService;

/**
 * @author Administrator
 * @date 2022-11-19
 */
@Service
public class ShoppingCartServiceImpl extends ServiceImpl<ShoppingCartMapper, ShoppingCart>
		implements ShoppingCartService {
}
