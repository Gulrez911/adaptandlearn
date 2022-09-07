package com.assessment.web.controllers;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.assessment.common.PropertyConfig;
import com.assessment.data.AdaptiveAssessment;
import com.assessment.data.AssessmentUploadData;
import com.assessment.data.DemoPage;
import com.assessment.data.User;
import com.assessment.repositories.AssessmentUploadDataRepository;
import com.assessment.repositories.DemoPageRepository;
import com.assessment.services.AdaptiveAssessmentService;
import com.assessment.services.AssessmentUploadDataService;
import com.assessment.services.DemoPageService;
import com.assessment.web.dto.DemoColumnDto;
import com.assessment.web.dto.DemoElementDto;
import com.assessment.web.dto.DemoPageDto;
@Controller
public class DemoPageCreationController {
	
	@Autowired
	DemoPageService demoPageService;
	
	@Autowired
	DemoPageRepository demoPageRep;
	
	@Autowired
	AdaptiveAssessmentService adaptiveAssService;
	
	@Autowired	
	AssessmentUploadDataRepository assRep;
	
	@Autowired
	PropertyConfig propertyConfig;
	
	@RequestMapping(value = "/saveDemoPage", method = RequestMethod.POST)
	public ModelAndView saveDemoPage(HttpServletRequest request, HttpServletResponse response, @ModelAttribute("demoPageDto") DemoPageDto demoPageDto) throws Exception {
		User user = (User) request.getSession().getAttribute("user");
		DemoPage demoPage = new DemoPage();
		demoPage.setCompanyId(user.getCompanyId());
		demoPage.setCompanyName(user.getCompanyName());
		demoPage.setPageName(demoPageDto.getDemoPage());
		demoPage.setColumn1Header(demoPageDto.getHeadingColumn1());
		demoPage.setColumn2Header(demoPageDto.getHeadingColumn2());
		demoPage.setColumn3Header(demoPageDto.getHeadingColumn3());
		demoPage.setColumn4Header(demoPageDto.getHeadingColumn4());
		demoPage.setColumn5Header(demoPageDto.getHeadingColumn5());
		
		for(String name: demoPageDto.getSelected1()){
			String jobdescName= name.split("___")[0].trim();
			name = name.substring(name.indexOf("___")+3, name.length());
			String domain = name.split("___")[1].trim();
			
			String adaptiveAssessmentName = "Adaptive Assessment - "+jobdescName;
			AssessmentUploadData data = assRep.findByPrimaryKey(user.getCompanyId(), domain, adaptiveAssessmentName);
			demoPage.getColumn1().add(data);
		}
		
		for(String name: demoPageDto.getSelected2()){
			String jobdescName= name.split("___")[0].trim();
			name = name.substring(name.indexOf("___")+3, name.length());
			String domain = name.split("___")[1].trim();
			String adaptiveAssessmentName = "Adaptive Assessment - "+jobdescName;
			AssessmentUploadData data = assRep.findByPrimaryKey(user.getCompanyId(), domain, adaptiveAssessmentName);
			demoPage.getColumn2().add(data);
		}
		
		for(String name: demoPageDto.getSelected3()){
			String jobdescName= name.split("___")[0].trim();
			name = name.substring(name.indexOf("___")+3, name.length());
			String domain = name.split("___")[1].trim();
			String adaptiveAssessmentName = "Adaptive Assessment - "+jobdescName;
			AssessmentUploadData data = assRep.findByPrimaryKey(user.getCompanyId(), domain, adaptiveAssessmentName);
			demoPage.getColumn3().add(data);
		}
		
		for(String name: demoPageDto.getSelected4()){
			String jobdescName= name.split("___")[0].trim();
			name = name.substring(name.indexOf("___")+3, name.length());
			String domain = name.split("___")[1].trim();
			String adaptiveAssessmentName = "Adaptive Assessment - "+jobdescName;
			AssessmentUploadData data = assRep.findByPrimaryKey(user.getCompanyId(), domain, adaptiveAssessmentName);
			demoPage.getColumn4().add(data);
		}
		
		for(String name: demoPageDto.getSelected5()){
			String jobdescName= name.split("___")[0].trim();
			name = name.substring(name.indexOf("___")+3, name.length());
			String domain = name.split("___")[1].trim();
			String adaptiveAssessmentName = "Adaptive Assessment - "+jobdescName;
			AssessmentUploadData data = assRep.findByPrimaryKey(user.getCompanyId(), domain, adaptiveAssessmentName);
			demoPage.getColumn5().add(data);
		}
		
		
		demoPageService.saveOrUpdate(demoPage);
		return demoPageCreationAndListing(null, "Demo Page Save Success!", request, response);
	}
	
	@RequestMapping(value = "/deleteDemoArea", method = RequestMethod.GET)
	public ModelAndView deleteDemoArea(@RequestParam(name= "id", required = true) Long id, HttpServletRequest request, HttpServletResponse response)
								throws Exception {
		demoPageRep.deleteById(id);
		return demoPageCreationAndListing(null, "Demo Area Deleted", request, response);
	}
	
	
	@RequestMapping(value = "/demoPageCreation", method = RequestMethod.GET)
	public ModelAndView demoPageCreationAndListing(@RequestParam(name= "search", required = false) String search,@RequestParam(name= "msg", required = false) String msg,  HttpServletRequest request, HttpServletResponse response)
								throws Exception {
		//ModelAndView mav = new ModelAndView("automatedAssForDomain");
		User user = (User) request.getSession().getAttribute("user");
			
		List<DemoPage> demoPages;
			if(search == null){
				 demoPages = 	demoPageRep.findByCompanyId(user.getCompanyId());
			}
			else{
				demoPages = demoPageRep.searchDemoPages(user.getCompanyId(), search);
			}
		
		List<DemoPageDto> dtos = new ArrayList<>();
		for(DemoPage demoPage : demoPages){
			DemoPageDto dto = convert(demoPage);
			String demopageId = new String(Base64.getEncoder().encode(String.valueOf(demoPage.getId()).getBytes()));
			dto.setDemoPageLink(propertyConfig.getBaseUrl()+"showDemoAssessments?demoPageId="+demopageId);
			dtos.add(dto);
		}
		ModelAndView mav = new ModelAndView("demoPageCreationAndListing");
		//mav.addObject("data", demoPages);
		mav.addObject("dtos", dtos);
			if(msg != null){
				mav.addObject("message", msg);// later put it as label
				mav.addObject("msgtype", "Information");
			}
		List<AssessmentUploadData> data = 	assRep.findAssessmentsByCompany(user.getCompanyId());
			for(AssessmentUploadData rec: data){
				AdaptiveAssessment adaptiveAssessment = adaptiveAssService.findUniqueAdaptiveAssessment(rec.getCompanyId(), rec.getAdaptiveAssessmentName());
				rec.setSkillOverView(adaptiveAssessment.getCoreSkills());
			}
		mav.addObject("data", data);
		mav.addObject("demoPageDto", new DemoPageDto());
		return mav;
		//mav.addObject("assessments", data);
		//return mav;
	}
	
	private DemoPageDto convert(DemoPage demoPage){
		DemoPageDto dto = new DemoPageDto();
		dto.setId(demoPage.getId());
		dto.setDemoPage(demoPage.getPageName());
		DemoColumnDto column1 = new DemoColumnDto();
		column1.setSkillsOverView("Skills Associated - ");
		DemoColumnDto column2 = new DemoColumnDto();
		column2.setSkillsOverView("Skills Associated - ");
		DemoColumnDto column3 = new DemoColumnDto();
		column3.setSkillsOverView("Skills Associated - ");
		DemoColumnDto column4 = new DemoColumnDto();
		column4.setSkillsOverView("Skills Associated - ");
		DemoColumnDto column5 = new DemoColumnDto();
		column5.setSkillsOverView("Skills Associated - ");
		
		for(AssessmentUploadData data : demoPage.getColumn1()){
			DemoElementDto element = new DemoElementDto();
			AdaptiveAssessment adaptiveAssessment = adaptiveAssService.findUniqueAdaptiveAssessment(data.getCompanyId(), data.getAdaptiveAssessmentName());
			column1.setSkillsOverView(column1.getSkillsOverView()+adaptiveAssessment.getCoreSkills()+", ");
			element.setHeading(data.getJobDescName());
			element.setData(data);
			column1.getElements().add(element);
			
		}
		
		for(AssessmentUploadData data : demoPage.getColumn2()){
			DemoElementDto element = new DemoElementDto();
			AdaptiveAssessment adaptiveAssessment = adaptiveAssService.findUniqueAdaptiveAssessment(data.getCompanyId(), data.getAdaptiveAssessmentName());
			column2.setSkillsOverView(column2.getSkillsOverView()+adaptiveAssessment.getCoreSkills()+", ");
			element.setHeading(data.getJobDescName());
			element.setData(data);
			column2.getElements().add(element);
		}
		
		for(AssessmentUploadData data : demoPage.getColumn3()){
			DemoElementDto element = new DemoElementDto();
			AdaptiveAssessment adaptiveAssessment = adaptiveAssService.findUniqueAdaptiveAssessment(data.getCompanyId(), data.getAdaptiveAssessmentName());
			column3.setSkillsOverView(column3.getSkillsOverView()+adaptiveAssessment.getCoreSkills()+", ");
			element.setHeading(data.getJobDescName());
			element.setData(data);
			column3.getElements().add(element);
		}
		
		for(AssessmentUploadData data : demoPage.getColumn4()){
			DemoElementDto element = new DemoElementDto();
			AdaptiveAssessment adaptiveAssessment = adaptiveAssService.findUniqueAdaptiveAssessment(data.getCompanyId(), data.getAdaptiveAssessmentName());
			column4.setSkillsOverView(column4.getSkillsOverView()+adaptiveAssessment.getCoreSkills()+", ");
			element.setHeading(data.getJobDescName());
			element.setData(data);
			column4.getElements().add(element);
		}
		
		for(AssessmentUploadData data : demoPage.getColumn5()){
			DemoElementDto element = new DemoElementDto();
			AdaptiveAssessment adaptiveAssessment = adaptiveAssService.findUniqueAdaptiveAssessment(data.getCompanyId(), data.getAdaptiveAssessmentName());
			column5.setSkillsOverView(column5.getSkillsOverView()+adaptiveAssessment.getCoreSkills()+", ");
			element.setHeading(data.getJobDescName());
			element.setData(data);
			column5.getElements().add(element);
		}
		
		
		column1.setSkillsOverView(column1.getSkillsOverView().trim().substring(0, column1.getSkillsOverView().length()-1));
		column2.setSkillsOverView(column2.getSkillsOverView().trim().substring(0, column2.getSkillsOverView().length()-1));
		column3.setSkillsOverView(column3.getSkillsOverView().trim().substring(0, column3.getSkillsOverView().length()-1));
		column4.setSkillsOverView(column4.getSkillsOverView().trim().substring(0, column4.getSkillsOverView().length()-1));
		column5.setSkillsOverView(column5.getSkillsOverView().trim().substring(0, column5.getSkillsOverView().length()-1));
		
		column1.setHeading(demoPage.getColumn1Header());
		column2.setHeading(demoPage.getColumn2Header());
		column3.setHeading(demoPage.getColumn3Header());
		column4.setHeading(demoPage.getColumn4Header());
		column5.setHeading(demoPage.getColumn5Header());
		
		
		dto.getColumns().add(column1);
		dto.getColumns().add(column2);
		dto.getColumns().add(column3);
		dto.getColumns().add(column4);
		dto.getColumns().add(column5);
		return dto;
	}
	
	@RequestMapping(value = "/showDemoAssessments", method = RequestMethod.GET)
	public ModelAndView showDemoAssessments(@RequestParam(name= "demoPageId", required = true) String demoPageId, HttpServletRequest request, HttpServletResponse response)
								throws Exception {
		Long id = Long.parseLong(new String(Base64.getDecoder().decode(demoPageId.getBytes())));
		DemoPage demoPage = demoPageRep.findById(id).get();
		ModelAndView mav = new ModelAndView("demoPageAreaGeneral");
		mav.addObject("demoPage", demoPage);
		return mav;
	}
}
