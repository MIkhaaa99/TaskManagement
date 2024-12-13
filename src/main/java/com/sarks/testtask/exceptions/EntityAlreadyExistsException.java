package com.sarks.testtask.exceptions;

public class EntityAlreadyExistsException extends RuntimeException {

    public EntityAlreadyExistsException(String messageError) {
        super(messageError);
    }
}
