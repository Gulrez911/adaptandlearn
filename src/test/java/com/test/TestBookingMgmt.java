package com.test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.assessment.data.BookingSlot;
import com.assessment.data.BookingSlotInstance;
import com.assessment.services.BookingSlotInstanceService;
import com.assessment.services.BookingSlotService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:appContext.xml"})
@Transactional
public class TestBookingMgmt {
	
	@Autowired
	BookingSlotService slotService;
	
	@Autowired
	BookingSlotInstanceService instanceService;
	
	@Test
	@Rollback(value=false)
	public void testCreateBookingInstance() throws ParseException{
		
		String day = "11";
        String month = "01";
        String year = "2021";
        String time = "04:00 AM";
//        slot.setStart(start);
        String dateStr = day+"/"+month+"/"+year+" "+time;
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy hh:mm aa");
        Date start = dateFormat.parse(dateStr);
        Calendar cal = Calendar.getInstance();
        cal.setTime(start);
        cal.add(Calendar.HOUR, 1);
        Date end = cal.getTime();
        BookingSlot slot= slotService.findUniqueBookingSlot("IH", start, end);
        BookingSlotInstance instance = new  BookingSlotInstance();
        instance.setSlot(slot);
        instance.setCompanyId("IH");
        instance.setCompanyName("IIHT");
        instance.setTestName("Test A");
        instance.setTestUrl("http://google.com");
        instance.setTestPracticeUrl("http://yahoo.com");
        instance.setFirstName("John");
        instance.setLastName("Smith");
        instance.setEmail("john@smith.com");
        instance.setMobile("001020304");
        instanceService.saveOrUpdate(instance);
	}
	
	@Test
	@Rollback(value=false)
	public void testFetchBookingInstances(){
		List<BookingSlotInstance> instances = instanceService.getBookingSlotInstancesForSlot("IH", 10925l);
		System.out.println(instances.size());
	}

}
