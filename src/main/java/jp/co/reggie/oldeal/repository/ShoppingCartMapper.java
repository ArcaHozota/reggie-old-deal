package jp.co.reggie.oldeal.repository;

import org.apache.ibatis.annotations.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import jp.co.reggie.newdeal.entity.ShoppingCart;

/**
 * 購物車實體類接口
 *
 * @author Administrator
 * @date 2022-11-29
 */
@Mapper
public interface ShoppingCartMapper extends BaseMapper<ShoppingCart> {
}
