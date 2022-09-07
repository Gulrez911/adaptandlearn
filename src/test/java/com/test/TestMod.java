package com.test;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.net.ssl.HttpsURLConnection;

import org.junit.Test;
import org.springframework.test.annotation.Rollback;

import com.assessment.web.dto.MeetingParticipant;
import com.assessment.web.dto.MeetingSession;
import com.assessment.web.dto.MeetingSessionResponse;
import com.assessment.web.dto.MeetingUser;
import com.assessment.web.dto.MeetingUserResponse;
import com.fasterxml.jackson.databind.ObjectMapper;

public class TestMod {
	
	String modUserCreationUrl = "https://mod-api.azurewebsites.net/api/services/mod/Users/SelfRegisterUser"; 
	
	String modSessionCreationUrl = "https://mod-api.azurewebsites.net/api/services/mod/Session/ExternalScheduleSessionAsync";
	
	@Test
	@Rollback(value=false)
	public void testCreateUser(){
		MeetingUser meetingUser = new MeetingUser();
		meetingUser.setModRole("Trainer");
		meetingUser.setName("Gulrez");
		meetingUser.setSurName("F");
		meetingUser.setEmail("gulrez.farooqui@thev2technologies.com");
		System.out.println(createModTrainer(meetingUser));
	}
	
	public String createModTrainer(MeetingUser meetingUser) {
		try {
			URL url = new URL(modUserCreationUrl);
			HttpsURLConnection con = (HttpsURLConnection)url.openConnection();
			con.setRequestMethod("POST");
			con.setRequestProperty("Content-Type", "application/json; utf-8");
			con.setRequestProperty("Accept", "application/json");
			con.setRequestProperty("Authorization", "null");
			con.setDoOutput(true);
			ObjectMapper mapper = new ObjectMapper();
			
			String json = mapper.writeValueAsString(meetingUser);
			try(OutputStream os = con.getOutputStream()) {
			    byte[] input = json.getBytes("utf-8");
			    os.write(input, 0, input.length);			
			}
			 StringBuilder response = new StringBuilder();
			try(BufferedReader br = new BufferedReader(
					  new InputStreamReader(con.getInputStream(), "utf-8"))) {
					   
					    String responseLine = null;
					    while ((responseLine = br.readLine()) != null) {
					        response.append(responseLine.trim());
					    }
					    System.out.println(response.toString());
					}
			MeetingUserResponse res = mapper.readValue(response.toString().getBytes(), MeetingUserResponse.class);
			return res.getResult();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			return e.getMessage();
		}

	}
	
	@Test
	@Rollback(value=false)
	public void testCreateModSession(){
		MeetingSession meetingSession = new MeetingSession();
		meetingSession.setSessionTitle("Meeting 1");
		meetingSession.setStartDate("05/01/2021 11:30 AM");
		meetingSession.setEndDate("05/01/2021 11:30 PM");
		meetingSession.setRequesterEmailId("gulrez.farooqui@thev2technologies.com");
		meetingSession.getSkills().add("Java");
		MeetingParticipant meetingParticipant1 = new MeetingParticipant();
		meetingParticipant1.setParticipantEmailId("jatinsut@yahoo.com");
		meetingSession.getParticipants().add(meetingParticipant1);
		
		MeetingParticipant meetingParticipant2 = new MeetingParticipant();
		meetingParticipant2.setParticipantEmailId("gulrez.farooqui@thev2technologies.com");
		meetingSession.getParticipants().add(meetingParticipant2);
		System.out.println(createModSession(meetingSession));
	}
	
	public String createModSession(MeetingSession meetingSession) {
		try {
			URL url = new URL(modSessionCreationUrl);
			HttpsURLConnection con = (HttpsURLConnection)url.openConnection();
			con.setRequestMethod("POST");
			con.setRequestProperty("Content-Type", "application/json; utf-8");
			con.setRequestProperty("Accept", "application/json");
			con.setRequestProperty("Authorization", "null");
			con.setDoOutput(true);
			ObjectMapper mapper = new ObjectMapper();
			
			String json = mapper.writeValueAsString(meetingSession);
			try(OutputStream os = con.getOutputStream()) {
			    byte[] input = json.getBytes("utf-8");
			    os.write(input, 0, input.length);			
			}
			 StringBuilder response = new StringBuilder();
			try(BufferedReader br = new BufferedReader(
					  new InputStreamReader(con.getInputStream(), "utf-8"))) {
					   
					    String responseLine = null;
					    while ((responseLine = br.readLine()) != null) {
					        response.append(responseLine.trim());
					    }
					    System.out.println(response.toString());
					}
			MeetingSessionResponse res = mapper.readValue(response.toString().getBytes(), MeetingSessionResponse.class);
			if(res.getResult().getErrorMessage() != null && res.getResult().getErrorMessage().toString().trim().length() > 0){
				return res.getResult().getErrorMessage().toString();
			}
			return res.getResult().getSessionUrl();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			return e.getMessage();
		}

	}

	@Test
	public void testDateFormat() throws Exception{
		String dd = "2021-01-06T15:30";
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
		Date dt = dateFormat.parse(dd);
		
		DateFormat dateFormat2 = new SimpleDateFormat("dd/MM/yyyy hh:mm aa");
		System.out.println(dateFormat2.format(dt));
				
	}
}
