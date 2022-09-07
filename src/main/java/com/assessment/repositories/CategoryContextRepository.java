package com.assessment.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.assessment.data.CategoryContext;
import com.assessment.web.dto.LevelDto;

public interface CategoryContextRepository extends JpaRepository<CategoryContext, Long> {
	
	@Query(value="SELECT q FROM CategoryContext q WHERE q.companyId=:companyId and q.level1=:level1 and q.level2=:level2 and q.level3=:level3 and q.level4=:level4 and q.assessmentUpoloadDataName=:assessmentUpoloadDataName")
	public CategoryContext findUniqueByAssessmentUploadDataName(@Param("companyId") String companyId, @Param("level1") String level1, @Param("level2") String level2, @Param("level3") String level3, @Param("level4") String level4, @Param("assessmentUpoloadDataName") String assessmentUpoloadDataName);
	
	@Query(value="SELECT q FROM CategoryContext q WHERE q.companyId=:companyId and q.level1=:level1 and q.level2=:level2 and q.level3=:level3 and q.level4=:level4 and q.assessmentName=:assessmentName")
	public CategoryContext findUniqueByAssessmentName(@Param("companyId") String companyId, @Param("level1") String level1, @Param("level2") String level2, @Param("level3") String level3, @Param("level4") String level4, @Param("assessmentName") String assessmentName);
	
	@Query(value="SELECT q FROM CategoryContext q WHERE q.companyId=:companyId and q.level1=:level1 and q.level2=:level2 and q.level3=:level3 and q.level4=:level4 and q.collective=true")
	public List<CategoryContext> findAssessmentUploadDatasForLevel(@Param("companyId") String companyId, @Param("level1") String level1, @Param("level2") String level2, @Param("level3") String level3, @Param("level4") String level4);
	
	@Query(value="SELECT q FROM CategoryContext q WHERE q.companyId=:companyId and q.level1=:level1 and q.level2=:level2 and q.level3=:level3 and q.level4=:level4 and q.collective=false")
	public List<CategoryContext> findAssessmentsForLevel(@Param("companyId") String companyId, @Param("level1") String level1, @Param("level2") String level2, @Param("level3") String level3, @Param("level4") String level4);
	
	
	@Query(value="SELECT q FROM CategoryContext q WHERE q.companyId=:companyId and q.collective=true")
	public Page<CategoryContext> findCategoriesForAssUploadData(@Param("companyId") String companyId, Pageable pageable);
	
	@Query(value="SELECT q FROM CategoryContext q WHERE q.companyId=:companyId and q.collective=false")
	public Page<CategoryContext> findCategoriesForAss(@Param("companyId") String companyId, Pageable pageable);
	
	@Query(value="SELECT q FROM CategoryContext q WHERE q.companyId=:companyId and q.assessmentUpoloadDataName=:assessmentUpoloadDataName")
	public List<CategoryContext> findByAssUploadDataName(@Param("companyId") String companyId,  @Param("assessmentUpoloadDataName") String assessmentUpoloadDataName);
	
	
	@Query(value="SELECT distinct (q.level1) FROM CategoryContext q WHERE q.companyId=:companyId and q.collective=true")
	public List<String> findParentCategoriesLevel1(@Param("companyId") String companyId);
	
	@Query(value="SELECT distinct (q.level2) FROM CategoryContext q WHERE q.companyId=:companyId and q.level1=:level1 and q.collective=true")
	public List<String> findLevel2Categories(@Param("companyId") String companyId, @Param("level1") String level1);
	
	@Query(value="SELECT distinct (q.level3) FROM CategoryContext q WHERE q.companyId=:companyId and q.level1=:level1 and q.level2=:level2 and q.collective=true")
	public List<String> findLevel3Categories(@Param("companyId") String companyId, @Param("level1") String level1, @Param("level2") String level2);
	
	@Query(value="SELECT q.level4 FROM CategoryContext q WHERE q.companyId=:companyId and q.level1=:level1 and q.level2=:level2 and q.level3=:level3 and q.collective=true")
	public List<String> findLevel4Categories(@Param("companyId") String companyId, @Param("level1") String level1, @Param("level2") String level2, @Param("level3") String level3);
	

	@Query("SELECT " +
	           "    new com.assessment.web.dto.LevelDto(q.level1, q.id) " +
	           "FROM " +
	           "    CategoryContext q WHERE q.companyId=:companyId and q.collective=true group by q.level1")
	//@Query(value="SELECT distinct (q.level1) FROM CategoryContext q WHERE q.companyId=:companyId and q.collective=true")
	public List<LevelDto> findParentCategoriesLevel1WithId(@Param("companyId") String companyId);
	
	
	@Query("SELECT " +
	           "    new com.assessment.web.dto.LevelDto(q.level2, q.id) " +
	           "FROM " +
	           "    CategoryContext q WHERE q.companyId=:companyId and q.level1=:level1  and q.collective=true group by q.level2")
	//@Query(value="SELECT distinct (q.level2) FROM CategoryContext q WHERE q.companyId=:companyId and q.level1=:level1 and q.collective=true")
	public List<LevelDto> findLevel2CategoriesWithId(@Param("companyId") String companyId, @Param("level1") String level1);
	
	@Query("SELECT " +
	           "    new com.assessment.web.dto.LevelDto(q.level3, q.id) " +
	           "FROM " +
	           "    CategoryContext q WHERE q.companyId=:companyId and q.level1=:level1 and q.level2=:level2 and q.collective=true group by q.level3")
	//@Query(value="SELECT distinct (q.level3) FROM CategoryContext q WHERE q.companyId=:companyId and q.level1=:level1 and q.level2=:level2 and q.collective=true")
	public List<LevelDto> findLevel3CategoriesWithId(@Param("companyId") String companyId, @Param("level1") String level1, @Param("level2") String level2);
	
	@Query("SELECT " +
	           "    new com.assessment.web.dto.LevelDto(q.level4, q.id) " +
	           "FROM " +
	           "    CategoryContext q WHERE q.companyId=:companyId and q.level1=:level1 and q.level2=:level2 and q.level3=:level3 and q.collective=true")
	//@Query(value="SELECT q.level4 FROM CategoryContext q WHERE q.companyId=:companyId and q.level1=:level1 and q.level2=:level2 and q.level3=:level3 and q.collective=true")
	public List<LevelDto> findLevel4CategoriesWithId(@Param("companyId") String companyId, @Param("level1") String level1, @Param("level2") String level2, @Param("level3") String level3);
	
	
}
