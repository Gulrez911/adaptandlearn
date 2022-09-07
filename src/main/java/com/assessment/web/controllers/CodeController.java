package com.assessment.web.controllers;

import java.io.File;
import java.io.FileInputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;

import org.glassfish.jersey.client.ClientConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.assessment.common.CodeInput;
import com.assessment.common.CommonUtil;
import com.assessment.common.PropertyConfig;
import com.assessment.data.DifficultyLevel;
import com.assessment.data.ProgrammingLanguage;
import com.assessment.data.QuestionMapperInstance;
import com.assessment.data.QuestionType;
import com.assessment.data.User;
import com.assessment.data.UserTestSession;
import com.assessment.repositories.QuestionMapperInstanceRepository;
import com.assessment.services.QuestionService;
import com.assessment.services.UserService;
import com.assessment.services.UserTestSessionService;
import com.assessment.web.dto.CodeReportData;

@Controller
public class CodeController {
	
	Logger logger = LoggerFactory.getLogger(CodeController.class);

	@Autowired
	UserTestSessionService service;
	
	@Autowired
	QuestionService questionService;
	
	@Autowired
	QuestionMapperInstanceRepository questionMapperInstanceRepository;
	
	@Autowired
	UserService userService;
	
	@Autowired
	UserTestSessionService userTestSessionService;
	
	@Autowired
	PropertyConfig propertyConfig;
	
	
	//codingSessions
	@RequestMapping(value = "/singleFileSessions", method = RequestMethod.GET)
	public ModelAndView singleFileSessions(@RequestParam(name= "page", required = false) Integer pageNumber, @RequestParam(name= "qid", required = false) Long qid, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mav = new ModelAndView("single_file_results");
		User user = (User) request.getSession().getAttribute("user");
		if(pageNumber == null) {
			pageNumber = 0;
		}
		Page<QuestionMapperInstance> questionMapperInstances = questionMapperInstanceRepository.findCodingQuestionMapperInstances(user.getCompanyId(), PageRequest.of(pageNumber, 20));
		
  		mav.addObject("answers", questionMapperInstances.getContent());
		CommonUtil.setCommonAttributesOfPagination(questionMapperInstances, mav.getModelMap(), pageNumber, "singleFileSessions", null);
		return mav;
	}
	
	@RequestMapping(value = "/fetchCodingAnswerDetails", method = RequestMethod.GET)
	public @ResponseBody String fetchCodingAnswerDetails(@RequestParam(name = "ansid", required = true) Long ansid, HttpServletRequest request, HttpServletResponse response){
	String html = " <section style=\"border: 2px outset red;\"><b>${Q}</b>		  </section>		  <table class=\"table\">  <thead class=\"grey lighten-2\" style=\"background-color: #b4c8cc;\">    <tr>      <th scope=\"col\">#</th>      <th scope=\"col\">Weight</th>      <th scope=\"col\">Failed Syntax Check</th>      <th scope=\"col\">Status</th>	  	 	     </tr>  </thead>  <tbody>    <tr>      <th scope=\"row\">Basic Integrity</th>      <td>${BASIC_INTEGRITY_WEIGHT}</td>      <td>${SYNTAX_KNOWLEDGE}</td>      <td>${BASIC_INTEGRITY_STATUS}</td>    </tr>    <tr>      <th scope=\"row\">Basic Validations</th>       <td>${BASIC_VALIDITY_WEIGHT}</td>      <td>${SYNTAX_KNOWLEDGE}</td>      <td>${BASIC_VALIDITY_STATUS}</td>    </tr>	<tr>      <th scope=\"row\">Withstand Low Inputs</th>       <td>${LOW_INPUTS_WEIGHT}</td>      <td>${SYNTAX_KNOWLEDGE}</td>      <td>${LOW_INPUTS_STATUS}</td>    </tr>	<tr>      <th scope=\"row\">Withstand High Inputs</th>       <td>${HIGH_INPUTS_WEIGHT}</td>     <td>${SYNTAX_KNOWLEDGE}</td>      <td>${HIGH_INPUTS_STATUS}</td>    </tr>	<tr>      <th scope=\"row\">Production Grade</th>       <td>${PRODUCTION_GRADE_WEIGHT}</td>      <td>${SYNTAX_KNOWLEDGE}</td>      <td>${PRODUCTION_GRADE_STATUS}</td>    </tr>  </tbody></table><table class=\"table\">  <thead class=\"blue lighten-2\" style=\"background-color: #b4c8cc;\">    <tr>      <th scope=\"col\">Overall Weighted Score</th>      <th scope=\"col\">Pass threshold</th>      <th scope=\"col\">Overall Status</th>     </tr>  </thead> <tbody>    <tr>      <th scope=\"row\">${WEIGHTED_SCORE}%</th>      <td>${PASS_THRESHOLD}%</td>            <td>${STATUS}</td>	      </tr>	</tbody></table><button type=\"button\" class=\"btn btn-info\" style=\"text-align: center;position: absolute;width:92%\" onclick=\"javascript:closeModal()\">Close</button>";	
	
	QuestionMapperInstance instance = questionMapperInstanceRepository.findById(ansid).get();
	Integer wtPositive = null;
	String q = "NA";
		try {
			q = instance.getQuestionMapper().getQuestion().getQuestionText() == null?"NA":instance.getQuestionMapper().getQuestion().getQuestionText();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			q = "NA";
		}
		
	html = html.replace("${Q}", q)	;
		try{
			wtPositive = instance.getQuestionMapper().getQuestion().getWeightInputPositive() == null?1:instance.getQuestionMapper().getQuestion().getWeightInputPositive();
		}
		catch(Exception e){
			wtPositive = 1;
		}
	html = html.replace("${BASIC_INTEGRITY_WEIGHT}", ""+wtPositive);
	html = html.replace("${SYNTAX_KNOWLEDGE}", (instance.getCodeCompilationErrors()!=null && instance.getCodeCompilationErrors()==true)?"Code Not Compiled": "No Compilation Errors");
	html = html.replace("${BASIC_INTEGRITY_STATUS}", (instance.getTestCaseInputPositive()!=null && instance.getTestCaseInputPositive()==true)?"Pass": "Fail");
	
	Integer wtNegative = null;
	try{
		wtNegative = instance.getQuestionMapper().getQuestion().getWeightInputNegative() == null?1:instance.getQuestionMapper().getQuestion().getWeightInputNegative();
	}
	catch(Exception e){
		wtNegative = 1;
	}
	html = html.replace("${BASIC_VALIDITY_WEIGHT}", ""+wtNegative);
	html = html.replace("${SYNTAX_KNOWLEDGE}", (instance.getCodeCompilationErrors()!=null && instance.getCodeCompilationErrors()==true)?"Code Not Compiled": "No Compilation Errors");
	html = html.replace("${BASIC_VALIDITY_STATUS}", (instance.getTestCaseInputNegative()!=null && instance.getTestCaseInputNegative()==true)?"Pass": "Fail");

	Integer wtLowInput = null;
	try{
		wtLowInput = instance.getQuestionMapper().getQuestion().getWeightExtremeMinimalValue() == null?1:instance.getQuestionMapper().getQuestion().getWeightExtremeMinimalValue();
	}
	catch(Exception e){
		wtLowInput = 1;
	}
	html = html.replace("${LOW_INPUTS_WEIGHT}", ""+wtLowInput);
	html = html.replace("${SYNTAX_KNOWLEDGE}", (instance.getCodeCompilationErrors()!=null && instance.getCodeCompilationErrors()==true)?"Code Not Compiled": "No Compilation Errors");
	html = html.replace("${LOW_INPUTS_STATUS}", (instance.getTestCaseMinimalValue()!=null && instance.getTestCaseMinimalValue()==true)?"Pass": "Fail");

	Integer wtHighInput = null;
	try{
		wtHighInput = instance.getQuestionMapper().getQuestion().getWeightExtremePositiveValue() == null?1:instance.getQuestionMapper().getQuestion().getWeightExtremePositiveValue();
	}
	catch(Exception e){
		wtHighInput = 1;
	}
	html = html.replace("${HIGH_INPUTS_WEIGHT}", ""+wtHighInput);
	html = html.replace("${SYNTAX_KNOWLEDGE}", (instance.getCodeCompilationErrors()!=null && instance.getCodeCompilationErrors()==true)?"Code Not Compiled": "No Compilation Errors");
	html = html.replace("${HIGH_INPUTS_STATUS}", (instance.getTestCaseMaximumValue()!=null && instance.getTestCaseMaximumValue()==true)?"Pass": "Fail");

	
	Integer wtProd = null;
	try{
		wtProd = instance.getQuestionMapper().getQuestion().getWeightInvalidDataValue() == null?1:instance.getQuestionMapper().getQuestion().getWeightInvalidDataValue();
	}
	catch(Exception e){
		wtProd = 1;
	}
	html = html.replace("${PRODUCTION_GRADE_WEIGHT}", ""+wtProd);
	html = html.replace("${SYNTAX_KNOWLEDGE}", (instance.getCodeCompilationErrors()!=null && instance.getCodeCompilationErrors()==true)?"Code Not Compiled": "No Compilation Errors");
	html = html.replace("${PRODUCTION_GRADE_STATUS}", (instance.getTestCaseInvalidData()!=null && instance.getTestCaseInvalidData()==true)?"Pass": "Fail");

	html = html.replace("${WEIGHTED_SCORE}", ""+instance.getCodingScore());
	Integer passThreshold;
		try{
			passThreshold = instance.getQuestionMapper().getQuestion().getPassPercentforCodingQAsPerWeightedScore() == null?50:instance.getQuestionMapper().getQuestion().getPassPercentforCodingQAsPerWeightedScore();
		}
		catch(Exception e){
			passThreshold = 50;
		}
		html = html.replace("${PASS_THRESHOLD}", ""+passThreshold);
		
		html = html.replace("${STATUS}", instance.getCorrect() == true?"<b>Pass<b>":"<b>Fail</b>");
	
	return html;
	}
	
	@RequestMapping(value = "/codingSessions", method = RequestMethod.GET)
	public ModelAndView addQuestion(@RequestParam(name= "page", required = false) Integer pageNumber, @RequestParam(name= "qid", required = false) Long qid, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mav = new ModelAndView("reports_Coding");
		
		User user = (User) request.getSession().getAttribute("user");
		if(pageNumber == null) {
			pageNumber = 0;
		}
		
		
		
		List<QuestionMapperInstance> questionMapperInstances = questionMapperInstanceRepository.findCodingQuestionMapperInstances(user.getCompanyId());
		List<CodeReportData> codeReportDatas = new ArrayList<>();
		// DecimalFormat f = new DecimalFormat("##.00");
		for(QuestionMapperInstance instance : questionMapperInstances){
			CodeReportData  codeReportData = new CodeReportData();
			User user2 = userService.findByPrimaryKey(instance.getUser(), user.getCompanyId());
			if(user2 == null){
				codeReportData.setFirstName("NA");
				codeReportData.setLastName("NA");
				codeReportData.setEmail("NA");
			}
			else{
				codeReportData.setFirstName(user2.getFirstName());
				codeReportData.setLastName(user2.getLastName());
				codeReportData.setEmail(user2.getEmail());
			}
			
			codeReportData.setTestName(instance.getTestName());
			codeReportData.setProblemStatement(instance.getQuestionMapper().getQuestion().getQuestionText().replaceAll("\r\n|\n", "<br>").replaceAll("\t", "&nbsp;&nbsp;&nbsp;").replace("'", "\\'").replace("\"", "<q>"));
			codeReportData.setOutputCode(instance.getCodeByUser() != null?instance.getCodeByUser().replaceAll("\r\n|\n", "<br>").replace("'", "\\'").replace("\"", "<q>").replaceAll("\t", "&nbsp;&nbsp;&nbsp;"):"NA");
			
			UserTestSession session = userTestSessionService.findUserTestSession(user2.getEmail(), instance.getTestName(), user.getCompanyId());
				if(session != null && session.getPercentageMarksRecieved() != null){
					codeReportData.setOverallScore(String.format("%.2f", session.getPercentageMarksRecieved()));
				}
				else{
					codeReportData.setPass("NA");
				}
			
				if(session != null){
					codeReportData.setPass((session.getPass()?"Pass":"Fail"));
				}
				else{
					codeReportData.setPass("NA");
				}
			
				if(instance.getQuestionMapper().getQuestion().getLanguage().getLanguage().equals(ProgrammingLanguage.JAVA.getLanguage())){
					codeReportData.setAnalysisApplicable(true);
				}
				
				codeReportData.setQuestionMapperInstanceId(instance.getId());
			codeReportDatas.add(codeReportData);
		}
		
  		mav.addObject("data", codeReportDatas);
		return mav;
	} 
	
	@RequestMapping(value = "/downloadCodeAnalysis", method = RequestMethod.GET)
    public ResponseEntity<InputStreamResource> downloadUserReportsForTestWithExtraAttrs(@RequestParam Long id, HttpServletRequest request, HttpServletResponse response)
    {
        try
        {
        	User usr = (User) request.getSession().getAttribute("user");
        	QuestionMapperInstance instance = questionMapperInstanceRepository.findById(id).get();
        	CodeInput codeInput = new CodeInput();
        	/**
        	 * start here for start/end date
        	 */
        	UserTestSession session = userTestSessionService.findUserTestSession(instance.getUser(), instance.getTestName(), instance.getCompanyId());
        	SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MMM/yyyy HH:mm:ss");
        	 	if(session != null){
        			Date sdate = session.getCreateDate();
        			Date edate = session.getUpdateDate();
        			if(sdate != null){
        				String s1 = dateFormat.format(sdate);
        				codeInput.setTestStart(s1);
        			}
        			else{
        				codeInput.setTestStart("NA");
        			}
        			
        			if(edate != null){
        				String s1 = dateFormat.format(edate);
        				codeInput.setTestEnd(s1);
        			}
        			else{
        				codeInput.setTestEnd("NA");
        			}
        		}
        	
        	
        	codeInput.setEmail(instance.getUser());
        	User user = userService.findByPrimaryKey(instance.getUser(), usr.getCompanyId());
        	codeInput.setFirstName(user.getFirstName());
        	codeInput.setLastName(user.getLastName());
        	codeInput.setTestName(instance.getTestName());
        	codeInput.setProblemStatement(instance.getQuestionMapper().getQuestion().getQuestionText());
        	codeInput.setCode(instance.getCodeByUser());
        	ClientConfig config = new ClientConfig();

            Client client = ClientBuilder.newClient(config);
            String baseUrl = propertyConfig.getPmdServerBaseUrl();
            WebTarget target = client.target(baseUrl+"generate");
            String fileName = target.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).post(Entity.json(codeInput), String.class);
    		File file = new File(fileName);
            HttpHeaders respHeaders = new HttpHeaders();
            MediaType mediaType = MediaType.parseMediaType("application/vnd.ms-excel");
            respHeaders.setContentType(mediaType);
            respHeaders.setContentLength(file.length());
            respHeaders.setContentDispositionFormData("attachment", file.getName());
            InputStreamResource isr = new InputStreamResource(new FileInputStream(file));
            return new ResponseEntity<InputStreamResource>(isr, respHeaders, HttpStatus.OK);
        }
        catch (Exception e)
        {
//            String message = "Errore nel download del file "+idForm+".csv; "+e.getMessage();
//            logger.error(message, e);
        	e.printStackTrace();
        	logger.error("error in downloadUserReportsForTestWithExtraAttrs", e);
            return new ResponseEntity<InputStreamResource>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
		
    }
}
