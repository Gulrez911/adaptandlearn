package com.assessment.web.dto.proctortrack;

public class ProctorTrackRequest {

	Assignment_data assignment_data;
	
	ProctorTrackUser user;

	

	public Assignment_data getAssignment_data() {
		return assignment_data;
	}

	public void setAssignment_data(Assignment_data assignment_data) {
		this.assignment_data = assignment_data;
	}

	public ProctorTrackUser getUser() {
		return user;
	}

	public void setUser(ProctorTrackUser user) {
		this.user = user;
	}
	
	
}
