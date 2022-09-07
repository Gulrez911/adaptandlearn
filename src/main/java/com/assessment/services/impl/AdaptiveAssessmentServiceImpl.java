package com.assessment.services.impl;

import java.util.Date;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.assessment.data.AdaptiveAssessment;
import com.assessment.repositories.AdaptiveAssessmentRepository;
import com.assessment.services.AdaptiveAssessmentService;

@Service
@Transactional
public class AdaptiveAssessmentServiceImpl implements AdaptiveAssessmentService {
	
	@Autowired
	AdaptiveAssessmentRepository rep;

	@Override
	public AdaptiveAssessment findUniqueAdaptiveAssessment(String companyId, String adaptiveAssessmentName) {
		// TODO Auto-generated method stub
		return rep.findUniqueAdaptiveAssessment(companyId, adaptiveAssessmentName);
	}

	@Override
	public AdaptiveAssessment saveOrUpdate(AdaptiveAssessment assessment) {
		// TODO Auto-generated method stub
		AdaptiveAssessment assessment2 = findUniqueAdaptiveAssessment(assessment.getCompanyId(), assessment.getAdaptiveAssessmentName());
			if(assessment2 == null){
				assessment.setCreateDate(new Date());
				return rep.save(assessment);
			}
			else{
				assessment.setId(assessment2.getId());
				assessment.setCreateDate(assessment2.getCreateDate());
				assessment.setUpdateDate(new Date());
				Mapper mapper = new DozerBeanMapper();
				mapper.map(assessment, assessment2);
				return rep.save(assessment2);
			}
	}

}
