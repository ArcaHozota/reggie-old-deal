package jp.co.reggie.mbpdeal.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;

/**
 * 套餐實體類
 *
 * @author Administrator
 */
@Data
@TableName(value = "setmeal")
public class Setmeal implements Serializable {

	private static final long serialVersionUID = 4020217756505140488L;

	/**
	 * ID
	 */
	@TableId
	private Long id;

	/**
	 * 分類ID
	 */
	private Long categoryId;

	/**
	 * 套餐名稱
	 */
	private String name;

	/**
	 * 套餐價格
	 */
	private BigDecimal price;

	/**
	 * 套餐在售狀態:1在售, 0停售;
	 */
	private Integer status;

	/**
	 * 編碼
	 */
	private String code;

	/**
	 * 描述信息
	 */
	private String description;

	/**
	 * 圖片
	 */
	private String image;

	/**
	 * 創建時間
	 */
	@TableField(fill = FieldFill.INSERT)
	private LocalDateTime createdTime;

	/**
	 * 更新時間
	 */
	@TableField(fill = FieldFill.INSERT_UPDATE)
	private LocalDateTime updatedTime;

	/**
	 * 創建人
	 */
	@TableField(fill = FieldFill.INSERT)
	private Long createdUser;

	/**
	 * 修改者
	 */
	@TableField(fill = FieldFill.INSERT_UPDATE)
	private Long updatedUser;

	/**
	 * 邏輯刪除字段
	 */
	@TableLogic
	private String deleteFlg;
}