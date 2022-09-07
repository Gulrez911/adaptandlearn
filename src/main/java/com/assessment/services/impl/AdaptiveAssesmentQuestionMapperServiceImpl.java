package com.assessment.services.impl;

import java.util.Date;

import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.assessment.common.AssessmentGenericException;
import com.assessment.data.AdaptiveAssessmentQuestionMapper;
import com.assessment.data.AdaptiveAssessmentSkillLevel;
import com.assessment.repositories.AdaptiveAssesmentQuestionMapperRepository;
import com.assessment.services.AdaptiveAssesmentQuestionMapperService;
@Service
@Transactional(noRollbackFor=AssessmentGenericException.class)
public class AdaptiveAssesmentQuestionMapperServiceImpl implements AdaptiveAssesmentQuestionMapperService {
	@Autowired
	AdaptiveAssesmentQuestionMapperRepository rep;
	
	@Override
	public AdaptiveAssessmentQuestionMapper findUniqueAdaptiveAssessmentQuestionMapper(String companyId,
			 String qualifier1, String qualifier2, String qualifier3,
			String qualifier4, String qualifier5, AdaptiveAssessmentSkillLevel level) {
		// TODO Auto-generated method stub
		return rep.findUniqueAdaptiveAssessmentQuestionMapper(companyId,  qualifier1, qualifier2, qualifier3, qualifier4, qualifier5, level);
	}

	@Override
	public AdaptiveAssessmentQuestionMapper saveOrUpdate(AdaptiveAssessmentQuestionMapper mapper) {
		// TODO Auto-generated method stub
		AdaptiveAssessmentQuestionMapper mapper2 = findUniqueAdaptiveAssessmentQuestionMapper(mapper.getCompanyId(), mapper.getQualifier1(), mapper.getQualifier2(), mapper.getQualifier3(), mapper.getQualifier4(), mapper.getQualifier5(), mapper.getLevel());
			if(mapper2 == null){
				mapper.setCreateDate(new Date());
				rep.save(mapper);
				return mapper;
			}
			else{
				mapper.setCreateDate(mapper2.getCreateDate());
				rep.delete(mapper2);
				mapper.setUpdateDate(new Date());
				rep.save(mapper);
//				mapper.setCreateDate(mapper2.getCreateDate());
//				mapper.setUpdateDate(new Date());
//				mapper.setId(mapper2.getId());
////				mapper2.getQuestions().clear();
////				mapper2.setQuestions(mapper.getQuestions());
//				DozerBeanMapper beanMapper = new DozerBeanMapper();
//				beanMapper.map(mapper, mapper2);
//				rep.save(mapper2);
				return mapper;
			}
		
	}

}
