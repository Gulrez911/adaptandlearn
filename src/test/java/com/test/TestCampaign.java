package com.test;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.assessment.data.Campaign;
import com.assessment.data.CampaignReviewer;
import com.assessment.data.CampaignTest;
import com.assessment.repositories.CampaignRepository;
import com.assessment.services.CampaignReviewerService;
import com.assessment.services.CampaignService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:appContext.xml"})
@Transactional
public class TestCampaign {
	
	@Autowired
	CampaignService campaignService;
	
	@Autowired
	CampaignReviewerService campaignReviewerService;
	
	@Autowired
	CampaignRepository campaignRep;
	
	/**
	 * Need to make sure reviewers are created in advance
	 */
	@Test
	@Rollback(value=false)
	public void testCreateCampaignReviewer(){
		CampaignReviewer campaignReviewer = new CampaignReviewer();
		campaignReviewer.setCompanyId("IH");
		campaignReviewer.setCompanyName("IIHT");
		campaignReviewer.setFirstName("ReviewerFirst1");
		campaignReviewer.setLastName("ReviewerLast1");
		campaignReviewer.setEmail("campaign@review1.com");
		campaignReviewerService.saveOrUpdate(campaignReviewer);
	}
	/**
	 * Need to make sure reviewers are created in advance
	 */
	@Test
	@Rollback(value=false)
	public void testCreateCampaign(){
		CampaignReviewer campaignReviewer = new CampaignReviewer();
		campaignReviewer.setCompanyId("IH");
		campaignReviewer.setCompanyName("IIHT");
		campaignReviewer.setFirstName("ReviewerFirst3");
		campaignReviewer.setLastName("ReviewerLast3");
		campaignReviewer.setEmail("campaign@review3.com");
		campaignReviewer.setPassword(""+campaignReviewer.getEmail().hashCode());
		
		
		CampaignReviewer campaignReviewer2 = new CampaignReviewer();
		campaignReviewer2.setCompanyId("IH");
		campaignReviewer2.setCompanyName("IIHT");
		campaignReviewer2.setFirstName("ReviewerFirst1");
		campaignReviewer2.setLastName("ReviewerLast1");
		campaignReviewer2.setEmail("campaign@review1.com");
		campaignReviewer2.setPassword(""+campaignReviewer.getEmail().hashCode());
		
		Campaign campaign = new Campaign();
		campaign.setActive(true);
		campaign.setCampaignName("Campign 1");
		campaign.setCompanyId("IH");
		campaign.setCompanyName("IIHT");
		
		CampaignTest campaignTest = new CampaignTest();
		campaignTest.setCompanyId("IH");
		campaignTest.setCompanyName("IIHT");
		campaignTest.setTestName("BulkUpdateTest");
		campaignTest.setTestName("CBA 1");//7Nov_Mcq_Coding
		campaignTest.setWeight(2);
		
		CampaignTest campaignTest2 = new CampaignTest();
		campaignTest2.setCompanyId("IH");
		campaignTest2.setCompanyName("IIHT");
		campaignTest2.setTestName("6Sep_2019");//Geography_Unit_Test
		campaignTest2.setWeight(2);
		
		campaign.getReviewers().add(campaignReviewer);
		//campaign.getReviewers().add(campaignReviewer2);
		campaign.getRounds().add(campaignTest);
		campaign.getRounds().add(campaignTest2);
		campaignService.saveOrUpdate(campaign);
		
	}
	
	@Test
	@Rollback(value=false)
	public void testCreateCampaign2(){
		CampaignReviewer campaignReviewer = new CampaignReviewer();
		campaignReviewer.setCompanyId("IH");
		campaignReviewer.setCompanyName("IIHT");
		campaignReviewer.setFirstName("ReviewerFirst1");
		campaignReviewer.setLastName("ReviewerLast1");
		campaignReviewer.setEmail("campaign@review1.com");
		campaignReviewer.setPassword(""+campaignReviewer.getEmail().hashCode());
		
		
		CampaignReviewer campaignReviewer2 = new CampaignReviewer();
		campaignReviewer2.setCompanyId("IH");
		campaignReviewer2.setCompanyName("IIHT");
		campaignReviewer2.setFirstName("ReviewerFirst2");
		campaignReviewer2.setLastName("ReviewerLast2");
		campaignReviewer2.setEmail("campaign@review2.com");
		campaignReviewer2.setPassword(""+campaignReviewer.getEmail().hashCode());
		
		Campaign campaign = new Campaign();
		campaign.setActive(true);
		campaign.setCampaignName("Campign 2");
		campaign.setCompanyId("IH");
		campaign.setCompanyName("IIHT");
		
		
		CampaignTest campaignTest = new CampaignTest();
		campaignTest.setCompanyId("IH");
		campaignTest.setCompanyName("IIHT");
		campaignTest.setTestName("Justification Test");//BulkUpdateTest
	//	campaignTest.setTestName("CBA 1");//7Nov_Mcq_Coding
		campaignTest.setWeight(2);
		
		CampaignTest campaignTest2 = new CampaignTest();
		campaignTest2.setCompanyId("IH");
		campaignTest2.setCompanyName("IIHT");
		campaignTest2.setTestName("Geography_Unit_Test");//Geography_Unit_Test
		campaignTest2.setWeight(2);
		
		campaign.getReviewers().add(campaignReviewer);
		campaign.getReviewers().add(campaignReviewer2);
		campaign.getRounds().add(campaignTest);
		campaign.getRounds().add(campaignTest2);
		campaignService.saveOrUpdate(campaign);
		
	}
	
	@Test
	public void testgetCampaignsByReviewerAndCompany(){
		List<Campaign> campaigns = campaignRep.getCampaignsByReviewerAndCompany("IH", "campaign@review1.com", PageRequest.of(0, 15)).getContent();
			for(Campaign cam : campaigns){
				System.out.println(cam.getCampaignName());
			}
	}

}
