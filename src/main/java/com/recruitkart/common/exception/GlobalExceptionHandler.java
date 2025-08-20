package com.recruitkart.common.exception;

import com.recruitkart.common.exception.common.ResourceNotFoundException;
import com.recruitkart.common.exception.employeeExceptions.DuplicateResourceException;
import com.recruitkart.common.exception.employeeExceptions.EmployeeNotFoundException;
import com.recruitkart.common.response.ApiResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // Handle Resource Not Found
    @ExceptionHandler({ResourceNotFoundException.class, EmployeeNotFoundException.class})
    public ResponseEntity<ApiResponse<Object>> handleNotFoundExceptions(
            RuntimeException ex,
            HttpServletRequest request
    ) {
        ApiResponse<Object> response = ApiResponse.error(
                ex.getMessage(),
                request.getRequestURI()
        );
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    // Handle Duplicate Resource
    @ExceptionHandler(DuplicateResourceException.class)
    public ResponseEntity<ApiResponse<Object>> handleDuplicateResourceException(
            DuplicateResourceException ex,
            HttpServletRequest request
    ) {
        ApiResponse<Object> response = ApiResponse.error(
                ex.getMessage(),
                request.getRequestURI()
        );
        return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
    }

    // Handle Validation Errors
    // Handle Validation Errors
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<Object>> handleValidationExceptions(
            MethodArgumentNotValidException ex,
            HttpServletRequest request
    ) {
        Map<String, String> errorMap = new HashMap<>();

        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            // ✅ Only keep the first error per field
            errorMap.putIfAbsent(fieldName, errorMessage);
        });

        // ✅ Just take the messages (no field names)
        List<String> errorMessages = errorMap.values().stream().toList();

        ApiResponse<Object> response = ApiResponse.error(
                "Validation failed",
                errorMessages,
                request.getRequestURI()
        );

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }







}
