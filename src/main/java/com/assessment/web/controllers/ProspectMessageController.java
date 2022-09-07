package com.assessment.web.controllers;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.assessment.common.PropertyConfig;
import com.assessment.common.util.EmailGenericMessageThread;
import com.assessment.data.Company;
import com.assessment.data.ProspectMessage;
import com.assessment.data.User;
import com.assessment.services.CompanyService;
import com.assessment.services.ProspectMessageService;

@Controller
public class ProspectMessageController {
	@Autowired
	ProspectMessageService service;
	
	@Autowired
	PropertyConfig propertyConfig;
	
	@Autowired
	CompanyService companyService;
	
	@RequestMapping(value = "/contact", method = RequestMethod.GET)
	  public ModelAndView prospect(HttpServletRequest request, HttpServletResponse response) {
		ProspectMessage message = new ProspectMessage();
		ModelAndView mav = new ModelAndView("submitFeedback");
		mav.addObject("prospect", message);
		mav.addObject("source", "Contact Us");
		User user = new User();
		mav.addObject("user", user);
		return mav;
	}
	
	@RequestMapping(value = "/feedback", method = RequestMethod.GET)
	  public ModelAndView feedback(@RequestParam String testName, HttpServletRequest request, HttpServletResponse response) {
		ProspectMessage message = new ProspectMessage();
		ModelAndView mav = new ModelAndView("submitFeedback");
		mav.addObject("prospect", message);
		mav.addObject("source", "Feedback Form");
		mav.addObject("testName", testName);
		User user = (User) request.getSession().getAttribute("user");
		mav.addObject("user", user);
		return mav;
	}
	
	

	@RequestMapping(value = { "/saveProspect" }, method = { RequestMethod.POST }, consumes = { "application/json" })
    @ResponseBody
    public String saveProspect(@RequestParam String source, @RequestParam (required = false) String testName, final HttpServletRequest request, final HttpServletResponse response, @RequestBody final ProspectMessage message) {
        final Company company = this.companyService.findByCompanyName(this.propertyConfig.getCompanyName());
        String companyId = propertyConfig.getCompanyId();
        String companyName = propertyConfig.getCompanyName();
        	if(companyId == null){
        		companyId = "AL";
        	}
        	
        	if(companyName == null){
        		companyName = "AL";
        	}
        
        	String subject = "";
        	if(source != null && source.contains("Contact")){
        		subject = "Contact";
        		message.setMsgType("Inquiry");
        	}
        	else{
        		subject = "Feedback on "+testName;
        		message.setTestName(testName);
        		message.setMsgType("Test Feedback");
        	}
        message.setCompanyName("AL");
        message.setCompanyId("AL");
        message.setCreateDate(new Date());
        this.service.addProspectMessage(message);
        String msg = "";
        msg = msg + "<h2> From " + message.getFirstName() + " " + message.getLastName() + "</h2><br/><br/>";
        msg = msg + "<b>Email - " + message.getEmail() + " </b><br/><br/>";
        msg = msg + "<b>Message - " + message.getMessage() + " </b><br/><br/><br/>";
        msg += "Thanks - Web Hit";
        final EmailGenericMessageThread genericMessageThread = new EmailGenericMessageThread("jatin.sutaria@thev2technologies.com", subject, msg, this.propertyConfig);
        final Thread thread = new Thread((Runnable)genericMessageThread);
        thread.start();
        return "ok";
    }

}
