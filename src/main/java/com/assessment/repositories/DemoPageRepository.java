package com.assessment.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.assessment.data.DemoPage;

public interface DemoPageRepository extends JpaRepository<DemoPage,Long>
{
	
	@Query("SELECT c FROM DemoPage c WHERE c.pageName=:pageName and c.companyId=:companyId")
	DemoPage findByPrimaryKey(@Param("pageName") String pageName, @Param("companyId") String companyId);
	
	@Query("SELECT c FROM DemoPage c WHERE c.companyId=:companyId order by c.createDate")
	List<DemoPage> findByCompanyId(@Param("companyId") String companyId);
	
	
	@Query(value="SELECT q FROM DemoPage q WHERE q.companyId=:companyId and q.pageName LIKE %:searchText%  order by q.createDate")
	public List<DemoPage> searchDemoPages(@Param("companyId") String companyId, @Param("searchText")  String searchText);
}