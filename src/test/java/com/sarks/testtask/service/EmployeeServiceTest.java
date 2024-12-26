package com.sarks.testtask.service;

import com.sarks.testtask.dto.employee.EmployeeCreationDto;
import com.sarks.testtask.dto.employee.EmployeeDto;
import com.sarks.testtask.entity.Employee;
import com.sarks.testtask.entity.Role;
import com.sarks.testtask.exceptions.EntityAlreadyExistsException;
import com.sarks.testtask.exceptions.MyEntityNotFoundException;
import com.sarks.testtask.repository.EmployeeRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;

class EmployeeServiceTest {

    private List<Employee> employees = new ArrayList<>();

    @Mock
    private EmployeeRepository employeeRepository;

    @InjectMocks
    private EmployeeService employeeService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void whenFindAll_thenReturnListOfEmployees() {
        Employee employee1 = Employee.builder()
                .id(1L)
                .username("admin@mail.ru")
                .password("123456")
                .role(Role.ADMIN)
                .build();
        Employee employee2 = Employee.builder()
                .id(2L)
                .username("ivan@mail.ru")
                .password("123456")
                .role(Role.PERFORMER)
                .build();

        List<Employee> employees = List.of(employee1, employee2);
        Pageable pageable = PageRequest.of(0, 3);
        Page<Employee> page = new PageImpl<>(employees, pageable, employees.size());

        when(employeeRepository.findAll(pageable)).thenReturn(page);

        List<EmployeeDto> expected = List.of(
                EmployeeDto.builder()
                        .id(1L)
                        .username("admin@mail.ru")
                        .role(Role.ADMIN)
                        .build(),
                EmployeeDto.builder()
                        .id(2L)
                        .username("ivan@mail.ru")
                        .role(Role.PERFORMER)
                        .build()
        );

        Assertions.assertEquals(expected, employeeService.findAll(pageable));
    }

    @Test
    public void givenValidId_whenFindById_thenReturnEmployee() {
        Long validId = 1L;

        when(employeeRepository.findById(validId)).thenReturn(Optional.of(Employee.builder()
                .id(validId)
                .username("admin@mail.ru")
                .password("123456")
                .role(Role.ADMIN)
                .build()));

        Employee expected = Employee.builder()
                .id(validId)
                .username("admin@mail.ru")
                .password("123456")
                .role(Role.ADMIN)
                .build();
        Assertions.assertEquals(expected, employeeService.findById(validId));
    }

    @Test
    public void givenNotValidId_whenFindById_thenThrowMyEntityNotFoundException() {
        Long invalidId = 1L;
        when(employeeRepository.findById(invalidId)).thenThrow(new MyEntityNotFoundException(invalidId));
        Assertions.assertThrows(MyEntityNotFoundException.class, () -> employeeService.findById(invalidId));
    }

    @Test
    public void givenValidUsername_whenCreateEmployee_thenReturnEmployee() {
        Employee employee = Employee.builder()
                .id(1L)
                .username("ivan@mail.ru")
                .password("123456")
                .role(Role.PERFORMER)
                .build();
        when(employeeRepository.save(employee)).thenReturn(employee);

        EmployeeDto expected = EmployeeDto.builder()
                .id(1L)
                .username("ivan@mail.ru")
                .role(Role.PERFORMER)
                .build();

        Assertions.assertEquals(expected, employeeService.createEmployee(EmployeeCreationDto.builder()
                .username("admin@mail.ru")
                .password("123456")
                .build()));
    }

    @Test
    public void givenNotValidUsername_whenCreateEmployee_thenThrow() {
        Employee employee = Employee.builder()
                .username("ivan@mail.ru")
                .password("123456")
                .role(Role.PERFORMER)
                .build();
        when(employeeRepository.save(employee)).thenThrow(new EntityAlreadyExistsException("EntityAlreadyExistsException"));

        Assertions.assertThrows(EntityAlreadyExistsException.class, () -> employeeService.createEmployee(EmployeeCreationDto.builder()
                .username("ivan@mail.ru")
                .password("123456")
                .build()));
    }
}