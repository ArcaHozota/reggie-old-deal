package jp.co.reggie.oldeal.repository;

import jp.co.reggie.oldeal.entity.DishFlavor;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 菜品口味實體類接口
 *
 * @author Administrator
 */
public interface DishFlavorDao extends JpaRepository<DishFlavor, Long> {
}