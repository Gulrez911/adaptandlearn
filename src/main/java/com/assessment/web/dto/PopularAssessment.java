package com.assessment.web.dto;

public class PopularAssessment {
	
	String testName;
	
	Long count;
	
	public PopularAssessment(){
		
	}
	
	public PopularAssessment(String testName, Long count){
		this.testName = testName;
		this.count = count;
	}

	public String getTestName() {
		return testName;
	}

	public void setTestName(String testName) {
		this.testName = testName;
	}

	public Long getCount() {
		return count;
	}

	public void setCount(Long count) {
		this.count = count;
	}

	
	
	

}
