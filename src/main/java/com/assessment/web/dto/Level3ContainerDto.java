package com.assessment.web.dto;

import java.util.ArrayList;
import java.util.List;

public class Level3ContainerDto {

	String level3;
	
	Long id;
	
	List<Level4ContainerDto> level3containers = new ArrayList<Level4ContainerDto>();

	public String getLevel3() {
		return level3;
	}

	public void setLevel3(String level3) {
		this.level3 = level3;
	}

	public List<Level4ContainerDto> getLevel3containers() {
		return level3containers;
	}

	public void setLevel3containers(List<Level4ContainerDto> level3containers) {
		this.level3containers = level3containers;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	
	
}
