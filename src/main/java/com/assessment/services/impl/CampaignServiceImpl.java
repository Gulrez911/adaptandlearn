package com.assessment.services.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.assessment.common.Qualifiers;
import com.assessment.data.Campaign;
import com.assessment.data.CampaignReviewer;
import com.assessment.data.CampaignTest;
import com.assessment.repositories.CampaignRepository;
import com.assessment.repositories.CampaignTestRepository;
import com.assessment.repositories.QuestionMapperRepository;
import com.assessment.services.CampaignReviewerService;
import com.assessment.services.CampaignService;
@Service
@Transactional
public class CampaignServiceImpl implements CampaignService{
	
	@Autowired
	CampaignRepository rep;
	
	@Autowired
	CampaignReviewerService campaignReviewerService;
	
	@Autowired
	QuestionMapperRepository questionMapperRep;
	
	@Autowired
	CampaignTestRepository campaignTestRep;

	@Override
	public Campaign findUniqueCampaign(String companyId, String campaignName) {
		// TODO Auto-generated method stub
		return rep.findUniqueCampaign(companyId, campaignName);
	}
	
	private CampaignReviewer resolveReviewer(CampaignReviewer reviewer){
		return campaignReviewerService.findByEmail(reviewer.getCompanyId(), reviewer.getEmail());
	}
	
	private List<CampaignReviewer> resolveReviewers(List<CampaignReviewer>  reviewers){
		List<CampaignReviewer> revs = new ArrayList<CampaignReviewer>();
		for(CampaignReviewer reviewer : reviewers){
			reviewer = resolveReviewer(reviewer);
			revs.add(reviewer);
		}
		return revs;
	}
	
	private Campaign resolveCampaignTests(Campaign campaign){
		for(CampaignTest campaignTest : campaign.getRounds()){
			Set<Qualifiers> qualifiers = questionMapperRep.getAllUniqueQualifiersForTest(campaign.getCompanyId(), campaignTest.getTestName());
			String skills = "";
				for(Qualifiers q : qualifiers){
					skills += q.toString()+",";
				}
				if(skills.contains(",")){
					skills = skills.substring(0, skills.lastIndexOf(","));
				}
				campaignTest.setTestSkills(skills);
		
			//CampaignTest test2 = campaignTestRep.findUniqueCampaignTest(campaign.getCompanyId(), campaignTest.getCampaignTestName());
			CampaignTest test2 = campaignTestRep.findUniqueCampaignTest(campaign.getCompanyId(),campaign.getCampaignName(), campaignTest.getCampaignTestName());
			if(test2 == null){
				campaignTest.setCompanyId(campaign.getCompanyId());
				campaignTest.setCompanyName(campaign.getCompanyName());
				campaignTestRep.save(campaignTest);
			}
		}
		return campaign;
	}

	@Override
	public Campaign saveOrUpdate(Campaign campaign) {
		// TODO Auto-generated method stub
		Campaign campaign2 = findUniqueCampaign(campaign.getCompanyId(), campaign.getCampaignName());
			if(campaign2 == null){
				List<CampaignReviewer> revs = resolveReviewers(campaign.getReviewers());
				campaign.setReviewers(revs);
				campaign = resolveCampaignTests(campaign);
				campaign.setCreateDate(new Date());
				rep.save(campaign);
				return campaign;
			}
			else{
				campaign2.setActive(campaign.getActive());
				campaign2.setCampaignDesc(campaign.getCampaignDesc());
				campaign2.getRounds().clear();
				campaign2.getRounds().addAll(campaign.getRounds());
			
				List<CampaignReviewer> revs = resolveReviewers(campaign.getReviewers());
				campaign2.setReviewers(revs);
				campaign2.setUpdateDate(new Date());
				campaign2 = resolveCampaignTests(campaign2);
				rep.save(campaign2);
				return campaign2;
			}
		
	}

	@Override
	public Page<Campaign> getCampaignsByCompany(String companyId, Pageable pageable) {
		// TODO Auto-generated method stub
		return rep.getCampaignsByCompany(companyId, pageable);
	}

	@Override
	public Page<Campaign> searchCampaigns(String companyId, String searchText, Pageable pageable) {
		// TODO Auto-generated method stub
		return rep.searchCampaigns(companyId, searchText, pageable);
	}

	@Override
	public Page<Campaign> getCampaignsByReviewerAndCompany(String companyId, String email, Pageable pageable) {
		// TODO Auto-generated method stub
		return rep.getCampaignsByReviewerAndCompany(companyId, email, pageable);
	}

	@Override
	public Page<Campaign> searchCampaignsByReviewerAndCompany(String companyId, String search, String email,
			Pageable pageable) {
		// TODO Auto-generated method stub
		return rep.searchCampaignsByReviewerAndCompany(companyId, search, email, pageable);
	}
	
	@Override
	public List<Campaign> findByPrimaryKey(String companyId) {
		// TODO Auto-generated method stub
		return rep.findByCompanyId(companyId);
	}
	
//	private void deleteCampaignTests(List<CampaignTest> campaignTests){
//		for(CampaignTest campaignTest : campaignTests){
//			campaignTest = campaignTestRep.findUniqueCampaignTest(campaignTest.getCompanyId(), campaignTest.getTestName());
//			if(campaignTest != null){
//				campaignTestRep.delete(campaignTest);
//			}
//		}
//	}

}
