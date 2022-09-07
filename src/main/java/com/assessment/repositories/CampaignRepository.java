package com.assessment.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.assessment.data.Campaign;

public interface CampaignRepository extends JpaRepository<Campaign, Long> {

	
	@Query(value="SELECT q FROM Campaign q WHERE q.companyId=:companyId and q.campaignName=:campaignName")
	public Campaign findUniqueCampaign(@Param("companyId") String companyId, @Param("campaignName") String campaignName);
	
	@Query(value="SELECT c FROM Campaign c WHERE c.companyId=:companyId" )
	public Page<Campaign> getCampaignsByCompany(@Param("companyId") String companyId, Pageable pageable);
	
	@Query(value="SELECT c FROM Campaign c WHERE c.companyId=:companyId and c.campaignName LIKE %:searchText%")
	public Page<Campaign> searchCampaigns(@Param("companyId") String companyId, @Param("searchText")  String searchText, Pageable pageable);

	@Query(value="SELECT c FROM Campaign c join c.reviewers r WHERE c.companyId=:companyId and r.email=:email" )
	public Page<Campaign> getCampaignsByReviewerAndCompany(@Param("companyId") String companyId,@Param("email") String email, Pageable pageable);
	
	
	@Query(value="SELECT c FROM Campaign c join c.reviewers r WHERE c.companyId=:companyId and c.campaignName LIKE %:search% and r.email=:email" )
	public Page<Campaign> searchCampaignsByReviewerAndCompany(@Param("companyId") String companyId,@Param("search") String search,@Param("email") String email, Pageable pageable);

	public List<Campaign> findByCompanyId(String companyId);
}
