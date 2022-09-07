package com.assessment.web.dto;

import java.util.ArrayList;
import java.util.List;

import com.assessment.data.AssessmentUploadData;

public class DemoPageDto {
	
	String demoPage;
	
	List<DemoColumnDto> columns = new ArrayList<DemoColumnDto>();
	
	String demoPageLink;
	
	
	
	List<String> selected1 = new ArrayList<>();
	List<String> selected2 = new ArrayList<>();
	List<String> selected3 = new ArrayList<>();
	List<String> selected4 = new ArrayList<>();
	List<String> selected5 = new ArrayList<>();
	
	
	String headingColumn1;
	String headingColumn2;
	String headingColumn3;
	String headingColumn4;
	String headingColumn5;
	
	Long id;

	public List<DemoColumnDto> getColumns() {
		return columns;
	}

	public void setColumns(List<DemoColumnDto> columns) {
		this.columns = columns;
	}

	public String getDemoPage() {
		return demoPage;
	}

	public void setDemoPage(String demoPage) {
		this.demoPage = demoPage;
	}

	public String getDemoPageLink() {
		return demoPageLink;
	}

	public void setDemoPageLink(String demoPageLink) {
		this.demoPageLink = demoPageLink;
	}

	

	public String getHeadingColumn1() {
		return headingColumn1;
	}

	public void setHeadingColumn1(String headingColumn1) {
		this.headingColumn1 = headingColumn1;
	}

	public String getHeadingColumn2() {
		return headingColumn2;
	}

	public void setHeadingColumn2(String headingColumn2) {
		this.headingColumn2 = headingColumn2;
	}

	public String getHeadingColumn3() {
		return headingColumn3;
	}

	public void setHeadingColumn3(String headingColumn3) {
		this.headingColumn3 = headingColumn3;
	}

	public String getHeadingColumn4() {
		return headingColumn4;
	}

	public void setHeadingColumn4(String headingColumn4) {
		this.headingColumn4 = headingColumn4;
	}

	public String getHeadingColumn5() {
		return headingColumn5;
	}

	public void setHeadingColumn5(String headingColumn5) {
		this.headingColumn5 = headingColumn5;
	}

	public List<String> getSelected1() {
		return selected1;
	}

	public void setSelected1(List<String> selected1) {
		this.selected1 = selected1;
	}

	public List<String> getSelected2() {
		return selected2;
	}

	public void setSelected2(List<String> selected2) {
		this.selected2 = selected2;
	}

	public List<String> getSelected3() {
		return selected3;
	}

	public void setSelected3(List<String> selected3) {
		this.selected3 = selected3;
	}

	public List<String> getSelected4() {
		return selected4;
	}

	public void setSelected4(List<String> selected4) {
		this.selected4 = selected4;
	}

	public List<String> getSelected5() {
		return selected5;
	}

	public void setSelected5(List<String> selected5) {
		this.selected5 = selected5;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	

}
