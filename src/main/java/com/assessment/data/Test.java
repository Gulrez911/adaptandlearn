package com.assessment.data;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.Transient;

import com.assessment.web.dto.SectionDto;
import com.assessment.web.dto.SectionInstanceDto;
/**
 * pk - testName & companyId
 * @author jsutaria
 *
 */
@Entity
public class Test extends Base {
	
	private String testName;
	
	private String qualifier1;
	private String qualifier2;
	private String qualifier3;
	private String qualifier4;
	private String qualifier5;
	
	String imageUrl;
	
	
	Integer testTimeInMinutes;
	
	Integer totalMarks;
	
	@Column(length=4000)
	String intro;
	
	String postTestCompletionText;
	
	Boolean showFinalScoreToParticipants;
	
	Boolean questionOrder;
	
	@ManyToMany(fetch=FetchType.EAGER)
	List<Skill> skills = new ArrayList<>();
	
	@Transient
	List<String> skls = new ArrayList<>();
	
	@Transient
	String skillInstring;
	
	Float passPercent = 60f;
	
	Boolean sentToStudent = false;
	//new added
//	Boolean displayResultToStudent = false;
	Boolean displayResultToStudent = true;
	Boolean sendResultToAny = false;
	
	String sendResultToEmails;
	//
	@Transient
	String category;
	
	@Transient
	String uDate;
	
	@Transient
	String cDate;
	
	String sendToAdminEmail;
	
	@Transient
	Set<User> users = new HashSet<>();
	
	@Transient
	List<SectionDto> sectionDtos = new ArrayList<>();
	
	Boolean randomQuestions = true;
	
	@Transient
	String publicUrl;
	
	String domainEmailSupported = "*";
	
	
	String testType = "Java";
	
	Boolean sendRecommReport = false;
	
	Integer noOfConfigurableAttempts = 1;
	
	Boolean considerConfidence;
	
	Boolean fullStackTest;
	
	
	
	Boolean justification =true;
	
	String courseContext;
	
	Boolean newUi = false;
	
	Boolean newUiPro = true;
	
	@Enumerated(EnumType.STRING)
	DifficultyLevel difficultyLevel = DifficultyLevel.MEDIUM;
	
	Boolean softDelete = false;
	
	Boolean leoForce = false;
	
	Boolean webProctoring=false;
	
	String testLanguage;
	
	public String getTestLanguage() {
		return testLanguage == null?"eng":testLanguage;
	}
	public void setTestLanguage(String testLanguage) {
		this.testLanguage = testLanguage;
	}
	public Boolean getWebProctoring() {
		return webProctoring;
	}
	public void setWebProctoring(Boolean webProctoring) {
		this.webProctoring = webProctoring;
	}
	public Boolean getSendResultToAny() {
		return sendResultToAny;
	}
	public void setSendResultToAny(Boolean sendResultToAny) {
		this.sendResultToAny = sendResultToAny;
	}
	public String getSendResultToEmails() {
		return sendResultToEmails;
	}
	public void setSendResultToEmails(String sendResultToEmails) {
		this.sendResultToEmails = sendResultToEmails;
	}
	public Boolean getDisplayResultToStudent() {
		return displayResultToStudent;
	}
	public void setDisplayResultToStudent(Boolean displayResultToStudent) {
		this.displayResultToStudent = displayResultToStudent;
	}
 
	public Boolean getSentToStudent() {
		return sentToStudent;
	}
	public void setSentToStudent(Boolean sentToStudent) {
		this.sentToStudent = sentToStudent;
	}
	public String getDefaultSendTo() {
		return defaultSendTo;
	}
	public void setDefaultSendTo(String defaultSendTo) {
		this.defaultSendTo = defaultSendTo;
	}
	public String getOptionalSendTo() {
		return optionalSendTo;
	}
	public void setOptionalSendTo(String optionalSendTo) {
		this.optionalSendTo = optionalSendTo;
	}
	String defaultSendTo;
	
	String optionalSendTo;
	
	
	Boolean onboardingTest = false;
	
	Integer noOfFullScreen = 5;
	
	
	public String getQualifier1() {
		return qualifier1;
	}
	public void setQualifier1(String qualifier1) {
		this.qualifier1 = qualifier1;
	}
	public String getQualifier2() {
		return qualifier2;
	}
	public void setQualifier2(String qualifier2) {
		this.qualifier2 = qualifier2;
	}
	public String getQualifier3() {
		return qualifier3;
	}
	public void setQualifier3(String qualifier3) {
		this.qualifier3 = qualifier3;
	}
	public String getQualifier4() {
		return qualifier4;
	}
	public void setQualifier4(String qualifier4) {
		this.qualifier4 = qualifier4;
	}
	public String getQualifier5() {
		return qualifier5;
	}
	public void setQualifier5(String qualifier5) {
		this.qualifier5 = qualifier5;
	}
	public String getTestName() {
		return testName;
	}
	public void setTestName(String testName) {
		this.testName = testName;
	}
	public Integer getTestTimeInMinutes() {
		return testTimeInMinutes;
	}
	public void setTestTimeInMinutes(Integer testTimeInMinutes) {
		this.testTimeInMinutes = testTimeInMinutes;
	}
	
	public Float getPassPercent() {
		return passPercent;
	}
	public void setPassPercent(Float passPercent) {
		this.passPercent = passPercent;
	}
	public List<Skill> getSkills() {
		return skills;
	}
	public void setSkills(List<Skill> skills) {
		this.skills = skills;
		List<String> ss = new ArrayList<>();
		for(Skill skill : skills){
			ss.add(skill.getId()+"");
		}
		setSkls(ss);
	}
	
	
	public String getCategory() {
		if(getQualifier1() != null && getQualifier1().trim().length() != 0) {
			if(getQualifier2() != null && getQualifier2().trim().length() != 0) {
				if(getQualifier3() != null && getQualifier3().trim().length() != 0) {
					if(getQualifier4() != null && getQualifier4().trim().length() != 0) {
						if(getQualifier5() != null && getQualifier5().trim().length() != 0) {
							return getQualifier1()+" / "+getQualifier2() +"/"+getQualifier3()+"/"+getQualifier4()+"/"+getQualifier5();
						}
						else {
							return getQualifier1()+" / "+getQualifier2() +"/"+getQualifier3()+"/"+getQualifier4();
						}
					}
					else {
						return getQualifier1()+" / "+getQualifier2() +"/"+getQualifier3();
					}
				}
				else {
					return getQualifier1()+" / "+getQualifier2();
				}
			}
			else {
				return getQualifier1();
			}
		}
		return "";
	}
	
	
	
	
	public void setCategory(String category) {
		this.category = category;
	}
	public String getuDate() {
		String pattern = "dd-MMM-yyyy";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
		if(getUpdateDate() == null) {
			if(getCreateDate() == null) {
				return "Not Available";
			}
			else {
				return simpleDateFormat.format(getCreateDate());
			}
			
		}
		else {
			return simpleDateFormat.format(getUpdateDate());
		}
	}
	public void setuDate(String uDate) {
		this.uDate = uDate;
	}
	public String getcDate() {
		String pattern = "dd-MMM-yyyy";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
		if(getCreateDate() == null) {
			return "Not Available";
		}
		else {
			return simpleDateFormat.format(getCreateDate());
		}
		
	}
	public void setcDate(String cDate) {
		this.cDate = cDate;
	}
	public String getIntro() {
			if(this.intro == null || this.intro.length() == 0){
				String def = "The Test aims to assess understanding of Concepts or Reasoning or Visual language elements or Code. Different Questions represent different challenges. In case of Coding assessments, the test aims to measure functional compliance plus positive/negative/boundary conditions of the Code as written.";
				return def;
			}
		return intro;
	}
	public void setIntro(String intro) {
		this.intro = intro;
	}
	public String getPostTestCompletionText() {
		return postTestCompletionText;
	}
	public void setPostTestCompletionText(String postTestCompletionText) {
		this.postTestCompletionText = postTestCompletionText;
	}
	public Boolean getShowFinalScoreToParticipants() {
		return showFinalScoreToParticipants;
	}
	public void setShowFinalScoreToParticipants(Boolean showFinalScoreToParticipants) {
		this.showFinalScoreToParticipants = showFinalScoreToParticipants;
	}
	public Boolean getQuestionOrder() {
		return questionOrder;
	}
	public void setQuestionOrder(Boolean questionOrder) {
		this.questionOrder = questionOrder;
	}
	public Set<User> getUsers() {
		return users;
	}
	public void setUsers(Set<User> users) {
		this.users = users;
	}
	public List<SectionDto> getSectionDtos() {
		return sectionDtos;
	}
	public void setSectionDtos(List<SectionDto> sectionDtos) {
		this.sectionDtos = sectionDtos;
	}
	public String getSendToAdminEmail() {
		return sendToAdminEmail;
	}
	public void setSendToAdminEmail(String sendToAdminEmail) {
		this.sendToAdminEmail = sendToAdminEmail;
	}
	public Boolean getRandomQuestions() {
		return randomQuestions;
	}
	public void setRandomQuestions(Boolean randomQuestions) {
		this.randomQuestions = randomQuestions;
	}
	public Integer getTotalMarks() {
		return totalMarks;
	}
	public void setTotalMarks(Integer totalMarks) {
		this.totalMarks = totalMarks;
	}
	public String getPublicUrl() {
		return publicUrl;
	}
	public void setPublicUrl(String publicUrl) {
		this.publicUrl = publicUrl;
	}
	public String getDomainEmailSupported() {
		if(this.domainEmailSupported == null || this.domainEmailSupported.trim().length() == 0) {
			return "*";
		}
		return domainEmailSupported;
	}
	public void setDomainEmailSupported(String domainEmailSupported) {
		this.domainEmailSupported = domainEmailSupported;
	}
	public List<String> getSkls() {
		return skls;
	}
	public void setSkls(List<String> skls) {
		this.skls = skls;
	}
	public String getTestType() {
		if(this.testType == null){
			return "Java";
		}
		return testType;
	}
	public void setTestType(String testType) {
		this.testType = testType;
	}
	public Boolean getSendRecommReport() {
		return sendRecommReport;
	}
	public void setSendRecommReport(Boolean sendRecommReport) {
		this.sendRecommReport = sendRecommReport;
	}
	public Integer getNoOfConfigurableAttempts() {
		return noOfConfigurableAttempts;
	}
	public void setNoOfConfigurableAttempts(Integer noOfConfigurableAttempts) {
		this.noOfConfigurableAttempts = noOfConfigurableAttempts;
	}
	public Boolean getConsiderConfidence() {
		return considerConfidence;
	}
	public void setConsiderConfidence(Boolean considerConfidence) {
		this.considerConfidence = considerConfidence;
	}
	public Boolean getFullStackTest() {
		return fullStackTest;
	}
	public void setFullStackTest(Boolean fullStackTest) {
		this.fullStackTest = fullStackTest;
	}
	public Boolean getJustification() {
		return justification;
	}
	public void setJustification(Boolean justification) {
		this.justification = justification;
	}
	public String getCourseContext() {
		return courseContext;
	}
	public void setCourseContext(String courseContext) {
		this.courseContext = courseContext;
	}
	public Boolean getOnboardingTest() {
		return onboardingTest;
	}
	public void setOnboardingTest(Boolean onboardingTest) {
		this.onboardingTest = onboardingTest;
	}
	public String getSkillInstring() {
		String ret = "";
		for(int i=0; i<getSkills().size(); i++){
			Skill s = getSkills().get(i);
			ret += s.getSkillName();
			if( i  != (getSkills().size() - 1)){
				ret+= ", ";
			}
		}
		return ret;
	}
	public void setSkillInstring(String skillInstring) {
		this.skillInstring = skillInstring;
	}
	public DifficultyLevel getDifficultyLevel() {
		if(this.difficultyLevel == null){
			return DifficultyLevel.MEDIUM;
		}
		return difficultyLevel;
	}
	public void setDifficultyLevel(DifficultyLevel difficultyLevel) {
		this.difficultyLevel = difficultyLevel;
	}

	@Transient
	String level;


	public String getLevel() {
			if(getDifficultyLevel() == null){
				return "";
			}
		return getDifficultyLevel().getLevel();
	}
	public void setLevel(String level) {
		this.level = level;
			if(level != null){
				setDifficultyLevel(DifficultyLevel.valueOf(level));
			}
		
	}
	public Boolean getSoftDelete() {
		return softDelete;
	}
	public void setSoftDelete(Boolean softDelete) {
		this.softDelete = softDelete;
	}
	
	
	public Boolean getLeoForce() {
		return leoForce;
	}
	public void setLeoForce(Boolean leoForce) {
		this.leoForce = leoForce;
	}
	public Integer getNoOfFullScreen() {
		if(noOfFullScreen == null){
			return 5;
		}
		return noOfFullScreen;
	}
	public void setNoOfFullScreen(Integer noOfFullScreen) {
		this.noOfFullScreen = noOfFullScreen;
	}
	public Boolean getNewUi() {
		return newUi;
	}
	public void setNewUi(Boolean newUi) {
		this.newUi = newUi;
	}
	public String getImageUrl() {
			if(this.imageUrl == null || this.imageUrl.length() == 0){
				return "http://159.65.148.176/Eassess3/resources/images/assessment.png";
			}
		return imageUrl;
	}
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	public Boolean getNewUiPro() {
		return newUiPro;
	}
	public void setNewUiPro(Boolean newUiPro) {
		this.newUiPro = newUiPro;
	}
	
	
	
	
}
