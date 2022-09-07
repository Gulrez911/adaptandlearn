package com.assessment.web.controllers;

import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.TrueFileFilter;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
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
import com.google.common.collect.Lists;
@Controller
public class DrishtiContentGenerator {
	
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
	
	protected NormalAssessmentDto createNormalAssessment(String companyId, String companyName, String q1, String q2, String q3, String q4){
		NormalAssessmentDto dto = new NormalAssessmentDto();
		String testName = "Assessment - "+q1+"-"+q2+"-"+q3+"-"+q4;
		dto.setTestName(testName);
		dto.setQ1(q1);
		dto.setQ2(q2);
		dto.setQ3(q3);
		dto.setQ4(q4);
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
		test.setTestTimeInMinutes(30);
		
		List<Question> questions  = questionRepository.findQuestionsByQualifier4(companyId, q1, q2, q3, q4);
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
		section.setSectionName(q4);
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
	
	@RequestMapping(value = "/generateDrishAssRec", method = RequestMethod.GET)
	public @ResponseBody String generateDrishAssRec(@RequestParam(name= "companyId") String companyId, HttpServletRequest request, HttpServletResponse response) throws Exception{
		String loc = "/opt/ContentData/drishtiAss/Drishti.txt";
		Company company = companyService.findByCompanyId(companyId);
			if(company == null){
				return "Company Invalid";
			}
		List<String> lines = FileUtils.readLines(new File(loc));
		
		int bucket = 5;
		
		int diff = lines.size() % 5;
			if(diff != 0){
				return "No symmetry";
			}
		
		int noOfBuckets = lines.size() / 5;
		List<List<String>> group = Lists.partition(lines, 5);
		for(int i=0;i<group.size();i++){
			List<String> ass = group.get(i);
				if(ass.size() != 5){
					return "No symmetry 2";
				}
		}
		
		for(List<String> ass : group){
			List<NormalAssessmentDto> tests = new ArrayList<NormalAssessmentDto>();
			for(String line : ass){
				StringTokenizer stk = new StringTokenizer(line, ",");
				if(stk.countTokens() != 4){
					throw new AssessmentGenericException("problem in reading Dtrishti.txt");
				}
				String q1 = stk.nextToken().trim();
				String q2 = stk.nextToken().trim();
				String q3 = stk.nextToken().trim();
				String q4 = stk.nextToken().trim();
				NormalAssessmentDto dto = createNormalAssessment(company.getCompanyId(), company.getCompanyName(), q1, q2, q3, q4);
				tests.add(dto);
			}
			//
			NormalAdaptiveAssessmentDto dto = createAdaptiveAssessment(tests, company.getCompanyId(), company.getCompanyName());
			createAssUploadData(dto, company);
			FileUtils.write(new File("ass_drishti.txt"), dto.getAdaptiveAssessmentName()+" created\n", true);
		}
		
		FileUtils.write(new File("ass_drishti.txt"), "Processing done\n", true);
		
		return "ok";
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
		data.setDomain("UPSC");
		data.setAssessment1VideoUIrl("https://www.youtube.com/watch?v=opM0ox8nh8g");
		data.setAssessment2VideoUrl("https://www.youtube.com/watch?v=opM0ox8nh8g");
		data.setAssessment3VideoUrl("https://www.youtube.com/watch?v=opM0ox8nh8g");
		data.setAssessment4VideoUrl("https://www.youtube.com/watch?v=opM0ox8nh8g");
		data.setAssessment5VideoUrl("https://www.youtube.com/watch?v=opM0ox8nh8g");
		dataService.saveOrUpdate(data);
	}
	
	
	protected NormalAdaptiveAssessmentDto createAdaptiveAssessment(List<NormalAssessmentDto> tests, String companyId, String companyName){
		NormalAdaptiveAssessmentDto dto = new NormalAdaptiveAssessmentDto();
		
		//String testName = "Assessment - "+q1+"-"+q2+"-"+q3+"-"+q4;
		AdaptiveAssessment adaptiveAssessment = new  AdaptiveAssessment();
		adaptiveAssessment.setCompanyId(companyId);
		adaptiveAssessment.setCompanyName(companyName);
		String name = "Adaptive Assessment - ";
		name+= tests.get(0).getQ1()+"-"+tests.get(0).getQ2()+"-"+tests.get(0).getQ3()+"- Section 1 to 5";
		
		adaptiveAssessment.setAdaptiveAssessmentName(name);
		dto.setAdaptiveAssessmentName(name);
		dto.setTests(tests);
		
		
		String intro = "The nature of IAS examination is to test the candidate s awareness about the happenings around him/her. It a is test of alertness and awareness and not just knowledge! Students need to be updated on all current issues - regional, national or international. He/She should have sufficient knowledge of current issues which is tested by UPSC at all stages (Prelims, Mains and Interview). This assessment will evaluate your knowledge on the said topic.";
		adaptiveAssessment.setIntro(intro);
		dto.setIntro(intro);
		adaptiveAssessment.setCoreSkills(tests.get(0).getQ1()+"-"+tests.get(0).getQ2());
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
		level1.setSkillName(tests.get(0).getQ1() +" - "+tests.get(0).getQ2());
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
		level2.setSkillName(tests.get(1).getQ1() +" - "+tests.get(1).getQ2());
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
		level3.setSkillName(tests.get(2).getQ1() +" - "+tests.get(2).getQ2());
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
		level4.setSkillName(tests.get(3).getQ1() +" - "+tests.get(3).getQ2());
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
		level5.setSkillName(tests.get(4).getQ1() +" - "+tests.get(4).getQ2());
		level5.setStartingLevel(AdaptiveAssessmentSkillLevel.LEVEL5);
		adaptiveAssessmentSkillService.saveOrUpdate(level5);
		
		String url = propertyConfig.getBaseUrl();
		url+= "adaptiveAssessment1?startDate=MTU4NjMzMDUyMDAwMA%3D%3D&endDate=MTc0NDA5NjkyMDAwMA%3D%3D&userId=$EMAIL&companyId="+companyId+"&testId="+adaptiveAssessment.getId()+"&lang=eng";;
		
		dto.setAdaptiveAssessmentLink(url);
		return dto;
	}
	
	public String getUrlForUser(String user, Long testId, String companyId) {
		 //String userBytes =  Base64.getEncoder().encodeToString(user.getBytes());
		 
		 String after = "userId="+URLEncoder.encode(user)+"&testId="+URLEncoder.encode(testId.toString())+"&companyId="+URLEncoder.encode(companyId);
		 String url = propertyConfig.getBaseUrl()+"mcq2?"+after;
		 return url;
	  }
	
	
	@RequestMapping(value = "/generatedrishContent", method = RequestMethod.GET)
	public @ResponseBody String generatedrishContent(  HttpServletRequest request, HttpServletResponse response) throws Exception{
		String loc = "/opt/ContentData/drishti";
		File dir = new File(loc);
		List<File> files = (List<File>) FileUtils.listFiles(dir, TrueFileFilter.INSTANCE, TrueFileFilter.INSTANCE);
		for(File file : files){
			List<Question> qs = extractQuestions(file);
			System.out.println("Questions size for "+file+" is "+qs.size());
			if(qs.size() >= 25){
				List<List<Question>> partions = new ArrayList<>();
				int bucket = qs.size() / 5;
				int cnt = 0;
				List<Question> part1 = qs.subList(0, bucket );
				List<Question> part2 = qs.subList(bucket, (bucket *2) );
				List<Question> part3 = qs.subList((bucket *2), (bucket *3) );
				List<Question> part4 = qs.subList((bucket *3), (bucket *4) );
				List<Question> part5 = qs.subList((bucket *4), qs.size());
				partions.add(part1);	
				partions.add(part2);
				partions.add(part3);
				partions.add(part4);
				partions.add(part5);
				
				if(partions.size() != 5){
					System.out.println("some problem");
					
				}
				else{
					for(int i=0; i<5; i++){
						List<Question> qsBatch = partions.get(i);
						for(Question q: qsBatch){
							q.setQualifier4("Section "+(i+1));
							String q3 =q.getQualifier3().substring(0, q.getQualifier3().indexOf("."));
							q.setQualifier3(q3);
							q.setup();
							questionService.createQuestion(q);
						}
					}
				}
				FileUtils.write(new File("drishti.out"), "file processing complete for "+file.getName(), true);
			}
		}
		System.out.println("Process Complete");
		FileUtils.write(new File("drishti.out"), "********* file processing complete", true);
		return "OK";
	}
	
	private List<String> extractAnswers(File file) throws Exception{
		List<String> answers = new ArrayList<>();
		try {
            PDDocument doc = PDDocument.load(file);
            PDFTextStripper reader = new PDFTextStripper();
            
            reader.setStartPage(doc.getNumberOfPages() -1);
            String text = reader.getText(doc);
            //System.out.println(text);
            
            String pattern = "\\d{1,2}([\\.])[ ]{1,}[(]([a-d])[)]";
            
            Pattern p = Pattern.compile(pattern);
            Matcher matcher = p.matcher(text);
            //int count = matcher.groupCount();
            int count = 0;
            while(matcher.find()) {
                count++;
                int start = matcher.start();
                int end =  matcher.end();
                answers.add(text.substring(start, end));
               // System.out.println("found: " + count +"- "+text.substring(start, end));
                
            }
		}
		catch(Exception e){
			//e.printStackTrace();
			System.out.println(e.getMessage());
			throw e;
		}
		return answers;
	}
	
	public List<Question> extractQuestions(File file) throws Exception{
		List<Question> questions = new ArrayList<Question>();
		List<String> answers = extractAnswers(file);
//		StringTokenizer stk = new StringTokenizer(file.getName(), " ");
//		String one = stk.nextToken();
//		String two = stk.nextToken();
		try {
            PDDocument doc = PDDocument.load(file);
            PDFTextStripper reader = new PDFTextStripper();
            reader.setStartPage(2);
            //reader.setEndPage(i);
            String text = reader.getText(doc);
            text = text.substring(text.indexOf(1+". "), text.length());
            
	            for(int i=1; i<=100; i++){
	            	String q = "";
	            	String a1;
					String b1;
					String c1;
					String d1 = "";
					Question question = new Question();
					try {
						q = text.substring(text.indexOf(i+". "), text.indexOf("(a)"));
						a1 = text.substring(text.indexOf("(a)"), text.indexOf("(b)"));
						b1 = text.substring(text.indexOf("(b)"), text.indexOf("(c)"));
						c1 = text.substring(text.indexOf("(c)"), text.indexOf("(d)"));
						
						
						try {
							
							question.setQuestionText(q);
							String right = getRightAnswer(q, answers);
							question.setRightChoices(right);
							if(question.getOne() == false && question.getTwo() == false && question.getThree() == false && question.getFour() == false ){
								System.out.println("wrong Q since no right ans "+file.getName());
							}
							else{
								question.setQualifier1("Competitive  Exams");
								question.setQualifier2("Current Affairs");
								question.setQualifier3(file.getName());
								//question.setQualifier4(two);
								question.setCompanyId("AL");
								question.setCompanyName("AL");
								questions.add(question);
							}
							
							if(text.indexOf((i+1)+". ") < text.indexOf("(d)")){
								String tempQ = q.replace((i+1)+". ", ""+(i+1));
								String tempa1 = a1.replace((i+1)+". ", ""+(i+1));
								String tempb1 = b1.replace((i+1)+". ", ""+(i+1));
								String tempc1 = c1.replace((i+1)+". ", ""+(i+1));
								String tempText = text.replace((q + a1 + b1 + c1), (tempQ + tempa1 + tempb1 + tempc1));
								d1 = tempText.substring(tempText.indexOf("(d)"), tempText.indexOf((i+1)+". "));
							}
							else{
								d1 = text.substring(text.indexOf("(d)"), text.indexOf((i+1)+". "));
							}
							question.setChoice1(a1);
							question.setChoice2(b1);
							question.setChoice3(c1);
							question.setChoice4(d1);
						text = text.substring((q + a1 + b1 + c1 + d1).length(), text.length());
						} catch (Exception e) {
							// TODO Auto-generated catch block
							//e.printStackTrace();
							System.out.println(e.getMessage());
							
						}
					} catch (Exception e) {
						// TODO Auto-generated catch block
						//e.printStackTrace();
						System.out.println(e.getMessage());
						break;
					}
	            	//System.out.println("Q "+q);
	            	//System.out.println("Option 1 "+a1);
	            	//System.out.println("Option 2 "+b1);
	            	//System.out.println("Option 3 "+c1);
	            	//System.out.println("Option 4 "+d1);
	            }
            //System.out.println("Text in PDF\n---------------------------------");
           // System.out.println(text);
            } 
	 	catch (IOException e) {
            e.printStackTrace();
        }
		 return questions;
	}
	
	private String getRightAnswer(String questionText,  List<String> answers) throws Exception{
		 String pattern = "\\d{1,2}([\\.])[ ]{1,}";
        
        Pattern p = Pattern.compile(pattern);
        Matcher matcher = p.matcher(questionText);
        if(matcher.find()){
       	 int start = matcher.start();
            int end =  matcher.end();
       	 String init = questionText.substring(start, end);
       	 for(String ans : answers){
       		 init = init.trim();
       		 StringTokenizer stk = new StringTokenizer(ans, " ");
       		 String qInit = stk.nextToken();
       		 qInit = qInit.trim();
       		 if(init.equals(qInit)){
       			String aInit =  stk.nextToken().trim();
       			if(aInit.equals("(a)")){
       				return "Choice 1";
       			}
       			else if(aInit.equals("(b)")){
       				return "Choice 2";
       			}
       			else if(aInit.equals("(c)")){
       				return "Choice 3";
       			}
       			else if(aInit.equals("(d)")){
       				return "Choice 4";
       			}
       			else{
       				throw new AssessmentGenericException("Problem - no right answer");
       			}
       		 }
       	 }
       	 throw new AssessmentGenericException("Problem - no right answer");
        }
        else{
       	 throw new AssessmentGenericException("Problem");
        }
	}

}
