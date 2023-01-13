package jp.co.reggie.oldeal.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonFormat;

import jp.co.reggie.oldeal.handler.DatabaseListener;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 套餐與菜品關係實體類
 *
 * @author Administrator
 */
@Getter
@Setter
@Entity
@NoArgsConstructor
@EntityListeners(DatabaseListener.class)
@Table(name = "setmeal_dish")
public class SetmealDish implements Serializable {

	private static final long serialVersionUID = -641135780975738908L;

	/**
	 * ID
	 */
	@Id
	@GenericGenerator(name = "snowflakeId", strategy = "jp.co.reggie.oldeal.utils.SnowflakeIdGenerator")
	@GeneratedValue(generator = "snowflakeId")
	private Long id;

	/**
	 * 套餐ID
	 */
	@Column(name = "setmeal_id", nullable = false)
	private Long setmealId;

	/**
	 * 菜品ID
	 */
	@Column(name = "dish_id", nullable = false)
	private Long dishId;

	/**
	 * 菜品名稱(冗餘字段)
	 */
	private String name;

	/**
	 * 菜品原價(冗餘字段)
	 */
	private BigDecimal price;

	/**
	 * 份數
	 */
	@Column(nullable = false)
	private Integer copies;

	/**
	 * 排序
	 */
	@Column(nullable = false)
	private Integer sort;

	/**
	 * 創建時間
	 */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@Column(name = "create_time", updatable = false, nullable = false)
	private LocalDateTime createTime;

	/**
	 * 更新時間
	 */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@Column(name = "update_time", nullable = false)
	private LocalDateTime updateTime;

	/**
	 * 創建人
	 */
	@Column(name = "create_user", updatable = false, nullable = false)
	private Long createUser;

	/**
	 * 修改者
	 */
	@Column(name = "update_user", nullable = false)
	private Long updateUser;

	/**
	 * 邏輯刪除字段
	 */
	@Column(name = "is_deleted", nullable = false)
	private Integer isDeleted;
}
