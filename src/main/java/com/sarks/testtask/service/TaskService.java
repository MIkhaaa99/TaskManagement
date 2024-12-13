package com.sarks.testtask.service;

import com.sarks.testtask.dto.task.CreatedOrUpdatedTaskDto;
import com.sarks.testtask.dto.task.TaskCreationDto;
import com.sarks.testtask.entity.Employee;
import com.sarks.testtask.entity.Priority;
import com.sarks.testtask.entity.Status;
import com.sarks.testtask.entity.Task;
import com.sarks.testtask.exceptions.MyEntityNotFoundException;
import com.sarks.testtask.mapper.TaskCreationToTaskMapper;
import com.sarks.testtask.mapper.TaskToCreatedTaskMapper;
import com.sarks.testtask.repository.TaskRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private EmployeeService employeeService;

    public CreatedOrUpdatedTaskDto createTask(TaskCreationDto taskCreationDto) {
        Employee admin = employeeService.findById(taskCreationDto.adminId());
        Employee performer = employeeService.findById(taskCreationDto.performerId());

        Task taskCandidate = TaskCreationToTaskMapper.convertFrom(taskCreationDto, admin, performer);
        log.debug("task for update in DB: {} ", taskCandidate);

        Task taskFromDb = taskRepository.save(taskCandidate);
        log.debug("Created task from DB: {} ", taskFromDb);

        return TaskToCreatedTaskMapper.convertFrom(taskFromDb);
    }

    public void deleteTaskById(Long id) {
        taskRepository.deleteById(id);
    }

    public CreatedOrUpdatedTaskDto updateStatus(Long id, Status status) {
        Task taskCandidate = findById(id);
        taskCandidate.setStatus(status);
        log.debug("Task for update in: {} ", taskCandidate);

        Task updatedTask = taskRepository.save(taskCandidate);
        log.debug("Updated task's status from DB: {} ", updatedTask);
        return TaskToCreatedTaskMapper.convertFrom(updatedTask);
    }

    public CreatedOrUpdatedTaskDto updatePriority(Long id, Priority priority) {
        Task taskCandidate = findById(id);
        taskCandidate.setPriority(priority);
        log.debug("Task for update in: {} ", taskCandidate);

        Task updatedTask = taskRepository.save(taskCandidate);
        log.debug("Updated task's priority from DB: {} ", updatedTask);
        return TaskToCreatedTaskMapper.convertFrom(updatedTask);
    }

    public CreatedOrUpdatedTaskDto updatePerformer(Long taskId, Long performerId) {
        Task taskCandidate = findById(taskId);
        Employee employeeCandidate = employeeService.findById(performerId);
        taskCandidate.setPerformer(employeeCandidate);
        log.debug("Task for update in: {} ", taskCandidate);

        Task updatedTask = taskRepository.save(taskCandidate);
        log.debug("Updated task's performer from DB: {} ", updatedTask);
        return TaskToCreatedTaskMapper.convertFrom(updatedTask);
    }

    public Task findById(Long id) {
        Task taskCandidate = taskRepository.findById(id).orElseThrow(() -> new MyEntityNotFoundException(id));
        log.debug("task is found in DB: {} ", taskCandidate);
        return taskCandidate;
    }

    public Task getReferenceById(Long id) {
        return taskRepository.getReferenceById(id);
    }
}
