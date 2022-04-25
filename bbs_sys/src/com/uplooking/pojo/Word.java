package com.uplooking.pojo;

import java.io.Serializable;

public class Word implements Serializable {
	private String kid;
	private String kname;
	private Integer kclick;
	public String getKid() {
		return kid;
	}
	public void setKid(String kid) {
		this.kid = kid;
	}
	public String getKname() {
		return kname;
	}
	public void setKname(String kname) {
		this.kname = kname;
	}
	public Integer getKclick() {
		return kclick;
	}
	public void setKclick(Integer kclick) {
		this.kclick = kclick;
	}
	public Word() {
		super();
	}
	@Override
	public String toString() {
		return "Word [kid=" + kid + ", kname=" + kname + ", kclick=" + kclick + "]";
	}
	
}
