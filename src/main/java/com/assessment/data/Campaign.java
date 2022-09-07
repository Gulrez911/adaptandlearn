package com.assessment.data;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

@Entity
public class Campaign extends Base{
	@Column(length=500)
	String campaignName;
	
	@OneToMany(orphanRemoval=true, cascade=CascadeType.ALL)
	@LazyCollection(LazyCollectionOption.FALSE)
	List<CampaignTest> rounds = new ArrayList<CampaignTest>();
	
	@Column(length=3000)
	String campaignDesc;
	
	Boolean active = true;
	
	@Transient
	List<String> skillsForCampaign = new ArrayList<>();
	
	@Transient
	String skillsConcatenated;
	
	@ManyToMany(fetch=FetchType.EAGER)
	List<CampaignReviewer> reviewers = new ArrayList<>();
	
	Boolean meetingRound = false;
	
	@Transient
	String publishedDate;
	
	@Transient
	List<CampaignCandidate> candidates = new ArrayList<>();
	
	@Transient
	List<String> candidatesString = new ArrayList<>();
	
	@Transient
	String reviewersString = "";

	String language = "eng";
	
	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getCampaignName() {
		return campaignName;
	}

	public void setCampaignName(String campaignName) {
		this.campaignName = campaignName;
	}

	public List<CampaignTest> getRounds() {
		return rounds;
	}

	public void setRounds(List<CampaignTest> rounds) {
		this.rounds = rounds;
	}

	public String getCampaignDesc() {
		return campaignDesc;
	}

	public void setCampaignDesc(String campaignDesc) {
		this.campaignDesc = campaignDesc;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public List<String> getSkillsForCampaign() {
		List<String> skills = new ArrayList<>();
			for(CampaignTest test : rounds){
				skills.add(test.getTestSkills());
			}
		return skills;
	}

	public void setSkillsForCampaign(List<String> skillsForCampaign) {
		this.skillsForCampaign = skillsForCampaign;
	}

	public List<CampaignReviewer> getReviewers() {
		return reviewers;
	}

	public void setReviewers(List<CampaignReviewer> reviewers) {
		this.reviewers = reviewers;
	}

	public String getSkillsConcatenated() {
		List<String> skills = getSkillsForCampaign();
			if(skills == null || skills.size() == 0){
				return "NA";
			}
		String skl = "";
			for(String s : skills){
				skl += s+", ";
			}
		skl = skl.trim();
		skl = skl.substring(0, skl.lastIndexOf(","));
		return skl;
	}

	public void setSkillsConcatenated(String skillsConcatenated) {
		this.skillsConcatenated = skillsConcatenated;
	}

	public Boolean getMeetingRound() {
		return meetingRound;
	}

	public void setMeetingRound(Boolean meetingRound) {
		this.meetingRound = meetingRound;
	}

	public String getPublishedDate() {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy hh:mm aa");
			if(getCreateDate() == null && getUpdateDate() == null){
				return "NA";
			}
		String pub = dateFormat.format(getCreateDate()==null?getUpdateDate():getCreateDate());
		return pub;
	}

	public void setPublishedDate(String publishedDate) {
		this.publishedDate = publishedDate;
	}

	public List<CampaignCandidate> getCandidates() {
		return candidates;
	}

	public void setCandidates(List<CampaignCandidate> candidates) {
		this.candidates = candidates;
	}

	public List<String> getCandidatesString() {
		List<String> list = new ArrayList<>();
			for(CampaignCandidate can : this.candidates){
				list.add((can.getFirstName()==null?"NA":can.getFirstName())+"-"+(can.getLastName()==null?"NA":can.getLastName())+"-"+can.getEmail());
			}
		return list;
	}

	public void setCandidatesString(List<String> candidatesString) {
		this.candidatesString = candidatesString;
	}

	public String getReviewersString() {
		String ret = "";
		for(CampaignReviewer can : this.reviewers){
			ret += (can.getFirstName()==null?"NA":can.getFirstName())+"-"+(can.getLastName()==null?"NA":can.getLastName())+"-"+can.getEmail()+"<br>";
		}
		return ret;
	}

	public void setReviewersString(String reviewersString) {
		this.reviewersString = reviewersString;
	}
	
	
	

}
