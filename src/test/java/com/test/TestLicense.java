package com.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.assessment.data.License;
import com.assessment.services.LicenseService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:appContext.xml"})
@Transactional
public class TestLicense {
	
	@Autowired
	LicenseService licenseService;
	
	@Test
	@Rollback(value=false)
	public void testCreateLicense(){
		License license = new License();
		license.setCompanyId("IH");
		license.setCompanyName("IIHT");
		license.setLicenseName("License 2	");
		license.setLicenseDesc("NA");
		licenseService.saveOrUpdate(license);
	}

}
