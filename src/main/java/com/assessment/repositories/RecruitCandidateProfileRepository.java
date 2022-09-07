package com.assessment.repositories;

import java.util.List;
import java.util.Set;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.assessment.data.RecruitCandidateProfile;

public interface RecruitCandidateProfileRepository extends JpaRepository<RecruitCandidateProfile, Long> {

//<<<<<<< HEAD
	List<RecruitCandidateProfile> findByJobDescriptionIdAndRecruiterEmail(Long jobId, String recruiterEmail);
	
	List<RecruitCandidateProfile> findByCompanyId(String companyId);
	
	RecruitCandidateProfile findByEmailAndCompanyId(String email, String companyId);
//=======
	List<RecruitCandidateProfile> findByJobDescriptionIdAndRecruiterEmailAndCompanyId(Long jobDescriptionId, String recruiterEmail, String companyId);
	

	
	
	
	@Query("SELECT r FROM RecruitCandidateProfile r WHERE r.jobDescriptionId=:jobDescriptionId and r.email=:email and r.companyId=:companyId")
	RecruitCandidateProfile findByPrimaryKey(@Param("jobDescriptionId") Long jobDescriptionId, @Param("email")  String email, @Param("companyId")  String companyId);
	
	
	@Query("SELECT r FROM RecruitCandidateProfile r WHERE r.jobDescriptionId=:jobDescriptionId and r.companyId=:companyId")
	List<RecruitCandidateProfile> findByJobDescriptionIdAndCompanyId(@Param("jobDescriptionId") Long jobDescriptionId, @Param("companyId") String companyId);
	
	
	//RecruitCandidateProfile
	
	@Query("SELECT " +
	           "    new com.assessment.data.RecruitCandidateProfile(r.firstName, r.lastName, r.email, r.candidateCVURL, r.recruiterEmail, j.name, r.createDate) " +
	           "FROM " +
	           "    RecruitCandidateProfile r, JobDescription j WHERE r.companyId=:companyId and r.jobDescriptionId=j.id  and (j.name LIKE %:search% OR r.firstName LIKE %:search% OR r.lastName LIKE %:search% OR r.email LIKE %:search% OR r.recruiterEmail LIKE %:search%) order by  :sort")
	public Page<RecruitCandidateProfile> getAllProfiles(@Param("companyId")String companyId,  @Param("sort")String sort, @Param("search")String search, Pageable pageable);
//>>>>>>> branch 'master' of https://github.com/Gulrez911/Eassess3.git
}
