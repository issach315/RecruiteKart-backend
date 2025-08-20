package com.recruitkart.common.exception.employeeExceptions;

import com.recruitkart.employee.DTO.EmployeeResponseDTO;

public class EmployeeNotFoundException extends RuntimeException {

    public EmployeeNotFoundException(String message) {
        super(message);
    }
}
