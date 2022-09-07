package com.assessment.data;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Transient;

@Entity
public class RecruitCandidateProfile extends Base{

	String firstName;
	
	String lastName;

	String email;

	String candidateCVName;

	String candidateCVURL;
	
	Long jobDescriptionId;
	
	String recruiterEmail;
	
	String locationCVFileSystem;
	
//<<<<<<< HEAD
//	String jobDescriptionName;
//	
//	public String getJobDescriptionName() {
//		return jobDescriptionName;
//	}
//
//	public void setJobDescriptionName(String jobDescriptionName) {
//		this.jobDescriptionName = jobDescriptionName;
//=======
//	@Transient
	String jobDescriptionName;
	
	@Transient
	String dateWhenFirstApplied;
	
	@Transient
	String candidateProfileUrl;
	
	String parsedEmail;
	
	String jobDescName;
	
	String jobDescId;
	
	String parsedCompanyId;
	
	String parsedName;
	
	String parsedPhone;
	
	Integer relevancyMatch;
	
	String candidateLastLocation;
	
	@Column(length=5000)
	String parsedSkills;
	
	@Column(length=5000)
	String parsedSkills2;
	
	Integer totalExperience;
	
	@Column(length=1000)
	String designations;
	
	@Column(length=1000)
	String educations;
	
	@Column(length=3000)
	String primaryMatches;
	
	@Column(length=3000)
	String secondaryMatches;
	
	Boolean parsed = false;
	
	Boolean parsedButFailed = false;
	
	@Transient
	String expectedPrimarySkills;
	
	@Transient
	String expectedSecondarySkills;
	
	@Transient
	String expectedEducation;
	
	@Transient
	String expectedExperience;
	
	
	public String getCandidateProfileUrl() {
		return candidateProfileUrl;
	}

	public void setCandidateProfileUrl(String candidateProfileUrl) {
		this.candidateProfileUrl = candidateProfileUrl;
	}

	public RecruitCandidateProfile(){
		
	}
	
	public RecruitCandidateProfile(String firstName, String lastName, String email, String candidateCVURL, String recruiterEmail, String jobDescriptionName, Date createDate ){
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.candidateCVURL = candidateCVURL;
		this.recruiterEmail = recruiterEmail;
		this.jobDescriptionName = jobDescriptionName;
		this.createDate = createDate;
//>>>>>>> branch 'master' of https://github.com/Gulrez911/Eassess3.git
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCandidateCVName() {
		return candidateCVName;
	}

	public void setCandidateCVName(String candidateCVName) {
		this.candidateCVName = candidateCVName;
	}

	public String getCandidateCVURL() {
		return candidateCVURL;
	}

	public void setCandidateCVURL(String candidateCVURL) {
		this.candidateCVURL = candidateCVURL;
	}

	public Long getJobDescriptionId() {
		return jobDescriptionId;
	}

	public void setJobDescriptionId(Long jobDescriptionId) {
		this.jobDescriptionId = jobDescriptionId;
	}

	public String getRecruiterEmail() {
		return recruiterEmail;
	}

	public void setRecruiterEmail(String recruiterEmail) {
		this.recruiterEmail = recruiterEmail;
	}

	public String getJobDescriptionName() {
		return jobDescriptionName;
	}

	public void setJobDescriptionName(String jobDescriptionName) {
		this.jobDescriptionName = jobDescriptionName;
	}

	public String getDateWhenFirstApplied() {
			if(getCreateDate() != null){
				SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy hh:mm aa");
				return dateFormat.format(getCreateDate());
			}
		return "NA";
	}

	public void setDateWhenFirstApplied(String dateWhenFirstApplied) {
		this.dateWhenFirstApplied = dateWhenFirstApplied;
	}

	public String getParsedEmail() {
		return parsedEmail;
	}

	public void setParsedEmail(String parsedEmail) {
		this.parsedEmail = parsedEmail;
	}

	public String getJobDescName() {
		return jobDescName;
	}

	public void setJobDescName(String jobDescName) {
		this.jobDescName = jobDescName;
	}

	public String getJobDescId() {
		return jobDescId;
	}

	public void setJobDescId(String jobDescId) {
		this.jobDescId = jobDescId;
	}

	public String getParsedCompanyId() {
		return parsedCompanyId;
	}

	public void setParsedCompanyId(String parsedCompanyId) {
		this.parsedCompanyId = parsedCompanyId;
	}

	public String getParsedName() {
		return parsedName;
	}

	public void setParsedName(String parsedName) {
		this.parsedName = parsedName;
	}

	public String getParsedPhone() {
		return parsedPhone;
	}

	public void setParsedPhone(String parsedPhone) {
		this.parsedPhone = parsedPhone;
	}

	public Integer getRelevancyMatch() {
		return relevancyMatch;
	}

	public void setRelevancyMatch(Integer relevancyMatch) {
		this.relevancyMatch = relevancyMatch;
	}

	public String getCandidateLastLocation() {
		return candidateLastLocation;
	}

	public void setCandidateLastLocation(String candidateLastLocation) {
		this.candidateLastLocation = candidateLastLocation;
	}

//	public String getParsedSkills() {
//		if(this.parsedSkills != null){
//			//return this.parsedSkills.replace("###$", ", ");
//			String prim[] = this.parsedSkills.replace("###$", ", ").split(",");
//			String primRet[] = new String[prim.length];
//				for(int i=0;i<prim.length;i++){
//					primRet[i] = prim[i];
//					if(getExpectedPrimarySkills().contains(prim[i])){
//						primRet[i] = "<b>"+prim[i]+"</b>";
//					}
//					
//				}
//			return String.join(", ", primRet);
//		}
//		return parsedSkills;
//	}
	
	public String getParsedSkills() {
		if(this.parsedSkills != null){
			return this.parsedSkills.replace("###$", ", ");
			
		}
		return parsedSkills;
	}

	public void setParsedSkills(String parsedSkills) {
		
		this.parsedSkills = parsedSkills;
	}

//	public String getParsedSkills2() {
//		if(this.parsedSkills2 != null){
//			//return this.parsedSkills2.replace("###$", ", ");
//			String sec[] = this.parsedSkills2.replace("###$", ", ").split(",");
//			String secRet[] = new String[sec.length];
//				for(int i=0;i<sec.length;i++){
//					secRet[i] = sec[i];
//					if(getExpectedSecondarySkills().contains(sec[i])){
//						secRet[i] = "<b>"+sec[i]+"</b>";
//					}
//					
//				}
//			return String.join(", ", secRet);
//		}
//		return parsedSkills2;
//	}
	
	public String getParsedSkills2() {
		if(this.parsedSkills2 != null){
			return this.parsedSkills2.replace("###$", ", ");
			
		}
		return parsedSkills2;
	}

	public void setParsedSkills2(String parsedSkills2) {
		this.parsedSkills2 = parsedSkills2;
	}

	public Integer getTotalExperience() {
		return totalExperience;
	}

	public void setTotalExperience(Integer totalExperience) {
		this.totalExperience = totalExperience;
	}

	public String getDesignations() {
		if(this.designations != null){
			return this.designations.replace("###$", ", ");
		}
		return designations;
	}

	public void setDesignations(String designations) {
		this.designations = designations;
	}

	public String getEducations() {
		if(this.educations != null){
			return this.educations.replace("###$", ", ");
		}
		return educations;
	}

	public void setEducations(String educations) {
		this.educations = educations;
	}

//	public String getPrimaryMatches() {
//		if(this.primaryMatches != null){
//			String prim[] = this.primaryMatches.replace("###$", ", ").split(",");
//			String primRet[] = new String[prim.length];
//				for(int i=0;i<prim.length;i++){
//					primRet[i] = prim[i];
//					if(getExpectedPrimarySkills().contains(prim[i])){
//						primRet[i] = "<b>"+prim[i]+"</b>";
//					}
//					
//				}
//			return String.join(", ", primRet);
//		}
//		return primaryMatches;
//	}
	
	public String getPrimaryMatches() {
		if(this.primaryMatches != null){
			return  this.primaryMatches.replace("###$", ", ");
			
		}
		return primaryMatches;
	}

	public void setPrimaryMatches(String primaryMatches) {
		this.primaryMatches = primaryMatches;
	}

//	public String getSecondaryMatches() {
//		if(this.secondaryMatches != null){
//			String sec[] =  this.secondaryMatches.replace("###$", ", ").split(",");
//			String secRet[] = new String[sec.length];
//			for(int i=0;i<sec.length;i++){
//				secRet[i] = sec[i];
//				if(getExpectedPrimarySkills().contains(sec[i])){
//					secRet[i] = "<b>"+sec[i]+"</b>";
//				}
//				
//			}
//		return String.join(", ", secRet);
//		}
//		return secondaryMatches;
//	}
	
	public String getSecondaryMatches() {
		if(this.secondaryMatches != null){
			return  this.secondaryMatches.replace("###$", ", ");
			
		}
		return secondaryMatches;
	}

	public void setSecondaryMatches(String secondaryMatches) {
		this.secondaryMatches = secondaryMatches;
	}

	public Boolean getParsed() {
		return parsed;
	}

	public void setParsed(Boolean parsed) {
		this.parsed = parsed;
	}

	public String getLocationCVFileSystem() {
		return locationCVFileSystem;
	}

	public void setLocationCVFileSystem(String locationCVFileSystem) {
		this.locationCVFileSystem = locationCVFileSystem;
	}

	public String getExpectedPrimarySkills() {
		return expectedPrimarySkills;
	}

	public void setExpectedPrimarySkills(String expectedPrimarySkills) {
		this.expectedPrimarySkills = expectedPrimarySkills;
	}

	public String getExpectedSecondarySkills() {
		return expectedSecondarySkills;
	}

	public void setExpectedSecondarySkills(String expectedSecondarySkills) {
		this.expectedSecondarySkills = expectedSecondarySkills;
	}

	public String getExpectedEducation() {
		return expectedEducation;
	}

	public void setExpectedEducation(String expectedEducation) {
		this.expectedEducation = expectedEducation;
	}

	public String getExpectedExperience() {
		return expectedExperience;
	}

	public void setExpectedExperience(String expectedExperience) {
		this.expectedExperience = expectedExperience;
	}

	public Boolean getParsedButFailed() {
		return parsedButFailed;
	}

	public void setParsedButFailed(Boolean parsedButFailed) {
		this.parsedButFailed = parsedButFailed;
	}

	
	
	
	
}
