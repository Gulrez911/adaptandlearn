package com.assessment.data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Transient;

@Entity
public class CampaignTest extends Base{
	
	Boolean mandatory;
	
	@Column(length=1000)
	String testName;
	
	Integer weight;
	
	Integer sequenceNo;
	
	@Column(length=1000)
	String testSkills;
	
	@Column(length=1000)
	String campaignTestName;
	
	Long testId;
	
	@Column(length=500)
	String campaignName;
	
	Boolean meeting = false;
	
	@Transient
	Integer noOfMCQQuestions = 0;
	
	@Transient
	Integer noOfCodingQuestions = 0;
	
	@Transient
	Integer noOfFullStackQuestions = 0;

	@Transient
	Boolean complete;
	
	@Transient
	String testType;
	
	@Transient
	Integer testDuration;
	
	@Transient
	String url;
	
	@Transient
	String meetingStartTime;

	public Boolean getMandatory() {
		return mandatory;
	}

	public void setMandatory(Boolean mandatory) {
		this.mandatory = mandatory;
	}

	public String getTestName() {
		return testName;
	}

	public void setTestName(String testName) {
		this.testName = testName;
	}

	public Integer getWeight() {
		return weight;
	}

	public void setWeight(Integer weight) {
		this.weight = weight;
	}

	public Integer getSequenceNo() {
		return sequenceNo;
	}

	public void setSequenceNo(Integer sequenceNo) {
		this.sequenceNo = sequenceNo;
	}

	public String getTestSkills() {
		return testSkills;
	}

	public void setTestSkills(String testSkills) {
		this.testSkills = testSkills;
	}
	
	@Override
	public int hashCode(){
		return getCampaignTestName().hashCode();
	}
	
	@Override
	public boolean equals(Object obj){
		if(obj == null){
			return false;
		}
		CampaignTest campaignTest2 = (CampaignTest)obj;
		
		if(campaignTest2.getCampaignTestName() == null){
			return false;
		}
		
		if(this.getCampaignTestName().equals(campaignTest2.getCampaignTestName())){
			return true;
		}else{
			return false;
		}
	}

	public String getCampaignTestName() {
		return campaignTestName;
	}

	public void setCampaignTestName(String campaignTestName) {
		this.campaignTestName = campaignTestName;
	}

	public Long getTestId() {
		return testId;
	}

	public void setTestId(Long testId) {
		this.testId = testId;
	}

	public Boolean getMeeting() {
		return meeting;
	}

	public void setMeeting(Boolean meeting) {
		this.meeting = meeting;
	}

	public String getCampaignName() {
		return campaignName;
	}

	public void setCampaignName(String campaignName) {
		this.campaignName = campaignName;
	}

	public Integer getNoOfMCQQuestions() {
		return noOfMCQQuestions;
	}

	public void setNoOfMCQQuestions(Integer noOfMCQQuestions) {
		this.noOfMCQQuestions = noOfMCQQuestions;
	}

	public Integer getNoOfCodingQuestions() {
		return noOfCodingQuestions;
	}

	public void setNoOfCodingQuestions(Integer noOfCodingQuestions) {
		this.noOfCodingQuestions = noOfCodingQuestions;
	}

	public Integer getNoOfFullStackQuestions() {
		return noOfFullStackQuestions;
	}

	public void setNoOfFullStackQuestions(Integer noOfFullStackQuestions) {
		this.noOfFullStackQuestions = noOfFullStackQuestions;
	}

	public Boolean getComplete() {
		return complete;
	}

	public void setComplete(Boolean complete) {
		this.complete = complete;
	}

	public String getTestType() {
		
		String type = "";
		if(getNoOfMCQQuestions() > 0){
			type += "MCQ, ";
		}
		
		if(getNoOfCodingQuestions() > 0){
			type += "Coding, ";
		}
		
		if(getNoOfFullStackQuestions() > 0){
			type += "Fullstack App Dev";
		}
		type = type.trim();
		if(type.contains(",")){
			type = type.substring(0, type.lastIndexOf(","));
		}
				
			
				
		return type;
	}

	public void setTestType(String testType) {
		this.testType = testType;
	}

	public Integer getTestDuration() {
		return testDuration;
	}

	public void setTestDuration(Integer testDuration) {
		this.testDuration = testDuration;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getMeetingStartTime() {
		return meetingStartTime;
	}

	public void setMeetingStartTime(String meetingStartTime) {
		this.meetingStartTime = meetingStartTime;
	}

	
}
