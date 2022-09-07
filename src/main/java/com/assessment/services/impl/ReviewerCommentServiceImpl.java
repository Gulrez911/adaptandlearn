package com.assessment.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.assessment.data.ReviewerComment;
import com.assessment.repositories.ReviewerCommentRepository;
import com.assessment.services.ReviewerCommentService;

@Service
@Transactional
public class ReviewerCommentServiceImpl implements ReviewerCommentService {

	@Autowired
	ReviewerCommentRepository reviewerCommentRepository;

	@Override
	public void saveOrUpdate(ReviewerComment comment) {
		// TODO Auto-generated method stub
		reviewerCommentRepository.save(comment);
	}

	@Override
	public ReviewerComment findByPrimaryKey(String campaignName, String candidateEmail, String compId) {
		// TODO Auto-generated method stub
		return reviewerCommentRepository.findByPrimaryKey(campaignName, candidateEmail, compId);
	}

}
