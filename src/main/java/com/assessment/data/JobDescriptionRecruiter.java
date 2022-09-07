package com.assessment.data;

import javax.persistence.Entity;

@Entity
public class JobDescriptionRecruiter extends Base {

	String jobDescriptionName;
	
	String firstName;

	String lastName;

	String email;

	Long jobDescriptionId;

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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Long getJobDescriptionId() {
		return jobDescriptionId;
	}

	public void setJobDescriptionId(Long jobDescriptionId) {
		this.jobDescriptionId = jobDescriptionId;
	}

	public String getJobDescriptionName() {
		return jobDescriptionName;
	}

	public void setJobDescriptionName(String jobDescriptionName) {
		this.jobDescriptionName = jobDescriptionName;
	}

}
