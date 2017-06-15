package com.yu.spring.service.impl;

import com.yu.spring.dao.MenuDao;
import com.yu.spring.entity.Menu;
import com.yu.spring.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;

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

    @Override
    public List<Menu> queryMenuTreeAll() {
        return menuDao.queryMenuTreeAll();
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
