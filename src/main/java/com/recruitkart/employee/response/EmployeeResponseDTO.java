package com.recruitkart.employee.response;

import java.time.LocalDate;
import java.time.LocalDateTime;


public class EmployeeResponseDTO {

    private String employeeId;
    private String employeeName;
    private String employeeEmailId;
    private String employeeMobileNo;
    private LocalDate employeeDateOfBirth;
    private String employeeRole;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public EmployeeResponseDTO(String employeeId, String employeeName, String employeeEmailId, String employeeMobileNo, LocalDate employeeDateOfBirth, String employeeRole, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.employeeId = employeeId;
        this.employeeName = employeeName;
        this.employeeEmailId = employeeEmailId;
        this.employeeMobileNo = employeeMobileNo;
        this.employeeDateOfBirth = employeeDateOfBirth;
        this.employeeRole = employeeRole;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getEmployeeEmailId() {
        return employeeEmailId;
    }

    public void setEmployeeEmailId(String employeeEmailId) {
        this.employeeEmailId = employeeEmailId;
    }

    public String getEmployeeMobileNo() {
        return employeeMobileNo;
    }

    public void setEmployeeMobileNo(String employeeMobileNo) {
        this.employeeMobileNo = employeeMobileNo;
    }

    public LocalDate getEmployeeDateOfBirth() {
        return employeeDateOfBirth;
    }

    public void setEmployeeDateOfBirth(LocalDate employeeDateOfBirth) {
        this.employeeDateOfBirth = employeeDateOfBirth;
    }

    public String getEmployeeRole() {
        return employeeRole;
    }

    public void setEmployeeRole(String employeeRole) {
        this.employeeRole = employeeRole;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}
