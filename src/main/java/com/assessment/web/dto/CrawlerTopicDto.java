package com.assessment.web.dto;

public class CrawlerTopicDto {
	
	String topicName;
	
	String qualifier1;
	
	public CrawlerTopicDto(){
		
	}
	
	public CrawlerTopicDto(String topicName, String qualifier1){
		this.topicName = topicName;
		this.qualifier1 = qualifier1;
	}

	public String getTopicName() {
		return topicName;
	}

	public void setTopicName(String topicName) {
		this.topicName = topicName;
	}

	public String getQualifier1() {
		return qualifier1;
	}

	public void setQualifier1(String qualifier1) {
		this.qualifier1 = qualifier1;
	}
	
	

}
