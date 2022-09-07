package com.assessment.data;

import javax.persistence.Entity;

@Entity
public class TestCompletionUTF extends Base{

	String language;
	String completeTest;
	String completeTestIn;
	String attempt;
	String completeByYouAt;
	String sharedTest;
	String result;
	String totalQuestions;
	String totalMarks;
	String passPercentage;
	String resultPercentage;
	String status;
	String pass;
	String fail;
	String learnerTraits;
	String passThreshold;
	String weightedScore;
	String syntaxKnowledge;
	String yes;
	String no;
	String download;
	String overAllPercentage;
	
	
	public String getOverAllPercentage() {
		return overAllPercentage;
	}
	public void setOverAllPercentage(String overAllPercentage) {
		this.overAllPercentage = overAllPercentage;
	}
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
	public String getCompleteTest() {
		return completeTest;
	}
	public void setCompleteTest(String completeTest) {
		this.completeTest = completeTest;
	}
	public String getCompleteTestIn() {
		return completeTestIn;
	}
	public void setCompleteTestIn(String completeTestIn) {
		this.completeTestIn = completeTestIn;
	}
	public String getAttempt() {
		return attempt;
	}
	public void setAttempt(String attempt) {
		this.attempt = attempt;
	}
	public String getCompleteByYouAt() {
		return completeByYouAt;
	}
	public void setCompleteByYouAt(String completeByYouAt) {
		this.completeByYouAt = completeByYouAt;
	}
	public String getSharedTest() {
		return sharedTest;
	}
	public void setSharedTest(String sharedTest) {
		this.sharedTest = sharedTest;
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	public String getTotalQuestions() {
		return totalQuestions;
	}
	public void setTotalQuestions(String totalQuestions) {
		this.totalQuestions = totalQuestions;
	}
	public String getTotalMarks() {
		return totalMarks;
	}
	public void setTotalMarks(String totalMarks) {
		this.totalMarks = totalMarks;
	}
	public String getPassPercentage() {
		return passPercentage;
	}
	public void setPassPercentage(String passPercentage) {
		this.passPercentage = passPercentage;
	}
	public String getResultPercentage() {
		return resultPercentage;
	}
	public void setResultPercentage(String resultPercentage) {
		this.resultPercentage = resultPercentage;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
	public String getFail() {
		return fail;
	}
	public void setFail(String fail) {
		this.fail = fail;
	}
	public String getLearnerTraits() {
		return learnerTraits;
	}
	public void setLearnerTraits(String learnerTraits) {
		this.learnerTraits = learnerTraits;
	}
	public String getPassThreshold() {
		return passThreshold;
	}
	public void setPassThreshold(String passThreshold) {
		this.passThreshold = passThreshold;
	}
	public String getWeightedScore() {
		return weightedScore;
	}
	public void setWeightedScore(String weightedScore) {
		this.weightedScore = weightedScore;
	}
	public String getSyntaxKnowledge() {
		return syntaxKnowledge;
	}
	public void setSyntaxKnowledge(String syntaxKnowledge) {
		this.syntaxKnowledge = syntaxKnowledge;
	}
	public String getYes() {
		return yes;
	}
	public void setYes(String yes) {
		this.yes = yes;
	}
	public String getNo() {
		return no;
	}
	public void setNo(String no) {
		this.no = no;
	}
	public String getDownload() {
		return download;
	}
	public void setDownload(String download) {
		this.download = download;
	}
	
}
