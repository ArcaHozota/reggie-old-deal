package jp.co.reggie.oldeal.service.impl;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import jp.co.reggie.oldeal.entity.Orders;
import jp.co.reggie.oldeal.mapper.OrdersMapper;
import jp.co.reggie.oldeal.service.IOrdersService;

/**
 * @author Administrator
 * @date 2023-02-18
 */
@Service
public class OrdersServiceImpl extends ServiceImpl<OrdersMapper, Orders> implements IOrdersService {
}
