package com.assessment.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.assessment.data.AdaptiveAssessmentQuestionMapper;
import com.assessment.data.AdaptiveAssessmentSkillLevel;

public interface AdaptiveAssesmentQuestionMapperRepository extends JpaRepository<AdaptiveAssessmentQuestionMapper, Long> {

	
	@Query(value="SELECT q FROM AdaptiveAssessmentQuestionMapper q WHERE q.companyId=:companyId and q.qualifier1=:qualifier1 and q.qualifier2=:qualifier2 and q.qualifier3=:qualifier3 and q.qualifier4=:qualifier4 and q.qualifier5=:qualifier5 and q.level=:level")
	public AdaptiveAssessmentQuestionMapper findUniqueAdaptiveAssessmentQuestionMapper(@Param("companyId") String companyId, @Param("qualifier1") String qualifier1, @Param("qualifier2") String qualifier2, @Param("qualifier3") String qualifier3, @Param("qualifier4") String qualifier4, @Param("qualifier5") String qualifier5, @Param("level") AdaptiveAssessmentSkillLevel level );
	
		
}
