package com.yuegou.service.impl;

import com.yuegou.controller.pretreatment.Code;
import com.yuegou.controller.pretreatment.exceptionhandle.CURDException;
import com.yuegou.entity.ModifyPasswordEntity;
import com.yuegou.entity.User;
import com.yuegou.dao.UserDao;
import com.yuegou.service.UserService;
import com.yuegou.utils.BcryptUtil;
import com.yuegou.utils.FileUtil;
import com.yuegou.utils.JwtUtil;
import io.jsonwebtoken.Claims;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private Logger logger;

    @Value("${utils.imagessavepath}")
    private String path;

    @Override
    public boolean save(User user) {
        return userDao.save(user);
    }

    @Override
    public boolean delete(Long id) {
        return userDao.delete(id);
    }

    @Override
    public boolean update(User user) {
        return userDao.update(user);
    }

    @Override
    public boolean modifyPassword(ModifyPasswordEntity modifyPassword) {
        String userName = modifyPassword.getUserName();
        String oldPassword = modifyPassword.getOldPassword();
        String newPassword = modifyPassword.getNewPassword();
        User user = userDao.getUserName(userName);
        boolean flag = BcryptUtil.equalsPassword(oldPassword, user.getUserPassword());
        if (!flag) throw new CURDException(Code.UPDATE_ERR,"密码输入错误");
        user.setUserPassword(BcryptUtil.getPasswordBcrypt(newPassword));
        if (!userDao.update(user)) throw new CURDException(Code.UPDATE_ERR,"更改密码失败");
        return true;
    }

    @Override
    public boolean admModifyPassword(ModifyPasswordEntity modifyPassword) {
        Integer userId = modifyPassword.getUserId();
        String newPassword = modifyPassword.getNewPassword();
        User user = userDao.getById(Long.valueOf(userId));
        user.setUserPassword(BcryptUtil.getPasswordBcrypt(newPassword));
        logger.info("管理员修改用户：" + user.getUserName() + "密码");
        if (!userDao.update(user)) throw new CURDException(Code.UPDATE_ERR,"更改密码失败");
        return true;
    }

    @Override
    public User getById(Long id) {
        return userDao.getById(id);
    }

    @Override
    public User getUserName(User user) {
        return userDao.getUserName(user.getUserName());
    }

    @Override
    public List<User> getAll(Integer size,Integer offset) {
        return userDao.getAll(size,offset);
    }

    public List<User> getUserAndStore(){
        return userDao.getUserAndStore();
    }

    @Override
    public User getUserOnToken(String token) {
        Claims claims = jwtUtil.parseToken(token);
        User userName = userDao.getUserName((String) claims.get("userName"));
        if (userName.getUserHead()!=null) userName.setUserHead(FileUtil.fileToByte(path + userName.getUserHead()));
        return userName;
    }

    @Override
    public List<User> queryUserAll(Integer size, Integer offset, String search) {
        List<User> users = userDao.queryUserAll(size, offset, search);
        for (User user : users) {
            String userHead = user.getUserHead();
            if (userHead != null) user.setUserHead(FileUtil.fileToByte(path + userHead));
        }
        return userDao.queryUserAll(size,offset,search);
    }

}
