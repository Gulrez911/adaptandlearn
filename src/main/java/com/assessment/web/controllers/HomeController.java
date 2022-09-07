package com.assessment.web.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.assessment.common.PropertyConfig;
import com.assessment.data.CategoryContext;
import com.assessment.repositories.CategoryContextRepository;
import com.assessment.web.dto.Level1ContainerDto;
import com.assessment.web.dto.Level2ContainerDto;
import com.assessment.web.dto.Level3ContainerDto;
import com.assessment.web.dto.LevelDto;

@Controller
public class HomeController {

	@Autowired
	CategoryContextRepository categoryContextRepository;

	@Autowired
	PropertyConfig propertyConfig;

	
	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public ModelAndView home(HttpServletRequest request, HttpServletResponse response, @RequestParam(name = "q1", required = false) String q1) {
		ModelAndView mav = new ModelAndView("1");
		String companyId = propertyConfig.getDefaultCompanyId();
		if (companyId == null || companyId.trim().length() == 0) {
			companyId = "e-assess";
		}
		List<Level1ContainerDto> level1Containers = new ArrayList<>();
		List<LevelDto> level1List = categoryContextRepository.findParentCategoriesLevel1WithId(companyId);
		for (LevelDto level1 : level1List) {
			if (q1.equals(level1.getLevel())) {
				List<LevelDto> level2List = categoryContextRepository.findLevel2CategoriesWithId(companyId, level1.getLevel());
				for (LevelDto level2 : level2List) {

					List<Level2ContainerDto> level2Containers = new ArrayList<Level2ContainerDto>();
					List<LevelDto> level3List = categoryContextRepository.findLevel3CategoriesWithId(companyId, level1.getLevel(), level2.getLevel());
					for (LevelDto level4 : level3List) {
						Level2ContainerDto level2ContainerDto = new Level2ContainerDto();
						level2ContainerDto.setLevel2(level4.getLevel());
						level2ContainerDto.setId(level4.getId());
						level2Containers.add(level2ContainerDto);
					}
					Level1ContainerDto level1Container = new Level1ContainerDto();
					level1Container.setLevel1(level2.getLevel());
					level1Container.setId(level2.getId());
					level1Container.setLevel2Containers(level2Containers);
					level1Containers.add(level1Container);

				}

				break;
			}
		}
		mav.addObject("q1", q1);
		mav.addObject("level1Containers", level1Containers);
		return mav;
	}

	@RequestMapping(value = "/levele2", method = RequestMethod.GET)
	public ModelAndView levele2(HttpServletRequest request, HttpServletResponse response, @RequestParam(name = "q2", required = false) String q2,
			@RequestParam(name = "q1", required = false) String q1) {
		ModelAndView mav = new ModelAndView("2");
		String companyId = propertyConfig.getDefaultCompanyId();
		if (companyId == null || companyId.trim().length() == 0) {
			companyId = "e-assess";
		}
		List<LevelDto> level4List = categoryContextRepository.findLevel3CategoriesWithId(companyId, q1, q2);
		List<Level2ContainerDto> level2Containers = new ArrayList<Level2ContainerDto>();
		for (LevelDto level2 : level4List) {
			List<Level3ContainerDto> level3Containers = new ArrayList<Level3ContainerDto>();
			List<LevelDto> level3List = categoryContextRepository.findLevel4CategoriesWithId(companyId, q1, q2, level2.getLevel());
			for (LevelDto level4 : level3List) {
				Level3ContainerDto level3ContainerDto = new Level3ContainerDto();
				level3ContainerDto.setLevel3(level4.getLevel());
				level3ContainerDto.setId(level4.getId());
				level3Containers.add(level3ContainerDto);
			}
			Level2ContainerDto level2ContainerDto = new Level2ContainerDto();
			level2ContainerDto.setLevel2(level2.getLevel());
			level2ContainerDto.setId(level2.getId());
			level2ContainerDto.setLevel3containers(level3Containers);
			level2Containers.add(level2ContainerDto);
		}
		mav.addObject("q1", q1);
		mav.addObject("q2", q2);
		mav.addObject("level2Containers", level2Containers);
		return mav;
	}

	@RequestMapping(value = "/levele3", method = RequestMethod.GET)
	public ModelAndView levele3(HttpServletRequest request, HttpServletResponse response, @RequestParam String q1, @RequestParam String q2, @RequestParam String q3) {
		ModelAndView mav = new ModelAndView("3");
		String companyId = propertyConfig.getDefaultCompanyId();
		if (companyId == null || companyId.trim().length() == 0) {
			companyId = "e-assess";
		}
		List<LevelDto> level4List = categoryContextRepository.findLevel4CategoriesWithId(companyId, q1, q2, q3);
		List<Level3ContainerDto> level3Containers = new ArrayList<Level3ContainerDto>();
		for (LevelDto level4 : level4List) {
			Level3ContainerDto level3ContainerDto = new Level3ContainerDto();
			level3ContainerDto.setLevel3(level4.getLevel());
			level3ContainerDto.setId(level4.getId());
			level3Containers.add(level3ContainerDto);
		}
		mav.addObject("q1", q1);
		mav.addObject("q2", q2);
		mav.addObject("q3", q3);
		mav.addObject("level3Containers", level3Containers);
		return mav;

	}
}
