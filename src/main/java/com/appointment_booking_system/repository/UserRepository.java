package com.appointment_booking_system.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.appointment_booking_system.entity.User;
import com.appointment_booking_system.enums.Role;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{
	
	public Optional<User> findByEmailAndPasswordAndRole(String email, String password, Role role);
	
	public boolean existsByEmail(String email);

}
