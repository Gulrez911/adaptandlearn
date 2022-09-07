package com.assessment.web.dto;

import com.assessment.data.AdaptiveAssessmentQuestionMappperInstance;

public class AdaptiveQMIWrapper {
	
	String message;
	
	AdaptiveAssessmentQuestionMappperInstance instance;
	
	public AdaptiveQMIWrapper(){
		
	}
	
	public AdaptiveQMIWrapper(AdaptiveAssessmentQuestionMappperInstance instance){
		this.instance = instance;
	}
	
	public AdaptiveQMIWrapper(String message){
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public AdaptiveAssessmentQuestionMappperInstance getInstance() {
		return instance;
	}

	public void setInstance(AdaptiveAssessmentQuestionMappperInstance instance) {
		this.instance = instance;
	}

	
}
