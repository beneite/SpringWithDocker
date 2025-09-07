package com.beneite.SpringWithDocker.repository;

import com.beneite.SpringWithDocker.entity.EmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<EmployeeEntity, Long> {

    Optional<EmployeeEntity> findByCompanyEmail(String email);
    List<EmployeeEntity> findByDepartment(String departmentName);
    List<EmployeeEntity> findByBusinessUnit(String businessUnit);
    List<EmployeeEntity> findByBand(String band);
}
