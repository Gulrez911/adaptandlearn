package com.assessment.web.controllers;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.assessment.common.CommonUtil;
import com.assessment.data.Campaign;
import com.assessment.data.CampaignCandidate;
import com.assessment.data.CampaignMeetingForCandidate;
import com.assessment.data.CampaignReviewer;
import com.assessment.data.CampaignTest;
import com.assessment.data.User;
import com.assessment.services.CampaignCandidateService;
import com.assessment.services.CampaignMeetingForCandidateService;
import com.assessment.services.CampaignService;
import com.assessment.services.ModService;
import com.assessment.services.UserService;
import com.assessment.web.dto.MeetingParticipant;
import com.assessment.web.dto.MeetingSession;
import com.assessment.web.dto.MeetingUser;

@Controller
public class MeetingController {
	
	@Autowired
	CampaignMeetingForCandidateService meetingService;
	
	@Autowired
	CampaignCandidateService campaignCandidateService;
	
	@Autowired
	CampaignService campaignService;

	@Autowired
	ModService modService;
	
	@Autowired
	UserService userService;
	
	@RequestMapping(value = "/showMeetings", method = RequestMethod.GET)
	public ModelAndView showMeetings(@RequestParam(name= "page", required = false) Integer pageNumber, @RequestParam(name= "searchText", required = false) String searchText,HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mav = new ModelAndView("meetings");
		User user = (User) request.getSession().getAttribute("user");
		if(pageNumber == null) {
			pageNumber = 0;
		}
		
		Page<Campaign> campaigns = null;
			if(searchText == null){
				campaigns = campaignService.getCampaignsByCompany(user.getCompanyId(), PageRequest.of(pageNumber, 15));
			}
			else{
				campaigns = campaignService.searchCampaigns(user.getCompanyId(), searchText, PageRequest.of(pageNumber, 15));
			}
		List<Campaign> cs = campaigns.getContent(); 
	    	for(Campaign campaign: cs){
	    		List<CampaignCandidate> candidates = campaignCandidateService.getCampaignCandidatesForCampaignMeetingByCompany(user.getCompanyId(), campaign.getCampaignName());
	    		campaign.setCandidates(candidates);
	    	}
	    mav.addObject("campaigns", cs);
	    mav.addObject("firstName", user.getFirstName());
	    mav.addObject("lastName", user.getLastName());
  		CommonUtil.setCommonAttributesOfPagination(campaigns, mav.getModelMap(), pageNumber, "meetings", null);
		return mav;
	}
	
	
	
	
	@RequestMapping(value = "/getCandidatesForMeeting", method = RequestMethod.GET)
	 public @ResponseBody List<String> getSelectedReviewersSummary(@RequestParam("campaignName") String campaignName,    HttpServletRequest request, HttpServletResponse response) {
		User user = (User) request.getSession().getAttribute("user");
		Campaign campaign = campaignService.findUniqueCampaign(user.getCompanyId(), campaignName);
		List<CampaignCandidate> candidates = campaignCandidateService.getCampaignCandidatesForCampaignMeetingByCompany(user.getCompanyId(), campaign.getCampaignName());
		campaign.setCandidates(candidates);
		return campaign.getCandidatesString();
	}
	
	@RequestMapping(value = "/getReviewersForMeeting", method = RequestMethod.GET)
	 public @ResponseBody String getReviewersForMeeting(@RequestParam("campaignName") String campaignName,    HttpServletRequest request, HttpServletResponse response) {
		User user = (User) request.getSession().getAttribute("user");
		Campaign campaign = campaignService.findUniqueCampaign(user.getCompanyId(), campaignName);
		return campaign.getReviewersString();
	}
	
	@RequestMapping(value = "/getMeetingRoundNames", method = RequestMethod.GET)
	 public @ResponseBody List<String> getMeetingRoundNames(@RequestParam("campaignName") String campaignName,    HttpServletRequest request, HttpServletResponse response) {
		User user = (User) request.getSession().getAttribute("user");
		Campaign campaign = campaignService.findUniqueCampaign(user.getCompanyId(), campaignName);
		List<String> meetingRounds = new ArrayList<String>();
			for(CampaignTest test : campaign.getRounds()){
				if(test.getMeeting()){
					meetingRounds.add(test.getCampaignTestName());
				}
			}
		return meetingRounds;
	}
	
	@RequestMapping(value = "/scheduleMeeting", method = RequestMethod.GET)
	 public @ResponseBody String scheduleMeeting(@RequestParam("candidate") String candidate, @RequestParam("startDate") String startDate, @RequestParam("endDate") String endDate, @RequestParam("campaignName") String campaignName, @RequestParam("campaignTestName") String campaignTestName,   HttpServletRequest request, HttpServletResponse response) {
		User user = (User) request.getSession().getAttribute("user");
		Campaign campaign = campaignService.findUniqueCampaign(user.getCompanyId(), campaignName);
		String arr[] = candidate.split("-");
		String email = arr[2];
		
		User user2 = userService.findByPrimaryKey(email, user.getCompanyId());
		
		List<CampaignReviewer> reviewers = campaign.getReviewers();
			for(CampaignReviewer reviewer: reviewers){
				MeetingUser meetingUser = new MeetingUser();
				meetingUser.setModRole("Trainer");
				meetingUser.setName((reviewer.getFirstName()==null || reviewer.getFirstName().trim().length()==0)?"NA":reviewer.getFirstName());
				meetingUser.setSurName((reviewer.getLastName()==null || reviewer.getLastName().trim().length()==0)?"NA":reviewer.getLastName());
				meetingUser.setEmail(reviewer.getEmail());
				modService.createModTrainer(meetingUser);
			}
			
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
			Date start = null;
			Date end = null;
			try {
				start = dateFormat.parse(startDate);
				end = dateFormat.parse(endDate);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return "fail";
			}	
			CampaignMeetingForCandidate campaignMeetingForCandidate = new CampaignMeetingForCandidate();
			campaignMeetingForCandidate.setCampaignName(campaign.getCampaignName());
			campaignMeetingForCandidate.setCampaignTestName(campaignTestName);
			campaignMeetingForCandidate.setCompanyId(user.getCompanyId());
			campaignMeetingForCandidate.setCompanyName(user.getCompanyName());
			campaignMeetingForCandidate.setCandidateEmail(email);
			campaignMeetingForCandidate.setCandidateFirstName(user2.getFirstName());
			campaignMeetingForCandidate.setCandidateLastName(user2.getLastName());
			campaignMeetingForCandidate.setStartTime(start);
			campaignMeetingForCandidate.setEndTime(end);
			String url = scheduleMeetingMod(campaignMeetingForCandidate, reviewers);
			
				if(url != null && url.startsWith("https")){
					campaignMeetingForCandidate.setMeetingUrl(url);
					meetingService.saveOrUpdate(campaignMeetingForCandidate);
					return "OK";
				}
			
			
			
		return "NOK";
	}
	
	private String scheduleMeetingMod(CampaignMeetingForCandidate candidate, List<CampaignReviewer> reviewers){
		MeetingSession meetingSession = new MeetingSession();
		meetingSession.setSessionTitle(candidate.getCampaignName()+" - "+candidate.getCampaignTestName());
		DateFormat dateFormat2 = new SimpleDateFormat("dd/MM/yyyy hh:mm aa");
		
		meetingSession.setStartDate(dateFormat2.format(candidate.getStartTime()));
		meetingSession.setEndDate(dateFormat2.format(candidate.getEndTime()));
		meetingSession.setRequesterEmailId("gulrez.farooqui@thev2technologies.com");
		meetingSession.getSkills().add("Java");
			for(CampaignReviewer reviewer : reviewers){
				MeetingParticipant meetingParticipant1 = new MeetingParticipant();
				meetingParticipant1.setParticipantEmailId(reviewer.getEmail());
				meetingSession.getParticipants().add(meetingParticipant1);
			}
		return modService.createModSession(meetingSession);
	}
	
}
