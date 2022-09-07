package com.assessment.repositories;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.assessment.common.Qualifiers;
import com.assessment.data.QuestionMapper;

public interface QuestionMapperRepository extends JpaRepository<QuestionMapper,Long>{
	
	@Query("SELECT q FROM QuestionMapper q WHERE q.testName=:testName and q.sectionName=:sectionName and q.companyId=:companyId")
	public List<QuestionMapper> getQuestionsForSection(@Param("testName") String testName, @Param("sectionName") String sectionName, @Param("companyId") String companyId);
	
	@Query("SELECT COUNT(q) FROM QuestionMapper q WHERE q.sectionName=:sectionName and q.testName=:testName and q.companyId=:companyId")
	Integer findCountQuestionMapperForTestAndSection(@Param("sectionName") String sectionName, @Param("testName") String testName,   @Param("companyId") String companyId);
	
	List<QuestionMapper>  	findByQuestion_id(Long id);
	
	@Query("SELECT " +
	           "    new com.assessment.common.Qualifiers(q.qualifier1, q.qualifier2, q.qualifier3, q.qualifier4, q.qualifier5) " +
	           "FROM " +
	           "    Question q, QuestionMapper m WHERE q.companyId=:companyId and m.testName=:testName and q.id=m.question.id  GROUP BY q.qualifier1, q.qualifier2, q.qualifier3, q.qualifier4, q.qualifier5")
	public Set<Qualifiers> getAllUniqueQualifiersForTest(@Param("companyId")String companyId, @Param("testName")String testName);
	
	//select count(q2.id) from QuestionMapper q1, Question q2 where q1.testName = 'BulkUpdateTest' and q1.question_id = q2.id and q2.questionType = 'MCQ';
	@Query("SELECT COUNT(q1) FROM QuestionMapper q1 WHERE q1.testName=:testName and q1.companyId=:companyId and q1.question.questionType='MCQ'")
	Integer findCountOfMCQQsforTest(@Param("testName") String testName,   @Param("companyId") String companyId);
	
	@Query("SELECT COUNT(q1) FROM QuestionMapper q1 WHERE q1.testName=:testName and q1.companyId=:companyId and q1.question.questionType='CODING'")
	Integer findCountOfCodingQsforTest(@Param("testName") String testName,   @Param("companyId") String companyId);

	@Query("SELECT COUNT(q1) FROM QuestionMapper q1 WHERE q1.testName=:testName and q1.companyId=:companyId and (q1.question.questionType='FULL_STACK_JAVA'  OR q1.question.questionType='FULLSTACK')")
	Integer findCountOfFullstackQsforTest(@Param("testName") String testName,   @Param("companyId") String companyId);
	
	@Query("SELECT q FROM QuestionMapper q WHERE q.sectionName=:sectionName and q.testName=:testName and q.companyId=:companyId and q.question.id=:id")
	QuestionMapper findUniqueQuestionMapper(@Param("sectionName") String sectionName, @Param("testName") String testName, @Param("id") Long id,   @Param("companyId") String companyId);


}
