package com.recruitkart.employees.exception;

import javax.management.InvalidAttributeValueException;

public class InvalidEmployeeDataException extends RuntimeException {

    public InvalidEmployeeDataException(String message) {
        super(message);
    }
}
