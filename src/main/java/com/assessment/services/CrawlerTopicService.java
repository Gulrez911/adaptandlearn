package com.assessment.services;

import java.util.List;

import com.assessment.data.CrawlerTopic;

public interface CrawlerTopicService {

	CrawlerTopic findByPrimaryKey(String topicName, String qualifier1,  String qualifier2,  String companyId);

	
	List<CrawlerTopic> searchCrawlerTopics(String searchLabel, String companyId);
	
	
	List<CrawlerTopic> getCrawlerTopics(String companyId);
	
	CrawlerTopic saveOrUpdate(CrawlerTopic crawlerTopic);
	
}
