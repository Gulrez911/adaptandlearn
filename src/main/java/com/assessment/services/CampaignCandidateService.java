package com.assessment.services;

import java.util.List;

import com.assessment.data.CampaignCandidate;

public interface CampaignCandidateService {

	public CampaignCandidate findUniqueCampaignCandidate( String companyId, String campaignName, String email);
	
	public List<CampaignCandidate> getCampaignCandidatesForCampaignMeetingByCompany( String companyId,  String campaignName);

	public CampaignCandidate saveOrUpdate(CampaignCandidate campaignCandidate);
	
	public void deleteCampaignCandidate(Long cid);
	
	public List<CampaignCandidate> findByCampaignNameAndCompanyId(String campaignName, String companyId);

	public CampaignCandidate findCampaignCandidate( String companyId, String campaignName,  String email);
}
