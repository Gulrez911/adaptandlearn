package com.test;

import java.io.File;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.assessment.data.Question;
import com.assessment.services.QuestionService;
import com.assessment.web.controllers.ExamPunditController;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:appContext.xml"})
@Transactional
public class TestexamPunditController {

	@Autowired
	ExamPunditController controller;
	
	@Autowired
	QuestionService questionService;
	
	@Test
	@Rollback(value=false)
	public void testCreateQuestions() throws Exception{
		List<Question> qs = controller.extractQuestions(new File("examPundit"+File.separator+"MEDIEVAL__HISTORY.txt"));
		for (Question q : qs) {
			q.setCompanyId("AL");
			q.setCompanyName("AL");
			q.setQualifier1("Competitive Exams");
			q.setQualifier2("SSC");
			q.setQualifier3("CGL Exam");
			q.setQualifier4("2017");
			q.setQualifier5("MEDIEVAL HISTORY");
		
			q.setup();
			questionService.createQuestion(q);
		}
	}
	
	@Test
	@Rollback(value=false)
	public void testCreateExamPunditContent() throws Exception{
		controller.examPunditAssContent("AL", null, null);
	}
}
