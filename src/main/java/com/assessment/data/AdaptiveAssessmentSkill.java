package com.assessment.data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

@Entity
public class AdaptiveAssessmentSkill extends Base{
	
	String skillName;
	
	@NotNull
	private String adaptiveAssessmentName;
	
	@NotNull
	private String qualifier1;
	
	
	private String qualifier2;
	
	
	private String qualifier3;
	
	
	private String qualifier4;
	
	
	private String qualifier5;
	
	Integer noOfQuestions = 10;
	
	
	//Boolean primary = true;
	
	Integer passPercentForLevel = 60;
	
	Integer noOfProgressions = 3;
	
	Integer noOfRegressions = 3;
	
	@Enumerated(EnumType.STRING)
	AdaptiveAssessmentSkillLevel startingLevel =AdaptiveAssessmentSkillLevel.LEVEL1;
	
	/**
	 * If criteriaType is none, then criteriaAssessment would be null
	 */
	@ManyToOne
	Test criteriaAssessment;
	
	@Enumerated(EnumType.STRING)
	AdaptiveAssessmentCriteriaType criteriaType = AdaptiveAssessmentCriteriaType.NONE;
	
	/**
	 * If passPercentForAllLevelsFixed is false - it means as no of level increase in assessment, the pass percentage for a user 
	 * increases by  the number represented in passPercentIncreaseGradient variable.
	 */
	Boolean passPercentForAllLevelsFixed = true;
	
	Integer passPercentIncreaseGradient = 5;
	
	@Column(length=900)
	String courseLinkRecommendation;
	
	@Column(length=900)
	String assessmentLinkAfterCourseCompletion;

	public String getQualifier1() {
		return qualifier1;
	}

	public void setQualifier1(String qualifier1) {
		this.qualifier1 = qualifier1;
	}

	public String getQualifier2() {
		return qualifier2;
	}

	public void setQualifier2(String qualifier2) {
		this.qualifier2 = qualifier2;
	}

	public String getQualifier3() {
		return qualifier3;
	}

	public void setQualifier3(String qualifier3) {
		this.qualifier3 = qualifier3;
	}

	public String getQualifier4() {
		return qualifier4;
	}

	public void setQualifier4(String qualifier4) {
		this.qualifier4 = qualifier4;
	}

	public String getQualifier5() {
		return qualifier5;
	}

	public void setQualifier5(String qualifier5) {
		this.qualifier5 = qualifier5;
	}

	public String getSkillName() {
		return skillName;
	}

	public void setSkillName(String skillName) {
		this.skillName = skillName;
	}

	

	public Integer getPassPercentForLevel() {
		return passPercentForLevel;
	}

	public void setPassPercentForLevel(Integer passPercentForLevel) {
		this.passPercentForLevel = passPercentForLevel;
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

	public AdaptiveAssessmentSkillLevel getStartingLevel() {
		return startingLevel;
	}

	public void setStartingLevel(AdaptiveAssessmentSkillLevel startingLevel) {
		this.startingLevel = startingLevel;
	}

	public Test getCriteriaAssessment() {
		return criteriaAssessment;
	}

	public void setCriteriaAssessment(Test criteriaAssessment) {
		this.criteriaAssessment = criteriaAssessment;
	}

	public AdaptiveAssessmentCriteriaType getCriteriaType() {
		return criteriaType;
	}

	public void setCriteriaType(AdaptiveAssessmentCriteriaType criteriaType) {
		this.criteriaType = criteriaType;
	}

	public Boolean getPassPercentForAllLevelsFixed() {
		return passPercentForAllLevelsFixed;
	}

	public void setPassPercentForAllLevelsFixed(Boolean passPercentForAllLevelsFixed) {
		this.passPercentForAllLevelsFixed = passPercentForAllLevelsFixed;
	}

	public Integer getPassPercentIncreaseGradient() {
		return passPercentIncreaseGradient;
	}

	public void setPassPercentIncreaseGradient(Integer passPercentIncreaseGradient) {
		this.passPercentIncreaseGradient = passPercentIncreaseGradient;
	}

	public String getAdaptiveAssessmentName() {
		return adaptiveAssessmentName;
	}

	public void setAdaptiveAssessmentName(String adaptiveAssessmentName) {
		this.adaptiveAssessmentName = adaptiveAssessmentName;
	}

	public Integer getNoOfQuestions() {
		return noOfQuestions;
	}

	public void setNoOfQuestions(Integer noOfQuestions) {
		this.noOfQuestions = noOfQuestions;
	}
	
	
	public String getQualifiers(){
		String ret = getQualifier1();
		if(getQualifier2() != null && getQualifier2().trim().length() > 0){
			ret += "-"+getQualifier2()+",";
		}
		
		if(getQualifier3() != null && getQualifier3().trim().length() > 0){
			ret += "-"+getQualifier3()+",";
		}
		
		
		
		if(getQualifier4() != null && getQualifier4().trim().length() > 0){
			ret += "-"+getQualifier4()+",";
		}
		
		if(getQualifier5() != null && getQualifier5().trim().length() > 0){
			ret += "-"+getQualifier5()+",";
		}
		
		ret = ret.trim();
		if(ret.endsWith(",")){
			ret = ret.substring(0, ret.length() - 1);
		}
		
		return ret;
	}

	public String getCourseLinkRecommendation() {
		return courseLinkRecommendation;
	}

	public void setCourseLinkRecommendation(String courseLinkRecommendation) {
		this.courseLinkRecommendation = courseLinkRecommendation;
	}

	public String getAssessmentLinkAfterCourseCompletion() {
		return assessmentLinkAfterCourseCompletion;
	}

	public void setAssessmentLinkAfterCourseCompletion(String assessmentLinkAfterCourseCompletion) {
		this.assessmentLinkAfterCourseCompletion = assessmentLinkAfterCourseCompletion;
	}
	
	

}
