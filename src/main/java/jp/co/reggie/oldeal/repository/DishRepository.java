package jp.co.reggie.oldeal.repository;

import jp.co.reggie.oldeal.entity.Dish;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 菜品實體類數據接口
 *
 * @author Administrator
 */
public interface DishDao extends JpaRepository<Dish, Long> {
}