package jp.co.reggie.mbpdeal.service;

import java.util.List;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

import jp.co.reggie.mbpdeal.dto.SetmealDto;
import jp.co.reggie.mbpdeal.entity.Setmeal;

/**
 * @author Administrator
 */
public interface SetmealService extends IService<Setmeal> {

    /**
     * 新增套餐，同時保存套餐和菜品的關聯
     *
     * @param setmealDto 數據傳輸類
     */
    void saveWithDish(SetmealDto setmealDto);

    /**
     * 更新套餐，同時更新套餐和菜品的關聯
     *
     * @param setmealDto 數據傳輸類
     */
    void updateWithDish(SetmealDto setmealDto);

    /**
     * 刪除套餐，同時刪除套餐和菜品的關聯
     *
     * @param ids 套餐ID的集合
     */
    void removeWithDish(List<Long> ids);

    /**
     * 根據套餐集合批量停發售
     *
     * @param status   在售狀態
     * @param smIdList 套餐集合
     */
    void batchUpdateByIds(String status, List<Long> smIdList);

    /**
     * 進行分頁
     *
     * @param pageNum  頁碼
     * @param pageSize 頁面大小
     * @param keyword  檢索文
     * @return Page<SetmealDto>
     */
    Page<SetmealDto> pagination(Integer pageNum, Integer pageSize, String keyword);

    /**
     * 根據ID查詢套餐信息
     *
     * @param id 套餐ID
     * @return SetmealDto
     */
    SetmealDto getByIdWithDish(Long id);
}
