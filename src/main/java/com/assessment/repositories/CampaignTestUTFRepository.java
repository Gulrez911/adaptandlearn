package com.assessment.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.assessment.data.CampaignTestUTF;

public interface CampaignTestUTFRepository extends JpaRepository<CampaignTestUTF, Long>{

	CampaignTestUTF findByLanguage(String lang);
}
