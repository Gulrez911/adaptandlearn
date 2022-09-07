package com.assessment.web.controllers;

import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.Base64;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.assessment.common.PropertyConfig;
import com.assessment.common.util.PepipostEmailService;
import com.assessment.data.BookingSlot;
import com.assessment.data.BookingSlotInstance;
import com.assessment.data.Company;
import com.assessment.data.User;
import com.assessment.repositories.BookingSlotInstanceRepository;
import com.assessment.repositories.BookingSlotRepository;
import com.assessment.services.BookingSlotInstanceService;
import com.assessment.services.BookingSlotService;
import com.assessment.services.CompanyService;
import com.assessment.services.TestService;
import com.assessment.services.UserService;
import com.assessment.web.dto.BookingSlotDto;

@Controller
public class BookingUserController {

	
	@Autowired
	BookingSlotService slotService;
	
	@Autowired
	BookingSlotRepository slotRep;
	
	
	@Autowired
	BookingSlotInstanceService bookingSlotInstanceService;
	
	@Autowired
	CompanyService companyService;
	
	@Autowired
	PropertyConfig propertyConfig;
	
	@Autowired
	BookingSlotInstanceRepository instanceRep;
	
	@Autowired
	UserService userService;
	
	Random rand = new Random();
	
	@Autowired
	TestService testService;
	
	  @RequestMapping(value = "/bookingStep1", method = RequestMethod.POST)
	  public ModelAndView bokingUserStep1(HttpServletRequest request, HttpServletResponse response, @ModelAttribute("user") User user ) throws IOException {
		    ModelAndView mav = null;
		  Company company = companyService.findByCompanyName(propertyConfig.getTempCompanyName());
		  user.setCompanyId(company.getCompanyId());
		  user.setCompanyName(company.getCompanyName());
		  user = generateOtp(request, response, user);
		  String[] to = new String[3];
			to[0] = user.getEmail();
			to[1] = "jatin.sutaria@thev2technologies.com";
			to[2] = "contact@eassess.in";
			String subject= "OTP for Verification";
			File file = new File(propertyConfig.getOtpEmailFileLocation());
			String html = FileUtils.readFileToString(file);
			html = html.replace("{FULL_NAME}", user.getFirstName()+" "+user.getLastName());
			html = html.replace("{OTP}", user.getOtp());
		  PepipostEmailService client = new PepipostEmailService(to, subject, html, null, null);
		  Thread th = new Thread(client);
		  th.start();
		 request.getSession().setAttribute("user", user);
		  mav = new ModelAndView("registerAssOtp");
		  mav.addObject("user", user);
	  	return mav;
	  }
	  
	  @RequestMapping(value = "/bookingStep2", method = RequestMethod.POST)
	  public ModelAndView bokingUserStep2(HttpServletRequest request, HttpServletResponse response, @ModelAttribute("user") User user ) {
		    ModelAndView mav = null;
		 
		 User user2 = userService.findById(user.getId());
		 if(user2.getOtp()!= null && user.getTempOtp() != null && user.getTempOtp().equals(user2.getOtp())){
			 mav = bokingUserStep3(null, request, response, user2);
		 }
		 else{
			 mav = new ModelAndView("registerAssessment");
			 mav.addObject("msgtype", "Information");
			 mav.addObject("message", "OTP Validation Failed. Try generating OTP again. A click on Next button will do that");// later put it as label
			 mav.addObject("user", user2);
			 String testName = propertyConfig.getAssessmentNameForIT();
			 mav.addObject("testName", testName);
		 }
		
	  	return mav;
	  }
	  
	  @RequestMapping(value = "/bookingStep3", method = RequestMethod.POST)
	  public ModelAndView bokingUserStep3( @RequestParam(name="datestr", required=false) String datestr, HttpServletRequest request, HttpServletResponse response, @ModelAttribute("user") User user ) {
		    ModelAndView mav = new ModelAndView("bokingUserStep3");
		 
		 User user2 = userService.findById(user.getId());
		// bookingSlotInstanceService.
		 List<BookingSlotInstance> list = instanceRep.getBookingSlotInstancesForUser(user2.getCompanyId(), user2.getEmail());
		 mav.addObject("bookings", list);
		 	if(list.size() == 0){
		 		mav.addObject("bookingStyle", "none");
		 		mav.addObject("noBookingStyle", "");
		 		mav.addObject("noBookings", "You do not have any bookings yet");
		 	}
		 	else{
		 		mav.addObject("bookingStyle", "");
		 		mav.addObject("noBookingStyle", "none");
		 	}
		 mav.addObject("user", user2);
		 
		 com.assessment.data.Test test = testService.findbyTest(propertyConfig.getAssessmentNameForIT(), user2.getCompanyId());
		 mav.addObject("testName", propertyConfig.getAssessmentNameForIT());
		 mav.addObject("display", "none");
//		 for(BookingSlotInstance ins : list){
//			 Date d1 = ins.getSlot().getStart();
//			 Calendar c = Calendar.getInstance();
//			 c.setTime(d1);
//			 c.add(Calendar.DATE, 2);
//	 			Date d2 = c.getTime();
//	 			String sDate = Base64.getEncoder().encodeToString((""+d1.getTime()).getBytes());
//	 			String eDate = Base64.getEncoder().encodeToString((""+d2.getTime()).getBytes());
//	 			sDate = URLEncoder.encode(sDate);
//	 			eDate = URLEncoder.encode(eDate);
//			 String url = getUrlForUser(ins.getEmail(), test.getId(), user2.getCompanyId());
//			 url += "&inviteSent="+System.currentTimeMillis()+"&startDate="+sDate+"&endDate="+eDate;
//			 ins.setTestUrl(url);
//		 }
		
		 Set<BookingSlotDto> slotsDays = slotRep.getAllTimeCombs(user2.getCompanyId());
		 mav.addObject("times", slotsDays);
		 if(datestr != null){
			 mav.addObject("datestr", datestr);
			 String dat[] = datestr.split("/");
			 String day = dat[0];
			 String month = dat[1];
			 String year = dat[2];
			 List<BookingSlot> slots = slotService.getBookingSlotsForDay(user2.getCompanyId(), day, month, year);
			 	if(slots.size() == 0){
			 		mav.addObject("msgtype", "Information");
					mav.addObject("message", "No time slots are available for the chosen day. Please select another day");// later put it as label
					mav.addObject("datestr", null);
					return mav; 
			 	}
			 mav.addObject("availableSlots", slots);
			 mav.addObject("display", "");
			 
		 }
	  	return mav;
	  }
	  
	  
	  @RequestMapping(value = "/bookingStep4", method = RequestMethod.GET)
	  public ModelAndView bokingUserStep4( @RequestParam(name="slotid") Long slotid, @RequestParam(name="userid") Long userid, HttpServletRequest request, HttpServletResponse response ) {
		    ModelAndView mav = new ModelAndView("paymentGateway");
		    User user2 = userService.findById(userid);
		    BookingSlot slot = slotRep.findById(slotid).get();
		    mav.addObject("testName", propertyConfig.getAssessmentNameForIT());
		    mav.addObject("fullName", user2.getFirstName()+" "+user2.getLastName());
		    mav.addObject("bookingSlot", slot.getDateStr());
		    mav.addObject("costForAssessment", propertyConfig.getCostForAssessment());
		    mav.addObject("gstCharges", propertyConfig.getGstCharges());
		    mav.addObject("gatewayCharge", propertyConfig.getGatewayCharge());
		    
		    mav.addObject("slotid", slotid);
		    mav.addObject("userid", userid);
		    Float amt = propertyConfig.getCostForAssessment() + propertyConfig.getGstCharges() + propertyConfig.getGatewayCharge();
		    Integer amount = amt.intValue();
		    mav.addObject("amount",amount);
		    return mav;
//		    BookingSlotInstance ins = new BookingSlotInstance();
//		    ins.setCompanyId(user2.getCompanyId());
//		    ins.setEmail(user2.getEmail());
//		    ins.setFirstName(user.getFirstName());
//		    ins.setLastName(user.getLastName());
//		    ins.setMobile(user2.getMobileNumber());
//		    ins.setTestName(propertyConfig.getAssessmentNameForIT());
//		    Date d1 = ins.getSlot().getStart();
//			Calendar c = Calendar.getInstance();
//			c.setTime(d1);
//			c.add(Calendar.DATE, 2);
//	 		Date d2 = c.getTime();
//	 		String sDate = Base64.getEncoder().encodeToString((""+d1.getTime()).getBytes());
//	 		String eDate = Base64.getEncoder().encodeToString((""+d2.getTime()).getBytes());
//	 		sDate = URLEncoder.encode(sDate);
//	 		eDate = URLEncoder.encode(eDate);
//	 		com.assessment.data.Test test = testService.findbyTest(propertyConfig.getAssessmentNameForIT(), user2.getCompanyId());
//			String url = getUrlForUser(ins.getEmail(), test.getId(), user2.getCompanyId());
//			url += "&inviteSent="+System.currentTimeMillis()+"&startDate="+sDate+"&endDate="+eDate;
//			ins.setTestUrl(url);
//			ins.setSlot(slot);
//			bookingSlotInstanceService.saveOrUpdate(ins);
		    
	  }
	  
	  //bokingUserStep4
	  
	  public synchronized User generateOtp(HttpServletRequest httpRequest,HttpServletResponse httpResponse,User user){
		   // Payment Acknowledgment process here
		  String otp = String.format("%04d", rand.nextInt(10000));
		  user.setOtp(otp);
		  user.setPassword(user.getEmail().hashCode()+"");
		  userService.saveOrUpdate(user);
		  return user;
		}
	  
	  
	  
}
