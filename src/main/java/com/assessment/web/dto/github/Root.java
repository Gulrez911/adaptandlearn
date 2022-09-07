package com.assessment.web.dto.github;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Root {
	@JsonProperty("MyArray") 
    public List<MyArray> myArray = new ArrayList<MyArray>();

	public List<MyArray> getMyArray() {
		return myArray;
	}

	public void setMyArray(List<MyArray> myArray) {
		this.myArray = myArray;
	}
	
	
}
