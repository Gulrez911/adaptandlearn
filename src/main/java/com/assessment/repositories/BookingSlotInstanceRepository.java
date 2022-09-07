package com.assessment.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.assessment.data.BookingSlotInstance;

public interface BookingSlotInstanceRepository extends JpaRepository<BookingSlotInstance, Long> {

	
	@Query(value="SELECT q FROM BookingSlotInstance q WHERE q.companyId=:companyId and q.email=:email and q.slot.id=:sid")
	public BookingSlotInstance findUniqueBookingSlotInstance(@Param("companyId") String companyId, @Param("email") String email, @Param("sid") Long sid);
	
	@Query(value="SELECT q FROM BookingSlotInstance q WHERE q.companyId=:companyId and q.slot.id=:sid")
	public List<BookingSlotInstance> getBookingSlotInstancesForSlot(@Param("companyId") String companyId, @Param("sid") Long sid);
	
	@Query(value="SELECT count(q) FROM BookingSlotInstance q WHERE q.companyId=:companyId and q.slot.id=:sid")
	public Integer getCountOfParticipantsForSlot(@Param("companyId") String companyId, @Param("sid") Long sid);

	
	@Query(value="SELECT q FROM BookingSlotInstance q WHERE q.companyId=:companyId and q.email=:email")
	public List<BookingSlotInstance> getBookingSlotInstancesForUser(@Param("companyId") String companyId, @Param("email") String email);
	
}
