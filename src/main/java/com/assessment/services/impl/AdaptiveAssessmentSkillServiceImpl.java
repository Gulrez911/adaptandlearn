package com.assessment.services.impl;

import java.util.Date;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.assessment.data.AdaptiveAssessmentSkill;
import com.assessment.data.AdaptiveAssessmentSkillLevel;
import com.assessment.repositories.AdaptiveAssessmentSkillRepository;
import com.assessment.services.AdaptiveAssessmentSkillService;
@Service
@Transactional
public class AdaptiveAssessmentSkillServiceImpl implements AdaptiveAssessmentSkillService{
	
	@Autowired
	AdaptiveAssessmentSkillRepository rep;
	
	
	@Override
	public AdaptiveAssessmentSkill findUniqueAdaptiveAssessmentSkill(String companyId, String skillName,
			String adaptiveAssessmentName, String qualifier1, String qualifier2, String qualifier3, String qualifier4,
			String qualifier5) {
		// TODO Auto-generated method stub
		return rep.findUniqueAdaptiveAssessmentSkill(companyId, skillName, adaptiveAssessmentName, qualifier1, qualifier2, qualifier3, qualifier4, qualifier5);
	}

	@Override
	public AdaptiveAssessmentSkill findUniqueAdaptiveAssessmentSkillByQualifier(String companyId,
			String adaptiveAssessmentName, String qualifier1, String qualifier2, String qualifier3, String qualifier4,
			String qualifier5) {
		// TODO Auto-generated method stub
		return rep.findUniqueAdaptiveAssessmentSkillByQualifier(companyId, adaptiveAssessmentName, qualifier1, qualifier2, qualifier3, qualifier4, qualifier5);
	}

	@Override
	public AdaptiveAssessmentSkill saveOrUpdate(AdaptiveAssessmentSkill adaptiveAssessmentSkill) {
		// TODO Auto-generated method stub
		AdaptiveAssessmentSkill adaptiveAssessmentSkill2 = findUniqueLevelForAdaptiveAssessment(adaptiveAssessmentSkill.getCompanyId(), adaptiveAssessmentSkill.getAdaptiveAssessmentName(), adaptiveAssessmentSkill.getStartingLevel());
			if(adaptiveAssessmentSkill2 == null){
				adaptiveAssessmentSkill.setCreateDate(new Date());
				rep.save(adaptiveAssessmentSkill);
				return adaptiveAssessmentSkill;
			}
			else{
				adaptiveAssessmentSkill.setId(adaptiveAssessmentSkill2.getId());
				adaptiveAssessmentSkill.setCreateDate(adaptiveAssessmentSkill2.getCreateDate());
				adaptiveAssessmentSkill.setUpdateDate(new Date());
				Mapper mapper = new DozerBeanMapper();
				mapper.map(adaptiveAssessmentSkill, adaptiveAssessmentSkill2);
				rep.save(adaptiveAssessmentSkill2);
				return adaptiveAssessmentSkill2;
			}
		
	}

	@Override
	public AdaptiveAssessmentSkill findUniqueLevelForAdaptiveAssessment(String companyId, String adaptiveAssessmentName,
			AdaptiveAssessmentSkillLevel level) {
		// TODO Auto-generated method stub
		return rep.findUniqueLevelForAdaptiveAssessment(companyId, adaptiveAssessmentName, level);
	}

}
