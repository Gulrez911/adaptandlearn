package com.assessment.services;

import com.assessment.data.AdaptiveAssessmentQuestionMapper;
import com.assessment.data.AdaptiveAssessmentSkillLevel;

public interface AdaptiveAssesmentQuestionMapperService {
	
	public AdaptiveAssessmentQuestionMapper findUniqueAdaptiveAssessmentQuestionMapper( String companyId,  String qualifier1, String qualifier2, String qualifier3, String qualifier4,  String qualifier5,  AdaptiveAssessmentSkillLevel level );
	
	public AdaptiveAssessmentQuestionMapper saveOrUpdate(AdaptiveAssessmentQuestionMapper mapper);
}
