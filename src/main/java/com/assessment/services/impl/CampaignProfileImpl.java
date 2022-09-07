package com.assessment.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.assessment.data.CampaignProfile;
import com.assessment.repositories.CampaignProfileRepository;
import com.assessment.services.CampaignProfileService;

@Service
@Transactional
public class CampaignProfileImpl implements CampaignProfileService {

	@Autowired
	CampaignProfileRepository profileRepository;

	@Override
	public void saveOrUpdate(List<CampaignProfile> profiles) {
		if(profiles.size()>0) {
			profileRepository.deleteByUserEmail(profiles.get(0).getUserEmail());
		}
		for (CampaignProfile li : profiles) {
			profileRepository.save(li);
		}
	}

	@Override
	public void deleteByUserEmail(String userEmail) {
		// TODO Auto-generated method stub
		
	}

}
