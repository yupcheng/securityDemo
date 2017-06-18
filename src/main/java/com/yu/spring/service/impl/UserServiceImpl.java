package com.yu.spring.service.impl;

import com.yu.spring.dao.UserDao;
import com.yu.spring.entity.User;
import com.yu.spring.service.UserService;
import com.yu.spring.util.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User findById(int id) {
        return userDao.findById(id);
    }
    @Override
    public PageUtil<User> findAll(User user) {
        return userDao.findAll(user);
    }

    public User findBySso(String sso) {
        return userDao.findBySSO(sso);
    }

    public void save(User user){

        try {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            userDao.persist(user);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}