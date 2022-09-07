package com.test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.assessment.data.QuestionType;
import com.assessment.data.UserTestSession;
import com.assessment.repositories.QuestionMapperInstanceRepository;
import com.assessment.repositories.QuestionRepository;
import com.assessment.repositories.UserTestSessionRepository;
import com.assessment.web.dto.PopulaQuestion;
import com.assessment.web.dto.PopularAssessment;
import com.assessment.web.dto.PopularSkillBasedQuestion;
import com.assessment.web.dto.PopularTestTaker;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:appContext.xml"})
@Transactional
public class TestUserTestSession {
	@Autowired
	UserTestSessionRepository rep;
	
	@Autowired
	QuestionMapperInstanceRepository repQ;
	
	@Autowired
	QuestionRepository questionRep;
	
	@Test
	public void testGetTestNames() throws Exception{
		String start = "01-05-2020";
		String end = "28-05-2020";
		String format = "dd-MM-yyyy";
		Date startDate2=new SimpleDateFormat(format).parse(start);  
		Date endDate2=new SimpleDateFormat(format).parse(end);  
		Calendar c = Calendar.getInstance();
		c.setTime(endDate2);
		c.add(Calendar.DATE, 1);
		endDate2 = c.getTime();
		String domain = "may.com";
		List<String> testnames = rep.getUniqueTetsNamesForDomain("%"+domain.toUpperCase(), "IH", startDate2, endDate2);

//		Date start =  new SimpleDateFormat("yyyy-MM-dd").parse("2020-05-14");
//		
//		Date end  =  new SimpleDateFormat("yyyy-MM-dd").parse("2020-05-15");
//		List<String> testnames = rep.getUniqueTetsNamesForDomain("%MAY.COM", "IH", start ,end);
		System.out.println(testnames);
	}
	
	@Test
	public void testGetBulkResults() throws ParseException{
		 String pattern = "dd-MM-yyyy";
		 SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
		 Date dt = dateFormat.parse("15-06-2020");
		
			Calendar c = Calendar.getInstance();
			c.setTime(dt);
			c.add(Calendar.DATE, 1);
			Date endDate2 = c.getTime();
		 List<UserTestSession> sessions = rep.findResultsForDay("IH", dt, endDate2);
		 System.out.println(sessions.size());
	}
	
	@Test
	public void testGetMostPopularTests() throws ParseException{
		List<PopularAssessment> pop = rep.findMaxTestNamesInTests("IH", PageRequest.of(0, 10));
			for(PopularAssessment a : pop){
				 System.out.println(a.getTestName()+" "+a.getCount());
			}
		
	}
	
	@Test
	public void testGetMostPopularUsers() throws ParseException{
		List<PopularTestTaker> sessions = rep.findMaxUsersInTests("IH", PageRequest.of(0, 10));
			for(PopularTestTaker p : sessions){
				 System.out.println(p.getEmail() + " "+p.getCount());
			}
		
	}
	
	@Test
	public void testGetMostPopularQuestions() throws ParseException{
		List<PopulaQuestion> sessions = repQ.findMaxPopQuestionsInTests("IH", PageRequest.of(0, 10));
			for(PopulaQuestion p : sessions){
				 System.out.println(p.getQuestionText() + " "+p.getCount());
			}
		
	}
	
	@Test
	public void testGetMostSkillBasedContent() throws ParseException{
		List<PopularSkillBasedQuestion> sessions = questionRep.findPopSkillQuestionsInTests("IH", PageRequest.of(0, 10));
			for(PopularSkillBasedQuestion p : sessions){
				 System.out.println(p.getSkillCategory() + " "+p.getCount());
			}
		
	}
	
	@Test
	public void testGetCountQTypes(){
		System.out.println("MCQ "+questionRep.getQuestionCountByQuestionType("IH", QuestionType.MCQ));
		
		System.out.println("FIB "+questionRep.getQuestionCountByQuestionType("IH", QuestionType.FILL_BLANKS_MCQ));
		System.out.println("Image "+questionRep.getQuestionCountByQuestionType("IH", QuestionType.IMAGE_UPLOAD_BY_USER));
		
		System.out.println("MTF "+questionRep.getQuestionCountByQuestionType("IH", QuestionType.MATCH_FOLLOWING_MCQ));
		
		System.out.println("Coding "+questionRep.getQuestionCountByQuestionType("IH", QuestionType.CODING));
		
		System.out.println("Sub "+questionRep.getQuestionCountByQuestionType("IH", QuestionType.SUBJECTIVE_TEXT));
		
		System.out.println("Video "+questionRep.getQuestionCountByQuestionType("IH", QuestionType.VIDEO_UPLOAD_BY_USER));
		
		System.out.println("Fullstack "+questionRep.getFullStackQuestionCountByQuestionType("IH"));
	}

}
