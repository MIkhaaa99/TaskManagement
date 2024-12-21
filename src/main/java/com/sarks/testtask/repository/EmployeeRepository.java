package com.sarks.testtask.repository;

import com.sarks.testtask.entity.Employee;
import com.sarks.testtask.entity.Task;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    @Query("SELECT t FROM Task t LEFT JOIN FETCH t.comments c JOIN FETCH t.admin JOIN FETCH t.performer WHERE t.admin.id = :id")
    List<Task> findAllTasksAndComments(@Param("id") Long id, Pageable pageable);

    Page<Employee> findAll(Pageable pageable);
}
