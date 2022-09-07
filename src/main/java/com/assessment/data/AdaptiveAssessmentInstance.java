package com.assessment.data;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Transient;

@Entity
public class AdaptiveAssessmentInstance extends Base{
	private String adaptiveAssessmentName;
	
	Boolean complete;
	
	String email;
	
	@Enumerated(EnumType.STRING)
	AdaptiveAssessmentSkillLevel  currentLevel;
	
	Integer noofRegressions = 0;
	
	Integer noOfProgressions = 0;
	
	Integer noOfProgressionsLevel1 = 0;
	
	Integer noOfProgressionsLevel2 = 0;
	
	Integer noOfProgressionsLevel3 = 0;
	
	Integer noOfProgressionsLevel4 = 0;
	
	Integer noOfProgressionsLevel5 = 0;
	
	Integer noOfRegressionsLevel1 = 0;
	
	Integer noOfRegressionsLevel2 = 0;
	
	Integer noOfRegressionsLevel3 = 0;
	
	Integer noOfRegressionsLevel4 = 0;
	
	Integer noOfRegressionsLevel5 = 0;
	
	Boolean status;
	
	@Transient
	Integer averageScore;
	
	public AdaptiveAssessmentInstance(){
		
	}
	public AdaptiveAssessmentInstance(String email, String companyId, String testName){
		this.email = email;
		this.setCompanyId(companyId);
		this.adaptiveAssessmentName = testName;
	}

	public String getAdaptiveAssessmentName() {
		return adaptiveAssessmentName;
	}

	public void setAdaptiveAssessmentName(String adaptiveAssessmentName) {
		this.adaptiveAssessmentName = adaptiveAssessmentName;
	}

	public Boolean getComplete() {
		return complete;
	}

	public void setComplete(Boolean complete) {
		this.complete = complete;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public AdaptiveAssessmentSkillLevel getCurrentLevel() {
		return currentLevel;
	}

	public void setCurrentLevel(AdaptiveAssessmentSkillLevel currentLevel) {
		this.currentLevel = currentLevel;
	}

	public Integer getNoofRegressions() {
		return noofRegressions == null?0:noofRegressions;
	}

	public void setNoofRegressions(Integer noofRegressions) {
		this.noofRegressions = noofRegressions;
	}

	public Integer getNoOfProgressions() {
		return noOfProgressions == null?0:noOfProgressions;
	}

	public void setNoOfProgressions(Integer noOfProgressions) {
		this.noOfProgressions = noOfProgressions;
	}

	public Integer getNoOfProgressionsLevel1() {
		return noOfProgressionsLevel1;
	}

	public void setNoOfProgressionsLevel1(Integer noOfProgressionsLevel1) {
		this.noOfProgressionsLevel1 = noOfProgressionsLevel1;
	}

	public Integer getNoOfProgressionsLevel2() {
		return noOfProgressionsLevel2;
	}

	public void setNoOfProgressionsLevel2(Integer noOfProgressionsLevel2) {
		this.noOfProgressionsLevel2 = noOfProgressionsLevel2;
	}

	public Integer getNoOfProgressionsLevel3() {
		return noOfProgressionsLevel3;
	}

	public void setNoOfProgressionsLevel3(Integer noOfProgressionsLevel3) {
		this.noOfProgressionsLevel3 = noOfProgressionsLevel3;
	}

	public Integer getNoOfProgressionsLevel4() {
		return noOfProgressionsLevel4;
	}

	public void setNoOfProgressionsLevel4(Integer noOfProgressionsLevel4) {
		this.noOfProgressionsLevel4 = noOfProgressionsLevel4;
	}

	public Integer getNoOfProgressionsLevel5() {
		return noOfProgressionsLevel5;
	}

	public void setNoOfProgressionsLevel5(Integer noOfProgressionsLevel5) {
		this.noOfProgressionsLevel5 = noOfProgressionsLevel5;
	}

	public Integer getNoOfRegressionsLevel1() {
		return noOfRegressionsLevel1;
	}

	public void setNoOfRegressionsLevel1(Integer noOfRegressionsLevel1) {
		this.noOfRegressionsLevel1 = noOfRegressionsLevel1;
	}

	public Integer getNoOfRegressionsLevel2() {
		return noOfRegressionsLevel2;
	}

	public void setNoOfRegressionsLevel2(Integer noOfRegressionsLevel2) {
		this.noOfRegressionsLevel2 = noOfRegressionsLevel2;
	}

	public Integer getNoOfRegressionsLevel3() {
		return noOfRegressionsLevel3;
	}

	public void setNoOfRegressionsLevel3(Integer noOfRegressionsLevel3) {
		this.noOfRegressionsLevel3 = noOfRegressionsLevel3;
	}

	public Integer getNoOfRegressionsLevel4() {
		return noOfRegressionsLevel4;
	}

	public void setNoOfRegressionsLevel4(Integer noOfRegressionsLevel4) {
		this.noOfRegressionsLevel4 = noOfRegressionsLevel4;
	}

	public Integer getNoOfRegressionsLevel5() {
		return noOfRegressionsLevel5;
	}

	public void setNoOfRegressionsLevel5(Integer noOfRegressionsLevel5) {
		this.noOfRegressionsLevel5 = noOfRegressionsLevel5;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}
	public Integer getAverageScore() {
		return averageScore;
	}
	public void setAverageScore(Integer averageScore) {
		this.averageScore = averageScore;
	}
	
	
}
