package com.assessment.services;

import com.assessment.data.ReviewerComment;

public interface ReviewerCommentService {
	public void saveOrUpdate(ReviewerComment comment);

	public ReviewerComment findByPrimaryKey(String campaignName, String candidateEmail, String compId);
}
