package com.sarks.testtask.service;

import com.sarks.testtask.dto.comment.CommentWithUserDto;
import com.sarks.testtask.dto.employee.EmployeeCreationDto;
import com.sarks.testtask.dto.employee.EmployeeDto;
import com.sarks.testtask.dto.employee.EmployeeTasksWithCommentsDto;
import com.sarks.testtask.entity.Comment;
import com.sarks.testtask.entity.Employee;
import com.sarks.testtask.entity.Role;
import com.sarks.testtask.entity.Task;
import com.sarks.testtask.exceptions.EntityAlreadyExistsException;
import com.sarks.testtask.exceptions.MyEntityNotFoundException;
import com.sarks.testtask.mapper.PageEmployeeToListEmployeeDtoMapper;
import com.sarks.testtask.repository.EmployeeRepository;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@Getter
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    public List<EmployeeDto> getEmployees(Pageable pageable) {
        var employees = employeeRepository.findAll(pageable);
        return PageEmployeeToListEmployeeDtoMapper.convertFrom(employees);
    }

    public Employee findById(Long id) {
        Employee employeeCandidate = employeeRepository.findById(id).orElseThrow(() -> new MyEntityNotFoundException(id));
        log.debug("employee is found in DB: {} ", employeeCandidate);
        return employeeCandidate;
    }

    public Employee getReferenceById(Long id) {
        return employeeRepository.getReferenceById(id);
    }

    public void deleteEmployeeById(Long id) {
        employeeRepository.deleteById(id);
    }

    public EmployeeDto createEmployee(EmployeeCreationDto employeeCreationDto) {
        Employee employee = Employee.builder()
                .username(employeeCreationDto.getUsername())
                .password(employeeCreationDto.getPassword())
                .role(Role.PERFORMER)
                .build();

        Employee employeeFromDB;
        try {
            employeeFromDB = employeeRepository.save(employee);
        } catch (DataIntegrityViolationException ex) {
            throw new EntityAlreadyExistsException(ex.getMessage());
        }

        log.debug("Created employee from DB: {} ", employeeFromDB);
        EmployeeDto employeeDto = new EmployeeDto(employeeFromDB.getId(), employeeFromDB.getUsername(), employeeFromDB.getRole());
        log.debug("EmployeeDto: {} ", employeeFromDB);
        return employeeDto;
    }

    public List<EmployeeTasksWithCommentsDto> getAllTasksAndComments(Long id, Pageable pageable) {
        List<Task> tasks = employeeRepository.findAllTasksAndComments(id, pageable);
        List<EmployeeTasksWithCommentsDto> result = tasks.stream()
                .map(task -> {
                    List<Comment> comments = task.getComments();
                    List<CommentWithUserDto> commentDtos = comments.stream()
                            .map(comment -> new CommentWithUserDto(comment.getId(), comment.getText(), new EmployeeDto(comment.getEmployee().getId(), comment.getEmployee().getUsername(), comment.getEmployee().getRole())))
                            .collect(Collectors.toList());
                    return new EmployeeTasksWithCommentsDto(task.getId(), task.getTitle(), task.getDescription(),
                            new EmployeeDto(task.getAdmin().getId(), task.getAdmin().getUsername(), task.getAdmin().getRole()),
                            new EmployeeDto(task.getPerformer().getId(), task.getPerformer().getUsername(), task.getPerformer().getRole()),
                            commentDtos);
                }).collect(Collectors.toList());

        System.out.println(result);
        return result;
    }

}
