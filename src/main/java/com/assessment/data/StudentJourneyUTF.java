package com.assessment.data;

import javax.persistence.Entity;

@Entity
public class StudentJourneyUTF extends Base{

	String language;
	String next;
	String timeRemaining;
	String prev;
	String input;
	String output;
	String code;
	String runSystemTestCase;
	String runCode;
	String submit;
	String reviewInstructions;
	String questions;
	String testStatus;
	String questionCompleted;
	String forImageInstruction;
	String discription;
	String discription1;
	String discription2;
	String discription3;
	String answered;
	String notAnswered;
	String testStats;
	String termsAndConditions;
	String privacyPolicy;
	
	public String getTermsAndConditions() {
		return termsAndConditions;
	}
	public void setTermsAndConditions(String termsAndConditions) {
		this.termsAndConditions = termsAndConditions;
	}
	public String getPrivacyPolicy() {
		return privacyPolicy;
	}
	public void setPrivacyPolicy(String privacyPolicy) {
		this.privacyPolicy = privacyPolicy;
	}
	public String getTestStats() {
		return testStats;
	}
	public void setTestStats(String testStats) {
		this.testStats = testStats;
	}
	public String getAnswered() {
		return answered;
	}
	public void setAnswered(String answered) {
		this.answered = answered;
	}
	public String getNotAnswered() {
		return notAnswered;
	}
	public void setNotAnswered(String notAnswered) {
		this.notAnswered = notAnswered;
	}
	public String getForImageInstruction() {
		return forImageInstruction;
	}
	public void setForImageInstruction(String forImageInstruction) {
		this.forImageInstruction = forImageInstruction;
	}
	public String getDiscription() {
		return discription;
	}
	public void setDiscription(String discription) {
		this.discription = discription;
	}
	public String getDiscription1() {
		return discription1;
	}
	public void setDiscription1(String discription1) {
		this.discription1 = discription1;
	}
	public String getDiscription2() {
		return discription2;
	}
	public void setDiscription2(String discription2) {
		this.discription2 = discription2;
	}
	public String getDiscription3() {
		return discription3;
	}
	public void setDiscription3(String discription3) {
		this.discription3 = discription3;
	}
	public String getQuestionCompleted() {
		return questionCompleted;
	}
	public void setQuestionCompleted(String questionCompleted) {
		this.questionCompleted = questionCompleted;
	}
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
	public String getNext() {
		return next;
	}
	public void setNext(String next) {
		this.next = next;
	}
	public String getTimeRemaining() {
		return timeRemaining;
	}
	public void setTimeRemaining(String timeRemaining) {
		this.timeRemaining = timeRemaining;
	}
	public String getPrev() {
		return prev;
	}
	public void setPrev(String prev) {
		this.prev = prev;
	}
	public String getInput() {
		return input;
	}
	public void setInput(String input) {
		this.input = input;
	}
	public String getOutput() {
		return output;
	}
	public void setOutput(String output) {
		this.output = output;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getRunSystemTestCase() {
		return runSystemTestCase;
	}
	public void setRunSystemTestCase(String runSystemTestCase) {
		this.runSystemTestCase = runSystemTestCase;
	}
	public String getRunCode() {
		return runCode;
	}
	public void setRunCode(String runCode) {
		this.runCode = runCode;
	}
	public String getSubmit() {
		return submit;
	}
	public void setSubmit(String submit) {
		this.submit = submit;
	}
	public String getReviewInstructions() {
		return reviewInstructions;
	}
	public void setReviewInstructions(String reviewInstructions) {
		this.reviewInstructions = reviewInstructions;
	}
	public String getQuestions() {
		return questions;
	}
	public void setQuestions(String questions) {
		this.questions = questions;
	}
	public String getTestStatus() {
		return testStatus;
	}
	public void setTestStatus(String testStatus) {
		this.testStatus = testStatus;
	}
	
	
}
