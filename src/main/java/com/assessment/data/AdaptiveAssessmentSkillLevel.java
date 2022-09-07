package com.assessment.data;

public enum AdaptiveAssessmentSkillLevel {

	LEVEL1("LEVEL1"), LEVEL2("LEVEL2"), LEVEL3("LEVEL3"), LEVEL4("LEVEL4"), LEVEL5("LEVEL5"), NONE("NONE");
	
	String level;
	
	private  AdaptiveAssessmentSkillLevel(String level){
		this.level = level;
	}

	public String getLevel() {
		return level;
	}
	
	
}
