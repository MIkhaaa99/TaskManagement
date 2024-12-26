package com.sarks.testtask.mapper;

import com.sarks.testtask.dto.employee.EmployeeCreationDto;
import com.sarks.testtask.entity.Employee;
import com.sarks.testtask.entity.Role;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class EmployeeCreationDtoToEmployee {

    public static Employee convertFrom(EmployeeCreationDto employeeCreationDto) {
        Employee employee = Employee.builder()
                .username(employeeCreationDto.getUsername())
                .password(employeeCreationDto.getPassword())
                .role(Role.PERFORMER)
                .build();
        log.debug("Convert to Employee: {} ", employee);
        return employee;
    }
}
