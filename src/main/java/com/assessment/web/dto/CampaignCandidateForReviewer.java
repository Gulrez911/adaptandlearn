package com.assessment.web.dto;

public class CampaignCandidateForReviewer {

	
	String campaignName;
	
	
	String firstName;
	
	String lastName;
	
	String email;
	
	String reviewerEmail;
	
	String userProfileUrl;
	
	String skillsAssociatedWithCampaign;
	
	public CampaignCandidateForReviewer(){
		
	}
	
	public CampaignCandidateForReviewer(String campaignName, String firstName, String lastName, String email, String reviewerEmail){
		this.campaignName = campaignName;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.reviewerEmail = reviewerEmail;
	}

	public String getCampaignName() {
		return campaignName;
	}

	public void setCampaignName(String campaignName) {
		this.campaignName = campaignName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getReviewerEmail() {
		return reviewerEmail;
	}

	public void setReviewerEmail(String reviewerEmail) {
		this.reviewerEmail = reviewerEmail;
	}

	public String getUserProfileUrl() {
		return userProfileUrl;
	}

	public void setUserProfileUrl(String userProfileUrl) {
		this.userProfileUrl = userProfileUrl;
	}

	public String getSkillsAssociatedWithCampaign() {
		return skillsAssociatedWithCampaign;
	}

	public void setSkillsAssociatedWithCampaign(String skillsAssociatedWithCampaign) {
		this.skillsAssociatedWithCampaign = skillsAssociatedWithCampaign;
	}
	
	
	
}
