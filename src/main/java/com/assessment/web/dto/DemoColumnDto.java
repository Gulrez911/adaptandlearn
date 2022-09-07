package com.assessment.web.dto;

import java.util.ArrayList;
import java.util.List;

public class DemoColumnDto {

	String heading;
	
	List<DemoElementDto> elements = new ArrayList<DemoElementDto>();
	
	String skillsOverView;

	public String getHeading() {
		return heading;
	}

	public void setHeading(String heading) {
		this.heading = heading;
	}

	public List<DemoElementDto> getElements() {
		return elements;
	}

	public void setElements(List<DemoElementDto> elements) {
		this.elements = elements;
	}

	public String getSkillsOverView() {
		return skillsOverView;
	}

	public void setSkillsOverView(String skillsOverView) {
		this.skillsOverView = skillsOverView;
	}
	
	
}
