package com.assessment.web.dto;

public class CodingAnswerDTO {
	
	String questionText;
	
	String programmingLanguage;
	
	Boolean codeCompilationErrors;
	
	
	
	Boolean basicCodeIntegrity;
	
	Boolean basicValidations;
	
	Boolean withstandLowInputs;
	
	Boolean withstandHighInputs;
	
	Boolean productionGrade;
	
	public CodingAnswerDTO(){
		
	}
	
	public CodingAnswerDTO(String questionText, String programmingLanguage, Boolean codeCompilationErrors, Boolean basicCodeIntegrity, Boolean basicValidations,  Boolean withstandLowInputs, Boolean withstandHighInputs, Boolean productionGrade ){
		this.questionText = questionText;
		this.programmingLanguage = programmingLanguage;
		this.codeCompilationErrors = codeCompilationErrors;
		this.basicCodeIntegrity = basicCodeIntegrity;
		this.basicValidations = basicValidations;
		this.withstandLowInputs = withstandLowInputs;
		this.withstandHighInputs = withstandHighInputs;
		this.productionGrade = productionGrade;
	}

	public Boolean getCodeCompilationErrors() {
		return codeCompilationErrors;
	}

	public void setCodeCompilationErrors(Boolean codeCompilationErrors) {
		this.codeCompilationErrors = codeCompilationErrors;
	}

	

	public Boolean getBasicCodeIntegrity() {
		return basicCodeIntegrity;
	}

	public void setBasicCodeIntegrity(Boolean basicCodeIntegrity) {
		this.basicCodeIntegrity = basicCodeIntegrity;
	}

	public Boolean getBasicValidations() {
		return basicValidations;
	}

	public void setBasicValidations(Boolean basicValidations) {
		this.basicValidations = basicValidations;
	}

	public Boolean getWithstandLowInputs() {
		return withstandLowInputs;
	}

	public void setWithstandLowInputs(Boolean withstandLowInputs) {
		this.withstandLowInputs = withstandLowInputs;
	}

	public Boolean getWithstandHighInputs() {
		return withstandHighInputs;
	}

	public void setWithstandHighInputs(Boolean withstandHighInputs) {
		this.withstandHighInputs = withstandHighInputs;
	}

	public Boolean getProductionGrade() {
		return productionGrade;
	}

	public void setProductionGrade(Boolean productionGrade) {
		this.productionGrade = productionGrade;
	}

	public String getQuestionText() {
		return questionText;
	}

	public void setQuestionText(String questionText) {
		this.questionText = questionText;
	}

	public String getProgrammingLanguage() {
		return programmingLanguage;
	}

	public void setProgrammingLanguage(String programmingLanguage) {
		this.programmingLanguage = programmingLanguage;
	}
	
	
	
	

}
