package com.assessment.data;

public enum AdaptiveAssessmentCriteriaType {
	
	
	
	NONE("NONE"), START_WITH_ASSESSMENT("START_WITH_ASSESSMENT");
	
	private String type;
	
	private AdaptiveAssessmentCriteriaType(String type){
		this.type = type;
	}

	public String getType() {
		return type;
	}
	
	

}
