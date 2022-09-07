package com.assessment.services.impl;

import java.net.URLEncoder;
import java.util.Base64;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.assessment.common.PropertyConfig;
import com.assessment.data.Test;
import com.assessment.data.TestLinkTime;
import com.assessment.repositories.TestLinkTimeRepository;
import com.assessment.services.TestLinkTimeService;
import com.assessment.services.TestService;
@Service
@Transactional
public class TestLinkTimeServiceImpl implements TestLinkTimeService {

	@Autowired
	TestLinkTimeRepository testLinkRep;
	
	@Autowired
	PropertyConfig propertyConfig;
	
	@Autowired
	TestService testService;
	
	private String getPublicUrlForTest(Long testId, String companyId, String startTime, String endTime) {
		// String after = "&testId="+URLEncoder.encode(Base64.getEncoder().encodeToString(testId.toString().getBytes())+"&companyId="+URLEncoder.encode(Base64.getEncoder().encodeToString(companyId.getBytes())));
		Test test=testService.findTestById(testId);
		String after = "&testId="+URLEncoder.encode(testId.toString())+"&companyId="+URLEncoder.encode(companyId);
//			 String url = propertyConfig.getBaseUrl()+"mcqPublic?"+after+"&startTime="+startTime+"&endTime="+endTime+"&lang="+test.getTestLanguage()==null?"lang=eng":test.getTestLanguage();
//			 String url = propertyConfig.getBaseUrl()+"publicTest?"+after+"&startTime="+startTime+"&endTime="+endTime+"&lang=eng";
			 String url = propertyConfig.getBaseUrl()+"publicTestPro?"+after+"&startTime="+startTime+"&endTime="+endTime+"&lang=eng";

			 return url;
	  }
	
	private String getPublicUrlForTestWithWebProctorFlag(Long testId, String companyId, String startTime, String endTime) {
		// String after = "&testId="+URLEncoder.encode(Base64.getEncoder().encodeToString(testId.toString().getBytes())+"&companyId="+URLEncoder.encode(Base64.getEncoder().encodeToString(companyId.getBytes())));
		Test test=testService.findTestById(testId);
		String after = "&testId="+URLEncoder.encode(testId.toString())+"&companyId="+URLEncoder.encode(companyId);
			 String url = propertyConfig.getBaseUrl()+"mcqPublic?"+after+"&startTime="+startTime+"&endTime="+endTime+"&webProctorFlag=yes&lang="+test.getTestLanguage()+"&lang=eng";
			 return url;
	  }
	
	
	
	@Override
	public void saveOrUpdate(TestLinkTime testLinkTime) {
		// TODO Auto-generated method stub
		Long startTime = testLinkTime.getStartDate().getTime();
		Long endTime = testLinkTime.getEndDate().getTime();
		String encodedSTime = URLEncoder.encode(Base64.getEncoder().encodeToString((""+startTime).getBytes()));
		String encodedETime = URLEncoder.encode(Base64.getEncoder().encodeToString((""+endTime).getBytes()));
		
			if(testLinkTime.getWebProctored() != null && testLinkTime.getWebProctored() ==true){
				testLinkTime.setUrl(getPublicUrlForTestWithWebProctorFlag(testLinkTime.getTestId(), testLinkTime.getCompanyId(), encodedSTime, encodedETime));
			}
			else{
				testLinkTime.setUrl(getPublicUrlForTest(testLinkTime.getTestId(), testLinkTime.getCompanyId(), encodedSTime, encodedETime));
			}
		
		
	
		if(testLinkTime.getId() == null){
				testLinkTime.setCreateDate(new Date());
			testLinkRep.save(testLinkTime);
		}
		else{
			TestLinkTime testLinkTime2 = testLinkRep.findById(testLinkTime.getId()).get();
				if(testLinkTime.getWebProctored() != null && testLinkTime.getWebProctored() ==true){
					testLinkTime2.setUrl(getPublicUrlForTestWithWebProctorFlag(testLinkTime.getTestId(), testLinkTime.getCompanyId(), encodedSTime, encodedETime));
				}
				else{
					testLinkTime2.setUrl(getPublicUrlForTest(testLinkTime.getTestId(), testLinkTime.getCompanyId(), encodedSTime, encodedETime));
				}
			
			testLinkTime2.setUpdateDate(new Date());
			testLinkTime2.setTestName(testLinkTime.getTestName());
			testLinkTime2.setStartDate(testLinkTime.getStartDate());
			testLinkTime2.setEndDate(testLinkTime.getEndDate());
			testLinkTime2.setDontCheckTimeValidity(testLinkTime.getDontCheckTimeValidity());
			testLinkRep.save(testLinkTime);
		}
		
	}

	@Override
	public List<TestLinkTime> searchTestLinkTimes(String companyId, String testName) {
		// TODO Auto-generated method stub
		List<TestLinkTime> list = testLinkRep.searchTestLinkTimes(companyId, testName);
		return list;
	}

	@Override
	public void deleteTestLink(Long id) {
		// TODO Auto-generated method stub
		testLinkRep.deleteById(id);
	}

	@Override
	public Page<TestLinkTime> findAllLinks(Pageable pageable) {
		// TODO Auto-generated method stub
		return testLinkRep.findAllByOrderByIdDesc(pageable);
	}

	

}
