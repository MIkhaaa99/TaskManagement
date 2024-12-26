package com.sarks.testtask.mapper;

import com.sarks.testtask.dto.employee.EmployeeDto;
import com.sarks.testtask.entity.Employee;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class EmployeeToEmployeeDtoMapper {

    public static EmployeeDto convertFrom(Employee employee) {
        var employeeDto = EmployeeDto.builder()
                .id(employee.getId())
                .username(employee.getUsername())
                .role(employee.getRole())
                .build();
        log.debug("Convert to EmployeeDto: {} ", employeeDto);
        return employeeDto;
    }
}
