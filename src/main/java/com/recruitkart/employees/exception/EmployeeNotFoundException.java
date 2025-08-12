package com.recruitkart.employees.exception;

public class EmployeeNotFoundException extends RuntimeException {

    public EmployeeNotFoundException(String message){
        super(message);
    }
}
