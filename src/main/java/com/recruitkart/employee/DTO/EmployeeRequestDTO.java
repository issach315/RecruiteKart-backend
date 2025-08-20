package com.recruitkart.employee.DTO;



import com.recruitkart.common.constants.EmployeeRole;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public class EmployeeRequestDTO {

    @NotBlank(message = "Employee name is required")
    private String empName;

    @NotBlank(message = "Email is required")
    @Email(message = "Please provide a valid email address")
    private String empEmailId;

    @NotBlank(message = "Mobile number is required")
    @Pattern(regexp = "^[0-9]{10}$", message = "Mobile number must be 10 digits")
    private String empMobileNo;

    @NotNull(message = "Employee role is required")
    private EmployeeRole empRole;

    @NotBlank(message = "Password is required")
    private String accountPassword;

    // Note: updatedBy can be set from security context or request headers
    private String updatedBy;

    // No-args constructor
    public EmployeeRequestDTO() {
    }

    // All-args constructor
    public EmployeeRequestDTO(String empName, String empEmailId, String empMobileNo,
                              EmployeeRole empRole, String accountPassword, String updatedBy) {
        this.empName = empName;
        this.empEmailId = empEmailId;
        this.empMobileNo = empMobileNo;
        this.empRole = empRole;
        this.accountPassword = accountPassword;
        this.updatedBy = updatedBy;
    }

    // Getters and Setters
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

    public String getAccountPassword() {
        return accountPassword;
    }

    public void setAccountPassword(String accountPassword) {
        this.accountPassword = accountPassword;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }
}
