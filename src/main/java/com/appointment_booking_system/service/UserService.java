package com.appointment_booking_system.service;

import java.time.LocalDateTime;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import com.appointment_booking_system.entity.Appointment;
import com.appointment_booking_system.entity.Slot;
import com.appointment_booking_system.entity.User;
import com.appointment_booking_system.enums.AppointmentStatus;
import com.appointment_booking_system.enums.SlotStatus;
import com.appointment_booking_system.exception.SlotNotFoundException;
import com.appointment_booking_system.exception.UserNotFoundException;
import com.appointment_booking_system.repository.AppointmentRepository;
import com.appointment_booking_system.repository.SlotRepository;
import com.appointment_booking_system.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private SlotRepository slotRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	AppointmentRepository appointmentRepository;
	
	@Autowired
	private JavaMailSender mailSender;
	
	//VIEW AVAILABLE SLOTS
	public List<Slot> getAvailableSlots(){
		return slotRepository.findBySlotStatus(SlotStatus.AVAILABLE);
	}
	
	//BOOK AN APPOINTMENT
	public String bookAppointment(Long userId, Long slotId) {
		Slot slot = slotRepository.findById(slotId)
				.orElseThrow(()->new SlotNotFoundException());
		
		if(slot.getSlotStatus()==SlotStatus.BOOKED) {//for check if slot is booked
			return "Slot already booked";
		}
		
		User user = userRepository.findById(userId)
				.orElseThrow(()->new UserNotFoundException());
		
		Appointment appt = new Appointment();
		appt.setUser(user);
		appt.setSlot(slot);
		appt.setCreatedAt(LocalDateTime.now());
		appt.setAppointmentStatus(AppointmentStatus.PENDING);
		appointmentRepository.save(appt);
		
		//SEND MAIL
		SimpleMailMessage mail = new SimpleMailMessage();
		mail.setTo(user.getEmail());
		mail.setSubject("Appointment Booked Successfully");
		mail.setText("Hello " + user.getName() + ",\n\n"
	            + "Your appointment has been booked successfully.\n"
	            + "Date: " + slot.getDate() + "\n"
	            + "Time: " + slot.getStartTime() + " - " + slot.getEndTime() + "\n"
	            + "Status: PENDING\n\n"
	            + "Thank you!");
		mailSender.send(mail);
		
		return "Appointment booked (Pending approval)";
	}
	
	//CANCEL AN APPOINTMENT
	public String cancelAppointment(Long appointmentId) {
		Appointment appt = appointmentRepository.findById(appointmentId).orElseThrow(()->new RuntimeException());
		
		if(appt.getAppointmentStatus() == AppointmentStatus.APPROVED) {
			return "Approved appointment cannot be cancelled";
		}
		
		appt.setAppointmentStatus(AppointmentStatus.CANCELLED);
		appointmentRepository.save(appt);
		
		return "Appointment cancelled successfully";
	}
	
	//VIEW APPOINTMENT STATUS
	public List<Appointment> viewAppointmentStatus(Long userId) {
	    return appointmentRepository.findByUserId(userId);
	}
	
	
	
	
	
	
	
	
	
	
	
}
