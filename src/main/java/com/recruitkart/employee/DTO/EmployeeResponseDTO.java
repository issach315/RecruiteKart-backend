package com.recruitkart.employee.DTO;

import com.recruitkart.common.constants.EmployeeRole;
import java.time.LocalDateTime;

public class EmployeeResponseDTO {

    private String empId;
    private String empName;
    private String empEmailId;
    private String empMobileNo;
    private EmployeeRole empRole;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String updatedBy;

    // Note: accountPassword is excluded for security reasons

    // No-args constructor
    public EmployeeResponseDTO() {
    }

    // All-args constructor (without password)
    public EmployeeResponseDTO(String empId, String empName, String empEmailId,
                               String empMobileNo, EmployeeRole empRole,
                               LocalDateTime createdAt, LocalDateTime updatedAt,
                               String updatedBy) {
        this.empId = empId;
        this.empName = empName;
        this.empEmailId = empEmailId;
        this.empMobileNo = empMobileNo;
        this.empRole = empRole;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.updatedBy = updatedBy;
    }

    // Getters and Setters
    public String getEmpId() {
        return empId;
    }

    public void setEmpId(String empId) {
        this.empId = empId;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public String getEmpEmailId() {
        return empEmailId;
    }

    public void setEmpEmailId(String empEmailId) {
        this.empEmailId = empEmailId;
    }

    public String getEmpMobileNo() {
        return empMobileNo;
    }

    public void setEmpMobileNo(String empMobileNo) {
        this.empMobileNo = empMobileNo;
    }

    public EmployeeRole getEmpRole() {
        return empRole;
    }

    public void setEmpRole(EmployeeRole empRole) {
        this.empRole = empRole;
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

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }
}
