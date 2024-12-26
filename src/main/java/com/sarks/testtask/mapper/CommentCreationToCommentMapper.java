package com.sarks.testtask.mapper;

import com.sarks.testtask.dto.comment.CommentCreationDto;
import com.sarks.testtask.entity.Comment;
import com.sarks.testtask.entity.Employee;
import com.sarks.testtask.entity.Task;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CommentCreationToCommentMapper {

    public static Comment convertFrom(CommentCreationDto commentCreationDto, Employee employee, Task task) {
        Comment comment = Comment.builder()
                .text(commentCreationDto.text())
                .employee(employee)
                .task(task)
                .build();
        log.debug("Convert to Comment: {} ", comment);
        return comment;
    }
}
