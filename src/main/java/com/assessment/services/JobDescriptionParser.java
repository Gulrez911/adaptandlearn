package com.assessment.services;

import com.assessment.web.dto.JobDescriptionParseResponseDto;

public interface JobDescriptionParser {
	
	public JobDescriptionParseResponseDto parseJobDesc(String contents);	

}
