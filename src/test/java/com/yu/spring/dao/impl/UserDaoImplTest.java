package com.yu.spring.dao.impl;

import com.yu.spring.BaseTest;
import com.yu.spring.dao.UserDao;
import com.yu.spring.entity.User;
import com.yu.spring.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by Administrator on 2017/6/16.
 */

public class UserDaoImplTest extends BaseTest{

    @Autowired
    private UserDao dao ;
    @Autowired
    private UserService userService;
    @Test
    public void save() throws Exception {
        User user = new User();
        //user.setId(null);
        user.setPassword("123");
        user.setSsoId("ddd");
        user.setEmail("1107674062@qq.com");
        user.setFirstName("zhang");
        user.setLastName("san");
        //dao.save(user);
        userService.save(user);
    }

}