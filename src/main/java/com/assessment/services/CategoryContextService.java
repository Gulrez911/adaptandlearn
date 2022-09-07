package com.assessment.services;

import java.util.List;

import com.assessment.data.CategoryContext;

public interface CategoryContextService {

	
	public CategoryContext findUniqueByAssessmentUploadDataName(String companyId,  String level1,String level2,  String level3, String level4, String assessmentUpoloadDataName);
	
	public CategoryContext findUniqueByAssessmentName( String companyId, String level1, String level2,  String level3, String level4,  String assessmentName);
	
	public List<CategoryContext> findAssessmentUploadDatasForLevel( String companyId,  String level1, String level2,  String level3,  String level4);
	
	public List<CategoryContext> findAssessmentsForLevel(String companyId,  String level1,  String level2,  String level3, String level4);

	public CategoryContext saveOrUpdate(CategoryContext context);
	
//	public List<CategoryContext> findCollectiveCategoriesAtLevel4(String companyId);
//	
//	public List<CategoryContext> findCollectiveCategoriesAtLevel3(String companyId);
//	
//	public List<CategoryContext> findCollectiveCategoriesAtLevel2(String companyId);
//	
//	
//	public List<CategoryContext> findIndividualCategoriesAtLevel4(String companyId);
//	
//	public List<CategoryContext> findIndividualCategoriesAtLevel3(String companyId);
//	
//	public List<CategoryContext> findIndividualCategoriesAtLevel2(String companyId);
	
	
	
}
