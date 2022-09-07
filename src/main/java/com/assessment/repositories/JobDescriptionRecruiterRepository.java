package com.assessment.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.assessment.data.JobDescription;
import com.assessment.data.JobDescriptionRecruiter;

public interface JobDescriptionRecruiterRepository extends JpaRepository<JobDescriptionRecruiter, Long> {

	@Query("SELECT j FROM JobDescriptionRecruiter j WHERE j.companyId=:companyId and j.jobDescriptionName=:jobDescriptionName")
	List<JobDescriptionRecruiter> findByCompanyIdAndJobDescriptionName(@Param("companyId") String companyId,@Param("jobDescriptionName") String jobDescriptionName);
	
	@Query("SELECT j FROM JobDescriptionRecruiter j WHERE j.jobDescriptionName=:jobDescriptionName and j.companyId=:companyId")
	JobDescriptionRecruiter findByPrimaryKey(@Param("jobDescriptionName") String jobDescriptionName, @Param("companyId") String companyId);
	
	@Query(value="SELECT q FROM JobDescriptionRecruiter q WHERE q.companyId=:companyId and q.jobDescriptionId=:jobDescriptionId  and q.email=:email")
	public JobDescriptionRecruiter findUniqueJobDescriptionRecruiter(@Param("companyId") String companyId, @Param("jobDescriptionId") Long jobDescriptionId, @Param("email") String email);
	
	@Query("SELECT j FROM JobDescriptionRecruiter j WHERE j.email=:email and j.companyId=:companyId")
	List<JobDescriptionRecruiter> findByEmailAndCompanyId(@Param("email") String email, @Param("companyId") String companyId);
}
