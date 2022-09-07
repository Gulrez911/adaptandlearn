package com.assessment.services;

import java.util.List;

import org.springframework.data.repository.query.Param;

import com.assessment.data.AdaptiveAssessmentLevelInstance;
import com.assessment.data.AdaptiveAssessmentSkillLevel;

public interface AdaptiveAssessmentLevelInstanceService {

	
	public AdaptiveAssessmentLevelInstance findAdaptiveAssessmentLevelInstance( String companyId, String adaptiveAssessmentName, String email,  AdaptiveAssessmentSkillLevel  level, Integer attempt);

	public AdaptiveAssessmentLevelInstance saveOrUpdate(AdaptiveAssessmentLevelInstance instance);
	
	public Integer findCountOfAdaptiveAssessmentLevelInstances(String companyId, String adaptiveAssessmentName, String email, AdaptiveAssessmentSkillLevel  level);
	
	public List<AdaptiveAssessmentLevelInstance> findAdaptiveAssessmentLevelInstances(String companyId, String adaptiveAssessmentName, String email, AdaptiveAssessmentSkillLevel  level);

	public AdaptiveAssessmentLevelInstance findUniqueAdaptiveAssessmentLevelInstanceLastAttempt(String companyId,  String adaptiveAssessmentName,String email, AdaptiveAssessmentSkillLevel  level);


	public List<AdaptiveAssessmentLevelInstance> findAllAdaptiveAssessmentLevelInstances(String companyId,  String adaptiveAssessmentName, String email);
}
