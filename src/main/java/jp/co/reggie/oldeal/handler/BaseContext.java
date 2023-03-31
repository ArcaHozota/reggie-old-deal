package jp.co.reggie.oldeal.handler;

/**
 * 基於ThreadLocal封裝工具類，用於獲取和保存當前用戸ID；
 *
 * @author Administrator
 * @date 2022-11-18
 */
public class BaseContext {

	private static Long CURRENT_ID;

	protected static void setCurrentId(final Long id) {
		CURRENT_ID = id;
	}

	public static Long getCurrentId() {
		return CURRENT_ID;
	}
}
