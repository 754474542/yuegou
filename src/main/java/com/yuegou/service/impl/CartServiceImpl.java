package com.yuegou.service.impl;

import com.yuegou.controller.pretreatment.Code;
import com.yuegou.controller.pretreatment.exceptionhandle.CURDException;
import com.yuegou.dao.UserDao;
import com.yuegou.entity.*;
import com.yuegou.dao.CartDao;
import com.yuegou.service.CartService;
import com.yuegou.service.UserService;
import com.yuegou.utils.FileUtil;
import com.yuegou.utils.JwtUtil;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
    @Value("${utils.imagessavepath}")
    private String path;


    @Override
    public List<Cart> queryByUserId(Long userId) {
        List<Cart> carts = cartDao.queryByUserId(userId);
        for (Cart cart : carts) {
            Spu spu = cart.getSpu();
            SpuImages spuImages = spu.getSpuImages();
            spuImages.setIndexImgPathBase64(FileUtil.fileToByte(path + spuImages.getIndexImgPath()));
        }
        return carts;
    }

    @Override
    public boolean insert(Cart cart,String token) {
        Claims claims = jwtUtil.parseToken(token);
        User userName = userDao.getUserName((String) claims.get("userName"));
        cart.setUserId(userName.getUserId());
        List<Cart> carts = cartDao.queryByUserId(userName.getUserId());
        for (Cart cart1 : carts) {
            if (cart1.getSpuId() == cart.getSpuId()){
                if (cart1.getSkuId() == cart.getSkuId()){
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
