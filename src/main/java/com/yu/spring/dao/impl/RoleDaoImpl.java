package com.yu.spring.dao.impl;

import com.yu.spring.dao.AbstractDao;
import com.yu.spring.dao.RoleDao;
import com.yu.spring.entity.Role;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Administrator on 2017/6/12.
 */
@Repository("roleDao")
public class RoleDaoImpl extends AbstractDao<Integer,Role> implements RoleDao {
    @Override
    public Role findById(int id) {
        return getByKey(id);
    }

    @Override
    public List<Role> findAll() {
        Criteria crit = createEntityCriteria();
        crit.addOrder(Order.asc("type"));
        return (List<Role>)crit.list();
    }

    @Override
    public Role findByType(String type) {
        Criteria crit = createEntityCriteria();
        crit.add(Restrictions.eq("type", type));
        return (Role) crit.uniqueResult();
    }
}
