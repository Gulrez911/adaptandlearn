package com.assessment.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.assessment.data.Test;
import com.assessment.web.dto.TestLinkDTO;

public interface TestRepository extends JpaRepository<com.assessment.data.Test,Long>{
	
	@Query("SELECT t FROM Test t WHERE t.testName=:testName and t.companyId=:companyId")
	com.assessment.data.Test findByPrimaryKey(@Param("testName") String testName, @Param("companyId") String companyId);
	
	@Query("SELECT t FROM Test t WHERE  t.companyId=:companyId and (t.softDelete is null or t.softDelete = false)")
	List<com.assessment.data.Test> findByCompanyId( @Param("companyId") String companyId);
	
	@Query(value="SELECT t FROM Test t WHERE  t.companyId=:companyId and (t.softDelete is null or t.softDelete = false) ORDER BY t.createDate DESC", countQuery="SELECT COUNT(*) FROM Test t WHERE  t.companyId=:companyId and (t.softDelete is null or t.softDelete = false)")
	Page<com.assessment.data.Test> findByCompanyId(@Param("companyId") String companyId,  Pageable pageable);

	
	@Query("SELECT t FROM Test t WHERE t.companyId=:companyId and t.testName LIKE %:testName% and (t.softDelete is null or t.softDelete = false)")
	public List<Test> searchTests(@Param("companyId") String companyId, @Param("testName")  String testName);
	@Query(value="SELECT t FROM Test t WHERE t.companyId=:companyId and t.testName LIKE %:testName% and (t.softDelete is null or t.softDelete = false)", countQuery="SELECT COUNT(*) FROM Test t WHERE t.companyId=:companyId and t.testName LIKE %:testName% and (t.softDelete is null or t.softDelete = false)")
	Page<com.assessment.data.Test> searchTests(@Param("companyId") String companyId, @Param("testName")  String testName, Pageable pageable);
	
	@Query("SELECT t FROM Test t WHERE t.id=:testId and t.companyId=:companyId")
	com.assessment.data.Test findTestById(@Param("testId") Long TestId,@Param("companyId") String companyId);
	
	@Query("SELECT t FROM Test t WHERE t.testName=:testName and  t.companyId=:companyId")
	List<com.assessment.data.Test> findByMultiplr( @Param("testName") String testName, @Param("companyId") String companyId);

	@Query("SELECT " +
	           "    new com.assessment.web.dto.TestLinkDTO(t.id, t.testName) " +
	           "FROM " +
	           "    Test t where t.companyId=:companyId " +
	           "ORDER BY " +
	           "    t.testName")
	List<TestLinkDTO> getAllTests(@Param("companyId") String companyId);
	
	
	@Query("SELECT t.testName FROM Test t WHERE t.companyId=:companyId and t.testName IS NOT NULL and (t.softDelete is null or t.softDelete = false) ORDER BY t.testName ")
	List<String> getTestNames(@Param("companyId") String companyId);
	
	@Query(value="SELECT t FROM Test t join t.skills s WHERE t.companyId=:companyId and s.skillName =:skillName and (t.softDelete is null or t.softDelete = false)")
	Page<com.assessment.data.Test> searchTestsBySkill(@Param("companyId") String companyId, @Param("skillName")  String skillName, Pageable pageable);

	
	@Query(value="SELECT t FROM Test t WHERE t.companyId=:companyId and t.fullStackTest != true and (t.softDelete is null or t.softDelete = false)")
	Page<com.assessment.data.Test> generalTests(@Param("companyId") String companyId, Pageable pageable);

	
	@Query(value="SELECT t FROM Test t WHERE t.companyId=:companyId and t.fullStackTest = true and (t.softDelete is null or t.softDelete = false)")
	Page<com.assessment.data.Test> fullstackTests(@Param("companyId") String companyId, Pageable pageable);

	@Query(value="SELECT t FROM Test t WHERE t.companyId=:companyId and t.considerConfidence = true and (t.softDelete is null or t.softDelete = false)")
	Page<com.assessment.data.Test> confidenceTests(@Param("companyId") String companyId, Pageable pageable);

	@Query(value="SELECT t FROM Test t WHERE t.companyId=:companyId and t.onboardingTest = true and (t.softDelete is null or t.softDelete = false)")
	Page<com.assessment.data.Test> onboardingTests(@Param("companyId") String companyId, Pageable pageable);

	@Query(value="SELECT t FROM Test t WHERE t.companyId=:companyId and t.sendRecommReport = true and (t.softDelete is null or t.softDelete = false)")
	Page<com.assessment.data.Test> recommendationTests(@Param("companyId") String companyId, Pageable pageable);

	@Query(value="SELECT t FROM Test t WHERE t.companyId=:companyId and t.passPercent >= :passPercent and (t.softDelete is null or t.softDelete = false)")
	Page<com.assessment.data.Test> bypercentageTests(@Param("companyId") String companyId,@Param("passPercent") Float passPercent, Pageable pageable);

	@Query(value="SELECT t FROM Test t WHERE t.companyId=:companyId and t.difficultyLevel = com.assessment.data.DifficultyLevel.VERY_EASY and (t.softDelete is null or t.softDelete = false)")
	Page<com.assessment.data.Test> byVeryEasydifflevelTests(@Param("companyId") String companyId, Pageable pageable);

	@Query(value="SELECT t FROM Test t WHERE t.companyId=:companyId and t.difficultyLevel = com.assessment.data.DifficultyLevel.EASY and (t.softDelete is null or t.softDelete = false)")
	Page<com.assessment.data.Test> byEasydifflevelTests(@Param("companyId") String companyId, Pageable pageable);

	
	@Query(value="SELECT t FROM Test t WHERE t.companyId=:companyId and t.difficultyLevel = com.assessment.data.DifficultyLevel.MEDIUM and (t.softDelete is null or t.softDelete = false)")
	Page<com.assessment.data.Test> byMediumdifflevelTests(@Param("companyId") String companyId, Pageable pageable);

	@Query(value="SELECT t FROM Test t WHERE t.companyId=:companyId and t.difficultyLevel = com.assessment.data.DifficultyLevel.DIFFICULT and (t.softDelete is null or t.softDelete = false)")
	Page<com.assessment.data.Test> byDifficultdifflevelTests(@Param("companyId") String companyId, Pageable pageable);

	@Query(value="SELECT t FROM Test t WHERE t.companyId=:companyId and t.difficultyLevel = com.assessment.data.DifficultyLevel.VERY_DIFFICULT and (t.softDelete is null or t.softDelete = false)")
	Page<com.assessment.data.Test> byVeryDifficultdifflevelTests(@Param("companyId") String companyId, Pageable pageable);

	
	@Query("SELECT t FROM Test t WHERE t.createdBy=:createdBy and  t.companyId=:companyId ")
	List<com.assessment.data.Test> findByAdmin( @Param("createdBy") String createdBy,  @Param("companyId")  String companyId);
	
}