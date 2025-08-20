package com.recruitkart.employee.repository;

import com.recruitkart.employee.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee,String> {

    Employee findTopByOrderByEmpIdDesc();

    Optional<Employee> findByEmpEmailId(String email);
}
