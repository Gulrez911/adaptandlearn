package com.assessment.data;

import javax.persistence.Entity;

@Entity
public class TestIntroUTF extends Base{

	String language;
	String totalNoOfQuestion;
	String duration;
	String publishedOn;
	String currentAttempt;
	String shouldBeCompletedWithin;
	String startAssessment;
	String minutes;
	String attempt;
	String instruction;
	String isReadyTOask;
	String eAssessQuotes;
	
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
	public String getTotalNoOfQuestion() {
		return totalNoOfQuestion;
	}
	public void setTotalNoOfQuestion(String totalNoOfQuestion) {
		this.totalNoOfQuestion = totalNoOfQuestion;
	}
	public String getDuration() {
		return duration;
	}
	public void setDuration(String duration) {
		this.duration = duration;
	}
	public String getPublishedOn() {
		return publishedOn;
	}
	public void setPublishedOn(String publishedOn) {
		this.publishedOn = publishedOn;
	}
	public String getCurrentAttempt() {
		return currentAttempt;
	}
	public void setCurrentAttempt(String currentAttempt) {
		this.currentAttempt = currentAttempt;
	}
	public String getShouldBeCompletedWithin() {
		return shouldBeCompletedWithin;
	}
	public void setShouldBeCompletedWithin(String shouldBeCompletedWithin) {
		this.shouldBeCompletedWithin = shouldBeCompletedWithin;
	}
	public String getStartAssessment() {
		return startAssessment;
	}
	public void setStartAssessment(String startAssessment) {
		this.startAssessment = startAssessment;
	}
	public String getMinutes() {
		return minutes;
	}
	public void setMinutes(String minutes) {
		this.minutes = minutes;
	}
	public String getAttempt() {
		return attempt;
	}
	public void setAttempt(String attempt) {
		this.attempt = attempt;
	}
	public String getInstruction() {
		return instruction;
	}
	public void setInstruction(String instruction) {
		this.instruction = instruction;
	}
	public String getIsReadyTOask() {
		return isReadyTOask;
	}
	public void setIsReadyTOask(String isReadyTOask) {
		this.isReadyTOask = isReadyTOask;
	}
	public String geteAssessQuotes() {
		return eAssessQuotes;
	}
	public void seteAssessQuotes(String eAssessQuotes) {
		this.eAssessQuotes = eAssessQuotes;
	}
	
	
}
