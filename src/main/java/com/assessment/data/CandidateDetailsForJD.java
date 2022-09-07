package com.assessment.data;

import javax.persistence.Entity;

@Entity
public class CandidateDetailsForJD extends Base {

	String candidateName;

	String phoneNumber;

	String email;

	String totalExperience;

	String technicalSkills;

	String softSkills;

	String educationalDetails;

	String certifications;

	Integer cvPageCount;

	Integer keywordCountInCV;

	Integer relevancyToJobDesc;   //Relevancy to Job Desc in terms of percentage

	Long jobDescId;

	String jobDescName;

	Long recruitmentConsultantId;
	
	String relevantYears;
	
	String currentLocation;
	
	String street;
	
	String zipCode;
	
	String city;
	
	String state;

	String languages;
	
	public String getLanguages() {
		return languages;
	}

	public void setLanguages(String languages) {
		this.languages = languages;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCurrentLocation() {
		return currentLocation;
	}

	public void setCurrentLocation(String currentLocation) {
		this.currentLocation = currentLocation;
	}

	public String getRelevantYears() {
		return relevantYears;
	}

	public void setRelevantYears(String relevantYears) {
		this.relevantYears = relevantYears;
	}

	public void setCvPageCount(Integer cvPageCount) {
		this.cvPageCount = cvPageCount;
	}

	public void setKeywordCountInCV(Integer keywordCountInCV) {
		this.keywordCountInCV = keywordCountInCV;
	}

	public void setJobDescId(Long jobDescId) {
		this.jobDescId = jobDescId;
	}

	public void setRecruitmentConsultantId(Long recruitmentConsultantId) {
		this.recruitmentConsultantId = recruitmentConsultantId;
	}

	public String getCandidateName() {
		return candidateName;
	}

	public void setCandidateName(String candidateName) {
		this.candidateName = candidateName;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTotalExperience() {
		return totalExperience;
	}

	public void setTotalExperience(String totalExperience) {
		this.totalExperience = totalExperience;
	}

	public String getTechnicalSkills() {
		return technicalSkills;
	}

	public void setTechnicalSkills(String technicalSkills) {
		this.technicalSkills = technicalSkills;
	}

	public String getSoftSkills() {
		return softSkills;
	}

	public void setSoftSkills(String softSkills) {
		this.softSkills = softSkills;
	}

	public String getEducationalDetails() {
		return educationalDetails;
	}

	public void setEducationalDetails(String educationalDetails) {
		this.educationalDetails = educationalDetails;
	}

	public String getCertifications() {
		return certifications;
	}

	public void setCertifications(String certifications) {
		this.certifications = certifications;
	}

	public Integer getRelevancyToJobDesc() {
		return relevancyToJobDesc;
	}

	public void setRelevancyToJobDesc(Integer relevancyToJobDesc) {
		this.relevancyToJobDesc = relevancyToJobDesc;
	}

	public String getJobDescName() {
		return jobDescName;
	}

	public void setJobDescName(String jobDescName) {
		this.jobDescName = jobDescName;
	}

	public Integer getCvPageCount() {
		return cvPageCount;
	}

	public Integer getKeywordCountInCV() {
		return keywordCountInCV;
	}

	public Long getJobDescId() {
		return jobDescId;
	}

	public Long getRecruitmentConsultantId() {
		return recruitmentConsultantId;
	}

}
