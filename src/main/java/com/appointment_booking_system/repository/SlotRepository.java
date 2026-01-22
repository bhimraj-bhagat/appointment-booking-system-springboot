package com.appointment_booking_system.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.appointment_booking_system.entity.Slot;
import com.appointment_booking_system.enums.SlotStatus;

@Repository
public interface SlotRepository extends JpaRepository<Slot, Long>{
	 List<Slot> findBySlotStatus(SlotStatus status);

}
