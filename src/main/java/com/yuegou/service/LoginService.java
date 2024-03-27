package com.yuegou.service;

import com.yuegou.entity.User;
import com.yuegou.entity.UserForgot;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface LoginService {

    String login(User user);
    boolean register(UserForgot userForgot);
    boolean verificationCode(User user);
    boolean forgot(UserForgot userForgot);

}
