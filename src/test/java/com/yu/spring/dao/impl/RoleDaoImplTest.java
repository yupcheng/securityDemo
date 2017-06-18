package com.yu.spring.dao.impl;

import com.yu.spring.BaseTest;
import com.yu.spring.entity.Role;
import com.yu.spring.service.RoleService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by Administrator on 2017/6/17.
 */

public class RoleDaoImplTest extends BaseTest{
    @Autowired
    RoleService roleService;
    @Test
    public void findById() throws Exception {
        Role role = roleService.findById(1);
        System.out.println(role);
    }

}