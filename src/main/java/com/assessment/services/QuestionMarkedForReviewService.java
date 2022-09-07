package com.assessment.services;

import java.util.List;

import com.assessment.data.QuestionMarkedForReview;

public interface QuestionMarkedForReviewService {

	
	QuestionMarkedForReview findByPrimaryKey(String email, Long questionId,Long testId,  String companyId);
	
	QuestionMarkedForReview findByPrimaryKey2( String email, String quetionText, String testName,   String companyId);
	
	QuestionMarkedForReview saveOrUpdate(QuestionMarkedForReview questionMarkedForReview);
	
	Integer findMarkedQsForUserForTest(String email,  Long testId,  String companyId);
	
	List<QuestionMarkedForReview> findMarkedQuestionsForUserForTest(String email,  Long testId,  String companyId);
	
}
