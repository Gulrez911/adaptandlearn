package com.assessment.web.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.Cluster;
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
import com.assessment.data.CheCluster;
import com.assessment.data.User;
import com.assessment.repositories.CheClusterRepository;
import com.assessment.services.CheService;

@Controller
public class NewCheClusterController {

	@Autowired
	CheService cheService;
	
	@Autowired
	CheClusterRepository clusterRep;
	
	@RequestMapping(value = "/newShowClusters", method = RequestMethod.GET)
	public ModelAndView newShowClusters(@RequestParam(name= "page", required = false) Integer pageNumber,HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mav = new ModelAndView("newClusters");
		User user = (User) request.getSession().getAttribute("user");
		 if(pageNumber == null){
				pageNumber = 0;
		 }
		 Page<CheCluster> cheClusters = clusterRep.findClustersByCompanyId(user.getCompanyId(),PageRequest.of(pageNumber, NavigationConstants.NO_CLUSTERS_PAGE));
		 CheCluster cluster = new CheCluster();
		 mav.addObject("cluster", cluster);
		mav.addObject("clusters", cheClusters.getContent());
		 Map<String, String> queryParams = new HashMap<>();
		 CommonUtil.setCommonAttributesOfPagination(cheClusters, mav.getModelMap(), pageNumber, "newShowClusters", queryParams);
		return mav;
	}
	
	@RequestMapping(value = "/editCluster", method = RequestMethod.GET)
	@ResponseBody
	public Map<String,Object> newAddCluster(@RequestParam(name= "clusterId", required = false) Long clusterId, HttpServletRequest request, HttpServletResponse response) {
		 Map<String,Object>  map = new HashMap<>();
		 CheCluster	cluster = clusterRep.findById(clusterId).get();
		 map.put("cluster", cluster);
		 return map;
	}
	
	
	@RequestMapping(value = "/newSaveCluster", method = RequestMethod.POST)
	  public ModelAndView newSaveCluster(HttpServletRequest request, HttpServletResponse response,@ModelAttribute("cluster") CheCluster cluster) {
		User user = (User) request.getSession().getAttribute("user");
		ModelAndView mav = new ModelAndView("newClusters");
		cluster.setCompanyId(user.getCompanyId());
		cluster.setCompanyName(user.getCompanyName());
		cheService.saveOrUpdate(cluster);
		 Page<CheCluster> cheClusters = clusterRep.findClustersByCompanyId(user.getCompanyId(),PageRequest.of(0, NavigationConstants.NO_CLUSTERS_PAGE));
		 CheCluster cluster2 = new CheCluster();
		 mav.addObject("cluster", cluster2);
		mav.addObject("clusters", cheClusters.getContent());
		 Map<String, String> queryParams = new HashMap<>();
		 CommonUtil.setCommonAttributesOfPagination(cheClusters, mav.getModelMap(), 0, "newShowClusters", queryParams);
		mav.addObject("clusters", cheClusters.getContent());
		mav.addObject("message", "Cluster Info "+cluster.getClusterName()+" saved" );// later put it as label
		mav.addObject("msgtype", "success");
		mav.addObject("icon", "success");
		return mav;
	}
	
	@RequestMapping(value = "/newDeleteCluster", method = RequestMethod.GET)
	  public ModelAndView newDeleteCluster(@RequestParam(name= "clusterId", required = true) Long clusterId, HttpServletRequest request, HttpServletResponse response) {
		User user = (User) request.getSession().getAttribute("user");
		clusterRep.deleteById(clusterId);
		ModelAndView mav = new ModelAndView("newClusters");
		 Page<CheCluster> cheClusters = clusterRep.findClustersByCompanyId(user.getCompanyId(),PageRequest.of(0, NavigationConstants.NO_CLUSTERS_PAGE));
		 CheCluster cluster2 = new CheCluster();
		 mav.addObject("cluster", cluster2);
		mav.addObject("clusters", cheClusters.getContent());
		 Map<String, String> queryParams = new HashMap<>();
		 CommonUtil.setCommonAttributesOfPagination(cheClusters, mav.getModelMap(), 0, "newShowClusters", queryParams);
		mav.addObject("message", "Cluster deleted" );// later put it as label
		mav.addObject("msgtype", "success");
		mav.addObject("icon", "success");
		return mav;
	}
	
	 @RequestMapping(value = "/newSearchClusters", method = RequestMethod.GET)
	  public ModelAndView newSearchClusters(@RequestParam(name= "page", required = false) Integer pageNumber,@RequestParam String searchText, HttpServletResponse response, HttpServletRequest request ) throws Exception {
		 User user = (User) request.getSession().getAttribute("user");
		 if(pageNumber == null){
			 pageNumber = 0;
		 }
		 Page<CheCluster> cheClusters  = clusterRep.searchClusters(user.getCompanyId(), searchText,PageRequest.of(pageNumber, NavigationConstants.NO_CLUSTERS_PAGE));
		 ModelAndView mav = new ModelAndView("newClusters");
		 CheCluster cluster2 = new CheCluster();
		 mav.addObject("cluster", cluster2);
		mav.addObject("clusters", cheClusters.getContent());
		 Map<String, String> queryParams = new HashMap<>();
		queryParams.put("searchText", searchText);
		 CommonUtil.setCommonAttributesOfPagination(cheClusters, mav.getModelMap(), pageNumber, "newSearchClusters", queryParams);
		 return mav;
		}
}
