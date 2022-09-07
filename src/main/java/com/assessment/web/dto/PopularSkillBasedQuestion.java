package com.assessment.web.dto;

public class PopularSkillBasedQuestion {
	
	String skillCategory;
	
	Long count;
	
	public PopularSkillBasedQuestion(){
		
	}
	
	public PopularSkillBasedQuestion(String qualifier1, Long count){
		this.skillCategory = qualifier1;
		this.count = count;
	}

	public String getSkillCategory() {
		return skillCategory;
	}

	public void setSkillCategory(String skillCategory) {
		this.skillCategory = skillCategory;
	}

	public Long getCount() {
		return count;
	}

	public void setCount(Long count) {
		this.count = count;
	}
	
	

}
