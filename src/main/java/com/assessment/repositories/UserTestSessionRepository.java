package com.assessment.repositories;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.assessment.data.UserTestSession;
import com.assessment.reports.manager.AssessmentTestData;
import com.assessment.web.dto.PopularAssessment;
import com.assessment.web.dto.PopularTestTaker;

public interface UserTestSessionRepository extends JpaRepository< UserTestSession,Long>{
	

	@Query("SELECT u FROM UserTestSession u WHERE u.user=:user and u.testName=:testName and u.companyId=:companyId")
	 UserTestSession findByPrimaryKey(@Param("user") String user, @Param("testName")  String testName, @Param("companyId") String companyId);
	
	@Query("SELECT " +
	           "    new com.assessment.reports.manager.AssessmentTestData(u.percentageMarksRecieved, u.testName, u.user, u.noOfAttempts, u.pass, u.sectionResults, u.companyId, u.testInviteSent, u.sharedDirect, u.sectionsNoOfQuestionsNotAnswered, u.createDate, u.updateDate, u.weightedScorePercentage) " +
	           "FROM " +
	           "    UserTestSession u where u.companyId=:companyId " +
	           "GROUP BY " +
	           "    u.testName, u.user ORDER BY u.createDate desc")
	List<AssessmentTestData> getAllResultsData(@Param("companyId") String companyId);
	
	@Query("SELECT u FROM UserTestSession u WHERE u.testName=:testName and u.companyId=:companyId ORDER BY u.createDate desc, u.percentageMarksRecieved desc")
	 List<UserTestSession> findUserSessionsForTest( @Param("testName")  String testName, @Param("companyId") String companyId);
	
	@Query("SELECT u FROM UserTestSession u WHERE u.user LIKE CONCAT(:user,'%') and u.testName=:testName and u.companyId=:companyId")
	 List<UserTestSession> findByTestNamePart(@Param("user") String user, @Param("testName")  String testName, @Param("companyId") String companyId);

	@Query("SELECT count(u) FROM UserTestSession u WHERE u.user LIKE CONCAT(:user,'%') and u.testName=:testName and u.companyId=:companyId")
	Integer getTestStatus(@Param("user") String user, @Param("testName")  String testName, @Param("companyId") String companyId);

	
	@Query("SELECT u FROM UserTestSession u WHERE u.companyId=:companyId GROUP BY u.testName")
	List<UserTestSession>findTestList(@Param("companyId") String companyId);
	
	@Query("SELECT " +
	           "    new com.assessment.reports.manager.AssessmentTestData(u.percentageMarksRecieved, u.testName, u.user, u.noOfAttempts, u.pass, u.sectionResults, u.companyId, u.testInviteSent, u.sharedDirect, u.sectionsNoOfQuestionsNotAnswered, u.createDate, u.updateDate, u.weightedScorePercentage) " +
	           "FROM " +
	           "    UserTestSession u where u.companyId=:companyId and u.testName=:testName " +
	           "GROUP BY " +
	           "    u.testName, u.user ORDER BY u.createDate desc")
	List<AssessmentTestData> getAllResultsDataForTest(@Param("testName") String testName, @Param("companyId") String companyId);

	@Query("SELECT DISTINCT u.testName FROM UserTestSession u WHERE u.companyId=:companyId and UPPER(u.user) like  :domain  and u.createDate >= :startDate and u.createDate <= :endDate ORDER BY u.testName")
	List<String> getUniqueTetsNamesForDomain(@Param("domain") String domain, @Param("companyId") String companyId, @Param("startDate") Date startDate, @Param("endDate") Date endDate);

	@Query("SELECT " +
	           "    new com.assessment.reports.manager.AssessmentTestData(u.percentageMarksRecieved, u.testName, u.user, u.noOfAttempts, u.pass, u.sectionResults, u.companyId, u.testInviteSent, u.sharedDirect, u.sectionsNoOfQuestionsNotAnswered, u.createDate, u.updateDate, u.weightedScorePercentage, u.noOfNonCompliances) " +
	           "FROM " +
	           "    UserTestSession u where u.companyId=:companyId and u.testName=:testName and UPPER(u.user) like :domain and u.createDate >= :startDate and u.createDate <= :endDate" +
	           " GROUP BY " +
	           "    u.testName, u.user ORDER BY u.createDate")
	List<AssessmentTestData> getDomainResultsDataForTest(@Param("testName") String testName, @Param("companyId") String companyId, @Param("domain") String domain, @Param("startDate") Date startDate, @Param("endDate") Date endDate);

	
	@Query("SELECT u FROM UserTestSession u WHERE u.companyId=:companyId and u.user like CONCAT(:user,'%') GROUP BY u.createDate")
	List<UserTestSession> findTestListForUser(@Param("companyId") String companyId, @Param("user") String user);

	@Query("SELECT u FROM UserTestSession u WHERE u.companyId=:companyId and u.createDate >= :start and u.createDate <= :end  ORDER BY u.createDate")
	List<UserTestSession> findResultsForDay(@Param("companyId") String companyId, @Param("start") Date start, @Param("end") Date end);

	
	@Query("SELECT u FROM UserTestSession u WHERE u.companyId=:companyId and u.subjective=true and  u.collegeName=:collegeName and u.grade=:grade and u.classifier=:classifier and (u.markComplete is null or u.markComplete = false) ORDER BY u.createDate")
	List<UserTestSession> findSubjectiveResultsNotMarkedAsCompleteForInstitution(@Param("companyId") String companyId, @Param("collegeName") String collegeName, @Param("grade") String grade, @Param("classifier") String classifier);


	@Query("SELECT u FROM UserTestSession u WHERE u.companyId=:companyId and u.subjective=true and  u.collegeName=:collegeName and u.grade=:grade and u.classifier=:classifier and (u.markComplete = true) ORDER BY u.createDate")
	List<UserTestSession> findSubjectiveResultsMarkedAsCompleteForInstitution(@Param("companyId") String companyId, @Param("collegeName") String collegeName, @Param("grade") String grade, @Param("classifier") String classifier);

	@Query("SELECT u.testName FROM UserTestSession u WHERE u.user=:user and u.companyId=:companyId group by u.testName ORDER BY u.createDate")
	List<String> findUniqueTestNamesForUser(@Param("user") String user, @Param("companyId") String companyId);
	
	@Query("SELECT  new com.assessment.web.dto.PopularAssessment(u.testName, count(u.testName)) FROM UserTestSession u WHERE u.companyId=:companyId group by u.testName ORDER BY (count(u.testName)) desc")
	List<PopularAssessment> findMaxTestNamesInTests( @Param("companyId") String companyId, Pageable pageable);
	
	@Query("SELECT  new com.assessment.web.dto.PopularTestTaker(u.user, count(u.user)) FROM UserTestSession u WHERE u.companyId=:companyId group by u.testName ORDER BY (count(u.user)) desc")
	List<PopularTestTaker> findMaxUsersInTests( @Param("companyId") String companyId, Pageable pageable);
	
	@Query(value="SELECT * FROM UserTestSession u WHERE u.companyId=:companyId ORDER by u.createDate DESC  LIMIT 10" , nativeQuery=true)
	List<UserTestSession> findTop10User(@Param("companyId") String companyId);

}
