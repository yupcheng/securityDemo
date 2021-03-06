package com.yu.spring.dao;

import com.yu.spring.entity.Menu;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/6/12.
 */
public interface MenuDao {

    /**
     * 查询指定用户下的菜单权限
     *
     * @param uid
     * @return
     */
    List<Menu> queryMenuByUid(Integer uid);

    /**
     * 查询菜单子菜单
     *
     * @return
     */
    List<Menu> queryChildMenubyUser(Map map);

    /**
     * 查询菜单子菜单
     *
     * @return
     */
    List<Menu> queryChildrenMenu(Integer id);

    /**
     * 查询所有菜单
     *
     * @return
     */
    List<Menu> queryAll();

    /**
     * 所有的
     *
     * @return
     */
    List<Menu> queryMenuTreeAll();

    List<Map> queryJsonTreeAll();
    /**
     * 查询指定用户下的菜单
     *
     * @param uid
     * @return
     */
    List<Menu> queryMenuTreeAllByUser(Integer uid);

    void updateMenu(Menu menu);



    /**
     * 查询指定角色下的菜单
     *
     * @return
     */
    List<Menu> queryMenuRoleByRid(Integer roleId);


}
