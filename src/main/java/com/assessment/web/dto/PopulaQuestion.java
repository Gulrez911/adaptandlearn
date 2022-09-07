package com.assessment.web.dto;

public class PopulaQuestion {

	
String questionText;
	
	Long count;
	
	public PopulaQuestion(){
		
	}
	
	public PopulaQuestion(String questionText, Long count){
		this.questionText = questionText;
		this.count = count;
	}

	public String getQuestionText() {
		return questionText;
	}

	public void setQuestionText(String questionText) {
		this.questionText = questionText;
	}

	public Long getCount() {
		return count;
	}

	public void setCount(Long count) {
		this.count = count;
	}
	
	

	
}
