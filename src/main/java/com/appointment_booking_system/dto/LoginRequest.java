package com.appointment_booking_system.dto;

import com.appointment_booking_system.enums.Role;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginRequest {
	private String email;
	private String password;
	private Role role;
}
