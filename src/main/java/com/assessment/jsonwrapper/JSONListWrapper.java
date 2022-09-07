package com.assessment.jsonwrapper;

import java.util.List;

public class JSONListWrapper implements JsonBeanMarker{
	private List list;
	private String name;
	private List result;

	public List getResult() {
		return result;
	}

	public void setResult(List result) {
		this.result = result;
	}

	public List getFilterList() {
		return list;
	}

	public void setFilterList(List list) {
		this.list = list;
	}
	
	public String getName(){
		return name;
	}
	
	public void setName(String name){
		this.name = name;
	}
}
