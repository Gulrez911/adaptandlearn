package com.assessment.data;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class ReviewerComment extends Base {

	String campaignName;

	Long campaignId;

	String candidateEmail;

	String candidateFirstName;

	String candidateLastName;

	@Column(length=2000)
	String technicalCompetencyInCoreAreas;

	@Column(length=2000)
	String technicalCompetencyInAncillaryAreas;

	@Column(length=2000)
	String analyticalSkills;

	@Column(length=2000)
	String communicationSkills;

	Boolean status = false;
	
	@Column(length=2000)
	String overallReviewStatus;

	String reviewers;

	String actualReviewerEmail;
	
	String param1;
	
	String param2;
	
	String param3;
	
	String param4;

	public String getCampaignName() {
		return campaignName;
	}

	public void setCampaignName(String campaignName) {
		this.campaignName = campaignName;
	}

	public Long getCampaignId() {
		return campaignId;
	}

	public void setCampaignId(Long campaignId) {
		this.campaignId = campaignId;
	}

	public String getCandidateEmail() {
		return candidateEmail;
	}

	public void setCandidateEmail(String candidateEmail) {
		this.candidateEmail = candidateEmail;
	}

	public String getCandidateFirstName() {
		return candidateFirstName;
	}

	public void setCandidateFirstName(String candidateFirstName) {
		this.candidateFirstName = candidateFirstName;
	}

	public String getCandidateLastName() {
		return candidateLastName;
	}

	public void setCandidateLastName(String candidateLastName) {
		this.candidateLastName = candidateLastName;
	}

	

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	public String getOverallReviewStatus() {
		return overallReviewStatus;
	}

	public void setOverallReviewStatus(String overallReviewStatus) {
		this.overallReviewStatus = overallReviewStatus;
	}

	public String getReviewers() {
		return reviewers;
	}

	public void setReviewers(String reviewers) {
		this.reviewers = reviewers;
	}

	public String getActualReviewerEmail() {
		return actualReviewerEmail;
	}

	public void setActualReviewerEmail(String actualReviewerEmail) {
		this.actualReviewerEmail = actualReviewerEmail;
	}

	@Override
	public String toString() {
		return "ReviwerComment [campaignName=" + campaignName + ", campaignId=" + campaignId + ", candidateEmail="
				+ candidateEmail + ", candidateFirstName=" + candidateFirstName + ", candidateLastName="
				+ candidateLastName + ", technicalCompetencyInCoreAreas=" + technicalCompetencyInCoreAreas + ", technicalCompetencyInAncillaryAreas=" + technicalCompetencyInAncillaryAreas + ", analyticalSkills=" + analyticalSkills + ", communicationSkills="
				+ communicationSkills + ", status=" + status + ", overallReviewStatus=" + overallReviewStatus + ", reviewers="
				+ reviewers + ", actualReviewerEmail=" + actualReviewerEmail + "]";
	}

	public String getTechnicalCompetencyInCoreAreas() {
		return technicalCompetencyInCoreAreas;
	}

	public void setTechnicalCompetencyInCoreAreas(String technicalCompetencyInCoreAreas) {
		this.technicalCompetencyInCoreAreas = technicalCompetencyInCoreAreas;
	}

	public String getTechnicalCompetencyInAncillaryAreas() {
		return technicalCompetencyInAncillaryAreas;
	}

	public void setTechnicalCompetencyInAncillaryAreas(String technicalCompetencyInAncillaryAreas) {
		this.technicalCompetencyInAncillaryAreas = technicalCompetencyInAncillaryAreas;
	}

	public String getAnalyticalSkills() {
		return analyticalSkills;
	}

	public void setAnalyticalSkills(String analyticalSkills) {
		this.analyticalSkills = analyticalSkills;
	}

	public String getCommunicationSkills() {
		return communicationSkills;
	}

	public void setCommunicationSkills(String communicationSkills) {
		this.communicationSkills = communicationSkills;
	}

	public String getParam1() {
		return param1;
	}

	public void setParam1(String param1) {
		this.param1 = param1;
	}

	public String getParam2() {
		return param2;
	}

	public void setParam2(String param2) {
		this.param2 = param2;
	}

	public String getParam3() {
		return param3;
	}

	public void setParam3(String param3) {
		this.param3 = param3;
	}

	public String getParam4() {
		return param4;
	}

	public void setParam4(String param4) {
		this.param4 = param4;
	}
	
	

}
