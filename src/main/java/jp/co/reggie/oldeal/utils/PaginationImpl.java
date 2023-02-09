package jp.co.reggie.oldeal.utils;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.PageImpl;

import java.util.List;

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

    private Long totalRecords;

    public PaginationImpl(final List<T> content) {
        super(content);
        this.content = content;
    }
}