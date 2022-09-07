package com.assessment.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.assessment.data.AdaptiveAssessment;
import com.assessment.data.AdaptiveAssessmentInstance;

public interface AdaptiveAssessmentInstanceRepository extends JpaRepository<AdaptiveAssessmentInstance, Long> {

	
	@Query(value="SELECT q FROM AdaptiveAssessmentInstance q WHERE q.companyId=:companyId and q.adaptiveAssessmentName=:adaptiveAssessmentName and q.email=:email")
	public AdaptiveAssessmentInstance findUniqueAdaptiveAssessmentInstance(@Param("companyId") String companyId, @Param("adaptiveAssessmentName") String adaptiveAssessmentName, @Param("email") String email);
	
	@Query("SELECT u FROM AdaptiveAssessmentInstance u WHERE u.email LIKE CONCAT(:email,'%') and u.adaptiveAssessmentName=:testName and u.companyId=:companyId")
	 List<AdaptiveAssessmentInstance> findByTestNamePart(@Param("email") String email, @Param("testName")  String testName, @Param("companyId") String companyId);
}