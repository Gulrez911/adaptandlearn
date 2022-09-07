package com.assessment.data;

import javax.persistence.Entity;

@Entity
public class CategoryContext extends Base {
	
	
	String level1;
	
	String level2;
	
	String level3;
	
	String level4;
	
	String assessmentUpoloadDataName;
	
	String assessmentName;
	
	Boolean collective = true;
	
	String domain;

	public String getLevel1() {
		return level1;
	}

	public void setLevel1(String level1) {
		this.level1 = level1;
	}

	public String getLevel2() {
		return level2;
	}

	public void setLevel2(String level2) {
		this.level2 = level2;
	}

	public String getLevel3() {
		return level3;
	}

	public void setLevel3(String level3) {
		this.level3 = level3;
	}

	public String getLevel4() {
		return level4;
	}

	public void setLevel4(String level4) {
		this.level4 = level4;
	}

	public String getAssessmentUpoloadDataName() {
		return assessmentUpoloadDataName;
	}

	public void setAssessmentUpoloadDataName(String assessmentUpoloadDataName) {
		this.assessmentUpoloadDataName = assessmentUpoloadDataName;
	}

	public String getAssessmentName() {
		return assessmentName;
	}

	public void setAssessmentName(String assessmentName) {
		this.assessmentName = assessmentName;
	}

	public Boolean getCollective() {
		return collective;
	}

	public void setCollective(Boolean collective) {
		this.collective = collective;
	}

	public String getDomain() {
		return domain;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}
	
	
	
	
	

}
