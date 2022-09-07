package com.assessment.web.dto;

import java.util.ArrayList;
import java.util.List;

public class Level2ContainerDto {
	
	String level2;
	
	Long id;
	
	List<Level3ContainerDto> level3containers = new ArrayList<Level3ContainerDto>();

	public List<Level3ContainerDto> getLevel3containers() {
		return level3containers;
	}

	public void setLevel3containers(List<Level3ContainerDto> level3containers) {
		this.level3containers = level3containers;
	}

	public String getLevel2() {
		return level2;
	}

	public void setLevel2(String level2) {
		this.level2 = level2;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	
	
}
