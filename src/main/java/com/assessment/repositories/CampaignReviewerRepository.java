package com.assessment.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.assessment.data.CampaignReviewer;

public interface CampaignReviewerRepository extends JpaRepository<CampaignReviewer, Long> {

	@Query(value="SELECT q FROM CampaignReviewer q WHERE q.companyId=:companyId and q.email=:email")
	public CampaignReviewer findUniqueCampaignReviewer(@Param("companyId") String companyId, @Param("email") String email);

	@Query(value="SELECT t FROM CampaignReviewer t WHERE t.companyId=:companyId and ( t.firstName LIKE %:search% or t.lastName LIKE %:search% or t.email LIKE %:search%) ")
	Page<CampaignReviewer> searchCampaignReviewers(@Param("companyId") String companyId, @Param("search")  String search, Pageable pageable);

	@Query(value="SELECT t FROM CampaignReviewer t WHERE t.companyId=:companyId and ( t.firstName LIKE %:search% or t.lastName LIKE %:search% or t.email LIKE %:search%) ")
	List<CampaignReviewer> searchCampaignReviewersWithoutPagination(@Param("companyId") String companyId, @Param("search")  String search);

}
