package com.assessment.web.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.assessment.common.CommonUtil;
import com.assessment.common.util.NavigationConstants;
import com.assessment.data.DifficultyLevel;
import com.assessment.data.FullStackOptions;
import com.assessment.data.ProgrammingLanguage;
import com.assessment.data.Question;
import com.assessment.data.QuestionType;
import com.assessment.data.User;
import com.assessment.repositories.QuestionMapperInstanceRepository;
import com.assessment.repositories.QuestionRepository;
import com.assessment.repositories.SkillRepository;
import com.assessment.repositories.TestRepository;
import com.assessment.repositories.UserTestSessionRepository;
import com.assessment.services.QuestionService;
import com.assessment.services.UserService;
import com.assessment.web.dto.PopulaQuestion;
import com.assessment.web.dto.PopularAssessment;
import com.assessment.web.dto.PopularSkillBasedQuestion;
import com.assessment.web.dto.PopularTestTaker;

@Controller
public class NewLoginController {
	@Autowired
	SkillRepository skillRep;
	@Autowired
	TestRepository testRep;
	@Autowired
	QuestionRepository questionRep;
	@Autowired
	private QuestionService questionService;
	
	@Autowired
	QuestionMapperInstanceRepository instanceRep;
	
	@Autowired
	UserTestSessionRepository sessionRep;
	
	@Autowired
	UserService userService;
	
	@RequestMapping(value = "/dashboardnew", method = RequestMethod.GET)
	public ModelAndView dashboardnew(@RequestParam(name= "page", required = false) Integer pageNumber, HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) throws Exception {
		User user = (User) request.getSession().getAttribute("user");
		List<PopularAssessment> popTests = sessionRep.findMaxTestNamesInTests(user.getCompanyId(), PageRequest.of(0, 10));
		List<PopularTestTaker> popularUsers = sessionRep.findMaxUsersInTests(user.getCompanyId(), PageRequest.of(0, 10));
			
			for(PopularTestTaker taker : popularUsers){
				try {
					User usr = userService.findByPrimaryKey(taker.getEmail(), user.getCompanyId());
					taker.setFirstName(usr.getFirstName() == null?taker.getEmail():usr.getFirstName());
					taker.setLastName(usr.getLastName() == null?"NA":usr.getLastName());
				} catch (Exception e) {
					// TODO Auto-generated catch block
					taker.setFirstName(taker.getEmail());
					taker.setLastName("NA");
					e.printStackTrace();
				}
			}
		
		List<PopulaQuestion> popularquestions = instanceRep.findMaxPopQuestionsInTests(user.getCompanyId(), PageRequest.of(0, 10));
		List<PopularSkillBasedQuestion> popSkills = questionRep.findPopSkillQuestionsInTests(user.getCompanyId(), PageRequest.of(0, 10));
		
		Long countMCQ = questionRep.getQuestionCountByQuestionType(user.getCompanyId(), QuestionType.MCQ);
		Long countFib = questionRep.getQuestionCountByQuestionType(user.getCompanyId(), QuestionType.FILL_BLANKS_MCQ);
		Long countImage = questionRep.getQuestionCountByQuestionType(user.getCompanyId(), QuestionType.IMAGE_UPLOAD_BY_USER);
		Long countMTF = questionRep.getQuestionCountByQuestionType(user.getCompanyId(), QuestionType.MATCH_FOLLOWING_MCQ);
		Long countCoding = questionRep.getQuestionCountByQuestionType(user.getCompanyId(), QuestionType.CODING);
		Long countSub = questionRep.getQuestionCountByQuestionType(user.getCompanyId(), QuestionType.SUBJECTIVE_TEXT);
		Long countVideo = questionRep.getQuestionCountByQuestionType(user.getCompanyId(), QuestionType.VIDEO_UPLOAD_BY_USER);
		Long countFullStack = questionRep.getFullStackQuestionCountByQuestionType(user.getCompanyId());
		ModelAndView mav = new ModelAndView("admindashboard");
		mav.addObject("popTests", popTests);
		
		mav.addObject("popularUsers", popularUsers);
		mav.addObject("popularquestions", popularquestions);
		mav.addObject("popSkills", popSkills);
		mav.addObject("countMCQ", countMCQ);
		mav.addObject("countFib", countFib);
		mav.addObject("countImage", countImage);
		
		mav.addObject("countMTF", countMTF);
		mav.addObject("countCoding", countCoding);
		mav.addObject("countSub", countSub);
		mav.addObject("countVideo", countVideo);
		mav.addObject("countFullStack", countFullStack);
		mav.addObject("firstName", user.getFirstName());
		mav.addObject("lastName", user.getLastName());
		
		return mav;
		//return getTestsDashboard(pageNumber, null, null, user);
	}
	
	private ModelAndView getTestsDashboard(Integer pageNumber, String skill, ModelAndView mav, User user){
		List<String> skills = skillRep.getSkillNamessByCompanyId(user.getCompanyId());
		mav = new ModelAndView("testsnew");
		mav.addObject("skills", skills);
		if(pageNumber == null){
			pageNumber = 0;
		}
		
		if(skill == null ){
			if(skills.size() > 0){
				Page<com.assessment.data.Test> tests = testRep.searchTestsBySkill(user.getCompanyId(), skills.get(0), PageRequest.of(pageNumber, NavigationConstants.NO_TESTS_PAGE));
				mav.addObject("tests", tests.getContent());
				CommonUtil.setCommonAttributesOfPagination(tests, mav.getModelMap(), pageNumber, "testsnew", null);
				return mav;
			}
			else{
				Page<com.assessment.data.Test> tests = testRep.searchTestsBySkill(user.getCompanyId(), "Java", PageRequest.of(pageNumber, NavigationConstants.NO_TESTS_PAGE));
				mav.addObject("tests", tests.getContent());
				CommonUtil.setCommonAttributesOfPagination(tests, mav.getModelMap(), pageNumber, "testsnew", null);
				return mav;
			}
		}
		else{
			Page<com.assessment.data.Test> tests = testRep.searchTestsBySkill(user.getCompanyId(), skill, PageRequest.of(pageNumber, NavigationConstants.NO_TESTS_PAGE));
			mav.addObject("tests", tests.getContent());
			Map<String, String> queryParams = new HashMap<>();
			queryParams.put("skill", skill);
			CommonUtil.setCommonAttributesOfPagination(tests, mav.getModelMap(), pageNumber, "testsnew", queryParams);
			return mav;
		}
		
		
		//return mav;
	}
	
	private ModelAndView getFilteredTestsDashboard(Integer pageNumber, String filterkey,  ModelAndView mav, User user){
		List<String> skills = skillRep.getSkillNamessByCompanyId(user.getCompanyId());
		mav = new ModelAndView("testsnew");
		mav.addObject("skills", skills);
		if(pageNumber == null){
			pageNumber = 0;
		}
		
		if(filterkey.contains("###")){
			String percentage = filterkey.substring(3, filterkey.length());
			Float per = Float.parseFloat(percentage);
			Page<com.assessment.data.Test> tests = testRep.bypercentageTests(user.getCompanyId(), per, PageRequest.of(pageNumber, NavigationConstants.NO_TESTS_PAGE));
			mav.addObject("tests", tests.getContent());
			Map<String, String> queryParams = new HashMap<>();
			queryParams.put("filterkey", "filterByPercent");
			queryParams.put("filtervalue", ""+per);
			queryParams.put("percentage", ""+percentage);
			CommonUtil.setCommonAttributesOfPagination(tests, mav.getModelMap(), pageNumber, "filtertestsPercentnew", queryParams);
			return mav;
		}
		
		if(filterkey.equals("general")){
			Page<com.assessment.data.Test> tests = testRep.generalTests(user.getCompanyId(), PageRequest.of(pageNumber, NavigationConstants.NO_TESTS_PAGE));
			mav.addObject("tests", tests.getContent());
			Map<String, String> queryParams = new HashMap<>();
			queryParams.put("filterkey", "general");
			CommonUtil.setCommonAttributesOfPagination(tests, mav.getModelMap(), pageNumber, "filtertestsnew", queryParams);
			return mav;
		}
		else if(filterkey.equals("fullstack")){
			Page<com.assessment.data.Test> tests = testRep.fullstackTests(user.getCompanyId(), PageRequest.of(pageNumber, NavigationConstants.NO_TESTS_PAGE));
			mav.addObject("tests", tests.getContent());
			Map<String, String> queryParams = new HashMap<>();
			queryParams.put("filterkey", "fullstack");
			CommonUtil.setCommonAttributesOfPagination(tests, mav.getModelMap(), pageNumber, "filtertestsnew", queryParams);
			return mav;
		}
		else if(filterkey.equals("confidence")){
			Page<com.assessment.data.Test> tests = testRep.confidenceTests(user.getCompanyId(), PageRequest.of(pageNumber, NavigationConstants.NO_TESTS_PAGE));
			mav.addObject("tests", tests.getContent());
			Map<String, String> queryParams = new HashMap<>();
			queryParams.put("filterkey", "confidence");
			CommonUtil.setCommonAttributesOfPagination(tests, mav.getModelMap(), pageNumber, "filtertestsnew", queryParams);
			return mav;
		}
		else if(filterkey.equals("webproctoronboarding")){
			Page<com.assessment.data.Test> tests = testRep.onboardingTests(user.getCompanyId(), PageRequest.of(pageNumber, NavigationConstants.NO_TESTS_PAGE));
			mav.addObject("tests", tests.getContent());
			Map<String, String> queryParams = new HashMap<>();
			queryParams.put("filterkey", "webproctoronboarding");
			CommonUtil.setCommonAttributesOfPagination(tests, mav.getModelMap(), pageNumber, "filtertestsnew", queryParams);
			return mav;
		}
		else if(filterkey.equals("recommendation")){
			Page<com.assessment.data.Test> tests = testRep.recommendationTests(user.getCompanyId(), PageRequest.of(pageNumber, NavigationConstants.NO_TESTS_PAGE));
			mav.addObject("tests", tests.getContent());
			Map<String, String> queryParams = new HashMap<>();
			queryParams.put("filterkey", "recommendation");
			CommonUtil.setCommonAttributesOfPagination(tests, mav.getModelMap(), pageNumber, "filtertestsnew", queryParams);
			return mav;
		}
		else if(filterkey.equals("VERY_EASY")){
			Page<com.assessment.data.Test> tests = testRep.byVeryEasydifflevelTests(user.getCompanyId(), PageRequest.of(pageNumber, NavigationConstants.NO_TESTS_PAGE));
			mav.addObject("tests", tests.getContent());
			Map<String, String> queryParams = new HashMap<>();
			queryParams.put("filterkey", "VERY_EASY");
			CommonUtil.setCommonAttributesOfPagination(tests, mav.getModelMap(), pageNumber, "filtertestsnew", queryParams);
			return mav;
		}
		else if(filterkey.equals("EASY")){
			Page<com.assessment.data.Test> tests = testRep.byEasydifflevelTests(user.getCompanyId(), PageRequest.of(pageNumber, NavigationConstants.NO_TESTS_PAGE));
			mav.addObject("tests", tests.getContent());
			Map<String, String> queryParams = new HashMap<>();
			queryParams.put("filterkey", "EASY");
			CommonUtil.setCommonAttributesOfPagination(tests, mav.getModelMap(), pageNumber, "filtertestsnew", queryParams);
			return mav;
		}
		else if(filterkey.equals("MEDIUM")){
			Page<com.assessment.data.Test> tests = testRep.byMediumdifflevelTests(user.getCompanyId(), PageRequest.of(pageNumber, NavigationConstants.NO_TESTS_PAGE));
			mav.addObject("tests", tests.getContent());
			Map<String, String> queryParams = new HashMap<>();
			queryParams.put("filterkey", "MEDIUM");
			CommonUtil.setCommonAttributesOfPagination(tests, mav.getModelMap(), pageNumber, "filtertestsnew", queryParams);
			return mav;
		}
		else if(filterkey.equals("DIFFICULT")){
			Page<com.assessment.data.Test> tests = testRep.byDifficultdifflevelTests(user.getCompanyId(), PageRequest.of(pageNumber, NavigationConstants.NO_TESTS_PAGE));
			mav.addObject("tests", tests.getContent());
			Map<String, String> queryParams = new HashMap<>();
			queryParams.put("filterkey", "DIFFICULT");
			CommonUtil.setCommonAttributesOfPagination(tests, mav.getModelMap(), pageNumber, "filtertestsnew", queryParams);
			return mav;
		}
		else if(filterkey.equals("VERY_DIFFICULT")){
			Page<com.assessment.data.Test> tests = testRep.byVeryDifficultdifflevelTests(user.getCompanyId(), PageRequest.of(pageNumber, NavigationConstants.NO_TESTS_PAGE));
			mav.addObject("tests", tests.getContent());
			Map<String, String> queryParams = new HashMap<>();
			queryParams.put("filterkey", "VERY_DIFFICULT");
			CommonUtil.setCommonAttributesOfPagination(tests, mav.getModelMap(), pageNumber, "filtertestsnew", queryParams);
			return mav;
		}
		else{
			Page<com.assessment.data.Test> tests = testRep.generalTests(user.getCompanyId(), PageRequest.of(pageNumber, NavigationConstants.NO_TESTS_PAGE));
			mav.addObject("tests", tests.getContent());
			Map<String, String> queryParams = new HashMap<>();
			queryParams.put("filterkey", "general");
			CommonUtil.setCommonAttributesOfPagination(tests, mav.getModelMap(), pageNumber, "filtertestsnew", queryParams);
			return mav;
		}
		
		
	}
	
	
	@RequestMapping(value = "/testsnew", method = RequestMethod.GET)
	public ModelAndView testsnew(@RequestParam(name= "page", required = false) Integer pageNumber,@RequestParam(name= "skill", required = false) String skill, HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) throws Exception {
		User user = (User) request.getSession().getAttribute("user");
		
		return getTestsDashboard(pageNumber, skill, null, user);
	}
	
	
	@RequestMapping(value = "/filtertestsnew", method = RequestMethod.GET)
	public ModelAndView filtertestsnew(@RequestParam(name= "page", required = false) Integer pageNumber,@RequestParam(name= "filterkey", required = true) String filterkey, HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) throws Exception {
		User user = (User) request.getSession().getAttribute("user");
		
		return getFilteredTestsDashboard(pageNumber, filterkey, null, user);
	}
	
	@RequestMapping(value = "/filtertestsPercentnew", method = RequestMethod.GET)
	public ModelAndView filtertestsPercentnew(@RequestParam(name= "page", required = false) Integer pageNumber,@RequestParam(name= "filterkey", required = true) String filterkey,@RequestParam(name= "filtervalue", required = true) String filtervalue, HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) throws Exception {
		User user = (User) request.getSession().getAttribute("user");
		
		return getFilteredTestsDashboard(pageNumber, "###"+filtervalue, null, user);
	}
	
	
	
	
//	Gulrez
	@RequestMapping(value = "/questionssnew", method = RequestMethod.GET)
	public ModelAndView questionssnew(@RequestParam(name= "page", required = false) Integer pageNumber,@RequestParam(name= "filterkey", required = false) String filterkey, HttpServletRequest request, HttpServletResponse response, ModelMap modelMap,@RequestParam(name = "msg",required = false) String msg) throws Exception {
		ModelAndView mav = new ModelAndView("questionssnew");
		User user = (User) request.getSession().getAttribute("user");
		if(pageNumber == null){
			pageNumber = 0;
		}
		if(msg!=null) {
			mav.addObject("message", "Question Save Success");// later put it as label
			mav.addObject("msgtype", "Success");
			mav.addObject("icon", "success");
		}
		if(filterkey==null) {
			Page<com.assessment.data.Question> questions = questionRep.findQuestionsByCompanyId(user.getCompanyId(), PageRequest.of(pageNumber, NavigationConstants.NO_QUESTIONS_PAGE));
			mav.addObject("questions", questions.getContent());
			Map<String, String> queryParams = new HashMap<>();
			CommonUtil.setCommonAttributesOfPagination(questions, mav.getModelMap(), pageNumber, "questionssnew", queryParams);
		}
		else if (filterkey.equals("MCQ")||filterkey.equals("CODING")||filterkey.equals("FULL_STACK_JAVA")||filterkey.equals("FULLSTACK")||filterkey.equals("FILL_BLANKS_MCQ")||filterkey.equals("MATCH_FOLLOWING_MCQ")||filterkey.equals("IMAGE_UPLOAD_BY_USER")||filterkey.equals("VIDEO_UPLOAD_BY_USER")||filterkey.equals("SUBJECTIVE_TEXT")) {
			 Enum<?> questionType = Enum.valueOf(QuestionType.class, filterkey);
		     questionType = QuestionType.valueOf(filterkey);
			Page<com.assessment.data.Question> questions = questionRep.getQuestionByQuestionType(user.getCompanyId(), (QuestionType) questionType,PageRequest.of(pageNumber, NavigationConstants.NO_QUESTIONS_PAGE));
			mav.addObject("questions", questions.getContent());
			Map<String, String> queryParams = new HashMap<>();
			queryParams.put("filterkey", filterkey);
			CommonUtil.setCommonAttributesOfPagination(questions, mav.getModelMap(), pageNumber, "questionssnew", queryParams);
		}
		else if (filterkey.equals("VERY_EASY")||filterkey.equals("EASY")||filterkey.equals("MEDIUM")||filterkey.equals("DIFFICULT")||filterkey.equals("VERY_DIFFICULT")) {
			 Enum<?> level = Enum.valueOf(DifficultyLevel.class, filterkey);
		     level = DifficultyLevel.valueOf(filterkey);
			Page<com.assessment.data.Question> questions = questionRep.getQuestionByDifficultyLevel(user.getCompanyId(), (DifficultyLevel) level,PageRequest.of(pageNumber, NavigationConstants.NO_QUESTIONS_PAGE));
			mav.addObject("questions", questions.getContent());
			Map<String, String> queryParams = new HashMap<>();
			queryParams.put("filterkey", filterkey);
			CommonUtil.setCommonAttributesOfPagination(questions, mav.getModelMap(), pageNumber, "questionssnew", queryParams);
		}
		else if (filterkey.equals("JAVA")||filterkey.equals("C")||filterkey.equals("CPLUSPLUS")||filterkey.equals("DotNet")||filterkey.equals("CHASH")||filterkey.equals("PYTHON")||filterkey.equals("PHP")||filterkey.equals("JAVASCRIPT")||filterkey.equals("CLOJURE")||filterkey.equals("GO")||filterkey.equals("BASH")||filterkey.equals("OBJECTIVE_C")||filterkey.equals("MYSQL")||filterkey.equals("PERL")||filterkey.equals("RUST")) {
			 Enum<?> language = Enum.valueOf(ProgrammingLanguage.class, filterkey);
		     language = ProgrammingLanguage.valueOf(filterkey);
 			Page<Question> questions = questionRep.getQuestionByProgrammingLanguage(user.getCompanyId(), (ProgrammingLanguage) language, PageRequest.of(pageNumber, NavigationConstants.NO_QUESTIONS_PAGE));
			mav.addObject("questions", questions.getContent());
			Map<String, String> queryParams = new HashMap<>();
			queryParams.put("filterkey", filterkey);
			CommonUtil.setCommonAttributesOfPagination(questions, mav.getModelMap(), pageNumber, "questionssnew", queryParams);
		}
		mav.addObject("filterType", filterkey);
		return mav;
	}
 	
//	Gulrez
	
	@RequestMapping(value = "/createtestnew", method = RequestMethod.GET)
	public ModelAndView createtestnew(@RequestParam(name= "page", required = false) Integer pageNumber, HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) throws Exception {
		ModelAndView mav = new ModelAndView("create-test");
		return mav;
	}
	
	@RequestMapping(value = "/createquestionnew", method = RequestMethod.GET)
	public ModelAndView createquestionnew(@RequestParam(name="type",required = false) String type,@RequestParam(name= "qid", required = false) Long qid, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mav = new ModelAndView();
		
		if(qid != null) {
			Question question = questionService.findById(qid);
			type=question.getQuestionType().toString();
		}
		
		if(type.equals("MCQ")) {
			mav = new ModelAndView("create-question");
			mav.addObject("types", QuestionType.MCQ);
		}else if (type.equals("CODING")) {
			mav = new ModelAndView("create_coding_question");
			mav.addObject("types", QuestionType.CODING);
		}else if (type.equals("FULL_STACK_JAVA")) {
			mav = new ModelAndView("create_fullstack_question");
			mav.addObject("types", QuestionType.FULL_STACK_JAVA);
		}else if (type.equals("FULLSTACK")) {
			mav = new ModelAndView("create_fullstack_question");
			mav.addObject("types", QuestionType.FULLSTACK);
		}else if (type.equals("FILL_BLANKS_MCQ")) {
			mav = new ModelAndView("create_fillblankmcq_question");
			mav.addObject("types", QuestionType.FILL_BLANKS_MCQ);
		}else if (type.equals("MATCH_FOLLOWING_MCQ")) {
			mav = new ModelAndView("create_match_question");
			mav.addObject("types", QuestionType.MATCH_FOLLOWING_MCQ);
		}else if (type.equals("IMAGE_UPLOAD_BY_USER")) {
			mav = new ModelAndView("create_img_video_subjective_question");
			mav.addObject("types", QuestionType.IMAGE_UPLOAD_BY_USER);
		}else if (type.equals("VIDEO_UPLOAD_BY_USER")) {
			mav = new ModelAndView("create_img_video_subjective_question");
			mav.addObject("types", QuestionType.VIDEO_UPLOAD_BY_USER);
		} else if (type.equals("SUBJECTIVE_TEXT")) {
			mav = new ModelAndView("create_img_video_subjective_question");
			mav.addObject("types", QuestionType.SUBJECTIVE_TEXT);
		}
		
		if(qid == null) {
			Question question = new Question();
			mav.addObject("question", question);
		}
		else {
			Question question = questionService.findById(qid);
			question.setUpFromInUpdateMode();
			mav.addObject("question", question);
		}
		
		mav.addObject("levels", DifficultyLevel.values());
//		mav.addObject("types", QuestionType.values());
  		mav.addObject("languages", ProgrammingLanguage.values());
  		mav.addObject("stacks", FullStackOptions.values());
		
	
		return mav;
	}
	
	
}
