package com.yu.spring.service;

import com.yu.spring.entity.Role;

import java.util.List;

/**
 * Created by Administrator on 2017/6/12.
 */
public interface RoleService {
    Role findById(int id);
    List<Role> findAll();
    Role findByType(String type);
}
