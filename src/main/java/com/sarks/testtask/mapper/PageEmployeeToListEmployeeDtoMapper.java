package com.sarks.testtask.mapper;

import com.sarks.testtask.dto.employee.EmployeeDto;
import com.sarks.testtask.entity.Employee;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;

import java.util.List;

@Slf4j
public class PageEmployeeToListEmployeeDtoMapper {

    public static List<EmployeeDto> convertFrom(Page<Employee> employeePage) {
        List<Employee> employees = employeePage.getContent();

        var employeeDtoList = employeePage.stream().map(EmployeeToEmployeeDtoMapper::convertFrom).toList();
        log.debug("Convert to List<EmployeeDto>: {} ", employeeDtoList);
        return employeeDtoList;
    }
}
