package com.yuegou.dao;

import com.yuegou.entity.Cart;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * (Cart)表数据库访问层
 *
 * @author makejava
 * @since 2024-03-13 10:03:33
 */
@Mapper
public interface CartDao {

    List<Cart> queryByUserId(Long userId);

    boolean insert(Cart cart);

    boolean mergeCart(Cart cart);

    boolean update(Cart cart);

    boolean deleteById(Long cartId);

}

