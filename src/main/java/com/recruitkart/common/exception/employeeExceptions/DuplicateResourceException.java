package com.recruitkart.common.exception.employeeExceptions;

public class DuplicateResourceException extends RuntimeException {

    public DuplicateResourceException(String message){
        super(message);
    }
}
