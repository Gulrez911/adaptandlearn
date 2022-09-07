package com.assessment.services;

import java.util.List;

import com.assessment.data.AssessmentUploadData;

public interface AssessmentUploadDataService {
	
	public AssessmentUploadData findByPrimaryKey(String companyId,String domain,  String adaptiveAssessmentName);
	
	public List<AssessmentUploadData> findAssessmentsByDomain(String companyId, String domain);
	
	public AssessmentUploadData saveOrUpdate(AssessmentUploadData data);
	

}
