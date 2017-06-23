package com.yu.spring.dao;


import com.yu.spring.entity.User;
import com.yu.spring.util.PageUtil;

import java.util.List;

public interface UserDao {

    User findById(int id);

    User findBySSO(String sso);

    void persist(User user);
    PageUtil<User> queryPage(User user,PageUtil<User> pageUtil);
}