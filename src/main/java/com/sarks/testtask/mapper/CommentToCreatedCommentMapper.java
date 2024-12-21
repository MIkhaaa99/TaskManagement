package com.sarks.testtask.mapper;

import com.sarks.testtask.dto.comment.CreatedCommentDto;
import com.sarks.testtask.dto.employee.EmployeeDto;
import com.sarks.testtask.entity.Comment;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CommentToCreatedCommentMapper {

    public static CreatedCommentDto convertFrom(Comment comment) {
        CreatedCommentDto createdCommentDto = CreatedCommentDto.builder()
                .id(comment.getId())
                .text(comment.getText())
                .employee(new EmployeeDto(comment.getEmployee().getId(), comment.getEmployee().getUsername(), comment.getEmployee().getRole()))
                .taskId(comment.getTask().getId())
                .build();
        log.debug("Convert to createdCommentDto: {} ", createdCommentDto);
        return createdCommentDto;
    }
}
