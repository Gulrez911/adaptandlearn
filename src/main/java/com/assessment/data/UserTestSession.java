package com.assessment.data;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
/**
 * pk - user & test & companyId
 * @author jsutaria
 *
 */
@Entity
public class UserTestSession extends Base {

	@NotNull
	String user;
	@NotNull
	String testName;
	
	Integer timeTakenInMimnutes;
	@ManyToOne
	Test test;
	
	Boolean complete = false;
	
	Integer noOfAttempts;
	
	Float percentageMarksRecieved;
	
	Integer totalMarksRecieved;
	
	Integer totalMarks;
	
	String sectionResults;
	
	Boolean pass = false;
	
	Date testInviteSent;
	
	Boolean sharedDirect = false;
	
	Integer noOfQuestionsAnswered;
	
	String sectionsNoOfQuestionsNotAnswered;
	
	Float weightedScorePercentage;
	
	Integer noOfNonCompliances = 0;
	
	@Transient
	String style = "";
	
	Boolean subjective  = false;
	
	Boolean markComplete = false;//a
	
	String collegeName;
	
	String grade;
	
	String classifier;
	
	String firstName;
	
	String lastName;
	
	@Transient
	Integer attempt;
	
	@Transient
	String emailWithAttempt;
	
	@Transient
	String formattedDt;
	
	public Boolean getSharedDirect() {
		return sharedDirect;
	}
	public void setSharedDirect(Boolean sharedDirect) {
		this.sharedDirect = sharedDirect;
	}
	
//	@OneToMany
//	List<QuestionMapperInstance> questionMappersInstances;

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public Integer getTimeTakenInMimnutes() {
		return timeTakenInMimnutes;
	}

	public void setTimeTakenInMimnutes(Integer timeTakenInMimnutes) {
		this.timeTakenInMimnutes = timeTakenInMimnutes;
	}

	public Test getTest() {
		return test;
	}

	public void setTest(Test test) {
		this.test = test;
	}

	public Boolean getComplete() {
		return complete;
	}

	public void setComplete(Boolean complete) {
		this.complete = complete;
	}

	public Integer getNoOfAttempts() {
		return noOfAttempts;
	}

	public void setNoOfAttempts(Integer noOfAttempts) {
		this.noOfAttempts = noOfAttempts;
	}

	public String getTestName() {
		return testName;
	}

	public void setTestName(String testName) {
		this.testName = testName;
	}

	public Float getPercentageMarksRecieved() {
		return percentageMarksRecieved;
	}

	public void setPercentageMarksRecieved(Float percentageMarksRecieved) {
		this.percentageMarksRecieved = percentageMarksRecieved;
	}

	public Integer getTotalMarksRecieved() {
		return totalMarksRecieved;
	}

	public void setTotalMarksRecieved(Integer totalMarksRecieved) {
		this.totalMarksRecieved = totalMarksRecieved;
	}

	public Integer getTotalMarks() {
		return totalMarks;
	}

	public void setTotalMarks(Integer totalMarks) {
		this.totalMarks = totalMarks;
	}

	public Boolean getPass() {
		return pass;
	}

	public void setPass(Boolean pass) {
		this.pass = pass;
	}

	public String getSectionResults() {
		return sectionResults;
	}

	public void setSectionResults(String sectionResults) {
		this.sectionResults = sectionResults;
	}

	public Date getTestInviteSent() {
		return testInviteSent;
	}

	public void setTestInviteSent(Date testInviteSent) {
		this.testInviteSent = testInviteSent;
	}
	public Integer getNoOfQuestionsAnswered() {
		return noOfQuestionsAnswered;
	}
	public void setNoOfQuestionsAnswered(Integer noOfQuestionsAnswered) {
		this.noOfQuestionsAnswered = noOfQuestionsAnswered;
	}
	public String getSectionsNoOfQuestionsNotAnswered() {
		return sectionsNoOfQuestionsNotAnswered;
	}
	public void setSectionsNoOfQuestionsNotAnswered(String sectionsNoOfQuestionsNotAnswered) {
		this.sectionsNoOfQuestionsNotAnswered = sectionsNoOfQuestionsNotAnswered;
	}
	public Float getWeightedScorePercentage() {
		return weightedScorePercentage;
	}
	public void setWeightedScorePercentage(Float weightedScorePercentage) {
		this.weightedScorePercentage = weightedScorePercentage;
	}

	@Transient
	public String getFormattedWeightedScore(){
		DecimalFormat decimalFormat = new DecimalFormat("#.##");
		return decimalFormat.format(getWeightedScorePercentage() == null?0.0f:getWeightedScorePercentage());
	}
	public Integer getNoOfNonCompliances() {
		return noOfNonCompliances;
	}
	public void setNoOfNonCompliances(Integer noOfNonCompliances) {
		this.noOfNonCompliances = noOfNonCompliances;
	}
	public String getStyle() {
		return style;
	}
	public void setStyle(String style) {
		this.style = style;
	}
	
	@Transient
	public String getDateofTest(){
		Date dt = getUpdateDate() == null?getCreateDate():getUpdateDate();
		String ret = "NA";
		if(dt != null){
			SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy hh:mm aa");
			ret = dateFormat.format(dt);
		}
		return ret;
	}
	public Boolean getSubjective() {
		return subjective;
	}
	public void setSubjective(Boolean subjective) {
		this.subjective = subjective;
	}
	public Boolean getMarkComplete() {
		return markComplete;
	}
	public void setMarkComplete(Boolean markComplete) {
		this.markComplete = markComplete;
	}
	public String getCollegeName() {
		return collegeName;
	}
	public void setCollegeName(String collegeName) {
		this.collegeName = collegeName;
	}
	public String getGrade() {
		return grade;
	}
	public void setGrade(String grade) {
		this.grade = grade;
	}
	public String getClassifier() {
		return classifier;
	}
	public void setClassifier(String classifier) {
		this.classifier = classifier;
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
	public Integer getAttempt() {
		String email = getUser();
		if(!email.contains("[")){
			return 1;
		}
		else{
			String att = email.substring(email.indexOf("[")+1, email.indexOf("]"));
			String att2[] = att.split("-");
			try {
				return Integer.parseInt(att2[1]);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return 0;
			}
		}
		
	}
	public void setAttempt(Integer attempt) {
		this.attempt = attempt;
	}
	public String getEmailWithAttempt() {
		String email = getUser();
		if(!email.contains("[")){
			return email;
		}
		else{
			String att = email.substring(0, email.indexOf("["));
			return att;
		}
	}
	public void setEmailWithAttempt(String emailWithAttempt) {
		this.emailWithAttempt = emailWithAttempt;
	}
	
	public String getFormattedDt() {
		Date d = getUpdateDate() == null?getCreateDate():getUpdateDate();
			if(d == null){
				return "NA";
			}
			SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy hh:mm aa");
			return dateFormat.format(d);
	}
	
	public void setFormattedDt(String formattedDt) {
		this.formattedDt = formattedDt;
	}
	
	
}
