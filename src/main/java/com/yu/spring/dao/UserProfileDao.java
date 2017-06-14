package com.yu.spring.dao;

import java.util.List;

import com.yu.spring.entity.UserProfile;

/**
 * 角色
 */
public interface UserProfileDao {

    List<UserProfile> findAll();

    UserProfile findByType(String type);

    UserProfile findById(int id);
}