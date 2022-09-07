package com.assessment.web.dto;

public class PopularTestTaker {

	
	String email;
	
	Long count;
	
	String firstName;
	
	String lastName;
	
	public PopularTestTaker(){
		
	}
	
	public PopularTestTaker(String email, Long count){
		this.email = email;
		this.count = count;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Long getCount() {
		return count;
	}

	public void setCount(Long count) {
		this.count = count;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	
	
}
