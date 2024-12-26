package com.sarks.testtask.controller;

import com.sarks.testtask.dto.employee.EmployeeCreationDto;
import com.sarks.testtask.dto.employee.EmployeeDto;
import com.sarks.testtask.dto.employee.EmployeeTasksWithCommentsDto;
import com.sarks.testtask.entity.Employee;
import com.sarks.testtask.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteEmployee(@PathVariable Long id) {
        employeeService.deleteById(id);
        return new ResponseEntity<>("User is deleted", HttpStatus.ACCEPTED);
    }

    @PostMapping
    public ResponseEntity<Object> createEmployee(@Validated @RequestBody EmployeeCreationDto employeeCreationDto) {
        EmployeeDto employeeDto = employeeService.createEmployee(employeeCreationDto);
        return new ResponseEntity<>(employeeDto, HttpStatus.CREATED);
    }

    @GetMapping
    public List<EmployeeDto> getEmployees(@RequestParam(defaultValue = "0") int page,
                                       @RequestParam(defaultValue = "20") int size) {
        Pageable paging = PageRequest.of(page, size);
        return employeeService.findAll(paging);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getEmployeeById(@PathVariable Long id) {
        Employee employee = employeeService.findById(id);
        return new ResponseEntity<>(employee, HttpStatus.OK);
    }

    @GetMapping("/{adminId}/tasks")
    public List<EmployeeTasksWithCommentsDto> getTasksByAdminId(@PathVariable("adminId") Long id,
                                                                @RequestParam(defaultValue = "0") int page,
                                                                @RequestParam(defaultValue = "2") int size) {
        Pageable paging = PageRequest.of(page, size);
        var tasks = employeeService.getAllTasksAndComments(id, paging);
        return tasks;
    }
}
