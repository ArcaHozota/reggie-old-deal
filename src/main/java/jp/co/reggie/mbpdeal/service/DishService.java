package jp.co.reggie.mbpdeal.service;

import java.util.List;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

import jp.co.reggie.mbpdeal.dto.DishDto;
import jp.co.reggie.mbpdeal.entity.Dish;

/**
 * @author Administrator
 */
public interface DishService extends IService<Dish> {

	/**
	 * 新增菜品，同時插入菜品所對應的口味數據
	 *
	 * @param dishDto 菜品及口味數據傳輸類
	 */
	void saveWithFlavour(DishDto dishDto);

	/**
	 * 修改菜品信息並同時插入菜品所對應的口味數據
	 *
	 * @param dishDto 菜品及口味數據傳輸類
	 */
	void updateWithFlavour(DishDto dishDto);

	/**
	 * 根據ID查詢菜品信息以及對應的口味信息
	 *
	 * @param id 菜品ID
	 * @return dishDto 菜品及口味數據傳輸類
	 */
	DishDto getByIdWithFlavour(Long id);

	/**
	 * 根據菜品集合批量停發售
	 *
	 * @param status     在售狀態
	 * @param dishIdList 菜品集合
	 */
	void batchUpdateByIds(String status, List<Long> dishIdList);

	/**
	 * 回顯菜品表單數據
	 *
	 * @param dish 實體類對象
	 * @return List<Dish>
	 */
	List<DishDto> findList(Dish dish);

	/**
	 * 查詢分頁數據
	 *
	 * @param pageNum  頁碼
	 * @param pageSize 頁面大小
	 * @param keyword  檢索關鍵詞
	 * @return Page<DishDto>
	 */
	Page<DishDto> pagination(Integer pageNum, Integer pageSize, String keyword);
}
