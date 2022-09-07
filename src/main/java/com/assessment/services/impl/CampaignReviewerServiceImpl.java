package com.assessment.services.impl;

import java.util.Date;
import java.util.List;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.assessment.data.CampaignReviewer;
import com.assessment.repositories.CampaignReviewerRepository;
import com.assessment.services.CampaignReviewerService;
@Service
@Transactional
public class CampaignReviewerServiceImpl implements CampaignReviewerService{
	
	@Autowired
	CampaignReviewerRepository rep;
	
	

	@Override
	public CampaignReviewer findByEmail(String companyId, String email) {
		// TODO Auto-generated method stub
		return rep.findUniqueCampaignReviewer(companyId, email);
	}

	@Override
	public CampaignReviewer saveOrUpdate(CampaignReviewer campaignReviewer) {
		// TODO Auto-generated method stub
		CampaignReviewer campaignReviewer2 = findByEmail(campaignReviewer.getCompanyId(), campaignReviewer.getEmail());
		if(campaignReviewer2 == null){
			campaignReviewer.setCreateDate(new Date());
			rep.save(campaignReviewer);
			return campaignReviewer;
		}
		else{
			//update
			Mapper mapper = new DozerBeanMapper();
			campaignReviewer.setId(campaignReviewer2.getId());
			campaignReviewer.setUpdateDate(new Date());
			mapper.map(campaignReviewer, campaignReviewer2);
			rep.save(campaignReviewer2);
			return campaignReviewer2;
		}
		
	}

	@Override
	public List<CampaignReviewer> searchCampaignReviewersWithoutPagination(String companyId, String search) {
		// TODO Auto-generated method stub
		return rep.searchCampaignReviewersWithoutPagination(companyId, search);
	}

	@Override
	public CampaignReviewer getById(Long id) {
		// TODO Auto-generated method stub
		return rep.findById(id).get();
	}

}
