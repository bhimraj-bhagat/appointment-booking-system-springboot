package com.appointment_booking_system.exception;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ApplicationExceptionHandler{
	
	@ExceptionHandler(SlotNotFoundException.class)
	public String handleSlotNotFoundException(SlotNotFoundException snf) {
		return "Slot Not Found";
	}
	
	@ExceptionHandler(UserNotFoundException.class)
	public String handleUserNotFoundException(UserNotFoundException unf) {
		return "User Not Found";
	}
	
	@ExceptionHandler(AppointmentNotFoundException.class)
	public String handleAppointmentNotFoundException(AppointmentNotFoundException anf) {
		return "Appointment Not Found";
	}
	
	@ExceptionHandler(Exception.class)
	public String handleGeneralException(Exception e) {
		return "Something went wrong";
	}
	
}
