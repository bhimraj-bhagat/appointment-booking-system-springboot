package com.appointment_booking_system.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.appointment_booking_system.dto.LoginRequest;
import com.appointment_booking_system.entity.User;
import com.appointment_booking_system.enums.Role;
import com.appointment_booking_system.repository.UserRepository;

@Service
public class AuthService {
	
	@Autowired
	private UserRepository userRepository;
	
	
	public String register(User user) {
		boolean res = userRepository.existsByEmail(user.getEmail());
		if(res==true) {
			return "You have already register";
		}
		if(user.getRole()==null) {
			user.setRole(Role.USER);
		}
		userRepository.save(user);
		return "User registered successfully";
	}
	
	public String login(LoginRequest req) {
		Optional<User> user = userRepository.findByEmailAndPasswordAndRole(req.getEmail(), req.getPassword(), req.getRole());
		if (user.isPresent()){ 
			return req.getRole() + " Login Successfully";
		} 
		return "Invalid Email, Password, or Role"; 
	}
}
