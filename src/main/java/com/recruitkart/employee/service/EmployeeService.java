package com.recruitkart.employee.service;

import com.recruitkart.employee.model.Employee;
import com.recruitkart.employee.response.EmployeeResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface EmployeeService {

    Employee onBoardNewEmployee(Employee employee);

    List<Employee> getAllEmployees();

    Page<Employee> getAllEmployeesPaginated(Pageable pageable);

   void  deleteEmployeeById(String employeeId);

   Employee updateEmpById(String employeeId , Employee employee);

   EmployeeResponseDTO getEmployeeById(String employeeId);
}
