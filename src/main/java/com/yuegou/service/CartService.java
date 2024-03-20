package com.yuegou.service;

import com.yuegou.entity.Cart;
import org.springframework.transaction.annotation.Transactional;

/**
 * (Cart)表服务接口
 *
 * @author makejava
 * @since 2024-03-13 10:03:33
 */
@Transactional
public interface CartService {

    Cart queryByUserId(Long userId);

    /**
     * 通过主键删除数据
     *
     * @param cartId 主键
     * @return 是否成功
     */
    boolean deleteById(Long cartId);

}
