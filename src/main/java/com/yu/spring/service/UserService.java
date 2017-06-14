package com.yu.spring.service;


import com.yu.spring.entity.User;
import com.yu.spring.util.PageUtil;

import java.util.List;

public interface UserService {

    User findById(int id);

    User findBySso(String sso);

    void save(User user);
    PageUtil<User> findAll(User user);
}