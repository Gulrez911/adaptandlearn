package com.assessment.services.impl;

import java.util.Date;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.assessment.data.CampaignReviewer;
import com.assessment.data.CandidateCampaignSchedule;
import com.assessment.repositories.CandidateCampaignScheduleRepository;
import com.assessment.services.CandidateCampaignScheduleService;

@Service
@Transactional
public class CandidateCampaignScheduleServiceImpl implements CandidateCampaignScheduleService {

	@Autowired
	CandidateCampaignScheduleRepository campaignScheduleRepository;

	@Override
	public CandidateCampaignSchedule findByEmail(String companyId, String email) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CandidateCampaignSchedule saveOrUpdate(CandidateCampaignSchedule campaignSchedule) {
		// TODO Auto-generated method stub
		CandidateCampaignSchedule campaignSchedule2 = campaignScheduleRepository.findByEmailAndCampaignNameAndCompanyId(campaignSchedule.getEmail(),
									campaignSchedule.getCampaignName(), campaignSchedule.getCompanyId());
		if (campaignSchedule2 == null) {
//			campaignReviewer.setCreateDate(new Date());
			campaignScheduleRepository.save(campaignSchedule);
			return campaignSchedule;
		} else {
			// update
			Mapper mapper = new DozerBeanMapper();
			campaignSchedule.setId(campaignSchedule2.getId());
			mapper.map(campaignSchedule, campaignSchedule2);
			campaignScheduleRepository.save(campaignSchedule2);
			return campaignSchedule2;
		}
	}

	@Override
	public CandidateCampaignSchedule getById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CandidateCampaignSchedule findByEmailAndCampaignName(String email, String campName, String companyId) {
		// TODO Auto-generated method stub
		return campaignScheduleRepository.findByEmailAndCampaignNameAndCompanyId(email, campName, companyId);
	}

}
