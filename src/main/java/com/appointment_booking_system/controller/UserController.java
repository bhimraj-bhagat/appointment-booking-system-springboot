package com.appointment_booking_system.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.appointment_booking_system.entity.Appointment;
import com.appointment_booking_system.entity.Slot;
import com.appointment_booking_system.service.UserService;
@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	//VIEW AVAILABLE SLOTS
	@GetMapping("/viewAvailableSlots")
	public List<Slot> viewAvailableSlots(){
		return userService.getAvailableSlots();
	}
	
	//BOOK AN APPOINTMENT
	@PostMapping("/bookAppointment")
	public String bookAppointment(@RequestParam Long userId,@RequestParam Long slotId) {
		return userService.bookAppointment(userId, slotId);
	}
	
	//CANCEL AN APPOINTMENT
	@PostMapping("/cancelAppointment/{apptId}")
	public String cancelAppointment(@PathVariable Long apptId) {
		return userService.cancelAppointment(apptId);
	}
	//View Appointment Status
	@GetMapping("viewAppointmentStatus/{userId}")
	public List<Appointment> viewAppointmentStatus(@PathVariable Long userId){
		return userService.viewAppointmentStatus(userId);
	}
}
