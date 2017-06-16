package com.yu.spring.dao.impl;

import com.yu.spring.dao.UserDao;
import com.yu.spring.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by Administrator on 2017/6/16.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:application.xml"})
public class UserDaoImplTest {

    @Autowired
    private UserDao dao ;
    @Test
    public void save() throws Exception {
        User user = new User();
        user.setPassword("123");
        user.setSsoId("wangwu");
        user.setEmail("1107674062@qq.com");
        user.setFirstName("zhang");
        user.setLastName("san");
        dao.save(user);
    }

}