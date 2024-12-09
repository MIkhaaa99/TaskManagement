package com.sarks.testtask.service;

import com.sarks.testtask.dto.TaskCreationDto;
import com.sarks.testtask.entity.Status;
import com.sarks.testtask.entity.Task;
import com.sarks.testtask.repository.EmployeeRepository;
import com.sarks.testtask.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    public void createTask(TaskCreationDto taskCreationDto) {
        Task taskCandidate = Task.builder()
                .title(taskCreationDto.getTitle())
                .description(taskCreationDto.getDescription())
                .status(Status.PENDING)
                .priority(taskCreationDto.getPriority())
                .admin(employeeRepository.getReferenceById(taskCreationDto.getAdminId()))
                .performer(employeeRepository.getReferenceById(taskCreationDto.getPerformerId()))
                .build();
        taskRepository.save(taskCandidate);
    }
}
