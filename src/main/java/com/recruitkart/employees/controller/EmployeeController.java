package com.recruitkart.employees.controller;

import com.recruitkart.employees.dto.ApiResponse;
import com.recruitkart.employees.model.Employees;
import com.recruitkart.employees.service.EmployeeService;
import com.recruitkart.employees.service.EmployeeServiceImpl;
import jakarta.validation.Valid;
import org.hibernate.query.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    private EmployeeServiceImpl employeeService;

    @PostMapping
    public ResponseEntity<ApiResponse<Employees>> createEmployee(@Valid @RequestBody Employees employee) {
        Employees createdEmployee = employeeService.createEmployee(employee);
        return ResponseEntity.ok(ApiResponse.success("Employee created successfully", createdEmployee));
    }
    @GetMapping("/getAllEmployees")
    public ResponseEntity<ApiResponse<Page<Employees>>> getAllEmployees(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        Page<Employees> employeesPage = employeeService.getAllEmployees(page, size);
        return ResponseEntity.ok(
                ApiResponse.success("Employees fetched successfully", employeesPage)
        );
    }
}
