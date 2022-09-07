package com.assessment.services.impl;

import java.util.Date;
import java.util.List;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.assessment.data.BookingSlotInstance;
import com.assessment.repositories.BookingSlotInstanceRepository;
import com.assessment.services.BookingSlotInstanceService;


@Service
@Transactional
public class BookingSlotInstanceImpl implements BookingSlotInstanceService{
	
	@Autowired
	BookingSlotInstanceRepository rep;

	@Override
	public BookingSlotInstance findUniqueBookingSlotInstance(String companyId, String email, Long sid) {
		// TODO Auto-generated method stub
		return rep.findUniqueBookingSlotInstance(companyId,  email, sid);
	}

	@Override
	public List<BookingSlotInstance> getBookingSlotInstancesForSlot(String companyId, Long sid) {
		// TODO Auto-generated method stub
		return rep.getBookingSlotInstancesForSlot(companyId, sid);
	}

	@Override
	public Integer getCountOfParticipantsForSlot(String companyId, Long sid) {
		// TODO Auto-generated method stub
		return rep.getCountOfParticipantsForSlot(companyId, sid);
	}

	@Override
	public BookingSlotInstance saveOrUpdate(BookingSlotInstance bookingSlotInstance) {
		// TODO Auto-generated method stub
		BookingSlotInstance bookingSlotInstance2 = findUniqueBookingSlotInstance(bookingSlotInstance.getCompanyId(),  bookingSlotInstance.getEmail(), bookingSlotInstance.getSlot().getId());
		if(bookingSlotInstance2 == null){
			bookingSlotInstance.setCreateDate(new Date());
			return rep.save(bookingSlotInstance);
		}
		else{
			bookingSlotInstance.setCreateDate(bookingSlotInstance2.getCreateDate());
			bookingSlotInstance.setUpdateDate(new Date());
			bookingSlotInstance.setId(bookingSlotInstance2.getId());
			bookingSlotInstance.setUpdateDate(new Date());
			Mapper mapper = new DozerBeanMapper();
			mapper.map(bookingSlotInstance, bookingSlotInstance2);
			return rep.save(bookingSlotInstance2);
		}
		
	}

}
