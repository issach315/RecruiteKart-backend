package com.recruitkart.employee.service;

import com.recruitkart.common.exception.employeeExceptions.DuplicateResourceException;
import com.recruitkart.common.exception.employeeExceptions.EmployeeNotFoundException;
import com.recruitkart.common.response.PagedResponse;
import com.recruitkart.common.util.EmployeeIdGenerator;
import com.recruitkart.employee.DTO.EmployeeRequestDTO;
import com.recruitkart.employee.DTO.EmployeeResponseDTO;
import com.recruitkart.employee.model.Employee;
import com.recruitkart.employee.repository.EmployeeRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepo;

    @Autowired
    private EmployeeIdGenerator employeeIdGenerator;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public EmployeeResponseDTO registerEmployee(EmployeeRequestDTO requestDto) {
        // Check if email already exists
        if (employeeRepo.findByEmpEmailId(requestDto.getEmpEmailId()).isPresent()) {
            throw new DuplicateResourceException(
                    "Employee with email '" + requestDto.getEmpEmailId() + "' already exists"
            );
        }

        // Generate employee ID
        Employee lastEmployee = employeeRepo.findTopByOrderByEmpIdDesc();
        String lastEmpId = (lastEmployee != null) ? lastEmployee.getEmpId() : null;
        String nextEmpId = employeeIdGenerator.generateNextId(lastEmpId);

        // Map requestDto → Employee
        Employee employee = modelMapper.map(requestDto, Employee.class);

        // Set employee ID
        employee.setEmpId(nextEmpId);

        // Encode password
        employee.setAccountPassword(passwordEncoder.encode(requestDto.getAccountPassword()));

        // Set updatedBy (fallback to SYSTEM if not provided)
        employee.setUpdatedBy(
                requestDto.getUpdatedBy() != null ? requestDto.getUpdatedBy() : "SYSTEM"
        );

        // Save employee
        Employee savedEmployee = employeeRepo.save(employee);

        // Map back to Response DTO
        return modelMapper.map(savedEmployee, EmployeeResponseDTO.class);
    }

    @Override
    public PagedResponse<EmployeeResponseDTO> findAllEmployees(int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("empId").ascending());

        Page<Employee> employeePage = employeeRepo.findAll(pageable);

        Page<EmployeeResponseDTO> dtoPage = employeePage.map(employee -> modelMapper.map(employee, EmployeeResponseDTO.class));

        return PagedResponse.of(dtoPage);
    }

    @Override
    public EmployeeResponseDTO findEmployeeById(String empId) {
       return employeeRepo.findById(empId)
               .map(employee -> modelMapper.map(employee,EmployeeResponseDTO.class))
               .orElseThrow(()-> new EmployeeNotFoundException("Employee with ID '" + empId + "' not found"));

    }


}
