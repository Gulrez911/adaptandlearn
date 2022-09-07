package com.assessment.web.dto.resumeparsing;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Data {

		@JsonProperty("Job Description Name")
	    private String JobDescriptionName;

		@JsonProperty("Job Description ID")
	    private String JobDescriptionID;

		@JsonProperty("Company ID")
	    private String CompanyID;

		@JsonProperty("Relevancy Match")
	    private int RelevancyMatch;

		@JsonProperty("Candidate Name")
	    private String CandidateName;

		@JsonProperty("Candidate Email")
	    private String CandidateEmail;

		@JsonProperty("Candidate Phone")
	    private String CandidatePhone;

		@JsonProperty("Candidate Last Location")
	    private String CandidateLastLocation;

		@JsonProperty("Skills")
	    private List<String> Skills;

		@JsonProperty("skills_v2")
	    private List<String> skills_v2;

		@JsonProperty("Total Experience")
	    private int TotalExperience;

		@JsonProperty("Designation")
	    private List<String> Designation;

		@JsonProperty("Education")
	    private List<String> Education;

		@JsonProperty("Primary_match")
	    private List<String> Primary_match;

		@JsonProperty("Secondary_match")
	    private List<String> Secondary_match;

		public String getJobDescriptionName() {
			return JobDescriptionName;
		}

		public void setJobDescriptionName(String jobDescriptionName) {
			JobDescriptionName = jobDescriptionName;
		}

		public String getJobDescriptionID() {
			return JobDescriptionID;
		}

		public void setJobDescriptionID(String jobDescriptionID) {
			JobDescriptionID = jobDescriptionID;
		}

		public String getCompanyID() {
			return CompanyID;
		}

		public void setCompanyID(String companyID) {
			CompanyID = companyID;
		}

		public int getRelevancyMatch() {
			return RelevancyMatch;
		}

		public void setRelevancyMatch(int relevancyMatch) {
			RelevancyMatch = relevancyMatch;
		}

		public String getCandidateName() {
			return CandidateName;
		}

		public void setCandidateName(String candidateName) {
			CandidateName = candidateName;
		}

		public String getCandidateEmail() {
			return CandidateEmail;
		}

		public void setCandidateEmail(String candidateEmail) {
			CandidateEmail = candidateEmail;
		}

		public String getCandidatePhone() {
			return CandidatePhone;
		}

		public void setCandidatePhone(String candidatePhone) {
			CandidatePhone = candidatePhone;
		}

		public String getCandidateLastLocation() {
			return CandidateLastLocation;
		}

		public void setCandidateLastLocation(String candidateLastLocation) {
			CandidateLastLocation = candidateLastLocation;
		}

		public List<String> getSkills() {
			return Skills;
		}
		
		public String getSkillsInString(){
			String ret = "";
				for(String s : getSkills()){
					ret+= s+"###$";
				}
				if(ret.endsWith("###$")){
					ret = ret.substring(0, ret.lastIndexOf("###$"));
				}
			return ret;
		}

		public void setSkills(List<String> skills) {
			Skills = skills;
		}

		public List<String> getSkills_v2() {
			return skills_v2;
		}
		
		
		public String getSkills2InString(){
			String ret = "";
				for(String s : getSkills_v2()){
					ret+= s+"###$";
				}
				if(ret.endsWith("###$")){
					ret = ret.substring(0, ret.lastIndexOf("###$"));
				}
			return ret;
		}

		public void setSkills_v2(List<String> skills_v2) {
			this.skills_v2 = skills_v2;
		}

		public int getTotalExperience() {
			return TotalExperience;
		}

		public void setTotalExperience(int totalExperience) {
			TotalExperience = totalExperience;
		}

		public List<String> getDesignation() {
			return Designation;
		}
		
		
		public String getDesignationInString(){
			String ret = "";
				for(String s : getDesignation()){
					ret+= s+"###$";
				}
				if(ret.endsWith("###$")){
					ret = ret.substring(0, ret.lastIndexOf("###$"));
				}
			return ret;
		}

		public void setDesignation(List<String> designation) {
			Designation = designation;
		}

		public List<String> getEducation() {
			return Education;
		}

		public String getEducationInString(){
			String ret = "";
				for(String s : getEducation()){
					ret+= s+"###$";
				}
				if(ret.endsWith("###$")){
					ret = ret.substring(0, ret.lastIndexOf("###$"));
				}
			return ret;
		}
		
		public void setEducation(List<String> education) {
			Education = education;
		}

		public List<String> getPrimary_match() {
			return Primary_match;
		}
		
		public String getPrimary_matchInString(){
			String ret = "";
				for(String s : getPrimary_match()){
					ret+= s+"###$";
				}
				if(ret.endsWith("###$")){
					ret = ret.substring(0, ret.lastIndexOf("###$"));
				}
			return ret;
		}

		public void setPrimary_match(List<String> primary_match) {
			Primary_match = primary_match;
		}

		public List<String> getSecondary_match() {
			return Secondary_match;
		}
		
		public String getSecondary_matchInString(){
			String ret = "";
				for(String s : getSecondary_match()){
					ret+= s+"###$";
				}
				if(ret.endsWith("###$")){
					ret = ret.substring(0, ret.lastIndexOf("###$"));
				}
			return ret;
		}

		public void setSecondary_match(List<String> secondary_match) {
			Secondary_match = secondary_match;
		}
	    
	    

}
