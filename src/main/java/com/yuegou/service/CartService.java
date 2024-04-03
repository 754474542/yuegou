package com.yuegou.service;

import com.yuegou.entity.Cart;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * (Cart)表服务接口
 *
 * @author makejava
 * @since 2024-03-13 10:03:33
 */
@Transactional
public interface CartService {

    List<Cart> queryByUserId(Long userId);

    boolean insert(Cart cart,String token);

    boolean update(Cart cart);

    boolean deleteById(Long cartId);

}
