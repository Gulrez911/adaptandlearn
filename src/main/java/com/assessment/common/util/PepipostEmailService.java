package com.assessment.common.util;

import java.io.File;
import java.io.IOException;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

import org.apache.commons.io.FileUtils;
import org.glassfish.jersey.client.ClientConfig;

import com.assessment.pepipost.data.Content;
import com.assessment.pepipost.data.From;
import com.assessment.pepipost.data.Personalizations;
import com.assessment.pepipost.data.Root;
import com.assessment.pepipost.data.To;
import com.assessment.pepipost.response.data.Response;

public class PepipostEmailService implements Runnable{
	
	String to[];
	
	String subject;
	
	String message;
	
	String failureLocation;
	
	Long failureBookingFailureId;
	
	public PepipostEmailService(){
		
	}
	
	public PepipostEmailService(String to[], String subject, String message, String failureLocation, Long sid){
		this.to = to;
		this.subject = subject;
		this.message = message;
		this.failureLocation = failureLocation;
		this.failureBookingFailureId = sid;
	}
	
	public void sendEmail() throws IOException{
		
		try {
			Root root = new Root();
			From from =new From();
			from.setEmail("coordinator@eassess.in");
			from.setName("eAssess Admin");
			root.setFrom(from);
			Personalizations personalizations = new Personalizations();
			for(String t : to){
				To par = new To();
				par.setEmail(t);
				par.setName(t);
				personalizations.getTo().add(par);
			}
			
			root.getPersonalizations().add(personalizations);
			root.setSubject(subject);
			Content content = new Content();
			content.setType("html");
			content.setValue(message);
			root.getContent().add(content);
			
			ClientConfig config = new ClientConfig();

			Client client = ClientBuilder.newClient(config);
			 WebTarget target = client.target("https://api.pepipost.com/v5/mail/send");
			 Response response = target.request().header("api_key", "b41059db6c73184c751f6b3122533009").
			    				header("content-type", "application/json").
			              post(Entity.entity(root, MediaType.APPLICATION_JSON), Response.class);
			            
			   System.out.println(response.getData().getMessage_id()+" "+response.getStatus()+" for "+failureBookingFailureId);
			   if(!(response.getStatus() != null && response.getStatus().trim().equals("success"))){
				   if(failureBookingFailureId != null && failureLocation != null){
					   saveFailureMessage(message, failureBookingFailureId, failureLocation);
				   }
				   
			   }
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			if(failureBookingFailureId != null && failureLocation != null){
				saveFailureMessage(message, failureBookingFailureId, failureLocation);
			}
		}
		
	}
	
	private void saveFailureMessage(String html, Long sid,  String location) throws IOException{
		File file = new File(location  +  sid+".html");
		FileUtils.writeStringToFile(file, html);
		
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			sendEmail();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public String[] getTo() {
		return to;
	}

	public void setTo(String[] to) {
		this.to = to;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	

}
