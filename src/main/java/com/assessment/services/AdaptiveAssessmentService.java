package com.assessment.services;

import com.assessment.data.AdaptiveAssessment;

public interface AdaptiveAssessmentService {

	
	public AdaptiveAssessment findUniqueAdaptiveAssessment(String companyId, String adaptiveAssessmentName);
	
	public AdaptiveAssessment saveOrUpdate(AdaptiveAssessment assessment);
}
