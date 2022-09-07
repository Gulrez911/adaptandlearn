package com.assessment.services.impl;

import java.util.Date;
import java.util.List;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.assessment.data.JobDescriptionRecruiter;
import com.assessment.repositories.JobDescriptionRecruiterRepository;
import com.assessment.services.JobDescriptionRecruiterService;

@org.springframework.stereotype.Service
@Transactional
public class JobDescriptionRecruiterServiceImpl implements JobDescriptionRecruiterService{

	@Autowired
	JobDescriptionRecruiterRepository descriptionRecruiterRepository;
	
	@Override
	public JobDescriptionRecruiter findUniqueJobDescriptionRecruiter(String companyId, Long jobDescriptionId, String email) {
		// TODO Auto-generated method stub
		return descriptionRecruiterRepository.findUniqueJobDescriptionRecruiter(companyId, jobDescriptionId, email);
	}
	
	@Override
	public void saveOrUpdate(JobDescriptionRecruiter descriptionRecruiter) {
//		JobDescriptionRecruiter jobDescriptionRecruiter = descriptionRecruiterRepository.findByPrimaryKey(descriptionRecruiter.getJobDescriptionName(), descriptionRecruiter.getCompanyId());
		JobDescriptionRecruiter jobDescriptionRecruiter = findUniqueJobDescriptionRecruiter(descriptionRecruiter.getCompanyId(),descriptionRecruiter.getId(),descriptionRecruiter.getEmail());
		
		if(jobDescriptionRecruiter == null){
			descriptionRecruiter.setCreateDate(new Date());
			descriptionRecruiterRepository.save(descriptionRecruiter);
		}
		else{
			descriptionRecruiter.setId(jobDescriptionRecruiter.getId());
			descriptionRecruiter.setUpdateDate(new Date());
			Mapper mapper = new DozerBeanMapper();
			mapper.map(descriptionRecruiter, jobDescriptionRecruiter);
			descriptionRecruiterRepository.save(jobDescriptionRecruiter);
//			return campaignCandidate2;
		}
			 
		
	}

	@Override
	public List<JobDescriptionRecruiter> findByEmailAndCompanyId(String companyId, String email) {
		// TODO Auto-generated method stub
		return descriptionRecruiterRepository.findByEmailAndCompanyId(email, companyId);
	}

	

}
