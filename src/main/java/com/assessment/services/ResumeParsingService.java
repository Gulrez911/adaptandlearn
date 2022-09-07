package com.assessment.services;

import java.util.List;

import com.assessment.web.dto.resumeparsing.ResumeParsingResponse;

public interface ResumeParsingService {

	
	public ResumeParsingResponse parseResume(String fileToParse, String jobdescName, Long jobdescId, String compId, String createdBy, String profileLoc, List<String> expectedPrimarySkills, List<String> expectedSecondarySkills); 
}
