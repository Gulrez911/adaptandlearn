package com.assessment.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.assessment.data.CandidateCampaignSchedule;

public interface CandidateCampaignScheduleRepository extends JpaRepository<CandidateCampaignSchedule, Long>{

//	CandidateCampaignSchedule findByEmailAndCompanyIdAnd
	@Query("SELECT c FROM CandidateCampaignSchedule c WHERE c.email=:email and c.campaignName=:campaignName and c.companyId=:companyId")
	CandidateCampaignSchedule findByEmailAndCampaignNameAndCompanyId(@Param("email") String email, @Param("campaignName") String campaignName, @Param("companyId")String companyId);
}
