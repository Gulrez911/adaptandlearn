package com.assessment.data;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class BookingSlotInstance extends Base{
	@ManyToOne
	BookingSlot slot;
	
	String email;
	
	String firstName;
	
	String lastName;
	
	String testPracticeUrl;
	
	String testUrl;
	
	String testName;
	
	String mobile;
	
	String payment_id;
	
	String payment_request_id;
	
	String payment_status;
	
	Boolean confirmedByWebHook = false;
	
	Integer amount;
	
	Integer amountChargedByPaymentGateway;
	
	String currency;  

	public BookingSlot getSlot() {
		return slot;
	}

	public void setSlot(BookingSlot slot) {
		this.slot = slot;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFirstName() {
			if(this.firstName ==null){
				return "NA";
			}
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		if(this.lastName == null){
			return "NA";
		}
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getTestPracticeUrl() {
		return testPracticeUrl;
	}

	public void setTestPracticeUrl(String testPracticeUrl) {
		this.testPracticeUrl = testPracticeUrl;
	}

	public String getTestUrl() {
		return testUrl;
	}

	public void setTestUrl(String testUrl) {
		this.testUrl = testUrl;
	}

	public String getTestName() {
		return testName;
	}

	public void setTestName(String testName) {
		this.testName = testName;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getPayment_id() {
		return payment_id;
	}

	public void setPayment_id(String payment_id) {
		this.payment_id = payment_id;
	}

	public String getPayment_request_id() {
		return payment_request_id;
	}

	public void setPayment_request_id(String payment_request_id) {
		this.payment_request_id = payment_request_id;
	}

	public String getPayment_status() {
		return payment_status;
	}

	public void setPayment_status(String payment_status) {
		this.payment_status = payment_status;
	}

	public Boolean getConfirmedByWebHook() {
		return confirmedByWebHook;
	}

	public void setConfirmedByWebHook(Boolean confirmedByWebHook) {
		this.confirmedByWebHook = confirmedByWebHook;
	}

	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	public Integer getAmountChargedByPaymentGateway() {
		return amountChargedByPaymentGateway;
	}

	public void setAmountChargedByPaymentGateway(Integer amountChargedByPaymentGateway) {
		this.amountChargedByPaymentGateway = amountChargedByPaymentGateway;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}
	
	

}
