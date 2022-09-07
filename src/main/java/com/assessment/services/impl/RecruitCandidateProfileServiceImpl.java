package com.assessment.services.impl;

import java.util.Date;
import java.util.List;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.assessment.data.RecruitCandidateProfile;
import com.assessment.repositories.RecruitCandidateProfileRepository;
import com.assessment.services.RecruitCandidateProfileService;

@Service
@Transactional
public class RecruitCandidateProfileServiceImpl implements RecruitCandidateProfileService{

	@Autowired
	RecruitCandidateProfileRepository rep;
	
	@Override
	public RecruitCandidateProfile findByPrimaryKey(Long jobdescId, String candidateEmail, String companyId) {
		// TODO Auto-generated method stub
		return rep.findByPrimaryKey(jobdescId, candidateEmail, companyId);
	}

	@Override
	public RecruitCandidateProfile saveOrUpdate(RecruitCandidateProfile candidateProfile) {
		// TODO Auto-generated method stub
		RecruitCandidateProfile candidateProfile2 = findByPrimaryKey(candidateProfile.getJobDescriptionId(), candidateProfile.getEmail(), candidateProfile.getCompanyId());
			if(candidateProfile2 == null){
				candidateProfile.setCreateDate(new Date());
				return rep.save(candidateProfile);
				
			}
			else{
				candidateProfile.setId(candidateProfile2.getId());
				candidateProfile.setCreateDate(candidateProfile2.getCreateDate());
				candidateProfile.setUpdateDate(new Date());
				Mapper mapper = new DozerBeanMapper();
				mapper.map(candidateProfile, candidateProfile2);
				return rep.save(candidateProfile2);
			}
	}

	@Override
	public List<RecruitCandidateProfile> findByJobDescAndCompanyId(Long jid, String companyId) {
		// TODO Auto-generated method stub
		return rep.findByJobDescriptionIdAndCompanyId(jid, companyId);
	}

	@Override
	public RecruitCandidateProfile findByEmailAndCompanyId(String candidateEmail, String companyId) {
		// TODO Auto-generated method stub
		return rep.findByEmailAndCompanyId(candidateEmail, companyId);
	}

}
