package com.appointment_booking_system.entity;

import java.time.LocalDateTime;

import com.appointment_booking_system.enums.AppointmentStatus;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "appointment_info")
public class Appointment {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	private User user;
	
	@OneToOne
	private Slot slot;
	
	@Enumerated(EnumType.STRING)
	private AppointmentStatus appointmentStatus;
	
	private LocalDateTime createdAt;
}
