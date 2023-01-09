package jp.co.reggie.oldeal.repository;

import jp.co.reggie.oldeal.entity.Setmeal;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 套餐實體類接口
 *
 * @author Administrator
 */
public interface SetmealRepository extends JpaRepository<Setmeal, Long> {
}