package com.assessment.data;

import javax.persistence.Entity;

/**
 * @author gulfa
 *
 */
@Entity
public class Swaal extends Base {

	String mainCategory;

	String masterCategory;

	String branchLevel;

	String subjectLevel;

	String topicLevel;

	String link;

	int pages;

	public int getPages() {
		return pages;
	}

	public void setPages(int pages) {
		this.pages = pages;
	}

	public String getMainCategory() {
		return mainCategory;
	}

	public void setMainCategory(String mainCategory) {
		this.mainCategory = mainCategory;
	}

	public String getMasterCategory() {
		return masterCategory;
	}

	public void setMasterCategory(String masterCategory) {
		this.masterCategory = masterCategory;
	}

	public String getBranchLevel() {
		return branchLevel;
	}

	public void setBranchLevel(String branchLevel) {
		this.branchLevel = branchLevel;
	}

	public String getSubjectLevel() {
		return subjectLevel;
	}

	public void setSubjectLevel(String subjectLevel) {
		this.subjectLevel = subjectLevel;
	}

	public String getTopicLevel() {
		return topicLevel;
	}

	public void setTopicLevel(String topicLevel) {
		this.topicLevel = topicLevel;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

}
