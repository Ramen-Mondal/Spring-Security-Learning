package com.hm.hospitalmanagement.repository;

import com.hm.hospitalmanagement.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<Department, Long> {
}