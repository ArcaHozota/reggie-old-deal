package com.itheima.reggie.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 客戸信息實體類
 *
 * @author Administrator
 */
@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "user")
public class User implements Serializable {

	private static final long serialVersionUID = 2324630650798877027L;

	/**
	 * ID
	 */
	@Id
	@GenericGenerator(name = "snowflakeId", strategy = "com.itheima.reggie.utils.SnowflakeIdGenerator")
	@GeneratedValue(generator = "snowflakeId")
	private Long id;

	/**
	 * 姓名
	 */
	private String name;

	/**
	 * 手機號
	 */
	@Column(name = "phone_num", nullable = false)
	private String phoneNo;

	/**
	 * 性別：0女，1男
	 */
	@Column(name = "sex")
	private String gender;

	/**
	 * 身份證號
	 */
	@Column(name = "id_number")
	private String idNumber;

	/**
	 * 頭像
	 */
	private String avatar;

	/**
	 * 狀態： 0禁用，1正常
	 */
	private Integer status;
}
