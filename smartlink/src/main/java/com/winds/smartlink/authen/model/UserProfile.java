package com.winds.smartlink.authen.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Windreams
 *
 */
@Entity
@Table(name = "users_profiles", schema = "smarlink")
public class UserProfile {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long userProfileId;
	
	@Column
	private Long userId;
	
	@Column
	private Long profileId;

	public Long getProfileId() {
		return profileId;
	}

	public void setProfileId(Long profileId) {
		this.profileId = profileId;
	}

	public Long getUserProfileId() {
		return userProfileId;
	}

	public void setUserProfileId(Long userProfileId) {
		this.userProfileId = userProfileId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}
}
