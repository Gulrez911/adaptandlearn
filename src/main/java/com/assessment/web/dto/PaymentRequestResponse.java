package com.assessment.web.dto;

public class PaymentRequestResponse {
	 private boolean success;

	    private Payment_request payment_request;

	    public void setSuccess(boolean success){
	        this.success = success;
	    }
	    public boolean getSuccess(){
	        return this.success;
	    }
	    public void setPayment_request(Payment_request payment_request){
	        this.payment_request = payment_request;
	    }
	    public Payment_request getPayment_request(){
	        return this.payment_request;
	    }
}
