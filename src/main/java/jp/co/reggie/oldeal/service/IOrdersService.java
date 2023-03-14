package jp.co.reggie.oldeal.service;

import java.time.LocalDateTime;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

import jp.co.reggie.oldeal.entity.Orders;

/**
 * @author Administrator
 */
public interface IOrdersService extends IService<Orders> {

	/**
	 * 分頁查詢
	 *
	 * @param pageNum     頁碼
	 * @param pageSize    頁面大小
	 * @param orderId     訂單ID
	 * @param beginTime   開始時間
	 * @param termialTime 截止時間
	 * @return Page<Orders>
	 */
	Page<Orders> pagination(Integer pageNum, Integer pageSize, Long orderId, LocalDateTime beginTime,
			LocalDateTime terminalTime);
}
