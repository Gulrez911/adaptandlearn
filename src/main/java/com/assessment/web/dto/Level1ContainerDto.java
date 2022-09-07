package com.assessment.web.dto;

import java.util.ArrayList;
import java.util.List;

public class Level1ContainerDto {
	
	String level1;
	
	Long id;
	
	List<Level2ContainerDto> level2Containers = new ArrayList<Level2ContainerDto>();

	public List<Level2ContainerDto> getLevel2Containers() {
		return level2Containers;
	}

	public void setLevel2Containers(List<Level2ContainerDto> level2Containers) {
		this.level2Containers = level2Containers;
	}

	public String getLevel1() {
		return level1;
	}

	public void setLevel1(String level1) {
		this.level1 = level1;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	
	

}
