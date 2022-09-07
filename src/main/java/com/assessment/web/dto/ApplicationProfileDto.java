package com.assessment.web.dto;

import com.assessment.data.RecruitCandidateProfile;

public class ApplicationProfileDto {

	String jobDescName;
	
	String profileUploadDate;
	
	String candidateName;
	
	String profileLink;
	
	String directInterview;
	
	String sourceOfProfile;
	
	String assessments;
	
	String stageOfProfile;
	
	String interviewerFeedback;
	
	String candidateSkillProfileLink;
	
	String status;
	
	RecruitCandidateProfile recruitCandidateProfile;

	public String getJobDescName() {
		return jobDescName;
	}

	public void setJobDescName(String jobDescName) {
		this.jobDescName = jobDescName;
	}

	public String getProfileUploadDate() {
		return profileUploadDate;
	}

	public void setProfileUploadDate(String profileUploadDate) {
		this.profileUploadDate = profileUploadDate;
	}

	public String getCandidateName() {
		return candidateName;
	}

	public void setCandidateName(String candidateName) {
		this.candidateName = candidateName;
	}

	public String getProfileLink() {
		return profileLink;
	}

	public void setProfileLink(String profileLink) {
		this.profileLink = profileLink;
	}

	public String getDirectInterview() {
		return directInterview;
	}

	public void setDirectInterview(String directInterview) {
		this.directInterview = directInterview;
	}

	public String getSourceOfProfile() {
		return sourceOfProfile;
	}

	public void setSourceOfProfile(String sourceOfProfile) {
		this.sourceOfProfile = sourceOfProfile;
	}

	public String getAssessments() {
		return assessments;
	}

	public void setAssessments(String assessments) {
		this.assessments = assessments;
	}

	public String getStageOfProfile() {
		return stageOfProfile;
	}

	public void setStageOfProfile(String stageOfProfile) {
		this.stageOfProfile = stageOfProfile;
	}

	public String getInterviewerFeedback() {
		return interviewerFeedback;
	}

	public void setInterviewerFeedback(String interviewerFeedback) {
		this.interviewerFeedback = interviewerFeedback;
	}

	public String getCandidateSkillProfileLink() {
		return candidateSkillProfileLink;
	}

	public void setCandidateSkillProfileLink(String candidateSkillProfileLink) {
		this.candidateSkillProfileLink = candidateSkillProfileLink;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public RecruitCandidateProfile getRecruitCandidateProfile() {
		return recruitCandidateProfile;
	}

	public void setRecruitCandidateProfile(RecruitCandidateProfile recruitCandidateProfile) {
		this.recruitCandidateProfile = recruitCandidateProfile;
	}
	
	
}
