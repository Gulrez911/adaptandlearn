package com.assessment.web.controllers;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.ListUtils;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.assessment.common.AssessmentGenericException;
import com.assessment.data.AssessmentUploadData;
import com.assessment.data.Company;
import com.assessment.data.CrawlerTopic;
import com.assessment.repositories.AssessmentUploadDataRepository;
import com.assessment.repositories.CrawlerTopicRepository;
import com.assessment.repositories.QuestionMapperRepository;
import com.assessment.repositories.QuestionRepository;
import com.assessment.services.AssessmentUploadDataService;
import com.assessment.services.CompanyService;
import com.assessment.services.CrawlerTopicService;
import com.assessment.services.SectionService;
import com.assessment.services.TestLinkTimeService;
import com.assessment.services.TestService;
import com.assessment.web.dto.AdaptiveAssessmentCreationDto;
import com.assessment.web.dto.CrawlerTopicDto;
import com.assessment.web.dto.TopicToAdaptiveAssessmentMapperDto;

@Controller
public class ConvertCrawlerTopicToAssessmentUploadRecordsController {
	
	@Autowired
	CrawlerTopicService crawlerTopicService;
	
	@Autowired
	CrawlerTopicRepository crawlerTopicRepository;
	
	@Autowired
	AssessmentUploadDataRepository assessmentUploadDataRepository;
	
	@Autowired
	AssessmentUploadDataService assessmentUploadDataService;
	
	@Autowired
	TestService testService;
	
	@Autowired
	SectionService sectionservice;
	
	@Autowired
	CompanyService companyService;
	
	
	String dummyAssessment1 = "Assessment - Language-Communications";
	
	String dummyAssessment2 = "Assessment - Logical Reasoning-Aptitude";
	
	String dummySkill11 = "Language";
	
	String dummySkill12 = "Communications";
	
	String dummySkill21 = "Logical Reasoning";
	
	String dummySkill22 = "Aptitude";
	
	@Autowired
	QuestionRepository questionRepository;
	
	@Autowired
	QuestionMapperRepository questionMapperRepository;
	
	@Autowired
	TestLinkTimeService testLinkTimeService;
	
	@Autowired
	AssessmentUploadDataController assessmentUploadDataController;
	
	@Autowired
	AssessmentUploadDataService dataService;
	
	String jobDescTemplate = "<p>This assessment will help you prepare on following topics -\r\n\r\n<li>${LEVEL1}  </li>\r\n<li>${LEVEL2}  </li>\r\n<li>${LEVEL3}  </li>\r\n<li>${LEVEL4}  </li>\r\n<li>${LEVEL5}  </li>\r\n\r\n</p>\r\n\r\n<p>This is an adaptive assessment. This will attempt to zero in to your exact level of ability on the given topic by - \r\n<li> By staring with questions of low difficulty or a same type and then asking harder or other type questions when you get it right and same or easier questions if you get them wrong.</li>\r\n<li>Your response to a set of Questions decide the next set of questions that may appear for you to answer</li>\r\n<li> If you are stuck at a specific level, do not worry & continue to proceed. The system may throw you course (video) recommendations on your 'weak' areas</li>\r\n<li>You can go through the course video on the specific topic and choose to reassess on the same to complete the Assess - Train - ReAssess journey. </li>\r\n\r\n</p>";
	
	@RequestMapping(value = "/initiateConversion", method = RequestMethod.GET)
	public @ResponseBody String initiateConversion(@RequestParam(name= "companyId", required = true) String companyId, @RequestParam(name= "noOfRecordsToProcess", required = false) Integer noOfRecordsToProcess, HttpServletResponse response, HttpServletRequest request) throws Exception {
	Long totalTopicsCount = crawlerTopicRepository.getCrawlerTopicsSize(companyId);
	//PageRequest.of(pageNumber, 30)
	
		if(noOfRecordsToProcess == null){
			noOfRecordsToProcess = 500;
		}
		
	Long noOfPartitionPagess = (totalTopicsCount / noOfRecordsToProcess) + 1;
		try {
			for(int i = 0 ; i < noOfPartitionPagess; i++){
				List<CrawlerTopicDto> combinations = crawlerTopicRepository.findUniqueTopicNameAndQualifier1Combinations(companyId, PageRequest.of(i, noOfPartitionPagess.intValue())).getContent();
				for(CrawlerTopicDto dto : combinations){
					List<CrawlerTopic> topics = crawlerTopicRepository.getCrawlerTopicsForTopicAndqualifier1Data(companyId, dto.getTopicName(), dto.getQualifier1());
					if(topics.size() >= 3){
						transformTopicsToAdaptiveAssessmentBucket(topics, dto.getTopicName(), dto.getQualifier1(), companyId);
					}
					else{
						//create normal assessments
						createNormalAssessments(topics, companyId);
					}
				}
			}
		} catch (AssessmentGenericException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return e.getMessage();
		}
		File file = new File("crawlerToAssessmentTranssComplete.txt");
		String message = "All records processed";
		FileUtils.write(file, message, true);
	return "ok";
	
	}
	
	private void createNormalAssessments(List<CrawlerTopic> topics, String companyId) throws Exception{
		Company company = companyService.findByCompanyId(companyId);
		for(CrawlerTopic topic : topics){
			Long count1 = questionRepository.getCountQuestionsByQualifier1_2(companyId, topic.getQualifier1(), topic.getQualifier2());
			if(count1 > 5){
				assessmentUploadDataController.createNormalAssessment(company.getCompanyName(), company.getCompanyId(), topic.getQualifier1(), topic.getQualifier2(), count1);
			}
			else{
				File file = new File("crawlerToAssessmentTranss.txt");
				String message = "Can not create even Normal Ass - Insufficient no Of Questions("+count1+") for Skill 1"+topic.getQualifier1()+"-"+topic.getQualifier2()+" and Topic Name "+topic.getTopicName()+"\n";
				FileUtils.write(file, message, true);
			}
			
		}
	}
	
	
	private int getDiffRandomNo(Random rand, int max, int min ,int earlierno, int counter){
		counter++;
		int randomIndex1 = rand.nextInt((max - min) ) + min;
			if(randomIndex1 == earlierno){
				if(counter >= 3){
					return -1;
				}
				return getDiffRandomNo(rand, max, randomIndex1, earlierno, counter);
			}
		return randomIndex1;
	}
	/**
	 * topicName and qualifier1 are came for all topics in the list passed.
	 * @param topics
	 * @param topicName
	 * @param qualifier1
	 * @throws Exception 
	 */
	private void transformTopicsToAdaptiveAssessmentBucket(List<CrawlerTopic> topics, String topicName, String qualifier1, String companyId) throws Exception{
		List<TopicToAdaptiveAssessmentMapperDto> buckets = new ArrayList<TopicToAdaptiveAssessmentMapperDto>();
		Integer bucketsSize = topics.size() / 5;
		if ( bucketsSize == 0){
			int def = 5 - topics.size();
			if(def == 2){
				CrawlerTopic topic1 = new CrawlerTopic();
				topic1.setTopicName("Language Skills");
				topic1.setQualifier1(dummySkill11);
				topic1.setQualifier2(dummySkill12);
				
				CrawlerTopic topic2 = new CrawlerTopic();
				topic2.setTopicName("Aptitude");
				topic2.setQualifier1(dummySkill21);
				topic2.setQualifier2(dummySkill22);
				topics.add(topic1);
				topics.add(topic2);
			}
			else if(def == 1){
				CrawlerTopic topic2 = new CrawlerTopic();
				topic2.setTopicName("Aptitude");
				topic2.setQualifier1(dummySkill21);
				topic2.setQualifier2(dummySkill22);
				topics.add(topic2);
			}
		}
		
		bucketsSize = topics.size() / 5;
		Integer remainder = topics.size() % 5;
		
			if(remainder == 4){
				CrawlerTopic topic1 = new CrawlerTopic();
				topic1.setTopicName("Language Skills");
				topic1.setQualifier1(dummySkill11);
				topic1.setQualifier2(dummySkill12);
				topics.add(topic1);
			}
			else if(remainder == 3){
				CrawlerTopic topic1 = new CrawlerTopic();
				topic1.setTopicName("Language Skills");
				topic1.setQualifier1(dummySkill11);
				topic1.setQualifier2(dummySkill12);
				topics.add(topic1);
				
				CrawlerTopic topic2 = new CrawlerTopic();
				topic2.setTopicName("Aptitude");
				topic2.setQualifier1(dummySkill21);
				topic2.setQualifier2(dummySkill22);
				topics.add(topic2);
			}
			else if(remainder == 2){
				int max = (topics.size() - remainder) -1; //remember index starts from 0
				int min = 0;
				Random rand = new Random();
				int randomIndex1 = rand.nextInt((max - min) ) + min;
				CrawlerTopic randomTopic = topics.get(randomIndex1);
				CrawlerTopic randomTopicCopy = new CrawlerTopic();
				randomTopicCopy.setTopicName(randomTopic.getTopicName());
				randomTopicCopy.setQualifier1(randomTopic.getQualifier1());
				randomTopicCopy.setQualifier2(randomTopic.getQualifier2());
				topics.add(randomTopicCopy);
				
				CrawlerTopic topic1 = new CrawlerTopic();
				topic1.setTopicName("Language Skills");
				topic1.setQualifier1(dummySkill11);
				topic1.setQualifier2(dummySkill12);
				topics.add(topic1);
				
				CrawlerTopic topic2 = new CrawlerTopic();
				topic2.setTopicName("Aptitude");
				topic2.setQualifier1(dummySkill21);
				topic2.setQualifier2(dummySkill22);
				topics.add(topic2);
			}
			else if(remainder == 1){
				int max = (topics.size() - remainder) -1; //remember index starts from 0
				int min = 0;
				Random rand = new Random();
				int randomIndex1 = rand.nextInt((max - min) ) + min;
				CrawlerTopic randomTopic = topics.get(randomIndex1);
				CrawlerTopic randomTopicCopy = new CrawlerTopic();
				randomTopicCopy.setTopicName(randomTopic.getTopicName());
				randomTopicCopy.setQualifier1(randomTopic.getQualifier1());
				randomTopicCopy.setQualifier2(randomTopic.getQualifier2());
				topics.add(randomTopicCopy);
				
				
				int randomIndex2 = getDiffRandomNo(rand, max, min, randomIndex1, 0);
					if(randomIndex2 == -1){
						File file = new File("crawlerToAssessmentTranss.txt");
						String message = "Problem in random no gen"+topicName+" q1 "+qualifier1+" topics size "+topics.size()+" max "+max+"\n";
						FileUtils.write(file, message, true);
						return;
					}
				CrawlerTopic randomTopic2 = topics.get(randomIndex2);
				CrawlerTopic randomTopicCopy2 = new CrawlerTopic();
				randomTopicCopy2.setTopicName(randomTopic2.getTopicName());
				randomTopicCopy2.setQualifier1(randomTopic2.getQualifier1());
				randomTopicCopy2.setQualifier2(randomTopic2.getQualifier2());
				topics.add(randomTopicCopy2);
				
				CrawlerTopic topic1 = new CrawlerTopic();
				topic1.setTopicName("Language Skills");
				topic1.setQualifier1(dummySkill11);
				topic1.setQualifier2(dummySkill12);
				topics.add(topic1);
				
				CrawlerTopic topic2 = new CrawlerTopic();
				topic2.setTopicName("Aptitude");
				topic2.setQualifier1(dummySkill21);
				topic2.setQualifier2(dummySkill22);
				topics.add(topic2);
				
				
			}
		
			
			//now we get to a place where we have topics in multiples of 5
			remainder = topics.size() % 5;
			if(remainder != 0){
				throw new AssessmentGenericException("Wrong algorithm");
			}
			
			//now begin process of converting to AssessmentuploadRecord.
			bucketsSize = topics.size() / 5;
			List<List<CrawlerTopic>> partitions = org.apache.commons.collections4.ListUtils.partition(topics, 5);
			for(List<CrawlerTopic> partition : partitions){
				AssessmentUploadData assessmentUploadData = createRecord(partition,  topicName, qualifier1, companyId);
					if(assessmentUploadData != null){
						File file = new File("crawlerToAssessmentTranss_Processed.txt");
						String message = assessmentUploadData.getJobDescName()+" processed \n";
						FileUtils.write(file, message, true);
					}
			}
	}
	
	
	
	private AssessmentUploadData createRecord(List<CrawlerTopic> partition, String topicName, String qualifier1, String companyId) throws Exception{
			if(partition.size() != 5){
				throw new AssessmentGenericException("Wrong partition size");
			}
			
			topicName = topicName.replace(getNumbers(topicName), "").trim();
			topicName = topicName.replaceAll("(?i)mcqs", "").trim();
			
			String skill1 = partition.get(0).getQualifier1();
			String skill12 = partition.get(0).getQualifier2();
			String skill2 = partition.get(1).getQualifier1();
			String skill22 = partition.get(1).getQualifier2();
			
			String skill3 = partition.get(2).getQualifier1();
			String skill32 = partition.get(2).getQualifier2();
			
			String skill4 = partition.get(3).getQualifier1();
			String skill42 = partition.get(3).getQualifier2();
			
			String skill5 = partition.get(4).getQualifier1();
			String skill52 = partition.get(4).getQualifier2();
			
			String jobdescName = topicName+" - "+qualifier1+": ("+skill12+", "+skill22+", "+skill32+", "+skill42+", "+skill52+")";
			String str = FileUtils.readFileToString(new File("crawlerToAssessmentTranss_Processed.txt"));
				if(str.contains(jobdescName)){
					//no need to go further
					FileUtils.write(new File("crawlerToAssessmentDeliberateNotProcessing.txt"), "Deliberately not processing "+jobdescName+" \n");
					return null;
				}
			
			Long count1 = questionRepository.getCountQuestionsByQualifier1_2(companyId, skill1, skill12);
			Long count2 = questionRepository.getCountQuestionsByQualifier1_2(companyId, skill2, skill22);
			Long count3 = questionRepository.getCountQuestionsByQualifier1_2(companyId, skill3, skill32);
			Long count4 = questionRepository.getCountQuestionsByQualifier1_2(companyId, skill4, skill42);
			Long count5 = questionRepository.getCountQuestionsByQualifier1_2(companyId, skill5, skill52);
				if(count1 <= 5){
					File file = new File("crawlerToAssessmentTranss.txt");
					String message = "Insufficient no Of Questions("+count1+") for Skill 1"+skill1+"-"+skill12+" and Topic Name "+topicName+" and qualifier1 "+qualifier1+"\n";
					FileUtils.write(file, message, true);
					return null;
				}
				
				if(count2 <= 5){
					File file = new File("crawlerToAssessmentTranss.txt");
					String message = "Insufficient no Of Questions("+count2+") for Skill 2"+skill2+"-"+skill22+" and Topic Name "+topicName+" and qualifier1 "+qualifier1+"\n";
					FileUtils.write(file, message, true);
					return null;
				}
				
				if(count3 <= 5){
					File file = new File("crawlerToAssessmentTranss.txt");
					String message = "Insufficient no Of Questions("+count3+") for Skill 3"+skill3+"-"+skill32+" and Topic Name "+topicName+" and qualifier1 "+qualifier1+"\n";
					FileUtils.write(file, message, true);
					return null;
				}
				
				if(count4 <= 5){
					File file = new File("crawlerToAssessmentTranss.txt");
					String message = "Insufficient no Of Questions("+count4+") for Skill 4"+skill4+"-"+skill42+" and Topic Name "+topicName+" and qualifier1 "+qualifier1+"\n";
					FileUtils.write(file, message, true);
					return null;
				}
				
				if(count5 <= 5){
					File file = new File("crawlerToAssessmentTranss.txt");
					String message = "Insufficient no Of Questions("+count5+") for Skill 5"+skill5+"-"+skill52+" and Topic Name "+topicName+" and qualifier1 "+qualifier1+"\n";
					FileUtils.write(file, message, true);
					return null;
				}
				
				Company company = companyService.findByCompanyId(companyId);
				
				String testLink1 = assessmentUploadDataController.createNormalAssessment(company.getCompanyName(), company.getCompanyId(), skill1, skill12, count1);
				String testLink2 = assessmentUploadDataController.createNormalAssessment(company.getCompanyName(), company.getCompanyId(), skill2, skill22, count2);
				String testLink3 = assessmentUploadDataController.createNormalAssessment(company.getCompanyName(), company.getCompanyId(), skill3, skill32, count3);
				String testLink4 = assessmentUploadDataController.createNormalAssessment(company.getCompanyName(), company.getCompanyId(), skill4, skill42, count4);
				String testLink5 = assessmentUploadDataController.createNormalAssessment(company.getCompanyName(), company.getCompanyId(), skill5, skill52, count5);
				
				String campaignLink1 = assessmentUploadDataController.createCampaign(company.getCompanyName(), company.getCompanyId(), skill1, skill12, skill2, skill22, skill3, skill32, skill4, skill42, skill5, skill52);
				
				AdaptiveAssessmentCreationDto dto = new AdaptiveAssessmentCreationDto();
				dto.setAssessmentLink1(testLink1);
				dto.setAssessmentLink2(testLink2);
				dto.setAssessmentLink3(testLink3);
				dto.setAssessmentLink4(testLink4);
				dto.setAssessmentLink5(testLink5);
				
				dto.setCompanyId(company.getCompanyId());
				dto.setCompanyName(company.getCompanyName());
				dto.setCountAss1(count1.intValue() > 30?30:count1.intValue());
				dto.setCountAss2(count2.intValue() > 30?30:count2.intValue());
				dto.setCountAss3(count3.intValue() > 30?30:count3.intValue());
				dto.setCountAss4(count4.intValue() > 30?30:count4.intValue());
				dto.setCountAss5(count5.intValue() > 30?30:count5.intValue());
				
				String jobdesc = jobDescTemplate;
				jobdesc = jobDescTemplate.replace("${LEVEL1}", skill1+" - "+skill12);
				jobdesc = jobdesc.replace("${LEVEL2}", skill2+" - "+skill22);
				jobdesc = jobdesc.replace("${LEVEL3}", skill3+" - "+skill32);
				jobdesc = jobdesc.replace("${LEVEL4}", skill4+" - "+skill42);
				jobdesc = jobdesc.replace("${LEVEL5}", skill5+" - "+skill52);
				
				dto.setJobDesc(jobdesc);
				//String jobdescName = topicName+" - "+qualifier1+": ("+skill12+", "+skill22+", "+skill32+", "+skill42+", "+skill52+")";
				dto.setJobdescName(jobdescName);
				dto.setSkill1(skill1);
				dto.setSkill2(skill2);
				dto.setSkill3(skill3);
				dto.setSkill4(skill4);
				dto.setSkill4(skill4);
				dto.setSkill5(skill5);
				
				dto.setSkill12(skill12);
				dto.setSkill22(skill22);
				dto.setSkill32(skill32);
				dto.setSkill42(skill42);
				dto.setSkill52(skill52);
				
				String adaptiveAssessmentLink = assessmentUploadDataController.createAdaptiveAssessment(dto);
				AssessmentUploadData data = new AssessmentUploadData();
				data.setJobdesc(jobdesc);
				data.setJobDescName(jobdescName);
				String name = "Adaptive Assessment - "+dto.getJobdescName();
				data.setAdaptiveAssessmentName(name);
				data.setAdaptiveAssessmentPublicLink(adaptiveAssessmentLink);
				String testName1 = "Assessment - "+skill1+"-"+skill12;
				data.setAssessmentName1(testName1);
				
				String testName2 = "Assessment - "+skill2+"-"+skill22;
				data.setAssessmentName2(testName2);
				
				String testName3 = "Assessment - "+skill3+"-"+skill32;
				data.setAssessmentName3(testName3);
				
				String testName4 = "Assessment - "+skill4+"-"+skill42;
				data.setAssessmentName4(testName4);
				
				String testName5 = "Assessment - "+skill5+"-"+skill52;
				data.setAssessmentName5(testName5);
				
				data.setAssessmentPublicLink1(testLink1);
				data.setAssessmentPublicLink2(testLink2);
				data.setAssessmentPublicLink3(testLink3);
				data.setAssessmentPublicLink4(testLink4);
				data.setAssessmentPublicLink5(testLink5);
				//String campaignName = "Campaign for - "+skill1+", "+skill2+", "+skill3+", "+skill4+", "+skill5+" skills";
				String campaignName = "Campaign for - "+skill1+"-"+skill12+", "+skill22+", "+skill32+", "+skill42+", "+skill52+" skills";
				data.setCampaignName(campaignName);
				data.setCampaignTestLink(campaignLink1);
				data.setCompanyId(company.getCompanyId());
				data.setCompanyName(company.getCompanyName());
				data.setDomain("WebCrawl_1");
				
				dataService.saveOrUpdate(data);
		return data;
	}
	
	public static String getNumbers(String s) {

	    String[] n = s.split(""); //array of strings
	    StringBuffer f = new StringBuffer(); // buffer to store numbers

	    for (int i = 0; i < n.length; i++) {
	        if((n[i].matches("[0-9]+"))) {// validating numbers
	            f.append(n[i]); //appending
	        }else {
	            //parsing to int and returning value
	            return f.toString();
	        }   
	    }
	    return "";
	 }

}
