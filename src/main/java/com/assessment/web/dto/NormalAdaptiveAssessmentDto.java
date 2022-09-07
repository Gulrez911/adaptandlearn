package com.assessment.web.dto;

import java.util.List;

public class NormalAdaptiveAssessmentDto {
	
	String adaptiveAssessmentName;
	
	String adaptiveAssessmentLink;
	
	List<NormalAssessmentDto> tests;
	
	String intro;

	public String getAdaptiveAssessmentName() {
		return adaptiveAssessmentName;
	}

	public void setAdaptiveAssessmentName(String adaptiveAssessmentName) {
		this.adaptiveAssessmentName = adaptiveAssessmentName;
	}

	public String getAdaptiveAssessmentLink() {
		return adaptiveAssessmentLink;
	}

	public void setAdaptiveAssessmentLink(String adaptiveAssessmentLink) {
		this.adaptiveAssessmentLink = adaptiveAssessmentLink;
	}

	public List<NormalAssessmentDto> getTests() {
		return tests;
	}

	public void setTests(List<NormalAssessmentDto> tests) {
		this.tests = tests;
	}

	public String getIntro() {
		return intro;
	}

	public void setIntro(String intro) {
		this.intro = intro;
	}
	
	

}
