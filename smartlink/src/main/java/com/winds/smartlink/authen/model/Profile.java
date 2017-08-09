package com.winds.smartlink.authen.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Windreams
 *
 */
@Entity
@Table(name = "profiles", schema = "smarlink")
public class Profile {
	@Id
	private Long profileId;
	
	@Column
	private String type;

	public Long getProfileId() {
		return profileId;
	}

	public void setProfileId(Long profileId) {
		this.profileId = profileId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	
}
