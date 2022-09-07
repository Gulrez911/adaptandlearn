package com.assessment.web.dto;

public class MeetingSessionResponse {
	public MeetingSessionResponseResult result;
    public Object targetUrl;
    public boolean success;
    public Object error;
    public boolean unAuthorizedRequest;
    public boolean __abp;
	public MeetingSessionResponseResult getResult() {
		return result;
	}
	public void setResult(MeetingSessionResponseResult result) {
		this.result = result;
	}
	public Object getTargetUrl() {
		return targetUrl;
	}
	public void setTargetUrl(Object targetUrl) {
		this.targetUrl = targetUrl;
	}
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	public Object getError() {
		return error;
	}
	public void setError(Object error) {
		this.error = error;
	}
	public boolean isUnAuthorizedRequest() {
		return unAuthorizedRequest;
	}
	public void setUnAuthorizedRequest(boolean unAuthorizedRequest) {
		this.unAuthorizedRequest = unAuthorizedRequest;
	}
	public boolean is__abp() {
		return __abp;
	}
	public void set__abp(boolean __abp) {
		this.__abp = __abp;
	}
    
    
}
