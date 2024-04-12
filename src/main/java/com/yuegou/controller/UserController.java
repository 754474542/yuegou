package com.yuegou.controller;

import com.yuegou.config.PaginationConfig;
import com.yuegou.controller.pretreatment.Code;
import com.yuegou.controller.pretreatment.Result;
import com.yuegou.entity.ModifyPasswordEntity;
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
    @PutMapping("/modifyPassword")
    public Result setUserPassword(@RequestBody ModifyPasswordEntity modifyPassword){
        boolean flag = service.modifyPassword(modifyPassword);
        return new Result(flag ? Code.UPDATE_OK : Code.UPDATE_ERR, flag ? "密码修改成功" : "密码修改失败");
    }

    @PutMapping("/admModifyPassword")
    public Result admSetUserPassword(@RequestBody ModifyPasswordEntity modifyPassword){
        boolean flag = service.admModifyPassword(modifyPassword);
        return new Result(flag ? Code.UPDATE_OK : Code.UPDATE_ERR, flag ? "密码修改成功" : "密码修改失败");
    }

    @GetMapping("/queryUserAll")
    public Result queryUserAll(@RequestParam(value = "page",defaultValue = "#{paginationConfig.page}") Integer page,
                               @RequestParam(value = "size", defaultValue = "#{paginationConfig.size}") Integer size,
                               @RequestParam String search){
        int offset = PaginationUtil.calculateOffset(page, size);
        List<User> userList = service.queryUserAll(size,offset,search);
        return new Result(userList != null ? Code.SELECT_OK : Code.SELECT_ERR, userList, userList != null ? "OK" : "Error");
    }

}
