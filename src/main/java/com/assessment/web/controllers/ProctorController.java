package com.assessment.web.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.assessment.services.ProctorTrackService;
import com.assessment.web.dto.ProctorInstructorSignInDto;


@Controller
public class ProctorController {
	@Autowired
	ProctorTrackService proctorTrackservice;
	
	@RequestMapping(value = "/fetchInstructorDashboardUrl", method = RequestMethod.GET)
	public @ResponseBody String fetchInstructorDashboardUrl( HttpServletRequest request, HttpServletResponse response){
		try {
			ProctorInstructorSignInDto dto = new ProctorInstructorSignInDto();
			dto.setEmail("instructorNew@iiht.com");
			dto.setFirst_name("Instructor");
			dto.setLast_name("IIHT");
			dto.setGroup_id("IIHT_Single_Ins");
			dto.setRole("instructor");
			dto.setUser_id("instructorNew@iiht.com");
			String url = proctorTrackservice.getInstructorUrl(dto);
			return url;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "problem";
		}
	}
	
	@RequestMapping(value = "/instructorDashboard", method = RequestMethod.GET)
	  public ModelAndView hackathon(HttpServletRequest request, HttpServletResponse response) {
	    ModelAndView mav = new ModelAndView("instructorDashboard");
	    String url = fetchInstructorDashboardUrl(request, response);
	    mav.addObject("instructorUrl", url);
	    return mav;
	  }


}
