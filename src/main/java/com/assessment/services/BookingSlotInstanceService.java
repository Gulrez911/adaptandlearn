package com.assessment.services;

import java.util.List;

import com.assessment.data.BookingSlotInstance;

public interface BookingSlotInstanceService {
	
	public BookingSlotInstance findUniqueBookingSlotInstance( String companyId, String email,  Long sid);
	
	public List<BookingSlotInstance> getBookingSlotInstancesForSlot(String companyId, Long sid);
	
	public Integer getCountOfParticipantsForSlot( String companyId, Long sid);
	
	public BookingSlotInstance saveOrUpdate(BookingSlotInstance bookingSlotInstance);


}
