package com.sarks.testtask.mapper;

import com.sarks.testtask.dto.task.CreatedOrUpdatedTaskDto;
import com.sarks.testtask.dto.employee.EmployeeDto;
import com.sarks.testtask.entity.Task;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TaskToCreatedTaskMapper {

    public static CreatedOrUpdatedTaskDto convertFrom(Task task) {
        CreatedOrUpdatedTaskDto createdOrUpdatedTaskDto = CreatedOrUpdatedTaskDto.builder()
                .id(task.getId())
                .title(task.getTitle())
                .description(task.getDescription())
                .admin(EmployeeDto.builder()
                        .id(task.getAdmin().getId())
                        .username(task.getAdmin().getUsername())
                        .build())
                .performer(EmployeeDto.builder()
                        .id(task.getPerformer().getId())
                        .username(task.getPerformer().getUsername())
                        .build())
                .build();
        log.debug("Convert to createdOrUpdatedTaskDto: {} ", createdOrUpdatedTaskDto);
        return createdOrUpdatedTaskDto;
    }
}
