package com.sarks.testtask.dto.comment;

import com.sarks.testtask.dto.employee.EmployeeDto;
import lombok.Builder;

@Builder
public record CreatedCommentDto(Long id, String text, EmployeeDto employee, Long taskId) {
}
