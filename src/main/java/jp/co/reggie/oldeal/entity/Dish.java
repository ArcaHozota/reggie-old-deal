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

import jp.co.reggie.oldeal.handler.DatabaseListener;
import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 菜品實體類
 *
 * @author Administrator
 */
@Getter
@Setter
@Entity
@NoArgsConstructor
@EntityListeners(DatabaseListener.class)
@Table(name = "dish")
public class Dish implements Serializable {

	private static final long serialVersionUID = 6089472680388107154L;

	/**
	 * ID
	 */
	@Id
	@GenericGenerator(name = "snowflakeId", strategy = "com.itheima.reggie.utils.SnowflakeIdGenerator")
	@GeneratedValue(generator = "snowflakeId")
	private Long id;

	/**
	 * 菜品名稱
	 */
	@Column(nullable = false)
	private String name;

	/**
	 * 菜品分類ID
	 */
	@Column(name = "category_id", nullable = false)
	private Long categoryId;

	/**
	 * 菜品價格
	 */
	private BigDecimal price;

	/**
	 * 商品碼
	 */
	@Column(nullable = false)
	private String code;

	/**
	 * 圖片
	 */
	@Column(nullable = false)
	private String image;

	/**
	 * 描述信息
	 */
	private String description;

	/**
	 * 菜品銷售狀態:0停售, 1在售;
	 */
	@Column(nullable = false)
	private Integer status;

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
