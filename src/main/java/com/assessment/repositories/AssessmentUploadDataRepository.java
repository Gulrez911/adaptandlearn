package com.assessment.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.assessment.data.AssessmentUploadData;

public interface AssessmentUploadDataRepository extends JpaRepository<AssessmentUploadData, Long> {
	
	@Query(value="SELECT q FROM AssessmentUploadData q WHERE q.companyId=:companyId and q.domain=:domain and q.adaptiveAssessmentName=:adaptiveAssessmentName")
	public AssessmentUploadData findByPrimaryKey(@Param("companyId") String companyId, @Param("domain") String domain, @Param("adaptiveAssessmentName") String adaptiveAssessmentName);
	
	@Query(value="SELECT q FROM AssessmentUploadData q WHERE q.companyId=:companyId and q.domain=:domain")
	public List<AssessmentUploadData> findAssessmentsByDomain(@Param("companyId") String companyId, @Param("domain") String domain);
	
	@Query(value="SELECT q FROM AssessmentUploadData q WHERE q.companyId=:companyId")
	public List<AssessmentUploadData> findAssessmentsByCompany(@Param("companyId") String companyId);
	
	@Query(value="SELECT q FROM AssessmentUploadData q WHERE q.companyId=:companyId and q.domain=:domain and q.jobDescName=:jobDescName")
	public AssessmentUploadData findByPrimaryKey2(@Param("companyId") String companyId, @Param("domain") String domain, @Param("jobDescName") String jobDescName);
	
	
	@Query(value="SELECT q FROM AssessmentUploadData q WHERE q.companyId=:companyId  and q.jobDescName=:jobDescName")
	public List<AssessmentUploadData> findByPrimaryKey3(@Param("companyId") String companyId, @Param("jobDescName") String jobDescName);
	
	
}
