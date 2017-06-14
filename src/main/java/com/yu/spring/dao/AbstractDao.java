package com.yu.spring.dao;

import java.io.Serializable;

import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;

import com.yu.spring.util.PageUtil;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class AbstractDao<PK extends Serializable, T> {

    private final Class<T> persistentClass;

    @SuppressWarnings("unchecked")
    public AbstractDao(){
        this.persistentClass =(Class<T>) ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[1];
    }

    @Autowired
    private SessionFactory sessionFactory;

    protected Session getSession(){
        return sessionFactory.openSession();
    }

    @SuppressWarnings("unchecked")
    public T getByKey(PK key) {
        return (T) getSession().get(persistentClass, key);
    }

    public void save(T entity) {
        getSession().persist(entity);
    }

    public void delete(T entity) {
        getSession().delete(entity);
    }
    public void update(T entity){
        getSession().update(entity);
    }

    protected Criteria createEntityCriteria(){
        return getSession().createCriteria(persistentClass);
    }

    /**
     * 分页条件查询
     * @return
     */
    public PageUtil getAll(T t, PageUtil<T> pageUtil)
    {
        List<T> list =new ArrayList<>();
        //Session session = getSession();
        Criteria criteria = createEntityCriteria();
        //利用对象属性值生成查询条件
        if(t != null)
        {
            criteria.add(Example.create(t));
        }
        int totalNum = ((Long)criteria.setProjection(Projections.rowCount()).uniqueResult()).intValue();
        int totalPage = totalNum % pageUtil.getPageSize() == 0 ? totalNum / pageUtil.getPageSize() :totalNum / pageUtil.getPageSize() + 1 ;
        criteria.setProjection(null);
        pageUtil.setTotal(totalNum);
        pageUtil.setTotalPage(totalPage);
        if(pageUtil.getOrderBy() != null && !pageUtil.getOrderBy().equals(""))
        {

            if(pageUtil.isDesc())
            {
                list = criteria.setFirstResult(pageUtil.getPageIndex()*pageUtil.getPageSize()).setMaxResults(pageUtil.getPageSize()).
                        addOrder(Order.desc(pageUtil.getOrderBy())).list();
            }
            else
            {
                list = criteria.setFirstResult(pageUtil.getPageIndex()*pageUtil.getPageSize()).setMaxResults(pageUtil.getPageSize()).
                        addOrder(Order.asc(pageUtil.getOrderBy())).list();
            }
        }
        else
        {
            list = criteria.setFirstResult(pageUtil.getPageIndex()*pageUtil.getPageSize()).setMaxResults(pageUtil.getPageSize()).
                    list();
        }
        pageUtil.setList(list);
        return pageUtil;
    }


}