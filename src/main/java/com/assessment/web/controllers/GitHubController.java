package com.assessment.web.controllers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.assessment.common.CommonUtil;
import com.assessment.common.PropertyConfig;
import com.assessment.data.QuestionMapperInstance;
import com.assessment.data.User;
import com.assessment.repositories.QuestionMapperInstanceRepository;
import com.assessment.services.GithubService;
import com.assessment.services.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ibm.icu.text.DateFormat;

@Controller
public class GitHubController {

	
	Logger logger = LoggerFactory.getLogger(GitHubController.class);
	
	@Autowired
	GithubService gitService;
	
	@Autowired
	QuestionMapperInstanceRepository questionMapperInstanceRp;
	
	@Autowired
	UserService userService;
	
	@Autowired
	PropertyConfig propertyConfig;
	
	@RequestMapping(value = "/publicRepositories", method = RequestMethod.GET)
	public @ResponseBody List<String> fetchPublicRepositories(@RequestParam(required = true, name = "account") String account,  HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) throws Exception {
		List<String> ret = gitService.fetchGithubPublicRepos(account);
		
		return ret;
	}
	
	@RequestMapping(value = "/saveGitUrl", method = RequestMethod.GET)
	public @ResponseBody String saveGitUrl(@RequestParam(required = true, name = "instanceId") Long instanceId,@RequestParam(required = true, name = "githubUrl") String githubUrl,  HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) throws Exception {
		QuestionMapperInstance instance = questionMapperInstanceRp.findById(instanceId).get();
		githubUrl = URLDecoder.decode(githubUrl); 
		instance.setGithubUrlOfUser(githubUrl);
		instance.setProblemInLocalFullstack(true);
		questionMapperInstanceRp.save(instance);
		return "ok";
	}
	
	
	@RequestMapping(value = "/github", method = RequestMethod.GET)
	public ModelAndView github(@RequestParam(required = false, name = "code") String code, @RequestParam(required = false, name = "state") String state, HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) throws Exception {
		ModelAndView mav = new ModelAndView("github");
		System.out.println("code is "+code);
		String url = "https://github.com/login/oauth/access_token?client_id=Iv1.eb48e5a9cf3cc745&client_secret=d317fc19343bf94c9803a5ded667b0fc9e539667&code="+code;
		URL url2 = new URL(url);
		HttpsURLConnection conn = (HttpsURLConnection) url2.openConnection();
		conn.setRequestMethod("POST");
		conn.setRequestProperty("Accept", "application/json");
		conn.setDoOutput(true);
		conn.getOutputStream().write(new String().getBytes());
		String data = getResponse(conn);
	    ObjectMapper mapper = new ObjectMapper();
	    GitHubToken token = mapper.readValue(data, GitHubToken.class);
	    System.out.println("access token "+token.getAccess_token());
	    System.out.println("Scope "+token.getScope());
	    System.out.println("toekn type "+token.getToken_type());
		return mav;
	} 
	
	@RequestMapping(value = "/githublogin", method = RequestMethod.GET)
	public ModelAndView githublogin(@RequestParam(required = false, name = "code") String code, @RequestParam(required = false, name = "state") String state, HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) throws Exception {
		ModelAndView mav = new ModelAndView("githublogin");
		return mav;
	} 
	
	public String getResponse(HttpsURLConnection con) {
		if(con!=null){
			
			try {
				
			   BufferedReader br = 
				new BufferedReader(
					new InputStreamReader(con.getInputStream()));
						
			   String input;
			   String output="";
						
			   while ((input = br.readLine()) != null){
				   output +=input;
			   }
			   br.close();
			   return output;
						
			} catch (IOException e) {
			   e.printStackTrace();
			}
					
		       }
				
		   return null;
	}
	
	
	@RequestMapping(value = "/showAllResultsforMFALocal", method = RequestMethod.GET)
	public ModelAndView showAllResultsforMFALocal(@RequestParam(name= "page", required = false) Integer pageNumber, HttpServletRequest request, HttpServletResponse response) {
	 User user = (User) request.getSession().getAttribute("user");
	 
	 if(pageNumber == null) {
			pageNumber = 0;
		}
	 
	 
	 Page<QuestionMapperInstance> instances = questionMapperInstanceRp.findAllFullStackLocalQuestionMapperInstances(user.getCompanyId(), PageRequest.of(pageNumber, 20));
	 
	// List<QuestionMapperInstance> instances = questionMapperInstanceService.findAllFullStackQuestionMapperInstances(user.getCompanyId());
	  for(QuestionMapperInstance instance : instances){
		  User user2 = userService.findByPrimaryKey(instance.getUser(), user.getCompanyId());
		  instance.setUerFullName(user2.getFirstName()+" "+user2.getLastName());
		  instance.setLastDate(DateFormat.getDateTimeInstance().format(instance.getUpdateDate() == null?instance.getCreateDate():instance.getUpdateDate()));
	  }
	 
	 ModelAndView mav = new ModelAndView("fullStack_local_Results");
	  mav.addObject("instances", instances.getContent());
	  Map<String, String> params = new HashMap<>();
	  //params.put("qualifier1", qualifier1);
	  CommonUtil.setCommonAttributesOfPagination(instances, mav.getModelMap(), pageNumber, "showAllResultsforMFA", params);
	  return mav;

	 // return mav;
	}
	
	@RequestMapping(value = "/showLocalMultifileRecordsWithProblem", method = RequestMethod.GET)
	public ModelAndView showLocalMultifileRecordsWithProblem(@RequestParam(name= "page", required = false) Integer pageNumber, HttpServletRequest request, HttpServletResponse response) {
	 User user = (User) request.getSession().getAttribute("user");
	 
	 if(pageNumber == null) {
			pageNumber = 0;
		}
	 
	 
	 Page<QuestionMapperInstance> instances = questionMapperInstanceRp.findAllFullStackLocalQuestionMapperInstancesWithProblem(user.getCompanyId(), PageRequest.of(pageNumber, 20));
	 
	// List<QuestionMapperInstance> instances = questionMapperInstanceService.findAllFullStackQuestionMapperInstances(user.getCompanyId());
	  for(QuestionMapperInstance instance : instances){
		  User user2 = userService.findByPrimaryKey(instance.getUser(), user.getCompanyId());
		  instance.setUerFullName(user2.getFirstName()+" "+user2.getLastName());
		  instance.setLastDate(DateFormat.getDateTimeInstance().format(instance.getUpdateDate() == null?instance.getCreateDate():instance.getUpdateDate()));
	  }
	 
	 ModelAndView mav = new ModelAndView("fullStack_local_problem");
	  mav.addObject("instances", instances.getContent());
	  Map<String, String> params = new HashMap<>();
	  //params.put("qualifier1", qualifier1);
	  CommonUtil.setCommonAttributesOfPagination(instances, mav.getModelMap(), pageNumber, "showAllResultsforMFA", params);
	  return mav;

	 // return mav;
	}
	
	
	@RequestMapping(value = "/findAllFullStackLocalQuestionMapperInstancesWithDuplicateRecords", method = RequestMethod.GET)
	public ModelAndView findAllFullStackLocalQuestionMapperInstancesWithDuplicateRecords(@RequestParam(name= "page", required = false) Integer pageNumber, HttpServletRequest request, HttpServletResponse response) {
	 User user = (User) request.getSession().getAttribute("user");
	 
	 
	 
	 List<String> duplicateGits = questionMapperInstanceRp.findAllFullStackLocalQuestionMapperInstancesWithDuplicateRecords(user.getCompanyId());
	 List<QuestionMapperInstance> dupRecords = new ArrayList<QuestionMapperInstance>();	
	 	for(String dup : duplicateGits){
	 		List<QuestionMapperInstance>  ins = questionMapperInstanceRp.findLocalFullStackQuestionMapperInstancesWithGitUrl(user.getCompanyId(), dup);
	 		dupRecords.addAll(ins);
	 	}
	 
	// List<QuestionMapperInstance> instances = questionMapperInstanceService.findAllFullStackQuestionMapperInstances(user.getCompanyId());
	  for(QuestionMapperInstance instance : dupRecords){
		  User user2 = userService.findByPrimaryKey(instance.getUser(), user.getCompanyId());
		  instance.setUerFullName(user2.getFirstName()+" "+user2.getLastName());
		  instance.setLastDate(DateFormat.getDateTimeInstance().format(instance.getUpdateDate() == null?instance.getCreateDate():instance.getUpdateDate()));
	  }
	 
	 ModelAndView mav = new ModelAndView("fullStack_local_duplicate_git");
	  mav.addObject("instances", dupRecords);
	  return mav;

	 // return mav;
	}
	
	@RequestMapping(value = "/multiFileResultsLocal", method = RequestMethod.GET)
	public ModelAndView multiFileResults(@RequestParam String fullname, @RequestParam Long instanceId, HttpServletRequest request, HttpServletResponse response) throws IOException {
	  QuestionMapperInstance instance = questionMapperInstanceRp.findById(instanceId).get();
	  ModelAndView mav = new ModelAndView("multiFileResults");
	  
	  mav.addObject("testGiver", URLDecoder.decode(fullname));
	  mav.addObject("problemStatement", instance.getQuestionMapper().getQuestion().getQuestionText());
	  mav.addObject("testName", instance.getTestName());
	  String repo = instance.getGitRepositoryUI();
	  String rep[] = repo.split("###");
	  String first = rep[0].substring(rep[0].indexOf("/")+1, rep[0].length()).trim();
	  String last = rep[1].trim();
	  String sonarid = first+"-"+last;
	  
	  String url = propertyConfig.getCodeQualityServerLink()+URLEncoder.encode(sonarid);
	  System.out.println("sonarqube url is "+url);
	  mav.addObject("codeQuality", url);
	  mav.addObject("usageLink", instance.getUsageDocumentUrl());
	  mav.addObject("noOfTestCases", instance.getNoOfTestCases() == null?0:instance.getNoOfTestCases());
	  mav.addObject("noOfTestCasesPassed", instance.getNoOfTestCasesPassed() == null?0:instance.getNoOfTestCasesPassed()  );
	  mav.addObject("noOfFunctionalTestCases", instance.getFunctionalTestCases() == null?0: instance.getFunctionalTestCases());
	  mav.addObject("noOfFunctionalTestCasesPassed", instance.getFunctionalTestCasesPassed()==null?0: instance.getFunctionalTestCasesPassed());
	  
	  mav.addObject("noOfBoundaryTestCases", instance.getBoundaryTestCases()==null?0:instance.getBoundaryTestCases() );
	  mav.addObject("noOfBoundaryTestCasesPassed", instance.getBoundaryTestCasesPassed()==null?0:instance.getBoundaryTestCasesPassed() );
	  
	  mav.addObject("noOfExceptionTestCases", instance.getExceptionTestCases()==null?0:instance.getExceptionTestCases() );
	  mav.addObject("noOfExceptionTestCasesPassed", instance.getExceptionTestCasesPassed()==null?0:instance.getExceptionTestCasesPassed() );
	  
	  
	  return mav;
	}
}

class GitHubToken{
	
	String access_token;
	
	String scope;
	
	String token_type;

	public String getAccess_token() {
		return access_token;
	}

	public void setAccess_token(String access_token) {
		this.access_token = access_token;
	}

	public String getScope() {
		return scope;
	}

	public void setScope(String scope) {
		this.scope = scope;
	}

	public String getToken_type() {
		return token_type;
	}

	public void setToken_type(String token_type) {
		this.token_type = token_type;
	}
	
	
	
}
