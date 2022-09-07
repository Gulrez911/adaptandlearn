package com.assessment.services.impl;

import java.util.Date;
import java.util.List;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.assessment.data.QuestionMarkedForReview;
import com.assessment.repositories.QuestionMarkedForReviewRepository;
import com.assessment.services.QuestionMarkedForReviewService;
@Service
@Transactional
public class QuestionMarkedForReviewServiceImpl implements QuestionMarkedForReviewService{
	
	@Autowired
	QuestionMarkedForReviewRepository rep;

	@Override
	public QuestionMarkedForReview findByPrimaryKey(String email, Long questionId, Long testId, String companyId) {
		// TODO Auto-generated method stub
		return rep.findByPrimaryKey(email, questionId, testId, companyId);
	}

	@Override
	public QuestionMarkedForReview findByPrimaryKey2(String email, String quetionText, String testName,
			String companyId) {
		// TODO Auto-generated method stub
		return rep.findByPrimaryKey2(email, quetionText, testName, companyId);
	}

	@Override
	public QuestionMarkedForReview saveOrUpdate(QuestionMarkedForReview questionMarkedForReview) {
		// TODO Auto-generated method stub
		QuestionMarkedForReview questionMarkedForReview2 = findByPrimaryKey(questionMarkedForReview.getEmail(), questionMarkedForReview.getQuestionId(), questionMarkedForReview.getTestId(), questionMarkedForReview.getCompanyId());
			if(questionMarkedForReview2 == null){
				questionMarkedForReview.setCreateDate(new Date());
				return rep.save(questionMarkedForReview);
			}
			else{
				questionMarkedForReview.setCreateDate(questionMarkedForReview2.getCreateDate());
				questionMarkedForReview.setUpdateDate(new Date());
				questionMarkedForReview.setId(questionMarkedForReview2.getId());
				Mapper mapper = new DozerBeanMapper();
				mapper.map(questionMarkedForReview, questionMarkedForReview2);
				return rep.save(questionMarkedForReview2);
			}
	}

	@Override
	public Integer findMarkedQsForUserForTest(String email, Long testId, String companyId) {
		// TODO Auto-generated method stub
		return rep.findMarkedQsForUserForTest(email, testId, companyId);
	}

	@Override
	public List<QuestionMarkedForReview> findMarkedQuestionsForUserForTest(String email, Long testId,
			String companyId) {
		// TODO Auto-generated method stub
		return rep.findMarkedQuestionsForUserForTest(email, testId, companyId);
	}

}
