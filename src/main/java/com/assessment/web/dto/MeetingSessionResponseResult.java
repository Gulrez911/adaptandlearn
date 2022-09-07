package com.assessment.web.dto;

public class MeetingSessionResponseResult {
	public int sessionScheduleId;
    public Object errorMessage;
    public String sessionUrl;
	public int getSessionScheduleId() {
		return sessionScheduleId;
	}
	public void setSessionScheduleId(int sessionScheduleId) {
		this.sessionScheduleId = sessionScheduleId;
	}
	public Object getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(Object errorMessage) {
		this.errorMessage = errorMessage;
	}
	public String getSessionUrl() {
		return sessionUrl;
	}
	public void setSessionUrl(String sessionUrl) {
		this.sessionUrl = sessionUrl;
	}
    
    
    
}
