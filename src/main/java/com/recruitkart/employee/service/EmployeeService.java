package com.recruitkart.employee.service;

import com.recruitkart.employee.model.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface EmployeeService {

    Employee onBoardNewEmployee(Employee employee);

    List<Employee> getAllEmployees();

    Page<Employee> getAllEmployeesPaginated(Pageable pageable);
}
