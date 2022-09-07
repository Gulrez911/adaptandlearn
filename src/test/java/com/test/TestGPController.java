package com.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.assessment.web.controllers.GeneralPurposeController;
import com.assessment.web.controllers.GeneralPurposeController2;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:appContext.xml"})
@Transactional
public class TestGPController {

	@Autowired
	GeneralPurposeController generalPurposeController;
	
	@Autowired
	GeneralPurposeController2 generalPurposeController2;
	
	@Test
	public void testGetGPA() throws Exception{
		generalPurposeController.generateContentTillQual4("AL", "C:\\del\\eAss_Gul\\q4.csv", null, null);
	}
	
	
	@Test
	public void testGetGPA2() throws Exception{
		generalPurposeController2.generateContentTillQual5("AL", "C:\\del\\17Sep\\Gulrez_Gate_Data_Upto22Sep.csv", null, null);
	}
}
