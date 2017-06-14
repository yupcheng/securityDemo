package com.yu.spring.dao;

import com.yu.spring.entity.Role;

import java.util.List;

/**
 * Created by Administrator on 2017/6/12.
 */
public interface RoleDao {
    Role findById(int id);
    List<Role> findAll();
    Role findByType(String type);
}
