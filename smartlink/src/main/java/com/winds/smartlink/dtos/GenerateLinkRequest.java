package com.winds.smartlink.dtos;

import java.io.Serializable;

public class GenerateLinkRequest implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String mem;
	private String id_conten;
	private String gmail;
	private Long mahoa;
	private Long id_mem;
	private String id_kenh;
	
	public String getId_conten() {
		return id_conten;
	}

	public void setId_conten(String id_conten) {
		this.id_conten = id_conten;
	}

	public String getGmail() {
		return gmail;
	}

	public void setGmail(String gmail) {
		this.gmail = gmail;
	}

	public Long getMahoa() {
		return mahoa;
	}

	public void setMahoa(Long mahoa) {
		this.mahoa = mahoa;
	}

	public Long getId_mem() {
		return id_mem;
	}

	public void setId_mem(Long id_mem) {
		this.id_mem = id_mem;
	}

	public String getId_kenh() {
		return id_kenh;
	}

	public void setId_kenh(String id_kenh) {
		this.id_kenh = id_kenh;
	}

	public String getMem() {
		return mem;
	}

	public void setMem(String mem) {
		this.mem = mem;
	}

	@Override
	public String toString() {
		return "GenerateLinkRequest [mem=" + mem + ", id_conten=" + id_conten + ", gmail=" + gmail + ", mahoa=" + mahoa
				+ ", id_mem=" + id_mem + ", id_kenh=" + id_kenh + "]";
	}
}
