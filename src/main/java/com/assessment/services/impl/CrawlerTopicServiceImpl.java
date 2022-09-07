package com.assessment.services.impl;

import java.util.Date;
import java.util.List;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.assessment.data.CrawlerTopic;
import com.assessment.repositories.CrawlerTopicRepository;
import com.assessment.services.CrawlerTopicService;

@Service
@Transactional
public class CrawlerTopicServiceImpl implements CrawlerTopicService {

	@Autowired
	CrawlerTopicRepository rep;
	
	@Override
	public CrawlerTopic findByPrimaryKey(String topicName, String qualifier1, String qualifier2, String companyId) {
		// TODO Auto-generated method stub
		return rep.findByPrimaryKey(topicName, qualifier1, qualifier2, companyId);
	}

	@Override
	public List<CrawlerTopic> searchCrawlerTopics(String searchLabel, String companyId) {
		// TODO Auto-generated method stub
		return rep.searchCrawlerTopics(searchLabel, companyId);
	}

	@Override
	public List<CrawlerTopic> getCrawlerTopics(String companyId) {
		// TODO Auto-generated method stub
		return rep.getCrawlerTopics(companyId);
	}

	@Override
	public CrawlerTopic saveOrUpdate(CrawlerTopic crawlerTopic) {
		// TODO Auto-generated method stub
		CrawlerTopic crawlerTopic2 = findByPrimaryKey(crawlerTopic.getTopicName(), crawlerTopic.getQualifier1(), crawlerTopic.getQualifier2(), crawlerTopic.getCompanyId());
			if(crawlerTopic2 == null){
				crawlerTopic.setCreateDate(new Date());
				return rep.save(crawlerTopic);
			}
			else{
				crawlerTopic.setId(crawlerTopic2.getId());
				crawlerTopic.setCreateDate(crawlerTopic2.getCreateDate());
				Mapper mapper = new DozerBeanMapper();
				mapper.map(crawlerTopic, crawlerTopic2);
				return rep.save(crawlerTopic2);
			}
	}

}
