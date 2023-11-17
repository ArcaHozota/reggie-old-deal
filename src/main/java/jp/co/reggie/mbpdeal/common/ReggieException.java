package jp.co.reggie.mbpdeal.common;

/**
 * 自定義業務異常
 *
 * @author ArcaHozota
 * @since 1.00beta
 */
public class ReggieException extends RuntimeException {

	private static final long serialVersionUID = 1692201139310590555L;

	public ReggieException(final String message) {
		super(message);
	}
}
