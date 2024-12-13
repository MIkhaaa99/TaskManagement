package com.sarks.testtask.dto.task;

import com.sarks.testtask.dto.employee.EmployeeDto;
import com.sarks.testtask.entity.Priority;
import com.sarks.testtask.entity.Status;
import lombok.Builder;

@Builder
public record CreatedOrUpdatedTaskDto(Long id, String title, String description, Status status,
                                      Priority priority, EmployeeDto admin, EmployeeDto performer) {

}
