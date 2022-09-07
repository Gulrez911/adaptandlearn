package com.assessment.web.dto;

public class QualifierSkillLevelDto {
	
	String qualifier1;
	
	String qualifier2;
	
	String qualifier3;
	
	String qualifier4;
	
	String qualifier5;
	
	String overAll;
	
	Float percentage;
	
	String allQualifiers;
	
	public QualifierSkillLevelDto(){
		
	}
	
	public QualifierSkillLevelDto(String qualifier1, String qualifier2, String qualifier3, String qualifier4, String qualifier5){
		this.qualifier1 = qualifier1;
		this.qualifier2 = qualifier2;
		this.qualifier3 = qualifier3;
		this.qualifier4 = qualifier4;
		this.qualifier5 = qualifier5;
	}

	public String getQualifier1() {
		return qualifier1;
	}

	public void setQualifier1(String qualifier1) {
		this.qualifier1 = qualifier1;
	}

	public String getQualifier2() {
		return qualifier2;
	}

	public void setQualifier2(String qualifier2) {
		this.qualifier2 = qualifier2;
	}

	public String getQualifier3() {
		return qualifier3;
	}

	public void setQualifier3(String qualifier3) {
		this.qualifier3 = qualifier3;
	}

	public String getQualifier4() {
		return qualifier4;
	}

	public void setQualifier4(String qualifier4) {
		this.qualifier4 = qualifier4;
	}

	public String getQualifier5() {
		return qualifier5;
	}

	public void setQualifier5(String qualifier5) {
		this.qualifier5 = qualifier5;
	}

	public String getOverAll() {
		return overAll;
	}

	public void setOverAll(String overAll) {
		this.overAll = overAll;
	}

	public Float getPercentage() {
		return percentage;
	}

	public void setPercentage(Float percentage) {
		this.percentage = percentage;
	}

	public String getAllQualifiers() {
		String all = getQualifier1()+" - "+getQualifier2()+" - "+getQualifier3()+" - "+getQualifier4()+" - "+getQualifier5();
		//all = all.replace(" - NA ", "").trim();
		all = all.replace("NA", "").trim();
		all = all.replace("-", " ").trim();
		return all;
		
		//		if(getQualifier2() == null || getQualifier2().equals("NA")){
//			return all;
//		}
//		else{
//			all += " - "+getQualifier2();
//			if(getQualifier3() == null || getQualifier3().equals("NA")){
//				return all;
//			}
//			else{
//				all += " - "+getQualifier3();
//			}
//		}
		//return allQualifiers;
	}

	public void setAllQualifiers(String allQualifiers) {
		this.allQualifiers = allQualifiers;
	}

	
	
	
	

}
