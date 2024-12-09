package com.sarks.testtask.controller;

import com.sarks.testtask.dto.TaskCreationDto;
import com.sarks.testtask.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/task")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @PostMapping
    public void createTask(@RequestBody TaskCreationDto taskCreationDto) {
        taskService.createTask(taskCreationDto);
    }
}
