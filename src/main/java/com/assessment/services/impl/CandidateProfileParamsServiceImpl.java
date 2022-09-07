package com.assessment.services.impl;

import java.util.Date;
import java.util.List;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.assessment.data.CandidateProfileParams;
import com.assessment.repositories.CandidateProfileParamsRepository;
import com.assessment.services.CandidateProfileParamsService;

@Service
@Transactional
public class CandidateProfileParamsServiceImpl implements CandidateProfileParamsService{

	@Autowired
	CandidateProfileParamsRepository repository;
	
	@Override
	public CandidateProfileParams findUniqueCandidateProfileParams(String companyId, String qualifier1,
			String qualifier2, String qualifier3, String qualifier4, String qualifier5) {
		// TODO Auto-generated method stub
		return repository.findUniqueCandidateProfileParams(companyId, qualifier1, qualifier2, qualifier3, qualifier4, qualifier5);
	}

	@Override
	public void saveOrUpdate(CandidateProfileParams candidateProfileParams) {
		// TODO Auto-generated method stub
//		if(candidateProfileParams.getQualifier1() != null){
//			candidateProfileParams.setQualifier1(candidateProfileParams.getQualifier1().trim());
//		}
//		if(candidateProfileParams.getQualifier2() != null){
//			candidateProfileParams.setQualifier2(candidateProfileParams.getQualifier2().trim());
//		}
//		if(candidateProfileParams.getQualifier3() != null){
//			candidateProfileParams.setQualifier3(candidateProfileParams.getQualifier3().trim());
//		}
//		if(candidateProfileParams.getQualifier4() != null){
//			candidateProfileParams.setQualifier4(candidateProfileParams.getQualifier4().trim());
//		}
//		if(candidateProfileParams.getQualifier5() != null){
//			candidateProfileParams.setQualifier5(candidateProfileParams.getQualifier5().trim());
//		}
		CandidateProfileParams candidateProfileParams2 = findUniqueCandidateProfileParams(candidateProfileParams.getCompanyId(), candidateProfileParams.getQualifier1(), candidateProfileParams.getQualifier2(), candidateProfileParams.getQualifier3(), candidateProfileParams.getQualifier4(), candidateProfileParams.getQualifier5());
		if(candidateProfileParams2 == null){
			candidateProfileParams.setCreateDate(new Date());
			repository.save(candidateProfileParams);
		}
		else{
			Mapper mapper = new DozerBeanMapper();
			candidateProfileParams.setUpdateDate(new Date());
			candidateProfileParams.setId(candidateProfileParams2.getId());
			mapper.map(candidateProfileParams, candidateProfileParams2);
			repository.save(candidateProfileParams2);
		}
	}

	@Override
	public List<CandidateProfileParams> findCandidateProfileParamsByCompanyId(String companyId) {
		// TODO Auto-generated method stub
		return repository.findCandidateProfileParamsByCompanyId(companyId);
	}

}
