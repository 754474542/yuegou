package com.yuegou.controller;

import com.yuegou.controller.pretreatment.Code;
import com.yuegou.controller.pretreatment.Result;
import com.yuegou.entity.Cart;
import com.yuegou.entity.User;
import com.yuegou.service.CartService;
import com.yuegou.service.UserService;
import com.yuegou.utils.PaginationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/carts")
public class CartController {

    @Autowired
    private CartService cartService;

    @PostMapping
    public Result insert(@RequestBody Cart cart,@RequestHeader("token") String token){
        boolean flag = cartService.insert(cart,token);
        return new Result(flag ? Code.SAVE_OK : Code.SAVE_ERR, flag);
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Long id){
        boolean flag = cartService.deleteById(id);
        return new Result(flag ? Code.DELETE_OK : Code.DELETE_ERR, flag);
    }
    @PutMapping
    public Result update(@RequestBody Cart cart){
        boolean flag = cartService.update(cart);
        return new Result(flag ? Code.UPDATE_OK : Code.UPDATE_ERR, flag);
    }
    @GetMapping("/{id}")
    public Result getById(@PathVariable Long id){
        List<Cart> carts = cartService.queryByUserId(id);
        return new Result(carts != null ? Code.SELECT_OK : Code.SELECT_ERR, carts, carts != null ? "OK" : "Error");
    }

}
