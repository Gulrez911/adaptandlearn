package com.assessment.data;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Transient;

import com.assessment.web.dto.QualifierSkillLevelDto;

@Entity
public class AdaptiveAssessmentLevelInstance extends Base{
	
	private String adaptiveAssessmentName;
	
	@Enumerated(EnumType.STRING)
	AdaptiveAssessmentSkillLevel  level;
	
	Float averageScoreForLevel;
	
	Float weightedScoreForLevel;
	
	Boolean pass;
	
	String email;
	
	Integer attempt;
	
	Integer passThresholdInPercent;
	
	Boolean complete;
	
	@Transient
	String skills;
	
	@Transient
	List<QualifierSkillLevelDto> recommendations = new ArrayList<>();
	
	public AdaptiveAssessmentLevelInstance(){
		
	}
	
	public AdaptiveAssessmentLevelInstance(String companyId, String email, String testName, Integer attempt, AdaptiveAssessmentSkillLevel level){
		setCompanyId(companyId);
		this.email = email;
		this.adaptiveAssessmentName = testName;
		this.level = level;
		this.attempt = attempt;
	}

	public String getAdaptiveAssessmentName() {
		return adaptiveAssessmentName;
	}

	public void setAdaptiveAssessmentName(String adaptiveAssessmentName) {
		this.adaptiveAssessmentName = adaptiveAssessmentName;
	}

	public AdaptiveAssessmentSkillLevel getLevel() {
		return level;
	}

	public void setLevel(AdaptiveAssessmentSkillLevel level) {
		this.level = level;
	}

	public Float getAverageScoreForLevel() {
		return averageScoreForLevel;
	}

	public void setAverageScoreForLevel(Float averageScoreForLevel) {
		this.averageScoreForLevel = averageScoreForLevel;
	}

	public Float getWeightedScoreForLevel() {
		return weightedScoreForLevel;
	}

	public void setWeightedScoreForLevel(Float weightedScoreForLevel) {
		this.weightedScoreForLevel = weightedScoreForLevel;
	}

	public Boolean getPass() {
		return pass;
	}

	public void setPass(Boolean pass) {
		this.pass = pass;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getAttempt() {
		return attempt;
	}

	public void setAttempt(Integer attempt) {
		this.attempt = attempt;
	}

	public Boolean getComplete() {
		return complete;
	}

	public void setComplete(Boolean complete) {
		this.complete = complete;
	}

	public Integer getPassThresholdInPercent() {
		return passThresholdInPercent;
	}

	public void setPassThresholdInPercent(Integer passThresholdInPercent) {
		this.passThresholdInPercent = passThresholdInPercent;
	}
	
	
	@Transient
	public Integer getAverageScore(){
		return this.averageScoreForLevel.intValue();
	}

	public String getSkills() {
		return skills;
	}

	public void setSkills(String skills) {
		this.skills = skills;
	}

	public List<QualifierSkillLevelDto> getRecommendations() {
		return recommendations;
	}

	public void setRecommendations(List<QualifierSkillLevelDto> recommendations) {
		this.recommendations = recommendations;
	}
	
	
	

}
