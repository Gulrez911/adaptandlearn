package com.assessment.web.controllers;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.assessment.common.CommonUtil;
import com.assessment.common.util.NavigationConstants;
import com.assessment.data.Campaign;
import com.assessment.data.CampaignTest;
import com.assessment.data.JobDescription;
import com.assessment.data.RecruitCandidateProfile;
import com.assessment.data.ReviewerComment;
import com.assessment.data.User;
import com.assessment.repositories.JobDescriptionRepository;
import com.assessment.repositories.RecruitCandidateProfileRepository;
import com.assessment.services.CampaignService;
import com.assessment.services.JobDescriptionService;
import com.assessment.services.RecruitCandidateProfileService;
import com.assessment.services.ReviewerCommentService;
import com.assessment.services.UserService;
import com.assessment.web.dto.ApplicationProfileDto;
@Controller
public class ApplicationTrackingController {
	
	@Autowired
	JobDescriptionService descriptionService;
	
	@Autowired
	JobDescriptionRepository jobDescRep;
	
	@Autowired
	RecruitCandidateProfileService candidateProfileService;
	
	@Autowired
	CampaignService campaignService;
	
	@Autowired
	UserService userService;
	
	@Autowired
	ReviewerCommentService reviewCommentservice;
	
	@Autowired
	RecruitCandidateProfileRepository candidatesRep;
	
	@Autowired
	JobDescriptionService jobDescriptionService;
	
	@GetMapping("/applicationTracking")
	public ModelAndView jobDescription(@RequestParam(name = "page", required = false) Integer pageNumber, @RequestParam(name = "searchText", required = false) String searchText, @RequestParam(name = "msg", required = false) String msg,
								HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
		ModelAndView mav = new ModelAndView("applicationTracking");
		User user = (User) request.getSession().getAttribute("user");
		if (msg != null) {
			mav.addObject("msgtype", "Information");
			mav.addObject("message", msg);
			mav.addObject("icon", "success");
		}
		if (pageNumber == null) {
			pageNumber = 0;
		}
		
		
		if(searchText != null){
			Page<JobDescription> descriptions =  jobDescRep.searchJobDescriptions(user.getCompanyId(), searchText, PageRequest.of(pageNumber, NavigationConstants.NO_RECRUITER_PAGE));
			mav.addObject("descriptions", descriptions.getContent());
			CommonUtil.setCommonAttributesOfPagination(descriptions, modelMap, pageNumber, "applicationTracking", null);
		}
		else{
			Page<JobDescription> descriptions = descriptionService.findByCompanyId(user.getCompanyId(), PageRequest.of(pageNumber, NavigationConstants.NO_RECRUITER_PAGE));
			mav.addObject("descriptions", descriptions.getContent());
			CommonUtil.setCommonAttributesOfPagination(descriptions, modelMap, pageNumber, "applicationTracking", null);
		}
		
//			 mav.addObject("usr", new User());
		
		mav.addObject("user", user);
		
		return mav;
	}
	
	
	@GetMapping("/showApplications")
	public ModelAndView showApplications(@RequestParam(name = "jid") Long jid,
								HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
		ModelAndView mav = new ModelAndView("applicationTrackingJD");
		User user = (User) request.getSession().getAttribute("user");
		List<RecruitCandidateProfile> list = candidateProfileService.findByJobDescAndCompanyId(jid, user.getCompanyId());
		List<ApplicationProfileDto> dtos = new ArrayList<ApplicationProfileDto>();
		JobDescription jobDescription = jobDescRep.findById(jid).get();
		mav.addObject("jobDescName", jobDescription.getName() == null?"NA":jobDescription.getName());
		
		
			for(RecruitCandidateProfile profile : list){
				ApplicationProfileDto dto  = new ApplicationProfileDto();
				dto.setCandidateName(profile.getFirstName() +" "+profile.getLastName());
				
				String jobDescName = jobDescription.getName();
				dto.setJobDescName(jobDescName);
				dto.setCandidateSkillProfileLink("Not Available");
				dto.setInterviewerFeedback("Not Available");
				JobDescription description= jobDescriptionService.findbyPrimaryKey(profile.getJobDescriptionName(), user.getCompanyId());
				String profileUrl =  "getUserReport2?campName="+description.getCampaign().getCampaignName()+"&candEmail="+profile.getEmail()+"&compId="+user.getCompanyId();
				dto.setProfileLink(profileUrl);				
//				dto.setProfileLink(profile.getCandidateCVURL());
				SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy hh:mm aa");
				if(profile.getCreateDate() == null){
					dto.setProfileUploadDate("NA");
				}
				else{
					String dt = dateFormat.format(profile.getCreateDate());
					dto.setProfileUploadDate(dt);
				}
				
				dto.setDirectInterview(jobDescription.getCampaign() == null?"Direct":"Through Assessment Rounds");
				dto.setRecruitCandidateProfile(profile);
				dto.setSourceOfProfile("Recruitment Consultant "+profile.getCompanyName()+" - "+profile.getRecruiterEmail());
				
				User userRec = userService.findByPrimaryKey(profile.getRecruiterEmail(), user.getCompanyId());
				dto.setSourceOfProfile(user.getRecruitmentCompanyName() +" - "+userRec.getFirstName()+" "+userRec.getLastName());
				Campaign campaign = jobDescription.getCampaign();
					if(campaign != null){
						dto.setAssessments(campaign.getCampaignName());
						List<CampaignTest> tests = campaign.getRounds();
						ReviewerComment comment = reviewCommentservice.findByPrimaryKey(campaign.getCampaignName(), profile.getEmail(), user.getCompanyId());
							if(comment != null){
								dto.setStageOfProfile("Interview Done, F/B - "+comment.getOverallReviewStatus().replace("'", "").replace("\"", "")+" Final Remark - "+(comment.getStatus()?"Success":"Fail"));
							}
							else{
								dto.setStageOfProfile("Assessments/Interview Stage");
							}
					}
					else{
						dto.setAssessments("NA");
						dto.setStageOfProfile("Interview Stage");
					}
					dtos.add(dto);	
				
			}
			mav.addObject("dtos", dtos);
		return mav;
	}
	
	@GetMapping("/apprepository")
	public ModelAndView apprepository(@RequestParam(name = "page", required = false) Integer pageNumber, @RequestParam(name = "search", required = false) String search, @RequestParam(name = "sort", required = false) String sort,
								HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
		ModelAndView mav = new ModelAndView("apprepository");
		User user = (User) request.getSession().getAttribute("user");
		
		if (pageNumber == null) {
			pageNumber = 0;
		}
		
		
		Page<RecruitCandidateProfile> profiles =  candidatesRep.getAllProfiles(user.getCompanyId(), sort==null?"ASC":sort, search==null?"":search, PageRequest.of(pageNumber, 15));
		for (RecruitCandidateProfile candidateProfile:profiles.getContent()) {
//				candidateProfile.getJobDescriptionId()
				JobDescription description= jobDescriptionService.findbyPrimaryKey(candidateProfile.getJobDescriptionName(), user.getCompanyId());
				String profileUrl =  "?campName="+description.getCampaign().getCampaignName()+"&candEmail="+candidateProfile.getEmail()+"&compId="+user.getCompanyId();
				candidateProfile.setCandidateProfileUrl(profileUrl);
		}
		mav.addObject("profiles", profiles.getContent());
		CommonUtil.setCommonAttributesOfPagination(profiles, modelMap, pageNumber, "apprepository", null);
		
	
		mav.addObject("user", user);
		
		return mav;
	}
}
