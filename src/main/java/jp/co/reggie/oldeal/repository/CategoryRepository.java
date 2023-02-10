package jp.co.reggie.oldeal.repository;

import jp.co.reggie.oldeal.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 分類管理實體類接口
 *
 * @author Administrator
 */
public interface CategoryDao extends JpaRepository<Category, Long> {
}