package jp.co.reggie.oldeal.entity;

import java.io.Serializable;
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
 * 分類管理實體類
 *
 * @author Administrator
 */
@Getter
@Setter
@Entity
@NoArgsConstructor
@EntityListeners(DatabaseListener.class)
@Table(name = "category")
public class Category implements Serializable {

	private static final long serialVersionUID = -5583580956537498025L;

	/**
	 * ID
	 */
	@Id
	@GenericGenerator(name = "snowflakeId", strategy = "jp.co.reggie.oldeal.utils.SnowflakeIdGenerator")
	@GeneratedValue(generator = "snowflakeId")
	private Long id;

	/**
	 * 類型：1、菜品分類；2、套餐分類；
	 */
	private Integer type;

	/**
	 * 分類名稱
	 */
	@Column(nullable = false)
	private String name;

	/**
	 * 順序
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
