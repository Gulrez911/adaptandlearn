package com.assessment.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.assessment.data.Tenant;
import com.assessment.data.TestLinkTime;
import com.assessment.data.User;

public interface TestLinkTimeRepository extends JpaRepository<TestLinkTime, Long> {
	//LIKE %:searchText%"
	@Query(value="SELECT t FROM TestLinkTime t WHERE t.companyId=:companyId and t.testName LIKE %:testName%")
	public List<TestLinkTime> searchTestLinkTimes(@Param("companyId") String companyId, @Param("testName") String testName);

	@Query(value= "SELECT t FROM TestLinkTime t", countQuery="SELECT COUNT(*) FROM TestLinkTime t")
	public Page<TestLinkTime> findAllLinks(Pageable pageable);
	
	@Query(value= "SELECT t FROM TestLinkTime t where t.companyId=:companyId and t.url=:url")
	public TestLinkTime  findUniquetestLink(@Param("companyId") String companyId, @Param("url") String url);
	
//	Gulrez

	@Query("SELECT q FROM TestLinkTime q WHERE q.companyId=:companyId and ( q.testName LIKE %:searchText%  OR q.url LIKE %:searchText%)")
	Page<TestLinkTime> findTestLink(@Param("companyId") String companyId, @Param("searchText")  String searchText,Pageable pageable);
	
//	@Query("SELECT q FROM TestLinkTime q WHERE  ")
	Page<TestLinkTime> findAllByOrderByIdDesc(Pageable pageable);
}