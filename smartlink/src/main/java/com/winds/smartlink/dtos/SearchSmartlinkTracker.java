package com.winds.smartlink.dtos;

import java.util.Date;

public class SearchSmartlinkTracker {
	private Long smartlinkUserId;
	private String countryCode;
	private Date trackerDate;
	private Long userId;
	
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public Long getSmartlinkUserId() {
		return smartlinkUserId;
	}
	public void setSmartlinkUserId(Long smartlinkUserId) {
		this.smartlinkUserId = smartlinkUserId;
	}
	public String getCountryCode() {
		return countryCode;
	}
	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}
	public Date getTrackerDate() {
		return trackerDate;
	}
	public void setTrackerDate(Date trackerDate) {
		this.trackerDate = trackerDate;
	}
	
	

}
