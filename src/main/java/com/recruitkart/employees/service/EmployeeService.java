package com.recruitkart.employees.service;

import com.recruitkart.employees.model.Employees;
import org.hibernate.query.Page;

import java.util.List;

public interface EmployeeService {
    Employees createEmployee(Employees employee);

    Page<Employees> getAllEmployees(int page , int size);

}
