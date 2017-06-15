package com.yu.spring.service;

import com.yu.spring.entity.Privilege;

import java.util.List;

/**
 * Created by Administrator on 2017/6/15.
 */
public interface PrivilegeService {
    List<Privilege> queryPrivilegeByUid(Integer uid); // 从数据库中获取所有权限

    void insertSelective(Privilege prDo);

    void updateByPrimaryKeySelective(Privilege record);

    void updateDoDel(Integer[] id);


    String selectPrivilegeAll(Privilege model);

    /**
     * 功能启用或者禁用
     */
    void updateEnableOrInhibit(Integer id, int status);

    /**
     * 查询所有权限
     *
     * @return
     */
    List<Privilege> queryPrivilege();
}
