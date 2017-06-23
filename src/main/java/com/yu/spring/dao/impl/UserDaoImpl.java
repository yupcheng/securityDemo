package com.yu.spring.dao.impl;

import com.yu.spring.dao.AbstractDao;
import com.yu.spring.dao.UserDao;
import com.yu.spring.entity.User;
import com.yu.spring.util.PageUtil;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.omg.CORBA.PUBLIC_MEMBER;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("userDao")
public class UserDaoImpl extends AbstractDao<Integer, User> implements UserDao {

    public User findById(int id) {
        return getByKey(id);
    }

    public User findBySSO(String sso) {
        Criteria crit = createEntityCriteria();
        crit.add(Restrictions.eq("ssoId", sso));
        return (User) crit.uniqueResult();
    }


    public PageUtil<User> queryPage(User user,PageUtil<User> pageUtil) {
        //PageUtil<User> pageUtil = new PageUtil<>(0,20);
        return getAll(user,pageUtil);
    }
}