package jp.co.reggie.mbpdeal.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;

/**
 * 菜品口味實體類
 *
 * @author Administrator
 */
@Data
@TableName(value = "dish_flavor")
public class DishFlavour implements Serializable {

	private static final long serialVersionUID = 6752106293794210881L;

	/**
	 * ID
	 */
	@TableId
	private Long id;

	/**
	 * 菜品ID
	 */
	private Long dishId;

	/**
	 * 口味名稱
	 */
	private String name;

	/**
	 * 口味數據list
	 */
	private String value;

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
}
