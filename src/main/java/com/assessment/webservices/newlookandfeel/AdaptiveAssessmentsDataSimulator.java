package com.assessment.webservices.newlookandfeel;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.assessment.common.newlookandfeel.CommonUtil;
import com.assessment.data.AdaptiveAssessment;
import com.assessment.data.AdaptiveAssessmentSkill;
import com.assessment.data.AdaptiveAssessmentSkillLevel;
import com.assessment.data.Company;
import com.assessment.data.DifficultyLevel;
import com.assessment.data.Question;
import com.assessment.jsonwrapper.JsonStatusBean;
import com.assessment.services.AdaptiveAssessmentService;
import com.assessment.services.AdaptiveAssessmentSkillService;
import com.assessment.services.CompanyService;
import com.assessment.services.QuestionService;

@Controller
@RequestMapping(CommonUtil.API_Version)
public class AdaptiveAssessmentsDataSimulator {
	
	@Autowired
	AdaptiveAssessmentService addService;
	
	@Autowired
	AdaptiveAssessmentSkillService levelService;
	
	@Autowired
	CompanyService companyService;
	
	@Autowired
	QuestionService questionService;
	
	@RequestMapping(value = "/createAdaptiveAssessment", method = RequestMethod.GET)
	  public @ResponseBody JsonStatusBean createAdaptiveAssessment(@RequestHeader(name="token") String token, @RequestParam(name= "companyId") String companyId, @RequestParam(name= "testName") String testName ,@RequestParam(name= "qualifierLevel") String qualifierLevel,@RequestParam(name= "questionText") String questionText, HttpServletRequest request, HttpServletResponse response) throws IOException {
		 JsonStatusBean bean = new JsonStatusBean();
		 Company company = companyService.findByCompanyId(companyId);
		 AdaptiveAssessment adaptiveAssessment = new AdaptiveAssessment();
			adaptiveAssessment.setAdaptiveAssessmentName(testName);
			adaptiveAssessment.setCompanyId(companyId);
			adaptiveAssessment.setCompanyName(company.getCompanyName());
			adaptiveAssessment.setTestTimeInMinutes(180);
			addService.saveOrUpdate(adaptiveAssessment);
			
			AdaptiveAssessmentSkill level1 = new AdaptiveAssessmentSkill();
			level1.setAdaptiveAssessmentName(testName);
			level1.setCompanyId(companyId);
			level1.setCompanyName(company.getCompanyName());
			level1.setNoOfQuestions(5);
			level1.setNoOfProgressions(3);
			level1.setNoOfRegressions(3);
			level1.setPassPercentForLevel(60);
			level1.setPassPercentForAllLevelsFixed(true);
			level1.setStartingLevel(AdaptiveAssessmentSkillLevel.LEVEL1);
			level1.setQualifier1(qualifierLevel+"1");
			levelService.saveOrUpdate(level1);
			
			AdaptiveAssessmentSkill level2 = new AdaptiveAssessmentSkill();
			level2.setAdaptiveAssessmentName(testName);
			level2.setCompanyId(companyId);
			level2.setCompanyName(company.getCompanyName());
			level2.setNoOfQuestions(5);
			level2.setNoOfProgressions(3);
			level2.setNoOfRegressions(3);
			level2.setPassPercentForLevel(60);
			level2.setPassPercentForAllLevelsFixed(true);
			level2.setStartingLevel(AdaptiveAssessmentSkillLevel.LEVEL2);
			level2.setQualifier1(qualifierLevel+"2");
			levelService.saveOrUpdate(level2);
			
			AdaptiveAssessmentSkill level3 = new AdaptiveAssessmentSkill();
			level3.setAdaptiveAssessmentName(testName);
			level3.setCompanyId(companyId);
			level3.setCompanyName(company.getCompanyName());
			level3.setNoOfQuestions(5);
			level3.setNoOfProgressions(3);
			level3.setNoOfRegressions(3);
			level3.setPassPercentForLevel(60);
			level3.setPassPercentForAllLevelsFixed(true);
			level3.setStartingLevel(AdaptiveAssessmentSkillLevel.LEVEL3);
			level3.setQualifier1(qualifierLevel+"3");
			levelService.saveOrUpdate(level3);
			
			AdaptiveAssessmentSkill level4 = new AdaptiveAssessmentSkill();
			level4.setAdaptiveAssessmentName(testName);
			level4.setCompanyId(companyId);
			level4.setCompanyName(company.getCompanyName());
			level4.setNoOfQuestions(5);
			level4.setNoOfProgressions(3);
			level4.setNoOfRegressions(3);
			level4.setPassPercentForLevel(60);
			level4.setPassPercentForAllLevelsFixed(true);
			level4.setStartingLevel(AdaptiveAssessmentSkillLevel.LEVEL4);
			level4.setQualifier1(qualifierLevel+"4");
			levelService.saveOrUpdate(level4);
			
			AdaptiveAssessmentSkill level5 = new AdaptiveAssessmentSkill();
			level5.setAdaptiveAssessmentName(testName);
			level5.setCompanyId(companyId);
			level5.setCompanyName(company.getCompanyName());
			level5.setNoOfQuestions(5);
			level5.setNoOfProgressions(3);
			level5.setNoOfRegressions(3);
			level5.setPassPercentForLevel(60);
			level5.setPassPercentForAllLevelsFixed(true);
			level5.setStartingLevel(AdaptiveAssessmentSkillLevel.LEVEL5);
			level5.setQualifier1(qualifierLevel+"5");
			levelService.saveOrUpdate(level5);
			
			for(int j=1; j <= 5; j++){
				for(int i=1;i<=10;i++){
					Question q = new Question();
					String qt = questionText+" "+i+" - "+System.currentTimeMillis();
					q.setCompanyId(companyId);
					q.setCompanyName(company.getCompanyName());
					q.setQuestionText(qt);
					q.setDifficultyLevel(DifficultyLevel.EASY);
					q.setQualifier1(qualifierLevel+j);
					q.setChoice1("c1");
					q.setChoice2("c2");
					q.setRightChoices("Choice 1");
					questionService.createQuestion(q);
				}
			}
			
			System.out.println("done");
			bean.setData("OK");
			return bean;
	 }

}
