package jp.co.reggie.oldeal.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
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

	/**
	 * 訂單實體類接口
	 */
	@Autowired
	private OrdersMapper ordersMapper;

	/**
	 * 分頁查詢
	 *
	 * @param pageNum      頁碼
	 * @param pageSize     頁面大小
	 * @param orderId      訂單ID
	 * @param beginTime    開始時間
	 * @param terminalTime 截止時間
	 * @return Page<Orders>
	 */
	@Override
	public Page<Orders> pagination(final Integer pageNum, final Integer pageSize, final Long orderId,
			final String beginTime, final String terminalTime) {
		// 聲明分頁構造器對象；
		final Page<Orders> pageInfo = Page.of(pageNum, pageSize);
		// 創建條件構造器；
		final LambdaQueryWrapper<Orders> queryWrapper = new LambdaQueryWrapper<>();
		// 添加過濾條件；
		queryWrapper.eq(orderId != null, Orders::getId, orderId);
		queryWrapper.ge(beginTime != null, Orders::getOrderTime, beginTime);
		queryWrapper.le(terminalTime != null, Orders::getOrderTime, terminalTime);
		// 執行分頁查詢；
		return this.ordersMapper.selectPage(pageInfo, queryWrapper);
	}
}
