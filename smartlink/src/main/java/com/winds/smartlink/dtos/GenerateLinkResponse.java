package com.winds.smartlink.dtos;

import java.io.Serializable;

public class GenerateLinkResponse implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Long mem;
	private Long mahoa;
	private String url;
	
	public Long getMem() {
		return mem;
	}
	public void setMem(Long mem) {
		this.mem = mem;
	}
	public Long getMahoa() {
		return mahoa;
	}
	public void setMahoa(Long mahoa) {
		this.mahoa = mahoa;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	
	

}
