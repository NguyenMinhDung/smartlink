package com.winds.smartlink.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "smartlink_tracker", schema = "smarlink")
public class SmartlinkTracker {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long smartlinkTrackerId;
	
	@Column
	private Long smartLinkUserId;
	
	@Column
	private String countryCode;
	
	@Column
	private Integer visit;
	
	@Column
	private Integer clicked;
	
	@Column
	private Date trackerDate;
	
	public Date getTrackerDate() {
		return trackerDate;
	}

	public void setTrackerDate(Date trackerDate) {
		this.trackerDate = trackerDate;
	}

	public Long getSmartlinkTrackerId() {
		return smartlinkTrackerId;
	}

	public void setSmartlinkTrackerId(Long smartlinkTrackerId) {
		this.smartlinkTrackerId = smartlinkTrackerId;
	}

	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	public Integer getVisit() {
		return visit;
	}

	public void setVisit(Integer visit) {
		this.visit = visit;
	}

	public Integer getClicked() {
		return clicked;
	}

	public void setClicked(Integer clicked) {
		this.clicked = clicked;
	}

	public Long getSmartLinkUserId() {
		return smartLinkUserId;
	}

	public void setSmartLinkUserId(Long smartLinkUserId) {
		this.smartLinkUserId = smartLinkUserId;
	}
}
