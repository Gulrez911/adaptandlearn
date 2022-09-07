package com.assessment.web.controllers;

import java.io.File;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.exolab.castor.xml.Unmarshaller;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.assessment.Exceptions.AssessmentGenericException;
import com.assessment.common.CommonUtil;
import com.assessment.common.PropertyConfig;
import com.assessment.data.CandidateProfileParams;
import com.assessment.data.DifficultyLevel;
import com.assessment.data.FullStackOptions;
import com.assessment.data.ProgrammingLanguage;
import com.assessment.data.Question;
import com.assessment.data.QuestionType;
import com.assessment.data.TestCase;
import com.assessment.data.TestCases;
import com.assessment.data.User;
import com.assessment.data.UserType;
import com.assessment.repositories.CandidateProfileParamsRepository;
import com.assessment.services.CandidateProfileParamsService;
import com.assessment.services.CompanyService;
import com.assessment.services.QuestionService;
import com.assessment.services.UserService;

@Controller
public class QuestionProController {

	@Autowired
	private UserService userService;
	@Autowired
	private QuestionService questionService;
	@Autowired
	private CompanyService companyService;

	@Autowired
	PropertyConfig propertyConfig;

	@Autowired
	CandidateProfileParamsService profileService;
	@Autowired
	CandidateProfileParamsRepository profileRepository;

	Logger logger = LoggerFactory.getLogger(QuestionProController.class);

//	@RequestMapping(value = "/proquestionbank", method = RequestMethod.GET)
//	public ModelAndView proquestionbank(HttpServletRequest request, HttpServletResponse response) {
//		ModelAndView mav = new ModelAndView("pro_question-bank");
//		return mav;
//	}

	@RequestMapping(value = "/proquestionbank", method = RequestMethod.GET)
	public ModelAndView proquestionbank(@RequestParam(name = "page", required = false) Integer pageNumber,@RequestParam(name = "qualifier", required = false) String qualifier,
			HttpServletResponse response, HttpServletRequest request, ModelMap modelMap) throws Exception {
//		ModelAndView mav = new ModelAndView("question_list");
		ModelAndView mav = new ModelAndView("pro_question-bank");
		User user = (User) request.getSession().getAttribute("user");
		if (pageNumber == null) {
			pageNumber = 0;
		}
//		Page<Question> questions = questionService.findQuestionsByPage(user.getCompanyId(), pageNumber);

		List<String> listQualifier = questionService.uquiqeQualifier();
		mav.addObject("listQualifier", listQualifier);
		List<String> list = new ArrayList<String>();
		if(qualifier!=null) {
			list.add(qualifier);
		}else {
			for(String qq:listQualifier) {
				list.add(qq);
				break;
			}
		}
		

		Page<Question> listQuestions = questionService.searchQuestionsByQualifier(user.getCompanyId(), list, pageNumber);
		System.out.println(".... "+listQuestions.getContent().size());
		
		mav.addObject("listQuestions", listQuestions.getContent());
		mav.addObject("list22", list);
		mav.addObject("qualifier", qualifier);
		
//		Page<Question> questions = questionService.getRandomQuestionsForQualifiers1AndDifficultyLevel(user.getCompanyId(), pageNumber);
//		mav.addObject("qs", questions.getContent());
		
		mav.addObject("levels", DifficultyLevel.values());
		mav.addObject("types", QuestionType.values());
		mav.addObject("languages", ProgrammingLanguage.values());
		CommonUtil.setCommonAttributesOfPagination(listQuestions, modelMap, pageNumber, "proquestionbank", null);
		return mav;
	}

	@RequestMapping(value = "/creatQuestion", method = RequestMethod.GET)
	public ModelAndView creatQuestion(@RequestParam(name = "page", required = false) Integer pageNumber,
			@RequestParam(name = "qid", required = false) Long qid, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
//		ModelAndView mav = new ModelAndView("add_question");
		ModelAndView mav = new ModelAndView("create_question_pro");

		User user = (User) request.getSession().getAttribute("user");
		if (pageNumber == null) {
			pageNumber = 0;
		}
		if (qid == null) {
			Question question = new Question();
			mav.addObject("question", question);
			mav.addObject("question_label", "Add new Question");
		} else {
			Question question = questionService.findById(qid);
			question.setType(question.getQuestionType() != null ? question.getQuestionType().getType()
					: QuestionType.MCQ.getType());
			question.setLang(question.getLanguage() != null ? question.getLanguage().getLanguage()
					: ProgrammingLanguage.JAVA.getLanguage());

			question.setUpFromInUpdateMode();
			mav.addObject("question", question);
			mav.addObject("editQMode", "true");
			mav.addObject("question_label", "Update this Question");
		}

		// Page<Question> questions =
		// questionService.findQuestionsByPage(user.getCompanyId(), pageNumber);
		Page<Question> questions = questionService.getAllLevel1Questions(user.getCompanyId(), pageNumber);
		mav.addObject("qs", questions.getContent());
		mav.addObject("levels", DifficultyLevel.values());
		mav.addObject("types", QuestionType.values());
		mav.addObject("languages", ProgrammingLanguage.values());
		mav.addObject("stacks", FullStackOptions.values());
		CommonUtil.setCommonAttributesOfPagination(questions, mav.getModelMap(), pageNumber, "addQuestion", null);
		return mav;
	}

	@RequestMapping(value = "/saveQuestionPro", method = RequestMethod.POST)
	public ModelAndView saveQuestionPro(
			@RequestParam(name = "addtestcases", required = false) MultipartFile addtestcases,
			@RequestParam(name = "addimage", required = false) MultipartFile addimage,
			@RequestParam(name = "addaudio", required = false) MultipartFile addaudio,
			@RequestParam(name = "addvideo", required = false) MultipartFile addvideo, HttpServletRequest request,
			HttpServletResponse response, @ModelAttribute("question") Question question) throws Exception {
		logger.info("in save Q q.getconstr " + question.getConstrnt());
		ModelAndView mav = null;
		User user = (User) request.getSession().getAttribute("user");
		List<Question> questions = new ArrayList<Question>();

		if (addtestcases != null && addtestcases.getSize() != 0) {
			String error = "";
			try {
				System.out.println("1.......");
				logger.info("1.......");
				TestCases testCases = (TestCases) Unmarshaller.unmarshal(TestCases.class,
						new InputStreamReader(addtestcases.getInputStream()));
//				JAXBContext jaxbContext     = JAXBContext.newInstance( TestCases.class );
//				javax.xml.bind.Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
//				TestCases testCases = (TestCases)jaxbUnmarshaller.unmarshal(new InputStreamReader(addtestcases.getInputStream()));
				List<TestCase> cases = testCases.getCases();
				System.out.println("2.......");
				logger.info("2.......");
				for (TestCase case1 : cases) {
					System.out.println("2.1.......");
					logger.info("2.1.......");
					if (case1.getTestCaseType() == null || case1.getTestCaseType().trim().length() == 0) {
						error = "Test Cases Xml Upload - No Test Case Type Specified for test case " + case1.getName();
						break;
					} else if ((!case1.getTestCaseType().equalsIgnoreCase("functional"))
							&& (!case1.getTestCaseType().equalsIgnoreCase("boundary"))
							&& (!case1.getTestCaseType().equalsIgnoreCase("exception"))) {
						error = "Test Cases Xml Upload - Invalid case type specified for " + case1.getName();
						break;
					}

					if (case1.getExpectedOuput() == null || case1.getExpectedOuput().trim().length() == 0) {
						error = "Test Cases Xml Upload - Invalid expected output specified for " + case1.getName();
						break;
					}

					if (case1.getName() == null || case1.getName().trim().length() == 0) {
						error = "Test Cases Xml Upload - Name not present";
						break;
					}

					if (case1.getWeight() == null) {
						error = "Test Cases Xml Upload - Invalid Weight specified for " + case1.getName();
						break;
					}

					if (case1.getMandatory() == null) {
						error = "Test Cases Xml Upload - Invalid Mandatory specified for " + case1.getName();
						break;
					}

					if (case1.getDesc() == null || case1.getDesc().trim().length() == 0) {
						error = "Test Cases Xml Upload - Invalid Desc specified for " + case1.getName();
						break;
					}
					System.out.println("2.1.......end");
					logger.info("2.1.......end");
				}
				System.out.println("3.......");
				logger.info("3.......");
				String testcasesxml = new String(addtestcases.getBytes(), "UTF-8");
				logger.info("3.......testcases xml");
				System.out.println("3.......testcases xml");
				question.setTestcasesXml(testcasesxml);
				logger.info("3.......testcases xml setting done error " + error);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				// e.printStackTrace();
				logger.error("upload test acses prob", e);
				System.out.println("4.......");
				error = "Test Cases Xml Upload - Invalid Test Cases file " + e.getMessage();
			}

			logger.info("in error loop");
			if (error.trim().length() != 0) {
				// questions = questionService.findQuestions(user.getCompanyId());
				logger.info("in error loop 1");
				Page<Question> qs = questionService.getAllLevel1Questions(user.getCompanyId(), 0);
				logger.info("in error loop 2");
				mav = new ModelAndView("add_question");
				mav.addObject("question", question);
				mav.addObject("message", error);// later put it as label
				mav.addObject("msgtype", "failure");
				mav.addObject("types", QuestionType.values());
				mav.addObject("qs", qs.getContent());
				mav.addObject("levels", DifficultyLevel.values());
				mav.addObject("stacks", FullStackOptions.values());
				logger.info("in error loop ret");
				return mav;
			}
		}

		if (addimage != null && addimage.getSize() != 0) {
			String destination = propertyConfig.getFileServerLocation() + File.separator + "images" + File.separator
					+ addimage.getOriginalFilename();
			File file = new File(destination);
			if (file.exists()) {
				if (addimage.getOriginalFilename() != null && addimage.getOriginalFilename().trim().length() > 0) {
					FileUtils.forceDelete(file);
				}

			}
			if (addimage.getOriginalFilename() != null && addimage.getOriginalFilename().trim().length() > 0) {
				String imageUrl = propertyConfig.getFileServerWebUrl() + "images/" + addimage.getOriginalFilename();
				question.setImageUrl(imageUrl);
				addimage.transferTo(file);
			}

		}
		logger.info("addimage check gone");
		if (addaudio != null && addaudio.getSize() != 0) {
			String destination = propertyConfig.getFileServerLocation() + File.separator + "audios" + File.separator
					+ addaudio.getOriginalFilename();
			File file = new File(destination);
			if (file.exists()) {
				if (addaudio.getOriginalFilename() != null && addaudio.getOriginalFilename().trim().length() > 0) {
					FileUtils.forceDelete(file);
				}

			}

			if (addaudio.getOriginalFilename() != null && addaudio.getOriginalFilename().trim().length() > 0) {
				addaudio.transferTo(file);
				String audioUrl = propertyConfig.getFileServerWebUrl() + "audios/" + addaudio.getOriginalFilename();
				question.setAudioURL(audioUrl);
			}

		}
		logger.info("addaudio check gone");
		if (addvideo != null && addvideo.getSize() != 0) {
			String destination = propertyConfig.getFileServerLocation() + File.separator + "videos" + File.separator
					+ addvideo.getOriginalFilename();
			File file = new File(destination);
			if (file.exists()) {
				if (addvideo.getOriginalFilename() != null && addvideo.getOriginalFilename().trim().length() > 0) {
					FileUtils.forceDelete(file);
				}
			}

			if (addvideo.getOriginalFilename() != null && addvideo.getOriginalFilename().trim().length() > 0) {
				addvideo.transferTo(file);
				String videoUrl = propertyConfig.getFileServerWebUrl() + "videos/" + addvideo.getOriginalFilename();
				question.setVideoURL(videoUrl);
			}

		}

		try {
			logger.info("before setup");
			question.setup();
			logger.info("after setup");
		} catch (AssessmentGenericException e) {
			// TODO Auto-generated catch block
			// questions = questionService.findQuestions(user.getCompanyId());
			Page<Question> qs = questionService.getAllLevel1Questions(user.getCompanyId(), 0);
			mav = new ModelAndView("create_question_pro");
			mav.addObject("question", question);
			mav.addObject("message", "Select atleast 1 Correct answer");// later put it as label
			mav.addObject("msgtype", "failure");
			mav.addObject("icon", "error");
			mav.addObject("types", QuestionType.values());
			mav.addObject("qs", qs.getContent());
			mav.addObject("levels", DifficultyLevel.values());
			mav.addObject("stacks", FullStackOptions.values());
			return mav;
		}
		logger.info("after setup further");
		question.setCompanyId(user.getCompanyId());
		question.setCompanyName(user.getCompanyName());
//			if((!question.getInputCode().contains("<br />")) && question.getInputCode() != null){
//				question.setInputCode(question.getInputCode().replaceAll("\\r\\n|\\r|\\n", "<br />"));
//			}

//			if( (!question.getInstructionsIfAny().contains("<br />")) && question.getInstructionsIfAny() != null){
//				question.setInstructionsIfAny(question.getInstructionsIfAny().replaceAll("\n", "<br />"));
//			}

		if (question.getQuestionType().getType().equals(QuestionType.CODING.getType())
				|| question.getQuestionType().getType().equals(QuestionType.MCQ.getType())) {
			question.setFullstack(FullStackOptions.NONE);
		} else if (question.getQuestionType().getType().equals(QuestionType.FILL_BLANKS_MCQ.getType())
				|| question.getQuestionType().getType().equals(QuestionType.MATCH_FOLLOWING_MCQ.getType())) {
			question.setFullstack(FullStackOptions.NONE);
		} else if (question.getQuestionType().getType().equals(QuestionType.IMAGE_UPLOAD_BY_USER.getType())
				|| question.getQuestionType().getType().equals(QuestionType.VIDEO_UPLOAD_BY_USER.getType())
				|| question.getQuestionType().getType().equals(QuestionType.SUBJECTIVE_TEXT.getType())) {
			question.setFullstack(FullStackOptions.NONE);
		} else {
			String reviewer = question.getReviewerEmail();
			logger.info("review check");
			if (reviewer == null || reviewer.trim().length() == 0) {
				// questions = questionService.findQuestions(user.getCompanyId());
				logger.info("review check 1");
				Page<Question> qs = questionService.getAllLevel1Questions(user.getCompanyId(), 0);
				logger.info("review check 2");
				mav = new ModelAndView("create_question_pro");
				mav.addObject("question", question);
				mav.addObject("message", "Select a Reviewer for the Full Stack Problem statement");// later put it as
																									// label
				mav.addObject("msgtype", "failure");
				mav.addObject("icon", "error");
				mav.addObject("types", QuestionType.values());
				mav.addObject("qs", qs.getContent());
				mav.addObject("levels", DifficultyLevel.values());
				mav.addObject("stacks", FullStackOptions.values());
				return mav;
			}
			User usr = userService.findByPrimaryKey(reviewer, user.getCompanyId());
			if (usr != null && (!usr.getUserType().getType().equals(UserType.REVIEWER.getType()))) {
				// questions = questionService.findQuestions(user.getCompanyId());
				logger.info("review check 11");
				Page<Question> qs = questionService.getAllLevel1Questions(user.getCompanyId(), 0);
				logger.info("review check 111");
				mav = new ModelAndView("create_question_pro");
				mav.addObject("question", question);
				mav.addObject("message",
						"The reviewer email selected does have privileges to be a Reviewer. Enter some other Reviewer email id");// later
																																	// put
																																	// it
																																	// as
																																	// label
				mav.addObject("msgtype", "failure");
				mav.addObject("icon", "error");
				mav.addObject("types", QuestionType.values());
				mav.addObject("qs", qs.getContent());
				mav.addObject("levels", DifficultyLevel.values());
				mav.addObject("stacks", FullStackOptions.values());
				return mav;
			} else if (usr == null) {
				User user2 = new User();
				user2.setUserType(UserType.REVIEWER);
				user2.setEmail(reviewer);
				user2.setPassword("" + reviewer.hashCode());
				user2.setCompanyId(user.getCompanyId());
				user2.setCompanyName(user.getCompanyName());
				logger.info("saving user 1");
				userService.saveOrUpdate(user2);
				logger.info("saving user 2");
			}

		}

		if (question.getId() != null) {
			System.out.println("before saving Qu");
			logger.info("before saving Qu");
			questionService.updateQuestion(question);

			CandidateProfileParams params = profileService.findUniqueCandidateProfileParams(user.getCompanyId(),
					question.getQualifier1(),
					question.getQualifier2() == null ? "NA"
							: question.getQualifier2().isEmpty() ? "NA" : question.getQualifier2(),
					question.getQualifier3() == null ? "NA"
							: question.getQualifier3().isEmpty() ? "NA" : question.getQualifier3(),
					question.getQualifier4() == null ? "NA"
							: question.getQualifier4().isEmpty() ? "NA" : question.getQualifier4(),
					question.getQualifier5() == null ? "NA"
							: question.getQualifier5().isEmpty() ? "NA" : question.getQualifier5());
			if (params == null) {
				CandidateProfileParams candidateProfileParam = new CandidateProfileParams();
				candidateProfileParam.setCompanyId(user.getCompanyId());
				candidateProfileParam.setCompanyName(user.getCompanyName());
				candidateProfileParam.setCreateDate(new Date());
				candidateProfileParam.setLESS_THAN_TWENTY_PERCENT("Need to work a bit. Not good!");
				candidateProfileParam
						.setBETWEEN_TWENTY_AND_FIFTY("Knowledge Quotient inadequate for the given topic!!");
				candidateProfileParam.setBETWEEN_FIFTY_AND_SEVENTYFIVE("Average Grade for a given topic!!");
				candidateProfileParam
						.setBETWEEN_SEVENTYFIVE_AND_NINETY("Good knowledge. Potential to be an expert in this area!!");
				candidateProfileParam.setMORE_THAN_NINETY("Excellent knowledge on the theory behind the topic.");
				candidateProfileParam.setQualifier1(question.getQualifier1());
				candidateProfileParam.setQualifier2(question.getQualifier2() == null ? "NA"
						: question.getQualifier2().isEmpty() ? "NA" : question.getQualifier2());
				candidateProfileParam.setQualifier3(question.getQualifier3() == null ? "NA"
						: question.getQualifier3().isEmpty() ? "NA" : question.getQualifier3());
				candidateProfileParam.setQualifier4(question.getQualifier4() == null ? "NA"
						: question.getQualifier4().isEmpty() ? "NA" : question.getQualifier4());
				candidateProfileParam.setQualifier5(question.getQualifier5() == null ? "NA"
						: question.getQualifier5().isEmpty() ? "NA" : question.getQualifier5());
				profileRepository.save(candidateProfileParam);
			}
			System.out.println("before saving Qu");

			logger.info("before saving Qu");
		} else {
			logger.info("before saving Q");
			questionService.createQuestion(question);
			CandidateProfileParams params = profileService.findUniqueCandidateProfileParams(user.getCompanyId(),
					question.getQualifier1(),
					question.getQualifier2() == null ? "NA"
							: question.getQualifier2().isEmpty() ? "NA" : question.getQualifier2(),
					question.getQualifier3() == null ? "NA"
							: question.getQualifier3().isEmpty() ? "NA" : question.getQualifier3(),
					question.getQualifier4() == null ? "NA"
							: question.getQualifier4().isEmpty() ? "NA" : question.getQualifier4(),
					question.getQualifier5() == null ? "NA"
							: question.getQualifier5().isEmpty() ? "NA" : question.getQualifier5());
			if (params == null) {
				CandidateProfileParams candidateProfileParam = new CandidateProfileParams();
				candidateProfileParam.setCompanyId(user.getCompanyId());
				candidateProfileParam.setCompanyName(user.getCompanyName());
				candidateProfileParam.setCreateDate(new Date());
				candidateProfileParam.setLESS_THAN_TWENTY_PERCENT("Need to work a bit. Not good!");
				candidateProfileParam
						.setBETWEEN_TWENTY_AND_FIFTY("Knowledge Quotient inadequate for the given topic!!");
				candidateProfileParam.setBETWEEN_FIFTY_AND_SEVENTYFIVE("Average Grade for a given topic!!");
				candidateProfileParam
						.setBETWEEN_SEVENTYFIVE_AND_NINETY("Good knowledge. Potential to be an expert in this area!!");
				candidateProfileParam.setMORE_THAN_NINETY("Excellent knowledge on the theory behind the topic.");
				candidateProfileParam.setQualifier1(question.getQualifier1());
				candidateProfileParam.setQualifier2(question.getQualifier2() == null ? "NA"
						: question.getQualifier2().isEmpty() ? "NA" : question.getQualifier2());
				candidateProfileParam.setQualifier3(question.getQualifier3() == null ? "NA"
						: question.getQualifier3().isEmpty() ? "NA" : question.getQualifier3());
				candidateProfileParam.setQualifier4(question.getQualifier4() == null ? "NA"
						: question.getQualifier4().isEmpty() ? "NA" : question.getQualifier4());
				candidateProfileParam.setQualifier5(question.getQualifier5() == null ? "NA"
						: question.getQualifier5().isEmpty() ? "NA" : question.getQualifier5());
				profileRepository.save(candidateProfileParam);
			}
			logger.info("after saving Q");
		}

//		questions = questionService.findQuestions(user.getCompanyId());
//		mav = new ModelAndView("question_list");
//		mav.addObject("qs", questions);
//		return mav;
		mav = new ModelAndView("create_question_pro");
		mav.addObject("message", "Question Save Success");// later put it as label
		mav.addObject("msgtype", "Success");
		mav.addObject("icon", "success");
		// return listQuestions(null, response, request, mav.getModelMap());
		question = new Question();
		question.setType(
				question.getQuestionType() != null ? question.getQuestionType().getType() : QuestionType.MCQ.getType());

		question.setLang(question.getLanguage() != null ? question.getLanguage().getLanguage()
				: ProgrammingLanguage.JAVA.getLanguage());
		mav.addObject("question", question);
		mav.addObject("question_label", "Add new Question");
		logger.info("after saving Q ...1");
		Page<Question> questions2 = questionService.findQuestionsByPage(user.getCompanyId(), 0);
		logger.info("after saving Q ...2");
		mav.addObject("qs", questions2.getContent());
		mav.addObject("levels", DifficultyLevel.values());
		mav.addObject("types", QuestionType.values());
		mav.addObject("languages", ProgrammingLanguage.values());
		mav.addObject("stacks", FullStackOptions.values());
		logger.info("after saving Q ...3");
		CommonUtil.setCommonAttributesOfPagination(questions2, mav.getModelMap(), 0, "addQuestion", null);
		logger.info("after saving Q ...4");
		return mav;
	}

	@RequestMapping(value = "/searchQuestionsPro", method = RequestMethod.GET)
	public ModelAndView searchQuestionsPro(@RequestParam(name = "page", required = false) Integer pageNumber,
			@RequestParam String searchText, HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView("pro_question-bank");
		User user = (User) request.getSession().getAttribute("user");
		if (pageNumber == null) {
			pageNumber = 0;
		}
		Page<Question> questions = questionService.searchQuestions(user.getCompanyId(), searchText, pageNumber);
		mav.addObject("qs", questions.getContent());
		mav.addObject("levels", DifficultyLevel.values());
		mav.addObject("types", QuestionType.values());
		mav.addObject("languages", ProgrammingLanguage.values());
		Map<String, String> params = new HashMap<>();
		params.put("searchText", searchText);
		CommonUtil.setCommonAttributesOfPagination(questions, mav.getModelMap(), pageNumber, "searchQuestions", params);
		return mav;
	}

	@RequestMapping(value = "/removeQuestionPro", method = RequestMethod.GET)
	public ModelAndView removeQuestionPro(@RequestParam(name = "page", required = false) Integer pageNumber,
			@RequestParam(name = "qid", required = false) Long qid, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ModelAndView mav = new ModelAndView("pro_question-bank");
		if (qid != null) {
			Boolean canDelete = questionService.canDeleteQuestion(qid);
			if (canDelete) {
				questionService.removeQuestion(qid);
				mav.addObject("message", "Question successfully deleted");// later put it as label
				mav.addObject("msgtype", "success");
				mav.addObject("icon", "success");
			} else {
				mav.addObject("message", "This Question is associated with one or more Tests. Can not delete this Q");// later
																														// put
																														// it
																														// as
																														// label
				mav.addObject("msgtype", "failure");
				mav.addObject("icon", "error");
			}
		} else {
			mav.addObject("message", "Nothing to remove");// later put it as label
			mav.addObject("msgtype", "failure");
			mav.addObject("icon", "error");
		}

		Question question = new Question();
		mav.addObject("question", question);
		User user = (User) request.getSession().getAttribute("user");
		if (pageNumber == null) {
			pageNumber = 0;
		}
		// Page<Question> questions =
		// questionService.findQuestionsByPage(user.getCompanyId(), pageNumber);
		Page<Question> questions = questionService.getAllLevel1Questions(user.getCompanyId(), pageNumber);
		mav.addObject("qs", questions.getContent());
		mav.addObject("levels", DifficultyLevel.values());
		mav.addObject("types", QuestionType.values());
		mav.addObject("languages", ProgrammingLanguage.values());
		CommonUtil.setCommonAttributesOfPagination(questions, mav.getModelMap(), pageNumber, "addQuestion", null);
		return mav;
	}

}
