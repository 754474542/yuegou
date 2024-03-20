package com.yuegou.service.impl;

import com.yuegou.entity.Cart;
import com.yuegou.dao.CartDao;
import com.yuegou.service.CartService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * (Cart)表服务实现类
 *
 * @author makejava
 * @since 2024-03-13 10:03:33
 */
@Service("cartService")
public class CartServiceImpl implements CartService {
    @Resource
    private CartDao cartDao;

    /**
     * 通过ID查询单条数据
     *
     * @param cartId 主键
     * @return 实例对象
     */
    @Override
    public Cart queryByUserId(Long cartId) {
        return this.cartDao.queryByUserId(cartId);
    }

    /**
     * 通过主键删除数据
     *
     * @param cartId 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long cartId) {
        return this.cartDao.deleteById(cartId) > 0;
    }
}
