package com.assessment.data;

import javax.persistence.Entity;

@Entity
public class CrawlerTopic extends Base{
	
	String qualifier1;
	
	String qualifier2;
	
	String topicName;
	
	String courseVideoLink;
	
	String assessmentLink;
	
	String url;

	Integer noOfQuestions = 0;

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

	public String getTopicName() {
		return topicName;
	}

	public void setTopicName(String topicName) {
		this.topicName = topicName;
	}

	public String getCourseVideoLink() {
		return courseVideoLink;
	}

	public void setCourseVideoLink(String courseVideoLink) {
		this.courseVideoLink = courseVideoLink;
	}

	public String getAssessmentLink() {
		return assessmentLink;
	}

	public void setAssessmentLink(String assessmentLink) {
		this.assessmentLink = assessmentLink;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Integer getNoOfQuestions() {
		return noOfQuestions;
	}

	public void setNoOfQuestions(Integer noOfQuestions) {
		this.noOfQuestions = noOfQuestions;
	}
	
	

}
