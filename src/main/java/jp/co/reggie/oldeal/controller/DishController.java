package jp.co.reggie.oldeal.controller;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jp.co.reggie.oldeal.common.CustomMessage;
import jp.co.reggie.oldeal.dto.DishDto;
import jp.co.reggie.oldeal.mapper.CategoryRepository;
import jp.co.reggie.oldeal.mapper.DishFlavorRepository;
import jp.co.reggie.oldeal.mapper.DishRepository;
import jp.co.reggie.oldeal.utils.Reggie;
import lombok.extern.slf4j.Slf4j;

/**
 * 菜品管理控制器
 *
 * @author Administrator
 */
@Slf4j
@RestController
@RequestMapping("/dish")
public class DishController {

	@Resource
	private DishRepository dishDao;

	@Resource
	private CategoryRepository categoryDao;

	@Resource
	private DishFlavorRepository dishFlavorDao;

	/**
	 * 新增菜品
	 *
	 * @param dishDto 數據傳輸類對象
	 * @return R.success(成功新增菜品的信息)
	 */
	@PostMapping
	public Reggie<String> save(@RequestBody final DishDto dishDto) {
		log.info("新增菜品：{}" + dishDto.toString());
//		this.dishService.saveWithFlavour(dishDto);
		return Reggie.success(CustomMessage.SRP004);
	}

	/**
	 * 菜品信息分頁查詢
	 *
	 * @param pageNum  頁碼
	 * @param pageSize 頁面大小
	 * @return R.success(分頁信息)
	 */
//	@GetMapping("/page")
//	public Reggie<Page<DishDto>> pagination(@RequestParam("pageNum") final Integer pageNum,
//			@RequestParam("pageSize") final Integer pageSize, @RequestParam("name") final String name) {
//		// 聲明分頁構造器對象；
//		final PageRequest pageRequest = PageRequest.of(pageNum, pageSize);
//		final Page<Dish> pageInfo = this.dishDao.findAll(pageRequest);
//		final Page<DishDto> dtoPage;
//		// 創建條件構造器；
//		final LambdaQueryWrapper<Dish> queryWrapper = Wrappers.lambdaQuery(new Dish());
//		// 添加過濾條件；
//		queryWrapper.like(!name.isBlank(), Dish::getName, name);
//		// 添加排序條件；
//		queryWrapper.orderByDesc(Dish::getUpdateTime);
//		// 執行分頁查詢；
//		this.dishService.page(pageInfo, queryWrapper);
//		// 對象拷貝；
//		BeanUtils.copyProperties(pageInfo, dtoPage, "records");
//		// 獲取分頁數據；
//		final List<Dish> records = pageInfo.getRecords();
//		// 獲取數據傳輸類分頁；
//		final List<DishDto> list = records.stream().map((item) -> {
//			// 聲明菜品及口味數據傳輸類對象；
//			final DishDto dishDto = new DishDto();
//			// 拷貝除分類ID以外的屬性；
//			BeanUtils.copyProperties(item, dishDto);
//			// 獲取分類ID；
//			final Long categoryId = item.getCategoryId();
//			// 根據ID查詢分類對象；
//			final Category category = this.categoryService.getById(categoryId);
//			if (category != null) {
//				// 獲取分類名稱；
//				final String categoryName = category.getName();
//				// 存儲於DTO對象中並返回；
//				dishDto.setCategoryName(categoryName);
//			}
//			return dishDto;
//		}).collect(Collectors.toList());
//		// 設置分頁數據於構造器中並返回；
//		dtoPage.setRecords(list);
//		return Reggie.success(dtoPage);
//	}
//
//	/**
//	 * 根據ID顯示菜品信息
//	 *
//	 * @param id 菜品ID
//	 * @return R.success(菜品信息)
//	 */
//	@GetMapping("/{id}")
//	public Reggie<DishDto> getDishInfo(@PathVariable final Long id) {
//		// 根據ID查詢菜品信息以及對應的口味信息；
//		return Reggie.success(this.dishService.getByIdWithFlavour(id));
//	}
//
//	/**
//	 * 修改菜品信息
//	 *
//	 * @param dishDto 數據傳輸類對象
//	 * @return R.success(菜品更新成功的信息)
//	 */
//	@PutMapping
//	public Reggie<String> update(@RequestBody final DishDto dishDto) {
//		log.info(dishDto.toString());
//		this.dishService.updateWithFlavour(dishDto);
//		return Reggie.success(CustomMessage.SRP005);
//	}
//
//	/**
//	 * 回顯菜品表單數據
//	 *
//	 * @param dish 實體類對象
//	 * @return R.success(菜品信息)
//	 */
//	@GetMapping("/list")
//	public Reggie<List<Dish>> list(@RequestBody final Dish dish) {
//		// 創建條件構造器；
//		final LambdaQueryWrapper<Dish> queryWrapper = Wrappers.lambdaQuery(new Dish());
//		// 添加搜索條件；
//		queryWrapper.eq(dish.getCategoryId() != null, Dish::getCategoryId, dish.getCategoryId());
//		queryWrapper.eq(Dish::getStatus, 1);
//		// 添加排序條件；
//		queryWrapper.orderByAsc(Dish::getSort).orderByDesc(Dish::getUpdateTime);
//		// 查詢菜品信息並返回；
//		final List<Dish> list = this.dishService.list(queryWrapper);
//		return Reggie.success(list);
//	}
//
//	/**
//	 * 修改菜品在售狀態
//	 *
//	 * @param dishState 菜品狀態
//	 * @return R.success(修改成功信息)
//	 */
//	@PostMapping("/status/{status}")
//	public Reggie<String> changeStatus(@PathVariable Integer status, @RequestParam("ids") final Long[] ids) {
//		switch (status) {
//		case 0:
//			status = 1;
//			break;
//		case 1:
//			status = 0;
//			break;
//		default:
//			throw new CustomException((CustomMessage.ERP017));
//		}
//		if (ids.length == 1) {
//			final Dish dish = this.dishService.getById(ids[0]);
//			dish.setStatus(status);
//			this.dishService.updateById(dish);
//		} else {
//			final List<Dish> dList = new ArrayList<>();
//			for (final Long id : ids) {
//				final Dish dish = this.dishService.getById(id);
//				dish.setStatus(status);
//				dList.add(dish);
//			}
//			this.dishService.updateBatchById(dList);
//		}
//		return Reggie.success(CustomMessage.SRP016);
//	}
}
