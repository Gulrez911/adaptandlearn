package com.assessment.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.assessment.data.CampaignMeetingForCandidate;

public interface CampaignMeetingForCandidateRepository extends JpaRepository<CampaignMeetingForCandidate, Long> {

	
	@Query(value="SELECT q FROM CampaignMeetingForCandidate q WHERE q.companyId=:companyId and q.campaignName=:campaignName and q.campaignTestName=:campaignTestName and q.candidateEmail=:candidateEmail")
	public CampaignMeetingForCandidate findUniqueCampaignMeetingForCandidate(@Param("companyId") String companyId, @Param("campaignName") String campaignName, @Param("campaignTestName") String campaignTestName, @Param("candidateEmail") String candidateEmail);
	
	
	
}