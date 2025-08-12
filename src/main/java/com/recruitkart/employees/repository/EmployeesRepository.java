package com.recruitkart.employees.repository;

import com.recruitkart.employees.model.Employees;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.awt.print.Pageable;
import java.util.Optional;

@Repository
public interface EmployeesRepository extends JpaRepository<Employees, String> {
    Optional<Employees> findByEmpEmailId(String empEmailId);
    boolean existsByEmpEmailId(String empEmailId);


    // Find the employee with the highest empId (latest created)
    Optional<Employees> findTopByOrderByEmpIdDesc();

    // Alternative: Custom query to get the last employee ID
    @Query("SELECT e.empId FROM Employees e ORDER BY e.empId DESC LIMIT 1")
    Optional<String> findLastEmployeeId();

    @Query("SELECT e.empId FROM Employees e WHERE e.empId LIKE CONCAT('EMP', :year, '-%') ORDER BY e.empId DESC LIMIT 1")
    Optional<String> findLastEmployeeIdForYear(@Param("year") String year);


}
