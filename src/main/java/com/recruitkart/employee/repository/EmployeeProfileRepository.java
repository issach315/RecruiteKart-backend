package com.recruitkart.employee.repository;

import com.recruitkart.employee.model.EmployeeProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.ResponseBody;

@Repository
public interface EmployeeProfileRepository extends JpaRepository<EmployeeProfile, String> {


}
