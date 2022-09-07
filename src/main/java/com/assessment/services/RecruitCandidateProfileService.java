package com.assessment.services;

import java.util.List;

import com.assessment.data.RecruitCandidateProfile;

public interface RecruitCandidateProfileService {
	
	public RecruitCandidateProfile findByPrimaryKey(Long jobdescId, String candidateEmail, String companyId);
	
	public RecruitCandidateProfile saveOrUpdate(RecruitCandidateProfile candidateProfile);
	
	public List<RecruitCandidateProfile> findByJobDescAndCompanyId(Long jid, String companyId);
	
	public RecruitCandidateProfile findByEmailAndCompanyId(String candidateEmail, String companyId);

}
