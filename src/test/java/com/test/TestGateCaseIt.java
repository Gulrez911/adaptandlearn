package com.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.assessment.web.controllers.GateController2;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:appContext.xml"})
@Transactional
public class TestGateCaseIt {
	
	@Autowired
	GateController2 gateController2;
	
	@Test
	@Rollback(value=false)
	public void testCreateQs() throws Exception{
		gateController2.generatedrishContent("IH", "IIHT", null, null);
	}

}
