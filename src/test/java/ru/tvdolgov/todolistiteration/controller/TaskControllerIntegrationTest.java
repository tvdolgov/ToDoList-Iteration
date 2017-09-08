package ru.tvdolgov.todolistiteration.controller;

import ru.tvdolgov.todolistiteration.model.Task;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@Ignore
public class TaskControllerIntegrationTest {
    private final String ROOT   = "http://localhost:8080/task";
    private final String ADD    = "/add";
    private final String GET    = "/get";
    private final String ALL    = "/all";
    private final String DELETE = "/delete";
    private final String UPDATE = "/update";

    private Task createTask() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);

        Task task = prefillTask();

        HttpEntity<Task> httpEntity = new HttpEntity<>(task, headers);
        RestTemplate template = new RestTemplate();

        Task result = template.exchange(
                ROOT + ADD,
                HttpMethod.PUT,
                httpEntity,
                Task.class).getBody();

        assertNotNull(result);
        assertEquals("taskDescription", result.getDescription());
        assertNotNull(result.getId());

        return result;
    }

    private void deleteTask(long id) {
        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<String> responseEntity = restTemplate.exchange(
                ROOT + DELETE + "/{id}",
                HttpMethod.DELETE,
                null,
                String.class,
                id
        );

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    public void addTaskAndGet() {
        Task task = createTask();
        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<Task> responseEntity = restTemplate.exchange(
                ROOT + GET + "/{id}",
                HttpMethod.GET,
                null,
                Task.class,
                task.getId()
        );

        assertEquals("OK", responseEntity.getStatusCode().getReasonPhrase());
        Task result = responseEntity.getBody();
        assertEquals(task.getDescription(), result.getDescription());
        deleteTask(result.getId());
    }

    @Test
    public void updateTask() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);

        Task firstTask = createTask();
        firstTask.setDescription("updatedDescription");
        HttpEntity<Task> httpEntity = new HttpEntity<>(firstTask, headers);

        RestTemplate template = new RestTemplate();
        Task result = template.exchange(
                ROOT + UPDATE,
                HttpMethod.POST,
                httpEntity,
                Task.class).getBody();

        assertNotNull(result);
        assertEquals("updatedDescription", result.getDescription());
        assertNotNull(result.getId());
        deleteTask(firstTask.getId());
    }

    @Test
    public void getAllTasks() {
        RestTemplate restTemplate = new RestTemplate();

        Task firstTask = createTask();
        Task secondTask = createTask();

        ResponseEntity<List<Task>> responseEntity = restTemplate.exchange(
                ROOT + ALL,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Task>>() {
                }
        );

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        List<Task> resultList = responseEntity.getBody();
        assertNotNull(resultList);
        deleteTask(firstTask.getId());
        deleteTask(secondTask.getId());
    }

    private Task prefillTask() {
        Task task = new Task();
        task.setDescription("taskDescription");
        return task;
    }
}
