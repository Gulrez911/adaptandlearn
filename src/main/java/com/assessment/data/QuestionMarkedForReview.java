package com.assessment.data;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class QuestionMarkedForReview extends Base{
	
	Long questionId;
	
	@Column(length=3000)
	String questionText;
	
	String email;
	
	String firstName;
	
	String lastName;
	
	@Column(length=1000)
	String reason;
	
	String testName;
	
	Long testId;
	
	public QuestionMarkedForReview(){
		
	}
	
	public QuestionMarkedForReview(Long questionId, String email,  Long testId, String companyId, String companyName){
		this.questionId = questionId;
		this.email = email;
		this.testId = testId;
		this.setCompanyId(companyId);
		this.setCompanyName(companyName);
	}

	public Long getQuestionId() {
		return questionId;
	}

	public void setQuestionId(Long questionId) {
		this.questionId = questionId;
	}

	public String getQuestionText() {
		return questionText;
	}

	public void setQuestionText(String questionText) {
		this.questionText = questionText;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getTestName() {
		return testName;
	}

	public void setTestName(String testName) {
		this.testName = testName;
	}

	public Long getTestId() {
		return testId;
	}

	public void setTestId(Long testId) {
		this.testId = testId;
	}
	
	

}
