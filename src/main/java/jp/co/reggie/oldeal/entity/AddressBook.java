package jp.co.reggie.oldeal.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;

import lombok.Data;

/**
 * 地址簿實體類
 *
 * @author Administrator
 */
@Data
public class AddressBook implements Serializable {

	private static final long serialVersionUID = 3548464562522747007L;

	/**
	 * ID
	 */
	@TableId
	private Long id;

	/**
	 * 用戸ID
	 */
	private Long userId;

	/**
	 * 簽收人
	 */
	private String consignee;

	/**
	 * 手機號
	 */
	@TableField(value = "phone_num")
	private String phoneNo;

	/**
	 * 性別
	 */
	@TableField(value = "sex")
	private String gender;

	/**
	 * 省級行政區劃
	 */
	private String provinceCode;

	/**
	 * 省級名稱
	 */
	private String provinceName;

	/**
	 * 市縣級行政區劃
	 */
	private String cityCode;

	/**
	 * 市縣級名稱
	 */
	private String cityName;

	/**
	 * 區級行政區劃
	 */
	private String districtCode;

	/**
	 * 區級名稱
	 */
	private String districtName;

	/**
	 * 詳細地址
	 */
	private String detail;

	/**
	 * 備注
	 */
	private String label;

	/**
	 * 是否默認
	 */
	private Integer isDefault;

	/**
	 * 創建時間
	 */
	@TableField(fill = FieldFill.INSERT)
	private LocalDateTime createTime;

	/**
	 * 更新時間
	 */
	@TableField(fill = FieldFill.INSERT_UPDATE)
	private LocalDateTime updateTime;

	/**
	 * 創建人
	 */
	@TableField(fill = FieldFill.INSERT)
	private Long createUser;

	/**
	 * 修改者
	 */
	@TableField(fill = FieldFill.INSERT_UPDATE)
	private Long updateUser;

	/**
	 * 邏輯刪除字段
	 */
	@TableLogic
	private String logicDeleteFlg;
}
