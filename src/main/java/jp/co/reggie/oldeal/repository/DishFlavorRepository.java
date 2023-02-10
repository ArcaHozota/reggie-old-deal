package jp.co.reggie.oldeal.repository;

import jp.co.reggie.oldeal.entity.DishFlavor;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 菜品口味實體類接口
 *
 * @author Administrator
 */
public interface DishFlavorRepository extends JpaRepository<DishFlavor, Long> {
}