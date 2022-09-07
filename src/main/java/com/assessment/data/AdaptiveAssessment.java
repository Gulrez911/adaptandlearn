package com.assessment.data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Transient;

@Entity
public class AdaptiveAssessment extends Base{
	
private String adaptiveAssessmentName;
	Integer testTimeInMinutes;
	
	//Integer totalMarks;
	
	String coreSkills = "Machine Learning Basics";
	
	Boolean basedOnDifficultyLevel;
	
	
	@Column(length=3000)
	String intro;
	
	String postTestCompletionText;
	
	Boolean showFinalScoreToParticipants;
	
	
	
	
	
	
	
	Boolean sentToStudent = false;
	
	@Transient
	String category;
	
	@Transient
	String uDate;
	
	@Transient
	String cDate;
	
	String sendToAdminEmail;
	
	Integer noOfProgressions;
	
	Integer noOfRegressions;
	
	
	@Transient
	String publicUrl;
	
	String domainEmailSupported = "*";
	
	
	String testType = "Java";
	
	Boolean sendRecommReport = false;
	
	Integer noOfConfigurableAttempts = 1;
	
	Boolean considerConfidence;
	
	Boolean justification;

	

	public String getAdaptiveAssessmentName() {
		return adaptiveAssessmentName;
	}

	public void setAdaptiveAssessmentName(String adaptiveAssessmentName) {
		this.adaptiveAssessmentName = adaptiveAssessmentName;
	}

	public Integer getTestTimeInMinutes() {
		return testTimeInMinutes;
	}

	public void setTestTimeInMinutes(Integer testTimeInMinutes) {
		this.testTimeInMinutes = testTimeInMinutes;
	}

	public String getIntro() {
		return intro;
	}

	public void setIntro(String intro) {
		this.intro = intro;
	}

	public String getPostTestCompletionText() {
		return postTestCompletionText;
	}

	public void setPostTestCompletionText(String postTestCompletionText) {
		this.postTestCompletionText = postTestCompletionText;
	}

	public Boolean getShowFinalScoreToParticipants() {
		return showFinalScoreToParticipants;
	}

	public void setShowFinalScoreToParticipants(Boolean showFinalScoreToParticipants) {
		this.showFinalScoreToParticipants = showFinalScoreToParticipants;
	}

	

	

	

	public String getCoreSkills() {
		return coreSkills;
	}

	public void setCoreSkills(String coreSkills) {
		this.coreSkills = coreSkills;
	}

	public Boolean getBasedOnDifficultyLevel() {
		return basedOnDifficultyLevel;
	}

	public void setBasedOnDifficultyLevel(Boolean basedOnDifficultyLevel) {
		this.basedOnDifficultyLevel = basedOnDifficultyLevel;
	}

	

	

	

	public Boolean getSentToStudent() {
		return sentToStudent;
	}

	public void setSentToStudent(Boolean sentToStudent) {
		this.sentToStudent = sentToStudent;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getuDate() {
		return uDate;
	}

	public void setuDate(String uDate) {
		this.uDate = uDate;
	}

	public String getcDate() {
		return cDate;
	}

	public void setcDate(String cDate) {
		this.cDate = cDate;
	}

	public String getSendToAdminEmail() {
		return sendToAdminEmail;
	}

	public void setSendToAdminEmail(String sendToAdminEmail) {
		this.sendToAdminEmail = sendToAdminEmail;
	}

	

	public String getPublicUrl() {
		return publicUrl;
	}

	public void setPublicUrl(String publicUrl) {
		this.publicUrl = publicUrl;
	}

	public String getDomainEmailSupported() {
		return domainEmailSupported;
	}

	public void setDomainEmailSupported(String domainEmailSupported) {
		this.domainEmailSupported = domainEmailSupported;
	}

	public String getTestType() {
		return testType;
	}

	public void setTestType(String testType) {
		this.testType = testType;
	}

	public Boolean getSendRecommReport() {
		return sendRecommReport;
	}

	public void setSendRecommReport(Boolean sendRecommReport) {
		this.sendRecommReport = sendRecommReport;
	}

	public Integer getNoOfConfigurableAttempts() {
		return noOfConfigurableAttempts;
	}

	public void setNoOfConfigurableAttempts(Integer noOfConfigurableAttempts) {
		this.noOfConfigurableAttempts = noOfConfigurableAttempts;
	}

	public Boolean getConsiderConfidence() {
		return considerConfidence;
	}

	public void setConsiderConfidence(Boolean considerConfidence) {
		this.considerConfidence = considerConfidence;
	}

	public Boolean getJustification() {
		return justification;
	}

	public void setJustification(Boolean justification) {
		this.justification = justification;
	}

	public Integer getNoOfProgressions() {
		return noOfProgressions;
	}

	public void setNoOfProgressions(Integer noOfProgressions) {
		this.noOfProgressions = noOfProgressions;
	}

	public Integer getNoOfRegressions() {
		return noOfRegressions;
	}

	public void setNoOfRegressions(Integer noOfRegressions) {
		this.noOfRegressions = noOfRegressions;
	}

	
	
	
	

}
