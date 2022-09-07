package com.assessment.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.assessment.data.QuestionMapperInstance;
import com.assessment.web.dto.PopulaQuestion;

public interface QuestionMapperInstanceRepository extends JpaRepository<QuestionMapperInstance,Long>{
	
	@Query("SELECT q FROM QuestionMapperInstance q WHERE q.questionText=:questionText and q.testName=:testName and q.sectionName=:sectionName and q.user=:user and q.companyId=:companyId")
	QuestionMapperInstance findUniqueQuestionMapperInstanceForUser(@Param("questionText") String questionText, @Param("testName") String testName, @Param("sectionName") String sectionName, @Param("user") String user,@Param("companyId") String companyId);

	@Query("SELECT q FROM QuestionMapperInstance q WHERE q.questionText=:questionText and q.testName=:testName and q.sectionName=:sectionName and q.user=:user and q.companyId=:companyId")
	List<QuestionMapperInstance> findUniqueQuestionMapperInstanceForUserSet(@Param("questionText") String questionText, @Param("testName") String testName, @Param("sectionName") String sectionName, @Param("user") String user,@Param("companyId") String companyId);

	
	/**
	 * New add 11 apr
	 * @param questionText
	 * @param testName
	 * @param sectionName
	 * @param user
	 * @param companyId
	 * @return
	 */
	@Query("SELECT q FROM QuestionMapperInstance q WHERE q.questionText LIKE %:questionText% and q.testName=:testName and q.sectionName=:sectionName and q.user=:user and q.companyId=:companyId")
	List<QuestionMapperInstance> searcUniqueQuestionMapperInstanceForUserSet(@Param("questionText") String questionText, @Param("testName") String testName, @Param("sectionName") String sectionName, @Param("user") String user,@Param("companyId") String companyId);

	
	
	@Query("SELECT q FROM QuestionMapperInstance q WHERE q.questionText=:questionText and q.testName=:testName and q.sectionName=:sectionName and q.user=:user and q.companyId=:companyId")
	List<QuestionMapperInstance> findDuplicateQuestionMapperInstanceForUser(@Param("questionText") String questionText, @Param("testName") String testName, @Param("sectionName") String sectionName, @Param("user") String user,@Param("companyId") String companyId);

	
	
	@Query("SELECT q FROM QuestionMapperInstance q WHERE  q.testName=:testName and q.user=:user and q.companyId=:companyId GROUP BY q.questionMapper.id")
	List<QuestionMapperInstance> findQuestionMapperInstancesForUserForTest( @Param("testName") String testName,  @Param("user") String user, @Param("companyId") String companyId);
	
	@Query("SELECT q FROM QuestionMapperInstance q WHERE q.sectionName=:sectionName and q.testName=:testName and q.companyId=:companyId GROUP BY q.questionMapper.id")
	List<QuestionMapperInstance> findQuestionMapperInstancesForTestAndSection(@Param("sectionName") String sectionName, @Param("testName") String testName,   @Param("companyId") String companyId);

//	@Query("SELECT q FROM QuestionMapperInstance q WHERE  q.testName=:testName and q.user=:user and q.companyId=:companyId GROUP BY q.questionMapper.id")
//	List<QuestionMapperInstance> findUniqueQuestionMapperInstancesForUserForTest( @Param("testName") String testName,  @Param("user") String user, @Param("companyId") String companyId);
	
	@Query("SELECT q FROM QuestionMapperInstance q WHERE q.companyId=:companyId AND q.codeByUser IS NOT NULL GROUP BY q.questionMapper.id, q.user order by q.createDate desc")
	List<QuestionMapperInstance> findCodingQuestionMapperInstances(@Param("companyId") String companyId);
	
	
	@Query("SELECT q FROM QuestionMapperInstance q WHERE q.companyId=:companyId AND q.codeByUser IS NOT NULL GROUP BY q.questionMapper.id, q.user order by q.createDate desc")
	Page<QuestionMapperInstance> findCodingQuestionMapperInstances(@Param("companyId") String companyId, Pageable pageable);
	
	
	@Query("SELECT q FROM QuestionMapperInstance q WHERE q.companyId=:companyId AND q.workspaceUrl IS NOT NULL AND q.workSpaceId IS NOT NULL AND q.questionMapper.question.fullstack = 'JAVA_FULLSTACK' AND q.workspaceSubmitted = true GROUP BY q.questionMapper.id, q.user order by q.createDate desc")
	List<QuestionMapperInstance> findFullStackQuestionMapperInstancesForJava(@Param("companyId") String companyId);
	
	@Query("SELECT q FROM QuestionMapperInstance q WHERE q.companyId=:companyId AND q.workspaceUrl IS NOT NULL AND q.workSpaceId IS NOT NULL AND q.questionMapper.question.fullstack = 'DOTNET_FULLSTACK' AND q.workspaceSubmitted = true  GROUP BY q.questionMapper.id, q.user order by q.createDate desc")
	List<QuestionMapperInstance> findFullStackQuestionMapperInstancesForDotNet(@Param("companyId") String companyId);
	
	@Query("SELECT q FROM QuestionMapperInstance q WHERE q.companyId=:companyId AND q.workspaceUrl IS NOT NULL AND q.workSpaceId IS NOT NULL AND q.questionMapper.question.fullstack = 'JAVASCRIPT_FULLSTACK' AND q.workspaceSubmitted = true  GROUP BY q.questionMapper.id, q.user order by q.createDate desc")
	List<QuestionMapperInstance> findFullStackQuestionMapperInstancesForJavaScript(@Param("companyId") String companyId);
	
	@Query("SELECT q FROM QuestionMapperInstance q WHERE q.companyId=:companyId AND q.user=:user AND q.questionMapper.question.courseContext =:courseContext")
	List<QuestionMapperInstance> findQuestionMapperInstancesForUserForCourseContext(@Param("courseContext") String courseContext,  @Param("user") String user, @Param("companyId") String companyId);

	@Query("SELECT DISTINCT(q.user) FROM QuestionMapperInstance q WHERE q.companyId=:companyId AND q.user like %:user% AND q.questionMapper.question.courseContext =:courseContext")
	List<String> findUniqueUsersForCourseContext(@Param("courseContext") String courseContext,  @Param("user") String user, @Param("companyId") String companyId);

	@Query("SELECT DISTINCT(q.testName) FROM QuestionMapperInstance q WHERE q.companyId=:companyId AND q.user like %:user% AND q.questionMapper.question.courseContext =:courseContext")
	List<String> findUniqueTestsForCourseContext(@Param("courseContext") String courseContext,  @Param("user") String user, @Param("companyId") String companyId);

	@Query("SELECT DISTINCT(q.user) FROM QuestionMapperInstance q WHERE q.testName=:testName and  q.companyId=:companyId AND q.user like %:user% AND q.questionMapper.question.courseContext =:courseContext")
	List<String> findUniqueUsersForCourseContextAndTest(@Param("testName") String testName, @Param("courseContext") String courseContext,  @Param("user") String user, @Param("companyId") String companyId);

	
	@Query("SELECT q FROM QuestionMapperInstance q WHERE q.testName=:testName and q.companyId=:companyId AND q.user=:user AND q.questionMapper.question.courseContext =:courseContext")
	List<QuestionMapperInstance> findQuestionMapperInstancesForUserForCourseContextAndTest(@Param("testName") String testName, @Param("courseContext") String courseContext,  @Param("user") String user, @Param("companyId") String companyId);

	@Query("SELECT q FROM QuestionMapperInstance q WHERE q.companyId=:companyId AND q.workspaceUrl IS NOT NULL AND q.workSpaceId IS NOT NULL AND q.workspaceSubmitted = true  GROUP BY q.questionMapper.id, q.user order by q.createDate desc")
	List<QuestionMapperInstance> findAllFullStackQuestionMapperInstances(@Param("companyId") String companyId);
	
	
	@Query("SELECT q FROM QuestionMapperInstance q WHERE q.companyId=:companyId AND q.githubUrlOfUser IS NOT NULL AND q.problemInLocalFullstack = false AND q.workspaceSubmitted = true  GROUP BY q.questionMapper.id, q.user order by q.createDate desc")
	Page<QuestionMapperInstance> findAllFullStackLocalQuestionMapperInstances(@Param("companyId") String companyId,  Pageable pageable);
	
	@Query("SELECT q FROM QuestionMapperInstance q WHERE q.companyId=:companyId AND q.problemInLocalFullstack = true GROUP BY q.questionMapper.id, q.user order by q.createDate desc")
	Page<QuestionMapperInstance> findAllFullStackLocalQuestionMapperInstancesWithProblem(@Param("companyId") String companyId,  Pageable pageable);
	
	@Query("SELECT q.githubUrlOfUser FROM QuestionMapperInstance q WHERE q.companyId=:companyId AND q.githubUrlOfUser IS NOT NULL AND q.problemInLocalFullstack = false AND q.workspaceSubmitted = true GROUP BY q.githubUrlOfUser HAVING COUNT(q.id) > 1 order by q.createDate desc")
	List<String> findAllFullStackLocalQuestionMapperInstancesWithDuplicateRecords(@Param("companyId") String companyId);
	
	
	@Query("SELECT q FROM QuestionMapperInstance q WHERE q.companyId=:companyId AND q.githubUrlOfUser =:githubUrlOfUser  GROUP BY q.user order by q.createDate desc")
	List<QuestionMapperInstance> findLocalFullStackQuestionMapperInstancesWithGitUrl(@Param("companyId") String companyId, @Param("githubUrlOfUser") String githubUrlOfUser);
	
	
	@Query("SELECT q FROM QuestionMapperInstance q WHERE q.companyId=:companyId AND q.workspaceUrl IS NOT NULL AND q.workSpaceId IS NOT NULL AND q.workspaceSubmitted = true  GROUP BY q.questionMapper.id, q.user order by q.createDate desc")
	public Page<QuestionMapperInstance> findAllFullStackQuestionMapperInstances(@Param("companyId") String companyId,  Pageable pageable);
	
	
	
	@Query("SELECT count(q) FROM QuestionMapperInstance q WHERE  q.testName=:testName and q.user=:user and q.companyId=:companyId")
	Integer findQuestionMapperInstancesCountForUserForTest( @Param("testName") String testName,  @Param("user") String user, @Param("companyId") String companyId);

	
	@Query("SELECT q FROM QuestionMapperInstance q WHERE q.testName=:testName and q.companyId=:companyId AND q.user=:user AND q.subjective = true")
	List<QuestionMapperInstance> findSubjectQuestionMapperInstancesForUserAndTest(@Param("testName") String testName, @Param("user") String user, @Param("companyId") String companyId);

	
	@Query("SELECT count(q) FROM QuestionMapperInstance q WHERE q.testName=:testName and q.companyId=:companyId AND q.user=:user AND (q.answered = false OR q.answered is null)")
	Integer findUnAnsweredQuestionsForUserAndTest(@Param("testName") String testName, @Param("user") String user, @Param("companyId") String companyId);
	
	@Query("SELECT q.createDate FROM QuestionMapperInstance q WHERE  q.testName=:testName and q.user=:user and q.companyId=:companyId GROUP BY q.questionMapper.id")
	List<java.util.Date> findCreateDatesForTestAndUser(@Param("testName") String testName,  @Param("user") String user, @Param("companyId") String companyId);

	@Query("SELECT  new com.assessment.web.dto.PopulaQuestion(u.questionText, count(u.questionText)) FROM QuestionMapperInstance u WHERE u.companyId=:companyId group by u.questionText ORDER BY (count(u.questionText)) desc")
	List<PopulaQuestion> findMaxPopQuestionsInTests( @Param("companyId") String companyId, Pageable pageable);

	@Query("SELECT q FROM QuestionMapperInstance q WHERE q.companyId=:companyId AND q.workspaceUrl IS NOT NULL AND q.workSpaceId IS NOT NULL AND q.workspaceSubmitted = true AND (q.user LIKE %:searchText% OR q.testName LIKE %:searchText%) GROUP BY q.questionMapper.id, q.user order by q.createDate desc")
	public Page<QuestionMapperInstance> findFullStackQuestionMapperInstances(@Param("companyId") String companyId,   @Param("searchText")  String searchText,Pageable pageable);
	
	
	@Query("SELECT q FROM QuestionMapperInstance q WHERE q.companyId=:companyId AND q.codeByUser IS NOT NULL AND  ( q.testName LIKE %:searchText% OR q.questionText LIKE %:searchText%  OR q.user LIKE %:searchText%  ) GROUP BY q.questionMapper.id, q.user order by q.createDate desc")
	Page<QuestionMapperInstance> searchCodingQuestionMapperInstances(@Param("companyId") String companyId,@Param("searchText")  String searchText, Pageable pageable);
}

