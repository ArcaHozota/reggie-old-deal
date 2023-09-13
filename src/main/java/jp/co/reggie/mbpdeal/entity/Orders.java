package jp.co.reggie.mbpdeal.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;

/**
 * 訂單實體類
 */
@Data
@TableName(value = "REGGIE_ORDERS")
public class Orders implements Serializable {

	private static final long serialVersionUID = -4760386733875449380L;

	/**
	 * ID
	 */
	@TableId
	private Long id;

	/**
	 * 訂單號
	 */
	private String number;

	/**
	 * 訂單狀態: 1待付款，2待派送，3已派送，4已完成，5已取消
	 */
	private Integer status;

	/**
	 * 客戸ID
	 */
	private Long userId;

	/**
	 * 派送地址ID
	 */
	@TableField(value = "ADDRESSBOOK_ID")
	private Long addressBookId;

	/**
	 * 訂單生成時間
	 */
	private LocalDateTime ordersTime;

	/**
	 * 付款時間
	 */
	private LocalDateTime checkoutTime;

	/**
	 * 支付方式
	 */
	private String paymentMethod;

	/**
	 * 實收金額
	 */
	private BigDecimal amount;

	/**
	 * 備注
	 */
	private String remark;

	/**
	 * 收貨人手機號
	 */
	@TableField(value = "PHONE_NUMBER")
	private String phoneNo;

	/**
	 * 派送地址
	 */
	private String address;

	/**
	 * 客戸名稱
	 */
	private String userName;

	/**
	 * 簽收人昵稱
	 */
	private String consignee;
}
