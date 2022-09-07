package com.assessment.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.assessment.data.Course;
import com.assessment.data.CrawlerTopic;
import com.assessment.web.dto.CrawlerTopicDto;
import com.assessment.web.dto.PopularSkillBasedQuestion;

public interface CrawlerTopicRepository extends JpaRepository<CrawlerTopic,Long>
{
	
	@Query("SELECT c FROM CrawlerTopic c WHERE c.topicName=:topicName and c.qualifier1=:qualifier1 and c.qualifier2=:qualifier2 and c.companyId=:companyId")
	CrawlerTopic findByPrimaryKey(@Param("topicName") String topicName,@Param("qualifier1") String qualifier1, @Param("qualifier2") String qualifier2,  @Param("companyId") String companyId);
	
	@Query("SELECT c FROM CrawlerTopic c WHERE (c.topicName LIKE %:searchLabel% or c.qualifier1 LIKE %:searchLabel% or c.qualifier2  LIKE %:searchLabel% ) and  c.companyId=:companyId")
	List<CrawlerTopic> searchCrawlerTopics(@Param("searchLabel") String searchLabel, @Param("companyId") String companyId);
	
	
	@Query("SELECT c FROM CrawlerTopic c where c.companyId=:companyId order by c.createDate")
	List<CrawlerTopic> getCrawlerTopics(@Param("companyId") String companyId);
	
	@Query("SELECT c FROM CrawlerTopic c WHERE (c.topicName LIKE %:searchLabel% or c.qualifier1 LIKE %:searchLabel% or c.qualifier2  LIKE %:searchLabel% ) and  c.companyId=:companyId")
	Page<CrawlerTopic> searchCrawlerTopicsPage(@Param("searchLabel") String searchLabel, @Param("companyId") String companyId, Pageable pageable);
	
	
	@Query("SELECT c FROM CrawlerTopic c where c.companyId=:companyId order by c.createDate")
	Page<CrawlerTopic> getCrawlerTopicsPage(@Param("companyId") String companyId, Pageable pageable);
	
	@Query("SELECT count(c) FROM CrawlerTopic c where c.companyId=:companyId")
	Long getCrawlerTopicsSize(@Param("companyId") String companyId);
	
	@Query("SELECT distinct(c.topicName) FROM CrawlerTopic c where c.companyId=:companyId order by c.topicName")
	List<String> getDistinctTopicNames(@Param("companyId") String companyId);
	
	
	@Query("SELECT distinct(c.qualifier1) FROM CrawlerTopic c where c.companyId=:companyId and c.topicName=:topicName order by c.qualifier1")
	List<String> getDistinctQualifier1ForTopicName(@Param("companyId") String companyId, @Param("topicName") String topicName);
	
	
	@Query("SELECT c FROM CrawlerTopic c where c.companyId=:companyId and c.topicName=:topicName and c.qualifier1=:qualifier1 order by c.qualifier2")
	Page<CrawlerTopic> getCrawlerTopicsForTopicAndqualifier1(@Param("companyId") String companyId, @Param("topicName") String topicName, @Param("qualifier1") String qualifier1, Pageable pageable);

	@Query("SELECT c FROM CrawlerTopic c where c.companyId=:companyId and c.topicName=:topicName and c.qualifier1=:qualifier1 order by c.qualifier2")
	List<CrawlerTopic> getCrawlerTopicsForTopicAndqualifier1Data(@Param("companyId") String companyId, @Param("topicName") String topicName, @Param("qualifier1") String qualifier1);

	
	
	@Query("SELECT  new com.assessment.web.dto.CrawlerTopicDto(u.topicName, u.qualifier1) FROM CrawlerTopic u WHERE u.companyId=:companyId group by u.topicName, u.qualifier1 ORDER BY u.topicName, u.qualifier1")
	Page<CrawlerTopicDto> findUniqueTopicNameAndQualifier1Combinations( @Param("companyId") String companyId, Pageable pageable);

}