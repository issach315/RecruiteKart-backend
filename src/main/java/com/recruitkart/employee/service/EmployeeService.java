package com.recruitkart.employee.service;


import com.recruitkart.common.response.PagedResponse;
import com.recruitkart.employee.DTO.EmployeeRequestDTO;
import com.recruitkart.employee.DTO.EmployeeResponseDTO;
import com.recruitkart.employee.model.Employee;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

public interface EmployeeService {

   EmployeeResponseDTO registerEmployee(EmployeeRequestDTO requestDto);

   PagedResponse<EmployeeResponseDTO> findAllEmployees(int page , int size);

   EmployeeResponseDTO findEmployeeById(String empId);
}
