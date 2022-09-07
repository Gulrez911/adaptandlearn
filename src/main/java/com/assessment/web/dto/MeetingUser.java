package com.assessment.web.dto;

public class MeetingUser {

	String tenantName = "Yaksha";
	
	String modRole = "Trainer";
	
	String name = "";
	String surName = "";
	
	String email = "";

	public String getTenantName() {
		return tenantName;
	}

	public void setTenantName(String tenantName) {
		this.tenantName = tenantName;
	}

	public String getModRole() {
		return modRole;
	}

	public void setModRole(String modRole) {
		this.modRole = modRole;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurName() {
		return surName;
	}

	public void setSurName(String surName) {
		this.surName = surName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	
}
