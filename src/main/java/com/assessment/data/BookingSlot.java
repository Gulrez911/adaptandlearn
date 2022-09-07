package com.assessment.data;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Transient;

@Entity
public class BookingSlot extends Base {
	
	Date start;
	
	Date end;
	
	String month;
	
	String day;
	
	String year;
	
	Integer noOfParticipants;
	
	Long startTimeInMs;
	
	Boolean disabled = false;
	
	@Transient
	Integer capacityFilledSoFar;
	
	@Transient
	String timeStr;
	
	@Transient
	String dateStr;

	public Date getStart() {
		return start;
	}

	public void setStart(Date start) {
		this.start = start;
	}

	public Date getEnd() {
		return end;
	}

	public void setEnd(Date end) {
		this.end = end;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public String getDay() {
		return day;
	}

	public void setDay(String day) {
		this.day = day;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public Integer getNoOfParticipants() {
		return noOfParticipants;
	}

	public void setNoOfParticipants(Integer noOfParticipants) {
		this.noOfParticipants = noOfParticipants;
	}

	public Long getStartTimeInMs() {
		return startTimeInMs;
	}

	public void setStartTimeInMs(Long startTimeInMs) {
		this.startTimeInMs = startTimeInMs;
	}

	public Boolean getDisabled() {
		return disabled;
	}

	public void setDisabled(Boolean disabled) {
		this.disabled = disabled;
	}

	public Integer getCapacityFilledSoFar() {
		return capacityFilledSoFar;
	}

	public void setCapacityFilledSoFar(Integer capacityFilledSoFar) {
		this.capacityFilledSoFar = capacityFilledSoFar;
	}

	public String getTimeStr() {
		if(getStart() != null){
			return new SimpleDateFormat("hh:mm:ss a").format(getStart());
		}
		return "NA";
	}

	public void setTimeStr(String timeStr) {
		this.timeStr = timeStr;
	}

	public String getDateStr() {
		return getDay()+"/"+getMonth()+"/"+getYear()+" "+getTimeStr();
	}

	public void setDateStr(String dateStr) {
		this.dateStr = dateStr;
	}
	
	

}
