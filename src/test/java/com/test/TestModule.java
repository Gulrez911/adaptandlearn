package com.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.assessment.data.Module;
import com.assessment.data.ModuleItem;
import com.assessment.services.ModuleService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:appContext.xml"})
@Transactional
public class TestModule {
	
	@Autowired
	ModuleService moduleService;
	
	@Test
	@Rollback(value=false)
	public void testCreateModule(){
		//8515
		Module module1 = new Module();
		module1.setModuleName("Module 1");
		module1.setCompanyId("IH");
		module1.setCompanyName("IIHT");
		//module1.setId(8515l);
		
		ModuleItem item1 = new ModuleItem();
		item1.setItemName("Item 1");
		item1.setCompanyId("IH");
		item1.setCompanyName("IIHT");
		module1.getItems().add(item1);
		
		ModuleItem item2 = new ModuleItem();
		item2.setItemName("Item 3");
		module1.getItems().add(item2);
		item2.setCompanyId("IH");
		item2.setCompanyName("IIHT");
		
		moduleService.saveOrUpdate(module1);
	}

}
