package com.assessment.services.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.assessment.data.BookingSlot;
import com.assessment.repositories.BookingSlotInstanceRepository;
import com.assessment.repositories.BookingSlotRepository;
import com.assessment.services.BookingSlotService;
@Service
@Transactional
public class BookingSlotServiceImpl implements BookingSlotService {
	@Autowired
	BookingSlotRepository rep;

	@Autowired
	BookingSlotInstanceRepository instanceRep;
	
	@Override
	public BookingSlot findUniqueBookingSlot(String companyId, Date start, Date end) {
		// TODO Auto-generated method stub
		return rep.findUniqueBookingSlot(companyId, start, end);
	}

	

	@Override
	public List<BookingSlot> findAllBookingSlots(String companyId) {
		// TODO Auto-generated method stub
		List<BookingSlot> list =  rep.findAllBookingSlots(companyId);
		for(BookingSlot slot : list){
			Integer count = getCountOfParticipants(slot);
			slot.setCapacityFilledSoFar(count);
		}
		return list;
	}

	@Override
	public BookingSlot saveOrUpdate(BookingSlot bookingSlot) {
		// TODO Auto-generated method stub
		BookingSlot bookingSlot2 = findUniqueBookingSlot(bookingSlot.getCompanyId(), bookingSlot.getStart(), bookingSlot.getEnd());
			if(bookingSlot2 == null){
				bookingSlot.setCreateDate(new Date());
				String day = bookingSlot.getStart().getDate()+"";
				Date month = bookingSlot.getStart();
				 Calendar cal = Calendar.getInstance();
				 cal.setTime(month);
				 String monthName = new SimpleDateFormat("MMMM").format(cal.getTime());
				 int year = bookingSlot.getStart().getYear() + 1900;
				 String year2 = ""+year;
				 bookingSlot.setDay(day);
				 bookingSlot.setMonth(monthName);
				 bookingSlot.setYear(year2);
				 bookingSlot.setStartTimeInMs(bookingSlot.getStart().getTime());
				 bookingSlot.setDisabled(false);
				 return rep.save(bookingSlot);
			}
			else{
				bookingSlot.setCreateDate(bookingSlot2.getStart());
				bookingSlot.setUpdateDate(new Date());
				String day = bookingSlot2.getStart().getDate()+"";
				Date month = bookingSlot2.getStart();
				 Calendar cal = Calendar.getInstance();
				 cal.setTime(month);
				 String monthName = new SimpleDateFormat("MMMM").format(cal.getTime());
				 int year = bookingSlot2.getStart().getYear() + 1900;
				 String year2 = ""+year;
				 bookingSlot.setDay(day);
				 bookingSlot.setMonth(monthName);
				 bookingSlot.setYear(year2);
				 bookingSlot.setStartTimeInMs(bookingSlot.getStart().getTime());
				 bookingSlot.setDisabled(false);
				 Mapper mapper = new DozerBeanMapper();
				 bookingSlot.setId(bookingSlot2.getId());
				  mapper.map(bookingSlot, bookingSlot2);
				  return rep.save(bookingSlot2);
			}
	}

	@Override
	public boolean disable(BookingSlot bookingSlot) {
		BookingSlot bookingSlot2 = findUniqueBookingSlot(bookingSlot.getCompanyId(), bookingSlot.getStart(), bookingSlot.getEnd());
		bookingSlot2.setDisabled(true);
		rep.save(bookingSlot2);
		return false;
	}
	
	@Override
	public boolean enable(BookingSlot bookingSlot) {
		BookingSlot bookingSlot2 = findUniqueBookingSlot(bookingSlot.getCompanyId(), bookingSlot.getStart(), bookingSlot.getEnd());
		bookingSlot2.setDisabled(false);
		rep.save(bookingSlot2);
		return false;
	}



	@Override
	public List<BookingSlot> getBookingSlotsForDay(String companyId, String day, String month, String year) {
		// TODO Auto-generated method stub
		List<BookingSlot> list =  rep.getBookingSlotsForDay(companyId, day, month, year);
		List<BookingSlot> retList = new ArrayList<BookingSlot>();
			for(BookingSlot slot : list){
				Integer count = instanceRep.getCountOfParticipantsForSlot(slot.getCompanyId(), slot.getId());
				if(count < slot.getNoOfParticipants()){
					slot.setCapacityFilledSoFar(count);
					retList.add(slot);
				}
			}
		return retList;
		
	}
	
	private Integer getCountOfParticipants(BookingSlot slot){
		Integer count = instanceRep.getCountOfParticipantsForSlot(slot.getCompanyId(), slot.getId());
		return count;
	}



	@Override
	public List<BookingSlot> getAllBookingSlotsForDay_Filled_Unfilled(String companyId, String day, String month,
			String year) {
		// TODO Auto-generated method stub
		List<BookingSlot> list = rep.getBookingSlotsForDay(companyId, day, month, year);
			for(BookingSlot slot : list){
				Integer count = getCountOfParticipants(slot);
				slot.setCapacityFilledSoFar(count);
			}
		return list;
	}



	

}
