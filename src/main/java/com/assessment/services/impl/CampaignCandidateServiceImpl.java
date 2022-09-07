package com.assessment.services.impl;

import java.io.File;
import java.util.Date;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.assessment.common.util.EmailGenericMessageThread;
import com.assessment.data.CampaignCandidate;
import com.assessment.repositories.CampaignCandidateRepository;
import com.assessment.services.CampaignCandidateService;
@Service
@Transactional
public class CampaignCandidateServiceImpl implements CampaignCandidateService{
	@Autowired
	CampaignCandidateRepository rep;
	
	
	@Override
	public CampaignCandidate findUniqueCampaignCandidate(String companyId, String campaignName, String email) {
		// TODO Auto-generated method stub
		return rep.findUniqueCampaignCandidate(companyId, campaignName, email);
	}

	@Override
	public List<CampaignCandidate> getCampaignCandidatesForCampaignMeetingByCompany(String companyId,
			String campaignName) {
		// TODO Auto-generated method stub
		return rep.getCampaignCandidatesForCampaignMeetingByCompany(companyId, campaignName);
	}

	@Override
	public CampaignCandidate saveOrUpdate(CampaignCandidate campaignCandidate) {
		// TODO Auto-generated method stub
		CampaignCandidate campaignCandidate2 = findUniqueCampaignCandidate(campaignCandidate.getCompanyId(), campaignCandidate.getCampaignName(), campaignCandidate.getEmail());
			if(campaignCandidate2 == null){
				campaignCandidate.setCreateDate(new Date());
				rep.save(campaignCandidate);
				return campaignCandidate;
			}
			else{
				campaignCandidate.setId(campaignCandidate2.getId());
				campaignCandidate.setUpdateDate(new Date());
				Mapper mapper = new DozerBeanMapper();
				mapper.map(campaignCandidate, campaignCandidate2);
				rep.save(campaignCandidate2);
				return campaignCandidate2;
			}
			
			
	}

	@Override
	public void deleteCampaignCandidate(Long cid) {
		// TODO Auto-generated method stub
		rep.deleteById(cid);
	}

	@Override
	public List<CampaignCandidate> findByCampaignNameAndCompanyId(String campaignName, String companyId) {
		// TODO Auto-generated method stub
		return rep.findByCampaignNameAndCompanyId(campaignName, companyId);
	}

	@Override
	public CampaignCandidate findCampaignCandidate(String companyId, String campaignName, String email) {
		// TODO Auto-generated method stub
		return rep.findCampaignCandidate(companyId, campaignName, email);
	}

}
