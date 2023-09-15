package jp.co.reggie.mbpdeal.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;

/**
 * 分類管理實體類
 *
 * @author Administrator
 */
@Data
@TableName(value = "category")
public class Category implements Serializable {

	private static final long serialVersionUID = -5583580956537498025L;

	/**
	 * ID
	 */
	@TableId
	private Long id;

	/**
	 * 類型：1、菜品分類；2、套餐分類；
	 */
	private Integer type;

	/**
	 * 分類名稱
	 */
	private String name;

	/**
	 * 順序
	 */
	private Integer sort;

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
	@TableField(value = "is_deleted")
	private String logicDeleteFlg;
}
