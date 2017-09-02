package ru.tvdolgov.todolistiteration.service;

import ru.tvdolgov.todolistiteration.model.Task;

import java.util.List;


public interface TaskService {
    /**
     * Find Task at database
     * @param id - id of task from database
     * @return task
     */
    Task getById(long id);

    /**
     * Add Task to database
     * @param task - task for creation
     * @return created task
     */
    Task addTask(Task task);

    /**
     * Update Task
     * @param task - task for update
     * @return updates task
     */
    Task update(Task task);

    /**
     * Delete task from database
     * @param id - id of deleted task
     * @return deleted task
     */
    Task delete(long id);

    /**
     * Find all task by type
     * @return list of tasks
     */
    List<Task> getList();
}
