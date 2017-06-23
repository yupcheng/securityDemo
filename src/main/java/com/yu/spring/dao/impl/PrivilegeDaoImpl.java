package com.yu.spring.dao.impl;

import com.yu.spring.dao.AbstractDao;
import com.yu.spring.dao.PrivilegeDao;
import com.yu.spring.dao.UserDao;
import com.yu.spring.entity.Menu;
import com.yu.spring.entity.Privilege;
import com.yu.spring.entity.Role;
import com.yu.spring.entity.User;
import com.yu.spring.util.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/6/12.
 */
@Repository
public class PrivilegeDaoImpl extends AbstractDao<Integer,Privilege> implements PrivilegeDao {

    @Autowired
    private UserDao userDao;

    @Override
    public List<Privilege> queryPrivilegeByUid(Integer uid) {
        User user = userDao.findById(uid);
        List<Privilege> privileges = new ArrayList<>();
        if(user != null && user.getRoles().size() > 0)
        {
            for(Role role : user.getRoles())
            {
                if(role.getMenus() != null && role.getMenus().size() >0)
                {
                    for(Menu menu : role.getMenus())
                    {
                        privileges.addAll(new ArrayList<Privilege>(menu.getPrivileges()));
                    }

                }
            }
        }
        return privileges;
    }

    @Override
    public void insertSelective(Privilege prDo) {
        persist(prDo);
    }

    @Override
    public void updateByPrimaryKeySelective(Privilege record) {
        update(record);
    }

    @Override
    public void updateDoDel(Integer[] id) {

        for(int i : id)
        {
            Privilege privilege = new Privilege();
            privilege.setIsDeleted(1);
            privilege.setIsDeleted(i);
            update(privilege);
        }
    }

    /**
     * 批量保存
     * @param model
     * @return
     */
    @Override
    public String selectPrivilegeAll(Privilege model) {

        return null;
    }

    @Override
    public void updateEnableOrInhibit(Integer id, int status) {
        Privilege privilege = new Privilege();
        privilege.setStatus(status);
        privilege.setId(id);
        update(privilege);
    }

    @Override
    public List<Privilege> queryPrivilege() {
        PageUtil<Privilege> pageUtil = new PageUtil<>(0,20);
        getAll(null,pageUtil);
        return pageUtil.getRows();
    }
}
