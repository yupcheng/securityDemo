package com.yu.spring.dao.impl;

import com.yu.spring.dao.AbstractDao;
import com.yu.spring.dao.MenuDao;
import com.yu.spring.dao.RoleDao;
import com.yu.spring.dao.UserDao;
import com.yu.spring.entity.Menu;
import com.yu.spring.entity.Role;
import com.yu.spring.entity.User;
import com.yu.spring.util.PageUtil;
import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
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
        User user = userDao.findById(uid);
        if(user == null || user.getRoles() == null)
        {
            return null;
        }
        Set<Role> roles = user.getRoles();
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

    /**
     * 查询子节点菜单，包括自己
     * @param id
     * @return
     */
    @Override
    public List<Menu> queryChildrenMenu(Integer id) {
        /*Criteria criteria = createEntityCriteria();
        criteria.add(Restrictions.eq("parentMenuId", id));*/
        Session session = getSession();
        String sql = "select * from menu where FIND_IN_SET(id , getChildList( ? )) > 0";
        return session.createSQLQuery(sql).addEntity(Menu.class).setInteger(0,id).list();
        //sq.setInteger(0,id);
        //return sq.list();
    }

    @Override
    public List<Menu> queryMenuTreeAll() {

        return null;
    }

    @Override
    public List<Map> queryJsonTreeAll() {
        Session session = getSession();
        String sql = "select * , title as text from menu order by sort ";
        return session.createSQLQuery(sql).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).list();
    }

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
