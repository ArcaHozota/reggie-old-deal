package jp.co.reggie.oldeal.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonFormat;

import jp.co.reggie.oldeal.handler.DatabaseListener;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 員工管理實體類
 *
 * @author Administrator
 */
@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "employee")
@EntityListeners(DatabaseListener.class)
@NamedQuery(name = "Employee.getByNames", query = "select me from Employee me where me.name like concat('%', :name, '%') order by cv.id asc")
public class Employee implements Serializable {

	private static final long serialVersionUID = -6540113185665801143L;

	/**
	 * ID
	 */
	@Id
	@GenericGenerator(name = "snowflakeId", strategy = "jp.co.reggie.oldeal.utils.SnowflakeIdGenerator")
	@GeneratedValue(generator = "snowflakeId")
	private Long id;

	/**
	 * 賬號名
	 */
	@Column(nullable = false)
	private String username;

	/**
	 * 姓名
	 */
	@Column(nullable = false)
	private String name;

	/**
	 * 密碼
	 */
	@Column(nullable = false)
	private String password;

	/**
	 * 手機號
	 */
	@Column(name = "phone_num", nullable = false)
	private String phoneNo;

	/**
	 * 性別
	 */
	@Column(name = "sex", nullable = false)
	private String gender;

	/**
	 * 身份證號
	 */
	@Column(name = "id_number", nullable = false)
	private String idNumber;

	/**
	 * 賬號狀態：0:禁用，1:正常
	 */
	@Column(nullable = false)
	private Integer status;

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
}
