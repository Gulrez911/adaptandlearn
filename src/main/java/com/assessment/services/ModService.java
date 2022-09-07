package com.assessment.services;

import com.assessment.web.dto.MeetingSession;
import com.assessment.web.dto.MeetingUser;

public interface ModService {
	
	public String createModSession(MeetingSession meetingSession);
	
	public String createModTrainer(MeetingUser meetingUser) ;

}
