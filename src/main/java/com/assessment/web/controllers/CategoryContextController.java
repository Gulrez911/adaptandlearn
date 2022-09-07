package com.assessment.web.controllers;

import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.assessment.common.CommonUtil;
import com.assessment.common.ExcelReader;
import com.assessment.common.PropertyConfig;
import com.assessment.common.util.EmailGenericMessageThread;
import com.assessment.data.AssessmentUploadData;
import com.assessment.data.CategoryContext;
import com.assessment.data.CrawlerTopic;
import com.assessment.data.Test;
import com.assessment.data.User;
import com.assessment.repositories.AssessmentUploadDataRepository;
import com.assessment.repositories.CategoryContextRepository;
import com.assessment.services.AssessmentUploadDataService;
import com.assessment.services.CategoryContextService;
import com.assessment.services.TestService;
import com.assessment.web.dto.AssessmentExcelDto;
import com.assessment.web.dto.Level1ContainerDto;
import com.assessment.web.dto.Level2ContainerDto;
import com.assessment.web.dto.Level3ContainerDto;
import com.assessment.web.dto.LevelDto;

@Controller
public class CategoryContextController {

	@Autowired
	CategoryContextService categoryContextService;
	
	@Autowired
	CategoryContextRepository categoryContextRepository;
	
	@Autowired
	AssessmentUploadDataService assessmentUploadDataService;
	
	@Autowired
	TestService testservice;
	
	@Autowired
	AssessmentUploadDataRepository uploadRep;
	
	@Autowired
	PropertyConfig propertyConfig;
	
	//private List<String> get
	
	@RequestMapping(value = "/landing", method = RequestMethod.GET)
	public ModelAndView landing(  HttpServletRequest request, HttpServletResponse response){
		ModelAndView mav = new ModelAndView("newhome");
		String companyId = propertyConfig.getDefaultCompanyId();
		if(companyId == null || companyId.trim().length() == 0){
			companyId = "AL";
		}
		List<String> level1Cateories = distinctlevel1(request, response);
		//mav.addObject("level1Categories", level1Cateories);
		List<Level1ContainerDto> level1Containers = new ArrayList<>();
			for(String level1 : level1Cateories){
				List<String> level2Categories = distinctlevel2(level1, request, response);
				Level1ContainerDto level1Container = new Level1ContainerDto();
				level1Container.setLevel1(level1);
				level1Containers.add(level1Container);
				List<Level2ContainerDto> level2Containers = new ArrayList<>();
				int count =0;
				for(String level2 : level2Categories){
					Level2ContainerDto level2Container = new Level2ContainerDto();
					level2Container.setLevel2(level2);
					level2Containers.add(level2Container);
					
					
					count ++;
						if(count <= 1){
							List<String> level3Categories = distinctlevel3(level1, level2, request, response);
							List<Level3ContainerDto> level3Containers = new ArrayList<>();
							for(int i=0;i<level3Categories.size();i++){
								Level3ContainerDto level3Container = new Level3ContainerDto();
								level3Container.setLevel3(level3Categories.get(i));
								level3Containers.add(level3Container);
								
							}
							//level2Container.getLevel3containers().addAll(level3Containers);	
							mav.addObject("level3Containers", level3Containers);
						}
					
						
					
				}
				level1Container.getLevel2Containers().addAll(level2Containers);
			}
			mav.addObject("level1Containers", level1Containers);
		return mav;
	}
	
	@RequestMapping(value = "/landing2", method = RequestMethod.GET)
	public ModelAndView landing2(  HttpServletRequest request, HttpServletResponse response){
		ModelAndView mav = new ModelAndView("newhome");
		String companyId = propertyConfig.getDefaultCompanyId();
		if(companyId == null || companyId.trim().length() == 0){
			companyId = "AL";
		}
		List<LevelDto> level1Cateories = distinctlevel1ID(request, response);
		//mav.addObject("level1Categories", level1Cateories);
		List<Level1ContainerDto> level1Containers = new ArrayList<>();
			for(LevelDto level1 : level1Cateories){
				List<LevelDto> level2Categories = distinctlevel2ID(level1.getLevel(), request, response);
				Level1ContainerDto level1Container = new Level1ContainerDto();
				level1Container.setLevel1(level1.getLevel());
				
				level1Container.setId(level1.getId());
				level1Containers.add(level1Container);
				List<Level2ContainerDto> level2Containers = new ArrayList<>();
				int count =0;
				for(LevelDto level2 : level2Categories){
					Level2ContainerDto level2Container = new Level2ContainerDto();
					level2Container.setLevel2(level2.getLevel());
					level2Container.setId(level2.getId());
					level2Containers.add(level2Container);
					
					
					count ++;
						if(count <= 1){
							List<LevelDto> level3Categories = distinctlevel3ID(level1.getLevel(), level2.getLevel(), request, response);
							List<Level3ContainerDto> level3Containers = new ArrayList<>();
							for(int i=0;i<level3Categories.size();i++){
								Level3ContainerDto level3Container = new Level3ContainerDto();
								level3Container.setLevel3(level3Categories.get(i).getLevel());
								level3Container.setId(level3Categories.get(i).getId());
								level3Containers.add(level3Container);
								
							}
							//level2Container.getLevel3containers().addAll(level3Containers);	
								if(mav.getModel().get("level3Containers") == null){
									mav.addObject("level3Containers", level3Containers);
								}
							
						}
					
						
					
				}
				level1Container.getLevel2Containers().addAll(level2Containers);
			}
			mav.addObject("level1Containers", level1Containers);
		return mav;
	}
	
	
	
	@RequestMapping(value = "/distinctlevel1", method = RequestMethod.GET)
	public @ResponseBody List<String> distinctlevel1(  HttpServletRequest request, HttpServletResponse response){
		String companyId = propertyConfig.getDefaultCompanyId();
		if(companyId == null || companyId.trim().length() == 0){
			companyId = "AL";
		}
		
		List<String> level1 = categoryContextRepository.findParentCategoriesLevel1(companyId);
		return level1;
	}
	
	@RequestMapping(value = "/distinctlevel2", method = RequestMethod.GET)
	public @ResponseBody List<String> distinctlevel2(@RequestParam String level1,   HttpServletRequest request, HttpServletResponse response){
		String companyId = propertyConfig.getDefaultCompanyId();
		if(companyId == null || companyId.trim().length() == 0){
			companyId = "AL";
		}
		
		List<String> level2 = categoryContextRepository.findLevel2Categories(companyId, level1);
		return level2;
	}
	
	@RequestMapping(value = "/distinctlevel3", method = RequestMethod.GET)
	public @ResponseBody List<String> distinctlevel3(@RequestParam String level1,@RequestParam String level2,   HttpServletRequest request, HttpServletResponse response){
		String companyId = propertyConfig.getDefaultCompanyId();
		if(companyId == null || companyId.trim().length() == 0){
			companyId = "AL";
		}
		
		List<String> level3 = categoryContextRepository.findLevel3Categories(companyId, level1, level2);
		return level3;
	}
	
	@RequestMapping(value = "/distinctlevel3ById", method = RequestMethod.GET)
	public @ResponseBody List<LevelDto> distinctlevel3ById(@RequestParam Long l1id,@RequestParam Long l2id,   HttpServletRequest request, HttpServletResponse response){
		String companyId = propertyConfig.getDefaultCompanyId();
		if(companyId == null || companyId.trim().length() == 0){
			companyId = "AL";
		}
		
		CategoryContext l1 = categoryContextRepository.findById(l1id).get();
		CategoryContext l2 = categoryContextRepository.findById(l2id).get();
		System.out.println(l1.getLevel1()+" -- "+l2.getLevel2());
		List<LevelDto> level3 = categoryContextRepository.findLevel3CategoriesWithId(companyId, l1.getLevel1(), l2.getLevel2());
		return level3;
	}
	
	@RequestMapping(value = "/level4", method = RequestMethod.GET)
	public @ResponseBody List<String> level4(@RequestParam String level1,@RequestParam String level2, @RequestParam String level3,   HttpServletRequest request, HttpServletResponse response){
		String companyId = propertyConfig.getDefaultCompanyId();
		if(companyId == null || companyId.trim().length() == 0){
			companyId = "AL";
		}
		
		List<String> level4 = categoryContextRepository.findLevel4Categories(companyId, level1, level2, level3);
		return level4;
	}
	
	@RequestMapping(value = "/level4ById", method = RequestMethod.GET)
	public @ResponseBody List<LevelDto> level4ById(@RequestParam Long l1id,@RequestParam Long l2id, @RequestParam Long l3id,   HttpServletRequest request, HttpServletResponse response){
		String companyId = propertyConfig.getDefaultCompanyId();
		if(companyId == null || companyId.trim().length() == 0){
			companyId = "AL";
		}
		CategoryContext l1 = categoryContextRepository.findById(l1id).get();
		CategoryContext l2 = categoryContextRepository.findById(l2id).get();
		CategoryContext l3 = categoryContextRepository.findById(l3id).get();
		System.out.println(l1.getLevel1()+" -- "+l2.getLevel2()+"-- "+l3.getLevel3());
		List<LevelDto> level4 = categoryContextRepository.findLevel4CategoriesWithId(companyId, l1.getLevel1(), l2.getLevel2(), l3.getLevel3());
		return level4;
	}
	
	@RequestMapping(value = "/distinctlevel1ID", method = RequestMethod.GET)
	public @ResponseBody List<LevelDto> distinctlevel1ID(  HttpServletRequest request, HttpServletResponse response){
		String companyId = propertyConfig.getDefaultCompanyId();
		if(companyId == null || companyId.trim().length() == 0){
			companyId = "AL";
		}
		
		List<LevelDto> level1 = categoryContextRepository.findParentCategoriesLevel1WithId(companyId);
		return level1;
	}
	
	@RequestMapping(value = "/distinctlevel2ID", method = RequestMethod.GET)
	public @ResponseBody List<LevelDto> distinctlevel2ID(@RequestParam String level1,   HttpServletRequest request, HttpServletResponse response){
		String companyId = propertyConfig.getDefaultCompanyId();
		if(companyId == null || companyId.trim().length() == 0){
			companyId = "AL";
		}
		
		List<LevelDto> level2 = categoryContextRepository.findLevel2CategoriesWithId(companyId, level1);
		return level2;
	}
	
	@RequestMapping(value = "/distinctlevel3Id", method = RequestMethod.GET)
	public @ResponseBody List<LevelDto> distinctlevel3ID(@RequestParam String level1,@RequestParam String level2,   HttpServletRequest request, HttpServletResponse response){
		String companyId = propertyConfig.getDefaultCompanyId();
		if(companyId == null || companyId.trim().length() == 0){
			companyId = "AL";
		}
		
		List<LevelDto> level3 = categoryContextRepository.findLevel3CategoriesWithId(companyId, level1, level2);
		return level3;
	}
	
	
	@RequestMapping(value = "/categoryUI", method = RequestMethod.GET)
	public ModelAndView categoryUI(@RequestParam(name= "collective", required = false) Boolean collective,@RequestParam(name= "page", required = false) Integer pageNumber,@RequestParam(name= "msg", required = false) String msg,  HttpServletRequest request, HttpServletResponse response)
								throws Exception {
		//ModelAndView mav = new ModelAndView("automatedAssForDomain");
		User user = (User) request.getSession().getAttribute("user");
		
		List<CategoryContext> categories = new ArrayList<CategoryContext>();
		
		if(pageNumber == null) {
			pageNumber = 0;
		}
		org.springframework.data.domain.Page<CategoryContext> categoriesPage = null;
		
			if(collective == null){
				collective = true;
				categoriesPage = categoryContextRepository.findCategoriesForAssUploadData(user.getCompanyId(), PageRequest.of(pageNumber, 30));
				categories = categoriesPage.getContent();
			}
			else{
				categoriesPage = categoryContextRepository.findCategoriesForAss(user.getCompanyId(), PageRequest.of(pageNumber, 30));
				categories = categoriesPage.getContent();
			}
		
		ModelAndView mav = new ModelAndView("categoryUI");
		mav.addObject("categories", categories);
	
			if(msg != null){
				mav.addObject("msgtype", "Information");
				mav.addObject("message", msg);
				mav.addObject("icon", "Information");
			}
		
		CommonUtil.setCommonAttributesOfPagination(categoriesPage, mav.getModelMap(), pageNumber, "categoryUI", null);
		return mav;
	}
	
	@RequestMapping(value = "/deleteCategory", method = RequestMethod.GET)
	public ModelAndView deleteCategory(@RequestParam(name= "cid", required = true) Long cid,  HttpServletRequest request, HttpServletResponse response)
								throws Exception {
		//ModelAndView mav = new ModelAndView("automatedAssForDomain");
		User user = (User) request.getSession().getAttribute("user");
		CategoryContext context = categoryContextRepository.findById(cid).get();
		categoryContextRepository.deleteById(cid);
		String lev1 = context.getLevel1()== null?"###":context.getLevel1();
		String lev2 = context.getLevel2() == null?"###":context.getLevel2();
		String lev3 = context.getLevel3() == null?"###":context.getLevel3();
		String lev4 = context.getLevel4() == null?"###":context.getLevel4();
		return categoryUI(true, 0, "Category "+context.getLevel1()+"-"+context.getLevel2()+"-"+context.getLevel3()+"-"+context.getLevel4()+" deleted", request, response);
	}
	
	
	@RequestMapping(value = "/getAssUploadDataRecordsNotInCategory", method = RequestMethod.GET)
	public @ResponseBody List<String> getAssUploadDataRecordsNotInCategory(@RequestParam(name= "companyId") String companyId,HttpServletResponse response, HttpServletRequest request) throws Exception {
		List<String> ret = new ArrayList<>();
		List<AssessmentUploadData> data = uploadRep.findAssessmentsByDomain(companyId, "WebCrawl_1");
		File file = new File("assUploadDataNamesNotInCategories.txt");
			for(AssessmentUploadData d : data){
				List<CategoryContext> c = categoryContextRepository.findByAssUploadDataName(companyId, d.getJobDescName());
					if(c == null || c.size()==0){
						FileUtils.write(file, d.getJobDescName()+"\n", true);
						ret.add(d.getJobDescName());
					}
			}
		
		return ret;
		
	}
	
	@RequestMapping(value = "/uploadUIContextMenuItemsCollective", method = RequestMethod.POST)
	public @ResponseBody String uploadUIContextMenuItems(@RequestParam(name= "domain", required=false) String domain, HttpServletResponse response, MultipartHttpServletRequest request) throws Exception {
		try {
			User user = (User) request.getSession().getAttribute("user");
			MultipartFile multipartFile = request.getFile("fileContextCollective");
			Long size = multipartFile.getSize();
			String fileName = multipartFile.getName();
			String contentType = multipartFile.getContentType();
			InputStream stream = multipartFile.getInputStream();
			File file = ResourceUtils.getFile("classpath:uiContext.xml");
			List<CategoryContext> records = ExcelReader.parseExcelFileToBeans(stream, file);
			File wrongNamesFile = new File("wrongNamesFile_AssUploadData.txt");
			String ret = "";
				for(CategoryContext context : records){
					context.setCollective(true);
					context.setCompanyId(user.getCompanyId());
					context.setCompanyName(user.getCompanyName());
					String jobDescName = context.getAssessmentUpoloadDataName();
						if(jobDescName == null || jobDescName.trim().length() == 0){
							return "failure - Assessment Upload Data name not present";
						}
					//AssessmentUploadData data = assessmentUploadDataService.findByPrimaryKey(user.getCompanyId(), "WebCrawl_1", context.getAssessmentUpoloadDataName());
						if(domain == null || domain.trim().length() == 0){
							domain = "WebCrawl_1";
						}
						AssessmentUploadData data = uploadRep.findByPrimaryKey2(user.getCompanyId(), domain,  context.getAssessmentUpoloadDataName());
						System.out.println(user.getCompanyId()+" - WebCrawl_1 - "+context.getAssessmentUpoloadDataName());
						if(data == null){
							String lev1 = context.getLevel1()== null?"###":context.getLevel1();
							String lev2 = context.getLevel2() == null?"###":context.getLevel2();
							String lev3 = context.getLevel3() == null?"###":context.getLevel3();
							String lev4 = context.getLevel4() == null?"###":context.getLevel4();
							ret += context.getAssessmentUpoloadDataName()+"\n";
							FileUtils.write(wrongNamesFile, "Invalid AssessmentUploadData name "+(lev1+"-"+lev2+"-"+lev3+"-"+lev4)+": "+context.getAssessmentUpoloadDataName()+"\n", true);
						}
						else{
							categoryContextService.saveOrUpdate(context);
						}
				}
//				EmailGenericMessageThread email = new EmailGenericMessageThread("tikamsingh9768@gmail.com", "Missing Records", ret, propertyConfig);
//				Thread th = new Thread(email);
//				th.start();
				FileUtils.write(new File("complete_AssUploadData.txt"), "complete "+(new Date().toString()));

		}
		catch(Exception e){
			e.printStackTrace();
			return "fail - "+e.getMessage();
		}
		return "ok";
	}
	
	
	@RequestMapping(value = "/uploadUIContextMenuItemsIndividual", method = RequestMethod.POST)
	public @ResponseBody String uploadUIContextMenuItemsAssessments(HttpServletResponse response, MultipartHttpServletRequest request) throws Exception {
		try {
			User user = (User) request.getSession().getAttribute("user");
			MultipartFile multipartFile = request.getFile("fileContextIndividual");
			Long size = multipartFile.getSize();
			String fileName = multipartFile.getName();
			String contentType = multipartFile.getContentType();
			InputStream stream = multipartFile.getInputStream();
			File file = ResourceUtils.getFile("classpath:uiContext_ass.xml");
			List<CategoryContext> records = ExcelReader.parseExcelFileToBeans(stream, file);
			File wrongNamesFile = new File("wrongNamesFile_AssUploadData.txt");
				for(CategoryContext context : records){
					context.setCollective(false);
					String assName = context.getAssessmentName();
						if(assName == null || assName.trim().length() == 0){
							return "failure - Assessment Upload Data name not present";
						}
					Test data = testservice.findbyTest(context.getAssessmentName(), user.getCompanyId());
						if(data == null){
							String lev1 = context.getLevel1()== null?"###":context.getLevel1();
							String lev2 = context.getLevel2() == null?"###":context.getLevel2();
							String lev3 = context.getLevel3() == null?"###":context.getLevel3();
							String lev4 = context.getLevel4() == null?"###":context.getLevel4();
							
							FileUtils.write(wrongNamesFile, "Invalid Assessment name "+(lev1+"-"+lev2+"-"+lev3+"-"+lev4)+": "+context.getAssessmentName()+"\n", true);
						}
						else{
							categoryContextService.saveOrUpdate(context);
						}
				}
				FileUtils.write(new File("complete_AssUploadData.txt"), "complete "+(new Date().toString()));

		}
		catch(Exception e){
			
		}
		return "ok";
	}
}
