package com.yuegou.controller;

import com.yuegou.controller.pretreatment.Code;
import com.yuegou.controller.pretreatment.Result;
import com.yuegou.entity.User;
import com.yuegou.entity.UserForgot;
import com.yuegou.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class LoginController {

    @Autowired
    private LoginService loginService;

    @PostMapping("/login")
    public Result login(@RequestBody User user){
        String login = loginService.login(user);
        return new Result(login != null ? Code.LOGIN_OK : Code.LOGIN_ERR, login,login != null ? "登录成功"  : "账号或密码错误，请重新输入");
    }

    @PostMapping("/register")
    public Result register(@RequestBody User user){
        boolean register = loginService.register(user);
        return new Result(register ? Code.REGISTER_OK : Code.REGISTER_ERR, register,register ? "注册成功"  : "注册出现错误，请联系管理员");
    }

    @PostMapping("/verificationCode")
    public Result verificationCode(@RequestBody User user){
        boolean verificationCode = loginService.verificationCode(user);
        return new Result(verificationCode ? Code.FORGOT_OK : Code.FORGOT_ERR, verificationCode,verificationCode ? "246810"  : "发送验证码失败");
    }

    @PostMapping("/forgot")
    public Result forgot(@RequestBody UserForgot user){
        boolean forgot = loginService.forgot(user);
        return new Result(forgot ? Code.FORGOT_OK : Code.FORGOT_ERR, forgot,forgot ? "修改密码成功"  : "修改密码失败");
    }
}
