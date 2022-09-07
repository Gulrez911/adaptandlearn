package com.assessment.web.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.assessment.data.AssessmentUploadData;
import com.assessment.data.User;
import com.assessment.repositories.AssessmentUploadDataRepository;
import com.assessment.services.AssessmentUploadDataService;

@Controller
public class AssessmentDataUploadRetrievalController {
	
	@Autowired
	AssessmentUploadDataService dataService;
	
	@Autowired
	AssessmentUploadDataRepository repository;
	
	@RequestMapping(value = "/showAutomatedAssessmentsArea", method = RequestMethod.GET)
	public ModelAndView showsAlllots(@RequestParam(name= "domain", required = false) String domain,@RequestParam(name= "msg", required = false) String msg,  HttpServletRequest request, HttpServletResponse response)
								throws Exception {
		//ModelAndView mav = new ModelAndView("automatedAssForDomain");
		User user = (User) request.getSession().getAttribute("user");
			if(domain == null){
				domain = "Hospitals_Pharmacy";
			}
		
		List<AssessmentUploadData> data = dataService.findAssessmentsByDomain(user.getCompanyId(), domain);
		ModelAndView mav = new ModelAndView("showAutomatedAssessmentsArea");
		mav.addObject("data", data);
		mav.addObject("domain", domain);
			if(msg != null){
				mav.addObject("msgtype", "Information");
				mav.addObject("message", msg);
				mav.addObject("icon", "Information");
			}
		
		return mav;
		//mav.addObject("assessments", data);
		//return mav;
	}

}
