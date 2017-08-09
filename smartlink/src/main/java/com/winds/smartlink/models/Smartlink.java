package com.winds.smartlink.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "smartlink", schema = "smarlink")
public class Smartlink {
	@Id
	private Long smartlinkId;
	
	@Column
	private String link;
	
	@Column
	private String network;
	
	@Column
	private Integer status;

	public Long getSmartlinkId() {
		return smartlinkId;
	}

	public void setSmartlinkId(Long smartlinkId) {
		this.smartlinkId = smartlinkId;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getNetwork() {
		return network;
	}

	public void setNetwork(String network) {
		this.network = network;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Smartlink [smartlinkId=" + smartlinkId + ", link=" + link + ", network=" + network + ", status="
				+ status + "]";
	}
}
