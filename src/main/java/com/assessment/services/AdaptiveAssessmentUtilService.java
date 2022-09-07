package com.assessment.services;

import javax.servlet.http.HttpServletRequest;

import com.assessment.web.dto.AdaptiveQMIWrapper;
import com.assessment.web.dto.newlookandfeel.AdaptiveAssessmentInstanceDTO;

public interface AdaptiveAssessmentUtilService {
	
	public void setAnswers(HttpServletRequest request, AdaptiveAssessmentInstanceDTO currentAnswer) ;
	
	public AdaptiveQMIWrapper nextQuestion(HttpServletRequest request);
	
	public AdaptiveQMIWrapper prevQuestion(HttpServletRequest request);
	
	public AdaptiveQMIWrapper markProgression(HttpServletRequest request);
	
	public AdaptiveQMIWrapper markRegression(HttpServletRequest request);

}
