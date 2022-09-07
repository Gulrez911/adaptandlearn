package com.assessment.web.controllers;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.MediaType;

import org.apache.commons.io.FileUtils;
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
import com.assessment.web.dto.BookingSlotDto;

@Controller
public class BookingController {
	
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
	
	@RequestMapping(value = "/showSlots", method = RequestMethod.GET)
	public ModelAndView showsAlllots( HttpServletRequest request, HttpServletResponse response)
								throws Exception {
		ModelAndView mav = new ModelAndView("slots");
		User user = (User) request.getSession().getAttribute("user");
		
		List<BookingSlot> slots = slotService.findAllBookingSlots(user.getCompanyId());

		mav.addObject("slots", slots);
		Set<BookingSlotDto> times = slotRep.getAllTimeCombs(user.getCompanyId());
		mav.addObject("times", times);
		//CommonUtil.setCommonAttributesOfPagination(campaigns, mav.getModelMap(), pageNumber, "showCampaigns", null);
		return mav;
	}
	
	
	@RequestMapping(value = "/disableSlot", method = RequestMethod.GET)
	public ModelAndView disableSlot( @RequestParam(name="sid") Long sid, HttpServletRequest request, HttpServletResponse response)
								throws Exception {
		ModelAndView mav = new ModelAndView("slots");
		User user = (User) request.getSession().getAttribute("user");
		BookingSlot slot = slotRep.findById(sid).get();
		
		slotService.disable(slot);
		
		List<BookingSlot> slots = slotService.findAllBookingSlots(user.getCompanyId());

		mav.addObject("slots", slots);
		mav.addObject("message", "Slot Disabled");// later put it as label
		mav.addObject("msgtype", "Information");
		Set<BookingSlotDto> times = slotRep.getAllTimeCombs(user.getCompanyId());
		mav.addObject("times", times);
		//CommonUtil.setCommonAttributesOfPagination(campaigns, mav.getModelMap(), pageNumber, "showCampaigns", null);
		return mav;
	}
	
	@RequestMapping(value = "/enableSlot", method = RequestMethod.GET)
	public ModelAndView enableSlot( @RequestParam(name="sid") Long sid, HttpServletRequest request, HttpServletResponse response)
								throws Exception {
		ModelAndView mav = new ModelAndView("slots");
		User user = (User) request.getSession().getAttribute("user");
		BookingSlot slot = slotRep.findById(sid).get();
		
		slotService.enable(slot);
		
		List<BookingSlot> slots = slotService.findAllBookingSlots(user.getCompanyId());

		mav.addObject("slots", slots);
		mav.addObject("message", "Slot Enabled");// later put it as label
		mav.addObject("msgtype", "Information");
		Set<BookingSlotDto> times = slotRep.getAllTimeCombs(user.getCompanyId());
		mav.addObject("times", times);
		//CommonUtil.setCommonAttributesOfPagination(campaigns, mav.getModelMap(), pageNumber, "showCampaigns", null);
		return mav;
	}
	
	@RequestMapping(value = "/showSlotsForDay", method = RequestMethod.GET)
	public ModelAndView showslotsForDay(@RequestParam(name="day") String day, @RequestParam(name="month") String month,  @RequestParam(name="year") String year, HttpServletRequest request, HttpServletResponse response)
								throws Exception {
		ModelAndView mav = new ModelAndView("slots");
		User user = (User) request.getSession().getAttribute("user");
		
		List<BookingSlot> slots = slotService.getAllBookingSlotsForDay_Filled_Unfilled(user.getCompanyId(), day, month, year);

		mav.addObject("slots", slots);
		Set<BookingSlotDto> times = slotRep.getAllTimeCombs(user.getCompanyId());
		mav.addObject("times", times);
		mav.addObject("message", "Showing slots for "+(day+"/"+month+"/"+year));// later put it as label
		mav.addObject("msgtype", "Information");
		//CommonUtil.setCommonAttributesOfPagination(campaigns, mav.getModelMap(), pageNumber, "showCampaigns", null);
		return mav;
	}
	
	@RequestMapping(value = "/showAvaliableSlotsForDay", method = RequestMethod.GET)
	public ModelAndView showAvaliableSlotsForDay(@RequestParam(name="day") String day, @RequestParam(name="month") String month,  @RequestParam(name="year") String year, HttpServletRequest request, HttpServletResponse response)
								throws Exception {
		ModelAndView mav = new ModelAndView("slots");
		User user = (User) request.getSession().getAttribute("user");
		
		List<BookingSlot> slots = slotService.getBookingSlotsForDay(user.getCompanyId(), day, month, year);

		mav.addObject("slots", slots);
		Set<BookingSlotDto> times = slotRep.getAllTimeCombs(user.getCompanyId());
		mav.addObject("times", times);
		mav.addObject("msgtype", "Information");
		mav.addObject("message", "Showing only available slots for "+(day+"/"+month+"/"+year));// later put it as label
		//CommonUtil.setCommonAttributesOfPagination(campaigns, mav.getModelMap(), pageNumber, "showCampaigns", null);
		return mav;
	}
	
	@RequestMapping(value = "/saveSlot", method = RequestMethod.POST, produces=MediaType.APPLICATION_JSON)
	public @ResponseBody String saveSlot(@RequestParam(name="companyId") String companyId, @RequestParam(name="day") String day, @RequestParam(name="month") String month,  @RequestParam(name="year") String year, @RequestParam(name="timestr") String timestr, @RequestBody BookingSlot slot, HttpServletRequest request, HttpServletResponse response)
								throws Exception {
		
		
		Company company = companyService.findByCompanyId(companyId);
			if(company == null){
				return "Wrong Company Identifier";
			}
			
			 String dateStr = day+"/"+month+"/"+year+" "+timestr;
		        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy hh:mm aa");
		        Date start = dateFormat.parse(dateStr);
		        Calendar cal = Calendar.getInstance();
		        cal.setTime(start);
		        cal.add(Calendar.HOUR, 1);
		        Date end = cal.getTime();
			
			slot.setCompanyId(companyId);
			slot.setCompanyName(company.getCompanyName());
			slot.setStart(start);
			slot.setEnd(end);
			slotService.saveOrUpdate(slot);
			
		//CommonUtil.setCommonAttributesOfPagination(campaigns, mav.getModelMap(), pageNumber, "showCampaigns", null);
		return "ok";
	}
	
	@RequestMapping(value = "/showParticipantBookingForSlot", method = RequestMethod.GET)
	public ModelAndView showParticipantBookingForSlot(@RequestParam(name="sid") Long sid, HttpServletRequest request, HttpServletResponse response)
								throws Exception {
		ModelAndView mav = new ModelAndView("slotInstances");
		User user = (User) request.getSession().getAttribute("user");
		BookingSlot slot = slotRep.findById(sid).get();
			if(slot == null){
				mav = showsAlllots(request, response);
				mav.addObject("msgtype", "Information");
				mav.addObject("message", "Invalid slot Identifier passed");// later put it as label
				return mav;
			}
		List<BookingSlotInstance> instances = bookingSlotInstanceService.getBookingSlotInstancesForSlot(user.getCompanyId(), sid);
		mav.addObject("instances", instances);
		mav.addObject("msgtype", "Information");
		mav.addObject("message", "Showing participants for "+slot.getDay()+"/"+slot.getMonth()+"/"+slot.getYear());// later put it as label
		return mav;
	}
	
	@RequestMapping(value = "/sendEmailToBookedClient", method = RequestMethod.GET, produces=MediaType.APPLICATION_JSON)
	public @ResponseBody String saveSlot( @RequestParam(name="instanceId") Long instanceId, HttpServletRequest request, HttpServletResponse response)
								throws Exception {
		
		
		
		BookingSlotInstance instance = instanceRep.findById(instanceId).get();
			if(instance == null){
				return "Email can not be send - Wrong Identifier passed";
			}
		String html = FileUtils.readFileToString(new File(propertyConfig.getBookingTestLink()))	 ;
		html = html.replace("{FULL_NAME}", instance.getFirstName()+" "+instance.getLastName());
		html = html.replace("${TIME_ACTIVATION}", instance.getSlot().getDateStr());
		html = html.replace("{TEST_NAME} ", instance.getTestName());
		html = html.replace("{URL}", instance.getTestUrl());
		String[] to = new String[3];
		to[0] = instance.getEmail();
		to[1] = "jatin.sutaria@thev2technologies.com";
		to[2] = "contact@eassess.in";
		
		
		String subject = "Assessment Link on "+instance.getSlot().getDateStr();
		PepipostEmailService client = new PepipostEmailService(to, subject, html, propertyConfig.getBookingEmailFailureLocation(), instanceId);
		//EmailGenericMessageThread client = new EmailGenericMessageThread(instance.getEmail(), "Test Link", html, propertyConfig);
		Thread t = new Thread(client);
		t.start();
		return "Email with Test Link send to Client";
	}

}
