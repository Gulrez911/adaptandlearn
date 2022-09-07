package com.assessment.web.controllers;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.assessment.common.CommonUtil;
import com.assessment.data.QuestionMapperInstance;
import com.assessment.data.User;
import com.assessment.services.QuestionMapperInstanceService;
import com.assessment.services.UserService;
import com.ibm.icu.text.DateFormat;

@Controller
public class NewFullStackController {

	@Autowired
	QuestionMapperInstanceService questionMapperInstanceService;

	@Autowired
	UserService userService;

	@RequestMapping(value = "/newShowAllResultsforMFA", method = RequestMethod.GET)
	public ModelAndView newShowAllResultsforMFA(
			@RequestParam(name = "page", required = false) Integer pageNumber,
			HttpServletRequest request, HttpServletResponse response) {
		User user = (User) request.getSession().getAttribute("user");

		if (pageNumber == null) {
			pageNumber = 0;
		}

		Page<QuestionMapperInstance> instances = questionMapperInstanceService
				.findAllFullStackQuestionMapperInstances(user.getCompanyId(),
						PageRequest.of(pageNumber, 20));

		// List<QuestionMapperInstance> instances =
		// questionMapperInstanceService.findAllFullStackQuestionMapperInstances(user.getCompanyId());
		for (QuestionMapperInstance instance : instances) {
			User user2 = userService.findByPrimaryKey(instance.getUser(), user.getCompanyId());
			instance.setUerFullName(user2.getFirstName() + " " + user2.getLastName());
			instance.setLastDate(DateFormat.getDateTimeInstance()
					.format(instance.getUpdateDate() == null
							? instance.getCreateDate()
							: instance.getUpdateDate()));
		}

		ModelAndView mav = new ModelAndView("newFullStack_Results");
		mav.addObject("instances", instances.getContent());
		Map<String, String> params = new HashMap<>();
		// params.put("qualifier1", qualifier1);
		CommonUtil.setCommonAttributesOfPagination(instances, mav.getModelMap(), pageNumber,
				"showAllResultsforMFA", params);
		return mav;

		// return mav;
	}
}
