package com.yu.spring.service.impl;

import com.yu.spring.dao.PrivilegeDao;
import com.yu.spring.entity.Privilege;
import com.yu.spring.service.PrivilegeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by Administrator on 2017/6/15.
 */
@Service
@Transactional
public class PrivilegeServiceImpl implements PrivilegeService {

    @Autowired
    PrivilegeDao privilegeDao;
    @Override
    public List<Privilege> queryPrivilegeByUid(Integer uid) {
        return privilegeDao.queryPrivilegeByUid(uid);
    }

    @Override
    public void insertSelective(Privilege prDo) {
        privilegeDao.insertSelective(prDo);
    }

    @Override
    public void updateByPrimaryKeySelective(Privilege record) {
        privilegeDao.updateByPrimaryKeySelective(record);
    }

    @Override
    public void updateDoDel(Integer[] id) {
        privilegeDao.updateDoDel(id);
    }

    @Override
    public String selectPrivilegeAll(Privilege model) {
        return privilegeDao.selectPrivilegeAll(model);
    }

    @Override
    public void updateEnableOrInhibit(Integer id, int status) {
        privilegeDao.updateEnableOrInhibit(id,status);
    }

    @Override
    public List<Privilege> queryPrivilege() {
        return privilegeDao.queryPrivilege();
    }
}
