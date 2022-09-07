package com.assessment.data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Transient;

@Entity
public class AssessmentUploadData extends Base{
	
	String domain;
	
	String adaptiveAssessmentName;
	
	@Column(length=1000)
	String jobDescName;;
	
	@Column(length=3000)
	String jobdesc;
	
	@Column(length=1000)
	String adaptiveAssessmentPublicLink;
	
	String campaignName;
	
	@Column(length=1000)
	String campaignTestLink;
	
	String assessmentName1;
	
	String assessmentName2;
	
	String assessmentName3;
	
	String assessmentName4;
	
	String assessmentName5;
	
	@Column(length=1000)
	String assessmentPublicLink1;
	
	@Column(length=1000)
	String assessmentPublicLink2;
	
	@Column(length=1000)
	String assessmentPublicLink3;
	
	@Column(length=1000)
	String assessmentPublicLink4;
	
	@Column(length=1000)
	String assessmentPublicLink5;
	
	@Column(length=1000)
	String assessment1VideoUIrl;
	
	@Column(length=1000)
	String assessment2VideoUrl;
	
	@Column(length=1000)
	String assessment3VideoUrl;
	
	@Column(length=1000)
	String assessment4VideoUrl;
	
	@Column(length=1000)
	String assessment5VideoUrl;
	
	@Transient
	String skillOverView;
	
	String rootCategory;
	
	

	public String getDomain() {
		return domain;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}

	public String getAdaptiveAssessmentName() {
		return adaptiveAssessmentName;
	}

	public void setAdaptiveAssessmentName(String adaptiveAssessmentName) {
		this.adaptiveAssessmentName = adaptiveAssessmentName;
	}

	public String getAdaptiveAssessmentPublicLink() {
		return adaptiveAssessmentPublicLink;
	}

	public void setAdaptiveAssessmentPublicLink(String adaptiveAssessmentPublicLink) {
		this.adaptiveAssessmentPublicLink = adaptiveAssessmentPublicLink;
	}

	public String getCampaignName() {
		return campaignName;
	}

	public void setCampaignName(String campaignName) {
		this.campaignName = campaignName;
	}

	public String getCampaignTestLink() {
		return campaignTestLink;
	}

	public void setCampaignTestLink(String campaignTestLink) {
		this.campaignTestLink = campaignTestLink;
	}

	public String getAssessmentName1() {
		return assessmentName1;
	}

	public void setAssessmentName1(String assessmentName1) {
		this.assessmentName1 = assessmentName1;
	}

	public String getAssessmentName2() {
		return assessmentName2;
	}

	public void setAssessmentName2(String assessmentName2) {
		this.assessmentName2 = assessmentName2;
	}

	public String getAssessmentName3() {
		return assessmentName3;
	}

	public void setAssessmentName3(String assessmentName3) {
		this.assessmentName3 = assessmentName3;
	}

	public String getAssessmentName4() {
		return assessmentName4;
	}

	public void setAssessmentName4(String assessmentName4) {
		this.assessmentName4 = assessmentName4;
	}

	public String getAssessmentName5() {
		return assessmentName5;
	}

	public void setAssessmentName5(String assessmentName5) {
		this.assessmentName5 = assessmentName5;
	}

	public String getAssessmentPublicLink1() {
		return assessmentPublicLink1;
	}

	public void setAssessmentPublicLink1(String assessmentPublicLink1) {
		this.assessmentPublicLink1 = assessmentPublicLink1;
	}

	public String getAssessmentPublicLink2() {
		return assessmentPublicLink2;
	}

	public void setAssessmentPublicLink2(String assessmentPublicLink2) {
		this.assessmentPublicLink2 = assessmentPublicLink2;
	}

	public String getAssessmentPublicLink3() {
		return assessmentPublicLink3;
	}

	public void setAssessmentPublicLink3(String assessmentPublicLink3) {
		this.assessmentPublicLink3 = assessmentPublicLink3;
	}

	public String getAssessmentPublicLink4() {
		return assessmentPublicLink4;
	}

	public void setAssessmentPublicLink4(String assessmentPublicLink4) {
		this.assessmentPublicLink4 = assessmentPublicLink4;
	}

	public String getAssessmentPublicLink5() {
		return assessmentPublicLink5;
	}

	public void setAssessmentPublicLink5(String assessmentPublicLink5) {
		this.assessmentPublicLink5 = assessmentPublicLink5;
	}

	public String getAssessment1VideoUIrl() {
		return assessment1VideoUIrl;
	}

	public void setAssessment1VideoUIrl(String assessment1VideoUIrl) {
		this.assessment1VideoUIrl = assessment1VideoUIrl;
	}

	public String getAssessment2VideoUrl() {
		return assessment2VideoUrl;
	}

	public void setAssessment2VideoUrl(String assessment2VideoUrl) {
		this.assessment2VideoUrl = assessment2VideoUrl;
	}

	public String getAssessment3VideoUrl() {
		return assessment3VideoUrl;
	}

	public void setAssessment3VideoUrl(String assessment3VideoUrl) {
		this.assessment3VideoUrl = assessment3VideoUrl;
	}

	public String getAssessment4VideoUrl() {
		return assessment4VideoUrl;
	}

	public void setAssessment4VideoUrl(String assessment4VideoUrl) {
		this.assessment4VideoUrl = assessment4VideoUrl;
	}

	public String getAssessment5VideoUrl() {
		return assessment5VideoUrl;
	}

	public void setAssessment5VideoUrl(String assessment5VideoUrl) {
		this.assessment5VideoUrl = assessment5VideoUrl;
	}

	public String getJobDescName() {
		return jobDescName;
	}

	public void setJobDescName(String jobDescName) {
		this.jobDescName = jobDescName;
	}

	public String getJobdesc() {
		return jobdesc;
	}

	public void setJobdesc(String jobdesc) {
		this.jobdesc = jobdesc;
	}
	
	@Override
	public String toString(){
		return this.getJobDescName()+"___"+this.getSkillOverView()+"___"+getDomain();
	}

	public String getSkillOverView() {
		return skillOverView;
	}

	public void setSkillOverView(String skillOverView) {
		this.skillOverView = skillOverView;
	}

	public String getRootCategory() {
		return rootCategory;
	}

	public void setRootCategory(String rootCategory) {
		this.rootCategory = rootCategory;
	}

	
	
	

	
}
