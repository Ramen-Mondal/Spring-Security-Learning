package com.hm.hospitalmanagement.repository;

import com.hm.hospitalmanagement.entity.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
}