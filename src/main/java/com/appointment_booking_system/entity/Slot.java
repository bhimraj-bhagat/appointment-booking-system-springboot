package com.appointment_booking_system.entity;

import java.time.LocalDate;
import java.time.LocalTime;
import com.appointment_booking_system.enums.SlotStatus;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;


@Entity
@Data
@Table(name="slot_info")
public class Slot {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private LocalDate date;
	private LocalTime startTime;
	private LocalTime endTime;
	
	@Enumerated(EnumType.STRING)
	private SlotStatus slotStatus;
	
}
