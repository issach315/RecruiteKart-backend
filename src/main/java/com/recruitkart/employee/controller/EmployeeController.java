package com.recruitkart.employee.controller;

import com.recruitkart.common.response.ApiResponse;
import com.recruitkart.common.response.PagedResponse;
import com.recruitkart.employee.DTO.EmployeeRequestDTO;
import com.recruitkart.employee.DTO.EmployeeResponseDTO;
import com.recruitkart.employee.model.Employee;
import com.recruitkart.employee.service.EmployeeService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.Option;
import java.util.Optional;

@RestController
public class EmployeeController {


    @Autowired
    private EmployeeService employeeService;


    @PostMapping("/registerEmployee")
    public ResponseEntity<ApiResponse<EmployeeResponseDTO>> registerEmployee(@RequestBody @Valid EmployeeRequestDTO employeeRequestDto, HttpServletRequest request) {

        EmployeeResponseDTO responseDto = employeeService.registerEmployee(employeeRequestDto);

        ApiResponse<EmployeeResponseDTO> response =
                ApiResponse.success(responseDto, "Employee registered successfully", request.getRequestURI());

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/findAllEmployees")
    public ResponseEntity<PagedResponse<EmployeeResponseDTO>> getAllEmployees(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {

        return ResponseEntity.ok(employeeService.findAllEmployees(page, size));
    }

    @GetMapping("/findByEmpId/{empId}")
    public ResponseEntity<ApiResponse<EmployeeResponseDTO>> getEmployeeById(
            @PathVariable String empId,
            HttpServletRequest request) {

        EmployeeResponseDTO employeeDto = employeeService.findEmployeeById(empId);

        ApiResponse<EmployeeResponseDTO> response = ApiResponse.success(
                employeeDto,
                "Employee fetched successfully",
                request.getRequestURI()
        );
        return ResponseEntity.ok(response);
    }



}
