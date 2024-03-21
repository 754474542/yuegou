package com.yuegou.service.impl;

import com.yuegou.entity.User;
import com.yuegou.dao.UserDao;
import com.yuegou.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

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
    public User getById(Long id) {
        return userDao.getById(id);
    }

    @Override
    public User getUserName(User user) {
        return userDao.getUserName(user);
    }

    @Override
    public List<User> getAll(Integer size,Integer offset) {
        return userDao.getAll(size,offset);
    }

    public List<User> getUserAndStore(){
        return userDao.getUserAndStore();
    }

}
