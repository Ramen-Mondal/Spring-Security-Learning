package com.hm.hospitalmanagement.repository;

import com.hm.hospitalmanagement.entity.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DoctorRepository extends JpaRepository<Doctor, Long> {
}