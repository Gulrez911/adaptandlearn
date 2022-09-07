package com.assessment.jsonwrapper;


public class JsonStatusBean implements JsonBeanMarker{
	/**
	 * Http status code shopuld not come here as the http response comes any ways to the calling client.
	 */
	//private Integer statusCode; // important since primitive 'int' is initialized to zero by default
	//private String statusMessage;
	
	/**
	 * Not using this since this has to be an object
	 */
	private String responseContent;
	
	
	String errorMessage = "";
	
	Object data;
	
	public JsonStatusBean(){
		
	}
	
	public JsonStatusBean(Object data){
		this.data = data;
	}
	
	public JsonStatusBean(String error){
		this.errorMessage = error;
	}
	
	public String getResponseContent() {
		return responseContent;
	}
	public void setResponseContent(String responseContent) {
		this.responseContent = responseContent;
	}
//	public Integer getStatusCode() {
//		return statusCode;
//	}
//	public void setStatusCode(Integer statusCode) {
//		this.statusCode = statusCode;
//	}
//	public String getStatusMessage() {
//		return statusMessage;
//	}
//	public void setStatusMessage(String statusMessage) {
//		this.statusMessage = statusMessage;
//	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	
	
}
