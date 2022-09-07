package com.assessment.web.dto;

import com.assessment.data.AdaptiveAssessmentSkillLevel;

public class AdaptiveQuestionNavigationTransition {
	
	Boolean proceed;
	
	AdaptiveAssessmentSkillLevel levelToGo;
	
	Boolean progression;
	
	AdaptiveAssessmentSkillLevel levelOfOrigin;

	public Boolean getProceed() {
		return proceed;
	}

	public void setProceed(Boolean proceed) {
		this.proceed = proceed;
	}

	public AdaptiveAssessmentSkillLevel getLevelToGo() {
		return levelToGo;
	}

	public void setLevelToGo(AdaptiveAssessmentSkillLevel levelToGo) {
		this.levelToGo = levelToGo;
	}

	public Boolean getProgression() {
		return progression;
	}

	public void setProgression(Boolean progression) {
		this.progression = progression;
	}

	public AdaptiveAssessmentSkillLevel getLevelOfOrigin() {
		return levelOfOrigin;
	}

	public void setLevelOfOrigin(AdaptiveAssessmentSkillLevel levelOfOrigin) {
		this.levelOfOrigin = levelOfOrigin;
	}

	
	
	

}
