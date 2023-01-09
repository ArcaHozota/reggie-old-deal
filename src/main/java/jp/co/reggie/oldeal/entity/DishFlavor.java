package jp.co.reggie.oldeal.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import jp.co.reggie.oldeal.handler.DatabaseListener;
import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 菜品口味實體類
 *
 * @author Administrator
 */
@Getter
@Setter
@Entity
@NoArgsConstructor
@EntityListeners(DatabaseListener.class)
@Table(name = "dish_flavor")
public class DishFlavor implements Serializable {

	private static final long serialVersionUID = 6752106293794210881L;

	/**
	 * ID
	 */
	@Id
	@GenericGenerator(name = "snowflakeId", strategy = "com.itheima.reggie.utils.SnowflakeIdGenerator")
	@GeneratedValue(generator = "snowflakeId")
	private Long id;

	/**
	 * 菜品ID
	 */
	@Column(name = "dish_id", nullable = false)
	private Long dishId;

	/**
	 * 口味名稱
	 */
	@Column(nullable = false)
	private String name;

	/**
	 * 口味數據list
	 */
	private String value;

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
