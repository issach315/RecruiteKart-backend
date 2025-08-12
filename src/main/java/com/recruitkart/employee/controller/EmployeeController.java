package com.recruitkart.employee.controller;

import com.recruitkart.employee.response.EmployeeResponseDTO;
import com.recruitkart.employee.response.OnBoardEmployeeResponseDTO;
import com.recruitkart.employee.response.PaginatedEmployeeResponseDTO;
import com.recruitkart.employee.model.Employee;
import com.recruitkart.employee.response.ApiResponse;
import com.recruitkart.employee.service.EmployeeService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @PostMapping("/onBoardNewEmployee")
    public ResponseEntity<ApiResponse<OnBoardEmployeeResponseDTO>> onBoardNewEmployee(
            @RequestBody Employee employee, HttpServletRequest request) {

        Employee savedEmployee = employeeService.onBoardNewEmployee(employee);

        OnBoardEmployeeResponseDTO responseDTO = new OnBoardEmployeeResponseDTO(
                savedEmployee.getEmployeeId(),
                savedEmployee.getEmployeeName()
        );

        return ResponseEntity.status(HttpStatus.CREATED).body(
                new ApiResponse<>(
                        true,
                        "Employee created successfully",
                        responseDTO,
                        request.getRequestURI()
                )
        );
    }

    // Original method - kept for backward compatibility
    @GetMapping("/getAllEmployees")
    public ResponseEntity<ApiResponse<List<EmployeeResponseDTO>>> getAllEmployees(HttpServletRequest request) {

        List<EmployeeResponseDTO> employees = employeeService.getAllEmployees()
                .stream()
                .map(e -> new EmployeeResponseDTO(
                        e.getEmployeeId(),
                        e.getEmployeeName(),
                        e.getEmployeeEmailId(),
                        e.getEmployeeMobileNo(),
                        e.getEmployeeDateOfBirth(),
                        e.getEmployeeRole(),
                        e.getCreatedAt(),
                        e.getUpdatedAt()
                ))
                .collect(Collectors.toList());

        return ResponseEntity.ok(
                new ApiResponse<>(
                        true,
                        "Employees fetched successfully",
                        employees,
                        request.getRequestURI()
                )
        );
    }

    // New paginated endpoint
    @GetMapping("/getAllEmployeesPaginated")
    public ResponseEntity<ApiResponse<PaginatedEmployeeResponseDTO>> getAllEmployeesPaginated(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "employeeId") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDirection,
            HttpServletRequest request) {

        // Create sort object
        Sort sort = sortDirection.equalsIgnoreCase("desc")
                ? Sort.by(sortBy).descending()
                : Sort.by(sortBy).ascending();

        // Create pageable object
        Pageable pageable = PageRequest.of(page, size, sort);

        // Get paginated data from service
        Page<Employee> employeePage = employeeService.getAllEmployeesPaginated(pageable);

        // Convert to DTOs
        List<EmployeeResponseDTO> employeeDTOs = employeePage.getContent()
                .stream()
                .map(e -> new EmployeeResponseDTO(
                        e.getEmployeeId(),
                        e.getEmployeeName(),
                        e.getEmployeeEmailId(),
                        e.getEmployeeMobileNo(),
                        e.getEmployeeDateOfBirth(),
                        e.getEmployeeRole(),
                        e.getCreatedAt(),
                        e.getUpdatedAt()
                ))
                .collect(Collectors.toList());

        // Create paginated response DTO
        PaginatedEmployeeResponseDTO paginatedResponse = new PaginatedEmployeeResponseDTO(
                employeeDTOs,
                employeePage.getNumber(),
                employeePage.getSize(),
                employeePage.getTotalElements(),
                employeePage.getTotalPages(),
                employeePage.isFirst(),
                employeePage.isLast(),
                employeePage.hasNext(),
                employeePage.hasPrevious()
        );

        return ResponseEntity.ok(
                new ApiResponse<>(
                        true,
                        "Employees fetched successfully with pagination",
                        paginatedResponse,
                        request.getRequestURI()
                )
        );
    }
}