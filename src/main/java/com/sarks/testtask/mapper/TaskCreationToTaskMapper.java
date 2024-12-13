package com.sarks.testtask.mapper;

import com.sarks.testtask.dto.task.TaskCreationDto;
import com.sarks.testtask.entity.Employee;
import com.sarks.testtask.entity.Status;
import com.sarks.testtask.entity.Task;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TaskCreationToTaskMapper {

    public static Task convertFrom(TaskCreationDto taskCreationDto, Employee admin, Employee performer) {
        Task task = Task.builder()
                .title(taskCreationDto.title())
                .description(taskCreationDto.description())
                .status(Status.PENDING)
                .priority(taskCreationDto.priority())
                .admin(admin)
                .performer(performer)
                .build();
        log.debug("Convert to task: {} ", task);
        return task;
    }
}
