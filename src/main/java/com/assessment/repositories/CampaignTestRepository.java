package com.assessment.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.assessment.data.CampaignTest;

public interface CampaignTestRepository extends JpaRepository<CampaignTest, Long>  {
	
	@Query(value="SELECT q FROM CampaignTest q WHERE q.companyId=:companyId and q.testName=:testName")
	public CampaignTest findUniqueCampaignTest(@Param("companyId") String companyId, @Param("testName") String testName);
	
	
	@Query(value="SELECT q FROM CampaignTest q WHERE q.companyId=:companyId and q.campaignName=:campaignName and q.campaignTestName=:campaignTestName")
	public CampaignTest findUniqueCampaignTest(@Param("companyId") String companyId, @Param("campaignName") String campaignName, @Param("campaignTestName") String campaignTestName);
}
