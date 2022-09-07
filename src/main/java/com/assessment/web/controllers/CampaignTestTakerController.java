package com.assessment.web.controllers;

import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.assessment.common.PropertyConfig;
import com.assessment.data.Campaign;
import com.assessment.data.CampaignMeetingForCandidate;
import com.assessment.data.CampaignTest;
import com.assessment.data.CampaignTestUTF;
import com.assessment.data.FooterUTF;
import com.assessment.data.PublicTestUTF;
import com.assessment.data.Test;
import com.assessment.data.User;
import com.assessment.data.UserTestSession;
import com.assessment.repositories.CampaignRepository;
import com.assessment.repositories.CampaignTestUTFRepository;
import com.assessment.repositories.FooterUTFRepository;
import com.assessment.repositories.QuestionMapperRepository;
import com.assessment.services.CampaignCandidateService;
import com.assessment.services.CampaignMeetingForCandidateService;
import com.assessment.services.CampaignReviewerService;
import com.assessment.services.CampaignService;
import com.assessment.services.QuestionMapperService;
import com.assessment.services.ReviewerCampaignMappingService;
import com.assessment.services.TestService;
import com.assessment.services.UserService;
import com.assessment.services.UserTestSessionService;

@Controller
public class CampaignTestTakerController {

	
	@Autowired
	CampaignService campaignSevice;
	
	@Autowired
	CampaignRepository campaignRep;
	
	@Autowired
	TestService testService;
	
	@Autowired
	QuestionMapperRepository questionMapperRep;
	
	@Autowired
	CampaignCandidateService campaignCandidateService;
	
	@Autowired
	ReviewerCampaignMappingService reviewerMappingService;
	
	@Autowired
	UserService userService;
	
	@Autowired
	CampaignReviewerService campaignReviewerService;
	
	@Autowired
	PropertyConfig propertyConfig;
	
	@Autowired
	QuestionMapperService questionMapperService;
	
	@Autowired
	UserTestSessionService userTestSessionService;
	
	@Autowired
	CampaignMeetingForCandidateService meetingService;
	
	@Autowired
	CampaignTestUTFRepository campaignTestUTFRepository;
	
	@Autowired
	FooterUTFRepository footerUTFRepository;
	
	@RequestMapping(value = "/testtaker-campaign", method = RequestMethod.GET)
	public ModelAndView showClusters(@RequestParam(required = false) String lang, @RequestParam String campaignName, @RequestParam String companyId, @RequestParam String email, @RequestParam String firstName,@RequestParam String lastName,@RequestParam String startdate, @RequestParam String enddate,HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mav = new ModelAndView("testtaker-campaign");
		
//		   if(lang==null) {
//			    lang="eng";        
//		    }
//		    request.getSession().setAttribute("lang", lang);
		if(lang == null){
			lang = "eng";
		}
		System.out.println("language passed to this campaign "+campaignName+" is "+lang);
		    CampaignTestUTF  campaignTestUTF= campaignTestUTFRepository.findByLanguage(lang);
		    mav.addObject("campaignTestUTF", campaignTestUTF);
		    System.out.println("campaignTestUTF is "+campaignTestUTF);
		 FooterUTF  footerUTF=  footerUTFRepository.findByLanguage(lang);
		   mav.addObject("footerUTF", footerUTF);
		   System.out.println("footerUTF is "+footerUTF);
		campaignName = new URLDecoder().decode(new String(Base64.getDecoder().decode(campaignName.getBytes())));
		companyId = new URLDecoder().decode(new String(Base64.getDecoder().decode(companyId.getBytes())));
		
		email = new URLDecoder().decode(new String(Base64.getDecoder().decode(email.getBytes())));
		firstName = new URLDecoder().decode(new String(Base64.getDecoder().decode(firstName.getBytes())));
		lastName = new URLDecoder().decode(new String(Base64.getDecoder().decode(lastName.getBytes())));
		startdate = new URLDecoder().decode(new String(Base64.getDecoder().decode(startdate.getBytes())));
		enddate = new URLDecoder().decode(new String(Base64.getDecoder().decode(enddate.getBytes())));
		
		User user = userService.findByPrimaryKey(email, companyId);
		request.getSession().setAttribute("user", user);
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");//dd/MM/yyyy hh:mm aa
		
		Date sd = dateFormat.parse(startdate);
		Date ed = dateFormat.parse(enddate);
		Date now = new Date();
		String msg = "";
			if(sd.after(now)){
				msg = "Your Link will be enabled at following time - "+startdate;
				mav = new ModelAndView("campaignLinkNotEnabled");
				mav.addObject("message", msg);
				mav.addObject("campaignName", campaignName);
				return mav;
			}
			if(now.after(ed)){
				msg = "Your Link has expired at following time - "+startdate;
				mav = new ModelAndView("campaignLinkNotEnabled");
				mav.addObject("message", msg);
				mav.addObject("campaignName", campaignName);
				return mav;
			}
		Campaign campaign = campaignSevice.findUniqueCampaign(companyId, campaignName);
			if(campaign == null){
				msg = "It seems the campaign "+campaignName+" for which the link was shared do not exist. Please contact Admin";
				mav = new ModelAndView("campaignLinkNotEnabled");
				mav.addObject("message", msg);
				mav.addObject("campaignName", campaignName);
				return mav;
			}
			
			List<CampaignTest> rounds = campaign.getRounds();
				if(rounds.size() == 0){
					msg = "It seems the campaign "+campaignName+" has no Test rounds configured. Please contact Admin";
					mav = new ModelAndView("campaignLinkNotEnabled");
					mav.addObject("message", msg);
					mav.addObject("campaignName", campaignName);
					return mav;
				}
				
				long timeDiff = ed.getTime() - now.getTime();
				Long noOfDaysToexpire = TimeUnit.DAYS.convert(timeDiff, TimeUnit.MILLISECONDS);
				mav.addObject("expireDays", noOfDaysToexpire);
				
				
				for(CampaignTest test : rounds){
					if(test.getMeeting()){
						test.setNoOfMCQQuestions(0);
						test.setNoOfCodingQuestions(0);
						test.setNoOfFullStackQuestions(0);
						test.setTestDuration(60);
						test.setComplete(false);
						CampaignMeetingForCandidate campaignMeetingForCandidate = meetingService.findUniqueCampaignMeetingForCandidate(user.getCompanyId(), test.getCampaignName(), test.getCampaignTestName(), email);
							if(campaignMeetingForCandidate != null){
								test.setUrl(campaignMeetingForCandidate.getMeetingUrl());
								DateFormat dateFormat2 = new SimpleDateFormat("dd/MM/yyyy hh:mm aa");
								test.setMeetingStartTime(dateFormat2.format(campaignMeetingForCandidate.getStartTime()));
							}
							else{
								test.setMeetingStartTime("Not yet Scheduled");
							}
						
					}
					else{
						Integer noOfMcqQs = questionMapperRep.findCountOfMCQQsforTest(test.getTestName(), user.getCompanyId());
						Integer noOfCodingQs = questionMapperRep.findCountOfCodingQsforTest(test.getTestName(), user.getCompanyId());
						Integer noOfFullstackQs = questionMapperRep.findCountOfFullstackQsforTest(test.getTestName(), user.getCompanyId());
						test.setNoOfMCQQuestions(noOfMcqQs);
						test.setNoOfCodingQuestions(noOfCodingQs);
						test.setNoOfFullStackQuestions(noOfFullstackQs);
						Test tt = testService.findbyTest(test.getTestName(), companyId);
						System.out.println("campaign name "+campaign.getCampaignName());
						System.out.println("test campaign name "+test.getCampaignTestName());
						System.out.println("test  name "+test.getTestName());
						test.setTestDuration(tt.getTestTimeInMinutes() == null?60:tt.getTestTimeInMinutes());
						String testUrlForUser = getUrlForUser(email, tt.getId(), companyId, campaign.getLanguage()==null?"eng":campaign.getLanguage());
						test.setUrl(testUrlForUser);
						UserTestSession session = userTestSessionService.findUserTestSession(email, test.getTestName(), companyId);
						if(session == null){
							test.setComplete(false);
						}
						else{
							test.setComplete(true);
						}
					}
				}
				
				mav.addObject("campaign", campaign);
				return mav;
	}
	
	private String getUrlForUser(String user, Long testId, String companyId, String lang) {
		 String userBytes =  Base64.getEncoder().encodeToString(user.getBytes());
		 
		 String after = "userId="+URLEncoder.encode(userBytes)+"&testId="+URLEncoder.encode(testId.toString())+"&companyId="+URLEncoder.encode(companyId);
		 String url = propertyConfig.getBaseUrl()+"mcq2?"+after+"&lang="+lang ;
		 Date d1 = new Date();
		 String sDate = Base64.getEncoder().encodeToString((""+d1.getTime()).getBytes());
		 Calendar c = Calendar.getInstance();
		 c.setTime(d1);
		 c.add(Calendar.YEAR, 2);
		 Date d2 = c.getTime();
		 String	eDate = Base64.getEncoder().encodeToString((""+d2.getTime()).getBytes());
		sDate = URLEncoder.encode(sDate);
		eDate = URLEncoder.encode(eDate);
		url += "&startDate="+sDate+"&endDate="+eDate;
		 
		 return url;
	  }
}
