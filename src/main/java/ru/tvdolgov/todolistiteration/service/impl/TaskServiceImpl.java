package ru.tvdolgov.todolistiteration.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.tvdolgov.todolistiteration.dao.TaskDao;
import ru.tvdolgov.todolistiteration.model.Task;
import ru.tvdolgov.todolistiteration.service.TaskService;

import java.util.List;

@Service(value = "taskService")
public class TaskServiceImpl implements TaskService {
    @Autowired
    private TaskDao taskDao;

    @Override
    public Task getById(long id) {
        return taskDao.getById(id);
    }

    @Override
    public Task addTask(Task task) {
        return taskDao.create(task);
    }

    @Override
    public Task update(Task task) {
        return taskDao.update(task);
    }

    @Override
    public Task delete(long id) {
        Task taskForDelete = taskDao.getById(id);
        return taskDao.delete(taskForDelete);
    }

    @Override
    public List<Task> getList() {
        return taskDao.getList();
    }
}
