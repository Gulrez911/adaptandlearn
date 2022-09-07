package com.assessment.repositories;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.assessment.data.AdaptiveAssessmentLevelInstance;
import com.assessment.data.AdaptiveAssessmentSkillLevel;

public interface AdaptiveAssessmentLevelInstanceRepository extends JpaRepository<AdaptiveAssessmentLevelInstance, Long> {

	
	//AdaptiveAssessmentLevelInstance
	@Query(value="SELECT q FROM AdaptiveAssessmentLevelInstance q WHERE q.companyId=:companyId and q.adaptiveAssessmentName=:adaptiveAssessmentName and q.email=:email and q.level=:level and q.attempt=:attempt")
	public AdaptiveAssessmentLevelInstance findUniqueAdaptiveAssessmentLevelInstance(@Param("companyId") String companyId, @Param("adaptiveAssessmentName") String adaptiveAssessmentName, @Param("email") String email, @Param("level") AdaptiveAssessmentSkillLevel  level, @Param("attempt") Integer attempt);
	
	@Query(value="SELECT count(q) FROM AdaptiveAssessmentLevelInstance q WHERE q.companyId=:companyId and q.adaptiveAssessmentName=:adaptiveAssessmentName and q.email=:email and q.level=:level")
	public Integer findCountOfAdaptiveAssessmentLevelInstances(@Param("companyId") String companyId, @Param("adaptiveAssessmentName") String adaptiveAssessmentName, @Param("email") String email, @Param("level") AdaptiveAssessmentSkillLevel  level);

	@Query(value="SELECT q FROM AdaptiveAssessmentLevelInstance q WHERE q.companyId=:companyId and q.adaptiveAssessmentName=:adaptiveAssessmentName and q.email=:email and q.level=:level")
	public List<AdaptiveAssessmentLevelInstance> findAdaptiveAssessmentLevelInstances(@Param("companyId") String companyId, @Param("adaptiveAssessmentName") String adaptiveAssessmentName, @Param("email") String email, @Param("level") AdaptiveAssessmentSkillLevel  level);


	@Query(value="SELECT q FROM AdaptiveAssessmentLevelInstance q WHERE q.companyId=:companyId and q.adaptiveAssessmentName=:adaptiveAssessmentName and q.email=:email and q.level=:level order by q.attempt desc")
	public List<AdaptiveAssessmentLevelInstance> findUniqueAdaptiveAssessmentLevelInstanceLastAttempt(@Param("companyId") String companyId, @Param("adaptiveAssessmentName") String adaptiveAssessmentName, @Param("email") String email, @Param("level") AdaptiveAssessmentSkillLevel  level, Pageable pageable);
	

	@Query(value="SELECT q FROM AdaptiveAssessmentLevelInstance q WHERE q.companyId=:companyId and q.adaptiveAssessmentName=:adaptiveAssessmentName and q.email=:email order by q.level")
	public List<AdaptiveAssessmentLevelInstance> findAllAdaptiveAssessmentLevelInstances(@Param("companyId") String companyId, @Param("adaptiveAssessmentName") String adaptiveAssessmentName, @Param("email") String email);

}