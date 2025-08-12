package com.recruitkart.employees.exception;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.recruitkart.employees.dto.ApiResponse;
import com.recruitkart.employees.dto.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;

import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(EmployeeNotFoundException.class)
    public ResponseEntity<ApiResponse<Object>> handleEmployeeNotFound(EmployeeNotFoundException ex){
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(ApiResponse.error(ex.getMessage()));
    }

    @ExceptionHandler(EmployeeAlreadyExistsException.class)
    public ResponseEntity<ApiResponse<Object>> handleEmployeeAlreadyExists(EmployeeAlreadyExistsException ex){
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(ApiResponse.error(ex.getMessage()));
    }

    @ExceptionHandler(InvalidEmployeeDataException.class)
    public ResponseEntity<ApiResponse<Object>> handleInvalidEmployeeData(InvalidEmployeeDataException ex){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ApiResponse.error(ex.getMessage()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<Object>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        // Get the first validation error message only
        String errorMessage = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .findFirst()
                .map(error -> error.getDefaultMessage())
                .orElse("Validation failed");

        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ApiResponse.error(errorMessage));
    }



    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<Object>> handleGenericException(Exception ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(ApiResponse.error("An unexpected error occurred: " + ex.getMessage()));
    }


    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<Object> handleEnumParsingError(HttpMessageNotReadableException ex) {
        Throwable cause = ex.getCause();
        if (cause instanceof InvalidFormatException ife && ife.getTargetType().isEnum()) {
            String allowedValues = String.join(",",
                    ife.getTargetType().getEnumConstants()
                            .toString()
            );

            // Alternatively, get values with stream
            allowedValues = java.util.Arrays.stream(ife.getTargetType().getEnumConstants())
                    .map(Object::toString)
                    .collect(Collectors.joining(", "));

            ErrorResponse error = new ErrorResponse(
                    false,
                    "Invalid value for field '" + ife.getPath().get(0).getFieldName() +
                            "'. Allowed values: " + allowedValues,
                    null,
                    LocalDateTime.now()
            );
            return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
        }

        ErrorResponse error = new ErrorResponse(
                false,
                "Invalid request format",
                null,
                LocalDateTime.now()
        );
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
}
