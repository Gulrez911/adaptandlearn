package com.assessment.data;

public enum UserType {
	
	STUDENT("STUDENT"), ADMIN("ADMIN"), EVALUATOR("EVALUATOR"), SUPER_ADMIN("SUPER_ADMIN"), REVIEWER("REVIEWER"), LMS_ADMIN("LMS_ADMIN"), LMS_STUDENT("LMS_STUDENT"), TENANT_ADMIN("TENANT_ADMIN"), ADMIN_NEW("ADMIN_NEW"),RECRUITER("RECRUITER");
	String type;
	
	private UserType(String type) {
		this.type = type;
	}

	public String getType() {
		return type;
	}
	
	

}
