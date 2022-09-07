package com.assessment.web.controllers;

import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.assessment.common.CommonUtil;
import com.assessment.common.ExcelReader;
import com.assessment.common.Qualifiers;
import com.assessment.common.util.NavigationConstants;
import com.assessment.data.CandidateProfileParams;
import com.assessment.data.Company;
import com.assessment.data.License;
import com.assessment.data.Question;
import com.assessment.data.QuestionMapperInstance;
import com.assessment.data.Skill;
import com.assessment.data.TestLinkTime;
import com.assessment.data.User;
import com.assessment.data.UserType;
import com.assessment.repositories.CandidateProfileParamsRepository;
import com.assessment.repositories.LicenseRepository;
import com.assessment.repositories.QuestionMapperInstanceRepository;
import com.assessment.repositories.QuestionRepository;
import com.assessment.repositories.TestLinkTimeRepository;
import com.assessment.repositories.UserRepository;
import com.assessment.services.CandidateProfileParamsService;
import com.assessment.services.CompanyService;
import com.assessment.services.LicenseService;
import com.assessment.services.TestLinkTimeService;
import com.assessment.services.TestService;
import com.assessment.services.UserService;
import com.assessment.web.dto.VerificationResultsQ;

@Controller
public class MiscellaneousController {

	@Autowired
	LicenseService licenseService;

	@Autowired
	QuestionMapperInstanceRepository questionMapperInstanceRepository;
	
	@Autowired
	TestLinkTimeService testLinkService;
	
	@Autowired
	TestService testService;
	
	@Autowired
	QuestionRepository questionRepository;
	
	@Autowired
	CandidateProfileParamsService profileService;
	
	@Autowired
	CandidateProfileParamsRepository paramsRepository;
	
	@Autowired
	TestLinkTimeRepository testLinkTimeRepository;
	
	@Autowired
	LicenseRepository licenseRepository;
	
	@Autowired
	UserService userService;
	
	@Autowired
	CompanyService companyService;
	
	@Autowired
	UserRepository userRepository;
	
	Logger logger =LoggerFactory.getLogger(QuestionController.class);
	
	@GetMapping("/newDomainReport")
	public ModelAndView filterreport(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("newDomainReport");
	
		return mav;
	}
	
	@RequestMapping(value = "/newLicenses", method = RequestMethod.GET)
	public ModelAndView newLicenses(String msgType, String icon, String msg, @RequestParam(name= "page", required = false) Integer pageNumber, HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) throws Exception {
		ModelAndView mav = new ModelAndView("newLicenses");
		
		if(pageNumber == null) {
			pageNumber = 0;
		}
		License license = new License(); 
		User user = (User) request.getSession().getAttribute("user");
		Page<License> licenses = licenseService.getLicenses(user.getCompanyId(), PageRequest.of(pageNumber, NavigationConstants.NO_LICENSES_PAGE));
		mav.addObject("licenses", licenses.getContent());
		CommonUtil.setCommonAttributesOfPagination(licenses, modelMap, pageNumber, "licenses", null);
			if(msgType != null && msg != null){
				mav.addObject("message", msg);// later put it as label
				mav.addObject("msgtype", msgType);
				mav.addObject("icon", icon);
			}
			mav.addObject("license", license);
		return mav;
	}
	
	@RequestMapping(value = "/newSavelicense", method = RequestMethod.POST)
	public ModelAndView newSavelicense(@ModelAttribute("license") License license,  HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) throws Exception {
		User user = (User) request.getSession().getAttribute("user");
			if(license.getId() == null){
				//check if name is distinct and one of saved
				License lic = licenseService.findByPrimaryKey(license.getLicenseName(), user.getCompanyId());
				if(lic != null){
					return newLicenses("Information" ,"warning","License "+license.getLicenseName()+" can not be saved. Use a different license name. ", 0, request, response, modelMap);
				}
			}
		license.setCompanyId(user.getCompanyId());
		license.setCompanyName(user.getCompanyName());
		licenseService.saveOrUpdate(license);
		return newLicenses("Information","success", "License "+license.getLicenseName()+" saved.", 0, request, response, modelMap);
	}
	
	@RequestMapping(value = "/searchLicenses", method = RequestMethod.GET)
	public ModelAndView searchLicenses(@RequestParam(name= "page", required = false) Integer pageNumber,@RequestParam String searchText, HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView("newLicenses");
		if(pageNumber == null) {
			pageNumber = 0;
		}
		License license = new License(); 
		User user = (User) request.getSession().getAttribute("user");
		Page<License> licenses = licenseRepository.searchLicenses(user.getCompanyId(), searchText,PageRequest.of(pageNumber, NavigationConstants.NO_LICENSES_PAGE));
		mav.addObject("licenses", licenses.getContent());
		CommonUtil.setCommonAttributesOfPagination(licenses, mav.getModelMap(), pageNumber, "searchLicenses", null);
		mav.addObject("license", license);
		return mav;
	}
	
	@RequestMapping(value = "/editLicense", method = RequestMethod.GET)
	@ResponseBody
	public Map<String,Object> editLicense(@RequestParam(name= "id", required = false) Long id, HttpServletRequest request ){
		Map<String,Object> map = new HashMap<>();
		License license = licenseRepository.findById(id).get();
		map.put("license", license);
		return map;
	}
	
	@RequestMapping(value = "/newSingleFileSessions", method = RequestMethod.GET)
	public ModelAndView newSingleFileSessions(@RequestParam(name= "page", required = false) Integer pageNumber, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mav = new ModelAndView("newSingle_file_results");
		User user = (User) request.getSession().getAttribute("user");
		if(pageNumber == null) {
			pageNumber = 0;
		}
		Page<QuestionMapperInstance> questionMapperInstances = questionMapperInstanceRepository.findCodingQuestionMapperInstances(user.getCompanyId(), PageRequest.of(pageNumber, NavigationConstants.NO_SIGLEFILE_PAGE));
		
  		mav.addObject("answers", questionMapperInstances.getContent());
		CommonUtil.setCommonAttributesOfPagination(questionMapperInstances, mav.getModelMap(), pageNumber, "newSingleFileSessions", null);
		return mav;
	}
	
	@RequestMapping(value = "/searchSingleFileSessions", method = RequestMethod.GET)
	public ModelAndView searchSingleFileSessions(@RequestParam(name= "page", required = false) Integer pageNumber,@RequestParam String searchText, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mav = new ModelAndView("newSingle_file_results");
		User user = (User) request.getSession().getAttribute("user");
		if(pageNumber == null) {
			pageNumber = 0;
		}
		Page<QuestionMapperInstance> questionMapperInstances = questionMapperInstanceRepository.searchCodingQuestionMapperInstances(user.getCompanyId(), searchText,PageRequest.of(pageNumber, NavigationConstants.NO_SIGLEFILE_PAGE));
		
  		mav.addObject("answers", questionMapperInstances.getContent());
		CommonUtil.setCommonAttributesOfPagination(questionMapperInstances, mav.getModelMap(), pageNumber, "searchSingleFileSessions", null);
		return mav;
	}
	
	@RequestMapping(value = "/newListTestLinks", method = RequestMethod.GET)
	public ModelAndView newListTenants(@RequestParam(name= "page", required = false) Integer pageNumber, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mav = new ModelAndView("newList_link");
		User user = (User) request.getSession().getAttribute("user");
		if(pageNumber == null) {
			pageNumber = 0;
		}
		List<String> tests = testService.getTestNames(user.getCompanyId());
		mav.addObject("tests", tests);
		TestLinkTime testLinkTime=new TestLinkTime();
		mav.addObject("link", testLinkTime);
		Page<TestLinkTime> testlinks = testLinkService.findAllLinks(PageRequest.of(pageNumber, NavigationConstants.NO_TESTLINK_PAGE));
		mav.addObject("links", testlinks.getContent());
		CommonUtil.setCommonAttributesOfPagination(testlinks, mav.getModelMap(), pageNumber, "newListTestLinks", null);
		return mav;
	}
	
	@RequestMapping(value = "/newSaveTestLink", method = RequestMethod.POST)
	public ModelAndView newSaveTestLink(HttpServletRequest request, HttpServletResponse response, @ModelAttribute("link") TestLinkTime link) throws Exception {
		ModelAndView mav = new ModelAndView("newList_link");
		User user = (User) request.getSession().getAttribute("user");
		
			link.setCompanyId(user.getCompanyId());
			link.setCompanyName(user.getCompanyName());
			com.assessment.data.Test test = testService.findbyTest(link.getTestName(), user.getCompanyId());
			link.setTestId(test.getId());
		testLinkService.saveOrUpdate(link);
		mav.addObject("message", "Public Test Link for test - "+link.getTestName()+"!!!");// later put it as label
		mav.addObject("msgtype", "success");
		mav.addObject("icon", "success");
		Integer pageNumber = 0;
		List<String> tests = testService.getTestNames(user.getCompanyId());
		mav.addObject("tests", tests);
		Page<TestLinkTime> testlinks = testLinkService.findAllLinks(PageRequest.of(pageNumber, 15));
		mav.addObject("links", testlinks.getContent());
		CommonUtil.setCommonAttributesOfPagination(testlinks, mav.getModelMap(), pageNumber, "newListTestLinks", null);
		return mav;
	}
	
	@RequestMapping(value = "/searchTestLinks", method = RequestMethod.GET)
	public ModelAndView searchTestLinks(@RequestParam(name= "page", required = false) Integer pageNumber, @RequestParam String searchText,HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mav = new ModelAndView("newList_link");
		User user = (User) request.getSession().getAttribute("user");
		if(pageNumber == null) {
			pageNumber = 0;
		}
		List<String> tests = testService.getTestNames(user.getCompanyId());
		mav.addObject("tests", tests);
		TestLinkTime testLinkTime=new TestLinkTime();
		mav.addObject("link", testLinkTime);
		Page<TestLinkTime> testlinks =testLinkTimeRepository.findTestLink(user.getCompanyId(), searchText, PageRequest.of(pageNumber, NavigationConstants.NO_TESTLINK_PAGE)); 
		mav.addObject("links", testlinks.getContent());
		CommonUtil.setCommonAttributesOfPagination(testlinks, mav.getModelMap(), pageNumber, "searchTestLinks", null);
		return mav;
	}
	
	@RequestMapping(value = "/getLinkById", method = RequestMethod.GET)
	@ResponseBody
	public Map<String,Object> getLinkById(@RequestParam(name= "id", required = false) Long id, HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String,Object> map = new HashMap<>();
		 TestLinkTime linkTime = testLinkTimeRepository.findById(id).get();
		 map.put("link", linkTime);
		return map;
	}
	
	
	@RequestMapping(value = "/recomms", method = RequestMethod.GET)
	public ModelAndView recomms(HttpServletRequest request, HttpServletResponse response,@RequestParam(name= "character", required = false) String character,@RequestParam(name= "msg", required = false) String msg ) throws Exception {
		ModelAndView mav = new ModelAndView("recomms");
		User user = (User) request.getSession().getAttribute("user");
		char c;
		List<String> characters = new ArrayList<>();
		  for(c = 'A'; c <= 'Z'; ++c) {
		            System.out.print(c + " ");
		            characters.add(Character.toString(c));
		  }
		  mav.addObject("characters", characters);
		  if(character==null) {
			  character="A";
		  }
		  if(msg!=null) {
			 mav.addObject("message", msg);// later put it as label
			mav.addObject("msgtype", "Information");
			mav.addObject("icon", "success");
		  }
		  List<CandidateProfileParams> params =  paramsRepository.findCandidateProfileParamsByCharacter(user.getCompanyId(), character);
		  mav.addObject("params", params);
		Set<Qualifiers> qualifiers = questionRepository.getAllUniqueQualifiers(user.getCompanyId());
		mav.addObject("selected", character);
		mav.addObject("params2", new CandidateProfileParams());
		mav.addObject("qualifiers", qualifiers);
		return mav;
	}
	
	@RequestMapping(value = "/getProfileParams", method = RequestMethod.GET)
	@ResponseBody
	public Map<String,Object> getProfileParams(HttpServletRequest request, HttpServletResponse response,@RequestParam(name= "qual", required = false) String qual ) throws Exception {
		Map<String,Object> map = new HashMap<>();
		User user = (User) request.getSession().getAttribute("user");
		if(qual != null){
			String qls[] = qual.split("-");
			CandidateProfileParams params = profileService.findUniqueCandidateProfileParams(user.getCompanyId(), qls[0], qls[1], qls[2], qls[3], qls[4]);
			if(params == null){
				params = new CandidateProfileParams(qls[0], qls[1], qls[2], qls[3], qls[4]);
			}
			params.setContext(qual);
			map.put("params3", params);
		}
		return map;
	}
	
	@RequestMapping(value = "/newSaveProfileParams", method = RequestMethod.POST)
	    public ModelAndView  newSaveProfileParams(@ModelAttribute("params") CandidateProfileParams params, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mav = new ModelAndView("recomm");
		 User user = (User) request.getSession().getAttribute("user");
		 params.setCompanyId(user.getCompanyId());
		 params.setCompanyName(user.getCompanyName());
		 profileService.saveOrUpdate(params);
		 String character = "A";
		 String msg="Save Success";
	 	 return recomms(request, response, character,msg);
	    }
	
	 @RequestMapping(value = "/newLmsAdmins", method = RequestMethod.GET)
	  public ModelAndView newLmsAdmins(@RequestParam(name= "page", required = false) Integer pageNumber, HttpServletResponse response, HttpServletRequest request, ModelMap modelMap ) throws Exception {
		 User user = (User) request.getSession().getAttribute("user");
		 if(pageNumber == null) {
				pageNumber = 0;
			}
		 Page<User> users = userService.findUsersByTypeAndCompany(user.getCompanyId(), UserType.LMS_ADMIN.getType(), PageRequest.of(pageNumber, NavigationConstants.NO_LMSADMIN_PAGE));
		 ModelAndView mav = new ModelAndView("newLmsAdmins");
		 User usr = new User();
		 mav.addObject("usr", usr);
		 List<String> licenses = licenseService.getLicensesInString(user.getCompanyId());
		 mav.addObject("licenses", licenses);
		 mav.addObject("users", users.getContent());
		 CommonUtil.setCommonAttributesOfPagination(users, modelMap, pageNumber, "newLmsAdmins", null);
		 return mav;
		}
	 
	 @RequestMapping(value = "/newLmsAdminSearch", method = RequestMethod.GET)
	  public ModelAndView newLmsAdminSearch(@RequestParam(name= "page", required = false) Integer pageNumber,@RequestParam String searchText, HttpServletResponse response, HttpServletRequest request ) throws Exception {
		 User user = (User) request.getSession().getAttribute("user");
		 if(pageNumber == null){
			 pageNumber = 0;
		 }
//		 Page<User> users = userRepository.searchUsers(user.getCompanyId(), searchText,PageRequest.of(pageNumber, NavigationConstants.NO_USERS_PAGE));
		 Page<User> users = userRepository.searchLMSAdmins(user.getCompanyId(), searchText,PageRequest.of(pageNumber, NavigationConstants.NO_LMSADMIN_PAGE));
		 ModelAndView mav = new ModelAndView("newLmsAdmins");
		 List<String> licenses = licenseService.getLicensesInString(user.getCompanyId());
		 mav.addObject("licenses", licenses);
		 
		 mav.addObject("users", users.getContent());
		 User usr = new User();
		 usr.setCompanyId(user.getCompanyId());
		 usr.setCompanyName(user.getCompanyName());
		 mav.addObject("usr", usr);
		 Map<String, String> queryParams = new HashMap<>();
		queryParams.put("searchText", searchText);
		 CommonUtil.setCommonAttributesOfPagination(users, mav.getModelMap(), pageNumber, "newLmsAdminSearch", queryParams);
		 return mav;
		}
	 
	 @RequestMapping(value = "/newSavelmsadmin", method = RequestMethod.POST)
	  public ModelAndView newSavelmsadmin(@RequestParam(name= "page", required = false) Integer pageNumber, HttpServletRequest request, HttpServletResponse response,@ModelAttribute("usr") User usr, ModelMap modelMap) {
		 User user = (User) request.getSession().getAttribute("user");
		 usr.setCompanyId(user.getCompanyId());
		 usr.setCompanyName(user.getCompanyName());
		 usr.setUserType(UserType.LMS_ADMIN);
		 userService.saveOrUpdate(usr);
		 if(pageNumber == null){
			 pageNumber = 0;
		 }
		 Page<User> users = userService.findUsersByTypeAndCompany(user.getCompanyId(), UserType.LMS_ADMIN.getType(), PageRequest.of(pageNumber, NavigationConstants.NO_LMSADMIN_PAGE));
		 ModelAndView mav = new ModelAndView("newLmsAdmins");
		 List<String> licenses = licenseService.getLicensesInString(user.getCompanyId());
		 mav.addObject("licenses", licenses);
		 mav.addObject("users", users.getContent());
		 mav.addObject("message", "Lms Admin "+usr.getEmail()+" Saved");// later put it as label
		 mav.addObject("msgtype", "Information");
		 mav.addObject("icon", "success");
		 CommonUtil.setCommonAttributesOfPagination(users, modelMap, pageNumber, "newLmsAdmins", null);
		 return mav;
	 }
	 
	 @RequestMapping(value = "/editLMSAdmin", method = RequestMethod.GET)
	 @ResponseBody
	  public Map<String,Object> editLMSAdmin(HttpServletResponse response, HttpServletRequest request,@RequestParam String email ) throws Exception {
		 Map<String,Object> map = new HashMap<>();
		 User user = (User) request.getSession().getAttribute("user");
		 User user2=userService.findByPrimaryKey(email, user.getCompanyId());	
		map.put("user2", user2);
		 return map;
		}
	 
	 
	 @RequestMapping(value = "/newVerification", method = RequestMethod.GET)
		public ModelAndView newVerification(@RequestParam(name= "page", required = false) Integer pageNumber, HttpServletResponse response, HttpServletRequest request, ModelMap modelMap) throws Exception {
			ModelAndView mav = new ModelAndView("newVerifydata");
			
			return mav;
		}
	 
	 @RequestMapping(value = "/newVerifydata", method = RequestMethod.POST)
		public ModelAndView newVerifydata(HttpServletResponse response, MultipartHttpServletRequest request) throws Exception {
			ModelAndView mav = new ModelAndView("newVerifyresults");
			try {
				MultipartFile multipartFile = request.getFile("fileToUpload");
				Long size = multipartFile.getSize();
				String fileName = multipartFile.getName();
				String contentType = multipartFile.getContentType();
				InputStream stream = multipartFile.getInputStream();
				File file = ResourceUtils.getFile("classpath:questions.xml");
//				File file = new File("questions.xml");
				List<Question> questions = ExcelReader.parseExcelFileToBeans(stream, file);
				List<VerificationResultsQ> vers = new ArrayList<>();
					for(Question q : questions){
						Company c = companyService.findByCompanyId(q.getCompanyId());
						String problem = "";
							if(c == null){
								problem += "Invalid Company Id "+q.getCompanyId()+".";
							}
							
							if(q.getRightChoices() == null){
								problem += " No Correct Answer selected.";
							}
							
							if(!(q.getRightChoices().contains("Choice 1") || q.getRightChoices().contains("Choice 2") || q.getRightChoices().contains("Choice 3") || q.getRightChoices().contains("Choice 4") || q.getRightChoices().contains("Choice 5") || q.getRightChoices().contains("Choice 6"))){
								problem += " Invalid Correct Choice "+q.getRightChoices();
							}
						if(problem.length() > 0){
							VerificationResultsQ ver = new VerificationResultsQ();
							ver.setQuestionText(q.getQuestionText());
							ver.setQuestionProblem(problem);
							vers.add(ver);
						}
						
					}
				mav.addObject("results", vers);
				logger.info("verifydata - verification complete");
				return mav;
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				logger.error("problem verifydata - verification", e);
				mav.addObject("problem", "Problem in File Upload. Contact Admin");
				//throw new AssessmentGenericException("problem in uploading qs", e);
				return mav;
			}
		}
}
