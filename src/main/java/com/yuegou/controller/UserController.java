package com.yuegou.controller;

import com.yuegou.config.PaginationConfig;
import com.yuegou.controller.pretreatment.Code;
import com.yuegou.controller.pretreatment.Result;
import com.yuegou.entity.User;
import com.yuegou.service.UserService;
import com.yuegou.utils.PaginationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService service;

    @PostMapping
    public Result save(@RequestBody User user){
        boolean flag = service.save(user);
        return new Result(flag ? Code.SAVE_OK : Code.SAVE_ERR, flag);
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Long id){
        boolean flag = service.delete(id);
        return new Result(flag ? Code.DELETE_OK : Code.DELETE_ERR, flag);
    }
    @PutMapping
    public Result update(@RequestBody User user){
        System.out.println(user);
        boolean flag = service.update(user);
        return new Result(flag ? Code.UPDATE_OK : Code.UPDATE_ERR, flag);
    }
    @GetMapping("/{id}")
    public Result getById(@PathVariable Long id){
        User user = service.getById(id);
        return new Result(user != null ? Code.SELECT_OK : Code.SELECT_ERR, user, user != null ? "OK" : "Error");
    }
    @GetMapping
    public Result getAll(@RequestParam(value = "page",defaultValue = "#{paginationConfig.page}") Integer page, @RequestParam(value = "size", defaultValue = "#{paginationConfig.size}") Integer size){
        int offset = PaginationUtil.calculateOffset(page, size);
        List<User> user = service.getAll(size,offset);
        return new Result(user != null ? Code.SELECT_OK : Code.SELECT_ERR, user, user != null ? "OK" : "Error");
    }
    @GetMapping("/onToken")
    public Result getOnToken(@RequestHeader String token){
        User user = service.getUserOnToken(token);
        return new Result(user != null ? Code.SELECT_OK : Code.SELECT_ERR, user, user != null ? "OK" : "Error");
    }

}
