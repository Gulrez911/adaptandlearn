package com.assessment.web.controllers;

import java.io.IOException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;
import java.util.TimeZone;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.assessment.common.CommonUtil;
import com.assessment.common.PropertyConfig;
import com.assessment.common.SchedulerTask;
import com.assessment.common.util.EmailGenericMessageThread;
import com.assessment.data.Company;
import com.assessment.data.DifficultyLevel;
import com.assessment.data.FooterUTF;
import com.assessment.data.JobDescription;
import com.assessment.data.JobDescriptionRecruiter;
import com.assessment.data.LMSUserModuleMapping;
import com.assessment.data.License;
import com.assessment.data.Module;
import com.assessment.data.ModuleItem;
import com.assessment.data.PublicTestUTF;
import com.assessment.data.Question;
import com.assessment.data.QuestionMapperInstance;
import com.assessment.data.Test;
import com.assessment.data.TestLinkTime;
import com.assessment.data.TestScheduler;
import com.assessment.data.User;
import com.assessment.data.UserOtp;
import com.assessment.data.UserTestSession;
import com.assessment.data.UserType;
import com.assessment.repositories.FooterUTFRepository;
import com.assessment.repositories.PublicTestUTFRepository;
import com.assessment.repositories.TestLinkTimeRepository;
import com.assessment.repositories.TestRepository;
import com.assessment.repositories.TestSchedulerRepository;
import com.assessment.repositories.UserTestSessionRepository;
import com.assessment.scheduler.ScheduleTaskService;
import com.assessment.services.CompanyService;
import com.assessment.services.JobDescriptionRecruiterService;
import com.assessment.services.JobDescriptionService;
import com.assessment.services.LicenseService;
import com.assessment.services.LmsUserModuleMappingService;
import com.assessment.services.ModuleService;
import com.assessment.services.ProctorTrackService;
import com.assessment.services.QuestionMapperInstanceService;
import com.assessment.services.QuestionService;
import com.assessment.services.TestService;
import com.assessment.services.UserOtpService;
import com.assessment.services.UserService;
import com.assessment.services.UserTestSessionService;
import com.assessment.web.dto.LicenseModuleDTO;
import com.assessment.web.dto.ModuleDTO;
import com.assessment.web.dto.TestUserData;
import com.assessment.web.dto.proctortrack.Assignment_data;
import com.assessment.web.dto.proctortrack.ProctorTrackRequest;
import com.assessment.web.dto.proctortrack.ProctorTrackUser;

@Controller
public class LoginController {
	@Autowired
	UserService userService;
	@Autowired
	QuestionService questionService;
	@Autowired
	CompanyService companyService;
	@Autowired
	TestService testService;

	@Autowired
	PropertyConfig propertyConfig;

	Boolean first = false;

	@Autowired
	TestSchedulerRepository rep;

	@Autowired
	ScheduleTaskService schedulerService;
	@Autowired
	PropertyConfig config;
	@Autowired
	UserTestSessionRepository testSessionRepository;

	@Autowired
	QuestionMapperInstanceService qminService;

	@Autowired
	UserOtpService userOtpService;

	@Autowired
	LMSController lmsController;

	@Autowired
	TestLinkTimeRepository linkTimeRepository;

	@Autowired
	LmsUserModuleMappingService mappingService;

	@Autowired
	ModuleService moduleService;

	@Autowired
	LicenseService licenseService;

	@Autowired
	ProctorTrackService proctorTrackService;
	@Autowired
	JobDescriptionRecruiterService descriptionRecruiterService;
	@Autowired
	JobDescriptionService descriptionService;
	static Logger logger = LoggerFactory.getLogger(LoginController.class);

	@Autowired
	PublicTestUTFRepository publicTestUTFRepository;

	@Autowired
	FooterUTFRepository footerUTFRepository;

	@Autowired
	NewLoginController newLoginController;

	@Autowired
	TestRepository testRepository;

	@Autowired
	CategoryContextController categoryContextController;

	@Autowired
	UserTestSessionService testSessionService;

	private final String prefixURL = "iiht_html";

	@RequestMapping(value = "/prologin", method = RequestMethod.GET)
	public ModelAndView prologin(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView("pro_login");
		User user = new User();
		user.setEmail("newadmin2@iiht.com");
		user.setPassword("1234");
//				 user.setCompanyName("IIHT");
		mav.addObject("user", user);

		return mav;
	}

	@RequestMapping(value = "/testDesign", method = RequestMethod.GET)
	public ModelAndView testDesign(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView("testDesign");

		return mav;
	}

	@GetMapping("/loginCheck")
	@ResponseBody
	public Map<String, Object> authCheck(HttpServletRequest request, @RequestParam String email,
			@RequestParam String password) {
		Map<String, Object> map1 = new HashMap<>();
		String companyName = config.getCompanyName();
		User user = userService.authenticate(email, password, companyName);
		if (user != null) {
			request.getSession().setAttribute("user", user);
		}
		map1.put("user44", user);
		return map1;
	}

//	@RequestMapping(value = "/proindex", method = RequestMethod.GET)
	@RequestMapping(value = "/proindex", method = RequestMethod.GET)
	public ModelAndView proindex(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView("pro_index");

		User user = (User) request.getSession().getAttribute("user");
		if (user == null) {
			user = new User();
			mav.addObject("user", user);
			user.setEmail("newadmin2@iiht.com");
			user.setPassword("1234");

			mav.setViewName("pro_login");
		} else {

			List<Test> tests = testRepository.findByAdmin(user.getEmail(), user.getCompanyId());

			mav.addObject("tests", tests);
			List<UserTestSession> list = testSessionService.findTop10(user.getCompanyId());
			mav.addObject("list", list);
		}

		return mav;
	}

//	@RequestMapping(value = "/proindex", method = RequestMethod.GET)
	@RequestMapping(value = "/getResult", method = RequestMethod.GET)
	public ModelAndView getResult(HttpServletRequest request, HttpServletResponse response, @RequestParam String email,
			@RequestParam String testName) {
		ModelAndView mav = new ModelAndView("studentTestCompletionPro2");

		User user = (User) request.getSession().getAttribute("user");

		UserTestSession userTestSession = testSessionService.findByPrimaryKey(email, testName, user.getCompanyId());
		Test  test = testRepository.findByPrimaryKey(testName, user.getCompanyId());
		mav.addObject("test", test);
		mav.addObject("userTestSession", userTestSession);

		return mav;
	}

	@RequestMapping(value = "/proSignOut", method = RequestMethod.GET)
	public ModelAndView proSignOut(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView("pro_login");
		request.getSession().invalidate();
		User user = new User();
		mav.addObject("user", user);

		return mav;
	}

//	@RequestMapping(value = "/protestbank", method = RequestMethod.GET)
//	public ModelAndView protestbank(HttpServletRequest request, HttpServletResponse response) {
//		ModelAndView mav = new ModelAndView("pro_tests");
//		return mav;
//	}

//	@RequestMapping(value = "/pro_test-details", method = RequestMethod.GET)
//	public ModelAndView proTest(HttpServletRequest request, HttpServletResponse response) {
////		ModelAndView mav = new ModelAndView("pro_test-details");
//		ModelAndView mav = new ModelAndView("create-new-test");
//		return mav;
//	}
//	

	@RequestMapping(value = "/hackathon", method = RequestMethod.GET)
	public ModelAndView hackathon(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView("hackathon");
		return mav;
	}

	@RequestMapping(value = "/learner", method = RequestMethod.GET)
	public ModelAndView learner(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView("index_j");
		User user = new User();
		user.setCompanyName("AL");
		mav.addObject("user", user);
		return mav;
	}

	@RequestMapping(value = "/learnerlogoff", method = RequestMethod.GET)
	public ModelAndView learnerlogoff(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView("index_j");
		request.getSession().invalidate();
		User user = new User();
		user.setCompanyName("AL");
		mav.addObject("user", user);
		return mav;
	}

	@RequestMapping(value = "/removelater", method = RequestMethod.GET)
	public ModelAndView removelater(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView("ecl");
		return mav;
	}

	public void init(String companyId) {
		List<TestScheduler> schedulers = rep.findByCompanyId(companyId);
		for (TestScheduler scheduler : schedulers) {
			TimeZone timeZone = TimeZone.getTimeZone("Asia/Kolkata");
			CronTrigger cronTrigger = new CronTrigger(scheduler.getCronExpression(), timeZone);
			SchedulerTask schedulerTask = new SchedulerTask(scheduler.getTestId(), scheduler.getTestName(),
					scheduler.getCompanyId(), config.getBaseUrl(), scheduler.getUserEmails(),
					config.getTestLinkHtml_Generic_Location(), config);
			schedulerService.addTaskToScheduler(scheduler.getId().intValue(), schedulerTask, cronTrigger);
		}
	}

	@RequestMapping(value = "/lmsadmin", method = RequestMethod.GET)
	public ModelAndView lmsadmin(@RequestParam String lmsadminuser, @RequestParam String companyId,
			HttpServletRequest request, HttpServletResponse response) {
		String token = "bG1zYWRtaW5AaWlodC5jb20=";
		if (lmsadminuser == null || companyId == null) {
			ModelAndView mav = new ModelAndView("login_new");
			User user = new User();
			mav.addObject("user", user);
			return mav;
		} else {
			String usr = new String(Base64.getDecoder().decode(token.getBytes()));
			User user = userService.findByPrimaryKey(usr, companyId);
			if (user == null) {
				ModelAndView mav = new ModelAndView("login_new");
				User u = new User();
				mav.addObject("user", u);
				return mav;
			}
			ModelAndView mav = new ModelAndView("question_list");
			Page<Question> questions = questionService.getAllLevel1Questions(user.getCompanyId(), 0);
			request.getSession().setAttribute("user", user);
			request.getSession().setAttribute("companyId", user.getCompanyId());
			// request.getSession().setAttribute("questions", questions);

			mav = new ModelAndView("question_list");
			mav.addObject("qs", questions.getContent());
			mav.addObject("levels", DifficultyLevel.values());
			CommonUtil.setCommonAttributesOfPagination(questions, mav.getModelMap(), 0, "question_list", null);
			return mav;
		}

	}

	@RequestMapping(value = "/aabrakadaabra", method = RequestMethod.GET)
	public ModelAndView showLogin(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView("login_new");
		User user = new User();
		// user.setEmail("system@iiht.com");
		// user.setPassword("1234");
		// user.setCompanyName("IIHT");
		mav.addObject("user", user);
		return mav;
	}

	@RequestMapping(value = "/registerAssessment", method = RequestMethod.GET)
	public ModelAndView registerAssessment(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView("registerAssessment");
		User user = new User();
		mav.addObject("user", user);
		String testName = propertyConfig.getAssessmentNameForIT();
		mav.addObject("testName", testName);
		return mav;
	}

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView showRoot(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// return showLogin(request, response);
		// return new ModelAndView("redirect:/landing");
		String domain = request.getServerName();
		System.out.println("domain req " + domain);
		if (domain.contains("quantpro")) {
			System.out.println("redirecting to http://quantpro.in/public/index.html");
			response.sendRedirect("http://quantpro.in/public/index.html");
		}

		return categoryContextController.landing2(request, response);
	}

//	  @RequestMapping(value = "/publicTest", method = RequestMethod.GET)

//	  public ModelAndView showPublicTest2(HttpServletRequest request, HttpServletResponse response, @RequestParam String companyId, @RequestParam String startTime, 
//			  @RequestParam String endTime, @RequestParam Long testId, @RequestParam(required=false) String inviteSent) {
//		  
//		  ModelAndView mav = new ModelAndView("publicTest2");
//		  mav.addObject("inviteSent", inviteSent);
//		  mav.addObject("companyId", companyId);
//		  mav.addObject("startTime", startTime);
//		  mav.addObject("endTime", endTime);
//		  mav.addObject("testId", testId);
//		  
//		  return mav;
//	  }

	// it shows public data
	// @RequestMapping(value = "/publicTest", method = RequestMethod.GET)
	@RequestMapping(value = "/mcqPublic", method = RequestMethod.GET)
	public ModelAndView showPublicTest(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(name = "lang", required = false) String lang) {
		// ModelAndView mav = new ModelAndView("publicTest");
		ModelAndView mav = new ModelAndView("publicTest_new");
		User user = new User();
		TestUserData testUserData = new TestUserData();
		String testId = request.getParameter("testId");
		String companyId = request.getParameter("companyId");
		String inviteSent = request.getParameter("inviteSent");
		String startTime = request.getParameter("startTime");
		String endTime = request.getParameter("endTime");
		String webProctor = request.getParameter("webProctorFlag");

		if (webProctor != null && webProctor.equals("yes")) {
			request.getSession().setAttribute("webProctorFlag", "yes");
		} else {
			request.getSession().setAttribute("webProctorFlag", "no");
		}

		if (startTime == null || endTime == null) {
			mav = new ModelAndView("testLinkNotEnabled");
			mav.addObject("message",
					"This is a old test link and no longer used. Contact Test Admin to get a new test link.");
			return mav;
		}

		startTime = URLDecoder.decode(startTime);
		endTime = URLDecoder.decode(endTime);
		startTime = new String(Base64.getDecoder().decode(startTime.getBytes()));
		endTime = new String(Base64.getDecoder().decode(endTime.getBytes()));
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy hh:mm aa");
		String url = propertyConfig.getBaseUrl() + "publicTest?" + request.getQueryString();
		Boolean dontCheckTimeValidity = false;
		try {
			TestLinkTime linkTime = linkTimeRepository.findUniquetestLink(companyId, url);
			dontCheckTimeValidity = (linkTime == null ? false : linkTime.getDontCheckTimeValidity());
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			// e1.printStackTrace();
			System.out.println("error " + e1.getMessage());
		}
		if (!dontCheckTimeValidity) {
			try {
				Date sDate = new Date(Long.parseLong(startTime));
				Date eDate = new Date(Long.parseLong(endTime));
				Long now = System.currentTimeMillis();
				Long start = sDate.getTime();
				Long end = eDate.getTime();
				String view = "testLinkNotEnabled";
				String message = "";
				Boolean inactive = false;
				if (start > now) {
					message = "Link is not yet active. It will be activated at " + dateFormat.format(sDate)
							+ ". Try later.";
					inactive = true;
				}
				if (now > end) {
					message = "Link has expired at " + dateFormat.format(eDate) + ". Contact Test Admin.";
					inactive = true;
				}

				if (inactive) {
					mav = new ModelAndView(view);
					mav.addObject("message", message);
					return mav;
				}

			} catch (Exception e) {
				// TODO Auto-generated catch block
				mav = new ModelAndView("testLinkNotEnabled");
				mav.addObject("message",
						"This is a invalid test link. Make sure you have correctly copied it. Contact Test Admin to get more help if it still doesn't work.");
				return mav;
			}
		}

		if (inviteSent != null) {
			request.getSession().setAttribute("inviteSent", inviteSent);
		}
		Company company = companyService.findByCompanyId(companyId);
		if (company == null) {
			return mav;
		}
		user.setCompanyName(company.getCompanyName());
		user.setCompanyId(company.getCompanyId());
		testUserData.setUser(user);
		Test test = testService.findTestById(Long.parseLong(testId));
		if (test.getNewUi() != null && test.getNewUi()) {
			// mav.setViewName("users-test-login");
			mav.setViewName("mcq1");
		}

		testUserData.setTestId(test.getId() + "");
		testUserData.setTestName(test.getTestName());
		request.getSession().setAttribute("user", user);
		request.getSession().setAttribute("dontCheckTimeValidity", dontCheckTimeValidity);
		mav.addObject("test", test);
		mav.addObject("testUserData", testUserData);
		mav.addObject("startTime", startTime);
		mav.addObject("endTime", endTime);

		if (lang == null) {
			if (test.getTestLanguage().equalsIgnoreCase("eng")) {
				lang = "eng";
			} else if (test.getTestLanguage().equalsIgnoreCase("arabic")) {
				lang = "arabic";
			}
		}
		request.getSession().setAttribute("lang", lang);
		PublicTestUTF publicTestUTF = publicTestUTFRepository.findByLanguage(lang);
		mav.addObject("publicTestUTF", publicTestUTF);

		FooterUTF footerUTF = footerUTFRepository.findByLanguage(lang);
		mav.addObject("footerUTF", footerUTF);
//	    getLang(mav, lang);
		return mav;
	}

//	  public void getLang(ModelAndView mav, String lang) {
//		  if(lang==null||lang.equalsIgnoreCase("eng")) {
//			  mav.addObject("firstName", "First Name");
//			  mav.addObject("email", "Email");
//			  mav.addObject("lastName","Last Name");
//			  mav.addObject("candidateId", "Candidate Id");
//			  mav.addObject("degree","Degree");
//			  mav.addObject("passingYear","Passing Year");
//			  mav.addObject("mobile","Mobile");
//			  mav.addObject("submit", "Submit");
//		  }else {
//			  mav.addObject("firstName", "الاسم الاول");
//			  mav.addObject("email", "عنوان بريد الكتروني");
//			  mav.addObject("lastName", "الكنية");
//			  mav.addObject("candidateId", "معرف المرشح");
//			  mav.addObject("degree", "درجة");
//			  mav.addObject("passingYear", "مرور عام");
//			  mav.addObject("mobile", "متحرك");
//			  mav.addObject("submit", "يقدم");
//		  }
//	  }

	// @RequestMapping(value = "/publicTestAuthenticate", method =
	// RequestMethod.POST)
	@RequestMapping(value = "/mcqAuth", method = RequestMethod.POST)
	public ModelAndView publicTestAuthenticate(HttpServletRequest request, HttpServletResponse response,
			@ModelAttribute("testUserData") TestUserData testUserData) {

		testUserData.getUser().setPassword("12345");
		Test test = testService.findTestById(Long.parseLong(testUserData.getTestId()));
		/**
		 * Just making sure users are allowed to give second attempt from same browser
		 * session. This flag supports preventing the results page to be refreshed again
		 * by the user.
		 */
		request.getSession().setAttribute("submitted", null);
		/**
		 * Remove otp entry for the user for the given test
		 */
		// userOtpService.deleteUserOtp(testUserData.getUser().getEmail(),
		// test.getCompanyId(), test.getTestName());

		/**
		 * Step 1 - figure out if the user has taken a test.
		 */
		UserTestSession session = testSessionRepository.findByPrimaryKey(testUserData.getUser().getEmail(),
				test.getTestName(), test.getCompanyId());
		String userNameNew = "";
		if (session == null) {
			userNameNew = testUserData.getUser().getEmail();
		} else {
			/**
			 * Step 2 - find out how many sessions for the given test the user has taken
			 */
			List<UserTestSession> sessions = testSessionRepository.findByTestNamePart(
					testUserData.getUser().getEmail() + "[" + test.getId(), test.getTestName(), test.getCompanyId());
			int noOfConfAttempts = test.getNoOfConfigurableAttempts() == null ? 50 : test.getNoOfConfigurableAttempts();
			if (noOfConfAttempts <= (sessions.size() + 1)) {
				ModelAndView mav = new ModelAndView("studentNoTest_ExceededAttempts");
				mav.addObject("firstName", testUserData.getUser().getFirstName());
				mav.addObject("lastName", testUserData.getUser().getLastName());
				mav.addObject("attempts", sessions.size() + 1);
				return mav;
			}

			userNameNew = testUserData.getUser().getEmail() + "[" + test.getId() + "-" + (sessions.size() + 1 + "]");
		}

		boolean validate = validateDomainCheck(test, testUserData.getUser().getEmail());
		if (!validate) {
			ModelAndView mav = new ModelAndView("studentNoTest_Domain");
			mav.addObject("firstName", testUserData.getUser().getFirstName());
			mav.addObject("lastName", testUserData.getUser().getLastName());
			mav.addObject("domain", test.getDomainEmailSupported());
			return mav;
		}
		testUserData.getUser().setEmail(userNameNew);
		userService.saveOrUpdate(testUserData.getUser());
		request.getSession().setAttribute("user", testUserData.getUser());

		request.getSession().setAttribute("test", test);
		if (testUserData.getUser() == null) {
			String lang = "";
			return showPublicTest(request, response, lang);
		}
		String userId = URLEncoder
				.encode(Base64.getEncoder().encodeToString(testUserData.getUser().getEmail().getBytes()));
		String companyId = URLEncoder.encode(test.getCompanyId());
		String inviteSent = (String) request.getSession().getAttribute("inviteSent");
		String append = "";
		if (inviteSent != null) {
			append += "&inviteSent=" + inviteSent;
		}
		// String url =
		// "redirect:/startTestSession2?userId="+userId+"&companyId="+companyId+"&testId="+test.getId()+append+"&sharedDirect=yes&startDate="+URLEncoder.encode(Base64.getEncoder().encodeToString(testUserData.getStartTime().getBytes()))+"&endDate="+URLEncoder.encode(Base64.getEncoder().encodeToString(testUserData.getEndTime().getBytes()));
		String url = "redirect:/mcq2?userId=" + userId + "&companyId=" + companyId + "&testId=" + test.getId() + append
				+ "&sharedDirect=yes&startDate="
				+ URLEncoder.encode(Base64.getEncoder().encodeToString(testUserData.getStartTime().getBytes()))
				+ "&endDate="
				+ URLEncoder.encode(Base64.getEncoder().encodeToString(testUserData.getEndTime().getBytes()));
		String webProctorFlag = (String) request.getSession().getAttribute("webProctorFlag");
		logger.info("webProctorFlag is " + webProctorFlag);
		// log
		if (webProctorFlag != null && webProctorFlag.equals("yes")) {

			String testUrl = propertyConfig.getBaseUrl() + "mcq2?userId=" + userId + "&companyId=" + companyId
					+ "&testId=" + test.getId() + append + "&sharedDirect=yes&startDate="
					+ URLEncoder.encode(Base64.getEncoder().encodeToString(testUserData.getStartTime().getBytes()))
					+ "&endDate="
					+ URLEncoder.encode(Base64.getEncoder().encodeToString(testUserData.getEndTime().getBytes()));
			logger.info("testUrl is " + testUrl);
			String externalProctoredUrl = getWebProctoredUrl(testUserData, testUrl, test);
			logger.info("externalProctoredUrl is " + externalProctoredUrl);
			if (externalProctoredUrl != null) {
				return new ModelAndView("redirect:" + externalProctoredUrl);
			} else {
				ModelAndView mav = new ModelAndView(url);
				return mav;
			}

		} else {
			ModelAndView mav = new ModelAndView(url);
			return mav;
		}

	}

	private String getWebProctoredUrl(TestUserData userData, String testUrl, Test test) {
		ProctorTrackRequest request = new ProctorTrackRequest();

		Assignment_data assignment_data = new Assignment_data();
		assignment_data.setAccess_code("NOT_REQUIRED");
		// assignment_data.setAttempts_allowed(test.getNoOfConfigurableAttempts());
		assignment_data.setAttempts_allowed(5);
		assignment_data.setDuration("" + (test.getTestTimeInMinutes() == null ? 60 : test.getTestTimeInMinutes()));
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");

		// LocalDate loc = LocalDate.now().minusDays(300);
		Date st = new Date();
		Calendar cal = Calendar.getInstance();
		cal.setTime(st);
		cal.add(Calendar.DATE, -300);
		Date date = cal.getTime();

		String start = format.format(date);

		Calendar cal2 = Calendar.getInstance();
		cal2.setTime(st);
		cal2.add(Calendar.DATE, 300);
		Date endDate = cal2.getTime();

		// Date endDate = new Date(date.getTime() +
		// (test.getTestTimeInMinutes()==null?(60* 60000):(test.getTestTimeInMinutes() *
		// 60000)));
		String end = format.format(endDate);
		assignment_data.setEnd(end);
		assignment_data.setGroup_id("IIHT_Single_Ins");
		assignment_data.setId("" + test.getId());
		assignment_data.setIs_active(true);

		Boolean onboard = test.getOnboardingTest() == null ? false : test.getOnboardingTest();

		if (test.getTestName().equalsIgnoreCase("CGI MOCK TEST")
				|| test.getTestName().equalsIgnoreCase("notesapp-mysql-production") || onboard) {
			assignment_data.setIs_onboarding(true);
		} else {
			assignment_data.setIs_onboarding(false);
		}

		assignment_data.setIs_proctored(true);
		assignment_data.setName(test.getTestName());
		assignment_data.setStart(start);
		assignment_data.setTest_url(testUrl);
		// assignment_data.setDuration(""+test.getTestTimeInMinutes());

		ProctorTrackUser proctorTrackUser = new ProctorTrackUser();
		proctorTrackUser.setEmail(userData.getUser().getEmail());
		proctorTrackUser
				.setFirst_name(userData.getUser().getFirstName() == null ? "na" : userData.getUser().getFirstName());
		proctorTrackUser
				.setLast_name(userData.getUser().getLastName() == null ? "na" : userData.getUser().getLastName());
		proctorTrackUser.setRole("student");
		proctorTrackUser.setUser_id(userData.getUser().getEmail());
		proctorTrackUser.setLang_code("en");
		request.setAssignment_data(assignment_data);
		request.setUser(proctorTrackUser);
		String url;
		try {
			url = proctorTrackService.getProctoredUrl(request);
			return url;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}

	}

	private boolean validateDomainCheck(Test test, String email) {
		if (test.getDomainEmailSupported() == null || test.getDomainEmailSupported().trim().length() == 0
				|| test.getDomainEmailSupported().equals("*")) {
			return true;
		}

		String dom = email.substring(email.indexOf("@") + 1, email.length());
		if (test.getDomainEmailSupported().contains(dom)) {
			return true;
		}

		return false;
	}

	@RequestMapping(value = "/problem", method = RequestMethod.GET)
	public ModelAndView problem(HttpServletRequest request, HttpServletResponse response) {

		String stack = (String) request.getSession().getAttribute("errorStack");
		if (stack != null) {
			EmailGenericMessageThread client = new EmailGenericMessageThread("jatin.sutaria@thev2technologies.com",
					"Error in Assessment Platform", stack, propertyConfig);
			Thread th = new Thread(client);
			th.start();
		}
		request.getSession().invalidate();
		ModelAndView mav = new ModelAndView("errorPage");

		return mav;
	}

	@RequestMapping(value = "/signoff", method = RequestMethod.GET)
	public ModelAndView signoff(HttpServletRequest request, HttpServletResponse response) {
		request.getSession().invalidate();
		// ModelAndView mav = new ModelAndView("index");
		ModelAndView mav = new ModelAndView("login_new");
		User user = new User();
		// user.setEmail("system@iiiht.com");
		// user.setPassword("1234");
		// user.setCompanyName("IIHT");
		mav.addObject("user", user);
		return mav;
	}

	@RequestMapping(value = "/populateModulesSample", method = RequestMethod.GET)
	public @ResponseBody String populateModulesSample(HttpServletRequest request, HttpServletResponse response) {
		User user = (User) request.getSession().getAttribute("user");
		List<Module> modules = moduleService.getModules(user.getCompanyId(), PageRequest.of(0, 10)).getContent();
		for (Module module : modules) {
			LMSUserModuleMapping lmsUserModuleMapping = new LMSUserModuleMapping();
			lmsUserModuleMapping.setModuleId(module.getId());
			lmsUserModuleMapping.setModuleName(module.getModuleName());
			lmsUserModuleMapping.setUser(user.getEmail());
			lmsUserModuleMapping.setCompanyId(user.getCompanyId());
			lmsUserModuleMapping.setCompanyName(user.getCompanyName());
			lmsUserModuleMapping.setCreatedBy(user.getEmail());
			mappingService.saveOrUpdate(lmsUserModuleMapping);
		}

		return "ok";
	}

	@RequestMapping(value = "/authenticateLearner", method = RequestMethod.POST)
	public ModelAndView authenticateLearner(HttpServletRequest request, HttpServletResponse response,
			@ModelAttribute("user") User user) {
		ModelAndView mav = null;
		user = userService.authenticate(user.getEmail(), user.getPassword(), user.getCompanyName());
		if (user != null && user.getUserType().getType().equals(UserType.LMS_STUDENT.getType())) {
			mav = new ModelAndView("dashboard_j");
			List<LicenseModuleDTO> licModules = getModulesForUserByLicenses(user);
			List<LicenseModuleDTO> freeLicModules = getModulesForUserByFreeLicenses(user, user.getCompanyId());
			System.out.println("modules size " + licModules.size());
			mav.addObject("licModules", licModules);
			mav.addObject("freeLicModules", freeLicModules);
			mav.addObject("user", user);
			request.getSession().setAttribute("user", user);
			return mav;
		} else {
			user = new User();
			mav = new ModelAndView("index_j");
			user = new User();
			user.setCompanyName("AL");
			mav.addObject("user", user);
			mav.addObject("user", user);
			mav.addObject("message", "Invalid Credentials to login as a Learner");// later put it as label
			mav.addObject("msgtype", "Failure");

			return mav;
		}
	}

	protected List<LicenseModuleDTO> getModulesForUserByFreeLicenses(User user, String companyId) {
		List<LicenseModuleDTO> licModules = new ArrayList<>();
		List<License> lics = licenseService.getFreeLicensesByCompany(companyId);
		Stack<String> stk = new Stack<>();
		stk.add("expired");
		stk.add("completed");
		stk.add("upcoming");
		stk.add("inprogress");
		for (License license : lics) {
			String lic = license.getLicenseName();
			lic = lic.trim();

			if (license != null) {
				List<Module> mods = moduleService.findModulesByLicense(lic, companyId);
				LicenseModuleDTO licenseModuleDTO = new LicenseModuleDTO();
				if (stk.size() == 0) {
					stk.add("expired");
					stk.add("completed");
					stk.add("upcoming");
					stk.add("inprogress");
				}
				String disp = stk.pop();
				licenseModuleDTO.setStyleClass(disp);

				licenseModuleDTO.setLicense(license);
				licModules.add(licenseModuleDTO);
				List<ModuleDTO> modules = new ArrayList<ModuleDTO>();
				for (Module module : mods) {
					ModuleDTO moduleDTO = new ModuleDTO();
					moduleDTO.setModule(module);

					moduleDTO.setLearnerEmail(user.getEmail());
					moduleDTO.setLearnerFullName(user.getFirstName() + " " + user.getLastName());

					modules.add(moduleDTO);
					for (ModuleItem item : module.getItems()) {
						String testName = item.getTestName();
						Test test = testService.findbyTest(testName, user.getCompanyId());
						String userTestUrl = getUrlForUser(user.getEmail(), test.getId(), user.getCompanyId());
						item.setUserSpecificLink(userTestUrl);
					}
				}
				licenseModuleDTO.setModules(modules);

			}
		}
		return licModules;
	}

	protected List<LicenseModuleDTO> getModulesForUserByLicenses(User user) {
		String lics = user.getLicenses();
		List<LicenseModuleDTO> licModules = new ArrayList<>();
		if (lics == null || lics.trim().length() == 0) {

		} else {
			String lc[] = lics.split(",");
			Stack<String> stk = new Stack<>();
			stk.add("inprogress");
			stk.add("upcoming");
			stk.add("completed");
			stk.add("expired");

			for (String lic : lc) {
				lic = lic.trim();
				License license = licenseService.findByPrimaryKey(lic, user.getCompanyId());
				if (license != null) {
					List<Module> mods = moduleService.findModulesByLicense(lic, user.getCompanyId());
					LicenseModuleDTO licenseModuleDTO = new LicenseModuleDTO();
					if (stk.size() == 0) {
						stk.add("inprogress");
						stk.add("upcoming");
						stk.add("completed");
						stk.add("expired");
					}
					String disp = stk.pop();
					licenseModuleDTO.setStyleClass(disp);
					licenseModuleDTO.setLicense(license);
					licModules.add(licenseModuleDTO);
					List<ModuleDTO> modules = new ArrayList<ModuleDTO>();
					for (Module module : mods) {
						ModuleDTO moduleDTO = new ModuleDTO();
						moduleDTO.setModule(module);

//		  							SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy hh:mm aa");
//		  							Date share = module.getUpdateDate() == null ? module.getCreateDate()
//		  									: module.getUpdateDate();
//		  							String dt = dateFormat.format(share);
//		  							moduleDTO.setSharedDate(dt);
						moduleDTO.setLearnerEmail(user.getEmail());
						moduleDTO.setLearnerFullName(user.getFirstName() + " " + user.getLastName());

						modules.add(moduleDTO);
						for (ModuleItem item : module.getItems()) {
							String testName = item.getTestName();
							Test test = testService.findbyTest(testName, user.getCompanyId());
							String userTestUrl = getUrlForUser(user.getEmail(), test.getId(), user.getCompanyId());
							item.setUserSpecificLink(userTestUrl);
						}
					}
					licenseModuleDTO.setModules(modules);

				}
			}
		}

		return licModules;
	}

	private List<ModuleDTO> getModulesForUser(User user) {
		List<LMSUserModuleMapping> mappings = mappingService.getAllModulesForUser(user.getCompanyId(), user.getEmail());
		List<ModuleDTO> modules = new ArrayList<ModuleDTO>();

		for (LMSUserModuleMapping mapping : mappings) {
			Module module = moduleService.findUniqueModule(mapping.getModuleName(), user.getCompanyId());
			ModuleDTO moduleDTO = new ModuleDTO();
			moduleDTO.setModule(module);
			moduleDTO.setSharedByEmail(mapping.getCreatedBy() == null ? "NA" : mapping.getCreatedBy());
			User trainer = null;
			if (mapping.getCreatedBy() != null) {
				trainer = userService.findByPrimaryKey(mapping.getCreatedBy(), user.getCompanyId());
				moduleDTO.setSharedByFullName(trainer.getFirstName() + " " + trainer.getLastName());
			}
			SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy hh:mm aa");
			Date share = mapping.getUpdateDate() == null ? mapping.getCreateDate() : mapping.getUpdateDate();
			String dt = dateFormat.format(share);
			moduleDTO.setSharedDate(dt);
			moduleDTO.setLearnerEmail(user.getEmail());
			moduleDTO.setLearnerFullName(user.getFirstName() + " " + user.getLastName());

			modules.add(moduleDTO);
			for (ModuleItem item : module.getItems()) {
				String testName = item.getTestName();
				Test test = testService.findbyTest(testName, user.getCompanyId());
				String userTestUrl = getUrlForUser(user.getEmail(), test.getId(), user.getCompanyId());
				item.setUserSpecificLink(userTestUrl);
			}
		}

		return modules;
	}

	@RequestMapping(value = "/authenticate", method = RequestMethod.POST)
	public ModelAndView authenticate(HttpServletRequest request, HttpServletResponse response,
			@ModelAttribute("user") User user) throws Exception {
		ModelAndView mav = null;

		user = userService.authenticate(user.getEmail(), user.getPassword(), user.getCompanyName());
		if (user == null) {
			// navigate to exception page
			// mav = new ModelAndView("index");
			mav = new ModelAndView("login_new");
			user = new User();
			mav.addObject("user", user);
			mav.addObject("message", "Invalid Credentials ");// later put it as label
			mav.addObject("msgtype", "Failure");
			return mav;
		} else if (user.getUserType().getType().equals(UserType.ADMIN_NEW.getType())) {
			request.getSession().setAttribute("user", user);
			return newLoginController.dashboardnew(0, request, response, null);
		} else if (user.getUserType().getType().equals(UserType.REVIEWER.getType())) {
			mav = new ModelAndView("java_fullstack");
			request.getSession().setAttribute("user", user);
			request.getSession().setAttribute("companyId", user.getCompanyId());
			List<QuestionMapperInstance> instances = qminService
					.findFullStackQuestionMapperInstancesForJava(user.getCompanyId());
			for (QuestionMapperInstance ins : instances) {
				User u = userService.findByPrimaryKey(ins.getUser(), user.getCompanyId());
				ins.setUerFullName(u.getFirstName() + " " + u.getLastName());
			}
			mav.addObject("instances", instances);
			return mav;
		} else if (user.getUserType().getType().equals(UserType.ADMIN.getType())
				|| user.getUserType().getType().equals(UserType.SUPER_ADMIN.getType())) {
			// to dashboard
			// List<Question> questions =
			// questionService.findQuestions(user.getCompanyId());
			/**
			 * Get all scheduled tests and load them into scheduler for the first time.
			 */
			if (!first) {
				init(user.getCompanyId());
				first = true;
			}
			// Page<Question> questions =
			// questionService.findQuestionsByPage(user.getCompanyId(), 0);
			Page<Question> questions = questionService.getAllLevel1Questions(user.getCompanyId(), 0);
			request.getSession().setAttribute("user", user);
			request.getSession().setAttribute("companyId", user.getCompanyId());
			// request.getSession().setAttribute("questions", questions);

			mav = new ModelAndView("question_list");
			mav.addObject("qs", questions.getContent());
			mav.addObject("levels", DifficultyLevel.values());
			CommonUtil.setCommonAttributesOfPagination(questions, mav.getModelMap(), 0, "question_list", null);
			return mav;
		} else if (user.getUserType().getType().equals(UserType.TENANT_ADMIN.getType())) {
			// to dashboard
			// List<Question> questions =
			// questionService.findQuestions(user.getCompanyId());
			/**
			 * Get all scheduled tests and load them into scheduler for the first time.
			 */
			if (!first) {
				init(user.getCompanyId());
				first = true;
			}
			// Page<Question> questions =
			// questionService.findQuestionsByPage(user.getCompanyId(), 0);
			Page<Question> questions = questionService.getAllLevel1Questions(user.getCompanyId(), 0);
			request.getSession().setAttribute("user", user);
			request.getSession().setAttribute("companyId", user.getCompanyId());
			// request.getSession().setAttribute("questions", questions);

			mav = new ModelAndView("tenant_admin");
			mav.addObject("qs", questions.getContent());
			mav.addObject("levels", DifficultyLevel.values());
			CommonUtil.setCommonAttributesOfPagination(questions, mav.getModelMap(), 0, "question_list", null);
			return mav;
		} else if (user.getUserType().getType().equals(UserType.RECRUITER.getType())) {

			List<JobDescriptionRecruiter> recruiters = descriptionRecruiterService
					.findByEmailAndCompanyId(user.getCompanyId(), user.getEmail());
			List<JobDescription> descriptions = new ArrayList<JobDescription>();
			for (JobDescriptionRecruiter descriptionRecruiter : recruiters) {
				JobDescription description = descriptionService.findbyPrimaryKey(
						descriptionRecruiter.getJobDescriptionName(), descriptionRecruiter.getCompanyId());
				descriptions.add(description);
			}
			request.getSession().setAttribute("user", user);
			request.getSession().setAttribute("companyId", user.getCompanyId());
			// request.getSession().setAttribute("questions", questions);

			mav = new ModelAndView("recruiterDashboard");
			mav.addObject("descriptions", descriptions);
//				CommonUtil.setCommonAttributesOfPagination(questions, mav.getModelMap(), 0, "question_list", null);
			return mav;
		} else {
			// student or learner
			// request.getSession().setAttribute("user", user);
			// return lmsController.goToLearnerDashboard(user.getEmail(), request,
			// response);

			mav = new ModelAndView("dashboard_j");
			request.getSession().setAttribute("user", user);
			request.getSession().setAttribute("companyId", user.getCompanyId());
			return mav;
		}

	}

	private String getUrlForUser(String user, Long testId, String companyId) {
		String userBytes = Base64.getEncoder().encodeToString(user.getBytes());

		String after = "userId=" + URLEncoder.encode(userBytes) + "&testId=" + URLEncoder.encode(testId.toString())
				+ "&companyId=" + URLEncoder.encode(companyId);
		String url = propertyConfig.getBaseUrl() + "mcq2?" + after;
		return url;
	}

	@RequestMapping(value = "/addQ", method = RequestMethod.GET)
	public ModelAndView addQ(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView("add_question");

		return mav;
	}

	@RequestMapping(value = "/listQ", method = RequestMethod.GET)
	public ModelAndView listQ(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView("question_list");

		return mav;
	}

	@RequestMapping(value = "/getotpfortest", method = RequestMethod.POST, consumes = { "application/json" })
	public @ResponseBody String getotpfortest(@RequestBody UserOtp userOtp, HttpServletRequest request,
			HttpServletResponse response) {

		UserOtp userOtp2 = userOtpService.getOtpForTestByUser(userOtp.getUser(), userOtp.getTestName(),
				userOtp.getCompanyId());
		if (userOtp2 == null) {
			return "Invalid details";
		}
		/**
		 * Send Email
		 */
		String message = "Hello,\n\n<br><br>" + "To appear for the test (" + userOtp.getTestName()
				+ ") - use following OTP - \n<br>\n<br>" + "<b><h3>OTP - " + userOtp2.getOtp() + "</h3></b>\n<br><br>"
				+ "Thanks and Regards\n<br>" + "System Admin - E-assess\n<br>" + "AL";

		EmailGenericMessageThread runnable = new EmailGenericMessageThread(userOtp.getUser(),
				"E-assessSHA - Use this to appear for the test - " + userOtp.getTestName(), message, propertyConfig);
		Thread th = new Thread(runnable);
		th.start();
		return "success";
	}

	@RequestMapping(value = "/getotp", method = RequestMethod.GET)
	public @ResponseBody String getotp(@RequestParam String email, @RequestParam String companyName,
			HttpServletRequest request, HttpServletResponse response) {
		companyName = companyName.trim();
		Company company = companyService.findByCompanyName(companyName);
		if (company == null) {
			return "Company Does not Exist";
		}

		UserOtp userOtp = userOtpService.getOtpForUser(email, company.getCompanyId());
		if (userOtp == null) {
			return "User Does not Exist";
		}
		/**
		 * Send Email
		 */
		String message = "Hello " + userOtp.getFirstName() + ",\n\n<br><br>"
				+ "To re-generate your password - use following OTP - \n<br>\n<br>" + "<b><h3>OTP - " + userOtp.getOtp()
				+ "</h3></b>\n<br><br>" + "Thanks and Regards\n<br>" + "System Admin - E-assess\n<br>" + "AL";

		EmailGenericMessageThread runnable = new EmailGenericMessageThread(email,
				"E-assessHA - OTP for Password Regeneration", message, propertyConfig);
		Thread th = new Thread(runnable);
		th.start();
		return "success";
	}

	@RequestMapping(value = "/validateotpfortest", method = RequestMethod.GET)
	public @ResponseBody String validateotpfortest(@RequestParam String test, @RequestParam String otp,
			@RequestParam String email, @RequestParam String companyId, HttpServletRequest request,
			HttpServletResponse response) {
		UserOtp userOtp = userOtpService.findExistingUserOtpforTest(email, companyId, test);
		if (userOtp == null) {
			return "failure";
		}

		if (!userOtp.getOtp().equals(otp)) {
			return "failure";
		}
		return "success";
	}

	@RequestMapping(value = "/validateotp", method = RequestMethod.GET)
	public @ResponseBody String validateotp(@RequestParam String otp, @RequestParam String email,
			@RequestParam String companyName, HttpServletRequest request, HttpServletResponse response) {
		companyName = companyName.trim();
		Company company = companyService.findByCompanyName(companyName);
		if (company == null) {
			return "Company Does not Exist";
		}

		UserOtp userOtp = userOtpService.findExistingUserOtp(email, company.getCompanyId());
		if (userOtp == null) {
			return "failure";
		}

		if (!userOtp.getOtp().equals(otp)) {
			return "failure";
		}
		return "success";
	}

	@RequestMapping(value = "/savenewpassword", method = RequestMethod.GET)
	public @ResponseBody String savenewpassword(@RequestParam String password, @RequestParam String email,
			@RequestParam String companyName, HttpServletRequest request, HttpServletResponse response) {
		companyName = companyName.trim();
		Company company = companyService.findByCompanyName(companyName);
		if (company == null) {
			return "Company Does not Exist";
		}

		User user = userService.findByPrimaryKey(email, company.getCompanyId());
		if (user == null) {
			return "failure";
		} else {
			user.setPassword(password);
			userService.saveOrUpdate(user);
		}
		return "success";
	}

	@RequestMapping(value = "/no_content", method = RequestMethod.GET)
	public ModelAndView no_content(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView("no_content");
		return mav;
	}

	// no_content
}
