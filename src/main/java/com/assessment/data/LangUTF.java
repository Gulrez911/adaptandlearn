package com.assessment.data;

import javax.persistence.Entity;

@Entity
public class LangUTF extends Base{

	String language;
	String screenName;
	String email;
	String firstName;
	String lastName;

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getScreenName() {
		return screenName;
	}

	public void setScreenName(String screenName) {
		this.screenName = screenName;
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

	@Override
	public String toString() {
		return "LangUTF [language=" + language + ", screenName=" + screenName + ", email=" + email + ", firstName=" + firstName + ", lastName=" + lastName + "]";
	}
}
