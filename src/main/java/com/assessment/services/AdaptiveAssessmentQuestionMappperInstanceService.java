package com.assessment.services;

import java.util.List;

import org.springframework.data.repository.query.Param;

import com.assessment.data.AdaptiveAssessmentQuestionMappperInstance;
import com.assessment.data.AdaptiveAssessmentSkillLevel;

public interface AdaptiveAssessmentQuestionMappperInstanceService {

	
	public AdaptiveAssessmentQuestionMappperInstance findUniqueAdaptiveAssessmentQuestionMappperInstance(String companyId,  String questionText, String adaptiveAssessmentName,  String email, AdaptiveAssessmentSkillLevel  level, Integer attempt);
	
	public Integer findCountAdaptiveAssessmentQuestionMappperInstanceForLevel( String companyId,  String adaptiveAssessmentName,  String email, AdaptiveAssessmentSkillLevel  level);
	
	public AdaptiveAssessmentQuestionMappperInstance removeDublicateAndGetInstance(String companyId,  String questionText, String adaptiveAssessmentName,  String email, AdaptiveAssessmentSkillLevel  level, Integer attempt);

	public AdaptiveAssessmentQuestionMappperInstance saveOrUpdate(AdaptiveAssessmentQuestionMappperInstance instance);
	
	public void deleteDuplicateAnswers(List<AdaptiveAssessmentQuestionMappperInstance> qms);
	
	public Integer findCorrectAnswersAdaptiveAssessmentQuestionMappperInstanceForLevel( String companyId,  String adaptiveAssessmentName,  String email, AdaptiveAssessmentSkillLevel  level,  Integer attempt);

	public List<AdaptiveAssessmentQuestionMappperInstance> findAdaptiveAssessmentQuestionMappperInstancesForLevelAndAttempt(String companyId,   String adaptiveAssessmentName,  String email, AdaptiveAssessmentSkillLevel  level,  Integer attempt);
	
}
