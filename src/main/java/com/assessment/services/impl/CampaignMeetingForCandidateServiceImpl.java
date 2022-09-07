package com.assessment.services.impl;

import java.util.Date;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.assessment.data.CampaignMeetingForCandidate;
import com.assessment.repositories.CampaignMeetingForCandidateRepository;
import com.assessment.services.CampaignMeetingForCandidateService;
@Service
@Transactional
public class CampaignMeetingForCandidateServiceImpl implements CampaignMeetingForCandidateService{

	@Autowired
	CampaignMeetingForCandidateRepository rep;
	
	@Override
	public CampaignMeetingForCandidate findUniqueCampaignMeetingForCandidate(String companyId, String campaignName,
			String campaignTestName, String candidateEmail) {
		// TODO Auto-generated method stub
		return rep.findUniqueCampaignMeetingForCandidate(companyId, campaignName, campaignTestName, candidateEmail);
	}

	@Override
	public CampaignMeetingForCandidate saveOrUpdate(CampaignMeetingForCandidate campaignMeetingForCandidate) {
		// TODO Auto-generated method stub
		CampaignMeetingForCandidate campaignMeetingForCandidate2 = findUniqueCampaignMeetingForCandidate(campaignMeetingForCandidate.getCompanyId(), campaignMeetingForCandidate.getCampaignName(), campaignMeetingForCandidate.getCampaignTestName(), campaignMeetingForCandidate.getCandidateEmail());
			if(campaignMeetingForCandidate2 == null){
				campaignMeetingForCandidate.setCreateDate(new Date());
				return rep.save(campaignMeetingForCandidate);
			}
			else{
				Mapper mapper = new DozerBeanMapper();
				campaignMeetingForCandidate.setId(campaignMeetingForCandidate2.getId());
				campaignMeetingForCandidate.setCreateDate(campaignMeetingForCandidate2.getCreateDate());
				campaignMeetingForCandidate.setUpdateDate(new Date());
				mapper.map(campaignMeetingForCandidate, campaignMeetingForCandidate2);
				return rep.save(campaignMeetingForCandidate2);
			}
			
	}

}
