package com.assessment.web.controllers;

import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.TrueFileFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.assessment.common.AssessmentGenericException;
import com.assessment.common.PropertyConfig;
import com.assessment.data.AdaptiveAssessment;
import com.assessment.data.AdaptiveAssessmentCriteriaType;
import com.assessment.data.AdaptiveAssessmentSkill;
import com.assessment.data.AdaptiveAssessmentSkillLevel;
import com.assessment.data.AssessmentUploadData;
import com.assessment.data.Company;
import com.assessment.data.Question;
import com.assessment.data.QuestionMapper;
import com.assessment.data.Section;
import com.assessment.data.Test;
import com.assessment.data.TestLinkTime;
import com.assessment.repositories.QuestionMapperRepository;
import com.assessment.repositories.QuestionRepository;
import com.assessment.services.AdaptiveAssessmentService;
import com.assessment.services.AdaptiveAssessmentSkillService;
import com.assessment.services.AssessmentUploadDataService;
import com.assessment.services.CompanyService;
import com.assessment.services.QuestionService;
import com.assessment.services.SectionService;
import com.assessment.services.TestLinkTimeService;
import com.assessment.services.TestService;
import com.assessment.web.dto.NormalAdaptiveAssessmentDto;
import com.assessment.web.dto.NormalAssessmentDto;

@Controller
public class ExamPunditController {

	@Autowired
	QuestionService questionService;
	
	@Autowired
	SectionService sectionservice;
	
	@Autowired
	TestService testService;
	
	@Autowired
	QuestionRepository questionRepository;
	
	@Autowired
	QuestionMapperRepository questionMapperRepository;
	
	@Autowired
	TestLinkTimeService testLinkTimeService;
	
	@Autowired
	PropertyConfig propertyConfig;
	
	@Autowired
	CompanyService companyService;
	
	@Autowired
	AdaptiveAssessmentService adaptiveAssessmentService;
	
	@Autowired
	AdaptiveAssessmentSkillService adaptiveAssessmentSkillService;
	
	@Autowired
	AssessmentUploadDataService dataService;
	
	
	@RequestMapping(value = "/examPunditAssContent", method = RequestMethod.GET)
	public @ResponseBody String examPunditAssContent(@RequestParam(name= "companyId") String companyId, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		File file = new File("examPundit"+File.separator+"assRecords.txt");
		List<String> lines = FileUtils.readLines(file);
			for(String line : lines){
				StringTokenizer stk = new StringTokenizer(line, ",");
				String q1 = stk.nextToken().trim();
				String q2 = stk.nextToken().trim();
				String q3 = stk.nextToken().trim();
				String q4 = stk.nextToken().trim();
				String q5 = stk.nextToken().trim();
				List<Question> questions = questionService.findQuestionsByQualifier5(companyId, q1, q2, q3, q4, q5);
					if(questions.size() <= 100){
						return "fail - "+line+" yielded only "+questions.size()+" questions";
					}
					
				int sectionSize = questions.size() / 3;
				List<Question> section1 = questions.subList(0, sectionSize)	;
				List<Question> section2 = questions.subList(sectionSize, sectionSize * 2)	;
					for(Question q : section2){
						q.setQualifier5(q.getQualifier5()+" Part 2");
						questionRepository.save(q);
					}
					
					
				List<Question> section3 = questions.subList(sectionSize * 2, questions.size())	;
				for(Question q : section3){
					q.setQualifier5(q.getQualifier5()+" Part 3");
					questionRepository.save(q);
				}
				
				genertateAssessments(section1, q1, q2, q3, q4, q5, companyId);
				
				genertateAssessments(section2, q1, q2, q3, q4, q5+" Part 2", companyId);
				genertateAssessments(section3, q1, q2, q3, q4, q5+" Part 3", companyId);
				
			}
			FileUtils.write(new File("ass_examPundit.txt"), " done\n", true);
		return "OK";
	}
	
	private void genertateAssessments(List<Question> section, String q1, String q2, String q3, String q4, String q5, String companyId) throws IOException{
		int bucket = section.size() / 5;
		List<Question> part1 = section.subList(0, bucket );
		List<Question> part2 = section.subList(bucket, (bucket *2) );
		List<Question> part3 = section.subList((bucket *2), (bucket *3) );
		List<Question> part4 = section.subList((bucket *3), (bucket *4) );
		List<Question> part5 = section.subList((bucket *4), section.size());
		Company company = companyService.findByCompanyId(companyId);
		NormalAssessmentDto dto = createNormalAssessment(companyId, company.getCompanyName(), q1, q2, q3, q4, q5, part1, "Section 1");
		NormalAssessmentDto dto2 = createNormalAssessment(companyId, company.getCompanyName(), q1, q2, q3, q4, q5, part2, "Section 2");
		NormalAssessmentDto dto3 = createNormalAssessment(companyId, company.getCompanyName(), q1, q2, q3, q4, q5, part3, "Section 3");
		NormalAssessmentDto dto4 = createNormalAssessment(companyId, company.getCompanyName(), q1, q2, q3, q4, q5, part4, "Section 4");
		NormalAssessmentDto dto5 = createNormalAssessment(companyId, company.getCompanyName(), q1, q2, q3, q4, q5, part5, "Section 5");
		List<NormalAssessmentDto> tests = new ArrayList<NormalAssessmentDto>();
		tests.add(dto);
		tests.add(dto2);
		tests.add(dto3);
		tests.add(dto4);
		tests.add(dto5);
		NormalAdaptiveAssessmentDto adaptiveAssessmentDto = createAdaptiveAssessment(tests, companyId, company.getCompanyName());
		createAssUploadData(adaptiveAssessmentDto, company);
		FileUtils.write(new File("ass_examPundit.txt"), adaptiveAssessmentDto.getAdaptiveAssessmentName()+" created\n", true);
		
	}
	
	private void createAssUploadData(NormalAdaptiveAssessmentDto dto, Company company){
		AssessmentUploadData data = new AssessmentUploadData();
		data.setJobdesc(dto.getIntro());
		data.setJobDescName(dto.getAdaptiveAssessmentName());
		String name = dto.getAdaptiveAssessmentName();
		data.setAdaptiveAssessmentName(name);
		data.setAdaptiveAssessmentPublicLink(dto.getAdaptiveAssessmentLink());
		String testName1 = dto.getTests().get(0).getTestName();
		data.setAssessmentName1(testName1);
		
		String testName2 = dto.getTests().get(1).getTestName();
		data.setAssessmentName2(testName2);
		
		String testName3 = dto.getTests().get(2).getTestName();
		data.setAssessmentName3(testName3);
		
		String testName4 = dto.getTests().get(3).getTestName();
		data.setAssessmentName4(testName4);
		
		String testName5 = dto.getTests().get(4).getTestName();
		data.setAssessmentName5(testName5);
		
		data.setAssessmentPublicLink1(dto.getTests().get(0).getTestLink());
		data.setAssessmentPublicLink2(dto.getTests().get(1).getTestLink());
		data.setAssessmentPublicLink3(dto.getTests().get(2).getTestLink());
		data.setAssessmentPublicLink4(dto.getTests().get(3).getTestLink());
		data.setAssessmentPublicLink5(dto.getTests().get(4).getTestLink());
		//String campaignName = "Campaign for - "+skill1+", "+skill2+", "+skill3+", "+skill4+", "+skill5+" skills";
		String campaignName = "Campaign for - "+dto.getTests().get(0).getQ1()+"-"+dto.getTests().get(0).getQ2()+"-"+dto.getTests().get(0).getQ3()+"-"+dto.getTests().get(0).getQ4()+"_"+dto.getTests().get(1).getQ4()+"_"+dto.getTests().get(2).getQ4()+"_"+dto.getTests().get(3).getQ4()+"_"+dto.getTests().get(4).getQ4();
		data.setCampaignName(campaignName);
		data.setCampaignTestLink("NA");
		data.setCompanyId(company.getCompanyId());
		data.setCompanyName(company.getCompanyName());
		data.setDomain("SSC");
		data.setAssessment1VideoUIrl("https://www.youtube.com/watch?v=o6zTge46XUU");
		data.setAssessment2VideoUrl("https://www.youtube.com/watch?v=o6zTge46XUU");
		data.setAssessment3VideoUrl("https://www.youtube.com/watch?v=o6zTge46XUU");
		data.setAssessment4VideoUrl("https://www.youtube.com/watch?v=o6zTge46XUU");
		data.setAssessment5VideoUrl("https://www.youtube.com/watch?v=o6zTge46XUU");
		dataService.saveOrUpdate(data);
	}
	
	protected NormalAdaptiveAssessmentDto createAdaptiveAssessment(List<NormalAssessmentDto> tests, String companyId, String companyName){
		NormalAdaptiveAssessmentDto dto = new NormalAdaptiveAssessmentDto();
		
		//String testName = "Assessment - "+q1+"-"+q2+"-"+q3+"-"+q4;
		AdaptiveAssessment adaptiveAssessment = new  AdaptiveAssessment();
		adaptiveAssessment.setCompanyId(companyId);
		adaptiveAssessment.setCompanyName(companyName);
		String name = "Adaptive Assessment - ";
		name+= tests.get(0).getQ1()+"-"+tests.get(0).getQ2()+"-"+tests.get(0).getQ3()+"-"+tests.get(0).getQ4()+"-"+tests.get(0).getQ5();
		
		adaptiveAssessment.setAdaptiveAssessmentName(name);
		dto.setAdaptiveAssessmentName(name);
		dto.setTests(tests);
		
		
		String intro = "Staff Selection Commission - Combined Graduate Level Examination, often referred to as SSC is an examination conducted to recruit staff to various posts in ministries, departments and organisations of the Government of India. This is the most popular government job exam in India. Every year 30 to 40 lac of Candidates appear for the exam.";
		adaptiveAssessment.setIntro(intro);
		dto.setIntro(intro);
		adaptiveAssessment.setCoreSkills(tests.get(0).getQ1()+"-"+tests.get(0).getQ2()+"-"+tests.get(0).getQ3()+"-"+tests.get(0).getQ4()+"-"+tests.get(0).getQ5());
		adaptiveAssessment.setNoOfProgressions(3);
		adaptiveAssessment.setNoOfRegressions(3);
		adaptiveAssessment.setNoOfConfigurableAttempts(100);
		adaptiveAssessment.setSentToStudent(true);
		adaptiveAssessmentService.saveOrUpdate(adaptiveAssessment);
		
		AdaptiveAssessmentSkill level1 = new AdaptiveAssessmentSkill();
		level1.setAdaptiveAssessmentName(name);
		level1.setAssessmentLinkAfterCourseCompletion(tests.get(0).getTestLink());
		level1.setCompanyId(companyId);
		level1.setCompanyName(companyName);
		level1.setCourseLinkRecommendation("https://www.youtube.com/watch?v=MV-srcT1Wi4");
		level1.setCriteriaType(AdaptiveAssessmentCriteriaType.NONE);
		level1.setNoOfProgressions(3);
		level1.setNoOfRegressions(3);
		level1.setPassPercentForLevel(60);
		int countLevel1 = (int)(tests.get(0).getNoOfQuestions()*(50/100.0f));
		level1.setNoOfQuestions(countLevel1);
		level1.setQualifier1(tests.get(0).getQ1());
		level1.setQualifier2(tests.get(0).getQ2());
		level1.setQualifier3(tests.get(0).getQ3());
		level1.setQualifier4(tests.get(0).getQ4());
		level1.setQualifier5(tests.get(0).getQ5());
		level1.setSkillName(tests.get(0).getQ5());
		level1.setStartingLevel(AdaptiveAssessmentSkillLevel.LEVEL1);
		adaptiveAssessmentSkillService.saveOrUpdate(level1);
		
		
		
		
		AdaptiveAssessmentSkill level2 = new AdaptiveAssessmentSkill();
		level2.setAdaptiveAssessmentName(name);
		level2.setAssessmentLinkAfterCourseCompletion(tests.get(1).getTestLink());
		level2.setCompanyId(companyId);
		level2.setCompanyName(companyName);
		level2.setCourseLinkRecommendation("https://www.youtube.com/watch?v=MV-srcT1Wi4");
		level2.setCriteriaType(AdaptiveAssessmentCriteriaType.NONE);
		level2.setNoOfProgressions(3);
		level2.setNoOfRegressions(3);
		level2.setPassPercentForLevel(60);
		int countLevel2 = (int)(tests.get(1).getNoOfQuestions()*(50/100.0f));
		level2.setNoOfQuestions(countLevel2);
		level2.setQualifier1(tests.get(1).getQ1());
		level2.setQualifier2(tests.get(1).getQ2());
		level2.setQualifier3(tests.get(1).getQ3());
		level2.setQualifier4(tests.get(1).getQ4());
		level2.setQualifier5(tests.get(1).getQ5());
		level2.setSkillName(tests.get(1).getQ5());
		level2.setStartingLevel(AdaptiveAssessmentSkillLevel.LEVEL2);
		adaptiveAssessmentSkillService.saveOrUpdate(level2);
		
		
		AdaptiveAssessmentSkill level3 = new AdaptiveAssessmentSkill();
		level3.setAdaptiveAssessmentName(name);
		level3.setAssessmentLinkAfterCourseCompletion(tests.get(2).getTestLink());
		level3.setCompanyId(companyId);
		level3.setCompanyName(companyName);
		level3.setCourseLinkRecommendation("https://www.youtube.com/watch?v=MV-srcT1Wi4");
		level3.setCriteriaType(AdaptiveAssessmentCriteriaType.NONE);
		level3.setNoOfProgressions(3);
		level3.setNoOfRegressions(3);
		level3.setPassPercentForLevel(60);
		int countLevel3 = (int)(tests.get(2).getNoOfQuestions()*(50/100.0f));
		level3.setNoOfQuestions(countLevel3);
		level3.setQualifier1(tests.get(2).getQ1());
		level3.setQualifier2(tests.get(2).getQ2());
		level3.setQualifier3(tests.get(2).getQ3());
		level3.setQualifier4(tests.get(2).getQ4());
		level3.setQualifier5(tests.get(2).getQ5());
		level3.setSkillName(tests.get(2).getQ5());
		level3.setStartingLevel(AdaptiveAssessmentSkillLevel.LEVEL3);
		adaptiveAssessmentSkillService.saveOrUpdate(level3);
		
		AdaptiveAssessmentSkill level4 = new AdaptiveAssessmentSkill();
		level4.setAdaptiveAssessmentName(name);
		level4.setAssessmentLinkAfterCourseCompletion(tests.get(3).getTestLink());
		level4.setCompanyId(companyId);
		level4.setCompanyName(companyName);
		level4.setCourseLinkRecommendation("https://www.youtube.com/watch?v=MV-srcT1Wi4");
		level4.setCriteriaType(AdaptiveAssessmentCriteriaType.NONE);
		level4.setNoOfProgressions(3);
		level4.setNoOfRegressions(3);
		level4.setPassPercentForLevel(60);
		int countlevel4 = (int)(tests.get(3).getNoOfQuestions()*(50/100.0f));
		level4.setNoOfQuestions(countlevel4);
		level4.setQualifier1(tests.get(3).getQ1());
		level4.setQualifier2(tests.get(3).getQ2());
		level4.setQualifier3(tests.get(3).getQ3());
		level4.setQualifier4(tests.get(3).getQ4());
		level4.setQualifier5(tests.get(3).getQ5());
		level4.setSkillName(tests.get(3).getQ5());
		level4.setStartingLevel(AdaptiveAssessmentSkillLevel.LEVEL4);
		adaptiveAssessmentSkillService.saveOrUpdate(level4);
		
		AdaptiveAssessmentSkill level5 = new AdaptiveAssessmentSkill();
		level5.setAdaptiveAssessmentName(name);
		level5.setAssessmentLinkAfterCourseCompletion(tests.get(4).getTestLink());
		level5.setCompanyId(companyId);
		level5.setCompanyName(companyName);
		level5.setCourseLinkRecommendation("https://www.youtube.com/watch?v=MV-srcT1Wi4");
		level5.setCriteriaType(AdaptiveAssessmentCriteriaType.NONE);
		level5.setNoOfProgressions(3);
		level5.setNoOfRegressions(3);
		level5.setPassPercentForLevel(60);
		int countlevel5 = (int)(tests.get(4).getNoOfQuestions()*(50/100.0f));
		level5.setNoOfQuestions(countlevel5);
		level5.setQualifier1(tests.get(4).getQ1());
		level5.setQualifier2(tests.get(4).getQ2());
		level5.setQualifier3(tests.get(4).getQ3());
		level5.setQualifier4(tests.get(4).getQ4());
		level5.setQualifier4(tests.get(4).getQ5());
		level5.setSkillName(tests.get(4).getQ5());
		level5.setStartingLevel(AdaptiveAssessmentSkillLevel.LEVEL5);
		adaptiveAssessmentSkillService.saveOrUpdate(level5);
		
		String url = propertyConfig.getBaseUrl();
		url+= "adaptiveAssessment1?startDate=MTU4NjMzMDUyMDAwMA%3D%3D&endDate=MTc0NDA5NjkyMDAwMA%3D%3D&userId=$EMAIL&companyId="+companyId+"&testId="+adaptiveAssessment.getId()+"&lang=eng";;
		
		dto.setAdaptiveAssessmentLink(url);
		return dto;
	}
	
	
	protected NormalAssessmentDto createNormalAssessment(String companyId, String companyName, String q1, String q2, String q3, String q4,String q5, List<Question> questions, String alias){
		NormalAssessmentDto dto = new NormalAssessmentDto();
		String testName = "Assessment - "+q1+"-"+q2+"-"+q3+"-"+q4+"-"+q5+" ("+alias+")";
		dto.setTestName(testName);
		dto.setQ1(q1);
		dto.setQ2(q2);
		dto.setQ3(q3);
		dto.setQ4(q4);
		dto.setQ5(q5);
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
		test.setQualifier1(q1);
		test.setQualifier2(q2);
		test.setQualifier3(q3);
		test.setQualifier4(q4);
		test.setQualifier5(q5);
		test.setTestTimeInMinutes(questions.size() + 10);
		
		
		dto.setNoOfQuestions(questions.size());
		test.setTotalMarks(questions.size());
		Test test2 = testService.findbyTest(testName, companyId);
			if(test2 != null){
				test.setId(test2.getId());
			}
		testService.saveOrUpdate(test);
		 
		Section section = new Section();
		section.setCompanyId(companyId);
		section.setCompanyName(companyName);
		section.setSectionName(q5);
		section.setPercentQuestionsAsked(100);
		section.setNoOfQuestions(questions.size());
		section.setSectionTimeInMinutes(30);
		section.setNoOfQuestionsToBeAsked(questions.size());
		
		section.setTestName(testName);
		section.setCreateDate(new Date());
		Section section2 = sectionservice.findByPrimaryKey(testName, section.getSectionName(), companyId);
			if(section2 == null){
				sectionservice.createSection(section);
			}
		for(Question question : questions){
			QuestionMapper mapper = questionMapperRepository.findUniqueQuestionMapper(section.getSectionName(), testName, question.getId(), companyId);
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
		dto.setTestLink(url);
		return dto;
	}
	
	private String getUrlForUser(String user, Long testId, String companyId) {
		 //String userBytes =  Base64.getEncoder().encodeToString(user.getBytes());
		 
		 String after = "userId="+URLEncoder.encode(user)+"&testId="+URLEncoder.encode(testId.toString())+"&companyId="+URLEncoder.encode(companyId);
		 String url = propertyConfig.getBaseUrl()+"mcq2?"+after;
		 return url;
	  }
	

	@RequestMapping(value = "/examPunditContent", method = RequestMethod.GET)
	public @ResponseBody String generatedrishContent(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String loc = "D:/panditExamQ";
		File dir = new File(loc);
		List<File> files = (List<File>) FileUtils.listFiles(dir, TrueFileFilter.INSTANCE,
				TrueFileFilter.INSTANCE);
		for (File file : files) {
			List<Question> qs = extractQuestions(file);
			for (Question q : qs) {

				questionService.createQuestion(q);
			}

		}
		System.out.println("Process Complete");
//		FileUtils.write(new File("drishti.out"), "********* file processing complete", true);
		return "OK";
	}

	/**
	 * @param file
	 * @return
	 * @throws Exception
	 */
	public List<Question> extractQuestions(File file) throws Exception {
		List<Question> questions = new ArrayList<Question>();
		LinkedList<String> answers = (LinkedList<String>) extractAnswers(file);
		String text = FileUtils.readFileToString(file, StandardCharsets.UTF_8);
		int count2 = 1;
		try {

			for (int i = 1; i <= answers.size(); i++) {
//				String	 text = questions.substring(questions.indexOf(i+")"), questions.length());
				String q;
				String a1;
				String b1;
				String c1;
				String d1;
				Question question = new Question();
				try {
					q = text.substring(text.indexOf(i + ")"), text.indexOf("A)"));
					String s1 = i + ")";
					int sepPos1 = q.indexOf(s1);
					String q2 = q.substring(sepPos1 + s1.length()).trim();
					a1 = text.substring(text.indexOf("A)"), text.indexOf("B)"));
					String s2 =  "A)";
					int sepPos2 = a1.indexOf(s2);
					String a2 = a1.substring(sepPos2 + s2.length()).trim();
					b1 = text.substring(text.indexOf("B)"), text.indexOf("C)"));
					String s3 =  "B)";
					int sepPos3 = b1.indexOf(s3);
					String b2 = b1.substring(sepPos3 + s3.length()).trim();
					c1 = text.substring(text.indexOf("C)"), text.indexOf("D)"));
					String s4 =  "C)";
					int sepPos4 = c1.indexOf(s4);
					String c2 = c1.substring(sepPos4 + s4.length()).trim();
					

					if (i == answers.size()) {
						d1 = text.substring(text.indexOf("D)"), text.indexOf("KEY"));
					} else {
						d1 = text.substring(text.indexOf("D)"), text.indexOf(i + 1 + ")"));
					}
					
					String s5 =  "D)";
					int sepPos5 = d1.indexOf(s5);
					String d2 = d1.substring(sepPos5 + s5.length()).trim();
					
					question.setQuestionText(q2);
					question.setChoice1(a2);
					question.setChoice2(b2);
					question.setChoice3(c2);
					question.setChoice4(d2);
//						System.out.println(" " + q);
//						System.out.println("  " + a1);
//						System.out.println(" " + b1);
//						System.out.println("  " + c1);
//						System.out.println("  " + d1);
					String right = getRightAnswer(i, answers);
					question.setRightChoices(right);
					question.setCompanyId("e-assess");
					question.setCompanyName("e-assess");
					count2++;
					if (count2 <= answers.size()) {
						text = text.substring(text.indexOf(count2 + ")"), text.length());
					}
					System.out.println("Qs:  " + i +" "+q2);
					System.out.println("Right:  " + right);
					questions.add(question);
				} catch (Exception e) {
					// TODO: handle exception
					System.out.println(e.getMessage());
				}

			}

			System.out.println("Count :  " + count2);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
			System.out.println(e.getMessage());

		}
		return questions;
	}

	private List<String> extractAnswers(File file) throws Exception {
		LinkedList<String> answers = new LinkedList<>();
		String ss = FileUtils.readFileToString(file, StandardCharsets.UTF_8);
		String result = ss.substring(ss.indexOf("KEY") + 5, ss.length()).replaceAll("\\s", "");
		String pattern = "\\d{1,3}{1,}([A-D])";
//		String pattern = "\\d{1,2}([\\.])[ ]{1,}[(]([a-d])[)]";
		Pattern p = Pattern.compile(pattern);
		Matcher matcher = p.matcher(result);
		int count = 0;
		while (matcher.find()) {
			int start = matcher.start();
			int end = matcher.end();
			answers.add(result.substring(start, end));
			count++;
			// System.out.println("found: " + count +"- "+text.substring(start, end));

		}
		System.out.println("         " + count);
		return answers;
	}

	private String getRightAnswer(int quesNo, List<String> answers) throws Exception {
		String temp2 = "";
		for (int i = 0; i < answers.size(); i++) {

			String temp = answers.get(i);
			temp = temp.substring(temp.length() - 1);
			if (quesNo == i + 1) {

				if (temp.equals("A")) {
					temp2 = "Choice 1";
				} else if (temp.equals("B")) {
					temp2 = "Choice 2";
				} else if (temp.equals("C")) {
					temp2 = "Choice 3";
				} else if (temp.equals("D")) {
					temp2 = "Choice 4";
				} else {
					throw new AssessmentGenericException("Problem - no right answer");
				}
			}

		}
//		System.out.println("....                     "+temp2);
		return temp2;
	}

}