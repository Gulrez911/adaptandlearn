package com.assessment.repositories;

import java.util.Date;
import java.util.List;
import java.util.Set;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.assessment.common.Qualifiers;
import com.assessment.data.DifficultyLevel;
import com.assessment.data.ProgrammingLanguage;
import com.assessment.data.Question;
import com.assessment.data.QuestionType;
import com.assessment.web.dto.PopularSkillBasedQuestion;

public interface QuestionRepository extends JpaRepository<Question, Long> {
	@Query("SELECT q FROM Question q WHERE q.companyId=:companyId AND fullstack != 'NONE'")
	public List<Question> findFullStackQuestionsByCompanyId(@Param("companyId") String companyId);

	@Query("SELECT q FROM Question q WHERE q.companyId=:companyId")
	public List<Question> findQuestionsByCompanyId(@Param("companyId") String companyId);

	@Query("SELECT q FROM Question q WHERE q.companyId=:companyId")
	public Page<Question> findQuestionsByCompanyId(@Param("companyId") String companyId, Pageable pageable);

	@Query("SELECT q FROM Question q WHERE q.questionText=:questionText and q.companyId=:companyId")
	Question findByPrimaryKey(@Param("questionText") String questionText, @Param("companyId") String companyId);

	@Query("SELECT q FROM Question q WHERE q.questionText=:questionText and q.companyId=:companyId and q.qualifier1=:qualifier1 and q.qualifier2=:qualifier2 and q.qualifier3=:qualifier3 and q.qualifier4=:qualifier4 and q.qualifier5=:qualifier5")
	Question findByPrimaryKeyAndAllQualifiers(@Param("questionText") String questionText,
			@Param("companyId") String companyId, @Param("qualifier1") String qualifier1,
			@Param("qualifier2") String qualifier2, @Param("qualifier3") String qualifier3,
			@Param("qualifier4") String qualifier4, @Param("qualifier5") String qualifier5);

	@Query("SELECT q FROM Question q WHERE q.questionText=:questionText and q.companyId=:companyId and q.qualifier1=:qualifier1 and q.qualifier2=:qualifier2 and q.qualifier3=:qualifier3 and q.qualifier4=:qualifier4")
	Question findByPrimaryKeyTillQualifier4(@Param("questionText") String questionText,
			@Param("companyId") String companyId, @Param("qualifier1") String qualifier1,
			@Param("qualifier2") String qualifier2, @Param("qualifier3") String qualifier3,
			@Param("qualifier4") String qualifier4);

	@Query("SELECT q FROM Question q WHERE q.questionText=:questionText and q.companyId=:companyId and q.qualifier1=:qualifier1 and q.qualifier2=:qualifier2 and q.qualifier3=:qualifier3")
	Question findByPrimaryKeyTillQualifier3(@Param("questionText") String questionText,
			@Param("companyId") String companyId, @Param("qualifier1") String qualifier1,
			@Param("qualifier2") String qualifier2, @Param("qualifier3") String qualifier3);

	@Query("SELECT q FROM Question q WHERE q.questionText=:questionText and q.companyId=:companyId and q.qualifier1=:qualifier1 and q.qualifier2=:qualifier2")
	Question findByPrimaryKeyTillQualifier2(@Param("questionText") String questionText,
			@Param("companyId") String companyId, @Param("qualifier1") String qualifier1,
			@Param("qualifier2") String qualifier2);

	@Query("SELECT q FROM Question q WHERE q.questionText=:questionText and q.companyId=:companyId and q.qualifier1=:qualifier1")
	Question findByPrimaryKeyTillQualifier1(@Param("questionText") String questionText,
			@Param("companyId") String companyId, @Param("qualifier1") String qualifier1);

	@Query(value = "SELECT q FROM Question q WHERE q.qualifier1=:qualifier1 and q.companyId=:companyId", countQuery = "SELECT COUNT(*) FROM Question q WHERE q.qualifier1=:qualifier1 and q.companyId=:companyId")
	public Page<Question> findQuestionsByQualifier1(@Param("companyId") String companyId,
			@Param("qualifier1") String qualifier1, Pageable pageable);

	@Query(value = "SELECT q FROM Question q WHERE q.qualifier1=:qualifier1 and q.companyId=:companyId")
	public List<Question> findQuestionsByQualifier1(@Param("companyId") String companyId,
			@Param("qualifier1") String qualifier1);

	@Query(value = "SELECT q FROM Question q WHERE q.qualifier1=:qualifier1 and q.qualifier2=:qualifier2 and q.companyId=:companyId", countQuery = "SELECT COUNT(*) FROM Question q WHERE q.qualifier1=:qualifier1 and q.qualifier2=:qualifier2 and q.companyId=:companyId")
	public Page<Question> findQuestionsByQualifier2(@Param("companyId") String companyId,
			@Param("qualifier1") String qualifier1, @Param("qualifier2") String qualifier2, Pageable pageable);

	@Query(value = "SELECT q FROM Question q WHERE q.qualifier1=:qualifier1 and q.qualifier2=:qualifier2 and q.companyId=:companyId")
	public List<Question> findQuestionsByQualifier2(@Param("companyId") String companyId,
			@Param("qualifier1") String qualifier1, @Param("qualifier2") String qualifier2);

	@Query(value = "SELECT count(q) FROM Question q WHERE q.qualifier1=:qualifier1 and q.qualifier2=:qualifier2 and q.companyId=:companyId")
	public Integer findQuestionsCountByQualifier2(@Param("companyId") String companyId,
			@Param("qualifier1") String qualifier1, @Param("qualifier2") String qualifier2);

	@Query(value = "SELECT q FROM Question q WHERE q.qualifier1=:qualifier1 and q.qualifier2=:qualifier2 and q.qualifier3=:qualifier3 and q.companyId=:companyId", countQuery = "SELECT COUNT(*) FROM Question q WHERE q.qualifier1=:qualifier1 and q.qualifier2=:qualifier2 and q.qualifier3=:qualifier3 and q.companyId=:companyId")
	public Page<Question> findQuestionsByQualifier3(@Param("companyId") String companyId,
			@Param("qualifier1") String qualifier1, @Param("qualifier2") String qualifier2,
			@Param("qualifier3") String qualifier3, Pageable pageable);

	@Query(value = "SELECT q FROM Question q WHERE q.qualifier1=:qualifier1 and q.qualifier2=:qualifier2 and q.qualifier3=:qualifier3 and q.companyId=:companyId")
	public List<Question> findQuestionsByQualifier3(@Param("companyId") String companyId,
			@Param("qualifier1") String qualifier1, @Param("qualifier2") String qualifier2,
			@Param("qualifier3") String qualifier3);

	@Query(value = "SELECT q FROM Question q WHERE q.qualifier1=:qualifier1 and q.qualifier2=:qualifier2 and q.qualifier3=:qualifier3 and q.qualifier4=:qualifier4 and q.companyId=:companyId", countQuery = "SELECT COUNT(*) FROM Question q WHERE q.qualifier1=:qualifier1 and q.qualifier2=:qualifier2 and q.qualifier3=:qualifier3 and q.qualifier4=:qualifier4 and q.companyId=:companyId")
	public Page<Question> findQuestionsByQualifier4(@Param("companyId") String companyId,
			@Param("qualifier1") String qualifier1, @Param("qualifier2") String qualifier2,
			@Param("qualifier3") String qualifier3, @Param("qualifier4") String qualifier4, Pageable pageable);

	@Query(value = "SELECT q FROM Question q WHERE q.qualifier1=:qualifier1 and q.qualifier2=:qualifier2 and q.qualifier3=:qualifier3 and q.qualifier4=:qualifier4 and q.companyId=:companyId")
	public List<Question> findQuestionsByQualifier4(@Param("companyId") String companyId,
			@Param("qualifier1") String qualifier1, @Param("qualifier2") String qualifier2,
			@Param("qualifier3") String qualifier3, @Param("qualifier4") String qualifier4);

	@Query(value = "SELECT q FROM Question q WHERE q.qualifier1=:qualifier1 and q.qualifier2=:qualifier2 and q.qualifier3=:qualifier3 and q.qualifier4=:qualifier4 and q.qualifier5=:qualifier5 and q.companyId=:companyId", countQuery = "SELECT COUNT(*) FROM Question q WHERE q.qualifier1=:qualifier1 and q.qualifier2=:qualifier2 and q.qualifier3=:qualifier3 and q.qualifier4=:qualifier4 and q.qualifier5=:qualifier5 and q.companyId=:companyId")
	public Page<Question> findQuestionsByQualifier5(@Param("companyId") String companyId,
			@Param("qualifier1") String qualifier1, @Param("qualifier2") String qualifier2,
			@Param("qualifier3") String qualifier3, @Param("qualifier4") String qualifier4,
			@Param("qualifier5") String qualifier5, Pageable pageable);

	@Query(value = "SELECT q FROM Question q WHERE q.qualifier1=:qualifier1 and q.qualifier2=:qualifier2 and q.qualifier3=:qualifier3 and q.qualifier4=:qualifier4 and q.qualifier5=:qualifier5 and q.companyId=:companyId")
	public List<Question> findQuestionsByQualifier5(@Param("companyId") String companyId,
			@Param("qualifier1") String qualifier1, @Param("qualifier2") String qualifier2,
			@Param("qualifier3") String qualifier3, @Param("qualifier4") String qualifier4,
			@Param("qualifier5") String qualifier5);

	@Query(value = "SELECT q FROM Question q WHERE q.companyId=:companyId and q.questionText LIKE %:searchText%", countQuery = "SELECT COUNT(*) FROM Question q WHERE q.companyId=:companyId and q.questionText LIKE %:searchText%")
	public Page<Question> searchQuestions(@Param("companyId") String companyId, @Param("searchText") String searchText,
			Pageable pageable);

	@Query(value = "SELECT q FROM Question q WHERE q.companyId=:companyId and q.questionText LIKE %:searchText%")
	public List<Question> searchQuestions(@Param("companyId") String companyId, @Param("searchText") String searchText);

	@Query(value = "SELECT q FROM Question q WHERE q.companyId=:companyId", countQuery = "SELECT COUNT(*) FROM Question q WHERE q.companyId=:companyId")
	public Page<Question> findQuestionsByCompanyIdAndPageNumber(@Param("companyId") String companyId,
			Pageable pageable);

	// @Query(value="SELECT q FROM Question q WHERE q.companyId=:companyId and
	// (q.qualifier2 IS NULL or trim(q.qualifier2)='' ) GROUP BY q.qualifier1")
	@Query(value = "SELECT q FROM Question q WHERE q.companyId=:companyId  GROUP BY q.qualifier1")
	public List<Question> getAllLevel1Questions(@Param("companyId") String companyId);

	@Query(value = "SELECT q FROM Question q WHERE q.companyId=:companyId  GROUP BY q.qualifier1")
	public Page<Question> getAllLevel1Questions(@Param("companyId") String companyId, Pageable pageable);

	@Query("SELECT "
			+ "    new com.assessment.common.Qualifiers(q.qualifier1, q.qualifier2, q.qualifier3, q.qualifier4, q.qualifier5) "
			+ "FROM " + "    Question q WHERE q.companyId=:companyId " + "GROUP BY "
			+ "    q.qualifier1, q.qualifier2, q.qualifier3, q.qualifier4, q.qualifier5")
	public Set<Qualifiers> getAllUniqueQualifiers(@Param("companyId") String companyId);

	@Query("select q from Question q where (q.qualifier1 is not null and q.qualifier2 is not null and q.qualifier3 is not null) group by q.qualifier1, q.qualifier2, q.qualifier3 having count(q) > 5")
	public List<Question> getAdaptiveAssessmentLevel3Questions(@Param("companyId") String companyId);

	// @Query("select q from Question q where (q.companyId=:companyId and
	// q.qualifier1=:qualifier1 and (q.qualifier2 is null or trim(q.qualifier2)
	// ='')) group by q.qualifier1 having count(q) > 5")

//	 @Query(value="select  count(*) from Question where (companyId =:companyId and qualifier1  =:qualifier1 and (qualifier2 is null or trim(qualifier2) ='')) having count(*) > 5")
//	 public Integer getAdaptiveAssessmentLevel1Count(@Param("qualifier1") String qualifier1, @Param("companyId")String companyId);

	@Query(value = "SELECT q FROM Question q WHERE q.companyId=:companyId and q.questionType=:type ")
	public Page<Question> getQuestionByQuestionType(@Param("companyId") String companyId,
			@Param("type") QuestionType type, Pageable pageable);

	@Query(value = "SELECT q FROM Question q WHERE q.companyId=:companyId and q.difficultyLevel=:level ")
	public Page<Question> getQuestionByDifficultyLevel(@Param("companyId") String companyId,
			@Param("level") DifficultyLevel level, Pageable pageable);

	@Query(value = "SELECT q FROM Question q WHERE q.companyId=:companyId and q.language=:language and q.questionType = com.assessment.data.QuestionType.CODING")
	public Page<Question> getQuestionByProgrammingLanguage(@Param("companyId") String companyId,
			@Param("language") ProgrammingLanguage language, Pageable pageable);

	@Query(value = "SELECT count(q) FROM Question q WHERE q.companyId=:companyId and q.questionType=:type ")
	public Long getQuestionCountByQuestionType(@Param("companyId") String companyId, @Param("type") QuestionType type);

	@Query(value = "SELECT count(q) FROM Question q WHERE q.companyId=:companyId and q.questionType=com.assessment.data.QuestionType.FULL_STACK_JAVA OR  q.questionType=com.assessment.data.QuestionType.FULLSTACK")
	public Long getFullStackQuestionCountByQuestionType(@Param("companyId") String companyId);

	@Query(value = "SELECT count(q) FROM Question q WHERE q.companyId=:companyId and q.questionType=com.assessment.data.QuestionType.FULL_STACK_JAVA OR  q.questionType=com.assessment.data.QuestionType.FULLSTACK")
	public Long getPopularSkillBasedQuestions(@Param("companyId") String companyId);

	@Query("SELECT  new com.assessment.web.dto.PopularSkillBasedQuestion(u.qualifier1, count(u.qualifier1)) FROM Question u WHERE u.companyId=:companyId group by u.qualifier1 ORDER BY (count(u.qualifier1)) desc")
	List<PopularSkillBasedQuestion> findPopSkillQuestionsInTests(@Param("companyId") String companyId,
			Pageable pageable);

	@Query(value = "SELECT q FROM Question q WHERE q.qualifier1=:qualifier1 and q.companyId=:companyId and q.difficultyLevel=:level and q.questionType=:type order by rand()")
	public List<Question> getRandomQuestionsForQualifiers1AndDifficultyLevel(@Param("companyId") String companyId,
			@Param("qualifier1") String qualifier1, @Param("level") DifficultyLevel level,
			@Param("type") QuestionType type, Pageable pageable);

	@Query(value = "SELECT q FROM Question q WHERE q.qualifier1=:qualifier1 and q.qualifier2=:qualifier2  and q.difficultyLevel=:level and q.companyId=:companyId and q.questionType=:type order by rand()")
	public List<Question> getRandomQuestionsForQualifiers1_2AndDifficultyLevel(@Param("companyId") String companyId,
			@Param("qualifier1") String qualifier1, @Param("qualifier2") String qualifier2,
			@Param("level") DifficultyLevel level, @Param("type") QuestionType type, Pageable pageable);

	@Query(value = "SELECT q FROM Question q WHERE q.qualifier1=:qualifier1 and q.qualifier2=:qualifier2 and q.qualifier3=:qualifier3 and q.difficultyLevel=:level and q.companyId=:companyId and q.questionType=:type order by rand()")
	public List<Question> getRandomQuestionsForQualifiers1_2_3AndDifficultyLevel(@Param("companyId") String companyId,
			@Param("qualifier1") String qualifier1, @Param("qualifier2") String qualifier2,
			@Param("qualifier3") String qualifier3, @Param("level") DifficultyLevel level,
			@Param("type") QuestionType type, Pageable pageable);

	@Query(value = "SELECT q FROM Question q WHERE q.qualifier1=:qualifier1 and q.qualifier2=:qualifier2 and q.qualifier3=:qualifier3 and q.qualifier4=:qualifier4 and q.difficultyLevel=:level and q.companyId=:companyId and q.questionType=:type order by rand()")
	public List<Question> getRandomQuestionsForQualifiers1_2_3_4AndDifficultyLevel(@Param("companyId") String companyId,
			@Param("qualifier1") String qualifier1, @Param("qualifier2") String qualifier2,
			@Param("qualifier3") String qualifier3, @Param("qualifier4") String qualifier4,
			@Param("level") DifficultyLevel level, @Param("type") QuestionType type, Pageable pageable);

	@Query(value = "SELECT q FROM Question q WHERE q.qualifier1=:qualifier1 and q.qualifier2=:qualifier2 and q.qualifier3=:qualifier3 and q.qualifier4=:qualifier4 and q.qualifier5=:qualifier5 and q.difficultyLevel=:level and q.companyId=:companyId and q.questionType=:type order by rand()")
	public List<Question> getRandomQuestionsForQualifiers1_2_3_4_5AndDifficultyLevel(
			@Param("companyId") String companyId, @Param("qualifier1") String qualifier1,
			@Param("qualifier2") String qualifier2, @Param("qualifier3") String qualifier3,
			@Param("qualifier4") String qualifier4, @Param("qualifier5") String qualifier5,
			@Param("level") DifficultyLevel level, @Param("type") QuestionType type, Pageable pageable);

	@Query(value = "SELECT q FROM Question q WHERE q.qualifier1=:qualifier1 and q.companyId=:companyId and q.questionType=:type order by rand()")
	public List<Question> getRandomQuestionsForQualifiers1(@Param("companyId") String companyId,
			@Param("qualifier1") String qualifier1, @Param("type") QuestionType type, Pageable pageable);

	@Query(value = "SELECT q FROM Question q WHERE q.qualifier1=:qualifier1 and q.qualifier2=:qualifier2 and q.companyId=:companyId and q.questionType=:type order by rand()")
	public List<Question> getRandomQuestionsForQualifiers1_2(@Param("companyId") String companyId,
			@Param("qualifier1") String qualifier1, @Param("qualifier2") String qualifier2,
			@Param("type") QuestionType type, Pageable pageable);

	@Query(value = "SELECT q FROM Question q WHERE q.qualifier1=:qualifier1 and q.qualifier2=:qualifier2 and q.qualifier3=:qualifier3 and q.companyId=:companyId and q.questionType=:type order by rand()")
	public List<Question> getRandomQuestionsForQualifiers1_2_3(@Param("companyId") String companyId,
			@Param("qualifier1") String qualifier1, @Param("qualifier2") String qualifier2,
			@Param("qualifier3") String qualifier3, @Param("type") QuestionType type, Pageable pageable);

	@Query(value = "SELECT q FROM Question q WHERE q.qualifier1=:qualifier1 and q.qualifier2=:qualifier2 and q.qualifier3=:qualifier3 and q.qualifier4=:qualifier4 and q.companyId=:companyId and q.questionType=:type order by rand()")
	public List<Question> getRandomQuestionsForQualifiers1_2_3_4(@Param("companyId") String companyId,
			@Param("qualifier1") String qualifier1, @Param("qualifier2") String qualifier2,
			@Param("qualifier3") String qualifier3, @Param("qualifier4") String qualifier4,
			@Param("type") QuestionType type, Pageable pageable);

	@Query(value = "SELECT q FROM Question q WHERE q.qualifier1=:qualifier1 and q.qualifier2=:qualifier2 and q.qualifier3=:qualifier3 and q.qualifier4=:qualifier4 and q.qualifier5=:qualifier5 and q.companyId=:companyId and q.questionType=:type order by rand()")
	public List<Question> getRandomQuestionsForQualifiers1_2_3_4_5(@Param("companyId") String companyId,
			@Param("qualifier1") String qualifier1, @Param("qualifier2") String qualifier2,
			@Param("qualifier3") String qualifier3, @Param("qualifier4") String qualifier4,
			@Param("qualifier5") String qualifier5, @Param("type") QuestionType type, Pageable pageable);

	@Query(value = "SELECT count(q) FROM Question q WHERE q.qualifier1=:qualifier1 and q.companyId=:companyId")
	public Long getCountQuestionsBySkill(@Param("companyId") String companyId, @Param("qualifier1") String qualifier1);

	@Query(value = "SELECT count(q) FROM Question q WHERE q.qualifier1=:qualifier1 and q.qualifier2=:qualifier2 and q.companyId=:companyId")
	public Long getCountQuestionsByQualifier1_2(@Param("companyId") String companyId,
			@Param("qualifier1") String qualifier1, @Param("qualifier2") String qualifier2);

//	 @Query("SELECT q FROM Question q WHERE q.companyId=:companyId q.qualifier1=:qualifier1 and q.qualifier2=:qualifier2 and q.qualifier3=:qualifier3 and q.qualifier4=:qualifier4")
//	 public List<Question> finduestionsTillQ4ByCompanyIdAfterDate(@Param("companyId") String companyId, @Param("qualifier1") String qualifier1,@Param("qualifier2") String qualifier2,@Param("qualifier3") String qualifier3,@Param("qualifier4") String qualifier4, @Param("afterDate") Date afterDate );

//	@Transactional
//	@Query("UPDATE  Question q set q.displayQ1 =?displayq1,  and q.parentGroupClassifier =?parentGroupClassifier where q.qualifier1 =?qualifier1 ")
//	@Query( value="update Question set displayQ1 =:displayQ1 where qualifier1 =:qualifier1 ", nativeQuery = true)
	@Modifying
	@Query(value = "update Question u set u.displayq1=:displayq1, u.parentGroupClassifier=:parentGroupClassifier where u.qualifier1=:qualifier1")
	public void updateQuestion(@Param("displayq1") String displayq1,
			@Param("parentGroupClassifier") String parentGroupClassifier, @Param("qualifier1") String qualifier1);

	@Query(value = "SELECT DISTINCT q.parentGroupClassifier FROM Question q WHERE q.parentGroupClassifier  !=''  ")
	public List<String> uquiqeQualifier();

//	@Query(value = "SELECT q FROM Question q WHERE  q.parentGroupClassifier IN :listparentGroupClassifier")
//	public List<Question> searchQuestionsByQualifier(@Param("companyId") String companyId,
//			@Param("listparentGroupClassifier") List<String> listparentGroupClassifier);
	
//	WORKING
//	@Query(value = "SELECT q FROM Question q WHERE  q.companyId=:companyId AND  q.parentGroupClassifier LIKE %:parentGroupClassifier%")
//	public List<Question> searchQuestionsByQualifier(@Param("companyId") String companyId,
//			@Param("parentGroupClassifier") String parentGroupClassifier);
	
	@Query(value = "SELECT q FROM Question q WHERE  q.companyId=:companyId AND  q.parentGroupClassifier IN:listparentGroupClassifier")
	public Page<Question> searchQuestionsByQualifier(@Param("companyId") String companyId,
			@Param("listparentGroupClassifier") List<String> listparentGroupClassifier, Pageable pageable);
}
