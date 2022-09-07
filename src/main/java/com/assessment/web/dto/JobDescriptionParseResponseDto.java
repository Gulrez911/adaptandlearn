package com.assessment.web.dto;

public class JobDescriptionParseResponseDto {
	
	String description;
	
	String primarySkills;
	
	String secondarySkills;
	
	String education;
	
	String certification;
	
	String domain;
	
	String experience;
	
	String location;
	
	Boolean errorDescription =false;
	
	Boolean errorPrimarySkills = false;
	
	Boolean errorSecondarySkills = false;
	
	Boolean errorEducation = false;
	
	Boolean errorcertification = false;
	
	Boolean errordomain = false;
	
	Boolean errorLocation = false;
	
	Boolean errorExperience = false;

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getPrimarySkills() {
		return primarySkills;
	}

	public void setPrimarySkills(String primarySkills) {
		this.primarySkills = primarySkills;
	}

	public String getSecondarySkills() {
		return secondarySkills;
	}

	public void setSecondarySkills(String secondarySkills) {
		this.secondarySkills = secondarySkills;
	}

	public String getEducation() {
		return education;
	}

	public void setEducation(String education) {
		this.education = education;
	}

	public String getCertification() {
		return certification;
	}

	public void setCertification(String certification) {
		this.certification = certification;
	}

	public String getDomain() {
		return domain;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}

	public String getExperience() {
		return experience;
	}

	public void setExperience(String experience) {
		this.experience = experience;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public Boolean getErrorPrimarySkills() {
		return errorPrimarySkills;
	}

	public void setErrorPrimarySkills(Boolean errorPrimarySkills) {
		this.errorPrimarySkills = errorPrimarySkills;
	}

	public Boolean getErrorSecondarySkills() {
		return errorSecondarySkills;
	}

	public void setErrorSecondarySkills(Boolean errorSecondarySkills) {
		this.errorSecondarySkills = errorSecondarySkills;
	}

	public Boolean getErrorEducation() {
		return errorEducation;
	}

	public void setErrorEducation(Boolean errorEducation) {
		this.errorEducation = errorEducation;
	}

	public Boolean getErrorcertification() {
		return errorcertification;
	}

	public void setErrorcertification(Boolean errorcertification) {
		this.errorcertification = errorcertification;
	}

	public Boolean getErrordomain() {
		return errordomain;
	}

	public void setErrordomain(Boolean errordomain) {
		this.errordomain = errordomain;
	}

	public Boolean getErrorLocation() {
		return errorLocation;
	}

	public void setErrorLocation(Boolean errorLocation) {
		this.errorLocation = errorLocation;
	}

	public Boolean getErrorExperience() {
		return errorExperience;
	}

	public void setErrorExperience(Boolean errorExperience) {
		this.errorExperience = errorExperience;
	}

	public Boolean getErrorDescription() {
		return errorDescription;
	}

	public void setErrorDescription(Boolean errorDescription) {
		this.errorDescription = errorDescription;
	}

	

	
	

}
