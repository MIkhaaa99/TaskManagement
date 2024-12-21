package com.sarks.testtask.dto.employee;

import com.sarks.testtask.entity.Role;
import lombok.Builder;

@Builder
public record EmployeeDto(Long id, String username, Role role) {
}
