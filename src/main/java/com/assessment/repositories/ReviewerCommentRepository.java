package com.assessment.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.assessment.data.ReviewerComment;

public interface ReviewerCommentRepository extends JpaRepository<ReviewerComment,Long> {
	@Query(value="SELECT r FROM ReviewerComment r WHERE r.campaignName=:campaignName and r.candidateEmail=:candidateEmail  and r.companyId=:companyId")
	public ReviewerComment findByPrimaryKey(@Param("campaignName")String campaignName,@Param("candidateEmail")String candidateEmail,@Param("companyId")String companyId);
}
