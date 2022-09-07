package com.assessment.jsonwrapper;

public class JsonResponseWrapperBean implements  JsonBeanMarker {

	private JsonBeanMarker responseStatus;
	private JsonBeanMarker responseContent;
	
	public JsonBeanMarker getResponseStatus() {
		return responseStatus;
	}
	public void setResponseStatus(JsonBeanMarker responseStatus) {
		this.responseStatus = responseStatus;
	}
	public JsonBeanMarker getResponseContent() {
		return responseContent;
	}
	public void setResponseContent(JsonBeanMarker responseContent) {
		this.responseContent = responseContent;
	}
	
}
