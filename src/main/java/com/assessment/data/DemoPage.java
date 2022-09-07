package com.assessment.data;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

@Entity
public class DemoPage extends Base{
	
	
	String pageName;
	
	String column1Header;
	String column2Header;
	String column3Header;
	String column4Header;
	String column5Header;
	
	@ManyToMany
	@LazyCollection(LazyCollectionOption.FALSE)
	 @JoinTable(
	            name="DemoPage_Column1",
	            joinColumns = @JoinColumn( name="demopage_id"),
	            inverseJoinColumns = @JoinColumn( name="column1_assUploadData_id")
	        )
	List<AssessmentUploadData> column1 = new ArrayList<AssessmentUploadData>();
	
	@ManyToMany
	@LazyCollection(LazyCollectionOption.FALSE)
	 @JoinTable(
	            name="DemoPage_Column2",
	            joinColumns = @JoinColumn( name="demopage_id"),
	            inverseJoinColumns = @JoinColumn( name="column2_assUploadData_id")
	        )
	List<AssessmentUploadData> column2 = new ArrayList<AssessmentUploadData>();
	
	
	@ManyToMany
	@LazyCollection(LazyCollectionOption.FALSE)
	 @JoinTable(
	            name="DemoPage_Column3",
	            joinColumns = @JoinColumn( name="demopage_id"),
	            inverseJoinColumns = @JoinColumn( name="column3_assUploadData_id")
	        )
	List<AssessmentUploadData> column3 = new ArrayList<AssessmentUploadData>();
	
	@ManyToMany
	@LazyCollection(LazyCollectionOption.FALSE)
	 @JoinTable(
	            name="DemoPage_Column4",
	            joinColumns = @JoinColumn( name="demopage_id"),
	            inverseJoinColumns = @JoinColumn( name="column4_assUploadData_id")
	        )
	List<AssessmentUploadData> column4 = new ArrayList<AssessmentUploadData>();
	
	@ManyToMany
	@LazyCollection(LazyCollectionOption.FALSE)
	 @JoinTable(
	            name="DemoPage_Column5",
	            joinColumns = @JoinColumn( name="demopage_id"),
	            inverseJoinColumns = @JoinColumn( name="column5_assUploadData_id")
	        )
	List<AssessmentUploadData> column5= new ArrayList<AssessmentUploadData>();

	public String getPageName() {
		return pageName;
	}

	public void setPageName(String pageName) {
		this.pageName = pageName;
	}

	

	public List<AssessmentUploadData> getColumn1() {
		return column1;
	}

	public void setColumn1(List<AssessmentUploadData> column1) {
		this.column1 = column1;
	}

	public List<AssessmentUploadData> getColumn2() {
		return column2;
	}

	public void setColumn2(List<AssessmentUploadData> column2) {
		this.column2 = column2;
	}

	public List<AssessmentUploadData> getColumn3() {
		return column3;
	}

	public void setColumn3(List<AssessmentUploadData> column3) {
		this.column3 = column3;
	}

	public List<AssessmentUploadData> getColumn4() {
		return column4;
	}

	public void setColumn4(List<AssessmentUploadData> column4) {
		this.column4 = column4;
	}

	public List<AssessmentUploadData> getColumn5() {
		return column5;
	}

	public void setColumn5(List<AssessmentUploadData> column5) {
		this.column5 = column5;
	}

	public String getColumn1Header() {
		return column1Header;
	}

	public void setColumn1Header(String column1Header) {
		this.column1Header = column1Header;
	}

	public String getColumn2Header() {
		return column2Header;
	}

	public void setColumn2Header(String column2Header) {
		this.column2Header = column2Header;
	}

	public String getColumn3Header() {
		return column3Header;
	}

	public void setColumn3Header(String column3Header) {
		this.column3Header = column3Header;
	}

	public String getColumn4Header() {
		return column4Header;
	}

	public void setColumn4Header(String column4Header) {
		this.column4Header = column4Header;
	}

	public String getColumn5Header() {
		return column5Header;
	}

	public void setColumn5Header(String column5Header) {
		this.column5Header = column5Header;
	}
	
	

}
