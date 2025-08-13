package com.recruitkart.employee.service;

import com.recruitkart.employee.exception.EmployeeNotFoundException;
import com.recruitkart.employee.model.Employee;
import com.recruitkart.employee.repository.EmployeeRepository;
import com.recruitkart.employee.response.EmployeeResponseDTO;
import com.recruitkart.employee.util.EmployeeIdGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService{

    @Autowired
    private EmployeeRepository employeeRepo;


    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Employee onBoardNewEmployee(Employee employee) {

        String lastId = employeeRepo.findLastEmployeeId();
        String newId = EmployeeIdGenerator.generateNextEmployeeCode(lastId);

        employee.setEmployeeId(newId);
        employee.setPassword(passwordEncoder.encode(employee.getPassword()));
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

    @Override
    public void deleteEmployeeById(String employeeId) {

        if (!employeeRepo.existsById(employeeId)){
                throw new EmployeeNotFoundException("Employee with ID "+employeeId+" Not Found...!");

        }
           employeeRepo.deleteById(employeeId);
    }

    @Override
    public Employee updateEmpById(String employeeId ,Employee employee) {

        Employee existedEmp = employeeRepo.findById(employeeId)
                .orElseThrow(()-> new EmployeeNotFoundException("Employee not found with ID "+employeeId));

        existedEmp.setEmployeeEmailId(employee.getEmployeeEmailId());
        existedEmp.setEmployeeName(employee.getEmployeeName());
        existedEmp.setEmployeeDateOfBirth(employee.getEmployeeDateOfBirth());
        existedEmp.setEmployeeMobileNo(employee.getEmployeeMobileNo());
        existedEmp.setEmployeeRole(employee.getEmployeeRole());

        return employeeRepo.save(existedEmp);
    }

    @Override
    public EmployeeResponseDTO getEmployeeById(String employeeId) {
        Employee employee =  employeeRepo.findById(employeeId)
                .orElseThrow(()->  new EmployeeNotFoundException("Employee not found "+employeeId));
        return convertToEMployeeDTO(employee);

    }


    public EmployeeResponseDTO convertToEMployeeDTO(Employee employee){
        EmployeeResponseDTO empDTO = new EmployeeResponseDTO();

        empDTO.setEmployeeId(employee.getEmployeeId());
        empDTO.setEmployeeName(employee.getEmployeeName());
        empDTO.setEmployeeRole(employee.getEmployeeRole());
        empDTO.setEmployeeDateOfBirth(employee.getEmployeeDateOfBirth());
        empDTO.setEmployeeMobileNo(employee.getEmployeeMobileNo());
        empDTO.setCreatedAt(employee.getCreatedAt());
        empDTO.setUpdatedAt(employee.getUpdatedAt());

        return empDTO;
    }


}
