package com.assessment.repositories;
 
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.assessment.data.Skill;
import com.assessment.data.SkillLevel;

public interface SkillRepository extends JpaRepository<Skill,Long>{
	
	@Query("SELECT s FROM Skill s WHERE s.skillName=:skillName and s.skillLevel=:skillLevel and s.companyId=:companyId")
	Skill findByPrimaryKey(@Param("skillName") String skillName, @Param("skillLevel") SkillLevel skillLevel, @Param("companyId") String companyId);

	@Query("SELECT s FROM Skill s WHERE s.companyId=:companyId")
	List<Skill> getSkillsByCompanyId(@Param("companyId") String companyId);
	
	
	@Query("SELECT DISTINCT(s.skillName) FROM Skill s WHERE s.companyId=:companyId ORDER BY s.skillName")
	List<String> getSkillNamessByCompanyId(@Param("companyId") String companyId);
	
	@Query("SELECT s FROM Skill s WHERE s.companyId=:companyId")
	Page<Skill> getSkillsByCompanyId(@Param("companyId") String companyId,Pageable pageable);
	
	@Query("SELECT s FROM Skill s WHERE s.companyId=:companyId and s.skillName LIKE %:searchText%")
	Page<Skill> searchSkill(@Param("companyId") String companyId,@Param("searchText")  String searchText,Pageable pageable );
	
	@Query(value="select * from Test_Skill where skills_id=:id",nativeQuery = true)
	List<Object> getSkillById(@Param("id") Long id);
}
