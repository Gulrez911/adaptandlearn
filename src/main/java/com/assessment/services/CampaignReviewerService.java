package com.assessment.services;

import java.util.List;

import com.assessment.data.CampaignReviewer;

public interface CampaignReviewerService {
	
	public CampaignReviewer findByEmail(String companyId, String email);
	
	
	public CampaignReviewer saveOrUpdate(CampaignReviewer campaignReviewer);
	
	List<CampaignReviewer> searchCampaignReviewersWithoutPagination(String companyId, String search);
	
	public CampaignReviewer getById(Long id);
	

}
