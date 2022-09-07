package com.assessment.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.assessment.data.AdaptiveAssessmentSkill;
import com.assessment.data.AdaptiveAssessmentSkillLevel;

public interface AdaptiveAssessmentSkillRepository extends JpaRepository<AdaptiveAssessmentSkill, Long> {

	
	@Query(value="SELECT q FROM AdaptiveAssessmentSkill q WHERE q.companyId=:companyId and q.skillName=:skillName and q.adaptiveAssessmentName=:adaptiveAssessmentName and q.qualifier1=:qualifier1 and q.qualifier2=:qualifier2 and q.qualifier3=:qualifier3 and q.qualifier4=:qualifier4 and q.qualifier5=:qualifier5")
	public AdaptiveAssessmentSkill findUniqueAdaptiveAssessmentSkill(@Param("companyId") String companyId, @Param("skillName") String skillName, @Param("adaptiveAssessmentName") String adaptiveAssessmentName, @Param("qualifier1") String qualifier1, @Param("qualifier2") String qualifier2, @Param("qualifier3") String qualifier3, @Param("qualifier4") String qualifier4, @Param("qualifier5") String qualifier5);
	
	@Query(value="SELECT q FROM AdaptiveAssessmentSkill q WHERE q.companyId=:companyId  and q.adaptiveAssessmentName=:adaptiveAssessmentName and q.qualifier1=:qualifier1 and q.qualifier2=:qualifier2 and q.qualifier3=:qualifier3 and q.qualifier4=:qualifier4 and q.qualifier5=:qualifier5")
	public AdaptiveAssessmentSkill findUniqueAdaptiveAssessmentSkillByQualifier(@Param("companyId") String companyId, @Param("adaptiveAssessmentName") String adaptiveAssessmentName, @Param("qualifier1") String qualifier1, @Param("qualifier2") String qualifier2, @Param("qualifier3") String qualifier3, @Param("qualifier4") String qualifier4, @Param("qualifier5") String qualifier5);
	
	@Query(value="SELECT q FROM AdaptiveAssessmentSkill q WHERE q.companyId=:companyId  and q.adaptiveAssessmentName=:adaptiveAssessmentName and q.startingLevel=:level")
	public AdaptiveAssessmentSkill findUniqueLevelForAdaptiveAssessment(@Param("companyId") String companyId, @Param("adaptiveAssessmentName") String adaptiveAssessmentName, @Param("level") AdaptiveAssessmentSkillLevel level );
		
}