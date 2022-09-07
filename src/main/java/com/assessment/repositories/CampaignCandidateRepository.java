package com.assessment.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.assessment.data.CampaignCandidate;

public interface CampaignCandidateRepository extends JpaRepository<CampaignCandidate, Long> {

	
	@Query(value="SELECT q FROM CampaignCandidate q WHERE q.companyId=:companyId and q.campaignName=:campaignName  and q.email=:email")
	public CampaignCandidate findUniqueCampaignCandidate(@Param("companyId") String companyId, @Param("campaignName") String campaignName, @Param("email") String email);
	
	@Query(value="SELECT q FROM CampaignCandidate q WHERE q.companyId=:companyId and q.campaignName=:campaignName")
	public List<CampaignCandidate> getCampaignCandidatesForCampaignMeetingByCompany(@Param("companyId") String companyId, @Param("campaignName") String campaignName);

	public List<CampaignCandidate> findByCampaignNameAndCompanyId(String campaignName, String companyId);

	@Query(value="SELECT q FROM CampaignCandidate q WHERE q.companyId=:companyId and q.campaignName=:campaignName  and q.email=:email")
	public CampaignCandidate findCampaignCandidate(@Param("companyId") String companyId, @Param("campaignName") String campaignName, @Param("email") String email);
	
}
