package com.assessment.web.dto;

public class LevelDto {
	
	String level;
	
	Long id;
	
	public LevelDto(){
		
	}
	
	public LevelDto(String level, Long id){
		this.level = level;
		this.id = id;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	

}
