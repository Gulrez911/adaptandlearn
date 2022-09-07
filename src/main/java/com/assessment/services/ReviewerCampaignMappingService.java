package com.assessment.services;

import java.util.List;

import com.assessment.data.ReviewerCampaignMapping;

public interface ReviewerCampaignMappingService {

	
	public ReviewerCampaignMapping findUniqueReviewerCampaignMapping( String companyId, String email,   String campaignName);

	List<ReviewerCampaignMapping> getReviewerCampaignMapping( String companyId,   String campaignName);
	
	public ReviewerCampaignMapping saveOrUpdate(ReviewerCampaignMapping reviewerCampaignMapping);
	
	public void deleteReviewerCampaignMapping(Long id);

	public ReviewerCampaignMapping findReviewerCampaignMappingById(Long id);
}
