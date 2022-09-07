package com.assessment.web.controllers;

import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Base64;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.assessment.data.FooterUTF;
import com.assessment.data.PublicTestUTF;
import com.assessment.data.User;
import com.assessment.repositories.FooterUTFRepository;
import com.assessment.repositories.PublicTestUTFRepository;
import com.assessment.services.UserService;

@Controller
public class DemoAreaController {
	
	@Autowired
	UserService userService;
	
	@Autowired
	PublicTestUTFRepository publicTestUTFRepository;
	
	@Autowired
	FooterUTFRepository footerUTFRepository;
	
	@RequestMapping(value = "/demoIT", method = RequestMethod.GET)
	public ModelAndView demoIT( HttpServletRequest request, HttpServletResponse response)
								throws Exception {
		ModelAndView mav = new ModelAndView("demo_IT");
		
		return mav;
	}
	
	@RequestMapping(value = "/showDemoRegistration", method = RequestMethod.GET)
	public ModelAndView showDemoRegistration( @RequestParam(required=true) String startTest,  HttpServletRequest request, HttpServletResponse response)
								throws Exception {
		ModelAndView mav = new ModelAndView("demoArea");
		User user = new User();
		
		mav.addObject("user", user);
		mav.addObject("registration", "");
		mav.addObject("login", "none");
		
		request.getSession().setAttribute("startTest", startTest);
		return mav;
	}
	
	@RequestMapping(value = "/showDemoLogin", method = RequestMethod.GET)
	public ModelAndView showDemoLogin(   HttpServletRequest request, HttpServletResponse response)
								throws Exception {
		ModelAndView mav = new ModelAndView("demoArea");
		User user = new User();
		user.setPassword("1234");
		mav.addObject("user", user);
		mav.addObject("login", "");
		mav.addObject("registration", "none");
		return mav;
	}
	
	@RequestMapping(value = "/demoUserauthenticate", method = RequestMethod.POST)
	  public ModelAndView demoUserauthenticate(HttpServletRequest request, HttpServletResponse response, @ModelAttribute("user") User user) {
		  user.setCompanyId("AL");
		  user.setCompanyName("AL");
		  user.setPassword("1234");
		  userService.saveOrUpdate(user);
		  String startTest = (String) request.getSession().getAttribute("startTest");
		  
		  
		  	if(startTest.contains("testtaker-campaign") ){
		  		startTest = startTest.replace("$EMAIL", URLEncoder.encode(Base64.getEncoder().encodeToString(user.getEmail().getBytes())));
		  		
		 	   
		  	}
		  	else{
		  		startTest = startTest.replace("$EMAIL", user.getEmail());
		  	}
		  	
		  request.getSession().setAttribute("lang", "eng");
		  request.getSession().setAttribute("user", user);
		  ModelAndView mav = new ModelAndView("redirect:" + startTest);
		  FooterUTF footerUTF= footerUTFRepository.findByLanguage("eng");
		  mav.addObject("footerUTF", footerUTF);
		  return mav;
	}
	
	@RequestMapping(value = "/startDemoTest", method = RequestMethod.GET)
	public ModelAndView goToDemoArea( @RequestParam(name="startTest") String startTest,  HttpServletRequest request, HttpServletResponse response)
								throws Exception {
		User user = (User) request.getSession().getAttribute("user");
		
		  if(user != null && user.getEmail().contains("[")){
			System.out.println("email is "+user.getEmail());
			String email = user.getEmail().substring(0, user.getEmail().lastIndexOf("["));
			System.out.println("email is "+email);
			user.setEmail(email);
		  }
		String requestURL = URLDecoder.decode(startTest);
				
			
			if(user == null){
				request.getSession().setAttribute("startTest", requestURL);
				return showDemoRegistration(requestURL, request, response);
			}
			
			if(requestURL.contains("testtaker-campaign")){
				requestURL = requestURL.replace("$EMAIL", URLEncoder.encode(Base64.getEncoder().encodeToString(user.getEmail().getBytes())));
		  	}
		  	else{
		  		requestURL = requestURL.replace("$EMAIL", user.getEmail());
		  	}	
			request.getSession().setAttribute("lang", "eng");
		ModelAndView mav = new ModelAndView("redirect:" + requestURL);
		FooterUTF footerUTF= footerUTFRepository.findByLanguage("eng");
		mav.addObject("footerUTF", footerUTF);
		return mav;
	}

}
