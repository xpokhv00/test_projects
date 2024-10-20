package com.example.task_service;

import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Controller
public class TaskController {

    private final TaskRepository taskRepository;
    private final RestTemplate restTemplate;

    public TaskController(TaskRepository taskRepository, RestTemplate restTemplate) {
        this.taskRepository = taskRepository;
        this.restTemplate = restTemplate;
    }

    @QueryMapping
    public List<Task> allTasks() {
        return taskRepository.findAll();
    }

    @QueryMapping
    public Task task(Long id) {
        return taskRepository.findById(id).orElse(null);
    }

    @MutationMapping
    public Task createTask(String title, Long userId) {
        Task task = new Task();
        task.setTitle(title);
        task.setUserId(userId);
        return taskRepository.save(task);
    }

    @QueryMapping
    public User user(Long id) {
        // Fetch user details from User Service via REST
        return restTemplate.getForObject("http://localhost:8080/users/{id}", User.class, id);
    }
}
