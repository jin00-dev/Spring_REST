package com.itwillbs.domain;

import jdk.jfr.DataAmount;

public class SampleVO {

	private Integer sno;
	private String name; 
	private String email;
	
	
	public Integer getSno() {
		return sno;
	}
	public void setSno(Integer sno) {
		this.sno = sno;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	@Override
	public String toString() {
		return "SampleVO [sno=" + sno + ", name=" + name + ", email=" + email + "]";
	}
	
	
}
