package jp.co.reggie.mbpdeal.handler;

/**
 * 基於ThreadLocal封裝工具類，用於獲取和保存當前用戸ID；
 *
 * @author Administrator
 * @date 2022-11-18
 */
public class BaseContext {

	private static Long currentId;

	private BaseContext() {
	}

	protected static void setCurrentId(final Long id) {
		currentId = id;
	}

	public static Long getCurrentId() {
		return currentId;
	}
}
