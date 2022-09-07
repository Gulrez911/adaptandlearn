package com.assessment.services.impl;

import java.util.Date;
import java.util.List;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.assessment.common.AssessmentGenericException;
import com.assessment.data.AdaptiveAssessmentLevelInstance;
import com.assessment.data.AdaptiveAssessmentSkillLevel;
import com.assessment.repositories.AdaptiveAssessmentLevelInstanceRepository;
import com.assessment.services.AdaptiveAssessmentLevelInstanceService;

@Service
@Transactional(noRollbackFor=AssessmentGenericException.class)
public class AdaptiveAssessmentLevelInstanceServiceImpl implements AdaptiveAssessmentLevelInstanceService {
	
	@Autowired
	AdaptiveAssessmentLevelInstanceRepository rep;

	@Override
	public AdaptiveAssessmentLevelInstance findAdaptiveAssessmentLevelInstance(String companyId,
			String adaptiveAssessmentName, String email, AdaptiveAssessmentSkillLevel level, Integer attempt) {
		// TODO Auto-generated method stub
		return rep.findUniqueAdaptiveAssessmentLevelInstance(companyId, adaptiveAssessmentName, email, level, attempt);
	}

	@Override
	@Transactional(noRollbackFor=AssessmentGenericException.class)
	public AdaptiveAssessmentLevelInstance saveOrUpdate(AdaptiveAssessmentLevelInstance instance) {
		// TODO Auto-generated method stub
		AdaptiveAssessmentLevelInstance instance2 = findAdaptiveAssessmentLevelInstance(instance.getCompanyId(), instance.getAdaptiveAssessmentName(), instance.getEmail(), instance.getLevel(), instance.getAttempt());
			if(instance2 == null){
				instance.setCreateDate(new Date());
				rep.save(instance);
				return instance;
			}
			else{
				instance.setCreateDate(instance2.getCreateDate());
				instance.setUpdateDate(new Date());
				instance.setId(instance2.getId());
				Mapper mapper = new DozerBeanMapper();
				mapper.map(instance, instance2);
				rep.save(instance2);
				return instance2;
			}
		
	}

	@Override
	public Integer findCountOfAdaptiveAssessmentLevelInstances(String companyId, String adaptiveAssessmentName,
			String email, AdaptiveAssessmentSkillLevel level) {
		// TODO Auto-generated method stub
		return rep.findCountOfAdaptiveAssessmentLevelInstances(companyId, adaptiveAssessmentName, email, level);
	}

	@Override
	public List<AdaptiveAssessmentLevelInstance> findAdaptiveAssessmentLevelInstances(String companyId,
			String adaptiveAssessmentName, String email, AdaptiveAssessmentSkillLevel level) {
		// TODO Auto-generated method stub
		return rep.findAdaptiveAssessmentLevelInstances(companyId, adaptiveAssessmentName, email, level);
	}

	@Override
	public AdaptiveAssessmentLevelInstance findUniqueAdaptiveAssessmentLevelInstanceLastAttempt(String companyId,
			String adaptiveAssessmentName, String email, AdaptiveAssessmentSkillLevel level) {
		List<AdaptiveAssessmentLevelInstance> list = rep.findUniqueAdaptiveAssessmentLevelInstanceLastAttempt(companyId, adaptiveAssessmentName, email, level, PageRequest.of(0,1));
		// TODO Auto-generated method stub
		if(list != null && list.size() == 0){
			return null;
		}
		return list.get(0);
	}

	@Override
	public List<AdaptiveAssessmentLevelInstance> findAllAdaptiveAssessmentLevelInstances(String companyId,
			String adaptiveAssessmentName, String email) {
		// TODO Auto-generated method stub
		return rep.findAllAdaptiveAssessmentLevelInstances(companyId, adaptiveAssessmentName, email);
	}

}
