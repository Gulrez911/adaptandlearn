package com.assessment.services;

import com.assessment.data.CampaignMeetingForCandidate;

public interface CampaignMeetingForCandidateService {
	
	public CampaignMeetingForCandidate findUniqueCampaignMeetingForCandidate(String companyId, String campaignName,  String campaignTestName, String candidateEmail);
	
	public CampaignMeetingForCandidate saveOrUpdate(CampaignMeetingForCandidate campaignMeetingForCandidate);
}
