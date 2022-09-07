package com.test;

import java.util.List;
import java.util.Set;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.assessment.common.util.NavigationConstants;
import com.assessment.data.RecruitCandidateProfile;
import com.assessment.repositories.RecruitCandidateProfileRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:appContext.xml"})
@Transactional
public class TestRecruitmentProfile {

	@Autowired
	RecruitCandidateProfileRepository recruitCandidateProfileRepository;
	
	@Test
	public void testFetchAllCandidates(){
		List<RecruitCandidateProfile> profiles = recruitCandidateProfileRepository.getAllProfiles("IH", "ASC",  "", PageRequest.of(0, NavigationConstants.NO_RECRUITER_PAGE)).getContent();
		System.out.println(profiles.size());
		for(RecruitCandidateProfile p : profiles){
			System.out.println(p.getDateWhenFirstApplied() +" - "+p.getEmail()+" "+p.getRecruiterEmail()+" "+p.getFirstName()+" "+p.getLastName()+" "+p.getJobDescriptionName());
		}
	}
	
	
	@Test
	public void testFetchAllCandidatesForJobdesc(){
		List<RecruitCandidateProfile> profiles = recruitCandidateProfileRepository.findByJobDescriptionIdAndCompanyId(10624l, "IH");
		System.out.println(profiles.size());
		for(RecruitCandidateProfile p : profiles){
			System.out.println(p.getDateWhenFirstApplied() +" - "+p.getEmail()+" "+p.getRecruiterEmail()+" "+p.getFirstName()+" "+p.getLastName()+" "+p.getJobDescriptionName());
		}
	}
}
