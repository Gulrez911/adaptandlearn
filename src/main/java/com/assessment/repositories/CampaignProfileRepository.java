package com.assessment.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.assessment.data.CampaignProfile;

public interface CampaignProfileRepository extends JpaRepository<CampaignProfile, Long> {

	public void deleteByUserEmail(String userEmail);
}
