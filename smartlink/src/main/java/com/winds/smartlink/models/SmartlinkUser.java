package com.winds.smartlink.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "smartlink_user", schema = "smarlink")
public class SmartlinkUser {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long smartlinkUserId;
	
	@Column
	private Long smartlinkId;
	
	@ManyToOne(targetEntity = Smartlink.class)
	@JoinColumn(name = "smartlinkId", referencedColumnName = "smartlinkId", insertable = false, updatable = false)
	private Smartlink smartlink;
	
	@Column
	private Long userId;
	
	@Column
	private String generateLink;
	
	@Column
	private Integer status;

	public String getGenerateLink() {
		return generateLink;
	}

	public void setGenerateLink(String generateLink) {
		this.generateLink = generateLink;
	}

	public Smartlink getSmartlink() {
		return smartlink;
	}

	public void setSmartlink(Smartlink smartlink) {
		this.smartlink = smartlink;
	}

	public Long getSmartlinkId() {
		return smartlinkId;
	}

	public void setSmartlinkId(Long smartlinkId) {
		this.smartlinkId = smartlinkId;
	}

	public Long getSmartlinkUserId() {
		return smartlinkUserId;
	}

	public void setSmartlinkUserId(Long smartlinkUserId) {
		this.smartlinkUserId = smartlinkUserId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
}
