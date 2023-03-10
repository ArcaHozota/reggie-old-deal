package jp.co.reggie.oldeal.service.impl;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import jp.co.reggie.newdeal.entity.AddressBook;
import jp.co.reggie.newdeal.mapper.AddressBookMapper;
import jp.co.reggie.newdeal.service.AddressBookService;

/**
 * @author Administrator
 * @date 2022-11-19
 */
@Service
public class AddressBookServiceImpl extends ServiceImpl<AddressBookMapper, AddressBook> implements AddressBookService {
}
