package com.assessment.web.controllers;

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
import org.springframework.web.servlet.ModelAndView;

import com.assessment.common.CommonUtil;
import com.assessment.common.util.NavigationConstants;
import com.assessment.data.Tenant;
import com.assessment.data.User;
import com.assessment.repositories.TenantRepository;
import com.assessment.services.TenantService;

@Controller
public class NewTenentController {

	@Autowired
	TenantService tenantService;
	
	@Autowired
	TenantRepository tenantRepository;
	
	@Autowired
	UserController userController;
	
	@RequestMapping(value = "/newListTenants", method = RequestMethod.GET)
	public ModelAndView newListTenants(@RequestParam(name= "page", required = false) Integer pageNumber, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mav = new ModelAndView("newList_tenant");
		if(pageNumber == null) {
			pageNumber = 0;
		}
		Page<Tenant> tenants = tenantRepository.findAllTenant(PageRequest.of(pageNumber, NavigationConstants.NO_TENANT_PAGE));
		mav.addObject("tenant", new Tenant());
		mav.addObject("tenants", tenants.getContent());
		mav.addObject("tenant", new Tenant());
		CommonUtil.setCommonAttributesOfPagination(tenants, mav.getModelMap(), pageNumber, "newListTenants", null);
		return mav;
	}
	
	@RequestMapping(value = "/newSaveTenant", method = RequestMethod.POST)
	public ModelAndView newSaveTenant(HttpServletRequest request, HttpServletResponse response, @ModelAttribute("tenant") Tenant tenant) throws Exception {
		ModelAndView mav = null;
		User user = (User) request.getSession().getAttribute("user");
		try {
			userController.setUpTenant(tenant.getSpoc(), tenant.getCompanyId(), tenant.getCompanyName(), tenant.getSpocPassword(), response, request);
		} catch (Exception e) {
			mav = new ModelAndView("newList_tenant");
			Page<Tenant> tenants = tenantService.findAllTenants(0);
			mav.addObject("tenants", tenants.getContent());
			mav.addObject("tenant", tenant);
			mav.addObject("message", "Looks like underlying schema or database user for this tenant - "+tenant.getCompanyId()+" has already been created. Contact Admin for further support.");// later put it as label
			mav.addObject("msgtype", "warning");
			CommonUtil.setCommonAttributesOfPagination(tenants, mav.getModelMap(), 0, "newListTenants", null);
			return mav;
		}
		//Tenant tenant = new Tenant();
		tenant.setCompanyId(tenant.getCompanyId());
		tenant.setCompanyName(tenant.getCompanyName());
		tenant.setSpoc(tenant.getSpoc());
		tenant.setDatabaseSchema(tenant.getCompanyId());
		tenant.setSchemaUser("'User_"+tenant.getCompanyId());
		tenantService.saveOrUpdate(tenant);
		return newListTenants(0, request, response);
	}
}
