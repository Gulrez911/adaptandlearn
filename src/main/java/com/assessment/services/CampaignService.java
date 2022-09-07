package com.assessment.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.assessment.data.Campaign;

public interface CampaignService {
	
	public Campaign findUniqueCampaign(String companyId, String campaignName);
	
	public Campaign saveOrUpdate(Campaign campaign);
	
	public Page<Campaign> getCampaignsByCompany(String companyId, Pageable pageable);
	
	public Page<Campaign> searchCampaigns(String companyId,  String searchText, Pageable pageable);

	public Page<Campaign> getCampaignsByReviewerAndCompany(String companyId, String email, Pageable pageable);
	
	public Page<Campaign> searchCampaignsByReviewerAndCompany(String companyId, String search, String email, Pageable pageable);

	public List<Campaign> findByPrimaryKey(String companyId);
}
