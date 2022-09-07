package com.assessment.web.dto.proctortrack;

public class Assignment_data {
	private String id;
	 private boolean is_proctored;
	 private String start;
	 private String end;
	 private boolean is_onboarding;
	 private String group_id;
	 private String access_code;
	 private String name;
	 private String test_url;
	 private String duration;
	 private boolean is_active;
	 
	 
	// private float attempts_allowed;
	 private Integer attempts_allowed;
	 
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public boolean isIs_proctored() {
		return is_proctored;
	}
	public void setIs_proctored(boolean is_proctored) {
		this.is_proctored = is_proctored;
	}
	public String getStart() {
		return start;
	}
	public void setStart(String start) {
		this.start = start;
	}
	public String getEnd() {
		return end;
	}
	public void setEnd(String end) {
		this.end = end;
	}
	public boolean isIs_onboarding() {
		return is_onboarding;
	}
	public void setIs_onboarding(boolean is_onboarding) {
		this.is_onboarding = is_onboarding;
	}
	public String getGroup_id() {
		return group_id;
	}
	public void setGroup_id(String group_id) {
		this.group_id = group_id;
	}
	public String getAccess_code() {
		return access_code;
	}
	public void setAccess_code(String access_code) {
		this.access_code = access_code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTest_url() {
		return test_url;
	}
	public void setTest_url(String test_url) {
		this.test_url = test_url;
	}
	public String getDuration() {
		return duration;
	}
	public void setDuration(String duration) {
		this.duration = duration;
	}
	public boolean isIs_active() {
		return is_active;
	}
	public void setIs_active(boolean is_active) {
		this.is_active = is_active;
	}
	public Integer getAttempts_allowed() {
		return attempts_allowed;
	}
	public void setAttempts_allowed(Integer attempts_allowed) {
		this.attempts_allowed = attempts_allowed;
	}
	 
	 
}
