package com.recruitkart.employees.model;

import com.recruitkart.employees.util.Adult;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.time.LocalDate;

@Entity
@Table(name = "Employees_table")
public class Employees {

    @Id
    private String empId;

    @NotBlank(message = "employee email is required")
    @Email(message = "email should be valid")
    @Pattern(regexp = "^[A-Za-z0-9._%+-]+@recuierkart\\.com$",
            message = "email must be from recruitkart.com domain")
    private String empEmailId;

    @NotBlank(message = "employee name is required")
    @Size(min = 3, max = 20, message = "employee name must be in the 3 to 20 characters")
    private String empName;

    @Pattern(regexp = "^[0-9]{12}$", message = "mobile number must be 12 digits")
    private String empMobileNo;

    @Past(message = "Date of birth must be in the past")
    @Adult(message = "Employee must be at least 18 years old")
    private LocalDate empDateOfBirth;

    @NotNull(message = "Date of joining is required")
    @FutureOrPresent(message = "Date of joining must be today or in the future")
    private LocalDate empDateOfJoining;


    @NotNull(message = "Role is required")
    @Enumerated(EnumType.STRING)
    private EmployeeRole roleType;

    @NotBlank(message = "Password is required")
    @Size(min = 8, max = 20, message = "Password must be between 8 and 20 characters")
    @Pattern(
            regexp = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]+$",
            message = "Password must contain at least one uppercase letter, one lowercase letter, one number, and one special character"
    )
    private String password;

    public Employees() {
    }

    public Employees(String empId, String empEmailId, String empName, String empMobileNo, LocalDate empDateOfBirth, LocalDate empDateOfJoining, EmployeeRole roleType, String password) {
        this.empId = empId;
        this.empEmailId = empEmailId;
        this.empName = empName;
        this.empMobileNo = empMobileNo;
        this.empDateOfBirth = empDateOfBirth;
        this.empDateOfJoining = empDateOfJoining;
        this.roleType = roleType;
        this.password = password;
    }


    public String getEmpId() {
        return empId;
    }

    public void setEmpId(String empId) {
        this.empId = empId;
    }

    public String getEmpEmailId() {
        return empEmailId;
    }

    public void setEmpEmailId(String empEmailId) {
        this.empEmailId = empEmailId;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public String getEmpMobileNo() {
        return empMobileNo;
    }

    public void setEmpMobileNo(String empMobileNo) {
        this.empMobileNo = empMobileNo;
    }

    public LocalDate getEmpDateOfBirth() {
        return empDateOfBirth;
    }

    public void setEmpDateOfBirth(LocalDate empDateOfBirth) {
        this.empDateOfBirth = empDateOfBirth;
    }

    public LocalDate getEmpDateOfJoining() {
        return empDateOfJoining;
    }

    public void setEmpDateOfJoining(LocalDate empDateOfJoining) {
        this.empDateOfJoining = empDateOfJoining;
    }

    public EmployeeRole getRoleType() {
        return roleType;
    }

    public void setRoleType(EmployeeRole roleType) {
        this.roleType = roleType;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }



}
