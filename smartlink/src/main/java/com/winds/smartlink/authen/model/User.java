package com.winds.smartlink.authen.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

/**
 * @author Windreams
 *
 */
@Entity
@Table(name = "users", schema = "smarlink")
public class User {
	@Id
	private Long userId;
	
	@Column
	private String username;
	
	@Column
	private String password;
	
	@Column
	private String email;
	
	@Column
	private Integer status;
	
	@ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "USERS_PROFILES", 
             joinColumns = { @JoinColumn(name = "userId") }, 
             inverseJoinColumns = { @JoinColumn(name = "profileId") })
    private Set<Profile> profiles = new HashSet<Profile>();
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Set<Profile> getProfiles() {
		return profiles;
	}
	public void setProfiles(Set<Profile> profiles) {
		this.profiles = profiles;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	
}
