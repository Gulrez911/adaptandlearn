package com.assessment.services.impl;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

import com.assessment.services.ProctorTrackService;
import com.assessment.web.dto.ProctorInstructorSignInDto;
import com.assessment.web.dto.proctortrack.ProctorTrackRequest;
import com.assessment.web.dto.proctortrack.ProctorTrackResponse;
import com.assessment.web.dto.proctortrack.ProctorTrackTokenRequest;
import com.assessment.web.dto.proctortrack.ProctorTrackTokenResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
@org.springframework.stereotype.Service
public class ProctorTrackServiceImpl implements ProctorTrackService{

	String accountId = "ZO3ENV6ILXYU72V0PWSI";
	String clientId = "ertdPuADJlVtiDdZ0uxwJVtVCABqxL2i6FqQ76lk";
	
	String clientSecret = "JoQDIkTscKDrAx52eqmJ1vIfMZQKBd8qfO4IyllEbukHdEj61itgQWqeAXQEAmTzexK6ClVlhUdX5qmS5S6ZxHuKHyVup3bcJCU7PjUndNW4rEJQhaa9dxDs6mo3BkRk";

	String tokenUrl = "https://testing.verificient.com/"+accountId+"/jwt/access_token/";
	
	String url = "https://testing.verificient.com/"+accountId+"/provision/user/";
	
	String instructor_url = "https://testing.verificient.com/"+accountId+"/sign-on/user/";

	@Override
	public String getAccessToken() throws Exception {
		// TODO Auto-generated method stub
		URL url = new URL(tokenUrl);
		HttpsURLConnection con = (HttpsURLConnection)url.openConnection();
		con.setRequestMethod("POST");
		con.setRequestProperty("Content-Type", "application/json; utf-8");
		con.setRequestProperty("Accept", "application/json");
		con.setDoOutput(true);
		ObjectMapper mapper = new ObjectMapper();
		ProctorTrackTokenRequest request = new ProctorTrackTokenRequest();
		request.setClient_id(clientId);
		request.setClient_secret(clientSecret);
		String json = mapper.writeValueAsString(request);
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
		ProctorTrackTokenResponse res = mapper.readValue(response.toString().getBytes(), ProctorTrackTokenResponse.class);
		return res.getAccess_token();
	}

	@Override
	public String getProctoredUrl(ProctorTrackRequest request) throws Exception{
		// TODO Auto-generated method stub
		URL url2 = new URL(url);
		HttpsURLConnection con = (HttpsURLConnection)url2.openConnection();
		con.setRequestMethod("POST");
		con.setRequestProperty("Content-Type", "application/json; utf-8");
		con.setRequestProperty("Accept", "application/json");
		con.setDoOutput(true);
		String authString = "jwt "+getAccessToken();
		con.setRequestProperty("Authorization", authString);
		ObjectMapper mapper = new ObjectMapper();
		String json = mapper.writeValueAsString(request);
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
		ProctorTrackResponse response2 = mapper.readValue(response.toString().getBytes(), ProctorTrackResponse.class);
		return response2.getUrl();
	}

	

	@Override
	public String getInstructorUrl(ProctorInstructorSignInDto request) throws Exception {
		// TODO Auto-generated method stub
		URL url2 = new URL(instructor_url);
		HttpsURLConnection con = (HttpsURLConnection)url2.openConnection();
		con.setRequestMethod("POST");
		con.setRequestProperty("Content-Type", "application/json; utf-8");
		con.setRequestProperty("Accept", "application/json");
		con.setDoOutput(true);
		String authString = "jwt "+getAccessToken();
		con.setRequestProperty("Authorization", authString);
		ObjectMapper mapper = new ObjectMapper();
		String json = mapper.writeValueAsString(request);
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
		
		ProctorTrackResponse response2 = mapper.readValue(response.toString().getBytes(), ProctorTrackResponse.class);
		return response2.getUrl();
	}

}
