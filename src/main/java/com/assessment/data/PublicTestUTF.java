package com.assessment.data;

import javax.persistence.Entity;

@Entity
public class PublicTestUTF extends Base{
	
	String language;
	String email;
	String firstName;
	String lastName;
	String candidateId;
	String degree;
	String passingYear;
	String mobile;
	String signIn;
	String isReadyTOask;
	String eAssessQuotes;
	String enterValidFirstName;
	String enterValidLastName;
	String enterValidEmail;
	String optional;
	String ifYouNeedAny;
	
	public String getIfYouNeedAny() {
		return ifYouNeedAny;
	}
	public void setIfYouNeedAny(String ifYouNeedAny) {
		this.ifYouNeedAny = ifYouNeedAny;
	}
	public String getOptional() {
		return optional;
	}
	public void setOptional(String optional) {
		this.optional = optional;
	}
	public String getEnterValidFirstName() {
		return enterValidFirstName;
	}
	public void setEnterValidFirstName(String enterValidFirstName) {
		this.enterValidFirstName = enterValidFirstName;
	}
	public String getEnterValidLastName() {
		return enterValidLastName;
	}
	public void setEnterValidLastName(String enterValidLastName) {
		this.enterValidLastName = enterValidLastName;
	}
	public String getEnterValidEmail() {
		return enterValidEmail;
	}
	public void setEnterValidEmail(String enterValidEmail) {
		this.enterValidEmail = enterValidEmail;
	}
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
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
	public String getCandidateId() {
		return candidateId;
	}
	public void setCandidateId(String candidateId) {
		this.candidateId = candidateId;
	}
	public String getDegree() {
		return degree;
	}
	public void setDegree(String degree) {
		this.degree = degree;
	}
	public String getPassingYear() {
		return passingYear;
	}
	public void setPassingYear(String passingYear) {
		this.passingYear = passingYear;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getSignIn() {
		return signIn;
	}
	public void setSignIn(String signIn) {
		this.signIn = signIn;
	}
	public String getIsReadyTOask() {
		return isReadyTOask;
	}
	public void setIsReadyTOask(String isReadyTOask) {
		this.isReadyTOask = isReadyTOask;
	}
	public String geteAssessQuotes() {
		return eAssessQuotes;
	}
	public void seteAssessQuotes(String eAssessQuotes) {
		this.eAssessQuotes = eAssessQuotes;
	}
	
	
}
