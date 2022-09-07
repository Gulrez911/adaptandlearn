package com.assessment.web.dto;

import java.util.ArrayList;
import java.util.List;

import com.assessment.data.License;

public class LicenseModuleDTO {
	
	License license;
	
	String styleClass;
	
	List<ModuleDTO> modules = new ArrayList<ModuleDTO>();

	public License getLicense() {
		return license;
	}

	public void setLicense(License license) {
		this.license = license;
	}

	public String getStyleClass() {
		return styleClass;
	}

	public void setStyleClass(String styleClass) {
		this.styleClass = styleClass;
	}

	public List<ModuleDTO> getModules() {
		return modules;
	}

	public void setModules(List<ModuleDTO> modules) {
		this.modules = modules;
	}
	
	
	

}
