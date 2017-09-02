package ru.tvdolgov.todolistiteration.dao.impl;

import ru.tvdolgov.todolistiteration.dao.TaskDao;
import ru.tvdolgov.todolistiteration.model.Task;

public class TaskDaoImpl extends PrimaryDaoImpl<Task> implements TaskDao {
    public TaskDaoImpl(Class<Task> entityClass) {
        super(entityClass);
    }
}
