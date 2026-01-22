package com.appointment_booking_system.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.appointment_booking_system.entity.Appointment;
import com.appointment_booking_system.entity.Slot;
import com.appointment_booking_system.enums.AppointmentStatus;
import com.appointment_booking_system.enums.SlotStatus;
import com.appointment_booking_system.exception.AppointmentNotFoundException;
import com.appointment_booking_system.exception.SlotNotFoundException;
import com.appointment_booking_system.repository.AppointmentRepository;
import com.appointment_booking_system.repository.SlotRepository;

@Service
public class AdminService {
	
	@Autowired
	private SlotRepository slotRepository;
	
	@Autowired
	private AppointmentRepository appointmentRepository;
	
	@Autowired
	private JavaMailSender mailSender;
	
	public String save(Slot slot) {
		slot.setSlotStatus(SlotStatus.AVAILABLE);
		slotRepository.save(slot);
		return "Slot Created..";
	}
	
	public String saveAll(List<Slot> slots) {
		slots.forEach(i->i.setSlotStatus(SlotStatus.AVAILABLE));
		slotRepository.saveAll(slots);
		return "Slots Created..";
	}
	
	//Approve / Reject Appointment
	public String updateAppointmentStatus(Long appointmentId, AppointmentStatus status) {
		Appointment appt = appointmentRepository.findById(appointmentId).orElseThrow(()->new AppointmentNotFoundException());
		appt.setAppointmentStatus(status);
		
		if(status==AppointmentStatus.APPROVED) {
			Slot slot = appt.getSlot();
			slot.setSlotStatus(SlotStatus.BOOKED);
			slotRepository.save(slot);
			
			SimpleMailMessage smm = new SimpleMailMessage();
			smm.setTo(appt.getUser().getEmail());
			smm.setSubject("Appointment Approved");
			smm.setText("Your appointment has been approved.");
			mailSender.send(smm);
		}
		appointmentRepository.save(appt);
		
		return "Appointment "+status;
	}
	
	//for delete slot
	public String deleteSlot(Long slotId) {
		Slot slot = slotRepository.findById(slotId).orElseThrow(()->new SlotNotFoundException());
		slotRepository.delete(slot);
		return "Slot deleted...";
	}
	
	// View All Appointments
	public List<Appointment> getAllAppointment(){
		return appointmentRepository.findAll();
	}
	
	//Reports 
    public Map<String, Long> getReports() {

        Map<String, Long> report = new HashMap<String, Long>();
        report.put("TOTAL", appointmentRepository.count());
        report.put("APPROVED", appointmentRepository.countByAppointmentStatus(AppointmentStatus.APPROVED));
        report.put("PENDING",appointmentRepository.countByAppointmentStatus(AppointmentStatus.PENDING));
        report.put("CANCELLED", appointmentRepository.countByAppointmentStatus(AppointmentStatus.CANCELLED));
        return report;
    }
}
