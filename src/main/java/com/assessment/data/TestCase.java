package com.assessment.data;

import javax.xml.bind.annotation.XmlElement;

public class TestCase {
	
	String name;
	
	String desc;
	
	Integer weight;
	
	Boolean mandatory;
	
	
	String expectedOuput;
	
	
	String testCaseType;
	
	/**
	 * Only for storing result
	 */
	Boolean passed;
	
	public TestCase(){
		
	}
	
	public TestCase(String name, String desc, Integer weight, Boolean mandatory, String testCaseType, String expectedOuput){
		this.name = name;
		this.desc = desc;
		this.weight = weight;
		this.mandatory = mandatory;
		this.testCaseType = testCaseType;
		this.expectedOuput = expectedOuput;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public Integer getWeight() {
		return weight;
	}

	public void setWeight(Integer weight) {
		this.weight = weight;
	}

	public Boolean getMandatory() {
		return mandatory;
	}

	public void setMandatory(Boolean mandatory) {
		this.mandatory = mandatory;
	}

	@XmlElement(name="expected-ouput")
	public String getExpectedOuput() {
		return expectedOuput;
	}

	public void setExpectedOuput(String expectedOuput) {
		this.expectedOuput = expectedOuput;
	}

	@XmlElement(name="test-case-type")
	public String getTestCaseType() {
		return testCaseType;
	}

	public void setTestCaseType(String testCaseType) {
		this.testCaseType = testCaseType;
	}

	@XmlElement(name="passed")
	public Boolean getPassed() {
		return passed;
	}

	public void setPassed(Boolean passed) {
		this.passed = passed;
	}
	
	

}
