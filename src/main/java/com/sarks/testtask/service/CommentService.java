package com.sarks.testtask.service;

import com.sarks.testtask.dto.comment.CommentCreationDto;
import com.sarks.testtask.dto.comment.CreatedCommentDto;
import com.sarks.testtask.entity.Comment;
import com.sarks.testtask.entity.Employee;
import com.sarks.testtask.entity.Task;
import com.sarks.testtask.mapper.CommentCreationToCommentMapper;
import com.sarks.testtask.mapper.CommentToCreatedCommentMapper;
import com.sarks.testtask.repository.CommentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private TaskService taskService;

    public CreatedCommentDto createComment(CommentCreationDto commentCreationDto) {
        //Employee employee = employeeService.findById(commentCreationDto.employeeId());
        //Task task = taskService.findById(commentCreationDto.employeeId());
        Employee employee = employeeService.getReferenceById(commentCreationDto.employeeId());
        Task task = taskService.getReferenceById(commentCreationDto.employeeId());

        Comment commentCandidate = CommentCreationToCommentMapper.convertFrom(commentCreationDto, employee, task);
        Comment commentFromDb = commentRepository.save(commentCandidate);
        log.debug("Created comment from DB: {} ", commentFromDb);

        return CommentToCreatedCommentMapper.convertFrom(commentFromDb);
    }

}
