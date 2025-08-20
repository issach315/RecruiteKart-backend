package com.recruitkart.common.response;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.time.LocalDateTime;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL) // Skip null fields in JSON
public class ApiResponse<T> {

    private boolean success;
    private String message;
    private T data;
    private List<String> errors;
    private LocalDateTime timestamp;
    private String path;

    public ApiResponse() {
        this.timestamp = LocalDateTime.now();
    }

    // Success response with data
    public static <T> ApiResponse<T> success(T data, String message, String path) {
        ApiResponse<T> response = new ApiResponse<>();
        response.success = true;
        response.message = message;
        response.data = data;
        response.path = path;
        return response;
    }

    // Success without data
    public static <T> ApiResponse<T> success(String message, String path) {
        return success(null, message, path);
    }

    // Error response with error details
    public static <T> ApiResponse<T> error(String message, List<String> errors, String path) {
        ApiResponse<T> response = new ApiResponse<>();
        response.success = false;
        response.message = message;
        response.errors = errors;
        response.path = path;
        return response;
    }

    // Error without details
    public static <T> ApiResponse<T> error(String message, String path) {
        return error(message, null, path);
    }

    // Getters
    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }

    public T getData() {
        return data;
    }

    public List<String> getErrors() {
        return errors;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public String getPath() {
        return path;
    }
}
