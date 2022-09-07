package com.test;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

import org.glassfish.jersey.client.ClientConfig;
import org.junit.Test;

import com.assessment.data.BookingSlot;

public class TestWebservice {
	
	@Test
	public void testSaveslot() throws Exception{
		
		ClientConfig config = new ClientConfig();

        Client client = ClientBuilder.newClient(config);
        
       
        //29/04/2021 03:38 PM
        String day = "13";
        String month = "01";
        String year = "2021";
        String time = "09:00 AM";
//        slot.setStart(start);
        String dateStr = day+"/"+month+"/"+year+" "+time;
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy hh:mm aa");
        Date start = dateFormat.parse(dateStr);
        Calendar cal = Calendar.getInstance();
        cal.setTime(start);
        cal.add(Calendar.HOUR, 1);
        Date end = cal.getTime();
        BookingSlot slot = new BookingSlot();
        slot.setCompanyId("e-assess");
        slot.setStart(start);
        slot.setEnd(end);
        slot.setNoOfParticipants(50);
        
        WebTarget target = client.target("http://eassess.in");
        String response = target.
                path("saveSlot").
                queryParam("companyId", "e-assess").
                queryParam("day", day).
                queryParam("month", month).
                queryParam("timestr", time).
                queryParam("year", year).request().
                post(Entity.entity(slot, MediaType.APPLICATION_JSON), String.class);
                
       System.out.println(response);
	}
	
	
	
	@Test
	public void testDate(){
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy hh:mm aa");
		System.out.println(dateFormat.format(new Date()));
	}
	

}
