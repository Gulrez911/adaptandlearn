package com.assessment.web.dto.proctortrack;

public class ProctorTrackTokenRequest {
	private String client_id;
	 private String client_secret;


	 // Getter Methods 

	 public String getClient_id() {
	  return client_id;
	 }

	 public String getClient_secret() {
	  return client_secret;
	 }

	 // Setter Methods 

	 public void setClient_id(String client_id) {
	  this.client_id = client_id;
	 }

	 public void setClient_secret(String client_secret) {
	  this.client_secret = client_secret;
	 }
}
