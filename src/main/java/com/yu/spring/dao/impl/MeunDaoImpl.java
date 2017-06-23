package com.yu.spring.dao.impl;

import com.yu.spring.dao.AbstractDao;
import com.yu.spring.dao.MenuDao;
import com.yu.spring.dao.RoleDao;
import com.yu.spring.dao.UserDao;
import com.yu.spring.entity.Menu;
import com.yu.spring.entity.Role;
import com.yu.spring.util.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.*;

/**
 * Created by Administrator on 2017/6/12.
 */
@Repository
public class MeunDaoImpl extends AbstractDao<Integer,Menu> implements MenuDao{
    @Autowired
    private UserDao userDao;

    @Autowired
    private RoleDao roleDao;
    /***
     * 用户——>角色-->菜单
     * @param uid
     * @return
     */

    @Override
    public List<Menu> queryMenuByUid(Integer uid) {
        Set<Role> roles = userDao.findById(uid).getRoles();
        List<Menu> menus = new ArrayList<>();
        if(roles != null && roles.size() > 0)
        {

            for(Role role : roles)
            {
                if(role.getMenus() != null && role.getMenus().size() >0)
                {
                    menus.addAll(new ArrayList<Menu>(role.getMenus()));
                }
            }
        }
        return menus;
    }
    @Override
    public List<Menu> queryChildMenubyUser(Map map) {
        return null;
    }

    @Override
    public List<Menu> queryChildrenMenu(Integer id) {
        return null;
    }

    @Override
    public List<Menu> queryMenuTreeAll() {
        return null;
    }


    @Override
    public List<Menu> queryMenu() {
        PageUtil<Menu> pageUtil = new PageUtil<>(0,20);
        getAll(new Menu(),pageUtil);
        return pageUtil.getRows();
    }


    @Override
    public List<Menu> queryMenuTreeAllByUser(Integer uid) {
        return null;
    }

    public void updateMenu(Menu menu) {
        update(menu);
    }



    /**
     * 指定角色下菜单
     * @param roleId
     * @return
     */
    @Override
    public List<Menu> queryMenuRoleByRid(Integer roleId) {
        return new ArrayList<>(roleDao.findById(roleId).getMenus());
    }
}
