package com.assessment.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.assessment.data.AdaptiveAssessmentQuestionMappperInstance;
import com.assessment.data.AdaptiveAssessmentSkillLevel;

public interface AdaptiveAssessmentQuestionMappperInstanceRepository extends JpaRepository<AdaptiveAssessmentQuestionMappperInstance, Long> {

	
	//AdaptiveAssessmentLevelInstance
	@Query(value="SELECT q FROM AdaptiveAssessmentQuestionMappperInstance q WHERE q.companyId=:companyId and q.questionText=:questionText and q.testName=:adaptiveAssessmentName and q.user=:email and q.adaptiveAssessmentSkillLevel=:level and q.attempt=:attempt")
	public AdaptiveAssessmentQuestionMappperInstance findUniqueAdaptiveAssessmentQuestionMappperInstance(@Param("companyId") String companyId, @Param("questionText") String questionText, @Param("adaptiveAssessmentName") String adaptiveAssessmentName, @Param("email") String email, @Param("level") AdaptiveAssessmentSkillLevel  level, @Param("attempt") Integer attempt);
	
	/**
	 * sometimes duplicate records also get inserted despite unique key validation
	 * @param companyId
	 * @param questionText
	 * @param adaptiveAssessmentName
	 * @param email
	 * @param level
	 * @param attempt
	 * @return
	 */
	@Query(value="SELECT q FROM AdaptiveAssessmentQuestionMappperInstance q WHERE q.companyId=:companyId and q.questionText=:questionText and q.testName=:adaptiveAssessmentName and q.user=:email and q.adaptiveAssessmentSkillLevel=:level and q.attempt=:attempt")
	public List<AdaptiveAssessmentQuestionMappperInstance> findUniqueAdaptiveAssessmentsQuestionMappperInstanceUserSet(@Param("companyId") String companyId, @Param("questionText") String questionText, @Param("adaptiveAssessmentName") String adaptiveAssessmentName, @Param("email") String email, @Param("level") AdaptiveAssessmentSkillLevel  level, @Param("attempt") Integer attempt);

	
	@Query(value="SELECT max(q.attempt) FROM AdaptiveAssessmentQuestionMappperInstance q WHERE q.companyId=:companyId and q.testName=:adaptiveAssessmentName and q.user=:email and q.adaptiveAssessmentSkillLevel=:level")
	public Integer findCountAdaptiveAssessmentQuestionMappperInstanceForLevel(@Param("companyId") String companyId, @Param("adaptiveAssessmentName") String adaptiveAssessmentName, @Param("email") String email, @Param("level") AdaptiveAssessmentSkillLevel  level);
	
	@Query(value="SELECT count(q) FROM AdaptiveAssessmentQuestionMappperInstance q WHERE q.companyId=:companyId and q.testName=:adaptiveAssessmentName and q.user=:email and q.adaptiveAssessmentSkillLevel=:level and q.attempt=:attempt and correct=true")
	public Integer findCorrectAnswersAdaptiveAssessmentQuestionMappperInstanceForLevel(@Param("companyId") String companyId, @Param("adaptiveAssessmentName") String adaptiveAssessmentName, @Param("email") String email, @Param("level") AdaptiveAssessmentSkillLevel  level, @Param("attempt") Integer attempt);
	
	
	@Query(value="SELECT q FROM AdaptiveAssessmentQuestionMappperInstance q WHERE q.companyId=:companyId and q.testName=:adaptiveAssessmentName and q.user=:email and q.adaptiveAssessmentSkillLevel=:level and q.attempt=:attempt")
	public List<AdaptiveAssessmentQuestionMappperInstance> findAdaptiveAssessmentQuestionMappperInstancesForLevelAndAttempt(@Param("companyId") String companyId,  @Param("adaptiveAssessmentName") String adaptiveAssessmentName, @Param("email") String email, @Param("level") AdaptiveAssessmentSkillLevel  level, @Param("attempt") Integer attempt);
	
	
}