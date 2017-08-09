package com.winds.smartlink.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "user_links", schema = "smarlink")
public class UserLink {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long userLinkId;
	
	@Column
	private Long userId;
	
	@Column
	private String link;
	
	@Column
	private String channel;
	
	@Column
	private String smartlink;
	
	@Column
	private Long code;
	
	@Column
	private String email;
	
	@Transient
	private String metadata;
	
	public String getSmartlink() {
		return smartlink;
	}

	public void setSmartlink(String smartlink) {
		this.smartlink = smartlink;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Long getCode() {
		return code;
	}

	public void setCode(Long code) {
		this.code = code;
	}

	public String getChannel() {
		return channel;
	}

	public void setChannel(String channel) {
		this.channel = channel;
	}

	public String getMetadata() {
		return metadata;
	}

	public void setMetadata(String metadata) {
		this.metadata = metadata;
	}

	public Long getUserLinkId() {
		return userLinkId;
	}

	public void setUserLinkId(Long userLinkId) {
		this.userLinkId = userLinkId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	@Override
	public String toString() {
		return "UserLink [userLinkId=" + userLinkId + ", userId=" + userId + ", link=" + link + ", metadata=" + metadata
				+ "]";
	}
}
