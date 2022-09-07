package com.assessment.services;

import java.util.Date;
import java.util.List;

import com.assessment.data.BookingSlot;

public interface BookingSlotService {

	
	public BookingSlot findUniqueBookingSlot(String companyId, Date start,  Date end);
	
	public List<BookingSlot> getBookingSlotsForDay(String companyId,  String day, String month, String year);
	
	public List<BookingSlot> getAllBookingSlotsForDay_Filled_Unfilled(String companyId,  String day, String month, String year);

	public List<BookingSlot> findAllBookingSlots(String companyId);
	
	public BookingSlot saveOrUpdate(BookingSlot bookingSlot);
	
	public boolean disable(BookingSlot bookingSlot);
	
	public boolean enable(BookingSlot bookingSlot) ;
}
