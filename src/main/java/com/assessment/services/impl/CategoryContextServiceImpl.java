package com.assessment.services.impl;

import java.util.Date;
import java.util.List;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.assessment.data.CategoryContext;
import com.assessment.repositories.CategoryContextRepository;
import com.assessment.services.AssessmentUploadDataService;
import com.assessment.services.CategoryContextService;

@Service
@Transactional
public class CategoryContextServiceImpl implements CategoryContextService{
	
	@Autowired
	CategoryContextRepository rep;
	
	@Autowired
	AssessmentUploadDataService assessmentUploadDataService;

	@Override
	public CategoryContext findUniqueByAssessmentUploadDataName(String companyId, String level1, String level2,
			String level3, String level4, String assessmentUpoloadDataName) {
		// TODO Auto-generated method stub
		return rep.findUniqueByAssessmentUploadDataName(companyId, level1, level2, level3, level4, assessmentUpoloadDataName);
	}

	@Override
	public CategoryContext findUniqueByAssessmentName(String companyId, String level1, String level2, String level3,
			String level4, String assessmentName) {
		// TODO Auto-generated method stub
		return rep.findUniqueByAssessmentName(companyId, level1, level2, level3, level4, assessmentName);
	}

	@Override
	public List<CategoryContext> findAssessmentUploadDatasForLevel(String companyId, String level1, String level2,
			String level3, String level4) {
		// TODO Auto-generated method stub
		return rep.findAssessmentsForLevel(companyId, level1, level2, level3, level4);
	}

	@Override
	public List<CategoryContext> findAssessmentsForLevel(String companyId, String level1, String level2, String level3,
			String level4) {
		// TODO Auto-generated method stub
		return rep.findAssessmentsForLevel(companyId, level1, level2, level3, level4);
	}

	@Override
	public CategoryContext saveOrUpdate(CategoryContext context) {
		// TODO Auto-generated method stub
		if(context.getLevel2() == null || context.getLevel2().trim().length() == 0){
			context.setLevel2("###");
			context.setLevel3("###");
			context.setLevel4("###");
		}
		else if( context.getLevel3() == null || context.getLevel3().trim().length() == 0){
			context.setLevel3("###");
			context.setLevel4("###");
		}
		else if( context.getLevel4() == null || context.getLevel4().trim().length() == 0){
			context.setLevel4("###");
		}
		
		CategoryContext categoryContext2 = null;
		
		if(context.getCollective()){
			//check if assessmentuploaddata record exists . do it in controller
			//String jobDescName = context.getAssessmentUpoloadDataName();
			//assessmentUploadDataService.findByPrimaryKey(context.getCompanyId()	, context.getDomain(), context.getAssessmentUpoloadDataName());
			categoryContext2 = findUniqueByAssessmentUploadDataName(context.getCompanyId(), context.getLevel1(), context.getLevel2(), context.getLevel3(), context.getLevel4(), context.getAssessmentUpoloadDataName());
		}
		else{
		//check if assessment record exists
			categoryContext2 = findUniqueByAssessmentName(context.getCompanyId(), context.getLevel1(), context.getLevel2(), context.getLevel3(), context.getLevel4(), context.getAssessmentName());
		}
		
		if(categoryContext2 == null){
			//create
			context.setCreateDate(new Date());
			return rep.save(context);
		}
		else{
			Mapper mapper = new DozerBeanMapper();
			context.setId(categoryContext2.getId());
			context.setCreateDate(categoryContext2.getCreateDate());
			context.setUpdateDate(new Date());
			mapper.map(context, categoryContext2);
			return rep.save(categoryContext2);
		}
		
	}

	

}
