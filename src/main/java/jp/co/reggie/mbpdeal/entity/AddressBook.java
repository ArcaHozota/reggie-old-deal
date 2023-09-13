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
 * 地址簿實體類
 *
 * @author Administrator
 */
@Data
@TableName(value = "REGGIE_ADDRESSBOOK")
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
	 * 性別
	 */
	private String gender;

	/**
	 * 手機號
	 */
	@TableField(value = "PHONE_NUMBER")
	private String phoneNo;

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
	@TableField(value = "DEL_FLG")
	private String logicDeleteFlg;
}
