package com.assessment.data;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

@Entity
public class CampaignMeetingForCandidate extends Base{
	@NotNull
	@Column(length=300)
	String campaignName;
	
	@NotNull
	@Column(length=1000)
	String campaignTestName;
	
	@NotNull
	String candidateEmail;
	
	String candidateFirstName;
	
	String candidateLastName;
	
	Date startTime;
	
	Date endTime;
	
	String meetingUrl;
	
	String candidateProfileLink;

	public String getCampaignName() {
		return campaignName;
	}

	public void setCampaignName(String campaignName) {
		this.campaignName = campaignName;
	}

	public String getCampaignTestName() {
		return campaignTestName;
	}

	public void setCampaignTestName(String campaignTestName) {
		this.campaignTestName = campaignTestName;
	}

	public String getCandidateEmail() {
		return candidateEmail;
	}

	public void setCandidateEmail(String candidateEmail) {
		this.candidateEmail = candidateEmail;
	}

	public String getCandidateFirstName() {
		return candidateFirstName;
	}

	public void setCandidateFirstName(String candidateFirstName) {
		this.candidateFirstName = candidateFirstName;
	}

	public String getCandidateLastName() {
		return candidateLastName;
	}

	public void setCandidateLastName(String candidateLastName) {
		this.candidateLastName = candidateLastName;
	}

	

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public String getMeetingUrl() {
		return meetingUrl;
	}

	public void setMeetingUrl(String meetingUrl) {
		this.meetingUrl = meetingUrl;
	}

	public String getCandidateProfileLink() {
		return candidateProfileLink;
	}

	public void setCandidateProfileLink(String candidateProfileLink) {
		this.candidateProfileLink = candidateProfileLink;
	}
	
	
}
