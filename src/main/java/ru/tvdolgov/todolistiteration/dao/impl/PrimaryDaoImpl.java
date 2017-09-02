package ru.tvdolgov.todolistiteration.dao.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import ru.tvdolgov.todolistiteration.dao.PrimaryDao;

import java.util.List;

@Transactional
public class PrimaryDaoImpl<T> implements PrimaryDao<T> {
    private final Class<T> entityClass;

    @Autowired
    protected SessionFactory sessionFactory;

    public PrimaryDaoImpl(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    @Override
    public Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    public T getById(long id) {
        return getCurrentSession().get(entityClass, id);
    }

    @Override
    public T create(T entity) {
        getCurrentSession().save(entity);
        return entity;
    }

    @Override
    public T update(T entity) {
        getCurrentSession().update(entity);
        return entity;
    }

    @Override
    public T delete(T entity) {
        getCurrentSession().delete(entity);
        return entity;
    }

    @Override
    public List<T> getList() {
        return getCurrentSession().createQuery( "from " + entityClass.getName()).list();

    }
}
