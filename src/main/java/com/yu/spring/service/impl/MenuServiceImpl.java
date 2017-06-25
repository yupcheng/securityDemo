package com.yu.spring.service.impl;

import com.yu.spring.dao.MenuDao;
import com.yu.spring.entity.Menu;
import com.yu.spring.service.MenuService;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigInteger;
import java.util.*;

import static java.awt.SystemColor.menu;
import static sun.security.krb5.Confounder.intValue;

/**
 * Created by Administrator on 2017/6/15.
 */
@Service
@Transactional
public class MenuServiceImpl implements MenuService {
    @Autowired
    MenuDao menuDao;
    @Override
    public List<Menu> queryMenuByUid(Integer uid) {
        return menuDao.queryMenuByUid(uid);
    }

    @Override
    public List<Menu> queryChildMenubyUser(Map map) {
        return menuDao.queryChildMenubyUser(map);
    }

    @Override
    public List<Menu> queryChildrenMenu(Integer id) {
        return menuDao.queryChildrenMenu(id);
    }

    @Override
    public List<Menu> queryMenu() {
        return null;
    }

    public List<Map> queryJsonTreeAll()
    {
        List<Map> list = menuDao.queryJsonTreeAll();
        List<Map> parent = new ArrayList<>();
        for(Map menu : list)
        {
            if(((BigInteger)menu.get("parentMenuId")).intValue() == 0)
            {
                parent.add(menu);
            }
        }
        list.removeAll(parent);
       for(Map m : parent)
       {
           setJsonChildren(m , list);
       }
        return parent;
    }

    public void setJsonChildren(Map parent , List<Map> list)
    {
        BigInteger pid = (BigInteger)parent.get("id");
        List<Map> children = new ArrayList<>();
        for(Map m : list)
        {
            if(m.get("parentMenuId") == pid)
            {
                setJsonChildren(m,list);
                children.add(m);
            }
        }
        if(children.size() > 0)
        {
            list.removeAll(children);
            parent.put("nodes",children);
        }else
        {
            return ;
        }
    }
    @Override
    public List<Menu> queryMenuTreeAll() {
        List<Menu> menuList = menuDao.queryAll();
        List<Menu> parent = new ArrayList<>();
        for(Menu menu : menuList)
        {
            if(menu.getParentMenuId() == 0 )
            {
                parent.add(menu);
            }
        }
        menuList.removeAll(parent);
        for(Menu menu : parent)
        {
            setChildren(menu,menuList);
        }
        return parent;
    }

    /**
     *
     * @param menu
     * @param list
     * @return
     */
    public void setChildren(Menu menu,List<Menu> list)
    {
        int pid = menu.getId();
        Set<Menu> children = new HashSet<>();
        for(Menu m : list)
        {
            if(m.getParentMenuId() == pid)
            {
                setChildren(m,list);
                children.add(m);
            }
        }
        if(children.size() > 0)
        {
            list.removeAll(children);
            menu.setChildren(children);
        }else
        {
            return ;
        }
    }

    @Override
    public List<Menu> queryMenuTreeAllByUser(Integer uid) {
        return menuDao.queryMenuTreeAllByUser(uid);
    }

    @Override
    public void updateMenu(Menu menu) {
        menuDao.updateMenu(menu);
    }

    @Override
    public List<Menu> queryMenuRoleByRid(Integer roleId) {
        return menuDao.queryMenuRoleByRid(roleId);
    }
}
