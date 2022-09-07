package com.assessment.services.impl;

import java.util.Date;
import java.util.List;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.assessment.data.ReviewerCampaignMapping;
import com.assessment.repositories.ReviewerCampaignMappingRepository;
import com.assessment.services.ReviewerCampaignMappingService;

@Service
@Transactional
public class ReviewerCampaignMappingServiceImpl implements  ReviewerCampaignMappingService{

	@Autowired
	ReviewerCampaignMappingRepository rep;

	@Override
	public ReviewerCampaignMapping findUniqueReviewerCampaignMapping(String companyId, String email,
			String campaignName) {
		// TODO Auto-generated method stub
		return rep.findUniqueReviewerCampaignMapping(companyId, email, campaignName);
	}

	@Override
	public List<ReviewerCampaignMapping> getReviewerCampaignMapping(String companyId, String campaignName) {
		// TODO Auto-generated method stub
		return rep.getReviewerCampaignMapping(companyId, campaignName);
	}

	@Override
	public ReviewerCampaignMapping saveOrUpdate(ReviewerCampaignMapping reviewerCampaignMapping) {
		ReviewerCampaignMapping reviewerCampaignMapping2 = findUniqueReviewerCampaignMapping(reviewerCampaignMapping.getCompanyId(), reviewerCampaignMapping.getEmail(), reviewerCampaignMapping.getCampaignName());
				if(reviewerCampaignMapping2 == null){
					reviewerCampaignMapping.setCreateDate(new Date());
					return rep.save(reviewerCampaignMapping);
				}
				else{
					reviewerCampaignMapping.setId(reviewerCampaignMapping2.getId());
					reviewerCampaignMapping.setCreateDate(reviewerCampaignMapping2.getCreateDate());
					Mapper mapper = new DozerBeanMapper();
					mapper.map(reviewerCampaignMapping, reviewerCampaignMapping2);
					return rep.save(reviewerCampaignMapping2);
				}
				
	}

	@Override
	public void deleteReviewerCampaignMapping(Long id) {
		// TODO Auto-generated method stub
		rep.deleteById(id);
	}

	@Override
	public ReviewerCampaignMapping findReviewerCampaignMappingById(Long id) {
		// TODO Auto-generated method stub
		return rep.findById(id).get();
	}
	
	

}
