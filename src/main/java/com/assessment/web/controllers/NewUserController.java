package com.assessment.web.controllers;

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

import com.assessment.common.CommonUtil;
import com.assessment.common.util.NavigationConstants;
import com.assessment.data.User;
import com.assessment.data.UserTestSession;
import com.assessment.repositories.UserRepository;
import com.assessment.services.UserService;
import com.assessment.services.UserTestSessionService;

@Controller
public class NewUserController {

	@Autowired
	UserService userService;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	
	UserTestSessionService userTestSessionService;
	
	@RequestMapping(value = "/newListUsers", method = RequestMethod.GET)
	  public ModelAndView newListUsers(@RequestParam(name= "page", required = false) Integer pageNumber,HttpServletResponse response, HttpServletRequest request ) throws Exception {
		 User user = (User) request.getSession().getAttribute("user");
		 if(pageNumber == null){
				pageNumber = 0;
		 }
		 Page<User> users = userRepository.findUsersByCompanyId(user.getCompanyId(),PageRequest.of(pageNumber, NavigationConstants.NO_USERS_PAGE));
		 ModelAndView mav = new ModelAndView("newList_user");
		 mav.addObject("users", users.getContent());
		 User usr = new User();
		 usr.setCompanyId(user.getCompanyId());
		 usr.setCompanyName(user.getCompanyName());
		 mav.addObject("usr", usr);
		 Map<String, String> queryParams = new HashMap<>();
		 CommonUtil.setCommonAttributesOfPagination(users, mav.getModelMap(), pageNumber, "newListUsers", queryParams);
		 return mav;
		}
	
	 @RequestMapping(value = "/newSaveUser", method = RequestMethod.POST)
	  public ModelAndView saveUser(HttpServletRequest request, HttpServletResponse response,@ModelAttribute("usr") User usr) {
		 User user = (User) request.getSession().getAttribute("user");
		 ModelAndView mav= new ModelAndView("newList_user");
		 usr.setCompanyId(user.getCompanyId());
		 usr.setCompanyName(user.getCompanyName());
		 userService.saveOrUpdate(usr);
		 Page<User> users = userRepository.findUsersByCompanyId(user.getCompanyId(),PageRequest.of(0, NavigationConstants.NO_USERS_PAGE));
		 mav.addObject("users", users.getContent());
		 Map<String, String> queryParams = new HashMap<>();
		 CommonUtil.setCommonAttributesOfPagination(users, mav.getModelMap(), 0, "newListUsers", queryParams);
		 User usr2 = new User();
		 mav.addObject("usr", usr2);
		 mav.addObject("message", "User saved successfully" );// later put it as label
		mav.addObject("msgtype", "Information");
		mav.addObject("icon", "success");
		 return mav;
	 }
	 
	 @RequestMapping(value = "/newSearchUsrs", method = RequestMethod.GET)
	  public ModelAndView newSearchUsers(@RequestParam(name= "page", required = false) Integer pageNumber,@RequestParam String searchText, HttpServletResponse response, HttpServletRequest request ) throws Exception {
		 User user = (User) request.getSession().getAttribute("user");
		 if(pageNumber == null){
			 pageNumber = 0;
		 }
		 Page<User> users = userRepository.searchUsers(user.getCompanyId(), searchText,PageRequest.of(pageNumber, NavigationConstants.NO_USERS_PAGE));
		 ModelAndView mav = new ModelAndView("newList_user");
		 mav.addObject("users", users.getContent());
		 User usr = new User();
		 usr.setCompanyId(user.getCompanyId());
		 usr.setCompanyName(user.getCompanyName());
		 mav.addObject("usr", usr);
		 Map<String, String> queryParams = new HashMap<>();
		queryParams.put("searchText", searchText);
		 CommonUtil.setCommonAttributesOfPagination(users, mav.getModelMap(), pageNumber, "newSearchUsrs", queryParams);
		 return mav;
		}
	 
	 @RequestMapping(value = "/getUser", method = RequestMethod.GET)
	 @ResponseBody
	  public Map<String,Object> getUser(HttpServletResponse response, HttpServletRequest request,@RequestParam String email ) throws Exception {
		 User user = (User) request.getSession().getAttribute("user");
		 Map<String,Object> map = new HashMap<>();
		User user2 = userService.findByPrimaryKey(email, user.getCompanyId());
		map.put("user", user2);
		 return map;
		}
	
	 @RequestMapping(value = "/deleteUser", method = RequestMethod.GET)
	  public ModelAndView deleteUser(HttpServletResponse response, HttpServletRequest request,@RequestParam String email ) throws Exception {
		 User user = (User) request.getSession().getAttribute("user");
		 Page<User> users = userRepository.findUsersByCompanyId(user.getCompanyId(),PageRequest.of(0, NavigationConstants.NO_USERS_PAGE));
		 ModelAndView mav = new ModelAndView("newList_user");
		 mav.addObject("users", users.getContent());
		 Map<String, String> queryParams = new HashMap<>();
		 CommonUtil.setCommonAttributesOfPagination(users, mav.getModelMap(), 0, "newListUsers", queryParams);
		List<UserTestSession> sessions= userTestSessionService.findTestListForUser(user.getCompanyId(), email);
		if(sessions==null||sessions.size()==0) {
			User user3=userService.findByPrimaryKey(email, user.getCompanyId());
			userRepository.delete(user3);
			mav.addObject("message", "User deleted successfully");
			mav.addObject("msgtype", "Information");
			mav.addObject("icon", "success");
		}else {
			mav.addObject("message", "User can not be deleted");
			mav.addObject("msgtype", "Information");
			mav.addObject("icon", "warning");
		}
		User usr = new User();
		usr.setCompanyId(user.getCompanyId());
		usr.setCompanyName(user.getCompanyName());
		mav.addObject("usr", usr);
		 return mav;
		}
}
