package com.assessment.services.impl;

import java.util.Date;
import java.util.List;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.assessment.data.AssessmentUploadData;
import com.assessment.repositories.AssessmentUploadDataRepository;
import com.assessment.services.AssessmentUploadDataService;
@Service
@Transactional
public class AssessmentUploadDataServiceImpl implements AssessmentUploadDataService{
	
	@Autowired
	AssessmentUploadDataRepository rep;

	@Override
	public AssessmentUploadData findByPrimaryKey(String companyId, String domain, String adaptiveAssessmentName) {
		// TODO Auto-generated method stub
		return rep.findByPrimaryKey(companyId, domain, adaptiveAssessmentName);
	}

	@Override
	public List<AssessmentUploadData> findAssessmentsByDomain(String companyId, String domain) {
		// TODO Auto-generated method stub
		return rep.findAssessmentsByDomain(companyId, domain);
	}

	@Override
	public AssessmentUploadData saveOrUpdate(AssessmentUploadData data) {
		// TODO Auto-generated method stub
		AssessmentUploadData data2 = findByPrimaryKey(data.getCompanyId(), data.getDomain(), data.getAdaptiveAssessmentName());
		if(data2 == null){
			data.setCreateDate(new Date());
			return rep.save(data);
		}
		else{
			data.setId(data2.getId());
			data.setUpdateDate(new Date());
			data.setCreateDate(data2.getCreateDate());
			data.setId(data2.getId());
			Mapper mapper = new DozerBeanMapper();
			mapper.map(data, data2);
			return rep.save(data2);
		}
	}

}
