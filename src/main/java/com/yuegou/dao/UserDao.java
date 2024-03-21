package com.yuegou.dao;

import com.yuegou.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper

public interface UserDao {
    boolean save(User user);
    boolean delete(Long id);
    boolean update(User user);
    boolean setEmpt(User user);
    User getById(Long id);
    User getUserPhone(User user);
    User getUserName(User user);
    List<User> getEmptTimeUser();
    List<User> getAll(@Param("size") Integer size,@Param("offset") Integer offset);
    List<User> getUserAndStore();
}
