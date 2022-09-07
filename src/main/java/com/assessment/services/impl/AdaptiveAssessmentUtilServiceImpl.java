package com.assessment.services.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.assessment.common.AssessmentGenericException;
import com.assessment.common.CompileData;
import com.assessment.common.CompileOutput;
import com.assessment.common.LanguageCodes;
import com.assessment.common.util.AdaptiveConstants;
import com.assessment.data.AdaptiveAssessment;
import com.assessment.data.AdaptiveAssessmentInstance;
import com.assessment.data.AdaptiveAssessmentLevelInstance;
import com.assessment.data.AdaptiveAssessmentQuestionMappperInstance;
import com.assessment.data.AdaptiveAssessmentSkill;
import com.assessment.data.AdaptiveAssessmentSkillLevel;
import com.assessment.data.Company;
import com.assessment.data.DifficultyLevel;
import com.assessment.data.Question;
import com.assessment.data.QuestionType;
import com.assessment.data.User;
import com.assessment.repositories.AdaptiveAssessmentRepository;
import com.assessment.services.AdaptiveAssessmentInstanceService;
import com.assessment.services.AdaptiveAssessmentLevelInstanceService;
import com.assessment.services.AdaptiveAssessmentQuestionMappperInstanceService;
import com.assessment.services.AdaptiveAssessmentSkillService;
import com.assessment.services.AdaptiveAssessmentUtilService;
import com.assessment.services.CompanyService;
import com.assessment.services.QuestionService;
import com.assessment.web.dto.AdaptiveQMIWrapper;
import com.assessment.web.dto.AdaptiveQuestionNavigationTransition;
import com.assessment.web.dto.newlookandfeel.AdaptiveAssessmentInstanceDTO;

@Service
@Transactional
public class AdaptiveAssessmentUtilServiceImpl implements AdaptiveAssessmentUtilService{
	
	Logger logger = LoggerFactory.getLogger(AdaptiveAssessmentUtilServiceImpl.class);
	
	@Autowired
	AdaptiveAssessmentQuestionMappperInstanceService mapperInstanceService;
	
	@Autowired
	CompilerService compiler;
	
	@Autowired
	AdaptiveAssessmentQuestionMappperInstanceService mapperInstanceservice;
	
	@Autowired
	AdaptiveAssessmentLevelInstanceService levelInstanceService;
	
	@Autowired
	AdaptiveAssessmentSkillService levelService;
	
	@Autowired
	QuestionService questionservice;
	
	@Autowired
	AdaptiveAssessmentInstanceService assessmentService;
	
	@Autowired
	AdaptiveAssessmentRepository adaptiveAssessmentRepository;
	
	@Autowired
	CompanyService companyService;
	
	private Integer getAttempt(User user, AdaptiveAssessment test, AdaptiveAssessmentSkillLevel currLevel){
		Integer attempt = 1;
		List<AdaptiveAssessmentLevelInstance> levelInstances = levelInstanceService.findAdaptiveAssessmentLevelInstances(user.getCompanyId(), test.getAdaptiveAssessmentName(), user.getEmail(), currLevel);
 		
 		if(levelInstances.size() == 0){
 			attempt = 1;
 		}
 		else{
 			AdaptiveAssessmentLevelInstance last = levelInstances.get(levelInstances.size() - 1);
 			if(last.getComplete()){
	 			attempt = levelInstances.size() + 1;
	 		}
	 		else{
	 			attempt = levelInstances.size();
	 		}
 		}
 		
 		return attempt;
	}

	@Override
	public void setAnswers(HttpServletRequest request, AdaptiveAssessmentInstanceDTO currentAnswer) {
		// TODO Auto-generated method stub
			Question currQ = (Question)request.getSession().getAttribute("currentQuestion");
			User user = (User) request.getSession().getAttribute("user");
			// Test test = (Test) request.getSession().getAttribute("test");
			 AdaptiveAssessment test = (AdaptiveAssessment) request.getSession().getAttribute("adaptiveAssessmentTest");
			 AdaptiveAssessmentQuestionMappperInstance questionMappperInstance = (AdaptiveAssessmentQuestionMappperInstance)request.getSession().getAttribute("questionMappperInstance");
			 questionMappperInstance.setCompanyId(user.getCompanyId());
			 questionMappperInstance.setCompanyName(user.getCompanyName());
			 questionMappperInstance.setUser(user.getEmail());
			 questionMappperInstance.setUerFullName(user.getFirstName()+" "+user.getLastName());
		//	questionInstanceDto.getQuestionMapperInstance().setSectionName(currentSection.getSection().getSectionName());
			 questionMappperInstance.setTestName(test.getAdaptiveAssessmentName());
			/**
			 * end adding mandatory info on questionInstanceDto.getQuestionMapperInstance() 
			 */
			
			 /**
			  * Code to determine curr level
			  */
			 AdaptiveAssessmentInstance instance = assessmentService.findAdaptiveAssessmentInstance(test.getAdaptiveAssessmentName(), user.getCompanyId(), user.getEmail());
			 AdaptiveAssessmentSkillLevel currLevel = AdaptiveAssessmentSkillLevel.LEVEL1;
			 	if(instance != null){
			 		currLevel = instance.getCurrentLevel();
			 	}
			 	questionMappperInstance.setAdaptiveAssessmentSkillLevel(currLevel);
			 	Integer attempt = getAttempt(user, test, currLevel);
			 	questionMappperInstance.setAttempt(attempt);
			 	/**
			 	 * end  Code to determine curr level
			 	 */
			 	
			/**	
			 * Add code for evaluating coding engine Q
			 */
			if(questionMappperInstance.getQuestion().getQuestionType() == null){
				questionMappperInstance.getQuestion().setQuestionType(QuestionType.MCQ);
			}
			String type = questionMappperInstance.getQuestion().getQuestionType().getType();
			//Question q = questionInstanceDto.getQuestion();
			if(type != null && type.equals(QuestionType.CODING.getType())){
				//evaluateCodingAnswer(currentQuestion, questionInstanceDto);//here questionInstanceDto is updated with compilation results
				evaluateCodingAnswer(currentAnswer, questionMappperInstance);
				
				/**
				 * Question level persistence code added
				 */
				//sectionInstanceService.saveOrUpdateAnswer(questionInstanceDto.getQuestionMapperInstance());
				mapperInstanceService.saveOrUpdate(questionMappperInstance);
				/**
				 * End Question level persistence
				 */
				//break;
			}
			if(type != null && type.equals(QuestionType.FILL_BLANKS_MCQ.getType())){
				//evaluateFillInBlankQuestion(currentQuestion, questionInstanceDto);
				//sectionInstanceDto.setQuestionInstanceDtos(currentSection.getQuestionInstanceDtos());
				/**
				 * Question level persistence code added
				 */
				//sectionInstanceService.saveOrUpdateAnswer(questionInstanceDto.getQuestionMapperInstance());
				/**
				 * End Question level persistence
				 */
				//break;
				//fib eval will be added later
				mapperInstanceService.saveOrUpdate(questionMappperInstance);
			}
			
			if(type != null && type.equals(QuestionType.MATCH_FOLLOWING_MCQ.getType())){
				evaluateMTFQuestion(currentAnswer, questionMappperInstance);
				//sectionInstanceDto.setQuestionInstanceDtos(currentSection.getQuestionInstanceDtos());
				/**
				 * Question level persistence code added
				 */
				//sectionInstanceService.saveOrUpdateAnswer(questionInstanceDto.getQuestionMapperInstance());
				/**
				 * End Question level persistence
				 */
				//break;
				mapperInstanceService.saveOrUpdate(questionMappperInstance);
			}
			
			if(type != null && type.equals(QuestionType.IMAGE_UPLOAD_BY_USER.getType())){
				mapperInstanceService.saveOrUpdate(questionMappperInstance);
			}
			
			if(type != null && type.equals(QuestionType.VIDEO_UPLOAD_BY_USER.getType())){
				mapperInstanceService.saveOrUpdate(questionMappperInstance);
			}
			
			if(type != null && type.equals(QuestionType.SUBJECTIVE_TEXT.getType())){
				mapperInstanceService.saveOrUpdate(questionMappperInstance);
			}
			
			
			/**
			 * End Add code for evaluating coding engine Q
			 */
			
			
			String userChoices = "";
			if(currentAnswer.getOne()) {
				userChoices = "Choice 1";
				
			}
			else {
				//questionInstanceDto.setOne(false);
			}
			
			if(currentAnswer.getTwo()) {
				if(userChoices.length() > 0) {
					userChoices += "-Choice 2";
					//questionInstanceDto.setTwo(true);
				}
				else {
					userChoices = "Choice 2";
					//questionInstanceDto.setTwo(true);
				}
			}
			else {
				//questionInstanceDto.setTwo(false);
			}
			
			if(currentAnswer.getThree()) {
				if(userChoices.length() > 0) {
					userChoices += "-Choice 3";
					//questionInstanceDto.setThree(true);
				}
				else {
					userChoices = "Choice 3";
					//questionInstanceDto.setThree(true);
				}
			}
			else {
				//questionInstanceDto.setThree(false);
			}
			
			if(currentAnswer.getFour()) {
				if(userChoices.length() > 0) {
					userChoices += "-Choice 4";
					//questionInstanceDto.setFour(true);
				}
				else {
					userChoices = "Choice 4";
					//questionInstanceDto.setFour(true);
				}
			}
			else {
				//questionInstanceDto.setFour(false);
			}
			
			
			if(currentAnswer.getFive()) {
				if(userChoices.length() > 0) {
					userChoices += "-Choice 5";
					//questionInstanceDto.setFive(true);
				}
				else {
					userChoices = "Choice 5";
					//questionInstanceDto.setFive(true);
				}
			}
			else {
				//questionInstanceDto.setFive(false);
			}
			
			if(currentAnswer.getSix()) {
				if(userChoices.length() > 0) {
					userChoices += "-Choice 6";
					//questionInstanceDto.setSix(true);
				}
				else {
					userChoices = "Choice 6";
					//questionInstanceDto.setSix(true);
				}
			}
			else {
				//questionInstanceDto.setSix(false);
			}
			
			
			questionMappperInstance.setUserChoices(userChoices);
			/**
			 * Confidence based assessment
			 */
			questionMappperInstance.setConfidence(currentAnswer.getConfidence());
			//questionMappperInstance.setRadioAnswer(currentAnswer.getRadioAnswer());
			//sectionInstanceDto.setQuestionInstanceDtos(currentSection.getQuestionInstanceDtos());
			/**
			 * Question level persistence code added
			 */
			
			//sectionInstanceService.saveOrUpdateAnswer(questionInstanceDto.getQuestionMapperInstance());
			mapperInstanceService.saveOrUpdate(questionMappperInstance);
			/**
			 * End Question level persistence
			 */
			//break;
	}
	
	private void evaluateCodingAnswer(AdaptiveAssessmentInstanceDTO currentQuestion,  AdaptiveAssessmentQuestionMappperInstance questionInstanceDto){
		 Question q = questionInstanceDto.getQuestion();
		 boolean answered = questionInstanceDto.getAnswered() == null?false:questionInstanceDto.getAnswered();
			if(answered){
				if(currentQuestion.getInput() == null){
					answered = true; // this situation should not occur
				}
				
				if(questionInstanceDto.getCodeByUser() == null){
					answered = true; // again a rar sitiation
				}
				
				if(!currentQuestion.getCode().equals(questionInstanceDto.getCodeByUser())){
					answered = false; // if the code has changed then need to recompile the code
				}
				else{
					answered = true;
				}
			}
			if(!answered){
			String lang = LanguageCodes.getLanguageCode(questionInstanceDto.getQuestion().getLanguage().getLanguage());
//				if(lang.equals("13")){
//					evaluateMySQLCoding(currentQuestion, questionInstanceDto);
//					return;
//				}
			
			logger.info("compiling lang is "+lang);
			//System.out.println("compiling lang is "+lang);
			CompileData compileData = new CompileData();
			compileData.setLanguage(lang);
			String code = currentQuestion.getCode();
			code = code.replaceAll("\\\\n", System.lineSeparator());
			code = code.replaceAll("\\\\t", "   ");
			compileData.setCode(code);
			compileData.setStdin(q.getHiddenInputNegative());
			/**
			 * Negative Test Case
			 */
			CompileOutput op = compiler.compile(compileData);
			op.setOutput((op.getOutput() == null)?op.getOutput():op.getOutput().replaceAll("\n", ""));
			currentQuestion.setOutput(op.getOutput() == null?"wrong":op.getOutput());
			//questionInstanceDto.setCode(currentQuestion.getCode());
			//questionInstanceDto.setOutput(op.getOutput() == null?"wrong":op.getOutput());
			questionInstanceDto.setCodeByUser(currentQuestion.getCode().replaceAll("\r", ""));
			questionInstanceDto.setCodingOuputBySystemTestCase(op.getOutput() == null?"wrong":op.getOutput());
			
			boolean compilationError = false;
			if(op.getErrors() != null && op.getErrors().trim().length() > 0){
				
				if(op.getErrors().contains("error")){
					questionInstanceDto.setCodeCompilationErrors(true);
					compilationError = true;
				}
				else{
					questionInstanceDto.setCodeRunTimeErrors(true);
				}
				
			}
			else{
				compilationError = false;
				questionInstanceDto.setCodeCompilationErrors(false);
			}
			
		
			
			/**
			 * Positive Test case
			 */
			if(q.getHiddenInputPositive() != null && q.getHiddenInputPositive().trim().length() != 0 && !compilationError){
				compileData.setStdin(q.getHiddenInputPositive());
				op = compiler.compile(compileData);
				op.setOutput((op.getOutput() == null)?op.getOutput():op.getOutput().replaceAll("\n", ""));
				currentQuestion.setOutput(op.getOutput() == null?"wrong":op.getOutput());
				//questionInstanceDto.setCode(currentQuestion.getCode());
				questionInstanceDto.setCodeByUser(currentQuestion.getCode());
					if(questionInstanceDto.getQuestion().getHiddenOutputPositive() != null && op.getOutput() != null){
questionInstanceDto.setTestCaseInputPositive(questionInstanceDto.getQuestion().getHiddenOutputPositive().trim().equals(op.getOutput().trim())?true:false);
					}
					else{
						questionInstanceDto.setTestCaseInputPositive(false);
					}
			}
			
			
			/**
			 * set correct coding answer based on both positive and negative test case output being successful
			 */
			if(questionInstanceDto.getTestCaseInputNegative() && questionInstanceDto.getTestCaseInputPositive()){
				questionInstanceDto.setCorrect(true);
			}
			else{
				questionInstanceDto.setCorrect(false);
			}
			
				/**
				 * Extreme Minimal Value Test Case
				 */
				if(q.getHiddenInputExtremeMinimalValue() != null && q.getHiddenInputExtremeMinimalValue().trim().length() > 0 && !compilationError){
					compileData.setStdin(q.getHiddenInputExtremeMinimalValue());
	 				op = compiler.compile(compileData);
	 				op.setOutput((op.getOutput() == null)?op.getOutput():op.getOutput().replaceAll("\n", ""));
	 				//currentQuestion.setOutput(op.getOutput() == null?"wrong":op.getOutput());
	 				questionInstanceDto.setCodeByUser(currentQuestion.getCode());
	 					if(questionInstanceDto.getQuestion().getHiddenOutputExtremeMinimalValue() != null && op.getOutput() != null){
questionInstanceDto.setTestCaseMinimalValue(questionInstanceDto.getQuestion().getHiddenOutputExtremeMinimalValue().trim().equals(op.getOutput().trim())?true:false);
	 					}
	 					else{
	 						questionInstanceDto.setTestCaseMinimalValue(false);
	 					}
				}
				
					
					/**
					 * Extreme Maximum Value Test Case
					 */
				if(q.getHiddenInputExtremePositiveValue() != null && q.getHiddenInputExtremePositiveValue() .trim().length() > 0 && !compilationError){
					compileData.setStdin(q.getHiddenInputExtremePositiveValue());
	 				op = compiler.compile(compileData);
	 				op.setOutput((op.getOutput() == null)?op.getOutput():op.getOutput().replaceAll("\n", ""));
	 				//currentQuestion.setOutput(op.getOutput() == null?"wrong":op.getOutput());
	 				questionInstanceDto.setCodeByUser(currentQuestion.getCode());
	 					if(questionInstanceDto.getQuestion().getHiddenOutputExtremePositiveValue() != null && op.getOutput() != null){
questionInstanceDto.setTestCaseMaximumValue(questionInstanceDto.getQuestion().getHiddenOutputExtremePositiveValue().trim().equals(op.getOutput().trim())?true:false);
	 					}
	 					else{
	 						questionInstanceDto.setTestCaseMaximumValue(false);
	 					}
				}
				
					
					/**
					 * Invalid Data Value Test Case
					 */
				if(q.getHiddenInputInvalidDataValue() != null && q.getHiddenInputInvalidDataValue().trim().length() > 0 && !compilationError){
					compileData.setStdin(q.getHiddenInputInvalidDataValue());
	 				op = compiler.compile(compileData);
	 				op.setOutput((op.getOutput() == null)?op.getOutput():op.getOutput().replaceAll("\n", ""));
	 				//currentQuestion.setOutput(op.getOutput() == null?"wrong":op.getOutput());
	 				questionInstanceDto.setCodeByUser(currentQuestion.getCode());
	 					if(questionInstanceDto.getQuestion().getHiddenOutputInvalidDataValue() != null && op.getOutput() != null){
questionInstanceDto.setTestCaseInvalidData(questionInstanceDto.getQuestion().getHiddenOutputInvalidDataValue().trim().equals(op.getOutput().trim())?true:false);
	 					}
	 					else{
	 						questionInstanceDto.setTestCaseInvalidData(false);
	 					}
				}
				
			
			//sectionInstanceDto.setQuestionInstanceDtos(currentSection.getQuestionInstanceDtos());
			}
	 }
	
	private void evaluateMTFQuestion(AdaptiveAssessmentInstanceDTO currentQuestion,  AdaptiveAssessmentQuestionMappperInstance questionInstanceDto){
		Map<String, String> correctCombinations = new HashMap<String, String>();
		 String left1 = questionInstanceDto.getQuestion().getMatchLeft1();
		 String right1 = questionInstanceDto.getQuestion().getMatchRight1();
		 correctCombinations.put(left1, right1);
		 
		 String left2 = questionInstanceDto.getQuestion().getMatchLeft2();
		 String right2 = questionInstanceDto.getQuestion().getMatchRight2();
		 correctCombinations.put(left2, right2);
		 
		 String left3 = questionInstanceDto.getQuestion().getMatchLeft3();
		 String right3 = questionInstanceDto.getQuestion().getMatchRight3();
		 if(left3 != null && right3 != null && left3.trim().length()!= 0 && right3.trim().length() != 0){
			 correctCombinations.put(left3, right3);
		 }
		 
		 
		 String left4 = questionInstanceDto.getQuestion().getMatchLeft4();
		 String right4 = questionInstanceDto.getQuestion().getMatchRight4();
		 if(left4 != null && right4 != null && left4.trim().length()!= 0 && right4.trim().length() != 0){
			 correctCombinations.put(left4, right4);
		 }
		 
		 String left5 = questionInstanceDto.getQuestion().getMatchLeft5();
		 String right5 = questionInstanceDto.getQuestion().getMatchRight5();
		 if(left5 != null && right5 != null && left5.trim().length()!= 0 && right5.trim().length() != 0){
			 correctCombinations.put(left5, right5);
		 }
		 
		 String left6 = questionInstanceDto.getQuestion().getMatchLeft6();
		 String right6 = questionInstanceDto.getQuestion().getMatchRight6();
		 if(left6 != null && right6 != null && left6.trim().length()!= 0 && right6.trim().length() != 0){
			 correctCombinations.put(left6, right6);
		 }
		 
		 
		 
		 String rightA = currentQuestion.getMtf().getMatchRight1();
		
		 
		 questionInstanceDto.setMatchRight1(currentQuestion.getMtf().getMatchRight1());
		 questionInstanceDto.setMatchRight2(currentQuestion.getMtf().getMatchRight2());
		 questionInstanceDto.setMatchRight3(currentQuestion.getMtf().getMatchRight3());
		 questionInstanceDto.setMatchRight4(currentQuestion.getMtf().getMatchRight4());
		 questionInstanceDto.setMatchRight5(currentQuestion.getMtf().getMatchRight5());
		 questionInstanceDto.setMatchRight6(currentQuestion.getMtf().getMatchRight6());
		 
//		 questionInstanceDto.setMatchRight1Display(currentQuestion.getMatchRight1Display());
//		 questionInstanceDto.setMatchRight2Display(currentQuestion.getMatchRight2Display());
//		 questionInstanceDto.setMatchRight3Display(currentQuestion.getMatchRight3Display());
//		 questionInstanceDto.setMatchRight4Display(currentQuestion.getMatchRight4Display());
//		 questionInstanceDto.setMatchRight5Display(currentQuestion.getMatchRight5Display());
//		 questionInstanceDto.setMatchRight6Display(currentQuestion.getMatchRight6Display());
		 
		 //currentQuestion.initAnswerColours();
		 //questionInstanceDto.initAnswerColours();
		 
		 if(rightA != null){
			 String expected = correctCombinations.get(left1);
			 if(expected == null){
				 questionInstanceDto.setAnswered(false);
				 questionInstanceDto.setCorrect(false);
				 return;
			 }
			 else if(!expected.equals(rightA)){
				 questionInstanceDto.setAnswered(true);
				 questionInstanceDto.setCorrect(false);
				 return;
			 }
		 }
		 
		 rightA = currentQuestion.getMtf().getMatchRight2();
		 questionInstanceDto.setMatchRight2(rightA);
		// questionInstanceDto.setMatchLeft2(left2);
		 if(rightA != null){
			 String expected = correctCombinations.get(left2);
			 if(expected == null){
				 questionInstanceDto.setAnswered(false);
				 questionInstanceDto.setCorrect(false);
				 return;
			 }
			 else if(!expected.equals(rightA)){
				 questionInstanceDto.setAnswered(true);
				 questionInstanceDto.setCorrect(false);
				 return;
			 }
		 }
		 
		 rightA = currentQuestion.getMtf().getMatchRight3();
		 questionInstanceDto.setMatchRight3(rightA);
		// questionInstanceDto.setMatchLeft3(left3);
		 if(rightA != null){
			 if(left3 != null){
				 String expected = correctCombinations.get(left3);
				 if(expected == null){
					 questionInstanceDto.setAnswered(false);
					 //questionInstanceDto.getCorrect(false);
					 return;
				 }
				 else if(!expected.equals(rightA)){
					 questionInstanceDto.setAnswered(true);
					 questionInstanceDto.setCorrect(false);
					 return;
				 }
			 }
			 
		 }
		 
		 rightA = currentQuestion.getMtf().getMatchRight4();
		 questionInstanceDto.setMatchRight4(rightA);
		// questionInstanceDto.setMatchLeft4(left4);
		 if(rightA != null){
			 if(left4 != null){
				 String expected = correctCombinations.get(left4);
				 if(expected == null){
					 questionInstanceDto.setAnswered(false);
					 questionInstanceDto.setCorrect(false);
					 return;
				 }
				 else if(!expected.equals(rightA)){
					 questionInstanceDto.setAnswered(true);
					 questionInstanceDto.setCorrect(false);
					 return;
				 }
			 }
			 
		 }
		 
		 rightA = currentQuestion.getMtf().getMatchRight5();
		 questionInstanceDto.setMatchRight5(rightA);
		// questionInstanceDto.setMatchLeft5(left5);
		 if(rightA != null){
			 if(left5 != null){
				 String expected = correctCombinations.get(left5);
				 if(expected == null){
					 questionInstanceDto.setAnswered(false);
					 questionInstanceDto.setCorrect(false);
					 return;
				 }
				 else if(!expected.equals(rightA)){
					 questionInstanceDto.setAnswered(true);
					 questionInstanceDto.setCorrect(false);
					 return;
				 }
			 }
			 
		 }
		 
		 rightA = currentQuestion.getMtf().getMatchRight6();
		 questionInstanceDto.setMatchRight6(rightA);
		// questionInstanceDto.setMatchLeft6(left6);
		 if(rightA != null){
			 if(left6 != null){
				 String expected = correctCombinations.get(left6);
				 if(expected == null){
					 questionInstanceDto.setAnswered(false);
					 questionInstanceDto.setCorrect(false);
					 return;
				 }
				 else if(!expected.equals(rightA)){
					 questionInstanceDto.setAnswered(true);
					 questionInstanceDto.setCorrect(false);
					 return;
				 }
			 }
			 
		 }
		 
		// currentQuestion.initAnswerColours();
		// questionInstanceDto.initAnswerColours();
		 
		 questionInstanceDto.setAnswered(true);
		 questionInstanceDto.setCorrect(true);
	}

	@Override
	public AdaptiveQMIWrapper nextQuestion(HttpServletRequest request) {
		// TODO Auto-generated method stub
		 //request.getSession().setAttribute("currentLevel", instance.getCurrentLevel());
		 List<Question> questions = (List<Question>)request.getSession().getAttribute("currentLevelQuestions");
		 //request.getSession().setAttribute("currentQuestion", questions.get(0));
		 Integer currentQuestionIndex = (Integer) request.getSession().getAttribute("currentQuestionIndex");
		// AdaptiveAssessmentSkillLevel currLevel = (AdaptiveAssessmentSkillLevel)request.getSession().getAttribute("currentLevel");
		 
		 AdaptiveAssessment test = (AdaptiveAssessment)request.getSession().getAttribute("test");
		 User user = (User) request.getSession().getAttribute("user");
		 AdaptiveAssessmentInstance instance = assessmentService.findAdaptiveAssessmentInstance(test.getAdaptiveAssessmentName(), user.getCompanyId(), user.getEmail());
		 AdaptiveAssessmentSkillLevel currLevel = AdaptiveAssessmentSkillLevel.LEVEL1;
		 	if(instance != null){
		 		currLevel = instance.getCurrentLevel();
		 	}
		  
		 
		
		 	if(currentQuestionIndex  == (questions.size()-1)){
		 	request.getSession().setAttribute("transitionNext", true);	
		 		//next level
		 		
		 		 Integer attempts = levelInstanceService.findCountOfAdaptiveAssessmentLevelInstances(user.getCompanyId(), test.getAdaptiveAssessmentName(), user.getEmail(), currLevel);
				 Integer attempt = 1;
				 //	if(attempts == 0){
				
				 	if(attempts != null){
				 		attempt = 1;
				 		List<AdaptiveAssessmentLevelInstance> levelInstances = levelInstanceService.findAdaptiveAssessmentLevelInstances(user.getCompanyId(), test.getAdaptiveAssessmentName(), user.getEmail(), currLevel);
				 		
				 		if(levelInstances.size() == 0){
				 			attempt = 1;
				 		}
				 		else{
				 			AdaptiveAssessmentLevelInstance last = levelInstances.get(levelInstances.size() - 1);
				 			if(last.getComplete()){
					 			attempt = levelInstances.size() + 1;
					 		}
					 		else{
					 			attempt = levelInstances.size();
					 		}
				 		}
				 		
				 		
				 	}
				
				 	/**
			 		 * Check whether should be allowed to go to next level
			 		 */
				 	//boolean proceed = saveStepAndDetermineFurtherStep(currLevel, questions.size(), user.getCompanyId(), user.getEmail(), test.getAdaptiveAssessmentName(), attempt);
				 	AdaptiveQuestionNavigationTransition transition = saveStepAndDetermineFurtherStep(currLevel, questions.size(), user.getCompanyId(), user.getEmail(), test.getAdaptiveAssessmentName(), attempt);
				 		request.getSession().setAttribute("transition", transition);
				 		if(!transition.getProceed() && !currLevel.getLevel().equals(AdaptiveAssessmentSkillLevel.LEVEL5.getLevel())){
				 			//throw new AssessmentGenericException(AdaptiveConstants.NUMBER_REGRESSIONS_EXCEEDED);
				 			AdaptiveQMIWrapper ret = new AdaptiveQMIWrapper(AdaptiveConstants.NUMBER_REGRESSIONS_EXCEEDED);
				 			return ret;
				 		}
				 		else{
				 			if(!transition.getProceed() && currLevel.getLevel().equals(AdaptiveAssessmentSkillLevel.LEVEL5.getLevel())){
				 				//throw new AssessmentGenericException(AdaptiveConstants.ADAPTIVE_ASSESSMENT_COMPLETE);
				 				//if(transition.getProceed())
				 				AdaptiveQMIWrapper ret = new AdaptiveQMIWrapper(AdaptiveConstants.ADAPTIVE_ASSESSMENT_COMPLETE);
					 			return ret;
				 			}
				 		}
				 	//AdaptiveAssessmentSkillLevel nextLevel = nextLevel(currLevel);
				 	AdaptiveAssessmentSkillLevel nextLevel = transition.getLevelToGo();
				 	AdaptiveAssessmentSkill skillLevel = levelService.findUniqueLevelForAdaptiveAssessment(user.getCompanyId(), test.getAdaptiveAssessmentName(), nextLevel);
					Integer noOfQuestions = skillLevel.getNoOfQuestions();
					questions = questionservice.getRandomQuestions(user.getCompanyId(), skillLevel.getQualifier1(), skillLevel.getQualifier2(), skillLevel.getQualifier3(), skillLevel.getQualifier4(), skillLevel.getQualifier5(), DifficultyLevel.EASY, QuestionType.MCQ, PageRequest.of(0, noOfQuestions));
					 if(questions.size() == 0){
						 //throw new AssessmentGenericException("NO_QUESTIONS_FOR_CURRENT_LEVEL - Add more content for "+skillLevel.getQualifier1()+"-"+skillLevel.getQualifier2()+"-"+skillLevel.getQualifier3()+"-"+skillLevel.getQualifier4()+"-"+skillLevel.getQualifier5());
						 //throw new AssessmentGenericException(AdaptiveConstants.NO_QUESTIONS_CURRENT_LEVEL);
						 AdaptiveQMIWrapper ret = new AdaptiveQMIWrapper(AdaptiveConstants.NO_QUESTIONS_CURRENT_LEVEL);
				 		 return ret;
					 }
					 request.getSession().setAttribute("currentLevel", nextLevel);
					 request.getSession().setAttribute("currentLevelQuestions", questions);
					 request.getSession().setAttribute("currentQuestion", questions.get(0));
					 request.getSession().setAttribute("currentQuestionIndex", 0);
					 request.getSession().setAttribute("attemptOfLevelInstance", attempt);
					 Question currentQuestion = questions.get(0);
					 request.getSession().setAttribute("currentQuestionIndex", 0);
					 AdaptiveAssessmentQuestionMappperInstance questionMappperInstance = getAdaptiveAssessmentQuestionMappperInstance(user.getEmail(), test.getAdaptiveAssessmentName(), user.getCompanyId(), nextLevel,currentQuestion, attempt);
					 request.getSession().setAttribute("questionMappperInstance", questionMappperInstance);
					 AdaptiveQMIWrapper ret = new AdaptiveQMIWrapper(questionMappperInstance);
					 return ret;
		 	}
		 	else {
		 		//same level (question is not yet last)
		 		request.getSession().setAttribute("transitionNext", false);	
		 		Question nextquestion = questions.get(currentQuestionIndex+1);
		 		request.getSession().setAttribute("currentQuestionIndex", currentQuestionIndex+1);
		 		request.getSession().setAttribute("currentQuestion", nextquestion);
		 		Integer attempt = (Integer) request.getSession().getAttribute("attemptOfLevelInstance");
		 		
		 		AdaptiveAssessmentQuestionMappperInstance questionMappperInstance = getAdaptiveAssessmentQuestionMappperInstance(user.getEmail(), test.getAdaptiveAssessmentName(), user.getCompanyId(), currLevel,nextquestion, attempt);
				request.getSession().setAttribute("questionMappperInstance", questionMappperInstance);
				AdaptiveQMIWrapper ret = new AdaptiveQMIWrapper(questionMappperInstance);
		 		 return ret;
				//return questionMappperInstance;
		 	}
		//return null;
	}
	
	private AdaptiveQuestionNavigationTransition saveStepAndDetermineFurtherStep(AdaptiveAssessmentSkillLevel level, Integer noOfQuestions, String companyId, String email, String testName, Integer attempt){
		Company company = companyService.findByCompanyId(companyId);
		
		Integer correctAnswers = mapperInstanceservice.findCorrectAnswersAdaptiveAssessmentQuestionMappperInstanceForLevel(companyId, testName, email, level, attempt);
		Float percent = (float)(Float.valueOf(""+correctAnswers) / Float.valueOf(""+noOfQuestions)) *100;
		AdaptiveAssessmentSkill skill = levelService.findUniqueLevelForAdaptiveAssessment(companyId, testName, level);
		
		boolean pass = (percent >= skill.getPassPercentForLevel()?true:false);
		AdaptiveAssessmentLevelInstance levelInstance = null;
		levelInstance = levelInstanceService.findAdaptiveAssessmentLevelInstance(companyId, testName, email, level, attempt);
			if(levelInstance == null){
				levelInstance = new AdaptiveAssessmentLevelInstance(companyId, email, testName, attempt, level);
			}
			levelInstance.setPass(pass);
			levelInstance.setComplete(true);
			levelInstance.setAverageScoreForLevel(percent);
			levelInstance.setCompanyName(company.getCompanyName());
			levelInstanceService.saveOrUpdate(levelInstance);
			
			AdaptiveQuestionNavigationTransition transition = new AdaptiveQuestionNavigationTransition();
			
			
			/**
			 * Save/Update AdaptiveInstance
			 */
			AdaptiveAssessmentInstance instance = assessmentService.findAdaptiveAssessmentInstance(testName, companyId, email);
				if(instance == null){
					instance = new AdaptiveAssessmentInstance(email, companyId, testName);
					instance.setCompanyName(company.getCompanyName());
				}
				if(pass){
					instance.setNoOfProgressions(instance.getNoOfProgressions() + 1);
					
				}
				else{
					instance.setNoofRegressions(instance.getNoofRegressions() + 1);
				}
				
				transition.setLevelOfOrigin(level);
				transition.setProceed(true);
				if(level.getLevel().equals(AdaptiveAssessmentSkillLevel.LEVEL1.getLevel())){
					if(pass){
						instance.setNoOfProgressionsLevel1(instance.getNoOfProgressionsLevel1()+1);
						instance.setCurrentLevel(AdaptiveAssessmentSkillLevel.LEVEL2);
						transition.setLevelToGo(AdaptiveAssessmentSkillLevel.LEVEL2);
						transition.setProgression(true);
					}
					else{
						instance.setNoOfRegressionsLevel1(instance.getNoOfRegressionsLevel1() + 1);
						instance.setCurrentLevel(AdaptiveAssessmentSkillLevel.LEVEL1);
						transition.setLevelToGo(AdaptiveAssessmentSkillLevel.LEVEL1);
						transition.setProgression(false);
					}
				}
				else if(level.getLevel().equals(AdaptiveAssessmentSkillLevel.LEVEL2.getLevel())){
					if(pass){
						instance.setNoOfProgressionsLevel2(instance.getNoOfProgressionsLevel2()+1);
						instance.setCurrentLevel(AdaptiveAssessmentSkillLevel.LEVEL3);
						transition.setLevelToGo(AdaptiveAssessmentSkillLevel.LEVEL3);
						transition.setProgression(true);
					}
					else{
						instance.setNoOfRegressionsLevel2(instance.getNoOfRegressionsLevel2() + 1);
						instance.setCurrentLevel(AdaptiveAssessmentSkillLevel.LEVEL1);
						transition.setLevelToGo(AdaptiveAssessmentSkillLevel.LEVEL1);
						transition.setProgression(false);
					}
				}
				else if(level.getLevel().equals(AdaptiveAssessmentSkillLevel.LEVEL3.getLevel())){
					if(pass){
						instance.setNoOfProgressionsLevel3(instance.getNoOfProgressionsLevel3()+1);
						instance.setCurrentLevel(AdaptiveAssessmentSkillLevel.LEVEL4);
						transition.setLevelToGo(AdaptiveAssessmentSkillLevel.LEVEL4);
						transition.setProgression(true);
					}
					else{
						instance.setNoOfRegressionsLevel3(instance.getNoOfRegressionsLevel3() + 1);
						instance.setCurrentLevel(AdaptiveAssessmentSkillLevel.LEVEL2);
						transition.setLevelToGo(AdaptiveAssessmentSkillLevel.LEVEL2);
						transition.setProgression(false);
					}
				}
				else if(level.getLevel().equals(AdaptiveAssessmentSkillLevel.LEVEL4.getLevel())){
					if(pass){
						instance.setNoOfProgressionsLevel4(instance.getNoOfProgressionsLevel4()+1);
						instance.setCurrentLevel(AdaptiveAssessmentSkillLevel.LEVEL5);
						transition.setLevelToGo(AdaptiveAssessmentSkillLevel.LEVEL5);
						transition.setProgression(true);
					}
					else{
						instance.setNoOfRegressionsLevel4(instance.getNoOfRegressionsLevel4() + 1);
						instance.setCurrentLevel(AdaptiveAssessmentSkillLevel.LEVEL3);
						transition.setLevelToGo(AdaptiveAssessmentSkillLevel.LEVEL3);
						transition.setProgression(false);
					}
				}
				else {
					if(pass){
						instance.setNoOfProgressionsLevel5(instance.getNoOfProgressionsLevel5()+1);
						instance.setComplete(true);
						transition.setProceed(false); // can not go further
						transition.setProgression(true);
						instance.setComplete(true);
					}
					else{
						instance.setNoOfRegressionsLevel5(instance.getNoOfRegressionsLevel5() + 1);
						instance.setCurrentLevel(AdaptiveAssessmentSkillLevel.LEVEL4);
						transition.setLevelToGo(AdaptiveAssessmentSkillLevel.LEVEL4);
						transition.setProgression(false);
						transition.setProceed(true); // can be allowed to go back
						instance.setComplete(false);
					}
				}
				
			AdaptiveAssessment assessment =  adaptiveAssessmentRepository.findUniqueAdaptiveAssessment(companyId, testName);
			if(instance.getNoofRegressions() >= (assessment.getNoOfRegressions()==null?3:assessment.getNoOfRegressions())){
				//AdaptiveAssessmentSkillLevel prev = prevLevel(level);
				instance.setComplete(true);
				assessmentService.saveOrUpdate(instance);
				transition.setProceed(false);
				return transition;//stop test
			}
			else{
//					if(level.getLevel().equals(AdaptiveAssessmentSkillLevel.LEVEL5.getLevel())){
//						instance.setComplete(true);
//						transition.setProceed(false);
//					}
//					else{
//						instance.setComplete(false);
//						transition.setProceed(true);
//					}
				
				assessmentService.saveOrUpdate(instance);
				//return true;
				
				return transition;
			}
	}
	
	private AdaptiveAssessmentSkillLevel nextLevel(AdaptiveAssessmentSkillLevel currLevel) {
		if(currLevel.getLevel().equals(AdaptiveAssessmentSkillLevel.LEVEL1.getLevel())){
			return AdaptiveAssessmentSkillLevel.LEVEL2;
		}
		else if(currLevel.getLevel().equals(AdaptiveAssessmentSkillLevel.LEVEL2.getLevel())){
			return AdaptiveAssessmentSkillLevel.LEVEL3;
		}
		else if(currLevel.getLevel().equals(AdaptiveAssessmentSkillLevel.LEVEL3.getLevel())){
			return AdaptiveAssessmentSkillLevel.LEVEL4;
		}
		else if(currLevel.getLevel().equals(AdaptiveAssessmentSkillLevel.LEVEL4.getLevel())){
			return AdaptiveAssessmentSkillLevel.LEVEL5;
		}
		else{
			throw new AssessmentGenericException(AdaptiveConstants.ADAPTIVE_ASSESSMENT_COMPLETE); 
		}
	}
	
	private AdaptiveAssessmentSkillLevel prevLevel(AdaptiveAssessmentSkillLevel currLevel) {
		if(currLevel.getLevel().equals(AdaptiveAssessmentSkillLevel.LEVEL5.getLevel())){
			return AdaptiveAssessmentSkillLevel.LEVEL4;
		}
		else if(currLevel.getLevel().equals(AdaptiveAssessmentSkillLevel.LEVEL4.getLevel())){
			return AdaptiveAssessmentSkillLevel.LEVEL3;
		}
		else if(currLevel.getLevel().equals(AdaptiveAssessmentSkillLevel.LEVEL3.getLevel())){
			return AdaptiveAssessmentSkillLevel.LEVEL2;
		}
		else if(currLevel.getLevel().equals(AdaptiveAssessmentSkillLevel.LEVEL2.getLevel())){
			return AdaptiveAssessmentSkillLevel.LEVEL1;
		}
		else{
			throw new AssessmentGenericException(AdaptiveConstants.REACHED_LEVEL_1); 
		}
	}

	@Override
	public AdaptiveQMIWrapper prevQuestion(HttpServletRequest request) {
		// TODO Auto-generated method stub
		 //request.getSession().setAttribute("currentLevel", instance.getCurrentLevel());
		 List<Question> questions = (List<Question>)request.getSession().getAttribute("currentLevelQuestions");
		 //request.getSession().setAttribute("currentQuestion", questions.get(0));
		 Integer currentQuestionIndex = (Integer) request.getSession().getAttribute("currentQuestionIndex");
		 AdaptiveAssessmentSkillLevel currLevel = (AdaptiveAssessmentSkillLevel)request.getSession().getAttribute("currentLevel");
		 AdaptiveAssessment test = (AdaptiveAssessment)request.getSession().getAttribute("test");
		 User user = (User) request.getSession().getAttribute("user");
		 	if(currentQuestionIndex  == 0){
		 		throw new AssessmentGenericException(AdaptiveConstants.ADAPTIVE_ASSESSMENT_NO_PREV);
		 		//prev level
//		 		AdaptiveAssessmentSkillLevel prevLevel = prevLevel(currLevel);
//		 		 Integer attempts = levelInstanceService.findCountOfAdaptiveAssessmentLevelInstances(user.getCompanyId(), test.getAdaptiveAssessmentName(), user.getEmail(), prevLevel);
//				 Integer attempt = 1;
//				 	if(attempts == 0){
//				 		attempt = 1;
//				 		List<AdaptiveAssessmentLevelInstance> levelInstances = levelInstanceService.findAdaptiveAssessmentLevelInstances(user.getCompanyId(), test.getAdaptiveAssessmentName(), user.getEmail(), prevLevel);
//				 		AdaptiveAssessmentLevelInstance last = levelInstances.get(levelInstances.size() - 1);
//				 		if(last.getComplete()){
//				 			attempt = levelInstances.size() + 1;
//				 		}
//				 		else{
//				 			attempt = levelInstances.size();
//				 		}
//				 	}
//				 	AdaptiveAssessmentSkill skillLevel = levelService.findUniqueLevelForAdaptiveAssessment(user.getCompanyId(), test.getAdaptiveAssessmentName(), prevLevel);
//					Integer noOfQuestions = skillLevel.getNoOfQuestions();
//					questions = questionservice.getRandomQuestions(user.getCompanyId(), skillLevel.getQualifier1(), skillLevel.getQualifier2(), skillLevel.getQualifier3(), skillLevel.getQualifier4(), skillLevel.getQualifier5(), DifficultyLevel.EASY, QuestionType.MCQ, PageRequest.of(0, noOfQuestions));
//					 if(questions.size() == 0){
//						 //throw new AssessmentGenericException("NO_QUESTIONS_FOR_CURRENT_LEVEL - Add more content for "+skillLevel.getQualifier1()+"-"+skillLevel.getQualifier2()+"-"+skillLevel.getQualifier3()+"-"+skillLevel.getQualifier4()+"-"+skillLevel.getQualifier5());
//						 throw new AssessmentGenericException("NO_QUESTIONS_FOR_CURRENT_LEVEL");
//					 }
//					 request.getSession().setAttribute("currentLevel", prevLevel);
//					 request.getSession().setAttribute("currentLevelQuestions", questions);
//					 request.getSession().setAttribute("currentQuestion", questions.get(0));
//					 request.getSession().setAttribute("currentQuestionIndex", 0);
//					 request.getSession().setAttribute("attemptOfLevelInstance", attempt);
//					 Question currentQuestion = questions.get(0);
//					 AdaptiveAssessmentQuestionMappperInstance questionMappperInstance = getAdaptiveAssessmentQuestionMappperInstance(user.getEmail(), test.getAdaptiveAssessmentName(), user.getCompanyId(), prevLevel,currentQuestion, attempt);
//					 request.getSession().setAttribute("questionMappperInstance", questionMappperInstance);
		 	}
		 	else {
		 		//same level (question is not yet last)
		 		Question prevquestion = questions.get(currentQuestionIndex-1);
		 		request.getSession().setAttribute("currentQuestionIndex", currentQuestionIndex-1);
		 		request.getSession().setAttribute("currentQuestion", prevquestion);
		 		Integer attempt = (Integer) request.getSession().getAttribute("attemptOfLevelInstance");
		 		
		 		AdaptiveAssessmentQuestionMappperInstance questionMappperInstance = getAdaptiveAssessmentQuestionMappperInstance(user.getEmail(), test.getAdaptiveAssessmentName(), user.getCompanyId(), currLevel,prevquestion, attempt);
				request.getSession().setAttribute("questionMappperInstance", questionMappperInstance);
				AdaptiveQMIWrapper ret = new AdaptiveQMIWrapper(questionMappperInstance);
				return ret;
		 	}
		//return null;
	}
	
	private AdaptiveAssessmentQuestionMappperInstance getAdaptiveAssessmentQuestionMappperInstance(String email, String testName, String companyId, AdaptiveAssessmentSkillLevel level, Question currentQuestion, Integer attempt){
		 AdaptiveAssessmentQuestionMappperInstance instance = mapperInstanceservice.findUniqueAdaptiveAssessmentQuestionMappperInstance(companyId, currentQuestion.getQuestionText(), testName, email, level, attempt);
		 if(instance == null){
			 instance = new AdaptiveAssessmentQuestionMappperInstance(companyId, currentQuestion, testName, email, level, attempt );
		 }
		 return instance;
	 }

	@Override
	public AdaptiveQMIWrapper markProgression(HttpServletRequest request) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AdaptiveQMIWrapper markRegression(HttpServletRequest request) {
		// TODO Auto-generated method stub
		return null;
	}

}
