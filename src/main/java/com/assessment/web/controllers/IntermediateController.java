package com.assessment.web.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.assessment.common.PropertyConfig;
import com.assessment.data.AssessmentUploadData;
import com.assessment.data.CategoryContext;
import com.assessment.repositories.AssessmentUploadDataRepository;
import com.assessment.repositories.CategoryContextRepository;
import com.assessment.services.AssessmentUploadDataService;
import com.assessment.services.CategoryContextService;
import com.assessment.services.TestService;

@Controller
public class IntermediateController {
	
	@Autowired
	CategoryContextService categoryContextService;
	
	@Autowired
	CategoryContextRepository categoryContextRepository;
	
	@Autowired
	AssessmentUploadDataService assessmentUploadDataService;
	
	@Autowired
	TestService testservice;
	
	@Autowired
	AssessmentUploadDataRepository uploadRep;
	
	@Autowired
	PropertyConfig propertyConfig;

	
	@RequestMapping(value = "/intermediate", method = RequestMethod.GET)
	public ModelAndView intermediate( @RequestParam Long l4id, HttpServletRequest request, HttpServletResponse response){
		ModelAndView mav = new ModelAndView("intermediate");
		String companyId = propertyConfig.getDefaultCompanyId();
		if(companyId == null || companyId.trim().length() == 0){
			companyId = "AL";
		}
		
		CategoryContext level4 = categoryContextRepository.findById(l4id).get();
		mav.addObject("level1", level4.getLevel1());
		mav.addObject("level2", level4.getLevel2());
		mav.addObject("level3", level4.getLevel3());
		mav.addObject("level4", level4.getLevel4());
		AssessmentUploadData dt = uploadRep.findByPrimaryKey3(companyId, level4.getAssessmentUpoloadDataName()).get(0);
		String ass1 = dt.getAssessmentName1();
		String ass2 = dt.getAssessmentName2();
		String ass3 = dt.getAssessmentName3();
		String ass4 = dt.getAssessmentName4();
		String ass5 = dt.getAssessmentName5();
		
		String ass1_url = dt.getAssessmentPublicLink1();
		String ass2_url = dt.getAssessmentPublicLink2();
		String ass3_url = dt.getAssessmentPublicLink3();
		String ass4_url = dt.getAssessmentPublicLink4();
		String ass5_url = dt.getAssessmentPublicLink5();
		
		String adaptive = dt.getAdaptiveAssessmentName();
		
		String adaptive_ass_url = dt.getAdaptiveAssessmentPublicLink();
		mav.addObject("ass1", ass1);
		mav.addObject("ass2", ass2);
		mav.addObject("ass3", ass3);
		mav.addObject("ass4", ass4);
		mav.addObject("ass5", ass5);
		
		mav.addObject("ass1_url", ass1_url);
		mav.addObject("ass2_url", ass2_url);
		mav.addObject("ass3_url", ass3_url);
		mav.addObject("ass4_url", ass4_url);
		mav.addObject("ass5_url", ass5_url);
		
		mav.addObject("adaptive_ass", adaptive);
		mav.addObject("adaptive_ass_url", adaptive_ass_url);
		
		return mav;
	}
}
