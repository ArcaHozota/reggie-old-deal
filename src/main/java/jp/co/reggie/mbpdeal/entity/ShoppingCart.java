package jp.co.reggie.mbpdeal.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;

/**
 * 購物車實體類
 *
 * @author Administrator
 */
@Data
@TableName(value = "shopping_cart")
public class ShoppingCart implements Serializable {

	private static final long serialVersionUID = 1618550099529253148L;

	/**
	 * ID
	 */
	@TableId
	private Long id;

	/**
	 * 名稱
	 */
	private String name;

	/**
	 * 用戸ID
	 */
	private Long userId;

	/**
	 * 菜品ID
	 */
	private Long dishId;

	/**
	 * 套餐ID
	 */
	private Long setmealId;

	/**
	 * 口味
	 */
	private String dishFlavorId;

	/**
	 * 數量
	 */
	private Integer number;

	/**
	 * 金額
	 */
	private BigDecimal amount;

	/**
	 * 圖片
	 */
	private String image;

	/**
	 * 創建時間
	 */
	@TableField(fill = FieldFill.INSERT)
	private LocalDateTime createdTime;
}
