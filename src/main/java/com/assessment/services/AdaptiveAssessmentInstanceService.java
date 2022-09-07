package com.assessment.services;

import com.assessment.data.AdaptiveAssessmentInstance;

public interface AdaptiveAssessmentInstanceService {
	
	public AdaptiveAssessmentInstance findAdaptiveAssessmentInstance(String testName, String companyId, String email);
	
	public AdaptiveAssessmentInstance saveOrUpdate(AdaptiveAssessmentInstance instance);
}
