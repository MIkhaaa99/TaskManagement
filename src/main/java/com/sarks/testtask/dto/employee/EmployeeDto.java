package com.sarks.testtask.dto.employee;

import lombok.Builder;

@Builder
public record EmployeeDto(Long id, String username) {
}
