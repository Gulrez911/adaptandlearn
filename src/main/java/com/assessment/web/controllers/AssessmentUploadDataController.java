package com.assessment.web.controllers;

import java.io.File;
import java.io.InputStream;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.assessment.Exceptions.AssessmentGenericException;
import com.assessment.common.ExcelReader;
import com.assessment.common.PropertyConfig;
import com.assessment.data.AdaptiveAssessment;
import com.assessment.data.AdaptiveAssessmentCriteriaType;
import com.assessment.data.AdaptiveAssessmentSkill;
import com.assessment.data.AdaptiveAssessmentSkillLevel;
import com.assessment.data.AssessmentUploadData;
import com.assessment.data.Campaign;
import com.assessment.data.CampaignCandidate;
import com.assessment.data.CampaignTest;
import com.assessment.data.Question;
import com.assessment.data.QuestionMapper;
import com.assessment.data.Section;
import com.assessment.data.Test;
import com.assessment.data.TestLinkTime;
import com.assessment.data.User;
import com.assessment.repositories.QuestionMapperRepository;
import com.assessment.repositories.QuestionRepository;
import com.assessment.services.AdaptiveAssessmentService;
import com.assessment.services.AdaptiveAssessmentSkillService;
import com.assessment.services.AssessmentUploadDataService;
import com.assessment.services.CampaignCandidateService;
import com.assessment.services.CampaignService;
import com.assessment.services.CompanyService;
import com.assessment.services.QuestionMapperService;
import com.assessment.services.QuestionService;
import com.assessment.services.SectionService;
import com.assessment.services.TestLinkTimeService;
import com.assessment.services.TestService;
import com.assessment.services.UserService;
import com.assessment.web.dto.AdaptiveAssessmentCreationDto;
import com.assessment.web.dto.AssessmentExcelDto;

@Controller
public class AssessmentUploadDataController {

	@Autowired
	AssessmentUploadDataService dataService;
	
	@Autowired
	private UserService userService;
	@Autowired
	private QuestionService questionService;
	@Autowired
	private CompanyService companyService;
	
	@Autowired
	PropertyConfig propertyConfig;
	
	
	@Autowired
	QuestionRepository questionRepository;
	
	@Autowired
	QuestionMapperService questionMapperService;
	
	@Autowired
	SectionService sectionservice;
	
	@Autowired
	TestService testService;
	
	@Autowired
	CampaignService campaignService;
	
	@Autowired
	AdaptiveAssessmentService adaptiveAssessmentService;
	
	@Autowired
	AdaptiveAssessmentSkillService adaptiveAssessmentSkillService;
	
	@Autowired
	TestLinkTimeService testLinkTimeService;
	
	@Autowired
	CampaignCandidateService campaignCandidateService;
	
	@Autowired
	QuestionMapperRepository questionMapperRepository;
	
	Logger logger =LoggerFactory.getLogger(AssessmentUploadDataController.class);
	
	@RequestMapping(value = "/uploadHospitalAssessments", method = RequestMethod.POST)
	public @ResponseBody String uploadHospitalAssessments(HttpServletResponse response, MultipartHttpServletRequest request) throws Exception {
		try {
			User user = (User) request.getSession().getAttribute("user");
			MultipartFile multipartFile = request.getFile("fileQuestionsHospitals");
			Long size = multipartFile.getSize();
			String fileName = multipartFile.getName();
			String contentType = multipartFile.getContentType();
			InputStream stream = multipartFile.getInputStream();
			File file = ResourceUtils.getFile("classpath:assessmentHosps.xml");

//			File file = new File("questions.xml");
			List<AssessmentExcelDto> records = ExcelReader.parseExcelFileToBeans(stream, file);
				for(AssessmentExcelDto rec : records){
					String skill1 = rec.getLevel1_qualifier1();
					String skill12 = rec.getLevel1_qualifier2();
					String skill2 = rec.getLevel2_qualifier1();
					String skill22 = rec.getLevel2_qualifier2();
					
					String skill3 = rec.getLevel3_qualifier1();
					String skill32 = rec.getLevel3_qualifier2();
					
					String skill4 = rec.getLevel4_qualifier1();
					String skill42 = rec.getLevel4_qualifier2();
					
					String skill5 = rec.getLevel5_qualifier1();
					String skill52 = rec.getLevel5_qualifier2();
					Long count1 = questionRepository.getCountQuestionsByQualifier1_2(user.getCompanyId(), skill1, skill12);
					Long count2 = questionRepository.getCountQuestionsByQualifier1_2(user.getCompanyId(), skill2, skill22);
					Long count3 = questionRepository.getCountQuestionsByQualifier1_2(user.getCompanyId(), skill3, skill32);
					Long count4 = questionRepository.getCountQuestionsByQualifier1_2(user.getCompanyId(), skill4, skill42);
					Long count5 = questionRepository.getCountQuestionsByQualifier1_2(user.getCompanyId(), skill5, skill52);
						if(count1 <= 9){
							return "Insufficient no Of Questions("+count1+") for Skill 1"+skill1+"-"+skill12+" and Job Desc "+rec.getJobDesriptionName();
						}
						
						if(count2 <= 9){
							return "Insufficient no Of Questions("+count2+") for Skill 2"+skill2+"-"+skill22+" and Job Desc "+rec.getJobDesriptionName();
						}
						
						if(count3 <= 9){
							return "Insufficient no Of Questions("+count3+") for Skill 3"+skill3+"-"+skill32+" and Job Desc "+rec.getJobDesriptionName();
						}
						
						if(count4 <= 9){
							return "Insufficient no Of Questions("+count4+") for Skill 4"+skill4+"-"+skill42+" and Job Desc "+rec.getJobDesriptionName();
						}
						
						if(count5 <= 9){
							return "Insufficient no Of Questions("+count5+") for Skill 5"+skill5+"-"+skill52+" and Job Desc "+rec.getJobDesriptionName();
						}
						
						String testLink1 = createNormalAssessment(user.getCompanyName(), user.getCompanyId(), skill1, skill12, count1);
						String testLink2 = createNormalAssessment(user.getCompanyName(), user.getCompanyId(), skill2, skill22, count2);
						String testLink3 = createNormalAssessment(user.getCompanyName(), user.getCompanyId(), skill3, skill32, count3);
						String testLink4 = createNormalAssessment(user.getCompanyName(), user.getCompanyId(), skill4, skill42, count4);
						String testLink5 = createNormalAssessment(user.getCompanyName(), user.getCompanyId(), skill5, skill52, count5);
						
						String campaignLink1 = createCampaign(user.getCompanyName(), user.getCompanyId(), skill1, skill12, skill2, skill22, skill3, skill32, skill4, skill42, skill5, skill52);
						
						AdaptiveAssessmentCreationDto dto = new AdaptiveAssessmentCreationDto();
						dto.setAssessmentLink1(testLink1);
						dto.setAssessmentLink2(testLink2);
						dto.setAssessmentLink3(testLink3);
						dto.setAssessmentLink4(testLink4);
						dto.setAssessmentLink5(testLink5);
						
						dto.setCompanyId(user.getCompanyId());
						dto.setCompanyName(user.getCompanyName());
						dto.setCountAss1(count1.intValue() > 30?30:count1.intValue());
						dto.setCountAss2(count2.intValue() > 30?30:count2.intValue());
						dto.setCountAss3(count3.intValue() > 30?30:count3.intValue());
						dto.setCountAss4(count4.intValue() > 30?30:count4.intValue());
						dto.setCountAss5(count5.intValue() > 30?30:count5.intValue());
						dto.setCourseVideo1(rec.getLevel1CourseVideoUrl());
						dto.setCourseVideo2(rec.getLevel2CourseVideoUrl());
						dto.setCourseVideo3(rec.getLevel3CourseVideoUrl());
						dto.setCourseVideo4(rec.getLevel4CourseVideoUrl());
						dto.setCourseVideo5(rec.getLevel5CourseVideoUrl());
						dto.setJobDesc(rec.getJobdesc());
						dto.setJobdescName(rec.getJobDesriptionName());
						dto.setSkill1(skill1);
						dto.setSkill2(skill2);
						dto.setSkill3(skill3);
						dto.setSkill4(skill4);
						dto.setSkill4(skill4);
						dto.setSkill5(skill5);
						
						dto.setSkill12(skill12);
						dto.setSkill22(skill22);
						dto.setSkill32(skill32);
						dto.setSkill42(skill42);
						dto.setSkill52(skill52);
						
						String adaptiveAssessmentLink = createAdaptiveAssessment(dto);
						AssessmentUploadData data = new AssessmentUploadData();
						data.setJobdesc(rec.getJobdesc());
						data.setJobDescName(rec.getJobDesriptionName());
						String name = "Adaptive Assessment - "+dto.getJobdescName();
						data.setAdaptiveAssessmentName(name);
						data.setAdaptiveAssessmentPublicLink(adaptiveAssessmentLink);
						String testName1 = "Assessment - "+skill1+"-"+skill12;
						data.setAssessmentName1(testName1);
						
						String testName2 = "Assessment - "+skill2+"-"+skill22;
						data.setAssessmentName2(testName2);
						
						String testName3 = "Assessment - "+skill3+"-"+skill32;
						data.setAssessmentName3(testName3);
						
						String testName4 = "Assessment - "+skill4+"-"+skill42;
						data.setAssessmentName4(testName4);
						
						String testName5 = "Assessment - "+skill5+"-"+skill52;
						data.setAssessmentName5(testName5);
						
						data.setAssessmentPublicLink1(testLink1);
						data.setAssessmentPublicLink2(testLink2);
						data.setAssessmentPublicLink3(testLink3);
						data.setAssessmentPublicLink4(testLink4);
						data.setAssessmentPublicLink5(testLink5);
						//String campaignName = "Campaign for - "+skill1+", "+skill2+", "+skill3+", "+skill4+", "+skill5+" skills";
						String campaignName = "Campaign for - "+skill1+"-"+skill12+", "+skill22+", "+skill32+", "+skill42+", "+skill52+" skills";
						data.setCampaignName(campaignName);
						data.setCampaignTestLink(campaignLink1);
						data.setCompanyId(user.getCompanyId());
						data.setCompanyName(user.getCompanyName());
						data.setDomain("Hospitals_Pharmacy");
						data.setAssessment1VideoUIrl(rec.getLevel1CourseVideoUrl());
						data.setAssessment2VideoUrl(rec.getLevel2CourseVideoUrl());
						data.setAssessment3VideoUrl(rec.getLevel3CourseVideoUrl());
						data.setAssessment4VideoUrl(rec.getLevel4CourseVideoUrl());
						data.setAssessment5VideoUrl(rec.getLevel5CourseVideoUrl());
						dataService.saveOrUpdate(data);
				}
			
			
			logger.info("upload qs in db complete");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.error("problem in uploading qs", e);
			return "Problem in Uploading "+e.getMessage();
		}
		return "ok";
	}
	
	@RequestMapping(value = "/uploadITAssessments", method = RequestMethod.POST)
	public @ResponseBody String uploadITAssessments(HttpServletResponse response, MultipartHttpServletRequest request) throws Exception {
		try {
			User user = (User) request.getSession().getAttribute("user");
			MultipartFile multipartFile = request.getFile("fileQuestionsIT");
			Long size = multipartFile.getSize();
			String fileName = multipartFile.getName();
			String contentType = multipartFile.getContentType();
			InputStream stream = multipartFile.getInputStream();
			File file = ResourceUtils.getFile("classpath:assessmentIT.xml");

//			File file = new File("questions.xml");
			List<AssessmentExcelDto> records = ExcelReader.parseExcelFileToBeans(stream, file);
				for(AssessmentExcelDto rec : records){
					String skill1 = rec.getLevel1_qualifier1().trim();
					String skill12 = rec.getLevel1_qualifier2().trim();
					String skill2 = rec.getLevel2_qualifier1().trim();
					String skill22 = rec.getLevel2_qualifier2().trim();
					
					String skill3 = rec.getLevel3_qualifier1().trim();
					String skill32 = rec.getLevel3_qualifier2().trim();
					
					String skill4 = rec.getLevel4_qualifier1().trim();
					String skill42 = rec.getLevel4_qualifier2().trim();
					
					String skill5 = rec.getLevel5_qualifier1().trim();
					String skill52 = rec.getLevel5_qualifier2().trim();
					Long count1 = questionRepository.getCountQuestionsByQualifier1_2(user.getCompanyId(), skill1, skill12);
					Long count2 = questionRepository.getCountQuestionsByQualifier1_2(user.getCompanyId(), skill2, skill22);
					Long count3 = questionRepository.getCountQuestionsByQualifier1_2(user.getCompanyId(), skill3, skill32);
					Long count4 = questionRepository.getCountQuestionsByQualifier1_2(user.getCompanyId(), skill4, skill42);
					Long count5 = questionRepository.getCountQuestionsByQualifier1_2(user.getCompanyId(), skill5, skill52);
					if(count1 <= 9){
						return "Insufficient no Of Questions("+count1+") for Skill 1"+skill1+"-"+skill12+" and Job Desc "+rec.getJobDesriptionName();
					}
					
					if(count2 <= 9){
						return "Insufficient no Of Questions("+count2+") for Skill 2"+skill2+"-"+skill22+" and Job Desc "+rec.getJobDesriptionName();
					}
					
					if(count3 <= 9){
						return "Insufficient no Of Questions("+count3+") for Skill 3"+skill3+"-"+skill32+" and Job Desc "+rec.getJobDesriptionName();
					}
					
					if(count4 <= 9){
						return "Insufficient no Of Questions("+count4+") for Skill 4"+skill4+"-"+skill42+" and Job Desc "+rec.getJobDesriptionName();
					}
					
					if(count5 <= 9){
						return "Insufficient no Of Questions("+count5+") for Skill 5"+skill5+"-"+skill52+" and Job Desc "+rec.getJobDesriptionName();
					}
						
						String testLink1 = createNormalAssessment(user.getCompanyName(), user.getCompanyId(), skill1, skill12, count1);
						String testLink2 = createNormalAssessment(user.getCompanyName(), user.getCompanyId(), skill2, skill22, count2);
						String testLink3 = createNormalAssessment(user.getCompanyName(), user.getCompanyId(), skill3, skill32, count3);
						String testLink4 = createNormalAssessment(user.getCompanyName(), user.getCompanyId(), skill4, skill42, count4);
						String testLink5 = createNormalAssessment(user.getCompanyName(), user.getCompanyId(), skill5, skill52, count5);
						
						String campaignLink1 = createCampaign(user.getCompanyName(), user.getCompanyId(), skill1, skill12, skill2, skill22, skill3, skill32, skill4, skill42, skill5, skill52);
						
						AdaptiveAssessmentCreationDto dto = new AdaptiveAssessmentCreationDto();
						dto.setAssessmentLink1(testLink1);
						dto.setAssessmentLink2(testLink2);
						dto.setAssessmentLink3(testLink3);
						dto.setAssessmentLink4(testLink4);
						dto.setAssessmentLink5(testLink5);
						
						dto.setCompanyId(user.getCompanyId());
						dto.setCompanyName(user.getCompanyName());
						dto.setCountAss1(count1.intValue() > 30?30:count1.intValue());
						dto.setCountAss2(count2.intValue() > 30?30:count2.intValue());
						dto.setCountAss3(count3.intValue() > 30?30:count3.intValue());
						dto.setCountAss4(count4.intValue() > 30?30:count4.intValue());
						dto.setCountAss5(count5.intValue() > 30?30:count5.intValue());
						dto.setCourseVideo1(rec.getLevel1CourseVideoUrl());
						dto.setCourseVideo2(rec.getLevel2CourseVideoUrl());
						dto.setCourseVideo3(rec.getLevel3CourseVideoUrl());
						dto.setCourseVideo4(rec.getLevel4CourseVideoUrl());
						dto.setCourseVideo5(rec.getLevel5CourseVideoUrl());
						dto.setJobDesc(rec.getJobdesc().trim());
						dto.setJobdescName(rec.getJobDesriptionName().trim());
						dto.setSkill1(skill1);
						dto.setSkill2(skill2);
						dto.setSkill3(skill3);
						dto.setSkill4(skill4);
						dto.setSkill4(skill4);
						dto.setSkill5(skill5);
						
						dto.setSkill12(skill12);
						dto.setSkill22(skill22);
						dto.setSkill32(skill32);
						dto.setSkill42(skill42);
						dto.setSkill52(skill52);
						
						String adaptiveAssessmentLink = createAdaptiveAssessment(dto);
						AssessmentUploadData data = new AssessmentUploadData();
						data.setJobdesc(rec.getJobdesc());
						data.setJobDescName(rec.getJobDesriptionName());
						String name = "Adaptive Assessment - "+dto.getJobdescName();
						data.setAdaptiveAssessmentName(name);
						data.setAdaptiveAssessmentPublicLink(adaptiveAssessmentLink);
						String testName1 = "Assessment - "+skill1+"-"+skill12;
						data.setAssessmentName1(testName1);
						
						String testName2 = "Assessment - "+skill2+"-"+skill22;
						data.setAssessmentName2(testName2);
						
						String testName3 = "Assessment - "+skill3+"-"+skill32;
						data.setAssessmentName3(testName3);
						
						String testName4 = "Assessment - "+skill4+"-"+skill42;
						data.setAssessmentName4(testName4);
						
						String testName5 = "Assessment - "+skill5+"-"+skill52;
						data.setAssessmentName5(testName5);
						
						data.setAssessmentPublicLink1(testLink1);
						data.setAssessmentPublicLink2(testLink2);
						data.setAssessmentPublicLink3(testLink3);
						data.setAssessmentPublicLink4(testLink4);
						data.setAssessmentPublicLink5(testLink5);
						//String campaignName = "Campaign for - "+skill1+", "+skill2+", "+skill3+", "+skill4+", "+skill5+" skills";
						String campaignName = "Campaign for - "+skill1+"-"+skill12+", "+skill22+", "+skill32+", "+skill42+", "+skill52+" skills";
						data.setCampaignName(campaignName);
						data.setCampaignTestLink(campaignLink1);
						data.setCompanyId(user.getCompanyId());
						data.setCompanyName(user.getCompanyName());
						data.setDomain("IT");
						data.setAssessment1VideoUIrl(rec.getLevel1CourseVideoUrl());
						data.setAssessment2VideoUrl(rec.getLevel2CourseVideoUrl());
						data.setAssessment3VideoUrl(rec.getLevel3CourseVideoUrl());
						data.setAssessment4VideoUrl(rec.getLevel4CourseVideoUrl());
						data.setAssessment5VideoUrl(rec.getLevel5CourseVideoUrl());
						dataService.saveOrUpdate(data);
				}
			
			
			logger.info("upload qs in db complete");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.error("problem in uploading qs", e);
			return "Problem in Uploading "+e.getMessage();
		}
		return "ok";
	}
	
	protected String createNormalAssessment(String companyName, String companyId, String skill, String skill2, Long count){
		Integer noOfQs = 30;
		if(count < noOfQs){
			noOfQs = count.intValue();
		}
		String testName = "Assessment - "+skill+"-"+skill2;
		Test test = new Test();
		test.setCompanyId(companyId);
		test.setCompanyName(companyName);
		test.setTestName(testName);
		test.setFullStackTest(false);
		test.setNewUi(true);
		test.setNoOfConfigurableAttempts(100);
		test.setIntro("Not Available");
		test.setPassPercent(60f);
		test.setSentToStudent(true);
		test.setShowFinalScoreToParticipants(true);
		test.setSendRecommReport(true);
		test.setDisplayResultToStudent(true);
		test.setJustification(true);
		test.setQualifier1(skill);
		test.setQualifier2(skill2);
		test.setTestTimeInMinutes(30);
		test.setTotalMarks(noOfQs);
		Test test2 = testService.findbyTest(testName, companyId);
			if(test2 != null){
				test.setId(test2.getId());
			}
		testService.saveOrUpdate(test);
		 
		List<Question> questions = questionRepository.findQuestionsByQualifier2(companyId, skill, skill2,  PageRequest.of(0, noOfQs)).getContent();
		Section section = new Section();
		section.setCompanyId(companyId);
		section.setCompanyName(companyName);
		section.setSectionName("Section - "+skill);
		section.setPercentQuestionsAsked(100);
		section.setNoOfQuestions(noOfQs);
		section.setSectionTimeInMinutes(30);
		section.setNoOfQuestionsToBeAsked(noOfQs);
		
		section.setTestName(testName);
		section.setCreateDate(new Date());
		Section section2 = sectionservice.findByPrimaryKey(testName, "Section - "+skill, companyId);
			if(section2 == null){
				sectionservice.createSection(section);
			}
		for(Question question : questions){
			QuestionMapper mapper = questionMapperRepository.findUniqueQuestionMapper("Section - "+skill, testName, question.getId(), companyId);
				if(mapper == null){
					sectionservice.addQuestionToSection(question, section, 1);
				}
			
		}
		
		
		
		TestLinkTime link = new TestLinkTime();
		link.setCompanyId(companyId);
		link.setCompanyName(companyName);
		Date start = new Date();
		link.setStartDate(start);
		Calendar c = Calendar.getInstance();
		c.setTime(start);
		c.add(Calendar.DATE, 1000); // Adding 1000 days
		Date end = c.getTime();
		link.setEndDate(end);
		
		link.setTestId(test.getId());
		testLinkTimeService.saveOrUpdate(link);
		
		String sDate;
 		String eDate;
		sDate = Base64.getEncoder().encodeToString((""+start.getTime()).getBytes());
		eDate = Base64.getEncoder().encodeToString((""+end.getTime()).getBytes());
		sDate = URLEncoder.encode(sDate);
		eDate = URLEncoder.encode(eDate);
 		
		
		String url = getUrlForUser("$EMAIL", test.getId(), companyId);
		url += "&inviteSent="+System.currentTimeMillis()+"&startDate="+sDate+"&endDate="+eDate+"&lang=eng";;
		
		return url;
	}
	
	 private String getUrlForUser(String user, Long testId, String companyId) {
		 //String userBytes =  Base64.getEncoder().encodeToString(user.getBytes());
		 
		 String after = "userId="+URLEncoder.encode(user)+"&testId="+URLEncoder.encode(testId.toString())+"&companyId="+URLEncoder.encode(companyId);
		 String url = propertyConfig.getBaseUrl()+"mcq2?"+after;
		 return url;
	  }
	
	protected String createCampaign(String companyName, String companyId, String skill1, String skill12, String skill2, String skill22, String skill3, String skill32, String skill4, String skill42, String skill5, String skill52){
		Campaign campaign = new Campaign();
		String campaignName = "Campaign for - "+skill1+"-"+skill12+", "+skill22+", "+skill32+", "+skill42+", "+skill52+" skills";
		
		String testName = "Assessment - "+skill1+"-"+skill12;
		Test test1 = testService.findbyTest(testName, companyId);
		
		testName = "Assessment - "+skill2+"-"+skill22;
		Test test2 = testService.findbyTest(testName, companyId);
		
		testName = "Assessment - "+skill3+"-"+skill32;
		Test test3 = testService.findbyTest(testName, companyId);
		
		testName = "Assessment - "+skill4+"-"+skill42;
		Test test4 = testService.findbyTest(testName, companyId);
		
		testName = "Assessment - "+skill5+"-"+skill52;
		Test test5 = testService.findbyTest(testName, companyId);
		
		CampaignTest campaigntest = new CampaignTest();
		campaigntest.setCampaignName(campaignName);
		campaigntest.setCampaignTestName(test1.getTestName());
		campaigntest.setTestName(test1.getTestName());
		campaigntest.setCompanyId(companyId);
		campaigntest.setCompanyName(companyName);
		campaigntest.setMandatory(true);
		campaigntest.setSequenceNo(1);
		campaigntest.setTestDuration(test1.getTestTimeInMinutes());
		campaigntest.setTestSkills(skill1);
		campaigntest.setTestId(test1.getId());
		campaigntest.setCompanyId(companyId);
		campaigntest.setCompanyName(companyName);
		campaign.getRounds().add(campaigntest);
		
		
		CampaignTest campaigntest2 = new CampaignTest();
		campaigntest2.setCampaignName(campaignName);
		campaigntest2.setCampaignTestName(test2.getTestName());
		campaigntest2.setTestName(test2.getTestName());
		campaigntest2.setCompanyId(companyId);
		campaigntest2.setCompanyName(companyName);
		campaigntest2.setMandatory(true);
		campaigntest2.setSequenceNo(2);
		campaigntest2.setTestDuration(test2.getTestTimeInMinutes());
		campaigntest2.setTestSkills(skill2);
		campaigntest2.setTestId(test2.getId());
		campaigntest2.setCompanyId(companyId);
		campaigntest2.setCompanyName(companyName);
		campaign.getRounds().add(campaigntest2);
		
		CampaignTest campaigntest3 = new CampaignTest();
		campaigntest3.setCampaignName(campaignName);
		campaigntest3.setCampaignTestName(test3.getTestName());
		campaigntest3.setTestName(test3.getTestName());
		campaigntest3.setCompanyId(companyId);
		campaigntest3.setCompanyName(companyName);
		campaigntest3.setMandatory(true);
		campaigntest3.setSequenceNo(3);
		campaigntest3.setTestDuration(test3.getTestTimeInMinutes());
		campaigntest3.setTestSkills(skill3);
		campaigntest3.setTestId(test3.getId());
		campaigntest3.setCompanyId(companyId);
		campaigntest3.setCompanyName(companyName);
		campaign.getRounds().add(campaigntest3);
		
		CampaignTest campaigntest4 = new CampaignTest();
		campaigntest4.setCampaignName(campaignName);
		campaigntest4.setCampaignTestName(test4.getTestName());
		campaigntest4.setTestName(test4.getTestName());
		campaigntest4.setCompanyId(companyId);
		campaigntest4.setCompanyName(companyName);
		campaigntest4.setMandatory(true);
		campaigntest4.setSequenceNo(4);
		campaigntest4.setTestDuration(test4.getTestTimeInMinutes());
		campaigntest4.setTestSkills(skill4);
		campaigntest4.setTestId(test4.getId());
		campaigntest4.setCompanyId(companyId);
		campaigntest4.setCompanyName(companyName);
		campaign.getRounds().add(campaigntest4);
		
		CampaignTest campaigntest5 = new CampaignTest();
		campaigntest5.setCampaignName(campaignName);
		campaigntest5.setCampaignTestName(test5.getTestName());
		campaigntest5.setTestName(test5.getTestName());
		campaigntest5.setCompanyId(companyId);
		campaigntest5.setCompanyName(companyName);
		campaigntest5.setMandatory(true);
		campaigntest5.setSequenceNo(5);
		campaigntest5.setTestDuration(test5.getTestTimeInMinutes());
		campaigntest5.setTestSkills(skill5);
		campaigntest5.setTestId(test5.getId());
		campaigntest5.setCompanyId(companyId);
		campaigntest5.setCompanyName(companyName);
		campaign.getRounds().add(campaigntest5);
		campaign.setCampaignName(campaignName);
		campaign.setCampaignDesc("NA");
		campaign.setActive(true);
		campaign.setCompanyId(companyId);
		campaign.setCompanyName(companyName);
		campaignService.saveOrUpdate(campaign);
		
		CampaignCandidate campaignCandidate = new CampaignCandidate();
		campaignCandidate.setCompanyId(companyId);
		campaignCandidate.setCompanyName(companyName);
		campaignCandidate.setCampaignName(campaignName);
		String sampleEmailExistingUser = "$EMAIL";
		campaignCandidate.setEmail(sampleEmailExistingUser);
		campaignCandidate.setFirstName("Test ");
		campaignCandidate.setLastName("User");
		campaignCandidate.setCampaignId(campaign.getId());
		
		campaignCandidateService.saveOrUpdate(campaignCandidate);
		
		Date start = new Date();
		Calendar c = Calendar.getInstance();
		c.setTime(start);
		c.add(Calendar.DATE, 1000); // Adding 1000 days
		Date end = c.getTime();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String sDate = dateFormat.format(start);
		String eDate = dateFormat.format(end);
		String candidateurl = getCampaignLinkForTestTaker(campaign.getCampaignName(), companyId, sampleEmailExistingUser,
				campaignCandidate.getFirstName() == null ? "NA" : campaignCandidate.getFirstName(),
						campaignCandidate.getLastName() == null ? "NA" : campaignCandidate.getLastName(), sDate, eDate,
				campaign.getLanguage()==null?"eng":campaign.getLanguage());
		return candidateurl;
	}
	
	
	
	
	protected String createAdaptiveAssessment(AdaptiveAssessmentCreationDto dto){
		AdaptiveAssessment adaptiveAssessment = new  AdaptiveAssessment();
		adaptiveAssessment.setCompanyId(dto.getCompanyId());
		adaptiveAssessment.setCompanyName(dto.getCompanyName());
		String name = "Adaptive Assessment - "+dto.getJobdescName();
		adaptiveAssessment.setAdaptiveAssessmentName(name);
		adaptiveAssessment.setIntro(dto.getJobDesc());
		adaptiveAssessment.setCoreSkills(dto.getSkill1()+"- "+dto.getSkill12()+", "+dto.getSkill22()+", "+dto.getSkill32()+", "+dto.getSkill42()+", "+dto.getSkill52());
		adaptiveAssessment.setNoOfProgressions(3);
		adaptiveAssessment.setNoOfRegressions(3);
		adaptiveAssessment.setNoOfConfigurableAttempts(100);
		adaptiveAssessment.setSentToStudent(true);
		adaptiveAssessmentService.saveOrUpdate(adaptiveAssessment);
		
		AdaptiveAssessmentSkill level1 = new AdaptiveAssessmentSkill();
		level1.setAdaptiveAssessmentName(name);
		level1.setAssessmentLinkAfterCourseCompletion(dto.getAssessmentLink1());
		level1.setCompanyId(dto.getCompanyId());
		level1.setCompanyName(dto.getCompanyName());
		level1.setCourseLinkRecommendation(dto.getCourseVideo1());
		level1.setCriteriaType(AdaptiveAssessmentCriteriaType.NONE);
		level1.setNoOfProgressions(3);
		level1.setNoOfRegressions(3);
		level1.setPassPercentForLevel(60);
		int countLevel1 = (int)(dto.getCountAss1()*(50/100.0f));
		level1.setNoOfQuestions(countLevel1);
		level1.setQualifier1(dto.getSkill1());
		level1.setQualifier2(dto.getSkill12());
		level1.setSkillName(dto.getSkill1()+"-"+dto.getSkill12());
		level1.setStartingLevel(AdaptiveAssessmentSkillLevel.LEVEL1);
		adaptiveAssessmentSkillService.saveOrUpdate(level1);
		
		AdaptiveAssessmentSkill level2 = new AdaptiveAssessmentSkill();
		level2.setAdaptiveAssessmentName(name);
		level2.setAssessmentLinkAfterCourseCompletion(dto.getAssessmentLink2());
		level2.setCompanyId(dto.getCompanyId());
		level2.setCompanyName(dto.getCompanyName());
		level2.setCourseLinkRecommendation(dto.getCourseVideo2());
		level2.setCriteriaType(AdaptiveAssessmentCriteriaType.NONE);
		level2.setNoOfProgressions(3);
		level2.setNoOfRegressions(3);
		level2.setPassPercentForLevel(60);
		int countLevel2 = (int)(dto.getCountAss2()*(50/100.0f));
		level2.setNoOfQuestions(countLevel2);
		level2.setQualifier1(dto.getSkill2());
		level2.setQualifier2(dto.getSkill22());
		level2.setSkillName(dto.getSkill2()+"-"+dto.getSkill22());
		level2.setStartingLevel(AdaptiveAssessmentSkillLevel.LEVEL2);
		adaptiveAssessmentSkillService.saveOrUpdate(level2);
		
		
		AdaptiveAssessmentSkill level3 = new AdaptiveAssessmentSkill();
		level3.setAdaptiveAssessmentName(name);
		level3.setAssessmentLinkAfterCourseCompletion(dto.getAssessmentLink3());
		level3.setCompanyId(dto.getCompanyId());
		level3.setCompanyName(dto.getCompanyName());
		level3.setCourseLinkRecommendation(dto.getCourseVideo3());
		level3.setCriteriaType(AdaptiveAssessmentCriteriaType.NONE);
		level3.setNoOfProgressions(3);
		level3.setNoOfRegressions(3);
		level3.setPassPercentForLevel(60);
		int countLevel3 = (int)(dto.getCountAss3()*(50/100.0f));
		level3.setNoOfQuestions(countLevel3);
		level3.setQualifier1(dto.getSkill3());
		level3.setQualifier2(dto.getSkill32());
		level3.setSkillName(dto.getSkill3()+"-"+dto.getSkill32());
		level3.setStartingLevel(AdaptiveAssessmentSkillLevel.LEVEL3);
		adaptiveAssessmentSkillService.saveOrUpdate(level3);
		
		AdaptiveAssessmentSkill level4 = new AdaptiveAssessmentSkill();
		level4.setAdaptiveAssessmentName(name);
		level4.setAssessmentLinkAfterCourseCompletion(dto.getAssessmentLink4());
		level4.setCompanyId(dto.getCompanyId());
		level4.setCompanyName(dto.getCompanyName());
		level4.setCourseLinkRecommendation(dto.getCourseVideo4());
		level4.setCriteriaType(AdaptiveAssessmentCriteriaType.NONE);
		level4.setNoOfProgressions(3);
		level4.setNoOfRegressions(3);
		level4.setPassPercentForLevel(60);
		int countLevel4 = (int)(dto.getCountAss4()*(50/100.0f));
		level4.setNoOfQuestions(countLevel4);
		level4.setQualifier1(dto.getSkill4());
		level4.setQualifier2(dto.getSkill42());
		level4.setSkillName(dto.getSkill4()+"-"+dto.getSkill42());
		level4.setStartingLevel(AdaptiveAssessmentSkillLevel.LEVEL4);
		adaptiveAssessmentSkillService.saveOrUpdate(level4);
		
		AdaptiveAssessmentSkill level5 = new AdaptiveAssessmentSkill();
		level5.setAdaptiveAssessmentName(name);
		level5.setAssessmentLinkAfterCourseCompletion(dto.getAssessmentLink5());
		level5.setCompanyId(dto.getCompanyId());
		level5.setCompanyName(dto.getCompanyName());
		level5.setCourseLinkRecommendation(dto.getCourseVideo5());
		level5.setCriteriaType(AdaptiveAssessmentCriteriaType.NONE);
		level5.setNoOfProgressions(3);
		level5.setNoOfRegressions(3);
		level5.setPassPercentForLevel(60);
		int countLevel5 = (int)(dto.getCountAss4()*(50/100.0f));
		level5.setNoOfQuestions(countLevel5);
		level5.setQualifier1(dto.getSkill5());
		level5.setQualifier2(dto.getSkill52());
		level5.setSkillName(dto.getSkill5()+"-"+dto.getSkill52());
		level5.setStartingLevel(AdaptiveAssessmentSkillLevel.LEVEL5);
		adaptiveAssessmentSkillService.saveOrUpdate(level5);
		
		String url = propertyConfig.getBaseUrl();
		url+= "adaptiveAssessment1?startDate=MTU4NjMzMDUyMDAwMA%3D%3D&endDate=MTc0NDA5NjkyMDAwMA%3D%3D&userId=$EMAIL&companyId="+dto.getCompanyId()+"&testId="+adaptiveAssessment.getId()+"&lang=eng";;
		return url;
	}
	
	private String getCampaignLinkForTestTaker(String campaignName, String companyId, String email, String firstName, String lastName, String sdate, String edate,
			String language) {
	language = language==null?"eng":language;
	String url = propertyConfig.getBaseUrl();
	String controllerMethod = "testtaker-campaign";
	campaignName = URLEncoder.encode(Base64.getEncoder().encodeToString(campaignName.getBytes()));
	companyId = URLEncoder.encode(Base64.getEncoder().encodeToString(companyId.getBytes()));
	//email = URLEncoder.encode(Base64.getEncoder().encodeToString(email.getBytes()));//$EMAIL
	email = URLEncoder.encode("$EMAIL");
	firstName = URLEncoder.encode(Base64.getEncoder().encodeToString(firstName.getBytes()));
	lastName = URLEncoder.encode(Base64.getEncoder().encodeToString(lastName.getBytes()));
	sdate = URLEncoder.encode(Base64.getEncoder().encodeToString(sdate.getBytes()));
	edate = URLEncoder.encode(Base64.getEncoder().encodeToString(edate.getBytes()));
	url += controllerMethod + "?campaignName=" + campaignName + "&companyId=" + companyId + "&email=" + email + "&firstName=" + firstName + "&lastName=" + lastName
					+ "&startdate=" + sdate + "&enddate=" + edate + "&lang=" + language;
	return url;
	}
	
	
	
	@RequestMapping(value = "/uploadOilAndGasAssessments", method = RequestMethod.POST)
	public @ResponseBody String uploadOilAndGasAssessments(HttpServletResponse response, MultipartHttpServletRequest request) throws Exception {
		try {
			User user = (User) request.getSession().getAttribute("user");
			MultipartFile multipartFile = request.getFile("fileQuestionsOilAndGas");
			Long size = multipartFile.getSize();
			String fileName = multipartFile.getName();
			String contentType = multipartFile.getContentType();
			InputStream stream = multipartFile.getInputStream();
			File file = ResourceUtils.getFile("classpath:assessmentOilAndGas.xml");

//			File file = new File("questions.xml");
			List<AssessmentExcelDto> records = ExcelReader.parseExcelFileToBeans(stream, file);
				for(AssessmentExcelDto rec : records){
					String skill1 = rec.getLevel1_qualifier1().trim();
					String skill12 = rec.getLevel1_qualifier2().trim();
					String skill2 = rec.getLevel2_qualifier1().trim();
					String skill22 = rec.getLevel2_qualifier2().trim();
					
					String skill3 = rec.getLevel3_qualifier1().trim();
					String skill32 = rec.getLevel3_qualifier2().trim();
					
					String skill4 = rec.getLevel4_qualifier1().trim();
					String skill42 = rec.getLevel4_qualifier2().trim();
					
					String skill5 = rec.getLevel5_qualifier1().trim();
					String skill52 = rec.getLevel5_qualifier2().trim();
					
					
					Long count1 = questionRepository.getCountQuestionsByQualifier1_2(user.getCompanyId(), skill1, skill12);
					Long count2 = questionRepository.getCountQuestionsByQualifier1_2(user.getCompanyId(), skill2, skill22);
					Long count3 = questionRepository.getCountQuestionsByQualifier1_2(user.getCompanyId(), skill3, skill32);
					Long count4 = questionRepository.getCountQuestionsByQualifier1_2(user.getCompanyId(), skill4, skill42);
					Long count5 = questionRepository.getCountQuestionsByQualifier1_2(user.getCompanyId(), skill5, skill52);
						if(count1 <= 9){
							return "Insufficient no Of Questions("+count1+") for Skill 1"+skill1+" and Job Desc "+rec.getJobDesriptionName();
						}
						
						if(count2 <= 9){
							return "Insufficient no Of Questions("+count2+") for Skill 2"+skill2+" and Job Desc "+rec.getJobDesriptionName();
						}
						
						if(count3 <= 9){
							return "Insufficient no Of Questions("+count3+") for Skill 3"+skill3+" and Job Desc "+rec.getJobDesriptionName();
						}
						
						if(count4 <= 9){
							return "Insufficient no Of Questions("+count4+") for Skill 4"+skill4+" and Job Desc "+rec.getJobDesriptionName();
						}
						
						if(count5 <= 9){
							return "Insufficient no Of Questions("+count5+") for Skill 5"+skill5+" and Job Desc "+rec.getJobDesriptionName();
						}
						
						String testLink1 = createNormalAssessment(user.getCompanyName(), user.getCompanyId(), skill1, skill12, count1);
						String testLink2 = createNormalAssessment(user.getCompanyName(), user.getCompanyId(), skill2, skill22, count2);
						String testLink3 = createNormalAssessment(user.getCompanyName(), user.getCompanyId(), skill3, skill32, count3);
						String testLink4 = createNormalAssessment(user.getCompanyName(), user.getCompanyId(), skill4, skill42, count4);
						String testLink5 = createNormalAssessment(user.getCompanyName(), user.getCompanyId(), skill5, skill52, count5);
						
						String campaignLink1 = createCampaign(user.getCompanyName(), user.getCompanyId(), skill1, skill12, skill2, skill22, skill3, skill32, skill4, skill42, skill5, skill52);
					
						
						AdaptiveAssessmentCreationDto dto = new AdaptiveAssessmentCreationDto();
						dto.setAssessmentLink1(testLink1);
						dto.setAssessmentLink2(testLink2);
						dto.setAssessmentLink3(testLink3);
						dto.setAssessmentLink4(testLink4);
						dto.setAssessmentLink5(testLink5);
						
						dto.setCompanyId(user.getCompanyId());
						dto.setCompanyName(user.getCompanyName());
						dto.setCountAss1(count1.intValue() > 30?30:count1.intValue());
						dto.setCountAss2(count2.intValue() > 30?30:count2.intValue());
						dto.setCountAss3(count3.intValue() > 30?30:count3.intValue());
						dto.setCountAss4(count4.intValue() > 30?30:count4.intValue());
						dto.setCountAss5(count5.intValue() > 30?30:count5.intValue());
						dto.setCourseVideo1(rec.getLevel1CourseVideoUrl());
						dto.setCourseVideo2(rec.getLevel2CourseVideoUrl());
						dto.setCourseVideo3(rec.getLevel3CourseVideoUrl());
						dto.setCourseVideo4(rec.getLevel4CourseVideoUrl());
						dto.setCourseVideo5(rec.getLevel5CourseVideoUrl());
						dto.setJobDesc(rec.getJobdesc().trim());
						dto.setJobdescName(rec.getJobDesriptionName().trim());
						dto.setSkill1(skill1);
						dto.setSkill2(skill2);
						dto.setSkill3(skill3);
						dto.setSkill4(skill4);
						dto.setSkill5(skill5);
						
						dto.setSkill12(skill12);
						dto.setSkill22(skill22);
						dto.setSkill32(skill32);
						dto.setSkill42(skill42);
						dto.setSkill52(skill52);
						
						String adaptiveAssessmentLink = createAdaptiveAssessment(dto);
						AssessmentUploadData data = new AssessmentUploadData();
						data.setJobdesc(rec.getJobdesc().trim());
						data.setJobDescName(rec.getJobDesriptionName().trim());
						String name = "Adaptive Assessment - "+dto.getJobdescName();
						data.setAdaptiveAssessmentName(name);
						data.setAdaptiveAssessmentPublicLink(adaptiveAssessmentLink);
						String testName1 = "Assessment - "+skill1+"-"+skill12;
						data.setAssessmentName1(testName1);
						
						String testName2 = "Assessment - "+skill2+"-"+skill22;
						data.setAssessmentName2(testName2);
						
						String testName3 = "Assessment - "+skill3+"-"+skill32;
						data.setAssessmentName3(testName3);
						
						String testName4 = "Assessment - "+skill4+"-"+skill42;
						data.setAssessmentName4(testName4);
						
						String testName5 = "Assessment - "+skill5+"-"+skill52;
						data.setAssessmentName5(testName5);
						
						data.setAssessmentPublicLink1(testLink1);
						data.setAssessmentPublicLink2(testLink2);
						data.setAssessmentPublicLink3(testLink3);
						data.setAssessmentPublicLink4(testLink4);
						data.setAssessmentPublicLink5(testLink5);
						//String campaignName = "Campaign for - "+skill1+", "+skill2+", "+skill3+", "+skill4+", "+skill5+" skills";
						String campaignName = "Campaign for - "+skill1+"-"+skill12+", "+skill22+", "+skill32+", "+skill42+", "+skill52+" skills";
						data.setCampaignName(campaignName);
						data.setCampaignTestLink(campaignLink1);
						data.setCompanyId(user.getCompanyId());
						data.setCompanyName(user.getCompanyName());
						data.setDomain("Oil_Gas");
						data.setAssessment1VideoUIrl(rec.getLevel1CourseVideoUrl());
						data.setAssessment2VideoUrl(rec.getLevel2CourseVideoUrl());
						data.setAssessment3VideoUrl(rec.getLevel3CourseVideoUrl());
						data.setAssessment4VideoUrl(rec.getLevel4CourseVideoUrl());
						data.setAssessment5VideoUrl(rec.getLevel5CourseVideoUrl());
						dataService.saveOrUpdate(data);
				}
			
			
			logger.info("upload qs in db complete");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.error("problem in uploading qs", e);
			return "Problem in Uploading "+e.getMessage();
		}
		return "ok";
	}
}
