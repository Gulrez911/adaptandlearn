package com.assessment.web.controllers;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.MediaType;

import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.assessment.common.PropertyConfig;
import com.assessment.common.util.PepipostEmailService;
import com.assessment.data.BookingSlot;
import com.assessment.data.BookingSlotInstance;
import com.assessment.data.User;
import com.assessment.repositories.BookingSlotInstanceRepository;
import com.assessment.repositories.BookingSlotRepository;
import com.assessment.services.BookingSlotInstanceService;
import com.assessment.services.BookingSlotService;
import com.assessment.services.CompanyService;
import com.assessment.services.TestService;
import com.assessment.services.UserService;
import com.assessment.web.dto.PaymentRequestResponse;
import com.assessment.web.dto.WebHookDto;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
public class PaymentController {

	
	
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
	
	@RequestMapping(value = "/redirectToPaymentGateway", method = RequestMethod.POST)
	  public String  redirectToPaymentGateway(@RequestParam(name="amount") Integer total, @RequestParam(name="slotid") Long slotid, @RequestParam(name="userid") Long userid,HttpServletRequest req, HttpServletResponse res) {
		User user = userService.findById(userid);
		BookingSlot slot = slotRep.findById(slotid).get();
		PaymentRequestResponse response = null;
		req.getSession().setAttribute("user", user);
		req.getSession().setAttribute("slot", slot);
		try {
			String data = sendRequestToInstaMojo(user, total, slot);
			ObjectMapper mapper = new ObjectMapper();
			response = mapper.readValue(data.getBytes(), PaymentRequestResponse.class);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "errorPaymentGateway";
		} 
		
		if(response == null || response.getPayment_request() == null || response.getPayment_request().getLongurl() == null || response.getPayment_request().getLongurl().trim().length() ==0 ){
			return "errorPaymentGateway";
		}
		
		return "redirect:" +response.getPayment_request().getLongurl();
	  }
	
	@RequestMapping(value = "/redirectBooking", method = RequestMethod.GET)
	  public ModelAndView  redirectBooking(@RequestParam(name="payment_status") String payment_status , @RequestParam(name="payment_id") String payment_id , @RequestParam(name="payment_request_id") String payment_request_id, HttpServletRequest req, HttpServletResponse res) {
		System.out.println("redirect booking");
		
		User user = (User) req.getSession().getAttribute("user");
		BookingSlot slot = (BookingSlot) req.getSession().getAttribute("slot");
		System.out.println("user is "+user.getEmail()+" payment status is "+payment_status+" pid "+payment_id+" pay req id "+payment_request_id);
	    BookingSlotInstance ins = new BookingSlotInstance();
	    ins.setPayment_id(payment_id);
	    ins.setPayment_status(payment_status);
	    ins.setPayment_request_id(payment_request_id);
	    Float tot = propertyConfig.getCostForAssessment() + propertyConfig.getGstCharges() + propertyConfig.getGatewayCharge();
	    
	    ins.setAmount(tot.intValue());
	    
	    ins.setCompanyId(user.getCompanyId());
	    ins.setCompanyName(user.getCompanyName());
	    ins.setEmail(user.getEmail());
	    ins.setFirstName(user.getFirstName());
	    ins.setLastName(user.getLastName());
	    ins.setMobile(user.getMobileNumber());
	    ins.setTestName(propertyConfig.getAssessmentNameForIT());
	    ins.setSlot(slot);
	    Date d1 = ins.getSlot().getStart();
		Calendar c = Calendar.getInstance();
		c.setTime(d1);
		c.add(Calendar.DATE, 2);
 		Date d2 = c.getTime();
 		String sDate = Base64.getEncoder().encodeToString((""+d1.getTime()).getBytes());
 		String eDate = Base64.getEncoder().encodeToString((""+d2.getTime()).getBytes());
 		sDate = URLEncoder.encode(sDate);
 		eDate = URLEncoder.encode(eDate);
 		com.assessment.data.Test test = testService.findbyTest(propertyConfig.getAssessmentNameForIT(), user.getCompanyId());
		String url = getUrlForUser(ins.getEmail(), test.getId(), user.getCompanyId());
		url += "&inviteSent="+System.currentTimeMillis()+"&startDate="+sDate+"&endDate="+eDate;
		ins.setTestUrl(url);
		
		bookingSlotInstanceService.saveOrUpdate(ins);
		String file = propertyConfig.getPaymentRedirectFileLocation();
		try {
			String html = FileUtils.readFileToString(new File(file));
			html = html.replace("{FULL_NAME}", user.getFirstName()+" "+user.getLastName());
			html = html.replace("{TEST_NAME}", propertyConfig.getAssessmentNameForIT());
			String[] to = new String[3];
			to[0] = user.getEmail();
			to[1] = "jatin.sutaria@thev2technologies.com";
			to[2] = "contact@eassess.in";
			PepipostEmailService client = new PepipostEmailService(to, "eAssess Order Initiation request", html, null, null);
			Thread th = new Thread(client);
			th.start();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		ModelAndView mav = new ModelAndView("redirectBook");
		mav.addObject("fullname", user.getFirstName()+" "+user.getLastName());
		mav.addObject("payment_status", payment_status);
		mav.addObject("payment_id", payment_id);
		mav.addObject("payment_request_id", payment_request_id);
		mav.addObject("testName", propertyConfig.getAssessmentNameForIT());
		return mav;
	}
	
	private String getUrlForUser(String user, Long testId, String companyId) {
		 String userBytes =  Base64.getEncoder().encodeToString(user.getBytes());
		 
		 String after = "userId="+URLEncoder.encode(userBytes)+"&testId="+URLEncoder.encode(testId.toString())+"&companyId="+URLEncoder.encode(companyId);
		 String url = propertyConfig.getBaseUrl()+"mcq2?"+after;
		 return url;
	  }
	
	private String sendRequestToInstaMojo(User user, Integer cost, BookingSlot slot) throws RuntimeException, Exception{
		String url = "https://www.instamojo.com/api/1.1/payment-requests/";
		URL url2 = new URL(url);
		HttpURLConnection conn = (HttpURLConnection) url2.openConnection();
		conn.setRequestMethod("POST");
		conn.setRequestProperty("X-Api-Key", "c23b6989f57693b73a129b54d45715a2");
		conn.setRequestProperty("X-Auth-Token", "8ae60235f52686382ffe49a0f475d628");
		conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
		String purpose = URLEncoder.encode(user.getId()+"-"+slot.getId());
		String buyer_name=URLEncoder.encode(user.getFirstName()+" "+user.getLastName());
		String email = URLEncoder.encode(user.getEmail());
		String mobile = processMobileNo(user.getMobileNumber());
		String redirectUrl = URLEncoder.encode("https://eassess.in/redirectBooking");
		String webHookUrl = URLEncoder.encode("https://eassess.in/webhookBooking");
		Boolean allow_repeated_payments = false;
		Boolean send_email = true;
		Boolean send_sms = false;
		
		String params = "amount="+cost+"&purpose="+purpose+"&buyer_name="+buyer_name+"&email="+email+"&phone="+mobile+"&redirect_url="+redirectUrl+"&webhook="+webHookUrl+"&allow_repeated_payments="+allow_repeated_payments+"&send_email="+send_email+"&send_sms="+send_sms;
		byte[] postData = params.getBytes( StandardCharsets.UTF_8 );
		int postDataLength = postData.length;
		conn.setRequestProperty("charset", "utf-8");
		conn.setRequestProperty("Content-Length", Integer.toString(postDataLength ));
		conn.setUseCaches(false);
		conn.setDoOutput(true);
		try(DataOutputStream wr = new DataOutputStream(conn.getOutputStream())) {
		   wr.write( postData );
		}
		String data = getResponse(conn);
		return data;
	}
	
	
	public String getResponse(HttpURLConnection con) {
		if(con!=null){
			
			try {
				
			   BufferedReader br = 
				new BufferedReader(
					new InputStreamReader(con.getInputStream()));
						
			   String input;
			   String output="";
						
			   while ((input = br.readLine()) != null){
				   output +=input;
			   }
			   br.close();
			   return output;
						
			} catch (IOException e) {
			   e.printStackTrace();
			}
					
		       }
				
		   return null;
	}
	
	
	public T getResponse(HttpURLConnection con, Class<T> cl) {
		if(con!=null){
			
			try {
				
			   BufferedReader br = 
				new BufferedReader(
					new InputStreamReader(con.getInputStream()));
						
			   String input;
			   String output="";
						
			   while ((input = br.readLine()) != null){
				   output +=input;
			   }
			   br.close();
			   ObjectMapper mapper = new ObjectMapper();
			   return mapper.readValue(output.getBytes(), cl);
			  
						
			} catch (IOException e) {
			   e.printStackTrace();
			}
					
		       }
				
		   return null;
	}
	
	private String processMobileNo(String mobileN0){
		try{
			Long.parseLong(mobileN0);
			if(mobileN0.length() != 10){
				return "9930070660"; 
			}
			else{
				return mobileN0;
			}
		}
		catch(NumberFormatException e){
			return "9930070660";
		}
	}
	
	
	@RequestMapping(value = "/webhookBooking", method = RequestMethod.POST, consumes=MediaType.APPLICATION_FORM_URLENCODED)
	  public @ResponseBody String  webhookBooking(HttpServletRequest req, HttpServletResponse res, @RequestParam MultiValueMap<String,String> map) {
		System.out.println("map is "+map);
		System.out.println("size "+map.keySet().size());
		for(String key: map.keySet()){
			System.out.println("key "+key+" and value is "+map.get(key)+" size "+map.get(key).size());
			
		}
		
		return "ok";
		
//		String purpose = map.get("purpose").get(0);
//		System.out.println("purpose "+purpose);
//		purpose = URLDecoder.decode(purpose);
//		
//		if(map.get("amount") != null && map.get("amount").size() > 0 ){
//			Integer amount = Integer.parseInt(map.get("amount").get(0));
//		}

	}
	
	@RequestMapping(value = "/webhookBooking2", method = RequestMethod.POST, consumes=org.springframework.http.MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	  public @ResponseBody String  webhookBooking2(HttpServletRequest req, HttpServletResponse res, @RequestParam Map<String,String> map) {
		System.out.println("map is "+map);
		System.out.println("size "+map.keySet().size());
		for(String key: map.keySet()){
			System.out.println("key "+key+" and value is "+map.get(key)+" size "+map.get(key));
			
		}
		
		return "ok";


	}
	
	@RequestMapping(value = "/webhookBooking3", method = RequestMethod.POST, consumes=org.springframework.http.MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	  public @ResponseBody String  webhookBooking3(HttpServletRequest req, HttpServletResponse res, @RequestParam WebHookDto hook) {
		System.out.println(hook.getBuyer());
		System.out.println(hook);
		return "ok";


	}
	
}
