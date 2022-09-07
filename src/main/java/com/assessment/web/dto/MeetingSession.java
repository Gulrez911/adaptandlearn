package com.assessment.web.dto;

import java.util.ArrayList;
import java.util.List;

public class MeetingSession {

	String tenantName = "Yaksha";
	
	String sessionTitle = "";
	
	String startDate;
	
	String endDate;
	
	String requesterEmailId;
	
	String timeZone = "India Standard Time";
	
	List<String> skills = new ArrayList<>();
	
	String sessionType = "3";
	
	List<MeetingParticipant> participants = new ArrayList<>();
	
	String joinMechanism = "3";
	
	String sessionStatus = "2";

	public String getTenantName() {
		return tenantName;
	}

	public void setTenantName(String tenantName) {
		this.tenantName = tenantName;
	}

	public String getSessionTitle() {
		return sessionTitle;
	}

	public void setSessionTitle(String sessionTitle) {
		this.sessionTitle = sessionTitle;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getRequesterEmailId() {
		return requesterEmailId;
	}

	public void setRequesterEmailId(String requesterEmailId) {
		this.requesterEmailId = requesterEmailId;
	}

	public String getTimeZone() {
		return timeZone;
	}

	public void setTimeZone(String timeZone) {
		this.timeZone = timeZone;
	}

	public List<String> getSkills() {
		return skills;
	}

	public void setSkills(List<String> skills) {
		this.skills = skills;
	}

	public String getSessionType() {
		return sessionType;
	}

	public void setSessionType(String sessionType) {
		this.sessionType = sessionType;
	}

	public List<MeetingParticipant> getParticipants() {
		return participants;
	}

	public void setParticipants(List<MeetingParticipant> participants) {
		this.participants = participants;
	}

	public String getJoinMechanism() {
		return joinMechanism;
	}

	public void setJoinMechanism(String joinMechanism) {
		this.joinMechanism = joinMechanism;
	}

	public String getSessionStatus() {
		return sessionStatus;
	}

	public void setSessionStatus(String sessionStatus) {
		this.sessionStatus = sessionStatus;
	}
	
	
}
