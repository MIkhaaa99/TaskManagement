package com.sarks.testtask.dto.employee;

import com.sarks.testtask.dto.comment.CommentWithUserDto;
import lombok.Builder;

import java.util.List;

@Builder
public record EmployeeTasksWithCommentsDto(Long id, String title, String description,
                                           EmployeeDto admin, EmployeeDto performer,
                                           List<CommentWithUserDto> comments) {
}
