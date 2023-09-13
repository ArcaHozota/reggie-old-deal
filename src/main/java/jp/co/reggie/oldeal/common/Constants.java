package jp.co.reggie.oldeal.common;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * 項目常量定義類
 *
 * @author Administrator
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class Constants {

	/**
	 * 未登錄狀態
	 */
	public static final String NOT_LOGIN = "NOT_LOGIN";

	/**
	 * 登錄失敗
	 */
	public static final String LOGIN_FAILED = "LOGIN_FAILED";

	/**
	 * 項目等待超時
	 */
	public static final String TIME_OUT = "TIMEOUT";

	/**
	 * 賬號被禁用
	 */
	public static final String FORBIDDEN = "ACCOUNT_PROHIBITED";

	/**
	 * 初始密碼
	 */
	public static final String PRIMARY_CODE = "111111";

	/**
	 * 出現異常
	 */
	public static final String ERROR = "ERROR_OCCURRED";

	/**
	 * 重複的主鍵値
	 */
	public static final String DUPLICATED_KEY = "DUPLICATE_KY_ETR";

	/**
	 * 沒有相對應的結果
	 */
	public static final String NO_CONSEQUENCE = "NO_SUCH_CONSEQUENCES";

	/**
	 * 非禁用狀態
	 */
	public static final Integer STATUS_VALID = 1;

	/**
	 * 禁用狀態
	 */
	public static final Integer STATUS_FORBIDDEN = 0;

	/**
	 * 獲取Entity類名
	 *
	 * @param obj object
	 * @return class_name
	 */
	public static String getEntityName(final Object obj) {
		return obj.getClass().getSimpleName().toLowerCase();
	}

}
