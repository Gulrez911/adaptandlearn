package com.assessment.data;

import javax.persistence.Entity;
import javax.validation.constraints.NotNull;



@Entity
public class CampaignReviewer extends Base{
	
	String firstName;
	
	String lastName;
	
	@NotNull
	String email;
	
	String password;
	
	String mobile;
	
	

	public String getFirstName() {
		if(this.firstName == null){
			return "NA";
		}
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		if(this.lastName == null){
			return "NA";
		}
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	
	

}
