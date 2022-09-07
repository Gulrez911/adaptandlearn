package com.assessment.web.controllers;

import java.io.File;
import java.io.PrintWriter;
import java.io.StringReader;
import java.io.StringWriter;
import java.net.URLEncoder;
import java.util.Base64;
import java.util.Collection;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathFactory;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.castor.core.util.Base64Decoder;
import org.castor.core.util.Base64Encoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import com.assessment.common.PropertyConfig;
import com.assessment.common.util.EmailGenericMessageThread;
import com.assessment.data.Company;
import com.assessment.data.User;
import com.assessment.data.UserType;
import com.assessment.services.CompanyService;
import com.assessment.services.UserService;
import com.assessment.web.dto.SamlResponseMiniDto;
import com.onelogin.saml2.Auth;

@Controller
public class SAMLController {
	Logger logger = LoggerFactory.getLogger(SAMLController.class);
	@Autowired
	PropertyConfig config;
	
	@Autowired
	UserService userService;
	
	@Autowired
	CompanyService companyService;
	
	
	@RequestMapping(value = "/che123", method = RequestMethod.GET)
	public ModelAndView che123( HttpServletRequest request, HttpServletResponse response) {
		return new ModelAndView("che");
	}
	
	@RequestMapping(value = "/metadata", method = RequestMethod.GET)
	public ModelAndView metadata( HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView("metadata");
		Enumeration<String> headerNames = request.getHeaderNames();
		while(headerNames.hasMoreElements()) {
		  String headerName = headerNames.nextElement();
		  System.out.println("Header Name - " + headerName + ", Value - " + request.getHeader(headerName));
		  logger.info("Header Name - " + headerName + ", Value - " + request.getHeader(headerName));
		}
		Enumeration<String> params = request.getParameterNames(); 
		while(params.hasMoreElements()){
		 String paramName = params.nextElement();
		 System.out.println("Parameter Name - "+paramName+", Value - "+request.getParameter(paramName));
		 logger.info("Parameter Name - "+paramName+", Value - "+request.getParameter(paramName));
		}
		logger.info("in metadata");
		return mav;
	}
	
	@RequestMapping(value = "/yakshaspconsumerendpointGet", method = RequestMethod.GET)
	public ModelAndView yakshaspconsumerendpoint_get(@RequestParam(required=false) Boolean practice, @RequestParam(required=false) String startDate, @RequestParam(required=false) String endDate, @RequestParam(required=false) String sharedDirect,@RequestParam(required=false) String inviteSent, @RequestParam(required=false) String companyId, @RequestParam String testId,  HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("testId is "+testId);
		System.out.println("startDate is "+startDate);
		System.out.println("endDate is "+endDate);
		System.out.println("compId is "+companyId);

		logger.info("testId is "+testId);
		logger.info("startDate is "+startDate);
		logger.info("endDate is "+endDate);
		logger.info("compId is "+companyId);
		
		
		ModelAndView mav = new ModelAndView("acs");
		Enumeration<String> headerNames = request.getHeaderNames();
		logger.info("in yakshaspconsumerendpoint");
		while(headerNames.hasMoreElements()) {
		  String headerName = headerNames.nextElement();
		  System.out.println("Header Name - " + headerName + ", Value - " + request.getHeader(headerName));
		  logger.info("Header Name - " + headerName + ", Value - " + request.getHeader(headerName));
		}
		Enumeration<String> params = request.getParameterNames(); 
		while(params.hasMoreElements()){
		 String paramName = params.nextElement();
		 System.out.println("Parameter Name - "+paramName+", Value - "+request.getParameter(paramName));
		 logger.info("Parameter Name - "+paramName+", Value - "+request.getParameter(paramName));
		}
		
		String requestBody = IOUtils.toString(request.getReader());
		FileUtils.write(new File("infosys"+File.separator +""+System.currentTimeMillis()+".requestbodyget"), requestBody);
		logger.info("creating auth");
		System.out.println("creating auth");
		
		Auth auth = new Auth(request, response);
		//auth.
		System.out.println("auth created");
		auth.setStrict(false);
		Collection<String> emails = auth.getAttribute("email");
		System.out.println("emails size "+emails.size());
		String email = "na";
			if(emails.size() > 0){
				email = emails.iterator().next();
			}
		System.out.println("email is "+email);	
		Map<String, List<String>> map = auth.getAttributes();
			for(String key : map.keySet()){
				List<String> val = map.get(key);
				System.out.println("key is "+key);
					for(String v : val){
						System.out.println("key "+key+" val "+v);
					}
				
				
			}
		
		List<String> sts = auth.getAttributesName();
		for(String v : sts){
			System.out.println(" att name "+v);
		}
		logger.info("creating auth 2");
		
		String url = "redirect:/startTestSession?userId="+email+"&companyId="+companyId+"&testId="+testId+"&sharedDirect=yes&startDate="+startDate+"&endDate="+endDate;
		
	  	mav = new ModelAndView(url);
	  	logger.info("url to redirect is "+url);
		System.out.println("checking if authenticated");
	    return mav;
		
//		try {
//			auth.processResponse();
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//			StringWriter sw = new StringWriter();
//			PrintWriter pw = new PrintWriter(sw);
//			e.printStackTrace(pw);
//			String sStackTrace = sw.toString(); // stack trace as a string
//			EmailGenericMessageThread messageThread = new EmailGenericMessageThread("jatinsut@yahoo.com", "Problem in SAML response processing", sStackTrace, config);
//			Thread th = new Thread(messageThread);
//			th.start();
//			logger.error("problem in saml processing", e);
//		}
		
//		if (!auth.isAuthenticated()) {
//			logger.info("creating auth 4 fail");
//			mav = new ModelAndView("doLogin"); 
//			return mav;
//		}
//		else{
//			logger.info("creating auth 4 pass");
//			return mav;
//		}
		
		
	}

	
	@RequestMapping(value = "/yakshaspconsumerendpoint", method = RequestMethod.POST)
	//public ModelAndView yakshaspconsumerendpoint(@RequestParam(name= "testId", required=false) Long testId, HttpServletRequest request, HttpServletResponse response) throws Exception {
	public ModelAndView yakshaspconsumerendpoint(@RequestParam(required=false) Boolean practice, @RequestParam(required=false) String startDate, @RequestParam(required=false) String endDate, @RequestParam(required=false) String sharedDirect,@RequestParam(required=false) String inviteSent, @RequestParam(required=false) String companyId, @RequestParam(name= "testId", required=false) Long testId,  HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mav = new ModelAndView("acs");
		Enumeration<String> headerNames = request.getHeaderNames();
		logger.info("in yakshaspconsumerendpoint");
		while(headerNames.hasMoreElements()) {
		  String headerName = headerNames.nextElement();
		//  System.out.println("Header Name - " + headerName + ", Value - " + request.getHeader(headerName));
		  logger.info("Header Name - " + headerName + ", Value - " + request.getHeader(headerName));
		}
		Enumeration<String> params = request.getParameterNames(); 
		while(params.hasMoreElements()){
		 String paramName = params.nextElement();
		// System.out.println("Parameter Name - "+paramName+", Value - "+request.getParameter(paramName));
		 logger.info("Parameter Name - "+paramName+", Value - "+request.getParameter(paramName));
		}
		
		String samlResponse = request.getParameter("SAMLResponse");
		System.out.println("samlResponse "+samlResponse);
		Base64Decoder  base64Decoder = new Base64Decoder();
		String decoded = new String(base64Decoder.decode(samlResponse));
		SamlResponseMiniDto dto = processXmlResp(decoded);
		
		
		
		
		System.out.println("email$$$444 "+dto.getEmail()+"first name "+dto.getFirstName()+"last name "+dto.getLastName());
		String relayState = request.getParameter("RelayState");
		relayState = (relayState==null)?"na":relayState;
		String keyvals = relayState.substring(relayState.indexOf("?")+1, relayState.length());
		System.out.println("keyvals "+keyvals);
		StringTokenizer stk = new StringTokenizer(keyvals, "&");
		String compId = "";
		String tid = "";
		String sharedD = "yes";
		String startd = "";
		String endd = "";
		while(stk.hasMoreTokens()){
			String st = stk.nextToken();
			if(st.contains("companyId")){
				compId = st.substring(st.indexOf("=")+1, st.length());
			}
			else if(st.contains("testId")){
				tid = st.substring(st.indexOf("=")+1, st.length());
			}
			else if(st.contains("startDate")){
				startd = st.substring(st.indexOf("=")+1, st.length());
			}
			else if(st.contains("endDate")){
				endd = st.substring(st.indexOf("=")+1, st.length());
			}
			
		}
	System.out.println("compid "+compId+" tid "+tid+" sd "+startd+" ed "+endd);	
	Company comp = companyService.findByCompanyId(compId);
	 User user = userService.findByPrimaryKey(dto.getEmail(), compId);
	 	if(user == null ){
	 		System.out.println("saving user");
	 		user = new User();
	 		user.setCompanyId(compId);
	 		user.setFirstName(dto.getFirstName());
	 		user.setLastName(dto.getLastName());
	 		user.setEmail(dto.getEmail());
	 		user.setUserType(UserType.STUDENT);
	 		user.setPassword(""+dto.getEmail().hashCode());
	 		user.setCompanyName(comp.getCompanyName());
	 		user.setCompanyDescription(comp.getCompanyDescription());
	 		userService.saveOrUpdate(user);
	 		System.out.println("user saved");
	 	}
	
		String encodeduser = new String(Base64Encoder.encode(dto.getEmail().getBytes()));
		encodeduser = URLEncoder.encode(encodeduser);
		String url = "redirect:/startTestSession?userId="+encodeduser+"&companyId="+compId+"&testId="+tid+"&sharedDirect=yes&startDate="+startd+"&endDate="+endd;
		
	  	mav = new ModelAndView(url);
	  	logger.info("url to redirect is "+url);
		System.out.println("checking if authenticated");
	    return mav;

	}
	
	@RequestMapping(value = "/samllogin", method = RequestMethod.GET)
	public ModelAndView samllogin( HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mav = new ModelAndView("doLogin"); 
		return mav;
	}
	
	@RequestMapping(value = "/samltest", method = RequestMethod.GET)
	public ModelAndView samltest( @RequestParam String startDate, @RequestParam String endDate,  @RequestParam String companyId, @RequestParam Long testId, HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setAttribute("startDate", startDate);
		request.setAttribute("endDate", endDate);
		request.setAttribute("companyId", companyId);
		request.setAttribute("testId", testId);
		//ModelAndView mav = new ModelAndView("samltest?&startDate="+startDate+"&endDate="+endDate+"&companyId="+companyId+"&testId="+testId);
		ModelAndView mav = new ModelAndView("saml_test");
		return mav;
	}
	
	@RequestMapping(value = "/yakshasplogoff", method = RequestMethod.GET)
	public ModelAndView yakshasplogoff(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView("sls");
		Enumeration<String> headerNames = request.getHeaderNames();
		while(headerNames.hasMoreElements()) {
		  String headerName = headerNames.nextElement();
		  System.out.println("Header Name - " + headerName + ", Value - " + request.getHeader(headerName));
		  logger.info("Header Name - " + headerName + ", Value - " + request.getHeader(headerName));
		}
		Enumeration<String> params = request.getParameterNames(); 
		while(params.hasMoreElements()){
		 String paramName = params.nextElement();
		 System.out.println("Parameter Name - "+paramName+", Value - "+request.getParameter(paramName));
		 logger.info("Parameter Name - "+paramName+", Value - "+request.getParameter(paramName));
		}
		logger.info("in sls");
		return mav;
	}
	
	//attrs
	@RequestMapping(value = "/attrs", method = RequestMethod.GET)
	public ModelAndView attrs(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView("attrs");
		
		logger.info("in attrs");
		return mav;
	}
	
	
	private SamlResponseMiniDto processXmlResp(String xml) throws Exception{
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		factory.setNamespaceAware(true);
		DocumentBuilder builder = factory.newDocumentBuilder();
		org.w3c.dom.Document document = builder.parse(new InputSource(new StringReader(xml)));
		XPathFactory xPathfactory = XPathFactory.newInstance();
		XPath xpath = xPathfactory.newXPath();
		
		Element root = null;

        NodeList list = document.getChildNodes();
        for (int i = 0; i < list.getLength(); i++) {
          if (list.item(i) instanceof Element) {
            root = (Element) list.item(i);
            break;
          }
        }
        root = document.getDocumentElement();
		root.normalize();
		System.out.println("Root element :" + document.getDocumentElement().getNodeName());
		String tag = "saml:Attribute";
		NodeList nList = document.getElementsByTagName(tag);
		SamlResponseMiniDto ret = new SamlResponseMiniDto();
		for (int temp = 0; temp < nList.getLength(); temp++) {

			Node nNode = nList.item(temp);

			System.out.println("\nCurrent Element :" + nNode.getNodeName());

			if (nNode.getNodeType() == Node.ELEMENT_NODE) {

				Element element = (Element) nNode;
				System.out.println("attr name is "+element.getAttribute("Name"));
				if(element.getAttribute("Name").equals("firstName")){
					System.out.println(element.getNodeValue());
					System.out.println(element.getTextContent());
					ret.setFirstName(element.getTextContent());
				}
				else if(element.getAttribute("Name").equals("lastName")){
					System.out.println(element.getNodeValue());
					System.out.println(element.getTextContent());
					ret.setLastName(element.getTextContent());
				}
				else if(element.getAttribute("Name").equals("email")){
					System.out.println(element.getNodeValue());
					System.out.println(element.getTextContent());
					ret.setEmail(element.getTextContent());
				}
//				System.out.println(element.getTagName());
//				System.out.println(element.getNodeValue());
//				System.out.println(element.getLocalName());
//				System.out.println(element.getTextContent());
//				System.out.println("            ");
					
			}
		}
		
		return ret;
	}
}
