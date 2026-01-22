package com.appointment_booking_system.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.appointment_booking_system.entity.Appointment;
import com.appointment_booking_system.enums.AppointmentStatus;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long>{
	public List<Appointment> findByUserId(Long userId);
	
	 Long countByAppointmentStatus(AppointmentStatus status);
	 
	 Appointment findBySlot_id(Long id);
}
