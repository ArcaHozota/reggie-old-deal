package jp.co.reggie.oldeal.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jp.co.reggie.oldeal.common.CustomMessage;
import jp.co.reggie.oldeal.entity.Category;
import jp.co.reggie.oldeal.repository.CategoryRepository;
import jp.co.reggie.oldeal.utils.Reggie;
import lombok.extern.slf4j.Slf4j;

/**
 * 分類管理控制器
 *
 * @author Administrator
 */
@Slf4j
@RestController
@RequestMapping("/category")
public class CategoryController {

	@Resource
	private CategoryRepository categoryDao;

	/**
	 * 分頁信息顯示
	 *
	 * @param pageNum  頁碼
	 * @param pageSize 頁面大小
	 * @return R.success(分頁信息)
	 */
	@GetMapping("/page")
	public Reggie<Page<Category>> pagination(@RequestParam("pageNum") final Integer pageNum,
			@RequestParam("pageSize") final Integer pageSize) {
		// 聲明分頁構造器；
		final PageRequest pageRequest = PageRequest.of(pageNum, pageSize, Sort.by(Sort.Direction.ASC, "sort"));
		// 執行查詢；
		final Page<Category> pageInfo = this.categoryDao.findAll(pageRequest);
		return Reggie.success(pageInfo);
	}

	/**
	 * 新增分類
	 *
	 * @param category 實體類對象
	 * @return R.success(分類新增成功的信息);
	 */
	@PostMapping
	public Reggie<String> save(@RequestBody final Category category) {
		log.info("category:{}", category);
		this.categoryDao.save(category);
		return Reggie.success(CustomMessage.SRP001);
	}

	/**
	 * 刪除分類
	 *
	 * @param id 分類ID
	 * @return R.success(分類刪除成功的信息);
	 */
	@DeleteMapping
	public Reggie<String> delete(@RequestParam("ids") final Long id) {
		log.info("刪除ID={}的分類", id);
		// 實施刪除；
		this.categoryDao.deleteById(id);
		return Reggie.success(CustomMessage.SRP003);
	}

	/**
	 * 更新分類
	 *
	 * @param category 實體類對象
	 * @return R.success(分類更新成功的信息);
	 */
	@PutMapping
	public Reggie<String> update(@RequestBody final Category category) {
		log.info("修改分類信息：{}", category);
		// 執行修改操作；
		this.categoryDao.saveAndFlush(category);
		return Reggie.success(CustomMessage.SRP002);
	}

	/**
	 * 根據條件查詢分類數據
	 *
	 * @param category 實體類對象
	 * @return R.success(分類結果的集合)
	 */
	@GetMapping("/list")
	public Reggie<List<Category>> queryList(final Category category) {
		// 獲取分類數據；
		final Integer type = category.getType();
		assert type != null;
		final Category category1 = new Category();
		category1.setType(type);
		final ExampleMatcher matcher = ExampleMatcher.matching().withStringMatcher(ExampleMatcher.StringMatcher.EXACT)
				.withMatcher(type.toString(), ExampleMatcher.GenericPropertyMatchers.exact());
		final Example<Category> example = Example.of(category1, matcher);
		// 查詢分類結果集並返回；
		final List<Category> list = this.categoryDao.findAll(example);
		return Reggie.success(list);
	}
}
