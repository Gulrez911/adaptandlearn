package com.assessment.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.assessment.data.AdaptiveAssessment;
import com.assessment.data.UserTestSession;

public interface AdaptiveAssessmentRepository extends JpaRepository<AdaptiveAssessment, Long> {

	
	@Query(value="SELECT q FROM AdaptiveAssessment q WHERE q.companyId=:companyId and q.adaptiveAssessmentName=:adaptiveAssessmentName")
	public AdaptiveAssessment findUniqueAdaptiveAssessment(@Param("companyId") String companyId, @Param("adaptiveAssessmentName") String adaptiveAssessmentName);
	
	
}
