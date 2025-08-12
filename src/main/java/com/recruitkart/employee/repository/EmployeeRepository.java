package com.recruitkart.employee.repository;

import com.recruitkart.employee.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee,String> {

    @Query("SELECT e.employeeId FROM Employee e ORDER BY e.employeeId DESC LIMIT 1")
    String findLastEmployeeId();

}
