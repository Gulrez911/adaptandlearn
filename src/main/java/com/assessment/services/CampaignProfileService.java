package com.assessment.services;

import java.util.List;

import com.assessment.data.CampaignProfile;

public interface CampaignProfileService {
	public void saveOrUpdate(List<CampaignProfile> learners);

	public void deleteByUserEmail(String userEmail);
}
