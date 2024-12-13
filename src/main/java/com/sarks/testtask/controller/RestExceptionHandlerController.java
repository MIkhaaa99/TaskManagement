package com.sarks.testtask.controller;

import com.sarks.testtask.api.ApiError;
import com.sarks.testtask.exceptions.EntityAlreadyExistsException;
import com.sarks.testtask.exceptions.MyEntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class RestExceptionHandlerController {

    @ExceptionHandler({EntityAlreadyExistsException.class})
    ResponseEntity<Object> handleEntityAlreadyExistsEx(EntityAlreadyExistsException ex) {
        ApiError apiError = new ApiError("Entity Already Exists", ex.getMessage());
        return new ResponseEntity<>(apiError, HttpStatus.METHOD_NOT_ALLOWED);
    }

    @ExceptionHandler({ MyEntityNotFoundException.class })
    protected ResponseEntity<Object> handleEntityNotFoundEx(MyEntityNotFoundException ex) {
        ApiError apiError = new ApiError("Entity Not Found Exception", ex.getMessage());
        return new ResponseEntity<>(apiError, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({MethodArgumentNotValidException.class})
    public ResponseEntity<Object> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return new ResponseEntity<>(errors, HttpStatus.UNPROCESSABLE_ENTITY);
    }

}
