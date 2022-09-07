package com.assessment.web.controllers;

import java.io.File;
import java.io.InputStream;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.assessment.common.CommonUtil;
import com.assessment.common.ExcelReader;
import com.assessment.common.PropertyConfig;
import com.assessment.data.Company;
import com.assessment.data.Question;
import com.assessment.data.Swaal;
import com.assessment.data.Tenant;
import com.assessment.data.Test;
import com.assessment.data.TestLinkTime;
import com.assessment.data.User;
import com.assessment.data.UserTestSession;
import com.assessment.repositories.QuestionRepository;
import com.assessment.repositories.TestLinkTimeRepository;
import com.assessment.repositories.UserTestSessionRepository;
import com.assessment.services.CompanyService;
import com.assessment.services.QuestionService;
import com.assessment.services.TestLinkTimeService;
import com.assessment.services.TestService;
import com.assessment.services.UserService;
import com.assessment.web.dto.Qualifier;
import com.assessment.web.dto.TestLinkDTO;
import com.assessment.web.dto.TestUserData;

@Controller
public class TestLinkProController {
	@Autowired
	TestLinkTimeService testLinkService;

	@Autowired
	TestLinkTimeRepository testLinkRep;

	@Autowired
	TestService testService;
	@Autowired
	PropertyConfig propertyConfig;
	@Autowired
	TestLinkTimeRepository linkTimeRepository;

	@Autowired
	CompanyService companyService;
	@Autowired
	UserTestSessionRepository testSessionRepository;
	@Autowired
	UserService userService;

	@Autowired
	QuestionService questionService;
	
	@RequestMapping(value = "/gQualifier", method = RequestMethod.GET)
	public @ResponseBody String gQualifier(HttpServletRequest request, HttpServletResponse response) throws Exception {
		File file = ResourceUtils.getFile("classpath:qualifier.xlsx");
		InputStream stream = FileUtils.openInputStream(file);
		File f = ResourceUtils.getFile("classpath:qualifier.xml");
		System.out.println("processing excel file " + f.getName());
		List<Qualifier> records = ExcelReader.parseExcelFileToBeans(stream, f);
		System.out.println(".........");
		System.out.println("........." + records);
		int i = 0;
		for (Qualifier qualifier: records) {
			if(qualifier.getDisplayq1()!=null) {
				System.out.println("ssssssss");
				questionService.updateQuestion(qualifier.getDisplayq1(), qualifier.getParentGroupClassifier(), qualifier.getQualifier1());
				i++;
				System.out.println("Count: "+i);
			}else {
				System.out.println("/////////");
			}
		}
		System.out.println("Process Complete");
		return "OK";
	}

	@RequestMapping(value = "/listTestLinksPro", method = RequestMethod.GET)
	public ModelAndView listTenantsPro(@RequestParam(name = "page", required = false) Integer pageNumber,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mav = new ModelAndView("list_linkPro");
		if (pageNumber == null) {
			pageNumber = 0;
		}
		Page<TestLinkTime> testlinks = testLinkService.findAllLinks(PageRequest.of(pageNumber, 15));
		mav.addObject("links", testlinks.getContent());
		CommonUtil.setCommonAttributesOfPagination(testlinks, mav.getModelMap(), pageNumber, "listTestLinksPro", null);
		return mav;
	}

	@RequestMapping(value = "/addTestLinkPro", method = RequestMethod.GET)
	public ModelAndView addTestLinkPro(@RequestParam(name = "linkId", required = false) Long linkId,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		User user = (User) request.getSession().getAttribute("user");
		ModelAndView mav = new ModelAndView("add_linkPro");
		TestLinkTime testLinkTime;
		if (linkId == null) {
			testLinkTime = new TestLinkTime();
		} else {
			testLinkTime = testLinkRep.findById(linkId).get();
		}
		// List<TestLinkDTO> tests = testService.getAllTests(user.getCompanyId());
		List<String> tests = testService.getTestNames(user.getCompanyId());
		mav.addObject("tests", tests);
		mav.addObject("link", testLinkTime);
		return mav;
	}

	// deleteTestLink
	@RequestMapping(value = "/deleteTestLinkPro", method = RequestMethod.GET)
	public ModelAndView deleteTestLinkPro(@RequestParam(name = "linkId", required = true) Long linkId,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mav = new ModelAndView("list_linkPro");
		testLinkService.deleteTestLink(linkId);
		mav.addObject("message", "Delete Successful!!!");// later put it as label
		mav.addObject("msgtype", "success");
		Integer pageNumber = 0;
		Page<TestLinkTime> testlinks = testLinkService.findAllLinks(PageRequest.of(pageNumber, 15));
		mav.addObject("links", testlinks.getContent());
		CommonUtil.setCommonAttributesOfPagination(testlinks, mav.getModelMap(), pageNumber, "listTestLinksPro", null);
		return mav;
	}

	@RequestMapping(value = "/saveTestLinkPro", method = RequestMethod.POST)
	public ModelAndView saveTestLinkPro(HttpServletRequest request, HttpServletResponse response,
			@ModelAttribute("link") TestLinkTime link) throws Exception {
		ModelAndView mav = new ModelAndView("list_linkPro");
		User user = (User) request.getSession().getAttribute("user");

		link.setCompanyId(user.getCompanyId());
		link.setCompanyName(user.getCompanyName());
		com.assessment.data.Test test = testService.findbyTest(link.getTestName(), user.getCompanyId());
		link.setTestId(test.getId());
		testLinkService.saveOrUpdate(link);
		mav.addObject("icon", "success");
		mav.addObject("message", "Public Test Link for test - " + link.getTestName() + "!!!");// later put it as label
		mav.addObject("msgtype", "success");
		Integer pageNumber = 0;
		Page<TestLinkTime> testlinks = testLinkService.findAllLinks(PageRequest.of(pageNumber, 15));
		mav.addObject("links", testlinks.getContent());
		CommonUtil.setCommonAttributesOfPagination(testlinks, mav.getModelMap(), pageNumber, "listTestLinksPro", null);
		return mav;
	}

	@RequestMapping(value = "/publicTestPro", method = RequestMethod.GET)
	public ModelAndView showPublicTestPro(HttpServletRequest request, HttpServletResponse response) {
//		ModelAndView mav = new ModelAndView("publicTest3");
//		  ModelAndView mav = new ModelAndView("publicTest_new");
		ModelAndView mav = new ModelAndView("publicTest_newPro");
		User user = new User();
		TestUserData testUserData = new TestUserData();
		String testId = request.getParameter("testId");
		String companyId = request.getParameter("companyId");
		String inviteSent = request.getParameter("inviteSent");
		String startTime = request.getParameter("startTime");
		String endTime = request.getParameter("endTime");
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
		testUserData.setTestId(test.getId() + "");
		testUserData.setTestName(test.getTestName());
		request.getSession().setAttribute("user", user);
		request.getSession().setAttribute("dontCheckTimeValidity", dontCheckTimeValidity);
		mav.addObject("test", test);
		mav.addObject("testUserData", testUserData);
		mav.addObject("startTime", startTime);
		mav.addObject("endTime", endTime);

		return mav;
	}

	@RequestMapping(value = "/publicTest", method = RequestMethod.GET)
	public ModelAndView showPublicTest(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView("publicTest3");
//		  ModelAndView mav = new ModelAndView("publicTest_new");
//		  ModelAndView mav = new ModelAndView("publicTest_newPro");
		User user = new User();
		TestUserData testUserData = new TestUserData();
		String testId = request.getParameter("testId");
		String companyId = request.getParameter("companyId");
		String inviteSent = request.getParameter("inviteSent");
		String startTime = request.getParameter("startTime");
		String endTime = request.getParameter("endTime");
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
		testUserData.setTestId(test.getId() + "");
		testUserData.setTestName(test.getTestName());
		request.getSession().setAttribute("user", user);
		request.getSession().setAttribute("dontCheckTimeValidity", dontCheckTimeValidity);
		mav.addObject("test", test);
		mav.addObject("testUserData", testUserData);
		mav.addObject("startTime", startTime);
		mav.addObject("endTime", endTime);

		return mav;
	}

	@RequestMapping(value = "/publicTestAuthenticatePro", method = RequestMethod.POST)
	public ModelAndView publicTestAuthenticatePro(HttpServletRequest request, HttpServletResponse response,
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
			return showPublicTest(request, response);
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
		String url = "redirect:/startTestSession?userId=" + userId + "&companyId=" + companyId + "&testId="
				+ test.getId() + append + "&sharedDirect=yes&startDate="
				+ URLEncoder.encode(Base64.getEncoder().encodeToString(testUserData.getStartTime().getBytes()))
				+ "&endDate="
				+ URLEncoder.encode(Base64.getEncoder().encodeToString(testUserData.getEndTime().getBytes()));

		ModelAndView mav = new ModelAndView(url);
		return mav;
	}

	@RequestMapping(value = "/publicTestAuthenticate", method = RequestMethod.POST)
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
			return showPublicTest(request, response);
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
		String url = "redirect:/startTestSession?userId=" + userId + "&companyId=" + companyId + "&testId="
				+ test.getId() + append + "&sharedDirect=yes&startDate="
				+ URLEncoder.encode(Base64.getEncoder().encodeToString(testUserData.getStartTime().getBytes()))
				+ "&endDate="
				+ URLEncoder.encode(Base64.getEncoder().encodeToString(testUserData.getEndTime().getBytes()));

		ModelAndView mav = new ModelAndView(url);
		return mav;
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
}
