package com.beneite.SpringWithDocker.repository;

import com.beneite.SpringWithDocker.entity.EmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<EmployeeEntity, Long> {
}
