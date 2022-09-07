package com.assessment.web.dto;

public class MeetingParticipant {

	String participantEmailId;
	
	String participantRole = "1";

	public String getParticipantEmailId() {
		return participantEmailId;
	}

	public void setParticipantEmailId(String participantEmailId) {
		this.participantEmailId = participantEmailId;
	}

	public String getParticipantRole() {
		return participantRole;
	}

	public void setParticipantRole(String participantRole) {
		this.participantRole = participantRole;
	}
	
	
}
