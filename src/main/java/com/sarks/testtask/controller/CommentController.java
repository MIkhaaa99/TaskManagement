package com.sarks.testtask.controller;

import com.sarks.testtask.dto.comment.CommentCreationDto;
import com.sarks.testtask.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/comments")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @PostMapping
    public ResponseEntity<Object> createComment(@RequestBody CommentCreationDto commentCreationDto) {
        var comment = commentService.createComment(commentCreationDto);
        return new ResponseEntity<>(comment, HttpStatus.CREATED);
    }
}
