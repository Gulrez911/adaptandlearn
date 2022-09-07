package com.assessment.web.controllers;

import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.assessment.Exceptions.AssessmentGenericException;
import com.assessment.common.CommonUtil;
import com.assessment.common.PropertyConfig;
import com.assessment.common.util.EmailGenericMessageThread;
import com.assessment.common.util.NavigationConstants;
import com.assessment.data.Campaign;
import com.assessment.data.CampaignTest;
import com.assessment.data.CandidateCampaignSchedule;
import com.assessment.data.CandidateDetailsForJD;
import com.assessment.data.JobDescription;
import com.assessment.data.JobDescriptionRecruiter;
import com.assessment.data.RecruitCandidateProfile;
import com.assessment.data.User;
import com.assessment.data.UserType;
import com.assessment.repositories.CampaignRepository;
import com.assessment.repositories.CandidateCampaignScheduleRepository;
import com.assessment.repositories.CandidateDetailsForJDRepository;
import com.assessment.repositories.JobDescriptionRecruiterRepository;
import com.assessment.repositories.JobDescriptionRepository;
import com.assessment.repositories.RecruitCandidateProfileRepository;
import com.assessment.services.CampaignService;
import com.assessment.services.CandidateCampaignScheduleService;
import com.assessment.services.JobDescriptionParser;
import com.assessment.services.JobDescriptionRecruiterService;
import com.assessment.services.JobDescriptionService;
import com.assessment.services.RecruitCandidateProfileService;
import com.assessment.services.ResumeParsingService;
import com.assessment.services.UserService;
import com.assessment.web.dto.JobDescriptionParseResponseDto;
import com.assessment.web.dto.resumeparsing.ResumeParsingResponse;

@Controller
public class RecruitmentController {

	@Autowired
	UserService userService;
	@Autowired
	PropertyConfig propertyConfig;
	@Autowired
	JobDescriptionService descriptionService;
	@Autowired
	JobDescriptionRepository descriptionRepository;
	@Autowired
	JobDescriptionRecruiterRepository descriptionRecruiterRepository;
	@Autowired
	JobDescriptionRecruiterService descriptionRecruiterService;
	@Autowired
	CampaignService campaignService;
	@Autowired
	CampaignRepository campaignRepository;
	@Autowired
	RecruitCandidateProfileRepository recruitCandidateProfileRepository;
	@Autowired
	CandidateDetailsForJDRepository jdRepository;
//<<<<<<< HEAD
	@Autowired
	CandidateCampaignScheduleRepository campaignScheduleRepository;
	@Autowired
	CandidateCampaignScheduleService campaignScheduleService;
//=======

	@Autowired
	RecruitCandidateProfileService recruitCandidateProfileService;
	
	@Autowired
	ResumeParsingService parser;
	
	@Autowired
	JobDescriptionParser jdParser;

//>>>>>>> branch 'master' of https://github.com/Gulrez911/Eassess3.git

	@GetMapping("/recruiters")
	public ModelAndView recruitment(@RequestParam(name = "page", required = false) Integer pageNumber, HttpServletRequest request, HttpServletResponse response,
								ModelMap modelMap) {
		ModelAndView mav = new ModelAndView("recruiters");
		User user = (User) request.getSession().getAttribute("user");
		if (pageNumber == null) {
			pageNumber = 0;
		}
		Page<User> users = userService.findUsersByTypeAndCompany(user.getCompanyId(), UserType.RECRUITER.getType(),
									PageRequest.of(pageNumber, NavigationConstants.NO_RECRUITER_PAGE));
		mav.addObject("usr", new User());
		mav.addObject("users", users.getContent());
		CommonUtil.setCommonAttributesOfPagination(users, modelMap, pageNumber, "recruiters", null);
		return mav;
	}

	@PostMapping("/saveRecruiter")
	public ModelAndView saveRecruiter(@RequestParam(name = "page", required = false) Integer pageNumber, @ModelAttribute("usr") User usr, HttpServletRequest request,
								HttpServletResponse response, ModelMap modelMap) throws IOException {
		ModelAndView mav = new ModelAndView("recruiters");
		User user = (User) request.getSession().getAttribute("user");
		usr.setCompanyId(user.getCompanyId());
		usr.setCompanyName(user.getCompanyName());
		usr.setUserType(UserType.RECRUITER);
		userService.saveOrUpdateRecruiter(usr);
		String html = FileUtils.readFileToString(new File(propertyConfig.getSendCredentialsToStudent()));
		html = html.replace("{FULL_NAME}", usr.getFirstName() + " " + usr.getLastName());
		html = html.replace("{BASE_URL}", propertyConfig.getBaseUrl());
		html = html.replace("{USER}", usr.getEmail());
		html = html.replace("{PASSWORD}", usr.getPassword());
		EmailGenericMessageThread client = new EmailGenericMessageThread(usr.getEmail(), "Registration Credentials - " + propertyConfig.getBaseUrl(), html,
									propertyConfig);
		Thread thread = new Thread(client);
		thread.start();
		if (pageNumber == null) {
			pageNumber = 0;
		}
		Page<User> users = userService.findUsersByTypeAndCompany(user.getCompanyId(), UserType.RECRUITER.getType(),
									PageRequest.of(pageNumber, NavigationConstants.NO_RECRUITER_PAGE));
		mav.addObject("msgtype", "Information");
		mav.addObject("message", "Recruiter saved successfully");
		mav.addObject("icon", "success");
		mav.addObject("usr", usr);
		mav.addObject("users", users.getContent());
		CommonUtil.setCommonAttributesOfPagination(users, modelMap, pageNumber, "recruiters", null);
		return mav;
	}

	@RequestMapping(value = "/editRecruiter", method = RequestMethod.GET, produces = "text/plain;charset=UTF-8")
	@ResponseBody
	public Map<String, Object> editRecruiter(HttpServletResponse response, HttpServletRequest request, @RequestParam String email) throws Exception {
		Map<String, Object> map = new HashMap<>();
		User user = (User) request.getSession().getAttribute("user");
		User user2 = userService.findByPrimaryKey(email, user.getCompanyId());
		map.put("user2", user2);
		return map;
	}

	@RequestMapping(value = "/searchRecruiter", method = RequestMethod.GET)
	public ModelAndView searchRecruiter(@RequestParam(name = "page", required = false) Integer pageNumber, @RequestParam String searchText, HttpServletResponse response,
								HttpServletRequest request) throws Exception {
		User user = (User) request.getSession().getAttribute("user");
		if (pageNumber == null) {
			pageNumber = 0;
		}
		Page<User> users = userService.searchRecruiters(user.getCompanyId(), searchText, PageRequest.of(pageNumber, NavigationConstants.NO_RECRUITER_PAGE));
		ModelAndView mav = new ModelAndView("recruiters");
		mav.addObject("users", users.getContent());
		mav.addObject("usr", new User());
		Map<String, String> queryParams = new HashMap<>();
		queryParams.put("searchText", searchText);
		CommonUtil.setCommonAttributesOfPagination(users, mav.getModelMap(), pageNumber, "searchRecruiter", queryParams);
		return mav;
	}

	@RequestMapping(value = "/searchJobDescriptions", method = RequestMethod.GET)
	public ModelAndView searchJobDescriptions(@RequestParam(name = "page", required = false) Integer pageNumber, @RequestParam String searchText, HttpServletResponse response,
								HttpServletRequest request) throws Exception {
		User user = (User) request.getSession().getAttribute("user");
		if (pageNumber == null) {
			pageNumber = 0;
		}
		Page<JobDescription> descriptions = descriptionService.searchJobDescription(user.getCompanyId(), searchText,
									PageRequest.of(pageNumber, NavigationConstants.NO_JOBDESCRIPTION_PAGE));
		ModelAndView mav = new ModelAndView("jobDescription");
		mav.addObject("descriptions", descriptions.getContent());
		Map<String, String> queryParams = new HashMap<>();
		CommonUtil.setCommonAttributesOfPagination(descriptions, mav.getModelMap(), pageNumber, "searchJobDescriptions", queryParams);
		return mav;
	}

	@GetMapping("/jobDescriptions")
	public ModelAndView jobDescription(@RequestParam(name = "page", required = false) Integer pageNumber, @RequestParam(name = "msg", required = false) String msg,
								HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
		ModelAndView mav = new ModelAndView("jobDescription");
		User user = (User) request.getSession().getAttribute("user");
		if (msg != null) {
			mav.addObject("msgtype", "Information");
			mav.addObject("message", msg);
			mav.addObject("icon", "success");
		}
		if (pageNumber == null) {
			pageNumber = 0;
		}
		Page<JobDescription> descriptions = descriptionService.findByCompanyId(user.getCompanyId(),
									PageRequest.of(pageNumber, NavigationConstants.NO_JOBDESCRIPTION_PAGE));
//			 mav.addObject("usr", new User());
		mav.addObject("descriptions", descriptions.getContent());
		CommonUtil.setCommonAttributesOfPagination(descriptions, modelMap, pageNumber, "jobDescriptions", null);
		return mav;
	}

	@GetMapping("/createJobStep1")
	public ModelAndView createJobStep1(@RequestParam(required = false) Long id, HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
		ModelAndView mav = new ModelAndView("jobDescStep1");
//		ModelAndView mav = new ModelAndView("testUpload");
		JobDescription description = new JobDescription();
		User user = (User) request.getSession().getAttribute("user");
		description.setCompanyId(user.getCompanyId());
		description.setCompanyName(user.getCompanyName());
		if (id != null) {
			description = descriptionRepository.findById(id).get();
				if(description.getPrimarySkills() != null && description.getPrimarySkills().trim().length() >0){
					mav.addObject("needToUpload", false);
				}
				else{
					mav.addObject("needToUpload", true);
				}
			mav.addObject("disabled", true);
//			request.getSession().setAttribute("newCampaign", false);
		} else {
			mav.addObject("needToUpload", true);
			mav.addObject("disabled", false);
//			request.getSession().setAttribute("newCampaign", true);
		}
		request.getSession().setAttribute("description", description);
		mav.addObject("description", description);
		return mav;
	}

	@PostMapping(value = "/goToJobStep2", produces = "text/plain;charset=UTF-8")
	@ResponseBody
	public String goToJobStep2(@RequestParam(name = "name", required = false) String name, @RequestParam(name = "file", required = false) MultipartFile uploadFile,
								HttpServletRequest request) throws IOException {
		if (uploadFile != null && uploadFile.getSize() != 0) {
			String destination = propertyConfig.getFileServerLocation() + File.separator + uploadFile.getOriginalFilename();
			JobDescription description = (JobDescription) request.getSession().getAttribute("description");
			description.setName(name);
			File file = new File(destination);
			if (file.exists()) {
				if (uploadFile.getOriginalFilename() != null && uploadFile.getOriginalFilename().trim().length() > 0) {
					FileUtils.forceDelete(file);
				}

			}
			if (uploadFile.getOriginalFilename() != null && uploadFile.getOriginalFilename().trim().length() > 0) {
				String fileUrl = propertyConfig.getFileServerWebUrl() + File.separator + uploadFile.getOriginalFilename();

				uploadFile.transferTo(file);
				description.setJobDescFileUrl(fileUrl);
			}
			
			String contents = FileUtils.readFileToString(file);
			JobDescriptionParseResponseDto  res = jdParser.parseJobDesc(contents);
			if(res.getErrorcertification() ){
				return "error: Improper Certification section in the Job Desc file";
			}
			if(res.getErrorDescription()){
				return "error: Improper Description section in the Job Desc file";
			}
			if(res.getErrordomain()){
				return "error: Improper Domain section in the Job Desc file";
			}
			if(res.getErrorEducation()){
				return "error: Improper Education section in the Job Desc file";
			}
			if(res.getErrorExperience()){
				return "error: Improper Experience section in the Job Desc file";
			}
			if(res.getErrorLocation()){
				return "error: Improper Location section in the Job Desc file";
			}
			if(res.getErrorPrimarySkills()){
				return "error: Improper Primary Skills section in the Job Desc file";
			}
			if(res.getErrorSecondarySkills()){
				return "error: Improper Secondary Skills section in the Job Desc file";
			}
			description.setDescription(res.getDescription());
			description.setPrimarySkills(res.getPrimarySkills());
			description.setSecondarySkills(res.getSecondarySkills());
			description.setEducation(res.getEducation());
			description.setCertification(res.getCertification());
			description.setExperience(res.getExperience());
			description.setDomain(res.getDomain());
			description.setLocation(res.getLocation());
		}
		else{
			return "error";
		}
		return "ok";
	}
	
	@GetMapping(value = "/getJobDescParsedContents", produces = "application/json")
	@ResponseBody
	public JobDescription getJobDescParsedContents(HttpServletRequest request) {
		JobDescription description = (JobDescription) request.getSession().getAttribute("description");
		return description;
	}

	@GetMapping(value = "/showRecruiters", produces = "text/plain;charset=UTF-8")
	@ResponseBody
	public String showRecruiters(HttpServletRequest request) {
		User user = (User) request.getSession().getAttribute("user");
		JobDescription description = (JobDescription) request.getSession().getAttribute("description");
//		 campaignName = URLDecoder.decode(campaignName);

		List<JobDescriptionRecruiter> descriptionRecruiters = descriptionRecruiterRepository.findByCompanyIdAndJobDescriptionName(user.getCompanyId(),
									description.getName());
		String parent = "<ul class=\"mt-3\" style=\"width: 250%;\">	${RECRUITERS}</ul>";

		String childs = "";
		for (JobDescriptionRecruiter recruiter : descriptionRecruiters) {
			String child = "<li style=\"border-bottom:none\">	<b>${FIRST} ${LAST}	</b>&nbsp;&nbsp;	(<small>  ${EMAIL}</small>)</li>\n";
			child = child.replace("${FIRST}", recruiter.getFirstName() == null ? "NA" : recruiter.getFirstName());
			child = child.replace("${LAST}", recruiter.getLastName() == null ? "NA" : recruiter.getLastName());
			child = child.replace("${EMAIL}", recruiter.getEmail());
			childs += child;
		}
		parent = parent.replace("${RECRUITERS}", childs);
		return parent;
	}

	@GetMapping(value = "/showCampaign", produces = "text/plain;charset=UTF-8")
	@ResponseBody
	public String showCampaign(@RequestParam(required = false) String search, HttpServletRequest request) {
		User user = (User) request.getSession().getAttribute("user");
		JobDescription description = (JobDescription) request.getSession().getAttribute("description");

		String parent = "<ul class=\"mt-3\" style=\"width: 250%;\">	${CAMPAIGN}</ul>";
		String childs = "";
		String child = "<li style=\"border-bottom:none\">	<b>Campaign Name: </b>&nbsp;&nbsp;	   ${CAMPAIGN_NAME} </li>\n";
		child = child.replace("${CAMPAIGN_NAME}", description.getCampaign().getCampaignName() == null ? "NA" : description.getCampaign().getCampaignName());
		childs += child;
		parent = parent.replace("${CAMPAIGN}", childs);
		return parent;
	}

	@GetMapping(value = "/searchAndPopulaterRecruitersTable", produces = "text/plain;charset=UTF-8")
	@ResponseBody
	public String searchAndPopulaterRecruitersTable(@RequestParam(required = false, name = "search") String search, HttpServletRequest request) {
		User user = (User) request.getSession().getAttribute("user");
		Page<User> users;
		if (search == null) {
			users = userService.findUsersByTypeAndCompany(user.getCompanyId(), UserType.RECRUITER.getType(), PageRequest.of(0, 100));
		} else {
			 users = userService.searchRecruiters(user.getCompanyId(), search, PageRequest.of(0, 100));
		}
		String parent = "<table class=\"table\">	<thead>		<tr>			<th>				<input type=\"checkbox\" name=\"\" >			</th>			<th>				Name			</th>			<th>				Email			</th>		</tr>	</thead>	<tbody>		${ROWS}			</tbody></table>";
		String childs = "";
		for (User usr : users.getContent()) {
//			String child = "<tr id=\"T${ID}\">	<td>		<input type=\"checkbox\" name=\"${ID}\" id=\"${ID}\" onchange=\"changeCandidateCheckbox(this, '${CAMPAIGN_NAME}')\">	</td>	<td>		${FIRSTNAME}, ${LASTNAME}	</td>	<td>		${EMAIL}	</td></tr>";
			String child = "<tr id=\"T${ID}\">	<td>		<input type=\"checkbox\" name=\"${ID}\" id=\"${ID}\" onchange=\"changeRecruiterCheckbox(this)\">	</td>	<td>		${FIRSTNAME} ${LASTNAME}	</td>	<td>		${EMAIL}	</td></tr>";

			child = child.replace("${FIRSTNAME}", usr.getFirstName() == null ? "NA" : usr.getFirstName());
			child = child.replace("${LASTNAME}", usr.getLastName() == null ? "NA" : usr.getLastName());
			child = child.replace("${EMAIL}", usr.getEmail());
			child = child.replace("${ID}", usr.getId() + "");
//			child = child.replace("${CAMPAIGN_NAME}", URLEncoder.encode(campaignName));
			childs += child;
		}
		parent = parent.replace("${ROWS}", childs);
		return parent;
//		return map;
	}

	@RequestMapping(value = "/selectRecruiterForJob", method = RequestMethod.GET, produces = "text/plain;charset=UTF-8")
	public @ResponseBody String selectCandidateForCampaign(@RequestParam(required = true, name = "cid") Long cid, HttpServletRequest request, HttpServletResponse response) {
		User user = (User) request.getSession().getAttribute("user");
		User can = userService.findById(cid);
		JobDescription description = (JobDescription) request.getSession().getAttribute("description");
		// campaignCandidateService
		JobDescriptionRecruiter descriptionRecruiter = new JobDescriptionRecruiter();
		descriptionRecruiter.setCompanyId(user.getCompanyId());
		descriptionRecruiter.setCompanyName(user.getCompanyName());
		descriptionRecruiter.setJobDescriptionName(description.getName());
		descriptionRecruiter.setEmail(can.getEmail());
		descriptionRecruiter.setFirstName(can.getFirstName());
		descriptionRecruiter.setLastName(can.getLastName());
		JobDescription description2 = descriptionRepository.findByPrimaryKey(description.getName(), user.getCompanyId());
		if (description2 != null) {
			descriptionRecruiter.setJobDescriptionId(description2.getId());
			descriptionRecruiter.setJobDescriptionName(description.getName());
		}
		descriptionRecruiterService.saveOrUpdate(descriptionRecruiter);
//			campaignCandidateService.saveOrUpdate(campaignCandidate);
		return "ok";
	}

	@RequestMapping(value = "/unselectRecruiterForJob", method = RequestMethod.GET, produces = "text/plain;charset=UTF-8")
	public @ResponseBody String unselectCandidateForCampaign(@RequestParam(required = true, name = "cid") Long cid, HttpServletRequest request, HttpServletResponse response) {
		User user = (User) request.getSession().getAttribute("user");
		JobDescription description = (JobDescription) request.getSession().getAttribute("description");
		User can = userService.findById(cid);
		JobDescriptionRecruiter descriptionRecruiter = descriptionRecruiterService.findUniqueJobDescriptionRecruiter(user.getCompanyId(), description.getId(),
									can.getEmail());
//		
		descriptionRecruiterRepository.deleteById(descriptionRecruiter.getId());
		return "ok";
	}

	@GetMapping(value = "/searchAndPopulaterCampaignTable", produces = "text/plain;charset=UTF-8")
	@ResponseBody
	public String searchAndPopulaterCampaignTable(HttpServletRequest request) {
		User user = (User) request.getSession().getAttribute("user");
		Page<User> users = userService.findUsersByTypeAndCompany(user.getCompanyId(), UserType.RECRUITER.getType(), PageRequest.of(0, 100));
		List<Campaign> campaigns = campaignService.findByPrimaryKey(user.getCompanyId());
		String parent = "<table class=\"table\">	<thead>		<tr>			<th>				 			</th>			<th>			Campaign Name			</th>			<th>				Camapaign Description			</th>		</tr>	</thead>	<tbody>		${ROWS}			</tbody></table>";
		String childs = "";
		for (Campaign campaign : campaigns) {
//			String child = "<tr id=\"T${ID}\">	<td>		<input type=\"checkbox\" name=\"${ID}\" id=\"${ID}\" onchange=\"changeCandidateCheckbox(this, '${CAMPAIGN_NAME}')\">	</td>	<td>		${FIRSTNAME}, ${LASTNAME}	</td>	<td>		${EMAIL}	</td></tr>";
			String child = "<tr id=\"T${ID}\">	<td>		<input type=\"radio\" name=\"campaignId\" id=\"${ID}\" onchange=\"selectCampaign(this)\">	</td>	<td>		${CAMPAIGN_NAME}	</td>	<td>		${CAMPAIGN_DESC}	</td></tr>";

			child = child.replace("${CAMPAIGN_NAME}", campaign.getCampaignName() == null ? "NA" : campaign.getCampaignName());
			child = child.replace("${CAMPAIGN_DESC}", campaign.getCampaignDesc() == null ? "NA" : campaign.getCampaignDesc());
//			child = child.replace("${EMAIL}", usr.getEmail());
			child = child.replace("${ID}", campaign.getId() + "");
//			child = child.replace("${CAMPAIGN_NAME}", URLEncoder.encode(campaignName));
			childs += child;
		}
		parent = parent.replace("${ROWS}", childs);
		return parent;
//		return map;
	}

	@RequestMapping(value = "/selectCampaignForJob", method = RequestMethod.GET, produces = "text/plain;charset=UTF-8")
	public @ResponseBody String selectCampaignForJob(@RequestParam(required = true, name = "cid") Long cid, HttpServletRequest request, HttpServletResponse response) {
		User user = (User) request.getSession().getAttribute("user");
		JobDescription description = (JobDescription) request.getSession().getAttribute("description");
		Campaign campaign = campaignRepository.findById(cid).get();
		description.setCampaign(campaign);
		return "ok";
	}

	@RequestMapping(value = "/saveJobDescription", method = RequestMethod.GET)
	public ModelAndView saveJobDescription(HttpServletRequest request, HttpServletResponse response) {
		User user = (User) request.getSession().getAttribute("user");
		JobDescription description = (JobDescription) request.getSession().getAttribute("description");
		descriptionRepository.save(description);
		String msg = "Saved Successfully";
		return new ModelAndView("redirect:/jobDescriptions?msg=" + msg);
	}

	@PostMapping(value = "/uploadProfile", produces = "text/plain;charset=UTF-8")
	@ResponseBody
	public String uploadProfile(@RequestParam Long jobId, @RequestParam String firstName, @RequestParam String lastName, @RequestParam(required = false) String email,
								@RequestParam(name = "file", required = false) MultipartFile uploadFile, HttpServletRequest request)
								throws IOException {
//		if (uploadFile != null && uploadFile.getSize() != 0) {
		User user = (User) request.getSession().getAttribute("user");
		String baseFolder = "";
		baseFolder = propertyConfig.getProfileBaseLocation() + File.separator + user.getRecruitmentCompanyName() + File.separator + user.getEmail() + File.separator
									+ jobId;
		File file = new File(baseFolder);
		file.mkdirs();
		File actual = new File(baseFolder + File.separator + uploadFile.getOriginalFilename());
		FileUtils.copyInputStreamToFile(uploadFile.getInputStream(), actual);// or uploadFile.transferTo(file);
		JobDescription description = descriptionService.findById(jobId);
		User user2 = new User();
		user2.setFirstName(firstName);
		user2.setLastName(lastName);
		user2.setEmail(email);
		user2.setPassword("1234");
		user2.setCompanyId(user.getCompanyId());
		user2.setCompanyName(user.getCompanyName());
		user2.setUserType(UserType.STUDENT);
		userService.saveOrUpdate(user2);

		RecruitCandidateProfile candidateProfile = new RecruitCandidateProfile();
		candidateProfile.setCompanyId(user.getCompanyId());
		candidateProfile.setCompanyName(user.getCompanyName());
		candidateProfile.setFirstName(firstName);
		candidateProfile.setLastName(lastName);
		candidateProfile.setLocationCVFileSystem(baseFolder + File.separator + uploadFile.getOriginalFilename());
		if (email != null) {
			candidateProfile.setEmail(email);
		} else {
			throw new AssessmentGenericException("EMAIL_MANDATORY");
		}
		candidateProfile.setRecruiterEmail(user.getEmail());
		candidateProfile.setJobDescriptionId(jobId);
		candidateProfile.setJobDescriptionName(description.getName());
		candidateProfile.setCandidateCVName(uploadFile.getOriginalFilename());
		candidateProfile.setCandidateCVURL(propertyConfig.getFileServerWebUrl() + "Files" + File.separator + user.getRecruitmentCompanyName() + File.separator
									+ user.getEmail() + File.separator + jobId + File.separator + uploadFile.getOriginalFilename());
		recruitCandidateProfileService.saveOrUpdate(candidateProfile);
		// recruitCandidateProfileRepository.save(candidateProfile);
//		}
		return "ok";
	}
	
	

	@GetMapping(value = "/getCandidateProfile", produces = "text/plain;charset=UTF-8")
	@ResponseBody
	public String getCandidateProfile(@RequestParam Long jobId, HttpServletRequest request) {
		User user = (User) request.getSession().getAttribute("user");
		// List<RecruitCandidateProfile> candidateProfiles =
		// recruitCandidateProfileRepository.findByJobDescriptionIdAndRecruiterEmail(jobId,
		// user.getEmail());
		List<RecruitCandidateProfile> candidateProfiles = recruitCandidateProfileRepository.findByJobDescriptionIdAndRecruiterEmailAndCompanyId(jobId, user.getEmail(),
									user.getCompanyId());

		Page<User> users = userService.findUsersByTypeAndCompany(user.getCompanyId(), UserType.RECRUITER.getType(), PageRequest.of(0, 100));
		String parent = "<table class=\"table\">	<thead>		<tr>  <th>				Name			</th>			<th>			Email				</th><th>		View Profile					</th>		</tr>	</thead>	<tbody>		${ROWS}			</tbody></table>";
		String childs = "";
		for (RecruitCandidateProfile candidateProfile : candidateProfiles) {
			String child = "<tr>		<td>		${FIRSTNAME} ${LASTNAME}	</td>	<td>		${EMAIL}	</td><td>	<a href='${URL}' target='_blank'>View Profile</a>		</td></tr>";

			child = child.replace("${FIRSTNAME}", candidateProfile.getFirstName() == null ? "NA" : candidateProfile.getFirstName());
			child = child.replace("${LASTNAME}", candidateProfile.getLastName() == null ? "NA" : candidateProfile.getLastName());
			child = child.replace("${EMAIL}", candidateProfile.getEmail());
//			child = child.replace("${ID}", candidateProfile.getId() + "");
			child = child.replace("${URL}", candidateProfile.getCandidateCVURL());
//			child = child.replace("${CAMPAIGN_NAME}", URLEncoder.encode(campaignName));
			childs += child;
		}
		parent = parent.replace("${ROWS}", childs);
		return parent;
//		return map;
	}

	@RequestMapping(value = "/searchJobDescription2", method = RequestMethod.GET)
	public ModelAndView searchJobDescription2(@RequestParam(name = "page", required = false) Integer pageNumber, @RequestParam String searchText, HttpServletResponse response,
								HttpServletRequest request) throws Exception {
		User user = (User) request.getSession().getAttribute("user");
		if (pageNumber == null) {
			pageNumber = 0;
		}
		Page<JobDescription> descriptions = descriptionService.searchJobDescription(user.getCompanyId(), searchText,
									PageRequest.of(pageNumber, NavigationConstants.NO_JOBDESCRIPTION_PAGE));
		ModelAndView mav = new ModelAndView("jobDescriptionProfile");
		mav.addObject("descriptions", descriptions.getContent());
		Map<String, String> queryParams = new HashMap<>();
		CommonUtil.setCommonAttributesOfPagination(descriptions, mav.getModelMap(), pageNumber, "searchJobDescription2", queryParams);
		return mav;
	}

	@RequestMapping(value = "/searchJobDescription3", method = RequestMethod.GET)
	public ModelAndView searchJobDescription3(@RequestParam(name = "page", required = false) Integer pageNumber, @RequestParam String searchText, HttpServletResponse response,
								HttpServletRequest request) throws Exception {
		User user = (User) request.getSession().getAttribute("user");
		if (pageNumber == null) {
			pageNumber = 0;
		}
		Page<JobDescription> descriptions = descriptionService.searchJobDescription(user.getCompanyId(), searchText,
									PageRequest.of(pageNumber, NavigationConstants.NO_JOBDESCRIPTION_PAGE));
		ModelAndView mav = new ModelAndView("recruiterDashboard");
		mav.addObject("descriptions", descriptions.getContent());
		Map<String, String> queryParams = new HashMap<>();
		CommonUtil.setCommonAttributesOfPagination(descriptions, mav.getModelMap(), pageNumber, "searchJobDescription3", queryParams);
		return mav;
	}

	@GetMapping("/profileForJobDescription")
	public ModelAndView profileForJobDescription(@RequestParam(name = "page", required = false) Integer pageNumber, HttpServletRequest request, HttpServletResponse response,
								ModelMap modelMap) {
		ModelAndView mav = new ModelAndView("jobDescriptionProfile");
		User user = (User) request.getSession().getAttribute("user");
		if (pageNumber == null) {
			pageNumber = 0;
		}
		Page<JobDescription> descriptions = descriptionService.findByCompanyId(user.getCompanyId(),
									PageRequest.of(pageNumber, NavigationConstants.NO_JOBDESCRIPTION_PAGE));
//			 mav.addObject("usr", new User());
		mav.addObject("descriptions", descriptions.getContent());
		CommonUtil.setCommonAttributesOfPagination(descriptions, modelMap, pageNumber, "profileForJobDescription", null);
		return mav;
	}
	
	@GetMapping(value = "/getCandidatesForJobDescToParse", produces = "text/plain;charset=UTF-8")
	@ResponseBody
	public String getCandidatesForJobDescToParse(@RequestParam(name = "jdId") Long jdId, @RequestParam(name = "jobDescName") String jobDescName, HttpServletRequest request) {
		User user = (User) request.getSession().getAttribute("user");


		List<RecruitCandidateProfile> candidateProfiles = recruitCandidateProfileRepository.findByJobDescriptionIdAndCompanyId(jdId, user.getCompanyId());

		String parent = "<table class=\"table\">	<thead>	<tr> <th>Candidate</th>	<th>  Parsing Done? </th><th>    Recruiter    </th	</tr>	</thead>	<tbody>		${ROWS}			</tbody></table>";
		String childs = "";

		for (RecruitCandidateProfile jd : candidateProfiles) {
			String child = "<tr>	 	 	<td>		${CANDIDATE}	</td>	<td>	${PARSING_DONE}	</td><td>${RECRUITER}</td></tr>";

			child = child.replace("${CANDIDATE}", jd.getFirstName() + " " + jd.getLastName());
		
			
			String par = "parseCandidate?recruitCandId="+jd.getId()+"&jobDescName="+jobDescName+"&jdId="+jdId;
			String javascriptFn = "parseProfile('"+par+"')";
			String pars = jd.getParsed() != null && !jd.getParsed()?"<a href=\"javascript:"+javascriptFn+"\">Click to Parse</a>":"Yes";

			child = child.replace("${PARSING_DONE}", pars);
			
			child = child.replace("${RECRUITER}", jd.getRecruiterEmail());

			childs += child;
		}
		parent = parent.replace("${ROWS}", childs);
		return parent;
	}
	
	
	@GetMapping(value = "/parseCandidate", produces = "text/plain;charset=UTF-8")
	@ResponseBody
	public String parseCandidate(@RequestParam(name = "recruitCandId") Long recruitCandId, @RequestParam(name = "jdId") Long jdId, @RequestParam(name = "jobDescName") String jobDescName, HttpServletRequest request) {
		User user = (User) request.getSession().getAttribute("user");
		RecruitCandidateProfile profile = recruitCandidateProfileRepository.findById(recruitCandId).get();
		JobDescription jobDesc = descriptionRepository.findById(jdId).get();
		String primSkills = jobDesc.getPrimarySkills();
		List<String> prim = Stream.of(primSkills.split(","))
			     .map(String::trim)
			     .collect(Collectors.toList());
		if(prim == null || prim.size() == 0){
			prim.add("Java");
			prim.add("Spring");
		}
		
		String secSkills = jobDesc.getSecondarySkills();
		List<String> sec = Stream.of(secSkills.split(","))
			     .map(String::trim)
			     .collect(Collectors.toList());
		if(sec == null || sec.size() == 0){
			sec = new ArrayList<String>();
			sec.add("Communications");
			sec.add("Aptitude");
		}
		
		ResumeParsingResponse response =  parser.parseResume(profile.getLocationCVFileSystem(), jobDescName, jdId, user.getCompanyId(), user.getEmail(), "Profile Location",prim , sec);
		if(response == null || response.getData() == null || response.getStatus() != 200){
			profile.setParsedButFailed(true);
			recruitCandidateProfileRepository.save(profile);
			return "fail";
		}
		else{
			profile.setParsed(true);
			profile.setParsedEmail(response.getData().getCandidateEmail());
			profile.setJobDescName(jobDescName);
			profile.setJobDescId(jdId+"");
			profile.setParsedCompanyId(response.getData().getCompanyID());
			profile.setParsedName(response.getData().getCandidateName());
			profile.setParsedPhone(response.getData().getCandidatePhone());
			profile.setRelevancyMatch(response.getData().getRelevancyMatch());
			profile.setCandidateLastLocation(response.getData().getCandidateLastLocation());
			profile.setParsedSkills(response.getData().getSkillsInString());
			profile.setParsedSkills2(response.getData().getSkills2InString());
			profile.setTotalExperience(response.getData().getTotalExperience());
			profile.setDesignations(response.getData().getDesignationInString());
			profile.setEducations(response.getData().getEducationInString());
			profile.setPrimaryMatches(response.getData().getPrimary_matchInString());
			profile.setSecondaryMatches(response.getData().getSecondary_matchInString());
			profile.setUpdateDate(new Date());
			profile.setUpdatedBy(user.getEmail());
			recruitCandidateProfileRepository.save(profile);
			return "ok";
		}
	
	}

	@GetMapping(value = "/getCandidates", produces = "text/plain;charset=UTF-8")
	@ResponseBody
	public String getCandidates(@RequestParam(name = "jdId", required = false) Long jdId, HttpServletRequest request) {
		User user = (User) request.getSession().getAttribute("user");
//		List<CandidateDetailsForJD> candidateDetailsForJDs = jdRepository.findByRelvancy(user.getCompanyId(), id);

		List<RecruitCandidateProfile> candidateProfiles = recruitCandidateProfileRepository.findByJobDescriptionIdAndCompanyId(jdId, user.getCompanyId());

		String parent = "<table class=\"table\">	<thead>	<tr> <th>Candidate</th>	<th>  View Parsing Details </th><th>   Bucket	</th><th>    No. Of relevant Years    </th><th>    Current Location   </th><th>    Source    </th><th>   Connect    </th>	<th>   Initiate Next Steps	  </th></tr>	</thead>	<tbody>		${ROWS}			</tbody></table>";
		String childs = "";
//		for (CandidateDetailsForJD  jd : candidateDetailsForJDs) {
		for (RecruitCandidateProfile jd : candidateProfiles) {
//			String child = "<tr id=\"T${ID}\">	<td>		<input type=\"checkbox\" name=\"${ID}\" id=\"${ID}\" onchange=\"changeCandidateCheckbox(this, '${CAMPAIGN_NAME}')\">	</td>	<td>		${FIRSTNAME}, ${LASTNAME}	</td>	<td>		${EMAIL}	</td></tr>";
			String child = "";
				if(propertyConfig.getShowParsedResumeDetails()){
					child = "<tr>	 	 	<td>		${CANDIDATE}	</td>	<td>		<a href=\"javascript:getParse2(${ARGS2})\">Click Here</a>	</td><td>${BUCKET}</td><td>${RELEVANT_YEARS}</td><td>${CURRENT_LOCATION}</td><td>${SOURCE}</td><td>${CONNECT}</td><td><a href=\"javascript:openScheduleModel(${ARGS})\">Schedule</a></td></tr>";
				}
				else{
					child = "<tr>	 	 	<td>		${CANDIDATE}	</td>	<td>		<a href=\"javascript:getParse(${ARGS2})\">Click Here</a>	</td><td>${BUCKET}</td><td>${RELEVANT_YEARS}</td><td>${CURRENT_LOCATION}</td><td>${SOURCE}</td><td>${CONNECT}</td><td><a href=\"javascript:openScheduleModel(${ARGS})\">Schedule</a></td></tr>";
				}
			

//			child = child.replace("${JDNAME}", jd.getJobDescriptionName() == null ? "NA" : jd.getJobDescriptionName());
			child = child.replace("${CANDIDATE}", jd.getFirstName() + " " + jd.getLastName());
//			child = child.replace("${FIRSTNAME}", candidateProfile.getFirstName() == null ? "NA" : candidateProfile.getFirstName());
//			child = child.replace("${PARSE}", "<a href='#'>Click Here</a>");
			String args2 = "'" + jd.getEmail() + "','" + jd.getFirstName() + " " + jd.getLastName() + "'";
				if(propertyConfig.getShowParsedResumeDetails()){
					args2 = "'" + jd.getEmail() + "','" + jd.getFirstName() + " " + jd.getLastName() + "', '"+jd.getId()+"'";
				}

			child = child.replace("${ARGS2}", args2);
			
			
			child = child.replace("${BUCKET}", "NA");
//			child = child.replace("${RELEVANT_YEARS}", jd.getRelevantYears());
			child = child.replace("${RELEVANT_YEARS}", "NA");
//			child = child.replace("${CURRENT_LOCATION}", jd.getCurrentLocation());
			child = child.replace("${CURRENT_LOCATION}", "NA");
			child = child.replace("${SOURCE}", "NA");
			child = child.replace("${CONNECT}", jd.getEmail());
//			child = child.replace("${NEXT_STEP}", "<a href='javascript:openScheduleModel("  +jd.getJobDescName()+ '\'  ","+jd.getEmail()+")'>Schedule</a>");"
			String args = "'" + jd.getJobDescriptionName() + "','" + jd.getEmail() + "'";
			child = child.replace("${ARGS}", args);
//			child = child.replace("${CAMPAIGN_NAME}", URLEncoder.encode(campaignName));
			childs += child;
		}
		parent = parent.replace("${ROWS}", childs);
		return parent;
//		return map;
	}

	@GetMapping(value = "/scheduleCampaign", produces = "text/plain;charset=UTF-8")
	@ResponseBody
	public String scheduleCampaign(@RequestParam(name = "jd", required = false) String jd, @RequestParam(name = "email", required = false) String email,
								HttpServletRequest request) {
		User user = (User) request.getSession().getAttribute("user");
		Map<String, Object> map = new HashMap<>();
//		List<CandidateDetailsForJD> candidateDetailsForJDs = jdRepository.findByRelvancy(user.getCompanyId(), id);
		RecruitCandidateProfile profile = recruitCandidateProfileRepository.findByEmailAndCompanyId(email, user.getCompanyId());
//		map.put("email", usr.getEmail());
//		map.put("name", usr.getFirstName()+" "+usr.getLastName());
		JobDescription description = descriptionService.findbyPrimaryKey(jd, user.getCompanyId());
//		map.put("campaignName", description.getCampaign().getCampaignName());
//		map.put("skills", description.getCampaign().getSkillsForCampaign());
//		map.put("tests", description.getCampaign().getRounds());
		String parent = "<div class=\"col-sm-6  \">Candidate Details<br><b> Name:  </b>    ${FULL_NAME} <br><b>Email: </b><span id=\"email\">	${EMAIL}</span></div><div class=\"col-sm-6\"><b>Campaign Name: </b><span id=\"campName\">${CAMPAIGN_NAME}</span><br><br> <b>	Skills:</b> ${SKILLS}   <br><br><b>Tests: </b>${TESTS} <br><br>Interview  Start Date:  <input type=\"datetime-local\" class=\"form-control\" id=\"startDate\">	End Date: 	 <input type=\"datetime-local\" class=\"form-control\" id=\"endDate\"></div>";
		parent = parent.replace("${FULL_NAME}", profile.getFirstName() + " " + profile.getLastName());
		parent = parent.replace("${EMAIL}", profile.getEmail());
		parent = parent.replace("${CAMPAIGN_NAME}", description.getCampaign().getCampaignName());
//		parent = parent.replace("${CAMP_ID}", description.getCampaign().getId().toString());
		String tests = "";
		String skills = "";
		for (CampaignTest round : description.getCampaign().getRounds()) {
			tests += round.getTestName() + ", ";
			skills += round.getTestSkills() + ", ";
//			parent = parent.replace(	"${SKILLS}", round.getTestSkills()==null?"NA":round.getTestSkills());
		}
		parent = parent.replace("${SKILLS}", skills);
		parent = parent.replace("${TESTS}", tests);
//		parent = parent.replace("${FULL_NAME}",usr.getFirstName()+" "+usr.getLastName());
		return parent;
	}

	@RequestMapping(value = "/shareScheduledCampaign", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> shareScheduledCampaign(@RequestParam(name = "startDate", required = false) String startDate,
								@RequestParam(name = "endDate", required = false) String endDate,
								@RequestParam(name = "email", required = false) String email,
								@RequestParam(name = "campName", required = false) String campName, HttpServletRequest request,
								HttpServletResponse response) throws Exception {
		User user = (User) request.getSession().getAttribute("user");
		Map<String, Object> map = new HashMap<>();

		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
		Date d1 = new Date();
		Date d2 = new Date();
		try {
			d1 = dateFormat.parse(startDate);
			d2 = dateFormat.parse(endDate);
			if (d2.after(d1)) {
				CandidateCampaignSchedule campaignSchedule = new CandidateCampaignSchedule();
				RecruitCandidateProfile candidateProfile = recruitCandidateProfileService.findByEmailAndCompanyId(email, user.getCompanyId());
				campaignSchedule.setFirstName(candidateProfile.getFirstName());
				campaignSchedule.setLastName(candidateProfile.getLastName());
				campaignSchedule.setEmail(email);
				campaignSchedule.setCompanyId(user.getCompanyId());
				campaignSchedule.setCompanyName(user.getCompanyName());
				campaignSchedule.setStartDate(d1);
				campaignSchedule.setEndDate(d2);
				campaignSchedule.setCampaignName(campName);
				campaignScheduleService.saveOrUpdate(campaignSchedule);

				Campaign campaign = campaignService.findUniqueCampaign(user.getCompanyId(), campName);
				String file = propertyConfig.getCampaignInviteHtmlLocation();
				String html = FileUtils.readFileToString(new File(file));
				html = html.replace("{FULL_NAME}", campaignSchedule.getFirstName() == null ? "NA"
											: campaignSchedule.getFirstName() + " " + campaignSchedule.getLastName() == null
																		? "NA"
																		: campaignSchedule.getLastName());
				html = html.replace("${CAMPAIGN_NAME}", campName);
				html = html.replace("${MEETING_ROUND}", (campaign.getMeetingRound() != null && campaign.getMeetingRound() == true) ? "Yes" : "No");
				String candidateurl = getCampaignLinkForTestTaker(campaign.getCampaignName(), user.getCompanyId(), campaignSchedule.getEmail(),
											campaignSchedule.getFirstName() == null ? "NA" : campaignSchedule.getFirstName(),
											campaignSchedule.getLastName() == null ? "NA" : campaignSchedule.getLastName(),
											startDate, endDate);
				html = html.replace("{URL}", candidateurl);
				html = html.replace("${CAMPAIGN_DESC}", campaign.getCampaignDesc());
				EmailGenericMessageThread client = new EmailGenericMessageThread(campaignSchedule.getEmail(),
											"Campaign Link - " + campaign.getCampaignName() + " Sent by E-ASSESS", html,
											propertyConfig);
				Thread th = new Thread(client);
				th.start();

				map.put("msgType", "Information");
				map.put("msg", "Campaign shared successfully");
				map.put("icon", "success");

			} else {
				map.put("msgType", "Information");
				map.put("msg", "Start date is after end date");
				map.put("icon", "warning");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return map;
	}

	private String getCampaignLinkForTestTaker(String campaignName, String companyId, String email, String firstName, String lastName, String sdate, String edate) {
		String url = propertyConfig.getBaseUrl();
		String controllerMethod = "testtaker-campaign";
		campaignName = URLEncoder.encode(Base64.getEncoder().encodeToString(campaignName.getBytes()));
		companyId = URLEncoder.encode(Base64.getEncoder().encodeToString(companyId.getBytes()));
		email = URLEncoder.encode(Base64.getEncoder().encodeToString(email.getBytes()));
		firstName = URLEncoder.encode(Base64.getEncoder().encodeToString(firstName.getBytes()));
		lastName = URLEncoder.encode(Base64.getEncoder().encodeToString(lastName.getBytes()));
		sdate = URLEncoder.encode(Base64.getEncoder().encodeToString(sdate.getBytes()));
		edate = URLEncoder.encode(Base64.getEncoder().encodeToString(edate.getBytes()));
		url += controllerMethod + "?campaignName=" + campaignName + "&companyId=" + companyId + "&email=" + email + "&firstName=" + firstName + "&lastName=" + lastName
									+ "&startdate=" + sdate + "&enddate=" + edate;
		return url;
	}

	@GetMapping(value = "/getParsing")
	@ResponseBody
	public Map<String, Object> getParsing(@RequestParam(name = "email", required = false) String email, @RequestParam(name = "name", required = false) String name,
								HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String, Object> map = new HashMap<>();
		User user = (User) request.getSession().getAttribute("user");
		CandidateDetailsForJD candidateDetailsForJD = getCandidateDetails(email, name);
		RecruitCandidateProfile candidateProfile = recruitCandidateProfileService.findByEmailAndCompanyId(email, user.getCompanyId());
		map.put("url", candidateProfile.getCandidateCVURL());
		map.put("jd", candidateDetailsForJD);
		return map;
	}
	
	@GetMapping(value = "/getParsing2")
	@ResponseBody
	public Map<String, Object> getParsing2(@RequestParam(name="recruitCandidateProfileId") Long recruitCandidateProfileId, HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String, Object> map = new HashMap<>();
		User user = (User) request.getSession().getAttribute("user");
		RecruitCandidateProfile profile = recruitCandidateProfileRepository.findById(recruitCandidateProfileId).get();
			if(profile.getParsedName() != null && profile.getParsedName().trim().length() == 0){
				profile.setParsedName(profile.getFirstName()+" "+profile.getLastName());
			}
		JobDescription jd = 	descriptionRepository.findById(profile.getJobDescriptionId()).get();
		profile.setExpectedPrimarySkills(jd.getPrimarySkills());
		profile.setExpectedSecondarySkills(jd.getSecondarySkills());
		profile.setExpectedEducation(jd.getEducation());
		profile.setExpectedExperience(jd.getExperience());
		if(profile.getParsedButFailed() != null && profile.getParsedButFailed()){
			map.put("failureToParse", "System could not parse profile for "+profile.getFirstName()+" "+profile.getLastName()+". Please parse again or contact Admin" );
		}
		map.put("url", profile.getCandidateCVURL());
		map.put("jd", profile);
		return map;
	}

	private CandidateDetailsForJD getCandidateDetails(String email, String name) {
		CandidateDetailsForJD candidateDetailsForJD = new CandidateDetailsForJD();
		if (email.startsWith("a") || email.startsWith("c") || email.startsWith("e") || email.startsWith("g") || email.startsWith("i")) {
			candidateDetailsForJD.setCandidateName(name);
			candidateDetailsForJD.setEmail(email);
			candidateDetailsForJD.setPhoneNumber("9184723643");
			candidateDetailsForJD.setZipCode("732342");
			candidateDetailsForJD.setCity("Mumbai");
			candidateDetailsForJD.setState("Maharashtra");
			candidateDetailsForJD.setTechnicalSkills("Java Virtual Machine (JVM), JavaScript Pages (JSP), Service-oriented architecture – SOAP/REST, Web technologies – HTML, CSS, JQuery, Web frameworks – Struts and Spring");
			candidateDetailsForJD.setSoftSkills("Communication, Time management, Team player, Critical thinking, Work well under pressure");
			candidateDetailsForJD.setLanguages("Hindi, English, Marathi");
			candidateDetailsForJD.setTotalExperience("5 Years");
			candidateDetailsForJD.setEducationalDetails("B.Tech. in Computer Science");
			candidateDetailsForJD.setCertifications("Learning How to Learn - Coursera Certificate");
			candidateDetailsForJD.setRelevantYears("7 Years");
			candidateDetailsForJD.setCurrentLocation("Mumbai");
		} else if (email.startsWith("b") || email.startsWith("d") || email.startsWith("f") || email.startsWith("h") || email.startsWith("j")) {
			candidateDetailsForJD.setCandidateName(name);
			candidateDetailsForJD.setEmail(email);
			candidateDetailsForJD.setPhoneNumber("9286524318");
			candidateDetailsForJD.setZipCode("732352");
			candidateDetailsForJD.setCity("Bhopal");
			candidateDetailsForJD.setState("Madhya Pardesh");
			candidateDetailsForJD.setTechnicalSkills("Web technologies – HTML, CSS,  Web frameworks – Struts and Spring, JQuery");
			candidateDetailsForJD.setSoftSkills("Team player, Time management, Work well under pressure, Communication");
			candidateDetailsForJD.setLanguages("Hindi, English");
			candidateDetailsForJD.setTotalExperience("3 Years");
			candidateDetailsForJD.setEducationalDetails("B.E. in Computer Science");
			candidateDetailsForJD.setCertifications("Oracle Certificate");
			candidateDetailsForJD.setRelevantYears("4 Years");
			candidateDetailsForJD.setCurrentLocation("New Delhi");
		} else if (email.startsWith("k") || email.startsWith("m") || email.startsWith("o") || email.startsWith("q") || email.startsWith("s")) {
			candidateDetailsForJD.setCandidateName(name);
			candidateDetailsForJD.setEmail(email);
			candidateDetailsForJD.setPhoneNumber("9326458733");
			candidateDetailsForJD.setZipCode("876539");
			candidateDetailsForJD.setCity("Jabalpur");
			candidateDetailsForJD.setState("Madhya Pardesh");
			candidateDetailsForJD.setTechnicalSkills("Service-oriented architecture – REST, Web technologies – HTML, CSS, JQuery, Web frameworks – Struts and Spring");
			candidateDetailsForJD.setSoftSkills("Work well under pressure, Good Communication, Time management, Team player, Critical thinking");
			candidateDetailsForJD.setLanguages("Hindi, English");
			candidateDetailsForJD.setTotalExperience("4 Years");
			candidateDetailsForJD.setEducationalDetails("B.Tech. in Computer Science");
			candidateDetailsForJD.setCertifications("Java Certificate");
			candidateDetailsForJD.setRelevantYears("6 Years");
			candidateDetailsForJD.setCurrentLocation("Mumbai");
		} else if (email.startsWith("l") || email.startsWith("n") || email.startsWith("p") || email.startsWith("r") || email.startsWith("t")) {
			candidateDetailsForJD.setCandidateName(name);
			candidateDetailsForJD.setEmail(email);
			candidateDetailsForJD.setPhoneNumber("9827609876");
			candidateDetailsForJD.setZipCode("803593");
			candidateDetailsForJD.setCity("Nalanda");
			candidateDetailsForJD.setState("Bihar");
			candidateDetailsForJD.setTechnicalSkills("Java, Hibernate, JPA, Web technologies – HTML, CSS, JQuery, Web frameworks – Struts and Spring");
			candidateDetailsForJD.setSoftSkills("Good Communication, Critical thinking");
			candidateDetailsForJD.setLanguages("Hindi, English, Bhojpuri");
			candidateDetailsForJD.setTotalExperience("5 Years");
			candidateDetailsForJD.setEducationalDetails("B.Tech. in Computer Science");
			candidateDetailsForJD.setCertifications("Java Certifaction, Oracle Certifaction");
			candidateDetailsForJD.setRelevantYears("7 Years");
			candidateDetailsForJD.setCurrentLocation("Mumbai");
		} else {
			candidateDetailsForJD.setCandidateName(name);
			candidateDetailsForJD.setEmail(email);
			candidateDetailsForJD.setPhoneNumber("9184723643");
			candidateDetailsForJD.setZipCode("873678");
			candidateDetailsForJD.setCity("Banglore");
			candidateDetailsForJD.setState("Karnataka");
			candidateDetailsForJD.setTechnicalSkills("Java Virtual Machine (JVM), JavaScript Pages (JSP), Service-oriented architecture – SOAP/REST, Web technologies – HTML, CSS, JQuery, Web frameworks – Struts and Spring");
			candidateDetailsForJD.setSoftSkills("Communication, Time management, Team player, Critical thinking, Work well under pressure");
			candidateDetailsForJD.setLanguages("Hindi, English, Kannada");
			candidateDetailsForJD.setTotalExperience("7 Years");
			candidateDetailsForJD.setEducationalDetails("B.Tech. in Computer Science");
			candidateDetailsForJD.setCertifications("Learning How to Learn - Coursera Certificate");
			candidateDetailsForJD.setRelevantYears("8 Years");
			candidateDetailsForJD.setCurrentLocation("Mumbai");
		}
		return candidateDetailsForJD;

	}
}
