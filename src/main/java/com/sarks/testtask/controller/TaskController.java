package com.sarks.testtask.controller;

import com.sarks.testtask.dto.task.CreatedOrUpdatedTaskDto;
import com.sarks.testtask.dto.task.TaskCreationDto;
import com.sarks.testtask.entity.Priority;
import com.sarks.testtask.entity.Status;
import com.sarks.testtask.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @PostMapping
    public ResponseEntity<Object> createTask(@RequestBody TaskCreationDto taskCreationDto) {
        CreatedOrUpdatedTaskDto task = taskService.createTask(taskCreationDto);
        return new ResponseEntity<>(task, HttpStatus.CREATED);
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<Object> updateTaskStatus(@PathVariable Long id, @RequestBody Status status) {
        CreatedOrUpdatedTaskDto task = taskService.updateStatus(id, status);
        return new ResponseEntity<>(task, HttpStatus.ACCEPTED);
    }

    @PatchMapping("/{id}/priority")
    public ResponseEntity<Object> updateTaskPriority(@PathVariable Long id, @RequestBody Priority priority) {
        CreatedOrUpdatedTaskDto task = taskService.updatePriority(id, priority);
        return new ResponseEntity<>(task, HttpStatus.ACCEPTED);
    }

    @PatchMapping("/{task_id}/performer/{performer_id}")
    public ResponseEntity<Object> updateTaskPerformer(@PathVariable("task_id") Long taskId, @PathVariable("performer_id") Long performerId) {
        CreatedOrUpdatedTaskDto task = taskService.updatePerformer(taskId, performerId);
        return new ResponseEntity<>(task, HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteTaskById(@PathVariable Long id) {
        taskService.deleteTaskById(id);
        return new ResponseEntity<>("Task is deleted", HttpStatus.ACCEPTED);
    }

}
