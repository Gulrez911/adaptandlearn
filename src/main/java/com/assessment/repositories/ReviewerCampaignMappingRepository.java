package com.assessment.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.assessment.data.CampaignReviewer;
import com.assessment.data.ReviewerCampaignMapping;

public interface ReviewerCampaignMappingRepository  extends JpaRepository<ReviewerCampaignMapping, Long> {
	@Query(value="SELECT q FROM ReviewerCampaignMapping q WHERE q.companyId=:companyId and q.email=:email and q.campaignName=:campaignName")
	public ReviewerCampaignMapping findUniqueReviewerCampaignMapping(@Param("companyId") String companyId, @Param("email") String email,  @Param("campaignName") String campaignName);

	@Query(value="SELECT t FROM ReviewerCampaignMapping t WHERE t.companyId=:companyId and t.campaignName=:campaignName) ")
	List<ReviewerCampaignMapping> getReviewerCampaignMapping(@Param("companyId") String companyId, @Param("campaignName")  String campaignName);

}
