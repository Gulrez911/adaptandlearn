package com.assessment.data;

import javax.persistence.Entity;

@Entity
public class UICategory extends Base{
	
	String rootCategory;
	
	String level1Category;
	
	String level2Category;
	
	String level3Category;
	
	String level4Category;

	public String getRootCategory() {
		return rootCategory;
	}

	public void setRootCategory(String rootCategory) {
		this.rootCategory = rootCategory;
	}

	public String getLevel1Category() {
		return level1Category;
	}

	public void setLevel1Category(String level1Category) {
		this.level1Category = level1Category;
	}

	public String getLevel2Category() {
		return level2Category;
	}

	public void setLevel2Category(String level2Category) {
		this.level2Category = level2Category;
	}

	public String getLevel3Category() {
		return level3Category;
	}

	public void setLevel3Category(String level3Category) {
		this.level3Category = level3Category;
	}

	public String getLevel4Category() {
		return level4Category;
	}

	public void setLevel4Category(String level4Category) {
		this.level4Category = level4Category;
	}
	
	

}
