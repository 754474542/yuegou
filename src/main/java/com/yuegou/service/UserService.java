package com.yuegou.service;

import com.yuegou.entity.User;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Transactional
public interface UserService {
    boolean save(User user);
    boolean delete(Long id);
    boolean update(User user);
    User getById(Long id);
    User getUserName(User user);
    List<User> getAll(Integer size,Integer offset);
    List<User> getUserAndStore();
}
