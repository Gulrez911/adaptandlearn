package com.assessment.web.dto.newlookandfeel;

public class AdaptiveAssessmentResponseDto {

	AdaptiveAssessmentInstanceDTO instanceDTO;
	
	String errorMessage;
	
	Integer noOfAttempts;
	
	Integer noOfAttemptsAvailable;
	
	String email;
	
	String testName;
	
	

	public AdaptiveAssessmentInstanceDTO getInstanceDTO() {
		return instanceDTO;
	}

	public void setInstanceDTO(AdaptiveAssessmentInstanceDTO instanceDTO) {
		this.instanceDTO = instanceDTO;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public Integer getNoOfAttempts() {
		return noOfAttempts;
	}

	public void setNoOfAttempts(Integer noOfAttempts) {
		this.noOfAttempts = noOfAttempts;
	}

	public Integer getNoOfAttemptsAvailable() {
		return noOfAttemptsAvailable;
	}

	public void setNoOfAttemptsAvailable(Integer noOfAttemptsAvailable) {
		this.noOfAttemptsAvailable = noOfAttemptsAvailable;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTestName() {
		return testName;
	}

	public void setTestName(String testName) {
		this.testName = testName;
	}
	
	
}
