package com.hm.hospitalmanagement.repository;

import com.hm.hospitalmanagement.entity.Insurance;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InsuranceRepository extends JpaRepository<Insurance, Long> {
}