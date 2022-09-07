package com.test;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.ModelAndView;

import com.assessment.data.AssessmentUploadData;
import com.assessment.repositories.AssessmentUploadDataRepository;
import com.assessment.repositories.CategoryContextRepository;
import com.assessment.web.controllers.CategoryContextController;
import com.assessment.web.dto.Level1ContainerDto;
import com.assessment.web.dto.LevelDto;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:appContext.xml"})
@Transactional
public class TestCategorycontext {

	@Autowired
	CategoryContextController categoryContextController;
	
	@Autowired
	CategoryContextRepository rep;
	
	@Autowired
	AssessmentUploadDataRepository assRep;
	
	@Test
	public void testGetHomePage(){
		ModelAndView mav =  categoryContextController.landing(null, null);
		List<Level1ContainerDto> level1Containers = (List<Level1ContainerDto>) mav.getModelMap().get("level1Containers");
		System.out.println(level1Containers.size());
	}
	
	@Test
	public void testUniqueLevel1WithIds(){
		List<LevelDto> levels = rep.findParentCategoriesLevel1WithId("e-assess");
			for(LevelDto dto : levels){
				System.out.println("level 1 "+dto.getLevel()+" id "+dto.getId());
			}
		
	}
	
	@Test
	public void testGetAssUploadData(){
		List<AssessmentUploadData> data = assRep.findByPrimaryKey3("e-assess","Applied Chemistry - Green Chemistry: (Methods of Synthesis, Needs of Green Chemistry, Principles of Green Chemistry, Communications, Aptitude)");
		System.out.println(data);
	}
}
