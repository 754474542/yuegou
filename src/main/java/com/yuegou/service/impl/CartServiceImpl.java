package com.yuegou.service.impl;

import com.yuegou.controller.pretreatment.Code;
import com.yuegou.controller.pretreatment.exceptionhandle.CURDException;
import com.yuegou.dao.UserDao;
import com.yuegou.entity.Cart;
import com.yuegou.dao.CartDao;
import com.yuegou.entity.User;
import com.yuegou.service.CartService;
import com.yuegou.service.UserService;
import com.yuegou.utils.JwtUtil;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (Cart)表服务实现类
 *
 * @author makejava
 * @since 2024-03-13 10:03:33
 */
@Service("cartService")
public class CartServiceImpl implements CartService {
    @Autowired
    private CartDao cartDao;
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private UserDao userDao;

    @Override
    public List<Cart> queryByUserId(Long userId) {
        return this.cartDao.queryByUserId(userId);
    }

    @Override
    public boolean insert(Cart cart,String token) {
        Claims claims = jwtUtil.parseToken(token);
        User userName = userDao.getUserName((String) claims.get("userName"));
        cart.setUserId(userName.getUserId());
        List<Cart> carts = cartDao.queryByUserId(userName.getUserId());
        for (Cart cart1 : carts) {
            System.out.println("==========================");
            System.out.println(cart1.getSkuId());
            System.out.println(cart1.getSpuId());
            System.out.println(cart.getSkuId());
            System.out.println(cart.getSpuId());
            System.out.println("==========================");
            if (cart1.getSpuId() == cart.getSpuId()){
                if (cart1.getSkuId() == cart.getSkuId()){
                    System.out.println("害羞羞");
                    cart.setNumber(cart1.getNumber() + cart.getNumber());
                    if (!cartDao.mergeCart(cart)) throw new CURDException(Code.UPDATE_ERR,"重复商品合并失败");
                    return true;
                }
            }
        }
        return cartDao.insert(cart);
    }

    @Override
    public boolean update(Cart cart) {
        return cartDao.update(cart);
    }

    @Override
    public boolean deleteById(Long cartId) {
        return cartDao.deleteById(cartId);
    }

}
