package com.assessment.web.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.assessment.common.CommonUtil;
import com.assessment.common.PropertyConfig;
import com.assessment.common.util.NavigationConstants;
import com.assessment.data.Campaign;
import com.assessment.data.CampaignCandidate;
import com.assessment.data.CampaignReviewer;
import com.assessment.data.CampaignTest;
import com.assessment.data.CheCluster;
import com.assessment.data.Company;
import com.assessment.data.User;
import com.assessment.repositories.CampaignReviewerRepository;
import com.assessment.services.CampaignCandidateService;
import com.assessment.services.CampaignReviewerService;
import com.assessment.services.CampaignService;
import com.assessment.services.CompanyService;
import com.assessment.web.dto.CampaignCandidateForReviewer;

@Controller
public class CampaignReviewerController {
	@Autowired
	CampaignReviewerService reviewerService;
	
	@Autowired
	CampaignReviewerRepository reviewerRep;
	
	@Autowired
	CampaignService campaignService;
	
	@Autowired
	CampaignCandidateService campaignCandidateService;
	
	@Autowired
	CompanyService companyService;
	
	@Autowired
	PropertyConfig propertyConfig;
	
	@RequestMapping(value = "/reviewers", method = RequestMethod.GET)
	public ModelAndView reviewers(@RequestParam(name= "page", required = false) Integer pageNumber, @RequestParam(name= "searchText", required = false) String searchText,HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mav = new ModelAndView("campaignReviewers");
		User user = (User) request.getSession().getAttribute("user");
		if(pageNumber == null) {
			pageNumber = 0;
		}
	    Page<CampaignReviewer> reviewers = reviewerRep.searchCampaignReviewers(user.getCompanyId(), searchText == null?"":searchText, PageRequest.of(pageNumber, 15));
  		
	    CampaignReviewer reviewer = new CampaignReviewer();
		 mav.addObject("reviewer", reviewer);
	    
	    mav.addObject("reviewers", reviewers.getContent());
	  
  		CommonUtil.setCommonAttributesOfPagination(reviewers, mav.getModelMap(), pageNumber, "reviewers", null);
		return mav;
	}
	
	@RequestMapping(value = "/deleteReviewer", method = RequestMethod.GET)
	public ModelAndView deleteReviewer(@RequestParam(name= "reviewerId") Long reviewerId,HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mav = new ModelAndView("campaignReviewers");
		User user = (User) request.getSession().getAttribute("user");
		
		reviewerRep.deleteById(reviewerId);
		
	    Page<CampaignReviewer> reviewers = reviewerRep.searchCampaignReviewers(user.getCompanyId(), "", PageRequest.of(0, 15));
  		
	    CampaignReviewer reviewer = new CampaignReviewer();
		 mav.addObject("reviewer", reviewer);
	    
	    mav.addObject("reviewers", reviewers.getContent());
	  
  		CommonUtil.setCommonAttributesOfPagination(reviewers, mav.getModelMap(), 0, "reviewers", null);
		return mav;
	}
	
	@RequestMapping(value = "/editReviewer", method = RequestMethod.GET)
	@ResponseBody
	public Map<String,Object> editReviewer(@RequestParam(name= "reviewerId", required = false) Long reviewerId, HttpServletRequest request, HttpServletResponse response) {
		 Map<String,Object>  map = new HashMap<>();
		 CampaignReviewer	reviewer = reviewerRep.findById(reviewerId).get();
		 map.put("reviewer", reviewer);
		 return map;
	}
	
	@RequestMapping(value = "/saveCampaignReviewer", method = RequestMethod.POST)
	  public ModelAndView saveCampaignReviewer(HttpServletRequest request, HttpServletResponse response,@ModelAttribute("reviewer") CampaignReviewer reviewer) {
		User user = (User) request.getSession().getAttribute("user");
		ModelAndView mav = new ModelAndView("campaignReviewers");
		reviewer.setCompanyId(user.getCompanyId());
		reviewer.setCompanyName(user.getCompanyName());
		reviewer.setPassword(reviewer.getEmail().hashCode()+"");
		reviewerService.saveOrUpdate(reviewer);
		 Page<CampaignReviewer> reviewers = reviewerRep.searchCampaignReviewers(user.getCompanyId(), "", PageRequest.of(0, 15));
	  		
		 CampaignReviewer reviewer2 = new CampaignReviewer();
		 mav.addObject("reviewer", reviewer2);
	    
	    mav.addObject("reviewers", reviewers.getContent());
	  
  		CommonUtil.setCommonAttributesOfPagination(reviewers, mav.getModelMap(), 0, "reviewers", null);
			
		mav.addObject("message", "Reviewer "+reviewer.getFirstName()+" "+reviewer.getLastName()+" saved" );// later put it as label
		mav.addObject("msgtype", "success");
		mav.addObject("icon", "success");
		return mav;
	}
	
	@RequestMapping(value = "/campaignreviewerlogin", method = RequestMethod.GET)
	  public ModelAndView learner(HttpServletRequest request, HttpServletResponse response) {
	    ModelAndView mav = new ModelAndView("loginCampaignReviewer");
	    User user = new User();
	    user.setCompanyName("AL");
		mav.addObject("user", user);
	    return mav;
	  }
	
	@RequestMapping(value = "/campaignreviewerauthenticate", method = RequestMethod.POST)
	  public ModelAndView campaignreviewerauthenticate(HttpServletRequest request, HttpServletResponse response, @ModelAttribute("user") User user ) throws Exception {
		    ModelAndView mav = null;
		    Company company = companyService.findByCompanyName(user.getCompanyName());
		    	if(company == null){
		    		mav = new ModelAndView("loginCampaignReviewer");
				 	   
			 	    user.setCompanyName("AL");
			 		mav.addObject("user", user);
			 		mav.addObject("message", "Invalid Company");// later put it as label
					mav.addObject("msgtype", "Failure");
			 	    return mav;
		    	}
		    
		    CampaignReviewer campaignReviewer = reviewerService.findByEmail(company.getCompanyId(), user.getEmail());
		    if(campaignReviewer == null){
		    	mav = new ModelAndView("loginCampaignReviewer");
		 	   
		 	    user.setCompanyName("AL");
		 		mav.addObject("user", user);
		 		mav.addObject("message", "Invalid Credentials ");// later put it as label
				mav.addObject("msgtype", "Failure");
		 	    return mav;
		    }
		    request.getSession().setAttribute("user", user);
		    request.getSession().setAttribute("campaignReviewer", campaignReviewer);
		   return reviewerDashboard(0, null, request, response);
	}
	
	@RequestMapping(value = "/reviewerDashboard", method = RequestMethod.GET)
	public ModelAndView reviewerDashboard(@RequestParam(name= "page", required = false) Integer pageNumber, @RequestParam(name= "searchText", required = false) String searchText,HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mav = new ModelAndView("reviewerDashboard");
		CampaignReviewer campaignReviewer = (CampaignReviewer) request.getSession().getAttribute("campaignReviewer");
		if(pageNumber == null) {
			pageNumber = 0;
		}
		Page<Campaign> campaigns = null;
		Map<String, String> params = new HashMap<>();
		if(searchText == null){
			campaigns = campaignService.getCampaignsByReviewerAndCompany(campaignReviewer.getCompanyId(), campaignReviewer.getEmail(), PageRequest.of(0, 15));
		}
		else{
			params.put("searchText", searchText);
			campaigns = campaignService.searchCampaignsByReviewerAndCompany(campaignReviewer.getCompanyId(), searchText, campaignReviewer.getEmail(), PageRequest.of(0, 15));
		}
		
		
		
		
	    List<Campaign> cs = campaigns.getContent();
	    List<CampaignCandidateForReviewer> list = new ArrayList<CampaignCandidateForReviewer>();
	    	for(Campaign campaign: cs){
	    		List<CampaignCandidate> candidates = campaignCandidateService.getCampaignCandidatesForCampaignMeetingByCompany(campaignReviewer.getCompanyId(), campaign.getCampaignName());
	    			for(CampaignCandidate candidate : candidates){
	    				String profileUrl = "getUserReport?reviewer="+campaignReviewer.getFirstName()+" "+campaignReviewer.getLastName()+"&campName="+campaign.getCampaignName()+"&candEmail="+candidate.getEmail()+"&compId="+candidate.getCompanyId();
	    				
	    				CampaignCandidateForReviewer candidateForReviewer = new CampaignCandidateForReviewer(campaign.getCampaignName(), candidate.getFirstName(), candidate.getLastName(), candidate.getEmail(), campaignReviewer.getEmail());
	    				candidateForReviewer.setUserProfileUrl(profileUrl);
	    				candidateForReviewer.setSkillsAssociatedWithCampaign(campaign.getSkillsConcatenated());
	    				list.add(candidateForReviewer);
	    			}
	    		
	    	}
	    mav.addObject("firstName", campaignReviewer.getFirstName());
	    mav.addObject("lastName", campaignReviewer.getLastName());
	    mav.addObject("candidatesForreviewer", list);
  		CommonUtil.setCommonAttributesOfPagination(campaigns, mav.getModelMap(), pageNumber, "reviewerDashboard", params);
		return mav;
	}

}
