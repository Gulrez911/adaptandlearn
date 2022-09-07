package com.test;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

import org.glassfish.jersey.client.ClientConfig;
import org.junit.Test;

import com.assessment.pepipost.data.Content;
import com.assessment.pepipost.data.From;
import com.assessment.pepipost.data.Personalizations;
import com.assessment.pepipost.data.Root;
import com.assessment.pepipost.data.To;
import com.assessment.pepipost.response.data.Response;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class TestPepiPostEmail {
	
	@Test
	public void sendMail() throws JsonProcessingException{
		Root root = new Root();
		From from =new From();
		from.setEmail("coordinator@eassess.in");
		from.setName("eAssess Admin");
		
		To to = new To();
		to.setEmail("jatin.sutaria@thev2technologies.com");
		to.setName("jatin");
		
		
		To to2 = new To();
		to2.setEmail("jatinsut@yahoo.com");
		to2.setName("jatin");
		
		To to3 = new To();
		to3.setEmail("contact@eassess.in");
		to3.setName("eAssess");
		
		Personalizations personalizations = new Personalizations();
		personalizations.getTo().add(to);
		personalizations.getTo().add(to2);
		personalizations.getTo().add(to3);
		
		Content content = new Content();
		content.setType("html");
		content.setValue("<b>Good</b> <h1>bad</h1> Ugly");
		
		root.getContent().add(content);
		root.getPersonalizations().add(personalizations);
		root.setFrom(from);
		root.setSubject("Hello from eAssess");
		
		ObjectMapper mapper = new ObjectMapper();
		String res = mapper.writeValueAsString(root);
		System.out.println(res);
		ClientConfig config = new ClientConfig();

        Client client = ClientBuilder.newClient(config);
		 WebTarget target = client.target("https://api.pepipost.com/v5/mail/send");
		 Response response = target.request().header("api_key", "b41059db6c73184c751f6b3122533009").
	        				header("content-type", "application/json").
	                  post(Entity.entity(root, MediaType.APPLICATION_JSON), Response.class);
	                
	       System.out.println("status "+response.getStatus()+"  "+response.getStatus()+" message id "+response.getData().getMessage_id() );
		
	}

}
