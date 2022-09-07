package com.assessment.services;

import com.assessment.data.DemoPage;

public interface DemoPageService {
	
	DemoPage findByPrimaryKey(String pageName, String companyId);
	
	DemoPage saveOrUpdate(DemoPage demoPage);
	

}
