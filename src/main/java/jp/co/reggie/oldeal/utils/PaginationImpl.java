package jp.co.reggie.oldeal.utils;

import java.util.List;

import org.springframework.data.domain.PageImpl;

import lombok.Getter;
import lombok.Setter;

/**
 * Basic {@code Page} implementation.
 *
 * @param <T> the type of which the page consists.
 * @author Oliver Gierke
 * @author Mark Paluch
 */
@Getter
@Setter
public class PaginationImpl<T> extends PageImpl<T> {

	private static final long serialVersionUID = -5664717729756413101L;

	private List<T> content;

	public PaginationImpl(final List<T> content) {
		super(content);
		this.content = content;
	}
}