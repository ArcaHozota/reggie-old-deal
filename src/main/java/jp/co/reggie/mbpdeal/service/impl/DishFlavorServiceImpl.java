package jp.co.reggie.mbpdeal.service.impl;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import jp.co.reggie.mbpdeal.entity.DishFlavor;
import jp.co.reggie.mbpdeal.mapper.DishFlavorMapper;
import jp.co.reggie.mbpdeal.service.DishFlavorService;

/**
 * @author Administrator
 * @date 2022-11-23
 */
@Service
public class DishFlavorServiceImpl extends ServiceImpl<DishFlavorMapper, DishFlavor> implements DishFlavorService {
}
