package com.yu.spring.dao;


import com.yu.spring.entity.User;
import com.yu.spring.util.PageUtil;

import java.util.List;

public interface UserDao {

    User findById(int id);

    User findBySSO(String sso);

    void save(User user);
    PageUtil<User> findAll(User user);
}