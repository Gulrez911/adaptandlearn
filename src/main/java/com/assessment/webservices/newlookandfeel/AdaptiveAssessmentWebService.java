package com.assessment.webservices.newlookandfeel;

import java.io.IOException;
import java.net.URLDecoder;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.assessment.common.AssessmentGenericException;
import com.assessment.common.newlookandfeel.CommonUtil;
import com.assessment.common.util.AdaptiveConstants;
import com.assessment.data.AdaptiveAssessment;
import com.assessment.data.AdaptiveAssessmentInstance;
import com.assessment.data.AdaptiveAssessmentLevelInstance;
import com.assessment.data.AdaptiveAssessmentQuestionMappperInstance;
import com.assessment.data.AdaptiveAssessmentSkill;
import com.assessment.data.AdaptiveAssessmentSkillLevel;
import com.assessment.data.CandidateProfileParams;
import com.assessment.data.Company;
import com.assessment.data.DifficultyLevel;
import com.assessment.data.Question;
import com.assessment.data.QuestionMapperInstance;
import com.assessment.data.QuestionType;
import com.assessment.data.Test;
import com.assessment.data.User;
import com.assessment.data.UserTestSession;
import com.assessment.jsonwrapper.JsonStatusBean;
import com.assessment.repositories.AdaptiveAssessmentInstanceRepository;
import com.assessment.repositories.AdaptiveAssessmentRepository;
import com.assessment.services.AdaptiveAssessmentInstanceService;
import com.assessment.services.AdaptiveAssessmentLevelInstanceService;
import com.assessment.services.AdaptiveAssessmentQuestionMappperInstanceService;
import com.assessment.services.AdaptiveAssessmentSkillService;
import com.assessment.services.AdaptiveAssessmentUtilService;
import com.assessment.services.CandidateProfileParamsService;
import com.assessment.services.CompanyService;
import com.assessment.services.QuestionService;
import com.assessment.services.UserService;
import com.assessment.web.dto.AdaptiveQMIWrapper;
import com.assessment.web.dto.CodingAnswerDTO;
import com.assessment.web.dto.QualifierSkillLevelDto;
import com.assessment.web.dto.TestSessionDTO;
import com.assessment.web.dto.newlookandfeel.AdaptiveAssessmentInstanceDTO;
import com.assessment.web.dto.newlookandfeel.AdaptiveAssessmentResponseDto;


@Controller
@RequestMapping(CommonUtil.API_Version)
public class AdaptiveAssessmentWebService {
	

	//@RequestBody AdaptiveAssessmentInstanceDTO currentQuestion
	
	//start
	@Autowired
	UserService userService;
	
	@Autowired
	AdaptiveAssessmentSkillService levelService;
	
	@Autowired
	AdaptiveAssessmentQuestionMappperInstanceService mapperInstanceservice;
	
	@Autowired
	AdaptiveAssessmentLevelInstanceService levelInstanceService;
	
	@Autowired
	AdaptiveAssessmentInstanceService assessmentService;
	
	@Autowired
	AdaptiveAssessmentRepository adaptiveAssessmentRepository;
	
	@Autowired
	AdaptiveAssessmentInstanceRepository assessmentInstanceRep;
	
	@Autowired
	QuestionService questionservice;
	
	@Autowired
	AdaptiveAssessmentUtilService adaptiveAssessmentUtilService;
	
	@Autowired
	CompanyService companyService;
	
	@Autowired
	CandidateProfileParamsService profileService;
	
	private JsonStatusBean prelimVal( String companyId,String startDate,String endDate,  String userId , String testName) throws IOException {
		JsonStatusBean bean = new JsonStatusBean();
		 if(startDate == null || endDate == null){
		    	
		    	bean.setErrorMessage("This is a old test link and no longer used. Contact Test Admin to get a new test link.");
		    	return bean;
		    }
			 
			 startDate = URLDecoder.decode(startDate);
			    endDate = URLDecoder.decode(endDate);
			    startDate = new String(Base64.getDecoder().decode(startDate.getBytes()));
			    endDate = new String(Base64.getDecoder().decode(endDate.getBytes()));
			    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy hh:mm aa");
			    try {
					Date sDate = new Date(Long.parseLong(startDate));
					Date eDate = new Date(Long.parseLong(endDate));
					Long now = System.currentTimeMillis();
					Long start = sDate.getTime();
					Long end = eDate.getTime();
					String view = "testLinkNotEnabled";
					String message = "";
					Boolean inactive = false;
					if(start > now){
						message = "Link is not yet active. It will be activated at "+dateFormat.format(sDate)+". Try later.";
						inactive = true;
					}
					if(now > end){
						message = "Link has expired at "+dateFormat.format(eDate)+". Contact Test Admin.";
						inactive = true;
					}
					
					if(inactive){
						bean.setErrorMessage(message);
						return bean;
					}
					
				} catch (Exception e) {
					// TODO Auto-generated catch block
					
			    	bean.setErrorMessage("This is a invalid test link. Make sure you have correctly copied it. Contact Test Admin to get more help if it still doesn't work.");
			    	return bean;
				}
			    return bean;
	}
	
	private User validateAndsetUser(User userDetails, JsonStatusBean bean, AdaptiveAssessment testDetails, HttpServletRequest request){
		/**
		 * Get no of attempts for the email id and make configurabled attempts work for test givers
		 */
		System.out.println("in validate suerDetails "+userDetails);
		String email = "";
			if(userDetails.getEmail().lastIndexOf("[") > 0 ){
		 		email = userDetails.getEmail().substring(0, userDetails.getEmail().lastIndexOf("["));
		 	}
		 	else{
		 		email = userDetails.getEmail();
		 	}
		//UserTestSession session2 = testSessionRepository.findByPrimaryKey(email, testDetails.getTestName(), testDetails.getCompanyId());
			AdaptiveAssessmentInstance session2 = assessmentService.findAdaptiveAssessmentInstance(testDetails.getAdaptiveAssessmentName(), testDetails.getCompanyId(), email);
			
			if(session2 != null){
				//for practice users no sessions will be there .
				if(session2.getComplete()!= null && session2.getComplete()){
					email =  getUserAfterCheckingNoOfAttempts(email, testDetails.getCompanyId(), testDetails, request);
					if(email.equals("fail")){

				  		bean.setErrorMessage("No of Attempts exceeded");
				  		//return null;
				  		throw new AssessmentGenericException("NO_OF_ATTEMPTS_EXCEEDED");
				  		
				  		//throw new y
					}
					else{
						userDetails.setEmail(email);
						userDetails.setId(null);
						userService.saveOrUpdate(userDetails);
						request.getSession().setAttribute("user", userDetails);
					}
				}
				
			}
			userDetails.setEmail(email);
			return userDetails;
	}
	
	@CrossOrigin(origins = "*", allowedHeaders = "*")
	@RequestMapping(value = "/startWithoutToken", method = RequestMethod.GET)
	  public @ResponseBody JsonStatusBean startAdaptiveAssessmentWithoutToken(@RequestParam(name= "companyId") String companyId, @RequestParam String startDate, @RequestParam String endDate, @RequestParam(name= "userId") String userId ,@RequestParam(name= "testName") String testName,  @RequestParam(required=false) String timeCounter, HttpServletRequest request, HttpServletResponse response) throws IOException {
		System.out.println("in start without token"); 
		return startAdaptiveAssessment(null, companyId, startDate, endDate, userId, testName, timeCounter, request, response);
	}
//	
	
	@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")
	@RequestMapping(value = "/start", method = RequestMethod.GET)
	  public @ResponseBody JsonStatusBean startAdaptiveAssessment(@RequestHeader(name="token") String token, @RequestParam(name= "companyId") String companyId, @RequestParam String startDate, @RequestParam String endDate, @RequestParam(name= "userId") String userId ,@RequestParam(name= "testName") String testName,  @RequestParam(required=false) String timeCounter, HttpServletRequest request, HttpServletResponse response) throws IOException {
	//public @ResponseBody JsonStatusBean startAdaptiveAssessment(@RequestHeader(name="token") String token, @RequestParam(name= "companyId") String companyId, @RequestParam String startDate, @RequestParam String endDate, @RequestParam(name= "userId") String userId ,@RequestParam(name= "testName") String testName,  @RequestParam(required=false) String timeCounter, HttpServletRequest request, HttpServletResponse response) throws IOException {
		System.out.println("in start"); 
		response.setHeader("Access-Control-Allow-Origin", "http://localhost:4200");
		response.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
		response.setHeader("Access-Control-Max-Age", "3600");
		response.setHeader("Access-Control-Allow-Headers", "X-PINGOTHER,Content-Type,X-Requested-With,accept,Origin,Access-Control-Request-Method,Access-Control-Request-Headers,Authorization");
		response.addHeader("Access-Control-Expose-Headers", "xsrf-token");	
		
		JsonStatusBean bean =  prelimVal(companyId, startDate, endDate, userId, testName);
		 	if(bean.getErrorMessage() != null && bean.getErrorMessage().trim().length() > 0){
		 		return bean;
		 	}
		
		    AdaptiveAssessmentResponseDto responseDto = new AdaptiveAssessmentResponseDto();
		    User userDetails=userService.findByPrimaryKey(userId, companyId);
		    Company company = companyService.findByCompanyId(companyId);
		    	if(userDetails == null){
		    		userDetails = new User();
		    		userDetails.setCompanyId(company.getCompanyId());
		    		userDetails.setCompanyName(company.getCompanyName());
		    		userDetails.setEmail(userId);
		    		userDetails.setFirstName("Adaptive Test Taker");
		    		userDetails.setLastName("");
		    		userDetails.setPassword(userId.hashCode()+"");
		    		userService.saveOrUpdate(userDetails);
		    	}
		    request.getSession().setAttribute("user", userDetails);
		    
		    
		   
	    	AdaptiveAssessment testDetails=adaptiveAssessmentRepository.findUniqueAdaptiveAssessment(companyId, testName);
	    	try {
				userDetails = validateAndsetUser(userDetails, bean, testDetails, request);
			} catch (AssessmentGenericException e1) {
				// TODO Auto-generated catch block
				bean.setErrorMessage(e1.getMessage());
    			return bean;
				
			}
	    		if(userDetails == null){
	    			bean.setErrorMessage("NO_OF_ATTEMPTS_EXCEEDED");
	    			return bean;
	    		}
				
			/**
			 * End code put to check configurabled attempts work for test givers who are send private test links through email
			 */
			User createTestUser = userService.findByPrimaryKey(testDetails.getCreatedBy(), companyId);
			String cre = (createTestUser == null)? ("NA"):((createTestUser.getFirstName() == null?"NA":createTestUser.getFirstName()) +" "+(createTestUser.getLastName() == null?"NA":createTestUser.getLastName()));
			request.getSession().setAttribute("test", testDetails);

			int allQuestionsTimeInMin=0;
		
			
			if(testDetails.getTestTimeInMinutes() == null || testDetails.getTestTimeInMinutes() == 0) {
				allQuestionsTimeInMin = 90;
			}
			else {
				allQuestionsTimeInMin = testDetails.getTestTimeInMinutes();
			
			testDetails.setCreatedBy(testDetails.getCreatedBy() == null?"na":testDetails.getCreatedBy());
			
			AdaptiveAssessmentInstance session = assessmentService.findAdaptiveAssessmentInstance(testName, companyId, userDetails.getEmail());
			if(session != null && session.getComplete()!= null && session.getComplete()) {
				bean.setErrorMessage("Attempt complete");
				return bean;
			}
			else if(session != null && session.getComplete()!= null && !session.getComplete()) {
				//studentTest.setNoOfAttempts(session.getNoOfAttempts());
				//responseDto.setNoOfAttempts(session.getNoOfAttempts());
			}
			else if(session == null){
				//studentTest.setNoOfAttempts(1);
				if(!userDetails.getEmail().contains("[")){
					request.getSession().setAttribute("noOfAttempts", 0);
				}
				
			}
			//studentTest.setNoOfAttemptsAvailable(testDetails.getNoOfConfigurableAttempts() ==null?50:testDetails.getNoOfConfigurableAttempts());
		responseDto.setNoOfAttemptsAvailable(testDetails.getNoOfConfigurableAttempts() ==null?50:testDetails.getNoOfConfigurableAttempts());
			}
			
			try{
				AdaptiveAssessmentQuestionMappperInstance current = setUp(userDetails.getEmail(), testName, companyId, request);
				responseDto.setEmail(userDetails.getEmail());
				responseDto.setTestName(testName);
				AdaptiveAssessmentInstanceDTO dto = new AdaptiveAssessmentInstanceDTO();
				dto.setQuestionMapperInstance(current);
				responseDto.setInstanceDTO(dto);
				bean.setData(responseDto);
				return bean;
			}
			catch(AssessmentGenericException e){
				bean.setErrorMessage(e.getMessage());
				return bean;
			}
		    
	 }
	 
	 private AdaptiveAssessmentQuestionMappperInstance setUp(String email, String testName, String companyId, HttpServletRequest request){
		 AdaptiveAssessmentInstance instance = assessmentService.findAdaptiveAssessmentInstance(testName, companyId, email);
		 adaptiveAssessmentRepository.findUniqueAdaptiveAssessment(companyId, testName);
		 AdaptiveAssessment adaptiveAssessment = adaptiveAssessmentRepository.findUniqueAdaptiveAssessment(companyId, testName);
		 request.getSession().setAttribute("adaptiveAssessmentTest", adaptiveAssessment);
		 Question currentQuestion;
		 if(instance == null){
			 instance = new AdaptiveAssessmentInstance(email, companyId, testName);
			 instance.setCurrentLevel(AdaptiveAssessmentSkillLevel.LEVEL1);
			 instance.setNoOfProgressions(0);
			 instance.setNoofRegressions(0);
			 AdaptiveAssessmentSkill skillLevel = levelService.findUniqueLevelForAdaptiveAssessment(companyId, testName, AdaptiveAssessmentSkillLevel.LEVEL1);
			 Integer noOfQuestions = skillLevel.getNoOfQuestions();
			 List<Question> questions = questionservice.getRandomQuestions(companyId, skillLevel.getQualifier1(), skillLevel.getQualifier2(), skillLevel.getQualifier3(), skillLevel.getQualifier4(), skillLevel.getQualifier5(), DifficultyLevel.EASY, QuestionType.MCQ, PageRequest.of(0, noOfQuestions));
			 if(questions.size() == 0){
				 //throw new AssessmentGenericException("NO_QUESTIONS_FOR_CURRENT_LEVEL - Add more content for "+skillLevel.getQualifier1()+"-"+skillLevel.getQualifier2()+"-"+skillLevel.getQualifier3()+"-"+skillLevel.getQualifier4()+"-"+skillLevel.getQualifier5());
				 throw new AssessmentGenericException(AdaptiveConstants.NO_QUESTIONS_CURRENT_LEVEL);
			 }
			 request.getSession().setAttribute("currentLevel", AdaptiveAssessmentSkillLevel.LEVEL1);
			 request.getSession().setAttribute("currentLevelQuestions", questions);
			 request.getSession().setAttribute("currentQuestion", questions.get(0));
			 request.getSession().setAttribute("currentQuestionIndex", 0);
			 request.getSession().setAttribute("attemptOfLevelInstance", 1);
			 currentQuestion = questions.get(0);
			 AdaptiveAssessmentQuestionMappperInstance questionMappperInstance = getAdaptiveAssessmentQuestionMappperInstance(email, testName, companyId, AdaptiveAssessmentSkillLevel.LEVEL1,currentQuestion, 1);
			 request.getSession().setAttribute("questionMappperInstance", questionMappperInstance);
			 return questionMappperInstance;
		 }
		 else{
			 	if(instance.getComplete() != null && instance.getComplete()){
			 		throw new AssessmentGenericException(AdaptiveConstants.ASSESSMENT_COMPLETE_FOR_USER);
			 	}
			 	
			 Integer attempts = levelInstanceService.findCountOfAdaptiveAssessmentLevelInstances(companyId, testName, email, instance.getCurrentLevel());
			 Integer attempt = 1;
			 	if(attempts == 0){
			 		attempt = 1;
			 		List<AdaptiveAssessmentLevelInstance> levelInstances = levelInstanceService.findAdaptiveAssessmentLevelInstances(companyId, testName, email, instance.getCurrentLevel());
			 			if(levelInstances.size() == 0){
			 				attempt= 1;
			 			}
			 			else{
			 				AdaptiveAssessmentLevelInstance last = levelInstances.get(levelInstances.size() - 1);
					 		if(last.getComplete() != null && last.getComplete()){
					 			attempt = levelInstances.size() + 1;
					 		}
					 		else{
					 			attempt = levelInstances.size();
					 		}
			 			}
			 		
			 	}
			 
			 
			 AdaptiveAssessmentSkill skillLevel = levelService.findUniqueLevelForAdaptiveAssessment(companyId, testName, instance.getCurrentLevel());
			 Integer noOfQuestions = skillLevel.getNoOfQuestions();
			 List<Question> questions = questionservice.getRandomQuestions(companyId, skillLevel.getQualifier1(), skillLevel.getQualifier2(), skillLevel.getQualifier3(), skillLevel.getQualifier4(), skillLevel.getQualifier5(), DifficultyLevel.EASY, QuestionType.MCQ, PageRequest.of(0, noOfQuestions));
			 if(questions.size() == 0){
				 //throw new AssessmentGenericException("NO_QUESTIONS_FOR_CURRENT_LEVEL - Add more content for "+skillLevel.getQualifier1()+"-"+skillLevel.getQualifier2()+"-"+skillLevel.getQualifier3()+"-"+skillLevel.getQualifier4()+"-"+skillLevel.getQualifier5());
				 throw new AssessmentGenericException(AdaptiveConstants.NO_QUESTIONS_CURRENT_LEVEL);
			 }
			 request.getSession().setAttribute("currentLevel", instance.getCurrentLevel());
			 request.getSession().setAttribute("currentLevelQuestions", questions);
			 request.getSession().setAttribute("currentQuestion", questions.get(0));
			 request.getSession().setAttribute("currentQuestionIndex", 0);
			 request.getSession().setAttribute("attemptOfLevelInstance", attempt);
			 currentQuestion = questions.get(0);
			 AdaptiveAssessmentQuestionMappperInstance questionMappperInstance = getAdaptiveAssessmentQuestionMappperInstance(email, testName, companyId, instance.getCurrentLevel(),currentQuestion, attempt);
			 request.getSession().setAttribute("questionMappperInstance", questionMappperInstance);
			 return questionMappperInstance;
		 }
		 
	 }
	 
	 private AdaptiveAssessmentQuestionMappperInstance getAdaptiveAssessmentQuestionMappperInstance(String email, String testName, String companyId, AdaptiveAssessmentSkillLevel level, Question currentQuestion, Integer attempt){
		 AdaptiveAssessmentQuestionMappperInstance instance = mapperInstanceservice.findUniqueAdaptiveAssessmentQuestionMappperInstance(companyId, currentQuestion.getQuestionText(), testName, email, level, attempt);
		 if(instance == null){
			 instance = new AdaptiveAssessmentQuestionMappperInstance(companyId, currentQuestion, testName, email, level, attempt );
		 }
		 return instance;
	 }
	
	
	 //next
	 @CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")
	 @RequestMapping(value = "/nextAdaptiveAssessmentQ", method = RequestMethod.POST)
	  public @ResponseBody JsonStatusBean nextAdaptiveAssessmentQ(@RequestHeader(name="token") String token, @RequestParam(name= "companyId") String companyId, @RequestParam(name= "email") String email ,@RequestParam(name= "testName") String testName,  @RequestParam String timeCounter, @RequestBody AdaptiveAssessmentInstanceDTO currentQuestion, HttpServletRequest request, HttpServletResponse response) throws IOException {
		 JsonStatusBean bean = new JsonStatusBean();
		 response.setHeader("Access-Control-Allow-Origin", "http://localhost:4200");
			response.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
			response.setHeader("Access-Control-Max-Age", "3600");
			response.setHeader("Access-Control-Allow-Headers", "X-PINGOTHER,Content-Type,X-Requested-With,accept,Origin,Access-Control-Request-Method,Access-Control-Request-Headers,Authorization");
			response.addHeader("Access-Control-Expose-Headers", "xsrf-token");	
		 AdaptiveAssessmentResponseDto responseDto = new AdaptiveAssessmentResponseDto();
		 try {
			adaptiveAssessmentUtilService.setAnswers(request, currentQuestion);
			//AdaptiveAssessmentQuestionMappperInstance current = adaptiveAssessmentUtilService.nextQuestion(request);
			AdaptiveQMIWrapper currentW = adaptiveAssessmentUtilService.nextQuestion(request);
				if(currentW.getMessage() != null && currentW.getMessage().trim().length() > 0){
					bean.setErrorMessage("");
					bean.setData(currentW.getMessage());
					return bean;
				}
			AdaptiveAssessmentQuestionMappperInstance current = currentW.getInstance();
			AdaptiveAssessment test = (AdaptiveAssessment)request.getSession().getAttribute("test");
			User user = (User) request.getSession().getAttribute("user");
			responseDto.setEmail(user.getEmail());
			responseDto.setTestName(testName);
			AdaptiveAssessmentInstanceDTO dto = new AdaptiveAssessmentInstanceDTO();
			dto.setQuestionMapperInstance(current);
			responseDto.setInstanceDTO(dto);
			bean.setData(responseDto);
//			response.setHeader("Access-Control-Allow-Origin", "http://localhost:4200,http://beforesubmit.com,*");
//			response.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
//			response.setHeader("Access-Control-Max-Age", "3600");
//			response.setHeader("Access-Control-Allow-Headers", "X-PINGOTHER,Content-Type,X-Requested-With,accept,Origin,Access-Control-Request-Method,Access-Control-Request-Headers,Authorization");
//			response.addHeader("Access-Control-Expose-Headers", "xsrf-token");	
			return bean;
		} catch (AssessmentGenericException e) {
			bean.setErrorMessage(e.getMessage());
			return bean;
			// TODO Auto-generated catch block
//				if(e.getMessage().equals(AdaptiveConstants.ADAPTIVE_ASSESSMENT_COMPLETE)){
//					bean.setErrorMessage("");
//					bean.setData("ASSESSMENT_COMPLETE");
//					return bean;
//				}
//				else{
//					bean.setErrorMessage(e.getMessage());
//					return bean;
//				}
			
		}
	
	 }
	
	//previous
	 @CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")
	 @RequestMapping(value = "/prevAdaptiveAssessmentQ", method = RequestMethod.POST)
	  public @ResponseBody JsonStatusBean prevAdaptiveAssessmentQ(@RequestHeader(name="token") String token, @RequestParam(name= "companyId") String companyId, @RequestParam(name= "email") String email ,@RequestParam(name= "testName") String testName,  @RequestParam String timeCounter, @RequestBody AdaptiveAssessmentInstanceDTO currentQuestion, HttpServletRequest request, HttpServletResponse response) throws IOException {
		 JsonStatusBean bean = new JsonStatusBean();
		 AdaptiveAssessmentResponseDto responseDto = new AdaptiveAssessmentResponseDto();
		 response.setHeader("Access-Control-Allow-Origin", "http://localhost:4200");
			response.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
			response.setHeader("Access-Control-Max-Age", "3600");
			response.setHeader("Access-Control-Allow-Headers", "X-PINGOTHER,Content-Type,X-Requested-With,accept,Origin,Access-Control-Request-Method,Access-Control-Request-Headers,Authorization");
			response.addHeader("Access-Control-Expose-Headers", "xsrf-token");	
		 try {
			adaptiveAssessmentUtilService.setAnswers(request, currentQuestion);
			//AdaptiveAssessmentQuestionMappperInstance current = adaptiveAssessmentUtilService.prevQuestion(request);
			AdaptiveQMIWrapper currentW = adaptiveAssessmentUtilService.prevQuestion(request);
			if(currentW.getMessage() != null && currentW.getMessage().trim().length() > 0){
				bean.setErrorMessage("");
				bean.setData(currentW.getMessage());
				return bean;
			}
			AdaptiveAssessmentQuestionMappperInstance current = currentW.getInstance();
			AdaptiveAssessment test = (AdaptiveAssessment)request.getSession().getAttribute("test");
			User user = (User) request.getSession().getAttribute("user");
			responseDto.setEmail(user.getEmail());
			responseDto.setTestName(testName);
			AdaptiveAssessmentInstanceDTO dto = new AdaptiveAssessmentInstanceDTO();
			dto.setQuestionMapperInstance(current);
			responseDto.setInstanceDTO(dto);
			bean.setData(responseDto);
//			response.setHeader("Access-Control-Allow-Origin", "http://localhost:4200");
//			response.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
//			response.setHeader("Access-Control-Max-Age", "3600");
//			response.setHeader("Access-Control-Allow-Headers", "X-PINGOTHER,Content-Type,X-Requested-With,accept,Origin,Access-Control-Request-Method,Access-Control-Request-Headers,Authorization");
//			response.addHeader("Access-Control-Expose-Headers", "xsrf-token");	
			return bean;
		} catch (AssessmentGenericException e) {
			// TODO Auto-generated catch block
			bean.setErrorMessage(e.getMessage());
			return bean;
		}
	
	 }
	
	//submit
//	 @RequestMapping(value = "/submitAdaptiveAssessment", method = RequestMethod.POST)
//	  public @ResponseBody JsonStatusBean submitAdaptiveAssessment(@RequestHeader(name="token") String token, @RequestParam(name= "companyId") String companyId, @RequestParam(name= "email") String email ,@RequestParam(name= "testName") String testName, @RequestParam String questionMapperId, @RequestParam String timeCounter, @RequestBody AdaptiveAssessmentInstanceDTO currentQuestion, HttpServletRequest request, HttpServletResponse response) throws IOException {
//		 //not needed
//		 return null;
//	 }
	 
	 
	 public String getUserAfterCheckingNoOfAttempts(String user, String companyId, AdaptiveAssessment test, HttpServletRequest request){
			//UserTestSession session = testSessionRepository.findByPrimaryKey(user, test.getTestName(), test.getCompanyId());
			AdaptiveAssessmentInstance session = assessmentService.findAdaptiveAssessmentInstance(test.getAdaptiveAssessmentName(), companyId, user);
			String userNameNew = "";
			  if(session == null){
				  userNameNew = user;
				  request.getSession().setAttribute("noOfAttempts", 0);
				  return userNameNew;
			  }
			  else{
				  /**
				   * Step 2 - find out how many sessions for the given test the user has taken
				   */
				 // List<UserTestSession> sessions = testSessionRepository.findByTestNamePart(user+"["+test.getId(), test.getTestName(), test.getCompanyId());
				  List<AdaptiveAssessmentInstance> sessions = assessmentInstanceRep.findByTestNamePart(user+"["+test.getId(), test.getAdaptiveAssessmentName(), test.getCompanyId());
				  //int noOfConfAttempts = test.getNoOfConfigurableAttempts() ==null?50:test.getNoOfConfigurableAttempts();
				  /**
				   * Check whether test is configured with a domain having configurable no of attempts
				   */
				  String domain = "";
				  int noOfConfAttempts = 50;
				  	if(user.contains("@")){
				  		domain = user.substring(user.indexOf("@")+1, user.length());
				  		noOfConfAttempts = getNoOfConfigurableAttempts(test, domain);
				  	}
				  	else{
				  		noOfConfAttempts = getNoOfConfigurableAttemptsWithoutDomainCheck(test);
				  	}
				  
				   
				  /**
				   * End above check
				   */
				  	if(noOfConfAttempts <= (sessions.size()+1)){
				  		return "fail";
				  	}
				  
				  userNameNew = user+"["+test.getId()+"-"+(sessions.size()+1+"]");
				  request.getSession().setAttribute("noOfAttempts", (sessions.size()+1));
				  return userNameNew;
			  }
		}
		
		private Integer getNoOfConfigurableAttemptsWithoutDomainCheck(AdaptiveAssessment test){
			int noOfConfAttempts = test.getNoOfConfigurableAttempts() ==null?50:test.getNoOfConfigurableAttempts();
			return noOfConfAttempts;  
		}
		
		private Integer getNoOfConfigurableAttempts(AdaptiveAssessment test, String domain){
			/**
			 * Add domain checking to Adaptive assessment later
			 */
			//			TestDomainMapping domainMapping = domainMappingService.findByPrimaryKey(test.getCompanyId(), test.getTestName(), domain);
//				if(domainMapping != null){
//					return domainMapping.getNoOfAttempts();
//				}
			int noOfConfAttempts = test.getNoOfConfigurableAttempts() ==null?50:test.getNoOfConfigurableAttempts();
			return noOfConfAttempts;  
		}
		
		@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")
		@RequestMapping(value = "/statsAdaptiveAssessment", method = RequestMethod.GET)
		  public @ResponseBody JsonStatusBean statsAdaptiveAssessment(@RequestHeader(name="token") String token, @RequestParam(name= "companyId") String companyId, @RequestParam(name= "email") String email ,@RequestParam(name= "testName") String testName, HttpServletRequest request, HttpServletResponse response) throws IOException {
			 response.setHeader("Access-Control-Allow-Origin", "http://localhost:4200");
				response.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
				response.setHeader("Access-Control-Max-Age", "3600");
				response.setHeader("Access-Control-Allow-Headers", "X-PINGOTHER,Content-Type,X-Requested-With,accept,Origin,Access-Control-Request-Method,Access-Control-Request-Headers,Authorization");
				response.addHeader("Access-Control-Expose-Headers", "xsrf-token");	
			JsonStatusBean bean = new JsonStatusBean();
			 //AdaptiveAssessmentResponseDto responseDto = new AdaptiveAssessmentResponseDto();
			 AdaptiveAssessmentInstance instance = assessmentService.findAdaptiveAssessmentInstance(testName, companyId, email);
			 bean.setData(instance);
//			 response.setHeader("Access-Control-Allow-Origin", "http://localhost:4200");
//				response.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
//				response.setHeader("Access-Control-Max-Age", "3600");
//				response.setHeader("Access-Control-Allow-Headers", "X-PINGOTHER,Content-Type,X-Requested-With,accept,Origin,Access-Control-Request-Method,Access-Control-Request-Headers,Authorization");
//				response.addHeader("Access-Control-Expose-Headers", "xsrf-token");	
			 return bean;
		
		 }
		
		@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")
		@RequestMapping(value = "/statsAdaptiveAssessmentLevel", method = RequestMethod.GET)
		  public @ResponseBody JsonStatusBean statsAdaptiveAssessmentLevel(@RequestHeader(name="token") String token, @RequestParam(name= "companyId") String companyId, @RequestParam(name= "email") String email ,@RequestParam(name= "testName") String testName, @RequestHeader(name="assLevel") String assLevel, HttpServletRequest request, HttpServletResponse response) throws IOException {
			 JsonStatusBean bean = new JsonStatusBean();
			 response.setHeader("Access-Control-Allow-Origin", "http://localhost:4200");
				response.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
				response.setHeader("Access-Control-Max-Age", "3600");
				response.setHeader("Access-Control-Allow-Headers", "X-PINGOTHER,Content-Type,X-Requested-With,accept,Origin,Access-Control-Request-Method,Access-Control-Request-Headers,Authorization");
				response.addHeader("Access-Control-Expose-Headers", "xsrf-token");	
			 AdaptiveAssessmentSkillLevel level = null;
			 try {
				level = AdaptiveAssessmentSkillLevel.valueOf(assLevel);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				bean.setErrorMessage(AdaptiveConstants.WRONG_LEVEL);
				return bean;
			}
			 //AdaptiveAssessmentResponseDto responseDto = new AdaptiveAssessmentResponseDto();
			 List<AdaptiveAssessmentLevelInstance> stepInstances = levelInstanceService.findAdaptiveAssessmentLevelInstances(companyId, testName, email, level);
			 bean.setData(stepInstances);
//			 response.setHeader("Access-Control-Allow-Origin", "http://localhost:4200");
//				response.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
//				response.setHeader("Access-Control-Max-Age", "3600");
//				response.setHeader("Access-Control-Allow-Headers", "X-PINGOTHER,Content-Type,X-Requested-With,accept,Origin,Access-Control-Request-Method,Access-Control-Request-Headers,Authorization");
//				response.addHeader("Access-Control-Expose-Headers", "xsrf-token");	
			 return bean;
		
		 }
		
		
		@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")
		@RequestMapping(value = "/recommsAdaptiveAssessmentLevel", method = RequestMethod.GET)
		  public @ResponseBody JsonStatusBean recommsAdaptiveAssessmentLevel(@RequestHeader(name="token") String token, @RequestParam(name= "companyId") String companyId, @RequestParam(name= "email") String email ,@RequestParam(name= "testName") String testName, @RequestHeader(name="assLevel") String assLevel, HttpServletRequest request, HttpServletResponse response) throws IOException {
			 response.setHeader("Access-Control-Allow-Origin", "http://localhost:4200");
				response.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
				response.setHeader("Access-Control-Max-Age", "3600");
				response.setHeader("Access-Control-Allow-Headers", "X-PINGOTHER,Content-Type,X-Requested-With,accept,Origin,Access-Control-Request-Method,Access-Control-Request-Headers,Authorization");
				response.addHeader("Access-Control-Expose-Headers", "xsrf-token");	
			JsonStatusBean bean = new JsonStatusBean();
			 AdaptiveAssessmentSkillLevel level = null;
			 try {
				level = AdaptiveAssessmentSkillLevel.valueOf(assLevel);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				bean.setErrorMessage(AdaptiveConstants.WRONG_LEVEL);
				return bean;
			}
			 //AdaptiveAssessmentResponseDto responseDto = new AdaptiveAssessmentResponseDto();
			 List<QualifierSkillLevelDto> recomms = getDynamicRecommForLevel(level, testName, email,  companyId);
			 	if(recomms.size() == 0){
			 		recomms = getRecommForLevel(level);
			 	}
			 bean.setData(recomms);
			 return bean;
		
		 }
		
		private List<QualifierSkillLevelDto> getDynamicRecommForLevel(AdaptiveAssessmentSkillLevel level, String testName, String email, String companyId){
			User user = userService.findByPrimaryKey(email, companyId);
			if(user== null){
				return null;
				
			}
			List<QualifierSkillLevelDto> skills = new ArrayList<>();
			
			AdaptiveAssessmentSkillLevel prev = prevLevel(level);
			
			AdaptiveAssessmentLevelInstance levelInstance = levelInstanceService.findUniqueAdaptiveAssessmentLevelInstanceLastAttempt(companyId, testName, email, prev);
			if(levelInstance == null){
				return skills;
			}
				
		
			List<AdaptiveAssessmentQuestionMappperInstance> answers = mapperInstanceservice.findAdaptiveAssessmentQuestionMappperInstancesForLevelAndAttempt(companyId, testName, email, prev, levelInstance.getAttempt());
			
			
			
			List<Date> dates = new ArrayList<>();
			
			List<CodingAnswerDTO> coding = new ArrayList<>();
			
			Map<CandidateProfileParams, List<AdaptiveAssessmentQuestionMappperInstance>> map = new HashMap<>();
			for(AdaptiveAssessmentQuestionMappperInstance ans : answers){
					if(ans.getCreateDate() != null){
						dates.add(ans.getCreateDate());
					}
				
				//CandidateProfileParams param = new CandidateProfileParams(ans.getQuestion().getQualifier1(), ans.getQuestion().getQualifier2(), ans.getQuestion().getQualifier3(), ans.getQuestion().getQualifier4(), ans.getQuestion().getQualifier5()); 
				String q2 = ans.getQuestion().getQualifier2() == null?"NA":ans.getQuestion().getQualifier2();
				String q3 = ans.getQuestion().getQualifier3() == null?"NA":ans.getQuestion().getQualifier3();
				String q4 = ans.getQuestion().getQualifier4() == null?"NA":ans.getQuestion().getQualifier4();
				String q5 = ans.getQuestion().getQualifier5() == null?"NA":ans.getQuestion().getQualifier5();
					CandidateProfileParams param = profileService.findUniqueCandidateProfileParams(user.getCompanyId(), ans.getQuestion().getQualifier1(), q2, q3, q4, q5);
					
						if(param == null){
							param = new CandidateProfileParams(ans.getQuestion().getQualifier1(), q2, q3, q4, q5);
							param.setLESS_THAN_TWENTY_PERCENT("Need to work a bit. Not good!");
							param.setBETWEEN_TWENTY_AND_FIFTY("Knowledge Quotient inadequate for the given topic!!");
							param.setBETWEEN_FIFTY_AND_SEVENTYFIVE("Average Grade for a given topic!!");
							param.setBETWEEN_SEVENTYFIVE_AND_NINETY("Good knowledge. Potential to be an expert in this area!!");
							param.setMORE_THAN_NINETY("Excellent knowledge on the theory behind the topic.");
							param.setQuestion(ans.getQuestion().getQuestionText());
						}
					
					
					if(param != null){
						if(map.get(param) == null){
							List<AdaptiveAssessmentQuestionMappperInstance> ins = new ArrayList<>();
							ins.add(ans);
							map.put(param, ins);
						}
						else{
							map.get(param).add(ans);
						}
					}

			}
			DecimalFormat df = new DecimalFormat("#.##");
			
			Mapper mapper = new DozerBeanMapper();
			int totQs = 0;
			int totAnsweredQs = 0;
			for(CandidateProfileParams param : map.keySet()){
				List<AdaptiveAssessmentQuestionMappperInstance> answersForQualifier = map.get(param);
				int noOfCorrect = 0;
				for(AdaptiveAssessmentQuestionMappperInstance ans :answersForQualifier ){
					totQs ++;
					if(ans.getCorrect()){
						noOfCorrect++;
					}
					
					if(ans.getAnswered() != null && ans.getAnswered()){
						totAnsweredQs++;
					}
				}
				
				Float percent = Float.parseFloat(df.format(noOfCorrect * 100 / answersForQualifier.size()));
				QualifierSkillLevelDto skill = new QualifierSkillLevelDto(param.getQualifier1(), param.getQualifier2(), param.getQualifier3(), param.getQualifier4(), param.getQualifier5());
				mapper.map(param, skill);
				//skill.setOverAll(param.toString());
				skill.setPercentage(percent);
				skills.add(skill);
				
				if(percent < 20 ){
					skill.setOverAll(param.getLESS_THAN_TWENTY_PERCENT());
					}
					else if(percent >= 20 && percent < 50){
						skill.setOverAll(param.getBETWEEN_TWENTY_AND_FIFTY());
					}
					else if(percent >= 50 && percent < 75){
						skill.setOverAll(param.getBETWEEN_FIFTY_AND_SEVENTYFIVE());
					}
					else if(percent >= 75 && percent < 90){
						skill.setOverAll(param.getBETWEEN_SEVENTYFIVE_AND_NINETY());
					}
					else{
						skill.setOverAll(param.getMORE_THAN_NINETY());
					}
				
			}
		 
			return skills;
			
		
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
				return AdaptiveAssessmentSkillLevel.LEVEL1;
			}
		}
		
		private List<QualifierSkillLevelDto> getRecommForLevel(AdaptiveAssessmentSkillLevel level){
			if(level.getLevel().equals(AdaptiveAssessmentSkillLevel.LEVEL1.getLevel())){
				 QualifierSkillLevelDto q1 = new QualifierSkillLevelDto();
				 q1.setQualifier1("HTML");
				 q1.setPercentage(75f);
				 q1.setOverAll("Reasonable Skills");
				 
				 QualifierSkillLevelDto q2 = new QualifierSkillLevelDto();
				 q2.setQualifier1("CSS");
				 q2.setPercentage(80f);
				 q2.setOverAll("Reasonable Skills");
				 List<QualifierSkillLevelDto> list = new ArrayList<QualifierSkillLevelDto>();
				 list.add(q1);
				 list.add(q2);
				 return list;
			}
			else if(level.getLevel().equals(AdaptiveAssessmentSkillLevel.LEVEL2.getLevel())){
				
				 QualifierSkillLevelDto q1 = new QualifierSkillLevelDto();
				 q1.setQualifier1("Python");
				 q1.setPercentage(55f);
				 q1.setOverAll("Acceptable Skills");
				 
				 QualifierSkillLevelDto q2 = new QualifierSkillLevelDto();
				 q2.setQualifier1("Video Processing");
				 q2.setPercentage(40f);
				 q2.setOverAll("Poor Skills");
				 List<QualifierSkillLevelDto> list = new ArrayList<QualifierSkillLevelDto>();
				 list.add(q1);
				 list.add(q2);
				 return list;
			}
			else if(level.getLevel().equals(AdaptiveAssessmentSkillLevel.LEVEL3.getLevel())){
				
				 QualifierSkillLevelDto q1 = new QualifierSkillLevelDto();
				 q1.setQualifier1("Core Mathematics");
				 q1.setPercentage(59f);
				 q1.setOverAll("Acceptable Skills");
				 
				 QualifierSkillLevelDto q2 = new QualifierSkillLevelDto();
				 q2.setQualifier1("Statistics");
				 q2.setPercentage(43f);
				 q2.setOverAll("Poor Skills");
				 List<QualifierSkillLevelDto> list = new ArrayList<QualifierSkillLevelDto>();
				 list.add(q1);
				 list.add(q2);
				 return list;
			}
			else if(level.getLevel().equals(AdaptiveAssessmentSkillLevel.LEVEL4.getLevel())){
				
				 QualifierSkillLevelDto q1 = new QualifierSkillLevelDto();
				 q1.setQualifier1("Algorithms & Datastructures ");
				 q1.setPercentage(54f);
				 q1.setOverAll("Acceptable Skills");
				 
				 QualifierSkillLevelDto q2 = new QualifierSkillLevelDto();
				 q2.setQualifier1("Data Mining");
				 q2.setPercentage(33f);
				 q2.setOverAll("Poor Skills");
				 List<QualifierSkillLevelDto> list = new ArrayList<QualifierSkillLevelDto>();
				 list.add(q1);
				 list.add(q2);
				 return list;
			}
			else {
				
				 QualifierSkillLevelDto q1 = new QualifierSkillLevelDto();
				 q1.setQualifier1("Neural Networks");
				 q1.setPercentage(51f);
				 q1.setOverAll("Acceptable Skills");
				 
				 QualifierSkillLevelDto q2 = new QualifierSkillLevelDto();
				 q2.setQualifier1("Machine Learning");
				 q2.setPercentage(31f);
				 q2.setOverAll("Poor Skills");
				 List<QualifierSkillLevelDto> list = new ArrayList<QualifierSkillLevelDto>();
				 list.add(q1);
				 list.add(q2);
				 return list;
			}
			
			
		}
		
		@RequestMapping(value = "/uniqueAdaptivetestbyid", method = RequestMethod.GET)
		@ResponseBody
		  public JsonStatusBean uniquetestbyid(@RequestHeader(name="token") String token, @RequestParam(name= "companyId") String companyId,@RequestParam Long testId, HttpServletRequest request, HttpServletResponse response) {
			  
			    AdaptiveAssessment test = adaptiveAssessmentRepository.findById(testId).get();
				JsonStatusBean bean = new JsonStatusBean();
				bean.setData(test);
				
				return bean;
			  }
}
