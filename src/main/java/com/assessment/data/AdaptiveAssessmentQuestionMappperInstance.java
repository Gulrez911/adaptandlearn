package com.assessment.data;

import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class AdaptiveAssessmentQuestionMappperInstance extends Base{

	@ManyToOne
	Question question;
	
	
	String userChoices;
	
	Boolean correct = false;
	
	Boolean answered = false;
	
	@Column(length=2000)
	String questionText;
	
	
	@Transient
	String encodedQuestionText;
	
	@javax.validation.constraints.NotNull
	String testName;
	
	
	String sectionName;
	
	@javax.validation.constraints.NotNull
	String user;
	
	String codingOuputBySystemTestCase;
	
	@Lob
	String codeByUser;
	
	@Lob
	String reviewerComments;
	
	@Column(length=400)
	String workspaceUrl;
	
	/**
	 * just to make sure we know in which actual cluster did the request go.
	 */
	@Column(length=400)
	String actualWorkspaceUrl;
	
	@Transient
	String encodedUrl;
	
	@Column(length=200)
	String workSpaceId;
	
	@Column(length=400)
	String usageDocumentUrl;
	
	@Column
	Boolean workspaceSubmitted;
	
	@Transient
	String uerFullName;
	//Long userTestSessionId;
	
	@Transient
	String workspaceDateOfSubmission;
	
	Boolean confidence;
	
	Boolean codeCompilationErrors;
	
	Boolean codeRunTimeErrors;
	
	Boolean testCaseInputPositive;
	
	Boolean testCaseInputNegative;
	
	Boolean testCaseMinimalValue;
	
	Boolean testCaseMaximumValue;
	
	Boolean testCaseInvalidData;
	
	Integer noOfTestCases;
	
	Integer noOfTestCasesPassed;
	
	Integer functionalTestCases;
	Integer boundaryTestCases;
	Integer exceptionTestCases;
	
	Integer functionalTestCasesPassed;
	Integer boundaryTestCasesPassed;
	Integer exceptionTestCasesPassed;
	
	String courseName;
	
	String moduleName;
	
	String learningPathName;
	
	@Transient
	String lastDate;
	
	@Transient
	String[] fillInBlanksAnswer;
	
	
	
	String matchRight1;
	
	String matchRight2;
	
	String matchRight3;
	
	String matchRight4;
	
	String matchRight5;
	
	String matchRight6;
	
	@Lob
	String testCasesResultXml;
	
	Long testId;
	
	@Column(length=1000)
	String imageUploadUrl;
	
	@Column(length=1000)
	String videoUploadUrl;
	
	@Column(length=5000)
	String subjectiveText;
	
	Boolean subjective = false;
	
	Boolean markComplete;
	
	Integer marksAssignedInPercentIncaseSubjective;
	
	String reviewedBy;
	
	@Transient
	String style;
	
	@Column(length=1000)
	String reviewerCommentsForSubjectiveQuestion;
	
	Float codingScore;
	
	@Column(length=300)
	String githubUrlOfUser;
	
	@Column(length=300)
	String gitRepositoryUI;
	
	Boolean problemInLocalFullstack = false;
	
	String keyCloakUrl;
	
	Boolean ldapUserAdded = false;
	
	String ldapUserPassword;
	
	Boolean mmlFullStack;
	
	@Column(length=1000)
	String mmlStackUrl;
	
	Boolean adaptiveAssessment = false;
	
	@Enumerated(EnumType.STRING)
	AdaptiveAssessmentSkillLevel adaptiveAssessmentSkillLevel = AdaptiveAssessmentSkillLevel.NONE;

	Integer attempt;
	
	public AdaptiveAssessmentQuestionMappperInstance(){
		
	}
	
	public AdaptiveAssessmentQuestionMappperInstance(String companyId, Question question, String adaptiveAssessmentName, String email,
			AdaptiveAssessmentSkillLevel level, Integer attempt){
		this.setCompanyId(companyId);
		this.question = question;
		this.questionText = question.getQuestionText();
		this.testName = adaptiveAssessmentName;
		this.user = email;
		this.adaptiveAssessmentSkillLevel = level;
		this.attempt = attempt;
	}



	public Boolean getCorrect() {
		String type = getQuestion().getType();
		if(type != null && type.equals(QuestionType.CODING.getType())){
			Integer passThreshold = getQuestion().getPassPercentforCodingQAsPerWeightedScore();
			Float codingScore = getCodingScore();
			if(codingScore >= passThreshold){
				return true;
			}
			else{
				return false;
			}
		}
		return correct;
	}

	public Question getQuestion() {
		return question;
	}

	public void setQuestion(Question question) {
		this.question = question;
	}

	public void setCorrect(Boolean correct) {
		this.correct = correct;
	}

	public Boolean getAnswered() {
		return answered;
	}

	public void setAnswered(Boolean answered) {
		this.answered = answered;
	}

	public String getUserChoices() {
		return userChoices;
	}

	public void setUserChoices(String userChoices) {
		this.userChoices = userChoices;
		if(this.userChoices != null){
			if(userChoices.length() > 0) {
				setAnswered(true);
				String choices = getQuestion().getRightChoices();
				String correct[] = choices.split("-");
				String userC[] = userChoices.split("-");
				//String correct[] = choices.split("-");
				//String userC[] = userChoices.split("-");
				if(Arrays.equals(correct, userC)) {
					setCorrect(true);
				}
			}
			else {
				setAnswered(false);
			}
		}
		else{
			String type = getQuestion().getType();
			if(type.equals(QuestionType.CODING.getType()) || type.equals(QuestionType.FILL_BLANKS_MCQ.getType()) || type.equals(QuestionType.MATCH_FOLLOWING_MCQ.getType()) || type.equals(QuestionType.FULL_STACK_JAVA.getType()) || type.equals(QuestionType.FULLSTACK.getType())){
				setAnswered(true);
			}
			else{
				setAnswered(false);
			}
		}
			
		
		
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getSectionName() {
		return sectionName;
	}

	public void setSectionName(String sectionName) {
		this.sectionName = sectionName;
	}

	public String getTestName() {
		return testName;
	}

	public void setTestName(String testName) {
		this.testName = testName;
	}

	public String getQuestionText() {
		return questionText;
	}

	public void setQuestionText(String questionText) {
		this.questionText = questionText;
	}

	public String getCodingOuputBySystemTestCase() {
		return codingOuputBySystemTestCase;
	}

	public void setCodingOuputBySystemTestCase(String codingOuputBySystemTestCase) {
		codingOuputBySystemTestCase = codingOuputBySystemTestCase == null?"":(codingOuputBySystemTestCase.trim());
		this.codingOuputBySystemTestCase = codingOuputBySystemTestCase;
		
		
			if(getCodeCompilationErrors()){
				setCorrect(false);
			}
		if(getQuestion() == null){
			return;
		}
			//System.out.println("in codingOuputBySystemTestCase "+codingOuputBySystemTestCase);
		//	System.out.println("in codingOuputBySystemTestCase2 "+getQuestionMapper().getQuestion().getHiddenOutputNegative());
		if(getQuestion().getHiddenOutputNegative() == null){
			setTestCaseInputNegative(false);
			//System.out.println("in setCodingOuputBySystemTestCase "+false);
			setCorrect(false);
		}
		else if(getQuestion().getHiddenOutputNegative().equalsIgnoreCase(codingOuputBySystemTestCase == null?"":codingOuputBySystemTestCase)){
			setTestCaseInputNegative(true);
			//System.out.println("in setCodingOuputBySystemTestCase "+true);
			setCorrect(true);
		}
		else{
			setTestCaseInputNegative(false);
			//System.out.println("in setCodingOuputBySystemTestCase "+false);
			setCorrect(false);
		}
		setAnswered(true);
	}

	public String getCodeByUser() {
		return codeByUser;
	}

	public void setCodeByUser(String codeByUser) {
		this.codeByUser = codeByUser;
	}

	public String getReviewerComments() {
		return reviewerComments;
	}

	public void setReviewerComments(String reviewerComments) {
		this.reviewerComments = reviewerComments;
	}

	public String getWorkspaceUrl() {
		return workspaceUrl;
	}

	public void setWorkspaceUrl(String workspaceUrl) {
		this.workspaceUrl = workspaceUrl;
	}

	public String getWorkSpaceId() {
		return workSpaceId;
	}

	public void setWorkSpaceId(String workSpaceId) {
		this.workSpaceId = workSpaceId;
	}

	public String getUsageDocumentUrl() {
		return usageDocumentUrl;
	}

	public void setUsageDocumentUrl(String usageDocumentUrl) {
		this.usageDocumentUrl = usageDocumentUrl;
	}

	public Boolean getWorkspaceSubmitted() {
		return workspaceSubmitted;
	}

	public void setWorkspaceSubmitted(Boolean workspaceSubmitted) {
		this.workspaceSubmitted = workspaceSubmitted;
	}

	public String getUerFullName() {
		return uerFullName;
	}

	public void setUerFullName(String uerFullName) {
		this.uerFullName = uerFullName;
	}

	public String getWorkspaceDateOfSubmission() {
		String pattern = "dd-MMM-yyyy";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
		if(getUpdateDate() == null) {
			if(getCreateDate() == null) {
				return "Not Available";
			}
			else {
				return simpleDateFormat.format(getCreateDate());
			}
			
		}
		else {
			return simpleDateFormat.format(getUpdateDate());
		}
	}

	public void setWorkspaceDateOfSubmission(String workspaceDateOfSubmission) {
		this.workspaceDateOfSubmission = workspaceDateOfSubmission;
	}

	public Boolean getConfidence() {
		return confidence;
	}

	public void setConfidence(Boolean confidence) {
		this.confidence = confidence;
	}

	public Boolean getTestCaseInputPositive() {
		if(this.testCaseInputPositive == null){
			return false;
		}
		return testCaseInputPositive;
	}

	public void setTestCaseInputPositive(Boolean testCaseInputPositive) {
		this.testCaseInputPositive = testCaseInputPositive;
	}

	public Boolean getTestCaseInputNegative() {
		if(this.testCaseInputNegative == null){
			return false;
		}
		return testCaseInputNegative;
	}

	public void setTestCaseInputNegative(Boolean testCaseInputNegative) {
		this.testCaseInputNegative = testCaseInputNegative;
	}

	public Boolean getTestCaseMinimalValue() {
		if(this.testCaseMinimalValue == null){
			return false;
		}
		return testCaseMinimalValue;
	}

	public void setTestCaseMinimalValue(Boolean testCaseMinimalValue) {
		this.testCaseMinimalValue = testCaseMinimalValue;
	}

	public Boolean getTestCaseMaximumValue() {
		if(this.testCaseMaximumValue == null){
			return false;
		}
		return testCaseMaximumValue;
	}

	public void setTestCaseMaximumValue(Boolean testCaseMaximumValue) {
		this.testCaseMaximumValue = testCaseMaximumValue;
	}

	public Boolean getTestCaseInvalidData() {
		if(this.testCaseInvalidData == null){
			return false;
		}
		return testCaseInvalidData;
	}

	public void setTestCaseInvalidData(Boolean testCaseInvalidData) {
		this.testCaseInvalidData = testCaseInvalidData;
	}

	public Boolean getCodeCompilationErrors() {
		if(this.codeCompilationErrors == null){
			return false;
		}
		return codeCompilationErrors;
	}

	public void setCodeCompilationErrors(Boolean codeCompilationErrors) {
		this.codeCompilationErrors = codeCompilationErrors;
	}

	public Boolean getCodeRunTimeErrors() {
			if(this.codeRunTimeErrors == null){
				return false;
			}
		return codeRunTimeErrors;
	}

	public void setCodeRunTimeErrors(Boolean codeRunTimeErrors) {
		this.codeRunTimeErrors = codeRunTimeErrors;
	}

	public String getEncodedUrl() {
		
		if(getWorkspaceUrl() == null || getWorkspaceUrl().trim().length() == 0){
			return "";
		}
		return URLEncoder.encode(Base64.getEncoder().encodeToString(getWorkspaceUrl().getBytes()));
	}

	public void setEncodedUrl(String encodedUrl) {
		this.encodedUrl = encodedUrl;
	}

	public String getEncodedQuestionText() {
		if(getQuestionText() == null){
			return "";
		}
		return new org.apache.commons.codec.binary.Base64().encodeAsString(getQuestionText().getBytes());
	}

	public void setEncodedQuestionText(String encodedQuestionText) {
		this.encodedQuestionText = encodedQuestionText;
	}

	public Integer getNoOfTestCases() {
		return noOfTestCases;
	}

	public void setNoOfTestCases(Integer noOfTestCases) {
		this.noOfTestCases = noOfTestCases;
	}

	public Integer getNoOfTestCasesPassed() {
		return noOfTestCasesPassed;
	}

	public void setNoOfTestCasesPassed(Integer noOfTestCasesPassed) {
		this.noOfTestCasesPassed = noOfTestCasesPassed;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public String getModuleName() {
		return moduleName;
	}

	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}

	public String getLearningPathName() {
		return learningPathName;
	}

	public void setLearningPathName(String learningPathName) {
		this.learningPathName = learningPathName;
	}

	public Integer getFunctionalTestCases() {
		return functionalTestCases;
	}

	public void setFunctionalTestCases(Integer functionalTestCases) {
		this.functionalTestCases = functionalTestCases;
	}

	public Integer getBoundaryTestCases() {
		return boundaryTestCases;
	}

	public void setBoundaryTestCases(Integer boundaryTestCases) {
		this.boundaryTestCases = boundaryTestCases;
	}

	public Integer getExceptionTestCases() {
		return exceptionTestCases;
	}

	public void setExceptionTestCases(Integer exceptionTestCases) {
		this.exceptionTestCases = exceptionTestCases;
	}

	public Integer getFunctionalTestCasesPassed() {
		return functionalTestCasesPassed;
	}

	public void setFunctionalTestCasesPassed(Integer functionalTestCasesPassed) {
		this.functionalTestCasesPassed = functionalTestCasesPassed;
	}

	public Integer getBoundaryTestCasesPassed() {
		return boundaryTestCasesPassed;
	}

	public void setBoundaryTestCasesPassed(Integer boundaryTestCasesPassed) {
		this.boundaryTestCasesPassed = boundaryTestCasesPassed;
	}

	public Integer getExceptionTestCasesPassed() {
		return exceptionTestCasesPassed;
	}

	public void setExceptionTestCasesPassed(Integer exceptionTestCasesPassed) {
		this.exceptionTestCasesPassed = exceptionTestCasesPassed;
	}

	public String getLastDate() {
		return lastDate;
	}

	public void setLastDate(String lastDate) {
		this.lastDate = lastDate;
	}

	public String[] getFillInBlanksAnswer() {
		return fillInBlanksAnswer;
	}

	public void setFillInBlanksAnswer(String[] fillInBlanksAnswer) {
		this.fillInBlanksAnswer = fillInBlanksAnswer;
	}

	public String getMatchRight1() {
		return matchRight1;
	}

	public void setMatchRight1(String matchRight1) {
		this.matchRight1 = matchRight1;
	}

	public String getMatchRight2() {
		return matchRight2;
	}

	public void setMatchRight2(String matchRight2) {
		this.matchRight2 = matchRight2;
	}

	public String getMatchRight3() {
		return matchRight3;
	}

	public void setMatchRight3(String matchRight3) {
		this.matchRight3 = matchRight3;
	}

	public String getMatchRight4() {
		return matchRight4;
	}

	public void setMatchRight4(String matchRight4) {
		this.matchRight4 = matchRight4;
	}

	public String getMatchRight5() {
		return matchRight5;
	}

	public void setMatchRight5(String matchRight5) {
		this.matchRight5 = matchRight5;
	}

	public String getMatchRight6() {
		return matchRight6;
	}

	public void setMatchRight6(String matchRight6) {
		this.matchRight6 = matchRight6;
	}

	public String getTestCasesResultXml() {
		return testCasesResultXml;
	}

	public void setTestCasesResultXml(String testCasesResultXml) {
		this.testCasesResultXml = testCasesResultXml;
	}

	public Long getTestId() {
		return testId;
	}

	public void setTestId(Long testId) {
		this.testId = testId;
	}

	public String getImageUploadUrl() {
		return imageUploadUrl;
	}

	public void setImageUploadUrl(String imageUploadUrl) {
		this.imageUploadUrl = imageUploadUrl;
	}

	public String getVideoUploadUrl() {
		return videoUploadUrl;
	}

	public void setVideoUploadUrl(String videoUploadUrl) {
		this.videoUploadUrl = videoUploadUrl;
	}

	public String getSubjectiveText() {
		return subjectiveText;
	}

	public void setSubjectiveText(String subjectiveText) {
		this.subjectiveText = subjectiveText;
	}

	public Boolean getSubjective() {
		return subjective;
	}

	public void setSubjective(Boolean subjective) {
		this.subjective = subjective;
	}

	public Boolean getMarkComplete() {
		return markComplete;
	}

	public void setMarkComplete(Boolean markComplete) {
		this.markComplete = markComplete;
	}

	public Integer getMarksAssignedInPercentIncaseSubjective() {
		return marksAssignedInPercentIncaseSubjective;
	}

	public void setMarksAssignedInPercentIncaseSubjective(Integer marksAssignedInPercentIncaseSubjective) {
		this.marksAssignedInPercentIncaseSubjective = marksAssignedInPercentIncaseSubjective;
	}

	public String getReviewedBy() {
		return reviewedBy;
	}

	public void setReviewedBy(String reviewedBy) {
		this.reviewedBy = reviewedBy;
	}

	
	@Transient
	@JsonIgnore
	public String getTimeOfAnswer(){
		Date dt = getUpdateDate() == null? getCreateDate():getUpdateDate();
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy hh:mm aa");
			if(dt != null){
				return dateFormat.format(dt);
			}
			else{
				return "NA";
			}
	}

	public String getStyle() {
		return style;
	}

	public void setStyle(String style) {
		this.style = style;
	}

	public String getReviewerCommentsForSubjectiveQuestion() {
		return reviewerCommentsForSubjectiveQuestion;
	}

	public void setReviewerCommentsForSubjectiveQuestion(String reviewerCommentsForSubjectiveQuestion) {
		this.reviewerCommentsForSubjectiveQuestion = reviewerCommentsForSubjectiveQuestion;
	}

	public Float getCodingScore() {
		Map<Integer, List<Boolean>> weight_Scores = new HashMap<>();
		Integer weightPositive = getQuestion().getWeightInputPositive() == null?1:getQuestion().getWeightInputPositive();
		Integer weightNegative = getQuestion().getWeightInputNegative() == null?1:getQuestion().getWeightInputNegative();
		Integer weightExtremePositive = getQuestion().getWeightExtremePositiveValue() == null?1:getQuestion().getWeightExtremePositiveValue();
		Integer weightExtremeMinimal = getQuestion().getWeightExtremeMinimalValue() == null ? 1: getQuestion().getWeightExtremeMinimalValue();
		Integer weightInvalidData = getQuestion().getWeightInvalidDataValue() == null ? 1: getQuestion().getWeightInvalidDataValue();
		List<Boolean> score = new ArrayList<Boolean>();
		score.add(getTestCaseInputPositive() == null?false:getTestCaseInputPositive());
		weight_Scores.put(weightPositive,score );
			
		if(weight_Scores.get(weightNegative) == null){
			List<Boolean> score2 = new ArrayList<Boolean>();
			score2.add(getTestCaseInputNegative() == null?false:getTestCaseInputNegative());
			weight_Scores.put(weightNegative,score2 );
		}
		else{
			weight_Scores.get(weightNegative).add(getTestCaseInputNegative() == null?false:getTestCaseInputNegative());
		}
		
		if(weight_Scores.get(weightExtremePositive) == null){
			List<Boolean> score2 = new ArrayList<Boolean>();
			score2.add(getTestCaseMaximumValue() == null?false:getTestCaseMaximumValue());
			weight_Scores.put(weightExtremePositive,score2 );
		}
		else{
			weight_Scores.get(weightExtremePositive).add(getTestCaseMaximumValue() == null?false:getTestCaseMaximumValue());
		}
		
		if(weight_Scores.get(weightExtremeMinimal) == null){
			List<Boolean> score2 = new ArrayList<Boolean>();
			score2.add(getTestCaseMinimalValue() == null ? false: getTestCaseMinimalValue());
			weight_Scores.put(weightExtremeMinimal,score2 );
		}
		else{
			weight_Scores.get(weightExtremeMinimal).add(getTestCaseMinimalValue() == null ? false: getTestCaseMinimalValue());
		}
		
		if(weight_Scores.get(weightInvalidData) == null){
			List<Boolean> score2 = new ArrayList<Boolean>();
			score2.add(getTestCaseInvalidData() == null?false:getTestCaseInvalidData() );
			weight_Scores.put(weightInvalidData,score2 );
		}
		else{
			weight_Scores.get(weightInvalidData).add(getTestCaseInvalidData() == null?false:getTestCaseInvalidData());
		}
		
		
		Integer totalWeight = 0;
		Float weightInToPercentageForWeight = 0f;
		for(Integer weightKey : weight_Scores.keySet()){
			Float total = 0f;
			Float passed = 0f;
			for(Boolean result : weight_Scores.get(weightKey)){
				total ++;
				if(result){
					passed ++;
				}
			}
			Float percentForWeight = (100 * passed) / total;
			weightInToPercentageForWeight += weightKey * percentForWeight;
			
			totalWeight += weightKey;
		}
		
		Float scoreAsPercent = weightInToPercentageForWeight / totalWeight;
		
		return scoreAsPercent;
	}

	public void setCodingScore(Float codingScore) {
		this.codingScore = codingScore;
	}

	public String getActualWorkspaceUrl() {
		return actualWorkspaceUrl;
	}

	public void setActualWorkspaceUrl(String actualWorkspaceUrl) {
		this.actualWorkspaceUrl = actualWorkspaceUrl;
	}

	public String getGithubUrlOfUser() {
		return githubUrlOfUser;
	}

	public void setGithubUrlOfUser(String githubUrlOfUser) {
		this.githubUrlOfUser = githubUrlOfUser;
	}

	public Boolean getProblemInLocalFullstack() {
		return problemInLocalFullstack;
	}

	public void setProblemInLocalFullstack(Boolean problemInLocalFullstack) {
		this.problemInLocalFullstack = problemInLocalFullstack;
	}

	public String getGitRepositoryUI() {
		return gitRepositoryUI;
	}

	public void setGitRepositoryUI(String gitRepositoryUI) {
		this.gitRepositoryUI = gitRepositoryUI;
	}

	public String getKeyCloakUrl() {
		return keyCloakUrl;
	}

	public void setKeyCloakUrl(String keyCloakUrl) {
		this.keyCloakUrl = keyCloakUrl;
	}

	public Boolean getLdapUserAdded() {
		return ldapUserAdded;
	}

	public void setLdapUserAdded(Boolean ldapUserAdded) {
		this.ldapUserAdded = ldapUserAdded;
	}

	public String getLdapUserPassword() {
		return ldapUserPassword;
	}

	public void setLdapUserPassword(String ldapUserPassword) {
		this.ldapUserPassword = ldapUserPassword;
	}

	public Boolean getMmlFullStack() {
		return mmlFullStack;
	}

	public void setMmlFullStack(Boolean mmlFullStack) {
		this.mmlFullStack = mmlFullStack;
	}

	public String getMmlStackUrl() {
		return mmlStackUrl;
	}

	public void setMmlStackUrl(String mmlStackUrl) {
		this.mmlStackUrl = mmlStackUrl;
	}

	public Boolean getAdaptiveAssessment() {
		return adaptiveAssessment;
	}

	public void setAdaptiveAssessment(Boolean adaptiveAssessment) {
		this.adaptiveAssessment = adaptiveAssessment;
	}

	public AdaptiveAssessmentSkillLevel getAdaptiveAssessmentSkillLevel() {
		return adaptiveAssessmentSkillLevel;
	}

	public void setAdaptiveAssessmentSkillLevel(AdaptiveAssessmentSkillLevel adaptiveAssessmentSkillLevel) {
		this.adaptiveAssessmentSkillLevel = adaptiveAssessmentSkillLevel;
	}

	public Integer getAttempt() {
		return attempt;
	}

	public void setAttempt(Integer attempt) {
		this.attempt = attempt;
	}
	
	
	
}
