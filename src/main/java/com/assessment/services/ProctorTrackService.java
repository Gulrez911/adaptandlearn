package com.assessment.services;

import com.assessment.web.dto.ProctorInstructorSignInDto;
import com.assessment.web.dto.proctortrack.ProctorTrackRequest;

public interface ProctorTrackService {

	
	public String getAccessToken() throws Exception;
	
	public String getProctoredUrl(ProctorTrackRequest request) throws Exception;
	
	
	public String getInstructorUrl(ProctorInstructorSignInDto request) throws Exception;
	
	
}
