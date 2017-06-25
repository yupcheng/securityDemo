package com.yu.spring.dao.impl;

import com.yu.spring.BaseTest;
import com.yu.spring.dao.MenuDao;
import com.yu.spring.entity.Menu;
import com.yu.spring.service.MenuService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Administrator on 2017/6/24.
 */
public class MeunDaoImplTest extends BaseTest{
    @Autowired
    MenuDao menuDao;
    @Autowired
    MenuService menuService;
    @Test
    public void queryChildrenMenu() {
        List<Menu> list = menuDao.queryChildrenMenu(2);
        System.out.println(list);
    }
    @Test
    public void queryMenuTreeAll()
    {
        List<Menu> list = menuService.queryMenuTreeAll();
        System.out.println("***************************");
        System.out.println(list);
    }
}