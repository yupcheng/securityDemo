package com.yu.spring.service;

import com.yu.spring.entity.UserProfile;

import java.util.List;

/**
 * Created by Administrator on 2017/6/10.
 */
public interface UserProfileService {

    List<UserProfile> findAll();

    UserProfile findByType(String type);

    UserProfile findById(int id);
}
