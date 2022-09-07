package com.assessment.web.dto.newlookandfeel;

import org.springframework.data.domain.Page;

import com.assessment.common.ApplicationConstants;

public class BaseCollectionDto {

	public Integer page;
	
	public Integer maxPages;
	
	Integer recordsFrom;
	
	public Integer getRecordsFrom() {
		return recordsFrom;
	}
	public void setRecordsFrom(Integer recordsFrom) {
		this.recordsFrom = recordsFrom;
	}


	Integer recordsTo;
	
	Long totalNumberOfRecords;
	
	Integer totalNumberOfPages;
	
	Integer selectedPage;
	
	Integer previousPage;
	
	Integer nextPage;
	
	Integer recordsPerPage;
	
	Boolean showPreviousPage;
	
	Boolean showNextPage;
	
	String callingMethod;
	
	
	
	
	
	public Integer getRecordsTo() {
		return recordsTo;
	}
	public void setRecordsTo(Integer recordsTo) {
		this.recordsTo = recordsTo;
	}
	
	
	
	public Long getTotalNumberOfRecords() {
		return totalNumberOfRecords;
	}
	public void setTotalNumberOfRecords(Long totalNumberOfRecords) {
		this.totalNumberOfRecords = totalNumberOfRecords;
	}
	public Integer getTotalNumberOfPages() {
		return totalNumberOfPages;
	}
	public void setTotalNumberOfPages(Integer totalNumberOfPages) {
		this.totalNumberOfPages = totalNumberOfPages;
	}
	public Integer getSelectedPage() {
		return selectedPage;
	}
	public void setSelectedPage(Integer selectedPage) {
		this.selectedPage = selectedPage;
	}
	public Integer getPreviousPage() {
		return previousPage;
	}
	public void setPreviousPage(Integer previousPage) {
		this.previousPage = previousPage;
	}
	public Integer getNextPage() {
		return nextPage;
	}
	public void setNextPage(Integer nextPage) {
		this.nextPage = nextPage;
	}
	public Integer getRecordsPerPage() {
		return recordsPerPage;
	}
	public void setRecordsPerPage(Integer recordsPerPage) {
		this.recordsPerPage = recordsPerPage;
	}
	public Boolean getShowPreviousPage() {
		return showPreviousPage;
	}
	public void setShowPreviousPage(Boolean showPreviousPage) {
		this.showPreviousPage = showPreviousPage;
	}
	public Boolean getShowNextPage() {
		return showNextPage;
	}
	public void setShowNextPage(Boolean showNextPage) {
		this.showNextPage = showNextPage;
	}
	public String getCallingMethod() {
		return callingMethod;
	}
	public void setCallingMethod(String callingMethod) {
		this.callingMethod = callingMethod;
	}
	public Integer getPage() {
		return page;
	}
	public void setPage(Integer page) {
		this.page = page;
	}
	public Integer getMaxPages() {
		return maxPages;
	}
	public void setMaxPages(Integer maxPages) {
		this.maxPages = maxPages;
	}
	
	
	public void setCommonAttributesOfPagination(Page<?> paginationObject, int pageNumber, String callingMethod) {
		//modelMap.put("recordsFrom", paginationObject.getNumber());
		
		setRecordsFrom(paginationObject.getNumber());
		
		//modelMap.put("recordsTo", paginationObject.getNumberOfElements());
		setRecordsTo(paginationObject.getNumberOfElements());
		
		
		//modelMap.put("totalNumberOfRecords", paginationObject.getTotalElements());
		setTotalNumberOfRecords(paginationObject.getTotalElements());
		
		
		//modelMap.put("totalNumberOfPages", paginationObject.getTotalPages());
		setTotalNumberOfPages(paginationObject.getTotalPages());
		
		//modelMap.put("selectedPage", pageNumber + 1);
		setSelectedPage(pageNumber + 1);
		
		//modelMap.put("previousPage", pageNumber - 1);
		setPreviousPage(pageNumber - 1);
		
		//modelMap.put("nextPage", pageNumber + 1);
		setNextPage(pageNumber + 1);
		
		
		//modelMap.put("recordsPerPage", ApplicationConstants.NUMBER_OF_RECORDS_PER_PAGE);
		setRecordsPerPage(ApplicationConstants.NUMBER_OF_RECORDS_PER_PAGE);
		
		//modelMap.put("showPreviousPage", true);
		setShowPreviousPage(true);
		
		//modelMap.put("showNextPage", true);
		setShowNextPage(true);
		
		
		
		
		if (pageNumber == 0) {
			//modelMap.put("showPreviousPage", false);
			setShowPreviousPage(false);
		}
		if (pageNumber + 1 == paginationObject.getTotalPages()) {
			setShowNextPage(false);
		}
		
		if(paginationObject.getTotalPages() == 0){
			//modelMap.put("showNextPage", false);
			setShowNextPage(false);
		}
	}
}
