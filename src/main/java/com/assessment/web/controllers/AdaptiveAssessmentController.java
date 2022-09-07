package com.assessment.web.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.assessment.common.util.AdaptiveConstants;
import com.assessment.data.AdaptiveAssessment;
import com.assessment.data.AdaptiveAssessmentInstance;
import com.assessment.data.AdaptiveAssessmentLevelInstance;
import com.assessment.data.AdaptiveAssessmentSkill;
import com.assessment.data.AdaptiveAssessmentSkillLevel;
import com.assessment.data.Company;
import com.assessment.data.User;
import com.assessment.jsonwrapper.JsonStatusBean;
import com.assessment.repositories.AdaptiveAssessmentInstanceRepository;
import com.assessment.repositories.AdaptiveAssessmentRepository;
import com.assessment.services.AdaptiveAssessmentInstanceService;
import com.assessment.services.AdaptiveAssessmentLevelInstanceService;
import com.assessment.services.AdaptiveAssessmentQuestionMappperInstanceService;
import com.assessment.services.AdaptiveAssessmentSkillService;
import com.assessment.services.AdaptiveAssessmentUtilService;
import com.assessment.services.CompanyService;
import com.assessment.services.QuestionService;
import com.assessment.services.UserService;
import com.assessment.web.dto.AdaptiveAreaOfImprovement;
import com.assessment.web.dto.AdaptiveQuestionNavigationTransition;
import com.assessment.web.dto.QualifierSkillLevelDto;
import com.assessment.web.dto.newlookandfeel.AdaptiveAssessmentInstanceDTO;
import com.assessment.web.dto.newlookandfeel.AdaptiveAssessmentResponseDto;
import com.assessment.webservices.newlookandfeel.AdaptiveAssessmentWebService;

@Controller
public class AdaptiveAssessmentController {
	
	//@RequestBody AdaptiveAssessmentInstanceDTO currentQuestion
	
		//start
		@Autowired
		UserService userService;
		
		@Autowired
		AdaptiveAssessmentSkillService levelService;
		
		@Autowired
		AdaptiveAssessmentQuestionMappperInstanceService mapperInstanceservice;
		
		@Autowired
		AdaptiveAssessmentLevelInstanceService levelInstanceService;
		
		@Autowired
		AdaptiveAssessmentInstanceService assessmentService;
		
		@Autowired
		AdaptiveAssessmentRepository adaptiveAssessmentRepository;
		
		@Autowired
		AdaptiveAssessmentInstanceRepository assessmentInstanceRep;
		
		@Autowired
		QuestionService questionservice;
		
		@Autowired
		AdaptiveAssessmentUtilService adaptiveAssessmentUtilService;
		
		@Autowired
		CompanyService companyService;
		
		@Autowired
		AdaptiveAssessmentWebService adaptiveAsswebservice;
	
	@RequestMapping(value = "/adaptiveAssessment1", method = RequestMethod.GET)
	  public ModelAndView   adaptiveAssessment1(HttpServletRequest req, HttpServletResponse res, @RequestParam String startDate, @RequestParam String endDate,  @RequestParam String userId, @RequestParam String companyId, @RequestParam Long testId) {
		 ModelAndView mav = new ModelAndView("adaptive_first_new");
		 
		    if(startDate == null || endDate == null){
		    	mav = new ModelAndView("testLinkNotEnabled");
		    	mav.addObject("message", "This is a old test link and no longer used. Contact Test Admin to get a new test link.");
		    	return mav;
		    }
		
//		    startDate = URLDecoder.decode(startDate);
//		    endDate = URLDecoder.decode(endDate);
//		    startDate = new String(Base64.getDecoder().decode(startDate.getBytes()));
//		    endDate = new String(Base64.getDecoder().decode(endDate.getBytes()));
//		    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy hh:mm aa");
//		    try {
//				Date sDate = new Date(Long.parseLong(startDate));
//				Date eDate = new Date(Long.parseLong(endDate));
//				Long now = System.currentTimeMillis();
//				Long start = sDate.getTime();
//				Long end = eDate.getTime();
//				String view = "testLinkNotEnabled";
//				String message = "";
//				Boolean inactive = false;
//				if(start > now){
//					message = "Link is not yet active. It will be activated at "+dateFormat.format(sDate)+". Try later.";
//					inactive = true;
//				}
//				if(now > end){
//					message = "Link has expired at "+dateFormat.format(eDate)+". Contact Test Admin.";
//					inactive = true;
//				}
//				
//				if(inactive){
//					mav = new ModelAndView(view);
//					mav.addObject("message", message);
//					return mav;
//				}
//				
//			} catch (Exception e) {
//				// TODO Auto-generated catch block
//				mav = new ModelAndView("testLinkNotEnabled");
//		    	mav.addObject("message", "This is a invalid test link. Make sure you have correctly copied it. Contact Test Admin to get more help if it still doesn't work.");
//		    	return mav;
//			}
		    
		 Company company = companyService.findByCompanyId(companyId);
		 	
		 AdaptiveAssessment test = adaptiveAssessmentRepository.findById(testId).get();
		 if(company == null || test == null){
		 		String view = "testLinkNotEnabled";
		 		mav.addObject("message", "Wrong Data passed");
		 		return mav;
		 	}
		 mav.addObject("test", test);
		 mav.addObject("userId", userId);
		 mav.addObject("startDate", startDate);
		 mav.addObject("endDate", endDate);
		 mav.addObject("companyId", companyId);
		return mav;


	}
	
	@RequestMapping(value = "/adaptiveAssessment2", method = RequestMethod.GET)
	  public ModelAndView   adaptiveAssessment2(HttpServletRequest req, HttpServletResponse res, @RequestParam String startDate, @RequestParam String endDate,  @RequestParam String userId, @RequestParam String companyId, @RequestParam Long testId) throws IOException {
		 ModelAndView mav = new ModelAndView("adaptive_second_new");
		 
		    if(startDate == null || endDate == null){
		    	mav = new ModelAndView("testLinkNotEnabled");
		    	mav.addObject("message", "This is a old test link and no longer used. Contact Test Admin to get a new test link.");
		    	return mav;
		    }
		
    
		 Company company = companyService.findByCompanyId(companyId);
		 AdaptiveAssessment test = adaptiveAssessmentRepository.findById(testId).get();
		 mav.addObject("test", test);
		 mav.addObject("userId", userId);
		 mav.addObject("startDate", startDate);
		 req.getSession().setAttribute("company", company);
		 req.getSession().setAttribute("test", test);
		 
		 try {
			JsonStatusBean bean = adaptiveAsswebservice.startAdaptiveAssessment(null, companyId, startDate, endDate, userId, test.getAdaptiveAssessmentName(), "0", req, res);
			if(bean.getErrorMessage() != null && bean.getErrorMessage().trim().length() > 0){
				mav = new ModelAndView("testLinkNotEnabled");
		    	mav.addObject("message", bean.getErrorMessage());
		    	return mav;
			}
			AdaptiveAssessmentResponseDto responseDto = (AdaptiveAssessmentResponseDto) bean.getData();
			mav.addObject("responseDto", responseDto);
			mav.addObject("currentQuestion", responseDto.getInstanceDTO());
			mav.addObject("sequence", 1);
			Integer noOfChoices = responseDto.getInstanceDTO().getQuestionMapperInstance().getQuestion().getNoOfChoices();
			mav.addObject("noOfChoices", noOfChoices);
			mav.addObject("questionType", responseDto.getInstanceDTO().getQuestionMapperInstance().getQuestion().getQuestionType().getType());
			mav.addObject("choices", responseDto.getInstanceDTO().getQuestionMapperInstance().getQuestion().getAllChoices());
			mav.addObject("finish", false);
		 } catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			String message = "Problem in setting up the assessment. Try later.";
			String view = "testLinkNotEnabled";
			mav = new ModelAndView(view);
			mav.addObject("message", message);
			return mav;
		}
		 User user = userService.findByPrimaryKey(userId, company.getCompanyId());
		 return setStats(test, user, mav, req, res);


	}
	
	@RequestMapping(value = "/nextAdaptive", method = RequestMethod.POST)
	public ModelAndView   nextAdaptive(HttpServletRequest req, HttpServletResponse res, @ModelAttribute("currentQuestion")  AdaptiveAssessmentInstanceDTO currentQuestion) throws IOException {
		 ModelAndView mav = new ModelAndView("adaptive_second_new");
		 
		 Company company = (Company)req.getSession().getAttribute("company");
		 User user = (User) req.getSession().getAttribute("user");
		 AdaptiveAssessment test = (AdaptiveAssessment) req.getSession().getAttribute("test");
		 JsonStatusBean bean = null;
		 try {
			bean = adaptiveAsswebservice.nextAdaptiveAssessmentQ(null, company.getCompanyId(), user.getEmail(), test.getAdaptiveAssessmentName(), "0", currentQuestion, req, res);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			String view = "testLinkNotEnabled";
			mav = new ModelAndView(view);
			mav.addObject("msgtype", "Information");
			mav.addObject("message", "Can not fetch the next Adaptive Question.");
			return mav;
		}
		 
		 if(bean.getErrorMessage() != null && bean.getErrorMessage().trim().length() > 0){
			
			 //mav.setViewName("adaptiveResults");
			
			 if(bean.getErrorMessage().equals(AdaptiveConstants.NUMBER_REGRESSIONS_EXCEEDED)){
				 mav = aResults1(req, res, false, user.getEmail(), user.getCompanyId(), test.getId());
				 mav.addObject("finish", true);
				 mav.addObject("user", user);
				 mav.addObject("test", test);
				 
				 mav.addObject("noofRegressionsExceeded", true);
				 mav.addObject("msgtype", "Information");
				 mav.addObject("message", "You exceeded the no of slides configured for the assessment");
			 }
			 else if(bean.getErrorMessage().equals(AdaptiveConstants.ADAPTIVE_ASSESSMENT_COMPLETE)){
				 mav = aResults1(req, res, true, user.getEmail(), user.getCompanyId(), test.getId());
				 mav.addObject("finish", true);
				 mav.addObject("user", user);
				 mav.addObject("test", test);
				 
				 mav.addObject("msgtype", "Information");
				 mav.addObject("message", "Congrats! You completed all the levels of the assessemnt");
				 mav.addObject("ASSESSMENT_COMPLETE", true);
			 }
			 return mav;
			 
		 }
		 else{
			 mav.addObject("finish", false);
			 
			 Boolean transitionNext = (Boolean) req.getSession().getAttribute("transitionNext");
			 if(transitionNext != null && transitionNext == true){
				 mav.addObject("transitionNext", true);
			 }
		 }
		 if(bean.getData() instanceof String){
			 if(bean.getData().equals(AdaptiveConstants.ADAPTIVE_ASSESSMENT_COMPLETE)){
				// mav.setViewName("adaptiveResults");
				 mav = aResults1(req, res, true, user.getEmail(), user.getCompanyId(), test.getId());
				 mav.addObject("finish", true);
				 mav.addObject("user", user);
				 mav.addObject("test", test);
				 mav.addObject("msgtype", "Information");
				 mav.addObject("message", "Congrats! You completed all the levels of the assessemnt");
				 return mav;
			 }
			 else{
				 //no of regressions exceeded
				// mav.setViewName("adaptiveResults");
				 mav = aResults1(req, res, false, user.getEmail(), user.getCompanyId(), test.getId());
				 mav.addObject("finish", true);
				 mav.addObject("user", user);
				 mav.addObject("test", test);
				 mav.addObject("msgtype", "Information");
				 mav.addObject("message", "You exceeded the no of slides configured for the assessment");
				 return mav;
			 }
		 }
		 else{
			 AdaptiveAssessmentResponseDto responseDto = (AdaptiveAssessmentResponseDto) bean.getData();
			    mav.addObject("test", test);
				mav.addObject("responseDto", responseDto);
				mav.addObject("currentQuestion", responseDto.getInstanceDTO());
				Integer noOfChoices = responseDto.getInstanceDTO().getQuestionMapperInstance().getQuestion().getNoOfChoices();
				mav.addObject("noOfChoices", noOfChoices);
				mav.addObject("questionType", responseDto.getInstanceDTO().getQuestionMapperInstance().getQuestion().getQuestionType().getType());
				mav.addObject("choices", responseDto.getInstanceDTO().getQuestionMapperInstance().getQuestion().getAllChoices());
				Integer index = (Integer) req.getSession().getAttribute("currentQuestionIndex");
				mav.addObject("sequence", index +1);
				
				Boolean transition = (Boolean)req.getSession().getAttribute("transitionNext");
					if(transition != null && transition == true){
					AdaptiveQuestionNavigationTransition trans = (AdaptiveQuestionNavigationTransition) req.getSession().getAttribute("transition");
						if(trans != null){
							
							String msg = "";
							if(trans.getProgression()){
								mav.addObject("msgtype", "Progression");
								msg += "Congragulations, You have cleared level -"+trans.getLevelOfOrigin().getLevel()+" and transited to level -"+trans.getLevelToGo().getLevel();
							}
							else{
								mav.addObject("msgtype", "Regression");
								msg += "Good Try, you have not cleared level -"+trans.getLevelOfOrigin().getLevel()+" and have regressed to "+trans.getLevelToGo().getLevel();
								AdaptiveAssessmentInstance instance = assessmentService.findAdaptiveAssessmentInstance(test.getAdaptiveAssessmentName(), user.getCompanyId(), user.getEmail());
								int leftReg = test.getNoOfRegressions() - instance.getNoofRegressions();
								msg += ". You have "+leftReg+" attempts left.";
							}
							
							mav.addObject("message", msg);
						}
						
					}
				
				return setStats(test, user, mav, req, res);
		 }
		 

	}
	
	
	
	
	private ModelAndView setStats(AdaptiveAssessment test, User user, ModelAndView mav, HttpServletRequest req, HttpServletResponse res) throws IOException{
		
		AdaptiveAssessmentInstance instance = assessmentService.findAdaptiveAssessmentInstance(test.getAdaptiveAssessmentName(), user.getCompanyId(), user.getEmail());
		mav.addObject("progressions", (instance == null ||instance.getNoOfProgressions() == null?0:instance.getNoOfProgressions()));
		mav.addObject("regressions", (instance == null || instance.getNoofRegressions() == null?0:instance.getNoofRegressions() ));
		if(instance == null || instance.getCurrentLevel() == null){
			mav.addObject("currentLevel", AdaptiveAssessmentSkillLevel.LEVEL1.getLevel());
			mav.addObject("norecomm", true);
			
			AdaptiveAssessmentSkill level = levelService.findUniqueLevelForAdaptiveAssessment(user.getCompanyId(), test.getAdaptiveAssessmentName(), AdaptiveAssessmentSkillLevel.LEVEL1);
			mav.addObject("currentLevelSkills", level.getQualifiers());
		}
		else{
			mav.addObject("currentLevel", instance.getCurrentLevel().getLevel());
			JsonStatusBean bean2 = adaptiveAsswebservice.recommsAdaptiveAssessmentLevel(null, user.getCompanyId(), user.getEmail(), test.getAdaptiveAssessmentName(), instance.getCurrentLevel().getLevel(), req, res);
			List<QualifierSkillLevelDto> recomms = (List<QualifierSkillLevelDto>) bean2.getData();
			mav.addObject("recommendations", recomms);
			AdaptiveAssessmentSkill level = levelService.findUniqueLevelForAdaptiveAssessment(user.getCompanyId(), test.getAdaptiveAssessmentName(), instance.getCurrentLevel());
			mav.addObject("currentLevelSkills", level.getQualifiers());
			String courseRecomm = level.getCourseLinkRecommendation();
			mav.addObject("currentCourseRecomm", courseRecomm);
		}
		
		AdaptiveAssessmentLevelInstance level1 = levelInstanceService.findUniqueAdaptiveAssessmentLevelInstanceLastAttempt(user.getCompanyId(), test.getAdaptiveAssessmentName(), user.getEmail(),  AdaptiveAssessmentSkillLevel.LEVEL1);
		if(level1 == null){
			mav.addObject("level1Score", 0);
		}
		else{
			Integer levvel1Score = level1.getAverageScoreForLevel().intValue();
			mav.addObject("level1Score", levvel1Score);
		}
		
		AdaptiveAssessmentLevelInstance level2 = levelInstanceService.findUniqueAdaptiveAssessmentLevelInstanceLastAttempt(user.getCompanyId(), test.getAdaptiveAssessmentName(), user.getEmail(),  AdaptiveAssessmentSkillLevel.LEVEL2);
		if(level2 == null){
			mav.addObject("level2Score", 0);
		}
		else{
			Integer levvel2Score = level2.getAverageScoreForLevel().intValue();
			mav.addObject("level2Score", levvel2Score);
		}
		
		AdaptiveAssessmentLevelInstance level3 = levelInstanceService.findUniqueAdaptiveAssessmentLevelInstanceLastAttempt(user.getCompanyId(), test.getAdaptiveAssessmentName(), user.getEmail(),  AdaptiveAssessmentSkillLevel.LEVEL3);
		if(level3 == null){
			mav.addObject("level3Score", 0);
		}
		else{
			Integer levvel3Score = level3.getAverageScoreForLevel().intValue();
			mav.addObject("level3Score", levvel3Score);
		}
		
		AdaptiveAssessmentLevelInstance level4 = levelInstanceService.findUniqueAdaptiveAssessmentLevelInstanceLastAttempt(user.getCompanyId(), test.getAdaptiveAssessmentName(), user.getEmail(),  AdaptiveAssessmentSkillLevel.LEVEL4);
		if(level4 == null){
			mav.addObject("level4Score", 0);
		}
		else{
			Integer levvel4Score = level4.getAverageScoreForLevel().intValue();
			mav.addObject("level4Score", levvel4Score);
		}
		
		AdaptiveAssessmentLevelInstance level5 = levelInstanceService.findUniqueAdaptiveAssessmentLevelInstanceLastAttempt(user.getCompanyId(), test.getAdaptiveAssessmentName(), user.getEmail(),  AdaptiveAssessmentSkillLevel.LEVEL5);
		if(level5 == null){
			mav.addObject("level5Score", 0);
		}
		else{
			Integer levvel5Score = level5.getAverageScoreForLevel().intValue();
			mav.addObject("level5Score", levvel5Score);
		}
		return mav;
	}


	@RequestMapping(value = "/aResults", method = RequestMethod.GET)
	  public ModelAndView   aResults(HttpServletRequest req, HttpServletResponse res, @RequestParam String startDate, @RequestParam String endDate,  @RequestParam String userId, @RequestParam String companyId, @RequestParam Long testId) {
		ModelAndView mav = new ModelAndView("adaptiveResults");
		User user = (User) req.getSession().getAttribute("user");
		 AdaptiveAssessment test = (AdaptiveAssessment) req.getSession().getAttribute("test");
		mav.addObject("user", user);
		mav.addObject("test", test);
		return mav;
	}
	
	@RequestMapping(value = "/aResults1", method = RequestMethod.GET)
	  public ModelAndView   aResults1(HttpServletRequest req, HttpServletResponse res, @RequestParam(required=false) Boolean clear,  @RequestParam String userId, @RequestParam String companyId, @RequestParam Long testId) throws IOException {
		ModelAndView mav = new ModelAndView("adaptive-final-result");
		User user = (User) req.getSession().getAttribute("user");
		 AdaptiveAssessment test = (AdaptiveAssessment) req.getSession().getAttribute("test");
		mav.addObject("user", user);
		mav.addObject("test", test);
		List<AdaptiveAssessmentLevelInstance> allInstances = levelInstanceService.findAllAdaptiveAssessmentLevelInstances(companyId, test.getAdaptiveAssessmentName(), user.getEmail());
		Map<AdaptiveAssessmentSkillLevel, List<AdaptiveAssessmentLevelInstance>> map = allInstances.stream().collect(Collectors.groupingBy(AdaptiveAssessmentLevelInstance::getLevel));
		List<AdaptiveAssessmentLevelInstance> collection = new ArrayList<AdaptiveAssessmentLevelInstance>();
		Float totScore = 0f;
		for(AdaptiveAssessmentSkillLevel level : map.keySet()){
			List<AdaptiveAssessmentLevelInstance> collForLevel = map.get(level);
			AdaptiveAssessmentLevelInstance max = Collections.max(collForLevel, Comparator.comparing(s -> s.getAttempt()));
			collection.add(max);
			AdaptiveAssessmentSkill lev = levelService.findUniqueLevelForAdaptiveAssessment(companyId, test.getAdaptiveAssessmentName(), level);
			max.setSkills(lev.getQualifiers().replace("NA", "").replace("-", "").trim());
			
			JsonStatusBean bean2 = adaptiveAsswebservice.recommsAdaptiveAssessmentLevel(null, user.getCompanyId(), user.getEmail(), test.getAdaptiveAssessmentName(), level.getLevel(), req, res);
			List<QualifierSkillLevelDto> recomms = (List<QualifierSkillLevelDto>) bean2.getData();
			max.setRecommendations(recomms);
			totScore += max.getAverageScoreForLevel();
		}
		
		List<AdaptiveAssessmentLevelInstance> failInstances = allInstances.stream().filter(item -> (item.getPass() == null || !item.getPass())).collect(Collectors.toList());
		Map<AdaptiveAssessmentSkillLevel,  List<AdaptiveAssessmentLevelInstance>> mapFail = failInstances.stream().collect(Collectors.groupingBy(AdaptiveAssessmentLevelInstance::getLevel));
			
		List<AdaptiveAreaOfImprovement> improvements = new ArrayList<>();	
			for(AdaptiveAssessmentSkillLevel level : mapFail.keySet()){
				AdaptiveAreaOfImprovement imp = new AdaptiveAreaOfImprovement();
				List<AdaptiveAssessmentLevelInstance> failedAtSameLevel = mapFail.get(level);
				imp.setAssessmentLevel(level.getLevel());
				String message = "";
					if(clear == null || !clear){
						message = "You have failed "+level.getLevel()+" "+failedAtSameLevel.size()+" times. Your overall assessment status is not successful as well";
					}
					else{
						message = "You have failed "+level.getLevel()+" "+failedAtSameLevel.size()+" times, though your overall assessment status is successful";
					}
				AdaptiveAssessmentSkill lev = levelService.findUniqueLevelForAdaptiveAssessment(companyId, test.getAdaptiveAssessmentName(), level);
				imp.setMessage(message);
				imp.setSkillsAssociated(lev.getQualifiers().replace("NA", "").replace("-", "").trim());
				imp.setRecommendedcourse(lev.getCourseLinkRecommendation() != null?lev.getCourseLinkRecommendation() : "https://www.udemy.com/course/motivation-for-nurses-30-days-of-praise-for-nurses/");
				imp.setRecommendedAssessment(lev.getAssessmentLinkAfterCourseCompletion() != null?lev.getAssessmentLinkAfterCourseCompletion(): "https://www.practiceaptitudetests.com/sign-up/programme/nursing");
				improvements.add(imp);
			}
		mav.addObject("improvements", improvements);
		//levels = Collections.sort(collection, (l1, l2) -> l1.getLevel().getLevel()));
		 Collections.sort(collection, new Comparator<AdaptiveAssessmentLevelInstance>() {
			
			 public int compare(AdaptiveAssessmentLevelInstance o1, AdaptiveAssessmentLevelInstance o2) {
		            // compare two instance of `Score` and return `int` as result.
		            return o1.getLevel().getLevel().compareTo(o2.getLevel().getLevel());
		        }
		});
		mav.addObject("levels", collection);
		
		Collections.sort(allInstances, new Comparator<AdaptiveAssessmentLevelInstance>() {
			
			 public int compare(AdaptiveAssessmentLevelInstance o1, AdaptiveAssessmentLevelInstance o2) {
		            // compare two instance of `Score` and return `int` as result.
		            return o1.getCreateDate().compareTo(o2.getCreateDate());
		        }
		});
		
		for(AdaptiveAssessmentLevelInstance ins  : allInstances){
			if(ins.getSkills() == null || ins.getSkills().trim().length() == 0){
				AdaptiveAssessmentSkill lev = levelService.findUniqueLevelForAdaptiveAssessment(companyId, test.getAdaptiveAssessmentName(), ins.getLevel());
				ins.setSkills(lev.getQualifiers().replace("NA", "").replace("-", "").trim());
			}
		}
		
		mav.addObject("alllevels", allInstances);
		
		AdaptiveAssessmentInstance instance = assessmentService.findAdaptiveAssessmentInstance(test.getAdaptiveAssessmentName(), companyId, user.getEmail());
		mav.addObject("instance", instance);
		Integer totalAverageScore = Math.round((totScore / 5));
		instance.setAverageScore(totalAverageScore);
		return mav;
	}
	
}
