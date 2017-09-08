package ru.tvdolgov.todolistiteration.dao.impl;

import ru.tvdolgov.todolistiteration.dao.TaskDao;
import ru.tvdolgov.todolistiteration.model.Task;

import java.util.List;

public class TaskDaoImpl extends PrimaryDaoImpl<Task> implements TaskDao {
    public TaskDaoImpl(Class<Task> entityClass) {
        super(entityClass);
    }

    @Override
    public List<Task> getList() {
        return getCurrentSession().createQuery( "from Task ORDER BY creationDate DESC").list();
    }
}
