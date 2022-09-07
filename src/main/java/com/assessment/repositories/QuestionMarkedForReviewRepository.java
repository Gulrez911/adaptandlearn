package com.assessment.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.assessment.data.QuestionMarkedForReview;

public interface QuestionMarkedForReviewRepository extends JpaRepository< QuestionMarkedForReview,Long>{
	
	@Query("SELECT c FROM QuestionMarkedForReview c WHERE c.email=:email and  c.questionId=:questionId and c.testId=:testId and c.companyId=:companyId")
	QuestionMarkedForReview findByPrimaryKey(@Param("email") String email, @Param("questionId") Long questionId,@Param("testId") Long testId,  @Param("companyId") String companyId);
	
	@Query("SELECT c FROM QuestionMarkedForReview c WHERE c.email=:email and  c.questionText=:questionText and c.testName=:testName and c.companyId=:companyId")
	QuestionMarkedForReview findByPrimaryKey2(@Param("email") String email, @Param("questionText") String quetionText,@Param("testName") String testName,  @Param("companyId") String companyId);
	
	
	@Query("SELECT count(c) FROM QuestionMarkedForReview c WHERE c.email=:email and c.testId=:testId and c.companyId=:companyId")
	Integer findMarkedQsForUserForTest(@Param("email") String email, @Param("testId") Long testId,  @Param("companyId") String companyId);
	
	@Query("SELECT c FROM QuestionMarkedForReview c WHERE c.email=:email and c.testId=:testId and c.companyId=:companyId")
	List<QuestionMarkedForReview> findMarkedQuestionsForUserForTest(@Param("email") String email, @Param("testId") Long testId,  @Param("companyId") String companyId);
	
	
}
