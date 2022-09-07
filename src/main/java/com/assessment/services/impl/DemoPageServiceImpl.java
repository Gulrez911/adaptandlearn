package com.assessment.services.impl;

import java.util.Date;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.assessment.data.AssessmentUploadData;
import com.assessment.data.DemoPage;
import com.assessment.repositories.DemoPageRepository;
import com.assessment.services.AssessmentUploadDataService;
import com.assessment.services.DemoPageService;

@Service
@Transactional
public class DemoPageServiceImpl implements DemoPageService{
	
	@Autowired
	DemoPageRepository rep;
	
	@Autowired
	AssessmentUploadDataService dataService;

	@Override
	public DemoPage findByPrimaryKey(String pageName, String companyId) {
		// TODO Auto-generated method stub
		return rep.findByPrimaryKey(pageName, companyId);
	}
	
	private void resolveAssessmentUploadData(DemoPage demoPage){
		for(AssessmentUploadData data : demoPage.getColumn1()){
			data = dataService.findByPrimaryKey(data.getCompanyId(), data.getDomain(), data.getAdaptiveAssessmentName());
		}
		
		for(AssessmentUploadData data : demoPage.getColumn2()){
			data = dataService.findByPrimaryKey(data.getCompanyId(), data.getDomain(), data.getAdaptiveAssessmentName());
		}
		
		for(AssessmentUploadData data : demoPage.getColumn3()){
			data = dataService.findByPrimaryKey(data.getCompanyId(), data.getDomain(), data.getAdaptiveAssessmentName());
		}
		
		for(AssessmentUploadData data : demoPage.getColumn4()){
			data = dataService.findByPrimaryKey(data.getCompanyId(), data.getDomain(), data.getAdaptiveAssessmentName());
		}
		
		for(AssessmentUploadData data : demoPage.getColumn5()){
			data = dataService.findByPrimaryKey(data.getCompanyId(), data.getDomain(), data.getAdaptiveAssessmentName());
		}
	}

	@Override
	public DemoPage saveOrUpdate(DemoPage demoPage) {
		// TODO Auto-generated method stub
		DemoPage demoPage2 = findByPrimaryKey(demoPage.getPageName(), demoPage.getCompanyId());
			if(demoPage2 == null){
				demoPage.setCreateDate(new Date());
				resolveAssessmentUploadData(demoPage);
				return rep.save(demoPage);
			}
			else{
				demoPage.setCreateDate(demoPage2.getCreateDate());
				demoPage.setUpdateDate(new Date());
				demoPage.setId(demoPage2.getId());
				resolveAssessmentUploadData(demoPage2);
				demoPage.setColumn1(demoPage2.getColumn1());
				demoPage.setColumn2(demoPage2.getColumn2());
				demoPage.setColumn3(demoPage2.getColumn3());
				demoPage.setColumn4(demoPage2.getColumn4());
				demoPage.setColumn5(demoPage2.getColumn5());
				
				Mapper mapper = new DozerBeanMapper();
				mapper.map(demoPage, demoPage2);
				return rep.save(demoPage2);
			}
	}

}
