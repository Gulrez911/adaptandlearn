package com.assessment.web.dto;

public class SkillRecommDto {
	
	String skill;
	
	Integer score;
	
	String skillAlias;
	
	String color;

	public String getSkill() {
		if(this.skill.contains("NA")){
			String skl = this.skill.substring(0, this.skill.indexOf("NA"));
			skl = skl.replace("###", " - ");
			return skl;
		}
		else{
			String skl = this.skill;
			skl = skl.replace("###", " - ");
			return skl;
		}
	}

	public void setSkill(String skill) {
		
		this.skill = skill;
	}

	public Integer getScore() {
		return score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}

	public String getSkillAlias() {
		
		return skillAlias;
	}

	public void setSkillAlias(String skillAlias) {
		this.skillAlias = skillAlias;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}
	
	

}
