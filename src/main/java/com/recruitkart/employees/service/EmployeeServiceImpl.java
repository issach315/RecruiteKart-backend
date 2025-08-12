package com.recruitkart.employees.service;

import com.recruitkart.employees.exception.EmployeeAlreadyExistsException;
import com.recruitkart.employees.exception.EmployeeNotFoundException;
import com.recruitkart.employees.exception.InvalidEmployeeDataException;
import com.recruitkart.employees.model.Employees;
import com.recruitkart.employees.repository.EmployeesRepository;
import org.hibernate.query.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.print.attribute.standard.PageRanges;
import java.awt.print.Pageable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {


    @Autowired
    private EmployeesRepository employeeRepo;


    @Override
    public Employees createEmployee(Employees employee) {
        try {
            // Check if employee with email already exists
            if (employeeRepo.existsByEmpEmailId(employee.getEmpEmailId())) {
                throw new EmployeeAlreadyExistsException("Employee with email " + employee.getEmpEmailId() + " already exists");
            }

            // Additional business validations (Bean validation handles most of this now)
            if (employee.getEmpDateOfJoining().isBefore(LocalDate.now())) {
                // Allow today's date for joining
                if (!employee.getEmpDateOfJoining().equals(LocalDate.now())) {
                    throw new InvalidEmployeeDataException("Date of joining cannot be in the past");
                }
            }

            // Generate employee ID based on last ID
            employee.setEmpId(generateNextEmployeeId());

            return employeeRepo.save(employee);
        } catch (Exception ex) {
            if (ex instanceof EmployeeAlreadyExistsException || ex instanceof InvalidEmployeeDataException) {
                throw ex;
            }
            throw new RuntimeException("Failed to create employee: " + ex.getMessage());
        }
    }

    private String generateNextEmployeeId() {
        String currentYear = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy"));
        String baseId = "EMP" + currentYear + "-";

        // Get the last employee ID from database for the current year
        Optional<String> lastIdOpt = employeeRepo.findLastEmployeeIdForYear(currentYear);

        int nextNumber = 1; // start sequence at 1

        if (lastIdOpt.isPresent()) {
            String lastId = lastIdOpt.get();
            if (lastId.startsWith(baseId)) {
                String numericPart = lastId.substring(baseId.length());
                try {
                    int lastNumber = Integer.parseInt(numericPart);
                    nextNumber = lastNumber + 1;
                } catch (NumberFormatException e) {
                    nextNumber = 1; // fallback
                }
            }
        }

        return baseId + String.format("%04d", nextNumber); // 4-digit sequence
    }


    @Override
    public Page<Employees> getAllEmployees(int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("empId").descending());
        Page<Employees> employeesPage = employeeRepo.findAll(pageable);

        if (employeesPage.isEmpty()) {
            throw new EmployeeNotFoundException("Employees not found");
        }

        return employeesPage;
    }

}

