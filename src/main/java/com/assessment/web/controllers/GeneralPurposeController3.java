package com.assessment.web.controllers;

import java.io.File;
import java.io.IOException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
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
public class GeneralPurposeController3 {
	@Autowired
	QuestionRepository questionRep;
	
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
	
	@Autowired
	QuestionService questionService;
	
	@Autowired
	SectionService sectionservice;
	
	@Autowired
	TestService testService;
	
	@Autowired
	DrishtiContentGenerator drishtiGen;
	
	
	
	
	@RequestMapping(value = "/generateContentForGroupOf5TillQual5", method = RequestMethod.GET)
	public @ResponseBody String generateContentForGroupOf5TillQual5(@RequestParam(name= "companyId") String companyId,@RequestParam(name= "pathtoFile") String pathtoFile, HttpServletRequest request, HttpServletResponse response) throws Exception{
		pathtoFile = URLDecoder.decode(pathtoFile);
		System.out.println("path to file "+pathtoFile);
		List<String> lines = FileUtils.readLines(new File(pathtoFile));
		Company company = companyService.findByCompanyId(companyId);
		Map<String, List<List<String>>> map = new HashMap();
			for(String line : lines){
				StringTokenizer stk = new StringTokenizer(line, ",");
				String q1 = stk.nextToken().trim();
				String q2 = stk.nextToken().trim();
				String q3 = stk.nextToken().trim();
				String q4 = stk.nextToken().trim();
				String q5 = stk.nextToken().trim();
				String group = stk.nextToken().trim();
					
				if(map.get(group) == null) {
					List<String> quals = new ArrayList();
					quals.add(q1);
					quals.add(q2);
					quals.add(q3);
					quals.add(q4);
					quals.add(q5);
					List<List<String>> container = new ArrayList();
					container.add(quals);
					map.put(group, container);
				}
				else {
					List<String> quals = new ArrayList();
					quals.add(q1);
					quals.add(q2);
					quals.add(q3);
					quals.add(q4);
					quals.add(q5);
					map.get(group).add(quals);
					
				}
			}
		for(String key : map.keySet()) {
			List<List<String>> quals = map.get(key);
			if(quals.size() != 5) {
				FileUtils.write(new File("groupOf5Failures.txt"), key+" group has "+quals.size()+" elements \n", true);
			}
			else {
				
				processsQuestions(quals, companyId, companyId, key);
			}
		}
			FileUtils.write(new File("endOfProcess.txt"), "done on "+new Date().toString()+"\n", true);
		System.out.println("All complete");
		return "ok";
	}
	
	private List<Question> getQuestions(List<String> quals, String companyId) throws Exception{
		String q1 = quals.get(0);
		String q2 = quals.get(1);
		String q3 = quals.get(2);
		String q4 = quals.get(3);
		String q5 = quals.get(4);
		List<Question> qs  = new ArrayList<>();
		if(q3 == null || q3.trim().length() == 0 || q3.equalsIgnoreCase("null")){
			qs = questionRep.findQuestionsByQualifier2(companyId, q1, q2);
			q3 = null;
		}
		else if(q4 == null || q4.trim().length() == 0 || q4.equalsIgnoreCase("null")){
			qs = questionRep.findQuestionsByQualifier3(companyId, q1, q2, q3);
			q4 = null;
		}
		else if(q5 == null || q5.trim().length() == 0 || q5.equalsIgnoreCase("null")){
			qs = questionRep.findQuestionsByQualifier4(companyId, q1, q2, q3, q4);
			q5 = null;
		}
		else if(q5 != null && q5.trim().length() > 0 && !q5.equalsIgnoreCase("null")){
			qs = questionRep.findQuestionsByQualifier5(companyId, q1, q2, q3, q4, q5);
		}
		else{
			System.out.println("PROBLEM IN INPUT FILE. CAN NOT COME HERE ");
			System.out.println(q1);
			System.out.println(q2);
			System.out.println(q3);
			System.out.println(q4);
			System.out.println(q5);
			String q = q1 +(q2==null?",na":","+q2)+(q3==null?",na":","+q3)+(q4==null?",na":","+q4)+(q5==null?",na":","+q5);
			FileUtils.write(new File("badInputFileGroupOf5.txt"), q+"\n", true);
		}
		if(qs.size() == 0) {
			String q = q1 +(q2==null?",na":","+q2)+(q3==null?",na":","+q3)+(q4==null?",na":","+q4)+(q5==null?",na":","+q5);
			throw new AssessmentGenericException("No data for "+q);
		}
		return qs;
	}
	
	private void processsQuestions(List<List<String>> qs,String companyId, String companyName, String group) throws Exception{
		List<List<Question>> partions = new ArrayList<>();
		
		
		
		List<String> quals1 = qs.get(0);
		List<String> quals2 = qs.get(1);
		List<String> quals3 = qs.get(2);
		List<String> quals4 = qs.get(3);
		List<String> quals5 = qs.get(4);
		List<Question> part1 = getQuestions(quals1, companyId);
		List<Question> part2 = getQuestions(quals2, companyId);
		List<Question> part3 = getQuestions(quals3, companyId);
		List<Question> part4 = getQuestions(quals4, companyId);
		List<Question> part5 = getQuestions(quals5, companyId);
		try {
			
				
			List<NormalAssessmentDto> tests = new ArrayList<NormalAssessmentDto>();
			NormalAssessmentDto dto1 = createNormalAssessment(companyId, companyName, quals1.get(0), quals1.get(1), quals1.get(2), quals1.get(3), quals1.get(4), part1, group);
			NormalAssessmentDto dto2 = createNormalAssessment(companyId, companyName, quals2.get(0), quals2.get(1), quals2.get(2), quals2.get(3), quals2.get(4), part2, group);
			NormalAssessmentDto dto3 = createNormalAssessment(companyId, companyName, quals3.get(0), quals3.get(1), quals3.get(2), quals3.get(3), quals3.get(4), part3, group);
			NormalAssessmentDto dto4 = createNormalAssessment(companyId, companyName, quals4.get(0), quals4.get(1), quals4.get(2), quals4.get(3), quals4.get(4), part4, group);
			NormalAssessmentDto dto5 = createNormalAssessment(companyId, companyName, quals5.get(0), quals5.get(1), quals5.get(2), quals5.get(3), quals5.get(4), part5, group);
			tests.add(dto1);
			tests.add(dto2);
			tests.add(dto3);
			tests.add(dto4);
			tests.add(dto5);
			NormalAdaptiveAssessmentDto dto = createAdaptiveAssessment(tests, companyId, companyName, group);
			Company company = companyService.findByCompanyId(companyId);
			createAssUploadData(dto, company);
			
			FileUtils.write(new File("ass_gulrez4.txt"), quals1.get(0)+","+quals1.get(1)+","+quals1.get(2)+","+getAggregatedSkillNam(tests)+","+dto.getAdaptiveAssessmentName()+"\n", true);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			FileUtils.write(new File("problemAssCreation.txt"), e.getMessage()+"\n"+(quals1.get(0)+","+ quals1.get(1)+","+ quals1.get(2)+","+(quals1.get(3) == null?"": quals1.get(3))+","+(quals1.get(4) == null?"":quals1.get(4)))+","+ part1, true);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			FileUtils.write(new File("problemAssCreation.txt"), e.getMessage()+"\n"+(quals1.get(0)+","+ quals1.get(1)+","+ quals1.get(2)+","+(quals1.get(3) == null?"": quals1.get(3))+","+(quals1.get(4) == null?"":quals1.get(4)))+","+ part1, true);
		}
	}
	
	private String getAggregatedSkillNam(List<NormalAssessmentDto> tests) {
		String ret = "";
			if(tests.get(0).getQ4() != null && tests.get(0).getQ4().trim().length() > 0) {
				ret += tests.get(0).getQ4().trim()+",";
			}
			
			if(tests.get(1).getQ4() != null && tests.get(1).getQ4().trim().length() > 0) {
				ret += tests.get(1).getQ4().trim()+",";
			}
			
			if(tests.get(2).getQ4() != null && tests.get(2).getQ4().trim().length() > 0) {
				ret += tests.get(2).getQ4().trim()+",";
			}
			
			if(tests.get(3).getQ4() != null && tests.get(3).getQ4().trim().length() > 0) {
				ret += tests.get(3).getQ4().trim()+",";
			}
			
			if(tests.get(4).getQ4() != null && tests.get(4).getQ4().trim().length() > 0) {
				ret += tests.get(4).getQ4().trim()+",";
			}
		
			////////////////////
			
			if(tests.get(0).getQ5() != null && tests.get(0).getQ5().trim().length() > 0) {
				ret += tests.get(0).getQ5().trim()+",";
			}
			
			if(tests.get(1).getQ5() != null && tests.get(1).getQ5().trim().length() > 0) {
				ret += tests.get(1).getQ5().trim()+",";
			}
			
			if(tests.get(2).getQ5() != null && tests.get(2).getQ5().trim().length() > 0) {
				ret += tests.get(2).getQ5().trim()+",";
			}
			
			if(tests.get(3).getQ5() != null && tests.get(3).getQ5().trim().length() > 0) {
				ret += tests.get(3).getQ5().trim()+",";
			}
			
			if(tests.get(4).getQ5() != null && tests.get(4).getQ5().trim().length() > 0) {
				ret += tests.get(4).getQ5().trim()+",";
			}
			
			if(ret.trim().length() == 0) {
				if(tests.get(0).getQ3() != null && tests.get(0).getQ3().trim().length() > 0) {
					ret += tests.get(0).getQ3().trim()+",";
				}
				
				if(tests.get(1).getQ3() != null && tests.get(1).getQ3().trim().length() > 0) {
					ret += tests.get(1).getQ3().trim()+",";
				}
				
				if(tests.get(2).getQ3() != null && tests.get(2).getQ3().trim().length() > 0) {
					ret += tests.get(2).getQ4().trim()+",";
				}
				
				if(tests.get(3).getQ3() != null && tests.get(3).getQ3().trim().length() > 0) {
					ret += tests.get(3).getQ3().trim()+",";
				}
				
				if(tests.get(4).getQ3() != null && tests.get(4).getQ3().trim().length() > 0) {
					ret += tests.get(4).getQ3().trim()+",";
				}
			}
			
			if(ret.trim().length() == 0) {
				if(tests.get(0).getQ2() != null && tests.get(0).getQ2().trim().length() > 0) {
					ret += tests.get(0).getQ2().trim()+",";
				}
				
				if(tests.get(1).getQ2() != null && tests.get(1).getQ2().trim().length() > 0) {
					ret += tests.get(1).getQ2().trim()+",";
				}
				
				if(tests.get(2).getQ2() != null && tests.get(2).getQ2().trim().length() > 0) {
					ret += tests.get(2).getQ2().trim()+",";
				}
				
				if(tests.get(3).getQ2() != null && tests.get(3).getQ2().trim().length() > 0) {
					ret += tests.get(3).getQ2().trim()+",";
				}
				
				if(tests.get(4).getQ2() != null && tests.get(4).getQ2().trim().length() > 0) {
					ret += tests.get(4).getQ2().trim()+",";
				}
			}
			
			if(ret.contains(",")) {
				ret = ret.substring(0, ret.lastIndexOf(","));
				return removeDuplicate(ret);
			}
			else {
				throw new AssessmentGenericException("No_Skill_Found");
			}
	}
	
	private String removeDuplicate(String str) {
		String[] strWords = str.split(",");
        
        //convert String array to LinkedHashSet to remove duplicates
        LinkedHashSet<String> lhSetWords 
            = new LinkedHashSet<String>( Arrays.asList(strWords) );
        
        //join the words again by space
        StringBuilder sbTemp = new StringBuilder();
        int index = 0;
        
        for(String s : lhSetWords){
            
            if(index > 0)
                sbTemp.append(",");
        
            sbTemp.append(s);
            index++;
        }
        
        str = sbTemp.toString();
        
      
        System.out.println(str);
        return str;
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
	
	protected NormalAssessmentDto createNormalAssessment(String companyId, String companyName, String q1, String q2, String q3, String q4, String q5, List<Question> questions, String alias){
			if(questions.size() < 3){
				throw new AssessmentGenericException("Less no of questions for assessment "+questions.size()+" "+q1+" "+q2+" "+q3+" "+(q4==null?"":q4)+" "+(q5==null?"":q5));
			}
		NormalAssessmentDto dto = new NormalAssessmentDto();
		if(q3 != null && q3.equalsIgnoreCase("null")){
			q3 = null;
		}
		
		if(q4 != null && q4.equalsIgnoreCase("null")){
			q4 = null;
		}

		if(q5 != null && q5.equalsIgnoreCase("null")){
			q5 = null;
		}
		String testName = "Assessment - "+q1+"-"+q2+(q3==null?"":"-"+q3)+(q4==null?"":"-"+q4)+(q5==null?"":"-"+q5)+" "+alias;
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
		test.setPassPercent(60f);//change
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
		section.setSectionName(q4);///change
		section.setPercentQuestionsAsked(100);
		section.setNoOfQuestions(questions.size());
		section.setSectionTimeInMinutes(30); //change
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
 		
		
		String url = drishtiGen.getUrlForUser("$EMAIL", test.getId(), companyId);
		url += "&inviteSent="+System.currentTimeMillis()+"&startDate="+sDate+"&endDate="+eDate+"&lang=eng";;
		dto.setTestLink(url);
		return dto;
	}
	
	
	protected NormalAdaptiveAssessmentDto createAdaptiveAssessment(List<NormalAssessmentDto> tests, String companyId, String companyName, String alias){
		NormalAdaptiveAssessmentDto dto = new NormalAdaptiveAssessmentDto();
		
		//String testName = "Assessment - "+q1+"-"+q2+"-"+q3+"-"+q4;
		AdaptiveAssessment adaptiveAssessment = new  AdaptiveAssessment();
		adaptiveAssessment.setCompanyId(companyId);
		adaptiveAssessment.setCompanyName(companyName);
		String name = "Adaptive Assessment - "+(System.currentTimeMillis()+" ");
		//name+= tests.get(0).getQ1()+"-"+tests.get(0).getQ2()+(tests.get(0).getQ3()==null?"":"-"+tests.get(0).getQ3())+(tests.get(0).getQ4() == null?"":"-"+tests.get(0).getQ4())+(tests.get(0).getQ5() == null?"":"-"+tests.get(0).getQ5()) + " "+alias;
		name += getAggregatedSkillNam(tests);
		System.out.println("adaptive ass name "+name);
		adaptiveAssessment.setAdaptiveAssessmentName(name);
		dto.setAdaptiveAssessmentName(name);
		dto.setTests(tests);
		
		
		String intro = "Adaptive TestingAdaptive assessment can be defined as any type of assessment that is tailored specifically to each examinee, based on their performance on previous items on the assessment. Most adaptive assessments are based on the theories and advances of Item Response Theory (IRT). ";
		adaptiveAssessment.setIntro(intro);
		dto.setIntro(intro);
		adaptiveAssessment.setCoreSkills(getAggregatedSkillNam(tests));
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
		int countLevel1 = (int)(tests.get(0).getNoOfQuestions()*(80/100.0f));
		level1.setNoOfQuestions(countLevel1);
		level1.setQualifier1(tests.get(0).getQ1());
		level1.setQualifier2(tests.get(0).getQ2());
		level1.setQualifier3(tests.get(0).getQ3());
		level1.setQualifier4(tests.get(0).getQ4());
		level1.setQualifier5(tests.get(0).getQ5());
		String skillName = (tests.get(0).getQ3() == null?tests.get(0).getQ2():tests.get(0).getQ3()) +(tests.get(0).getQ4() == null?"":" - "+tests.get(0).getQ4()) +(tests.get(0).getQ5() == null?"":" - "+tests.get(0).getQ5()) ;
		level1.setSkillName(skillName);
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
		int countLevel2 = (int)(tests.get(1).getNoOfQuestions()*(80/100.0f));
		level2.setNoOfQuestions(countLevel2);
		level2.setQualifier1(tests.get(1).getQ1());
		level2.setQualifier2(tests.get(1).getQ2());
		level2.setQualifier3(tests.get(1).getQ3());
		level2.setQualifier4(tests.get(1).getQ4());
		level2.setQualifier5(tests.get(1).getQ5());
		
		String skillName2 = (tests.get(1).getQ3() == null?tests.get(1).getQ2():tests.get(1).getQ3()) +(tests.get(1).getQ4() == null?"":" - "+tests.get(1).getQ4()) +(tests.get(1).getQ5() == null?"":" - "+tests.get(1).getQ5()) ;
		level2.setSkillName(skillName2);
		//level2.setSkillName(tests.get(1).getQ1() +" - "+tests.get(1).getQ2());
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
		int countLevel3 = (int)(tests.get(2).getNoOfQuestions()*(80/100.0f));
		level3.setNoOfQuestions(countLevel3);
		level3.setQualifier1(tests.get(2).getQ1());
		level3.setQualifier2(tests.get(2).getQ2());
		level3.setQualifier3(tests.get(2).getQ3());
		level3.setQualifier4(tests.get(2).getQ4());
		level3.setQualifier5(tests.get(2).getQ5());

		String skillName3 = (tests.get(2).getQ3() == null?tests.get(2).getQ2():tests.get(2).getQ3()) +(tests.get(2).getQ4() == null?"":" - "+tests.get(2).getQ4()) +(tests.get(2).getQ5() == null?"":" - "+tests.get(2).getQ5()) ;
		level3.setSkillName(skillName3);
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
		int countlevel4 = (int)(tests.get(3).getNoOfQuestions()*(80/100.0f));
		level4.setNoOfQuestions(countlevel4);
		level4.setQualifier1(tests.get(3).getQ1());
		level4.setQualifier2(tests.get(3).getQ2());
		level4.setQualifier3(tests.get(3).getQ3());
		level4.setQualifier4(tests.get(3).getQ4());
		level4.setQualifier5(tests.get(3).getQ5());
		String skillName4 = (tests.get(3).getQ3() == null?tests.get(3).getQ2():tests.get(3).getQ3()) +(tests.get(3).getQ4() == null?"":" - "+tests.get(3).getQ4()) +(tests.get(3).getQ5() == null?"":" - "+tests.get(3).getQ5()) ;
		level4.setSkillName(skillName4);
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
		int countlevel5 = (int)(tests.get(4).getNoOfQuestions()*(80/100.0f));
		level5.setNoOfQuestions(countlevel5);
		level5.setQualifier1(tests.get(4).getQ1());
		level5.setQualifier2(tests.get(4).getQ2());
		level5.setQualifier3(tests.get(4).getQ3());
		level5.setQualifier4(tests.get(4).getQ4());
		level5.setQualifier5(tests.get(4).getQ5());
		
		String skillName5 = (tests.get(4).getQ3() == null?tests.get(4).getQ2():tests.get(4).getQ3()) +(tests.get(4).getQ4() == null?"":" - "+tests.get(4).getQ4()) +(tests.get(4).getQ5() == null?"":" - "+tests.get(4).getQ5()) ;
		level5.setSkillName(skillName5);
		level5.setStartingLevel(AdaptiveAssessmentSkillLevel.LEVEL5);
		adaptiveAssessmentSkillService.saveOrUpdate(level5);
		
		String url = propertyConfig.getBaseUrl();
		url+= "adaptiveAssessment1?startDate=MTU4NjMzMDUyMDAwMA%3D%3D&endDate=MTc0NDA5NjkyMDAwMA%3D%3D&userId=$EMAIL&companyId="+companyId+"&testId="+adaptiveAssessment.getId()+"&lang=eng";;
		
		dto.setAdaptiveAssessmentLink(url);
		return dto;
	}
}
