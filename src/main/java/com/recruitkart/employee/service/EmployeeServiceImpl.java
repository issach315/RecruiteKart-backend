package com.recruitkart.employee.service;

import com.recruitkart.employee.model.Employee;
import com.recruitkart.employee.repository.EmployeeRepository;
import com.recruitkart.employee.util.EmployeeIdGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService{

    @Autowired
    private EmployeeRepository employeeRepo;

    @Override
    public Employee onBoardNewEmployee(Employee employee) {

        String lastId = employeeRepo.findLastEmployeeId();
        String newId = EmployeeIdGenerator.generateNextEmployeeCode(lastId);

        employee.setEmployeeId(newId);
      return employeeRepo.save(employee);
    }

    @Override
    public List<Employee> getAllEmployees() {
        return employeeRepo.findAll();
    }


    @Override
    public Page<Employee> getAllEmployeesPaginated(Pageable pageable){
        return employeeRepo.findAll(pageable);
    }


}
