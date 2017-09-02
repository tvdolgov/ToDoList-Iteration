package ru.tvdolgov.todolistiteration.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.tvdolgov.todolistiteration.dao.TaskDao;
import ru.tvdolgov.todolistiteration.dao.impl.TaskDaoImpl;
import ru.tvdolgov.todolistiteration.model.Task;

@Configuration
public class AppConfig {
    @Bean
    public TaskDao taskDao(){
        return new TaskDaoImpl(Task.class);
    }

}
