package com.assessment.services;

import java.util.List;

import com.assessment.data.JobDescriptionRecruiter;

public interface JobDescriptionRecruiterService {

	public void saveOrUpdate(JobDescriptionRecruiter description);

	public JobDescriptionRecruiter findUniqueJobDescriptionRecruiter( String companyId, Long jobDescriptionId, String email);
	
	public List<JobDescriptionRecruiter> findByEmailAndCompanyId(String companyId, String email);
}
