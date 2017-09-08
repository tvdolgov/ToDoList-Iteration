package ru.tvdolgov.todolistiteration.controller;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;
import ru.tvdolgov.todolistiteration.model.Task;
import ru.tvdolgov.todolistiteration.service.TaskService;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(value = "/task")
public class TaskController {
    @Autowired
    private TaskService taskService;

    @RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Task getById(@PathVariable(value = "id") String inputId) {
        return taskService.getById(Long.parseLong(inputId));
    }

    @RequestMapping(value = "/add", method = RequestMethod.PUT, produces = "application/json;charset=utf-8")
    @ResponseBody
    public Task addTask(@RequestBody Task task){
        task.setCreationDate(new Date(System.currentTimeMillis()));
        return taskService.addTask(task);
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    @ResponseBody
    public Task updateTask(@RequestBody Task task) {
        return taskService.update(task);
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public Task deleteTask(@PathVariable(value = "id") String inputId) {
        return taskService.delete(Long.parseLong(inputId));
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    @ResponseBody
    public List<Task> getTasks() {
        return taskService.getList();
    }
}
