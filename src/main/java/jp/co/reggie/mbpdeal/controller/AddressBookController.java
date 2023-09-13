package jp.co.reggie.mbpdeal.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;

import jp.co.reggie.mbpdeal.common.CustomMessages;
import jp.co.reggie.mbpdeal.entity.AddressBook;
import jp.co.reggie.mbpdeal.handler.BaseContext;
import jp.co.reggie.mbpdeal.service.AddressBookService;
import jp.co.reggie.mbpdeal.utils.Reggie;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/addressBook")
public class AddressBookController {

	@Autowired
	private AddressBookService addressBookService;

	/**
	 * 新增地址簿
	 *
	 * @param addressBook 實體類對象
	 * @return R.success(實體類對象)
	 */
	@PostMapping
	public Reggie<AddressBook> save(@RequestBody final AddressBook addressBook) {
		addressBook.setUserId(BaseContext.getCurrentId());
		log.info("addressBook:{}", addressBook);
		this.addressBookService.save(addressBook);
		return Reggie.success(addressBook);
	}

	/**
	 * 設置默認地址
	 *
	 * @param addressBook 實體類對象
	 * @return R.success(實體類對象)
	 */
	@PutMapping("/default")
	public Reggie<AddressBook> setDefault(@RequestBody final AddressBook addressBook) {
		log.info("addressBook:{}", addressBook);
		final LambdaUpdateWrapper<AddressBook> updateWrapper = Wrappers.lambdaUpdate(new AddressBook());
		updateWrapper.eq(AddressBook::getUserId, BaseContext.getCurrentId());
		updateWrapper.set(AddressBook::getIsDefault, 0);
		this.addressBookService.update(updateWrapper);
		addressBook.setIsDefault(1);
		this.addressBookService.updateById(addressBook);
		return Reggie.success(addressBook);
	}

	/**
	 * 根據ID查詢地址簿
	 *
	 * @param id ID
	 * @return R.success(實體類對象)
	 */
	@GetMapping("/{id}")
	public Reggie<AddressBook> getAddress(@PathVariable final Long id) {
		final AddressBook addressBook = this.addressBookService.getById(id);
		if (addressBook != null) {
			return Reggie.success(addressBook);
		}
		return Reggie.error(CustomMessages.ERP019);
	}

	/**
	 * 查詢默認地址
	 *
	 * @return R.success(實體類對象)
	 */
	@GetMapping("/default")
	public Reggie<AddressBook> getDefault() {
		final LambdaQueryWrapper<AddressBook> queryWrapper = Wrappers.lambdaQuery(new AddressBook());
		queryWrapper.eq(AddressBook::getUserId, BaseContext.getCurrentId());
		queryWrapper.eq(AddressBook::getIsDefault, 1);
		final AddressBook addressBook = this.addressBookService.getOne(queryWrapper);
		if (addressBook != null) {
			return Reggie.success(addressBook);
		}
		return Reggie.error(CustomMessages.ERP019);
	}

	/**
	 * 檢索指定用戸的全部地址
	 *
	 * @param addressBook 實體類對象
	 * @return R.success(list)
	 */
	@GetMapping("/list")
	public Reggie<List<AddressBook>> list(@RequestBody final AddressBook addressBook) {
		addressBook.setUserId(BaseContext.getCurrentId());
		log.info("addressBook:{}", addressBook);
		final LambdaQueryWrapper<AddressBook> queryWrapper = Wrappers.lambdaQuery(new AddressBook());
		queryWrapper.eq(AddressBook::getUserId, addressBook.getUserId());
		queryWrapper.orderByDesc(AddressBook::getUpdatedTime);
		final List<AddressBook> addressBooks = this.addressBookService.list(queryWrapper);
		return Reggie.success(addressBooks);
	}
}
