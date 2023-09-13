package jp.co.reggie.mbpdeal.common;

/**
 * 自定義業務異常
 *
 * @author Administrator
 * @since 1.00beta
 */
public class CustomException extends RuntimeException {

	private static final long serialVersionUID = 1692201139310590555L;

	public CustomException(final String message) {
		super(message);
	}
}
