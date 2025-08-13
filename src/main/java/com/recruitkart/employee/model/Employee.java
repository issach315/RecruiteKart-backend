package com.recruitkart.employee.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "employee")
public class Employee {

    @Id
    private String employeeId;

    @NotNull(message = "Employee name is required")
    @Size(min = 3, max = 20, message = "Employee name must be between 3 and 20 characters")
    private String employeeName;

    @NotNull(message = "Employee email is required")
    @Email(message = "Employee email must be in a valid format")
    private String employeeEmailId;

    @NotNull(message = "Mobile number is required")
    @Pattern(regexp = "^[6-9]\\d{9}$", message = "Mobile number must be a valid 10-digit Indian number starting with 6-9")
    private String employeeMobileNo;

    @NotNull(message = "Date of birth is required")
    @Past(message = "Date of birth must be in the past")
    private LocalDate employeeDateOfBirth;

    @NotNull(message = "Role is required")
    @Size(min = 3, max = 30, message = "Role must be between 3 and 30 characters")
    private String employeeRole;

    @NotNull(message = "Password is required")
    private String password;

    @Column(updatable = false, columnDefinition = "DATETIME")
    private LocalDateTime createdAt;

    @Column(columnDefinition = "DATETIME")
    private LocalDateTime updatedAt;


    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }




    public Employee() {
    }

    public Employee(String employeeId, String employeeName, String employeeEmailId,
                    String employeeMobileNo, LocalDate employeeDateOfBirth,
                    String employeeRole, String password) {
        this.employeeId = employeeId;
        this.employeeName = employeeName;
        this.employeeEmailId = employeeEmailId;
        this.employeeMobileNo = employeeMobileNo;
        this.employeeDateOfBirth = employeeDateOfBirth;
        this.employeeRole = employeeRole;
        this.password = password;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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
