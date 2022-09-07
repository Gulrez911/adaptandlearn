package com.assessment.services.impl;

import java.util.Date;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.assessment.common.AssessmentGenericException;
import com.assessment.data.AdaptiveAssessmentInstance;
import com.assessment.repositories.AdaptiveAssessmentInstanceRepository;
import com.assessment.services.AdaptiveAssessmentInstanceService;

@Service
@Transactional(noRollbackFor=AssessmentGenericException.class)
public class AdaptiveAssessmentInstanceServiceImpl implements AdaptiveAssessmentInstanceService{
	
	@Autowired
	AdaptiveAssessmentInstanceRepository rep;

	@Override
	public AdaptiveAssessmentInstance findAdaptiveAssessmentInstance(String testName, String companyId, String email) {
		// TODO Auto-generated method stub
		return rep.findUniqueAdaptiveAssessmentInstance(companyId, testName, email);
	}

	@Override
	@Transactional(noRollbackFor=AssessmentGenericException.class)
	public AdaptiveAssessmentInstance saveOrUpdate(AdaptiveAssessmentInstance instance) {
		// TODO Auto-generated method stub
		AdaptiveAssessmentInstance instance2 = findAdaptiveAssessmentInstance(instance.getAdaptiveAssessmentName(), instance.getCompanyId(), instance.getEmail());
			if(instance2 == null){
				instance.setCreateDate(new Date());
				return rep.save(instance);
			}
			else{
				Mapper mapper = new DozerBeanMapper();
				instance.setId(instance2.getId());
				instance.setCreateDate(instance2.getCreateDate());
				mapper.map(instance, instance2);
				return rep.save(instance);
			}
	}

}
