package com.sarks.testtask.exceptions;

import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MyEntityNotFoundException extends EntityNotFoundException {
    public MyEntityNotFoundException() {
        super("Entity is not found");
        log.info("Entity not found");
    }

    public MyEntityNotFoundException(Long id) {
        super("Entity not found with id=" + id);
        log.info("Entity not found with id=" + id);
    }
}
