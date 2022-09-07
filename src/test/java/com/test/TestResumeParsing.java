package com.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.assessment.services.ResumeParsingService;
import com.assessment.web.dto.resumeparsing.ResumeParsingResponse;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:appContext.xml"})
@Transactional
public class TestResumeParsing {
	@Autowired
	ResumeParsingService parser;
	
	@Test
	public void testPostResume(){
		
		ResumeParsingResponse res= parser.parseResume(null, null, null, null, null, null, null, null);
		System.out.println(res.getStatus());
	}
}
