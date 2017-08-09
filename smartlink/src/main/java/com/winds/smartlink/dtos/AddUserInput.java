package com.winds.smartlink.dtos;

public class AddUserInput {
	private String email;
	private String smartlink;
	private String network;
	
	public String getNetwork() {
		return network;
	}
	public void setNetwork(String network) {
		this.network = network;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getSmartlink() {
		return smartlink;
	}
	public void setSmartlink(String smartlink) {
		this.smartlink = smartlink;
	}
	@Override
	public String toString() {
		return "AddUserInput [email=" + email + ", smartlink=" + smartlink + "]";
	}
}
