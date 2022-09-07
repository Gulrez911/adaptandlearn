package com.assessment.web.controllers;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.assessment.Exceptions.AssessmentGenericException;
import com.assessment.common.CommonUtil;
import com.assessment.common.util.NavigationConstants;
import com.assessment.data.Skill;
import com.assessment.data.SkillLevel;
import com.assessment.data.User;
import com.assessment.repositories.SkillRepository;
import com.assessment.services.SkillService;

@Controller
public class NewSkillController {

	@Autowired
	SkillRepository skillRepository;
	
	@Autowired
	SkillService skillService;
	
	@RequestMapping(value = "/newSkills", method = RequestMethod.GET)
	  public ModelAndView newGetSkills(@RequestParam(name= "page", required = false) Integer pageNumber,HttpServletRequest request, HttpServletResponse response) {
		User user = (User) request.getSession().getAttribute("user");
		ModelAndView mav = new ModelAndView("newSkills");
		 if(pageNumber == null){
				pageNumber = 0;
		 }
		 Page<Skill> skills = skillRepository.getSkillsByCompanyId(user.getCompanyId(),PageRequest.of(pageNumber, NavigationConstants.NO_SKILLS_PAGE));
//		java.util.List<Skill> skills = skillRepository.getSkillsByCompanyId(user.getCompanyId());
		 Skill skill = new Skill();
		 mav.addObject("levels", SkillLevel.values());
		 mav.addObject("skill", skill);
//		 List<Skill>  list = new ArrayList<>();
		 for(Skill skill2:skills.getContent()) {
			List<Object> obj= skillRepository.getSkillById(skill2.getId());
			skill2.setNoOfTest(obj.size());
		 }
		mav.addObject("skills", skills.getContent());
		Map<String, String> queryParams = new HashMap<>();
		CommonUtil.setCommonAttributesOfPagination(skills, mav.getModelMap(), pageNumber, "newSkills", queryParams);
		return mav;
	}
 
	
	
	@RequestMapping(value = "/newSaveSkill", method = RequestMethod.POST)
	  public ModelAndView newSaveSkill(HttpServletRequest request, HttpServletResponse response,@ModelAttribute("skill") Skill skill) {
		User user = (User) request.getSession().getAttribute("user");
		ModelAndView mav = new ModelAndView("newSkills");
		Skill skill2 = new Skill();
		mav.addObject("skill", skill2);
		 Page<Skill> skills = skillRepository.getSkillsByCompanyId(user.getCompanyId(),PageRequest.of(0, NavigationConstants.NO_SKILLS_PAGE));
		mav.addObject("levels", SkillLevel.values());
		mav.addObject("skill", skill2);
		mav.addObject("skills", skills.getContent());
		Map<String, String> queryParams = new HashMap<>();
		CommonUtil.setCommonAttributesOfPagination(skills, mav.getModelMap(), 0, "newSkills", queryParams);
		if(skill.getId() != null){
			skill.setCompanyId(user.getCompanyId());
			skill.setCompanyName(user.getCompanyName());
			skill.setUpdateDate(new Date());
			skillService.updateSkill(skill);
			mav.addObject("message", "Skill saved successfully" );// later put it as label
			mav.addObject("msgtype", "Information");
			mav.addObject("icon", "success");
		}
		else{
			try {
				skill.setCompanyId(user.getCompanyId());
				skill.setCompanyName(user.getCompanyName());
				skill.setCreateDate(new Date());
				skillService.createSkill(skill);
				mav.addObject("message", "Skill saved successfully" );// later put it as label
				mav.addObject("msgtype", "Information");
				mav.addObject("icon", "success");
			} catch (AssessmentGenericException e) {
				// TODO Auto-generated catch block
		 
				mav.addObject("message", "Skill object of same name and level exists! You can not create a duplicate skill" );// later put it as label
				mav.addObject("msgtype", "Information");
				mav.addObject("icon", "warning");
			}
		}
		 return mav;
	}
	
	@RequestMapping(value = "/searchSkill", method = RequestMethod.GET)
	  public ModelAndView searchSkill(@RequestParam(name= "page", required = false) Integer pageNumber,@RequestParam String searchText,HttpServletRequest request, HttpServletResponse response) {
		User user = (User) request.getSession().getAttribute("user");
		ModelAndView mav = new ModelAndView("newSkills");
		 if(pageNumber == null){
				pageNumber = 0;
		 }
		 Page<Skill> skills = skillRepository.searchSkill(user.getCompanyId(),searchText,PageRequest.of(pageNumber, NavigationConstants.NO_SKILLS_PAGE));
		 Skill skill = new Skill();
		 mav.addObject("levels", SkillLevel.values());
		 mav.addObject("skill", skill);
		mav.addObject("skills", skills.getContent());
		Map<String, String> queryParams = new HashMap<>();
		queryParams.put("searchText", searchText);
		CommonUtil.setCommonAttributesOfPagination(skills, mav.getModelMap(), pageNumber, "searchSkill", queryParams);
		return mav;
	}
	
	@RequestMapping(value = "/editSkill", method = RequestMethod.GET)
	 @ResponseBody
	  public Map<String,Object> editSkill(HttpServletResponse response, HttpServletRequest request,@RequestParam Long id ) throws Exception {
		 Map<String,Object> map = new HashMap<>();
		Skill skill = skillRepository.findById(id).get();
		map.put("skill", skill);
		 return map;
		}
	
	@RequestMapping(value = "/deleteSkill", method = RequestMethod.GET)
	 @ResponseBody
	  public Map<String,Object> deleteSkill(HttpServletResponse response, HttpServletRequest request,@RequestParam Long id ) throws Exception {
		 Map<String,Object> map = new HashMap<>();
		List<Object> obj= skillRepository.getSkillById(id);
		if(obj==null||obj.size()==0) {
			skillRepository.deleteById(id);
			map.put("msg", "Skill deleted successfully");
			map.put("msgtype", "Information");
			map.put("icon", "success");
		}else {
			map.put("msg", "Skill can't be deleted");
			map.put("msgtype", "Information");
			map.put("icon", "warning");
		}
		 return map;
		}
}
