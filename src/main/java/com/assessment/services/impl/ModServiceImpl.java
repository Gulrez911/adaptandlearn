package com.assessment.services.impl;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

import org.springframework.stereotype.Service;

import com.assessment.services.ModService;
import com.assessment.web.dto.MeetingSession;
import com.assessment.web.dto.MeetingSessionResponse;
import com.assessment.web.dto.MeetingUser;
import com.assessment.web.dto.MeetingUserResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
@Service
public class ModServiceImpl implements ModService{
String modUserCreationUrl = "https://mod-api.azurewebsites.net/api/services/mod/Users/SelfRegisterUser"; 
	
	String modSessionCreationUrl = "https://mod-api.azurewebsites.net/api/services/mod/Session/ExternalScheduleSessionAsync";
	
	@Override
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

	@Override
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

}
