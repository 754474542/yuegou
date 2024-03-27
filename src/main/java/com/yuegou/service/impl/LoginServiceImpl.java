package com.yuegou.service.impl;

import com.yuegou.controller.pretreatment.Code;
import com.yuegou.controller.pretreatment.exceptionhandle.LoginException;
import com.yuegou.dao.UserDao;
import com.yuegou.entity.Ban;
import com.yuegou.entity.User;
import com.yuegou.entity.UserForgot;
import com.yuegou.service.BanService;
import com.yuegou.service.LoginService;
import com.yuegou.utils.BcryptUtil;
import com.yuegou.utils.JwtUtil;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private UserDao userDao;
    @Autowired
    private BanService banService;
    @Autowired
    private Logger logger;
    @Autowired
    private JwtUtil jwtUtil;

    @Override
    public String login(User user) {
        //验证用户名密码
        if (user.getUserName().trim().equals("") || user.getUserName() == null || user.getUserPassword() == null || user.getUserPassword().trim().equals("")) {
            throw new LoginException(Code.LOGIN_ERR, "请输入正确的用户名或密码");
        }
        User sql_user = userDao.getUserName(user.getUserName());
        if (sql_user == null) throw new LoginException(Code.LOGIN_ERR, "没有搜索到这个用户，请确保用户名输入是否正确");

        //验证是否被封号
        Ban ban = null;
        if ((ban = banService.queryById(sql_user.getUserId())) != null){
            Date banUntime = ban.getBanUntime();
            logger.info("封神榜 " + sql_user.getUserName() + " 登录被拦截");
            throw new LoginException(Code.LOGIN_ERR, "您的账号已被封禁，预计将在" + banUntime + " 解封");
        }

        //登录验证
        if (sql_user.getUserEmpt() >= 4) {
            throw new LoginException(Code.LOGIN_ERR, "登录失败次数过多，请稍后再试");
        }

        //Bcrypt加密验证
        boolean flag = BcryptUtil.equalsPassword(user.getUserPassword(), sql_user.getUserPassword());

        if (flag) {
            sql_user.setUserEmpt(0);
            if (!userDao.setEmpt(sql_user)) throw new LoginException(Code.LOGIN_ERR,"设置EMPT值错误");
            logger.info(sql_user.getUserName() + " | " + sql_user.getUserPhone() + " 登录成功");
            return jwtUtil.createToken(sql_user);
        }else {
            sql_user.setUserEmpt(sql_user.getUserEmpt() + 1);
            if (!userDao.setEmpt(sql_user)) throw new LoginException(Code.LOGIN_ERR,"设置EMPT值错误");
            if (sql_user.getUserEmpt() >= 4) logger.info(sql_user.getUserName() + " 进行登录限制");
        }

        return null;
    }

    @Override
    public boolean register(UserForgot userForgot) {
        User user = userForgot.getUser();
        String code = userForgot.getCode();
        if (!code.equals("246810")) throw new LoginException(Code.FORGOT_ERR,"验证码错误");
        if (user.getUserPhone() == null || user.getUserPhone().trim().equals("") || user.getUserPassword() == null || user.getUserPassword().trim().equals("") || user.getUserName().trim().equals("") || user.getUserName() == null) {
            throw new LoginException(Code.REGISTER_ERR, "输入的值不能为空");
        }
        if (user.getUserPhone().length() != 11) {
            throw new LoginException(Code.REGISTER_ERR, "请查看手机号是否合法");
        }
        User userPhone = userDao.getUserPhone(user);
        User userName = userDao.getUserName(user.getUserName());
        if (userPhone != null) throw new LoginException(Code.REGISTER_ERR, "此手机号已被注册");
        if (userName != null) throw new LoginException(Code.REGISTER_ERR, "此用户名已被注册");
        user.setUserPassword(BcryptUtil.getPasswordBcrypt(user.getUserPassword()));
        logger.info(user.getUserName() + " 注册了账号");
        return userDao.save(user);
    }

    @Override
    public boolean verificationCode(User user) {
        if (user.getUserPhone().length() != 11) {
            throw new LoginException(Code.REGISTER_ERR, "请查看手机号是否合法");
        }
        return true;
    }


    @Override
    public boolean forgot(UserForgot userForgot) {
        String code = userForgot.getCode();
        User user = userForgot.getUser();
        User userPhone = userDao.getUserPhone(user);
        if (!code.equals("246810")) throw new LoginException(Code.FORGOT_ERR,"验证码错误");
        if (userPhone == null) {
            throw new LoginException(Code.FORGOT_ERR, "此手机号还未注册");
        }
        if (!userForgot.getCode().equals("246810")) {
            throw new LoginException(Code.FORGOT_ERR, "验证码错误");
        }
        userPhone.setUserPassword(BcryptUtil.getPasswordBcrypt(user.getUserPassword()));
        return userDao.update(userPhone);
    }

}
