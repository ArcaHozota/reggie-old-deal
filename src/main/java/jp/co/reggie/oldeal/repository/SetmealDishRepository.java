package jp.co.reggie.oldeal.repository;

import jp.co.reggie.oldeal.entity.SetmealDish;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 套餐與菜品關係實體類接口
 *
 * @author Administrator
 */
public interface SetmealDishRepository extends JpaRepository<SetmealDish, Long> {
}