package com.assessment.services;

import com.assessment.data.AdaptiveAssessmentSkill;
import com.assessment.data.AdaptiveAssessmentSkillLevel;

public interface AdaptiveAssessmentSkillService {

	
	public AdaptiveAssessmentSkill findUniqueAdaptiveAssessmentSkill( String companyId,  String skillName, String adaptiveAssessmentName,  String qualifier1,  String qualifier2,  String qualifier3, String qualifier4,  String qualifier5);
	
	public AdaptiveAssessmentSkill findUniqueAdaptiveAssessmentSkillByQualifier( String companyId,  String adaptiveAssessmentName,  String qualifier1,  String qualifier2,  String qualifier3,  String qualifier4, String qualifier5);
	
	
	public AdaptiveAssessmentSkill saveOrUpdate(AdaptiveAssessmentSkill adaptiveAssessmentSkill);
	
	public AdaptiveAssessmentSkill findUniqueLevelForAdaptiveAssessment( String companyId,  String adaptiveAssessmentName, AdaptiveAssessmentSkillLevel level );
	
}
