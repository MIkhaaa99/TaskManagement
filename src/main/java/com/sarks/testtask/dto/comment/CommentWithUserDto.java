package com.sarks.testtask.dto.comment;

import com.sarks.testtask.dto.employee.EmployeeDto;

public record CommentWithUserDto(Long id, String text, EmployeeDto employee) {
}
