package com.appointment_booking_system.controller;

import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.appointment_booking_system.entity.Appointment;
import com.appointment_booking_system.entity.Slot;
import com.appointment_booking_system.enums.AppointmentStatus;
import com.appointment_booking_system.service.AdminService;

@RestController
@RequestMapping("/admin")
public class AdminController {
	
	@Autowired
	private AdminService adminService;
	
	//Create Slot
	@PostMapping("/createSlot")
	public String createSlot(@RequestBody Slot slot) {
		adminService.save(slot);
		return "Slot Created...";
	}
	
	//Create more than one slot
	@PostMapping("/createSlots")
	public String createAllSlot(@RequestBody List<Slot> slots) {
		adminService.saveAll(slots);
		return "Slots Created..";		
	}
	//Approve / CANCEL Appointment
	@PutMapping("/updateStatus/{apptId}")
	public String updateStatus(@PathVariable Long apptId,@RequestParam AppointmentStatus status) {
		return adminService.updateAppointmentStatus(apptId, status);
	}
	
	//Delete slot 
	@DeleteMapping("/deleteSlot/{slotId}")
	public String deleteSlot(@PathVariable Long slotId) {
		return adminService.deleteSlot(slotId);
	}
	
	//View All Appointments
	@GetMapping("/allAppointments")
	public List<Appointment> getAllAppointment(){
		return adminService.getAllAppointment();
	}
	
	//View Reports 
	@GetMapping("/reports")
	public Map<String, Long> getReports(){
		return adminService.getReports();
	}
}
