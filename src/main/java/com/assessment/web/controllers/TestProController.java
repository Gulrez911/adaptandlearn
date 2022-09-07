package com.assessment.web.controllers;

import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.assessment.common.CommonUtil;
import com.assessment.common.PropertyConfig;
import com.assessment.common.Qualifiers;
import com.assessment.common.util.EmailGenericMessageThread;
import com.assessment.data.CandidateProfileParams;
import com.assessment.data.Company;
import com.assessment.data.DifficultyLevel;
import com.assessment.data.ProgrammingLanguage;
import com.assessment.data.Question;
import com.assessment.data.QuestionMapper;
import com.assessment.data.QuestionMapperInstance;
import com.assessment.data.QuestionType;
import com.assessment.data.Section;
import com.assessment.data.Skill;
import com.assessment.data.Test;
import com.assessment.data.User;
import com.assessment.data.UserTestSession;
import com.assessment.data.UserType;
import com.assessment.repositories.QuestionMapperRepository;
import com.assessment.repositories.QuestionRepository;
import com.assessment.repositories.SkillRepository;
import com.assessment.repositories.UserTestSessionRepository;
import com.assessment.services.CandidateProfileParamsService;
import com.assessment.services.CompanyService;
import com.assessment.services.FileStatusService;
import com.assessment.services.QuestionMapperInstanceService;
import com.assessment.services.QuestionService;
import com.assessment.services.SectionService;
import com.assessment.services.SkillService;
import com.assessment.services.TestService;
import com.assessment.services.UserService;
import com.assessment.services.UserTestSessionService;
import com.assessment.web.dto.CodingAnswerDTO;
import com.assessment.web.dto.QualifierSkillLevelDto;
import com.assessment.web.dto.SectionDto;
import com.assessment.web.dto.SkillRecommDto;
import com.assessment.web.dto.TestDTO;
import com.assessment.web.dto.TestSessionDTO;
import com.assessment.web.dto.TestSessionDTOBulk;
import com.assessment.web.dto.TestSessionDTOBulkWrapper;

@Controller
@SessionAttributes("test,sectionDTO")
public class TestProController {
	@Autowired
	CompanyService companyService;

	@Autowired
	TestService testService;

	@Autowired
	SkillService skillService;

	@Autowired
	QuestionService questionService;

	@Autowired
	SectionService sectionService;

	@Autowired
	UserService userService;

	@Autowired
	PropertyConfig propertyConfig;

	@Autowired
	SkillRepository skillRepository;

	@Autowired
	QuestionMapperInstanceService questionMapperInstanceService;

	@Autowired
	QuestionRepository questionRepository;

	@Autowired
	FileStatusService fileStatusService;

	@Autowired
	QuestionMapperRepository questionMapperRepository;

	Logger logger = LoggerFactory.getLogger(TestProController.class);

	@Autowired
	UserTestSessionService userTestSessionService;

	@Autowired
	UserTestSessionRepository testSessionRepository;

	@Autowired
	CandidateProfileParamsService profileService;

//	@RequestMapping(value = "/protestbank", method = RequestMethod.GET)
//	public ModelAndView protestbank(HttpServletRequest request, HttpServletResponse response) {
//		ModelAndView mav = new ModelAndView("pro_tests");
//		return mav;
//	}
	
	@RequestMapping(value = "/protestbank", method = RequestMethod.GET)
	  public ModelAndView testlist(@RequestParam(name= "page", required = false) Integer pageNumber, HttpServletRequest request, HttpServletResponse response) {
		    ModelAndView mav = null;
		    User user = (User) request.getSession().getAttribute("user");
		    mav = new ModelAndView("pro_tests");
		    if(pageNumber == null) {
				pageNumber = 0;
			}
		    Page<Test> tests = testService.findByCompanyId(user.getCompanyId(),pageNumber);
	  		mav.addObject("tests", testService.populateWithPublicUrl(tests.getContent()));
		  
	  		CommonUtil.setCommonAttributesOfPagination(tests, mav.getModelMap(), pageNumber, "protestbank", null);
			return mav;
		  }
	
	@RequestMapping(value = "/pro_test_add", method = RequestMethod.GET)
	public ModelAndView proTest(HttpServletRequest request, HttpServletResponse response) {
//		ModelAndView mav = new ModelAndView("pro_test-details");
//		ModelAndView mav = new ModelAndView("create-new-test");

		ModelAndView mav = null;
		mav = new ModelAndView("create_test_pro_step1");
		Test test = new Test();
		mav.addObject("test", test);
		User user = (User) request.getSession().getAttribute("user");
		mav.addObject("user", user);
		request.getSession().setAttribute("test", test);
		SectionDto sectionDto = new SectionDto();
		sectionDto.setCurrent(true);
		sectionDto.setSectionNo(1);
		sectionDto.setCompanyId(user.getCompanyId());
		sectionDto.setTestName(test.getTestName());
		request.getSession().setAttribute("sectionDTO", sectionDto);
		String testTypes[] = { "Java", "Microsoft technologies", "C/C++", "Python", "General Knowledge",
				"Composite Test" };
		mav.addObject("testTypes", testTypes);
		// mav.addObject("qs", questions);
		List<Skill> skills = skillService.getSkillsByCompanyId(user.getCompanyId());
		mav.addObject("skls", skills);
		List<String> levels = Stream.of(DifficultyLevel.values()).map(DifficultyLevel::name)
				.collect(Collectors.toList());
		mav.addObject("levels", levels);
		return mav;
	}

	 @RequestMapping(value = "/updateTestPro", method = RequestMethod.GET)
	  public ModelAndView updateTest(@RequestParam String testId, HttpServletRequest request, HttpServletResponse response) {
	    ModelAndView mav = new ModelAndView("create_test_pro_step1");
	    User user = (User) request.getSession().getAttribute("user");
	   // testService.f
	    Test test = testService.findTestById(Long.valueOf(testId));
	    test.setSkills(test.getSkills());//just to populate the transient skls variable in Test object
		mav.addObject("test", test);
		mav.addObject("user", user);
		List<Skill> skills = skillService.getSkillsByCompanyId(user.getCompanyId());
	    mav.addObject("skls", skills);
	    String testTypes[] = {"Java", "Microsoft technologies", "C/C++", "Python", "General Knowledge", "Composite Test"};
	    mav.addObject("testTypes", testTypes);
		request.getSession().setAttribute("test", test);
		List<String> levels = Stream.of(DifficultyLevel.values())
               .map(DifficultyLevel::name)
               .collect(Collectors.toList());
	    mav.addObject("levels", levels);
		return mav;
	  }
	 
	 @RequestMapping(value = "/searchTestsPro", method = RequestMethod.GET)
	  public ModelAndView searchTests(@RequestParam(name= "page", required = false) Integer pageNumber, @RequestParam String searchText, HttpServletRequest request, HttpServletResponse response) {
	    ModelAndView mav = new ModelAndView("pro_tests");
	       User user = (User) request.getSession().getAttribute("user");
	       if(pageNumber == null) {
				pageNumber = 0;
			}
	       Page<Test> tests = testService.searchTests(user.getCompanyId(), searchText, pageNumber);
		mav.addObject("tests", testService.populateWithPublicUrl(tests.getContent()));
		Map<String, String> params = new HashMap<>();
		params.put("searchText", searchText);
		CommonUtil.setCommonAttributesOfPagination(tests, mav.getModelMap(), pageNumber, "searchTestsPro", params);
		return mav;
	  }
	 
	 
	@RequestMapping(value = "/saveAndGoToStep2Pro", method = RequestMethod.POST)
	public ModelAndView saveAndGoToStep2(HttpServletRequest request, HttpServletResponse response,
			@ModelAttribute("test") Test test) {
		ModelAndView mav = new ModelAndView("create_test_pro_step2");
		System.out.println(".......................................");
		User user = (User) request.getSession().getAttribute("user");
		System.out.println("......................................."+user.getEmail());
		if (!(user.getUserType().getType().equals(UserType.ADMIN.getType())
				|| user.getUserType().getType().equals(UserType.SUPER_ADMIN.getType())
				|| user.getUserType().getType().equals(UserType.LMS_ADMIN.getType()))) {
			
			System.out.println("............................failed..........."+user.getEmail());
			request.getSession().invalidate();
			mav = new ModelAndView("pro_login");
			user = new User();
			user.setEmail("newadmin2@iiht.com");
			user.setPassword("1234");
			user.setCompanyName("Eassess");
			mav.addObject("user", user);
			return mav;
		}

		Test test2 = (Test) request.getSession().getAttribute("test");
		if (test2.getId() != null) {
			test.setId(test2.getId());
			/**
			 * Since this is not user entered and disabled from ui - ui sends this as null.
			 */
			test.setTestName(test2.getTestName());
		} else {
			/**
			 * Make sure no body creates a test with same name again.This scenario is only
			 * applicable for new tests only.
			 */
			test.setTestName(test.getTestName().trim()); // make sure there are no leading or trailing spaces in test
															// name
			Test test3 = testService.findbyTest(test.getTestName(), user.getCompanyId());
			if (test3 != null) {
				mav = new ModelAndView("add_test");

				mav.addObject("test", test);

				mav.addObject("user", user);
				request.getSession().setAttribute("test", test2);
				SectionDto sectionDto = new SectionDto();
				sectionDto.setCurrent(true);
				sectionDto.setSectionNo(1);
				sectionDto.setCompanyId(user.getCompanyId());
				sectionDto.setTestName(test.getTestName());
				request.getSession().setAttribute("sectionDTO", sectionDto);
				String testTypes[] = { "Java", "Microsoft technologies", "C/C++", "Python", "General Knowledge",
						"Composite Test" };
				mav.addObject("testTypes", testTypes);
				// mav.addObject("qs", questions);
				List<Skill> skills = skillService.getSkillsByCompanyId(user.getCompanyId());
				mav.addObject("skls", skills);
				mav.addObject("message", "A test with the supplied test name exists! Please use a different name.");// later
																													// put
																													// it
																													// as
																													// label
				mav.addObject("msgtype", "Information");
				return mav;
			}
		}

		test.setCreatedBy(user.getEmail());
		test.setCompanyId(user.getCompanyId());
		test.setCompanyName(user.getCompanyName());
		if (test2 != null) {
			test.setTotalMarks(test2.getTotalMarks());
		}
		// Skill skill = skillService.findSkillByNameAndLevel("Java",
		// SkillLevel.BASIC.getLevel(), user.getCompanyId());
		test.setSkills(resoveSkillByIds(test.getSkls()));
		// test.getSkills().add(skill);
		testService.saveOrUpdate(test);
		request.getSession().setAttribute("test", test);
		mav.addObject("user", user);
		mav.addObject("test", test);
		SectionDto sectionDto = null;

		List<Section> sections = sectionService.getSectionsForTest(test.getTestName(), user.getCompanyId());
		if (sections.size() == 0) {
			sectionDto = new SectionDto();
			sectionDto.setCompanyId(user.getCompanyId());
			sectionDto.setTestName(test.getTestName());
			sectionDto.setSectionName("Main Section");
			sectionDto.setSectionNo(1);
			sectionDto.setCurrent(true);
			sectionDto.setNoOfQuestions(0);
			test.getSectionDtos().add(sectionDto);

		} else {
			int count = 1;

			for (Section s : sections) {
				Section section = s;
				SectionDto dto = new SectionDto();
				dto.setSectionNo(count);
				if (count == 1) {
					dto.setCurrent(true);
				}
				count++;
				dto.setSectionId(section.getId());
				dto.setCompanyId(user.getCompanyId());
				dto.setTestName(test.getTestName());
				dto.setSectionName(section.getSectionName());
				dto.setPercentQuestionsAsked(section.getPercentQuestionsAsked());
				dto.setNoOfQuestions(s.getNoOfQuestions());
				List<QuestionMapper> questionMappers = sectionService.getQuestionsForSection(test.getTestName(),
						section.getSectionName(), user.getCompanyId());
				for (QuestionMapper mapper : questionMappers) {
					dto.getQuestions().add(mapper.getQuestion());
				}
				test.getSectionDtos().add(dto);
			}

			if (sections.size() == 0) {
				sectionDto = new SectionDto();
				sectionDto.setCompanyId(user.getCompanyId());
				sectionDto.setTestName(test.getTestName());
				sectionDto.setSectionName("Main Section");
				sectionDto.setSectionNo(1);
				sectionDto.setCurrent(true);
				test.getSectionDtos().add(sectionDto);
			}

		}
		sectionDto = test.getSectionDtos().get(0);

		request.getSession().setAttribute("sectionDTO", sectionDto);
		mav.addObject("sectionDto", sectionDto);// added here
		// List<Question> qs = questionService.findQuestions(user.getCompanyId());
		List<Question> qs = null;
		if (test.getFullStackTest() == null || (!test.getFullStackTest())) {
			qs = questionService.getAllLevel1Questions(user.getCompanyId());
		} else {
			qs = questionRepository.findFullStackQuestionsByCompanyId(user.getCompanyId());
		}

		mav.addObject("qs", process(qs, sectionDto));
		mav.addObject("levels", DifficultyLevel.values());
		mav.addObject("types", QuestionType.values());
		mav.addObject("languages", ProgrammingLanguage.values());
		mav.addObject("test", test);
		return mav;
	}

	private List<Skill> resoveSkillByIds(List<String> skls) {
		List<Skill> skills = new ArrayList<>();
		for (String s : skls) {
			Skill skill = skillRepository.findById(Long.parseLong(s)).get();
			skills.add(skill);
		}
		return skills;
	}

	private List<Question> process(List<Question> questions, SectionDto sectionDto) {
		for (Question question : questions) {
			if (sectionDto.getQuestions().contains(question)) {
				question.setSelected(true);
			}
		}

		return questions;
	}

	@RequestMapping(value = "/addQuestionToSectionAjaxPro", method = RequestMethod.GET)
	public @ResponseBody String addQuestionToSectionAjaxPro(@RequestParam String sectionName,
			@RequestParam String questionId, HttpServletRequest request, HttpServletResponse response) {

		User user = (User) request.getSession().getAttribute("user");

		SectionDto sectionDto = (SectionDto) request.getSession().getAttribute("sectionDTO");
		Question q = questionService.findById(Long.valueOf(questionId));
		sectionDto.getQuestions().add(q);
		// List<Question> qs = questionService.findQuestions(user.getCompanyId());
		List<Question> questions = questionService.getAllLevel1Questions(user.getCompanyId());

		process(questions, sectionDto);

		return "" + sectionDto.getQuestions().size();
	}

	@RequestMapping(value = "/removeQuestionToSectionAjaxPro", method = RequestMethod.GET)
	public @ResponseBody String removeQuestionToSectionAjaxPro(@RequestParam String sectionName,
			@RequestParam String questionId, HttpServletRequest request, HttpServletResponse response) {
		User user = (User) request.getSession().getAttribute("user");
		SectionDto sectionDto = (SectionDto) request.getSession().getAttribute("sectionDTO");
		Question delete = new Question();
		delete.setId(Long.valueOf(questionId));
		sectionDto.getQuestions().remove(delete);
		// List<Question> qs = questionService.findQuestions(user.getCompanyId());
		List<Question> questions = questionService.getAllLevel1Questions(user.getCompanyId());
		process(questions, sectionDto);
		return "" + sectionDto.getQuestions().size();
	}

	@RequestMapping(value = "/saveSectionPro", method = RequestMethod.GET)
	@Transactional
	public ModelAndView saveSectionPro(@RequestParam String sectionTopic, @RequestParam String percentage,
			HttpServletRequest request, HttpServletResponse response, @ModelAttribute("test") Test test) {
//		 	ModelAndView mav = new ModelAndView("add_test_step2");
		ModelAndView mav = new ModelAndView("create_test_pro_step2");
		User user = (User) request.getSession().getAttribute("user");
		mav.addObject("user", user);

		test = (Test) request.getSession().getAttribute("test");

		if (!(user.getUserType().getType().equals(UserType.ADMIN.getType())
				|| user.getUserType().getType().equals(UserType.SUPER_ADMIN.getType())
				|| user.getUserType().getType().equals(UserType.LMS_ADMIN.getType()))) {
			request.getSession().invalidate();
			mav = new ModelAndView("index");
			user = new User();
			user.setEmail("system@iEassess.com");
			user.setPassword("1234");
			user.setCompanyName("Eassess");
			mav.addObject("user", user);
			return mav;
		}

		SectionDto sectionDto = (SectionDto) request.getSession().getAttribute("sectionDTO");
		String oldSectionName = sectionDto.getSectionName();
		for (SectionDto dto : test.getSectionDtos()) {
			if (dto.getSectionName().equals(sectionDto.getSectionName())) {
				dto.setSectionName(sectionTopic);
				dto.setPercentQuestionsAsked(Integer.parseInt(percentage));
			}
		}

		sectionDto.setSectionName(sectionTopic);
		sectionDto.setPercentQuestionsAsked(Integer.parseInt(percentage));
		Section section = null;
		// List<Question> qs = questionService.findQuestions(user.getCompanyId());
		List<Question> qs = questionService.getAllLevel1Questions(user.getCompanyId());
		/**
		 * Allow qs add to test after sesssions taken
		 */
//			boolean edit = questionMapperInstanceService.canEditTest(sectionTopic, test.getTestName(), user.getCompanyId());
//				if(!edit) {
//					mav.addObject("sectionDto", sectionDto);
//					mav.addObject("qs", process(qs,  sectionDto));
//			  		mav.addObject("test", test);
//			  		mav.addObject("message", "Users have started taking this test. You can't edit the test now!" );// later put it as label
//					mav.addObject("msgtype", "Information");
//					return mav;
//				}
		/**
		 * End Allow qs add to test after sesssions taken
		 */

		if (sectionDto.getSectionId() == null) {
			// create section first
			// Section existing = sectionService.findByPrimaryKey(test.getTestName(),
			// sectionDto.getSectionName(), user.getCompanyId());
			// if(existing != null) {
			if (checkMultipleSectionWithSameNames(sectionDto.getSectionName(), request)) {
				mav.addObject("sectionDto", sectionDto);
				mav.addObject("qs", process(qs, sectionDto));
				mav.addObject("test", test);
				mav.addObject("message", "Section - " + sectionDto.getSectionName()
						+ " already exists for the given Test. Use a different name");// later put it as label
				mav.addObject("msgtype", "Information");
				return mav;
			}
			section = new Section();
			section.setCompanyId(user.getCompanyId());
			section.setCompanyName(user.getCompanyName());
			section.setTestName(test.getTestName());
			section.setSectionName(sectionTopic);
			section.setPercentQuestionsAsked(sectionDto.getPercentQuestionsAsked());
			sectionService.createSection(section);
			sectionDto.setSectionId(section.getId());
		} else {
			// Section existing = sectionService.findByPrimaryKey(test.getTestName(),
			// sectionDto.getSectionName(), user.getCompanyId());
			// if(existing != null) {
			if (checkMultipleSectionWithSameNames(sectionDto.getSectionName(), request)) {
				mav.addObject("sectionDto", sectionDto);
				mav.addObject("qs", process(qs, sectionDto));
				mav.addObject("test", test);
				mav.addObject("message", "Section - " + sectionDto.getSectionName()
						+ " already exists for the given Test. Use a different name");// later put it as label
				mav.addObject("msgtype", "Information");
				return mav;
			}

			section = sectionService.getSectionById(sectionDto.getSectionId());
		}

		// oldSectionName
		// sectionService.removeQuestionsFromSection(section.getSectionName(),
		// section.getTestName(), user.getCompanyId());
		/**
		 * To make sure we dis-associate old questions (for tests where sessions have
		 * been taken)
		 */
		boolean edit = questionMapperInstanceService.canEditTest(sectionTopic, test.getTestName(), user.getCompanyId());
		if (edit) {
			// no user sessions has been taken
			sectionService.removeQuestionsFromSection(oldSectionName, section.getTestName(), user.getCompanyId());
		} else {
			sectionService.disassociateQuestionsFromSection(oldSectionName, section.getTestName(), user.getCompanyId());
		}

		/**
		 * V. Imp
		 */
		section.setSectionName(sectionTopic);
		section.setPercentQuestionsAsked(sectionDto.getPercentQuestionsAsked());
		sectionService.changeSectionNameAndPercent(section, sectionTopic, sectionDto.getPercentQuestionsAsked(),
				sectionDto.getQuestions().size());
		Set<Question> questions = sectionDto.getQuestions();
		for (Question question : questions) {

			sectionService.addQuestionToSection(question, section, 1);
		}
		Integer totMarks = testService.computeTestTotalMarksAndSave(test);
		test.setTotalMarks(totMarks);
		request.getSession().setAttribute("test", test);
		sectionDto.setNoOfQuestions(sectionDto.getQuestions().size());
		mav.addObject("sectionDto", sectionDto);
		mav.addObject("qs", process(qs, sectionDto));
		mav.addObject("test", test);
		mav.addObject("message", "Section - " + sectionDto.getSectionName()
				+ " has been renamed and the entire section has been saved successfully.");// later put it as label
		mav.addObject("msgtype", "Information");
		return mav;

	}

	private boolean checkMultipleSectionWithSameNames(String sectionName, HttpServletRequest request) {
		Test test = (Test) request.getSession().getAttribute("test");
		int count = 0;
		for (SectionDto dto : test.getSectionDtos()) {
			if (dto.getSectionName().equals(sectionName)) {
				count++;
			}
		}

		if (count > 1) {
			return true;
		}
		return false;
	}

	@RequestMapping(value = "/showSectionsQuestionsPro", method = RequestMethod.GET)
	public ModelAndView showSectionsQuestionsPro(HttpServletRequest request, HttpServletResponse response,
			@ModelAttribute("test") Test test) {
		ModelAndView mav = new ModelAndView("create_test_pro_step2");
		User user = (User) request.getSession().getAttribute("user");
		mav.addObject("user", user);
		SectionDto sectionDto = (SectionDto) request.getSession().getAttribute("sectionDTO");

		Set<Question> questions = sectionDto.getQuestions();
		for (Question q : questions) {
			q.setSelected(true);
		}
		mav.addObject("qs", questions);

		mav.addObject("levels", DifficultyLevel.values());
		mav.addObject("types", QuestionType.values());
		mav.addObject("languages", ProgrammingLanguage.values());
		mav.addObject("sectionDto", sectionDto);
		Test test2 = (Test) request.getSession().getAttribute("test");
		mav.addObject("test", test2);
		return mav;
	}

	@RequestMapping(value = "/addteststep2Pro", method = RequestMethod.GET)
	public ModelAndView addteststep2Pro(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = null;
		mav = new ModelAndView("create_test_pro_step2");
		User user = (User) request.getSession().getAttribute("user");
		// List<Question> qs = questionService.findQuestions(user.getCompanyId());
		List<Question> qs = questionService.getAllLevel1Questions(user.getCompanyId());
		SectionDto sectionDto = (SectionDto) request.getSession().getAttribute("sectionDTO");
		mav.addObject("sectionDto", sectionDto);
		if (sectionDto != null) {
			mav.addObject("qs", process(qs, sectionDto));
		} else {
			mav.addObject("qs", qs);
		}

		mav.addObject("levels", DifficultyLevel.values());
		mav.addObject("types", QuestionType.values());
		mav.addObject("languages", ProgrammingLanguage.values());
		Test test = (Test) request.getSession().getAttribute("test");
		mav.addObject("test", test);
		return mav;

	}

	@RequestMapping(value = "/removeAllQuestionsPro", method = RequestMethod.GET)
	public ModelAndView removeAllQuestionsPro(HttpServletRequest request, HttpServletResponse response,
			@ModelAttribute("test") Test test) {
		ModelAndView mav = new ModelAndView("create_test_pro_step2");
		User user = (User) request.getSession().getAttribute("user");
		mav.addObject("user", user);
		SectionDto sectionDto = (SectionDto) request.getSession().getAttribute("sectionDTO");
		sectionDto.getQuestions().clear();
		// List<Question> qs = questionService.findQuestions(user.getCompanyId());
		List<Question> questions = questionService.getAllLevel1Questions(user.getCompanyId());

		mav.addObject("levels", DifficultyLevel.values());
		mav.addObject("types", QuestionType.values());
		mav.addObject("languages", ProgrammingLanguage.values());
		mav.addObject("sectionDto", sectionDto);
		Test test2 = (Test) request.getSession().getAttribute("test");
		mav.addObject("test", test2);
		return mav;
	}

	@RequestMapping(value = "/addNewSectionPro", method = RequestMethod.GET)
	public ModelAndView addNewSectionPro(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView("create_test_pro_step2");
		User user = (User) request.getSession().getAttribute("user");
		mav.addObject("user", user);
		Test test = (Test) request.getSession().getAttribute("test");
		List<SectionDto> sectionDtos = test.getSectionDtos();
		for (SectionDto dto : sectionDtos) {
			dto.setCurrent(false);
		}
		SectionDto dto = new SectionDto();
		dto.setCompanyId(user.getCompanyId());
		dto.setTestName(test.getTestName());
		dto.setSectionNo(sectionDtos.size() + 1);
		dto.setSectionName("Section " + (sectionDtos.size() + 1));
		dto.setCurrent(true);
		dto.setNoOfQuestions(0);
		sectionDtos.add(dto);
		request.getSession().setAttribute("sectionDTO", dto);
		mav.addObject("test", test);
		mav.addObject("sectionDto", dto);
		// List<Question> qs = questionService.findQuestions(user.getCompanyId());
		List<Question> qs = questionService.getAllLevel1Questions(user.getCompanyId());
		mav.addObject("qs", qs);
		return mav;
	}

	@RequestMapping(value = "/goToSectionPro", method = RequestMethod.GET)
	public ModelAndView goToSectionPro(@RequestParam String sectionName, HttpServletRequest request,
			HttpServletResponse response) {
		ModelAndView mav = new ModelAndView("create_test_pro_step2");
		User user = (User) request.getSession().getAttribute("user");
		mav.addObject("user", user);
		Test test = (Test) request.getSession().getAttribute("test");
		List<SectionDto> sectionDtos = test.getSectionDtos();
		for (SectionDto dto : sectionDtos) {
			dto.setCurrent(false);
			if (dto.getSectionName().equals(sectionName)) {
				dto.setCurrent(true);
				request.getSession().setAttribute("sectionDTO", dto);
				mav.addObject("sectionDto", dto);
				// List<Question> qs = questionService.findQuestions(user.getCompanyId());
				List<Question> questions = questionService.getAllLevel1Questions(user.getCompanyId());
				mav.addObject("qs", process(questions, dto));
				mav.addObject("test", test);
			}
		}

		return mav;
	}

	@RequestMapping(value = "/removeSectionPro", method = RequestMethod.GET)
	public ModelAndView removeSectionPro(@RequestParam String sectionName, HttpServletRequest request,
			HttpServletResponse response) {
		ModelAndView mav = new ModelAndView("create_test_pro_step2");
		User user = (User) request.getSession().getAttribute("user");
		mav.addObject("user", user);
		Test test = (Test) request.getSession().getAttribute("test");
		List<SectionDto> sectionDtos = test.getSectionDtos();
		if (sectionDtos.size() == 1) {
			mav.addObject("sectionDto", sectionDtos.get(0));
			SectionDto dto = (SectionDto) request.getSession().getAttribute("sectionDto");
			mav.addObject("test", test);
			mav.addObject("sectionDto", dto);
			mav.addObject("message", "You can not have a Test with no sections. This section can not be deleted ");// later
																													// put
																													// it
																													// as
																													// label
			mav.addObject("msgtype", "Information");
			return mav;
		}

		for (SectionDto dto : sectionDtos) {
			dto.setCurrent(false);
			if (dto.getSectionName().equals(sectionName)) {

				if (dto.getSectionId() != null) {
					Section section = sectionService.getSectionById(dto.getSectionId());
					sectionService.removeSection(section);
				}
				mav.addObject("message", "Section - " + dto.getSectionName() + " deleted");// later put it as label
				mav.addObject("msgtype", "Information");
			}
		}

		boolean res = sectionDtos.remove(new SectionDto(sectionName));
		int count = 0;
		for (SectionDto dto : sectionDtos) {
			if (count == 0) {
				dto.setCurrent(true);
				mav.addObject("sectionDto", dto);
				request.getSession().setAttribute("sectionDTO", dto);
				// List<Question> qs = questionService.findQuestions(user.getCompanyId());
				List<Question> questions = questionService.getAllLevel1Questions(user.getCompanyId());
				mav.addObject("qs", process(questions, dto));

			}
			count++;

		}

		mav.addObject("test", test);
		return mav;
	}

	@RequestMapping(value = "/gobackStep1TestPro", method = RequestMethod.GET)
	public ModelAndView gobackStep1TestPro(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView("create_test_pro_step1");
		User user = (User) request.getSession().getAttribute("user");
		Test test = (Test) request.getSession().getAttribute("test");
		mav.addObject("user", user);
		mav.addObject("test", test);
		List<Skill> skills = skillService.getSkillsByCompanyId(user.getCompanyId());
		mav.addObject("skls", skills);
		String testTypes[] = { "Java", "Microsoft technologies", "C/C++", "Python", "General Knowledge",
				"Composite Test" };
		mav.addObject("testTypes", testTypes);
		List<String> levels = Stream.of(DifficultyLevel.values()).map(DifficultyLevel::name)
				.collect(Collectors.toList());
		mav.addObject("levels", levels);
		return mav;
	}

	@RequestMapping(value = "/addteststep3Pro", method = RequestMethod.GET)
	public ModelAndView addteststep3Pro(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = null;
		mav = new ModelAndView("create_test_pro_step3");
		User user = (User) request.getSession().getAttribute("user");
		// mav.addObject("qs", questions);
		Test test = (Test) request.getSession().getAttribute("test");
		mav.addObject("test", test);
		List<User> users = userService.findByCompany(user.getCompanyId());
		mav.addObject("users", process(users, test));
		return mav;
	}

	private List<User> process(List<User> users, Test test) {
		for (User user : users) {
			if (test.getUsers().contains(user)) {
				user.setSelected(true);
			}
		}

		return users;
	}

	/////////////////////////

	@RequestMapping(value = "/showUsersPro", method = RequestMethod.GET)
	public ModelAndView showUsersPro(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView("create_test_pro_step3");
		User user = (User) request.getSession().getAttribute("user");
		Test test = (Test) request.getSession().getAttribute("test");
		List<User> users = userService.findByCompany(user.getCompanyId());
		mav.addObject("users", process(users, test));
		return mav;
	}

	@RequestMapping(value = "/showSelectedUsersPro", method = RequestMethod.GET)
	public ModelAndView showSelectedUsersPro(HttpServletRequest request, HttpServletResponse response) {
		String referer = request.getHeader("Referer");
		ModelAndView mav = new ModelAndView("create_test_pro_step3");
		User user = (User) request.getSession().getAttribute("user");
		Test test = (Test) request.getSession().getAttribute("test");
		mav.addObject("users", test.getUsers());
		return mav;
	}

	@RequestMapping(value = "/addUserToTestPro", method = RequestMethod.GET)
	public ModelAndView addUserToTestPro(@RequestParam String userId, HttpServletRequest request,
			HttpServletResponse response) {
		ModelAndView mav = new ModelAndView("create_test_pro_step3");
		User user = (User) request.getSession().getAttribute("user");
		mav.addObject("user", user);
		Test test = (Test) request.getSession().getAttribute("test");
		User u = userService.findById(Long.valueOf(userId));
		u.setSelected(true);
		test.getUsers().add(u);
		List<User> users = userService.findByCompany(user.getCompanyId());
		mav.addObject("users", process(users, test));
		return mav;
	}

	@RequestMapping(value = "/sendEmailToAllPro", method = RequestMethod.GET)
	public @ResponseBody String sendEmailToAll(HttpServletRequest request, HttpServletResponse response) {
		User user = (User) request.getSession().getAttribute("user");
		Test test = (Test) request.getSession().getAttribute("test");
		List<User> users = userService.findByCompany(user.getCompanyId());
		// for(User u : user)
		return "done";
	}

	@RequestMapping(value = "/removeAllUsersPro", method = RequestMethod.GET)
	public ModelAndView removeAllUsersPro(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView("create_test_pro_step3");
		User user = (User) request.getSession().getAttribute("user");
		mav.addObject("user", user);
		Test test = (Test) request.getSession().getAttribute("test");
		test.getUsers().clear();
		List<User> users = userService.findByCompany(user.getCompanyId());
		mav.addObject("users", process(users, test));
		return mav;

	}

	@RequestMapping(value = "/removeUserToTestPro", method = RequestMethod.GET)
	public ModelAndView removeUserToTestPro(@RequestParam String userId, HttpServletRequest request,
			HttpServletResponse response) {
		ModelAndView mav = new ModelAndView("create_test_pro_step3");
		User user = (User) request.getSession().getAttribute("user");
		mav.addObject("user", user);
		Test test = (Test) request.getSession().getAttribute("test");
		User delete = new User();
		delete.setId(Long.valueOf(userId));
		test.getUsers().remove(delete);
		List<User> users = userService.findByCompany(user.getCompanyId());
		mav.addObject("users", process(users, test));
		return mav;
	}

	@RequestMapping(value = "/searchUsersPro", method = RequestMethod.GET)
	public ModelAndView searchUsersPro(@RequestParam String searchText, HttpServletRequest request,
			HttpServletResponse response) {
		ModelAndView mav = new ModelAndView("create_test_pro_step3");
		User user = (User) request.getSession().getAttribute("user");
		mav.addObject("user", user);
		Test test = (Test) request.getSession().getAttribute("test");

		List<User> users = userService.searchUsers(user.getCompanyId(), searchText);
		mav.addObject("users", process(users, test));
		return mav;
	}

	/////////

	@RequestMapping(value = "/addteststep4Pro", method = RequestMethod.GET)
	public ModelAndView addteststep4Pro(HttpServletRequest request, HttpServletResponse response, String message) {
		ModelAndView mav = null;
		mav = new ModelAndView("create_test_pro_step4");
		User user = (User) request.getSession().getAttribute("user");
		// mav.addObject("qs", questions);
		Test test2 = (Test) request.getSession().getAttribute("test");
		Set<User> selectedusers = test2.getUsers();
		SectionDto sectionDto = (SectionDto) request.getSession().getAttribute("sectionDTO");
		// Set<Question> questions = sectionDto.getQuestions();

		mav.addObject("test", test2);
		mav.addObject("selectedusers", selectedusers);
		if (message != null) {
			mav.addObject("message", "Invalid Start or End Date selected for the Test Link");// later put it as label
			mav.addObject("msgtype", "Wrong Data");
			mav.addObject("icon", "error");
		}
		// mav.addObject("selectedQuestions", questions);
		return mav;
	}

	@RequestMapping(value = "/shareTestWithUsersPro", method = RequestMethod.GET)
	public ModelAndView shareTestWithUsersPro(@RequestParam String startDate, @RequestParam String endDate,
			HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView("pro_tests");
		User user = (User) request.getSession().getAttribute("user");

		Test test = (Test) request.getSession().getAttribute("test");
//		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy hh:mm aa");
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
		String sDate;
		String eDate;
		try {
			Date d1 = dateFormat.parse(startDate);
			Date d2 = dateFormat.parse(endDate);
			sDate = Base64.getEncoder().encodeToString(("" + d1.getTime()).getBytes());
			eDate = Base64.getEncoder().encodeToString(("" + d2.getTime()).getBytes());
			sDate = URLEncoder.encode(sDate);
			eDate = URLEncoder.encode(eDate);
		} catch (Exception e) {
			return addteststep4Pro(request, response, "Please enter valid Start and End Dates");
		}

		for (User u : test.getUsers()) {
			shareTest(u.getEmail(), test.getId(), user.getCompanyId(), u.getFirstName(), u.getLastName(),
					test.getTestName(), sDate, eDate);

		}
		mav.addObject("message", "Congratulations! - Email with Test Links shared with users. ");// later put it as
																									// label
		mav.addObject("msgtype", "Success");
		mav.addObject("icon", "success");
		Page<Test> tests = testService.findByCompanyId(user.getCompanyId(), 0);
		mav.addObject("tests", testService.populateWithPublicUrl(tests.getContent()));
		// mav.addObject("tests", tests);
		return mav;
	}

	private void shareTest(String email, Long testId, String cid, String firstName, String lastName, String testName,
			String startDate, String endDate) {
		logger.info("sharing test " + testName + " with " + email);
		User user = userService.findByPrimaryKey(email, cid);
		if (user == null) {
			User us = new User();
			us.setFirstName(firstName);
			us.setLastName(lastName);
			us.setEmail(email);
			us.setCompanyId(cid);
			Company comp = companyService.findByCompanyId(cid);
			us.setCompanyName(comp.getCompanyName());
			us.setUserType(UserType.STUDENT);
			us.setInternalUser(false);
			us.setPassword("temp123");
			userService.addUser(us);
		}
		String url = getUrlForUser(email, testId, cid);
		url += "&inviteSent=" + System.currentTimeMillis() + "&startDate=" + startDate + "&endDate=" + endDate;
		String welcomeMailData;
		try {
			String html = propertyConfig.getTestLinkHtmlLocation();
			welcomeMailData = FileUtils.readFileToString(new File(html));
			welcomeMailData = welcomeMailData.replace("{FULL_NAME}", firstName + " " + lastName);
			welcomeMailData = welcomeMailData.replace("{TEST_NAME}", testName);
			welcomeMailData = welcomeMailData.replace("{URL}", url);
			EmailGenericMessageThread client = new EmailGenericMessageThread(email,
					"Test Link - " + testName + " Sent by Eassess", welcomeMailData, propertyConfig);
			// client.setEmailSentCC(emailSentCC);
			// client.setSetStatus(true);
			Thread th = new Thread(client);
			th.start();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			String message = "Test link mail could not be sent for " + email;
			EmailGenericMessageThread client = new EmailGenericMessageThread("jatin.sutaria@thev2technologies.com",
					"Can not send Test link email", message, propertyConfig);
			// client.setSetStatus(true);
			Thread th = new Thread(client);
			th.start();
		}
	}

	private String getUrlForUser(String user, Long testId, String companyId) {
		String userBytes = Base64.getEncoder().encodeToString(user.getBytes());

		String after = "userId=" + URLEncoder.encode(userBytes) + "&testId=" + URLEncoder.encode(testId.toString())
				+ "&companyId=" + URLEncoder.encode(companyId);
		String url = propertyConfig.getBaseUrl() + "mcq2?" + after;
		return url;
	}
}
