package jp.co.reggie.oldeal.dto;

import java.util.List;

import jp.co.reggie.oldeal.entity.Setmeal;
import jp.co.reggie.oldeal.entity.SetmealDish;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 套餐數據傳輸專用類
 *
 * @author Administrator
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class SetmealDto extends Setmeal {

	private static final long serialVersionUID = 5174917893420797875L;

	/**
	 * 套餐集合
	 */
	private List<SetmealDish> setmealDishes;

	/**
	 * 分類名稱
	 */
	private String categoryName;
}
