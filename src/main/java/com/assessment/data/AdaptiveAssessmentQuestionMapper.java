package com.assessment.data;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;

import org.hibernate.annotations.ManyToAny;

@Entity
public class AdaptiveAssessmentQuestionMapper extends Base{

	private String qualifier1;
	
	
	private String qualifier2;
	
	private String qualifier3;
	
	private String qualifier4;
	
	private String qualifier5;
	
	@Enumerated(EnumType.STRING)
	AdaptiveAssessmentSkillLevel level = AdaptiveAssessmentSkillLevel.LEVEL1;
	
	@ManyToMany(fetch=FetchType.EAGER)
	List<Question> questions = new ArrayList<Question>();

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

	public AdaptiveAssessmentSkillLevel getLevel() {
		return level;
	}

	public void setLevel(AdaptiveAssessmentSkillLevel level) {
		this.level = level;
	}

	public List<Question> getQuestions() {
		return questions;
	}

	public void setQuestions(List<Question> questions) {
		this.questions = questions;
	}
	
	
}
