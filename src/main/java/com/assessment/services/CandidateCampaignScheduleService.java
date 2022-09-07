package com.assessment.services;

import com.assessment.data.CandidateCampaignSchedule;

public interface CandidateCampaignScheduleService {

	public CandidateCampaignSchedule findByEmail(String companyId, String email);

	public CandidateCampaignSchedule saveOrUpdate(CandidateCampaignSchedule candidateCampaignSchedule);

	public CandidateCampaignSchedule getById(Long id);
	
	public CandidateCampaignSchedule findByEmailAndCampaignName(String email, String campName,String companyId);
}
