package com.uplooking.pojo;

import java.io.Serializable;

public class Perm implements Serializable {
	private String pid;
	private String purl;
	private String ptoken;
	private String pdesc;
	public String getPid() {
		return pid;
	}
	public void setPid(String pid) {
		this.pid = pid;
	}
	public String getPurl() {
		return purl;
	}
	public void setPurl(String purl) {
		this.purl = purl;
	}
	public String getPtoken() {
		return ptoken;
	}
	public void setPtoken(String ptoken) {
		this.ptoken = ptoken;
	}
	public String getPdesc() {
		return pdesc;
	}
	public void setPdesc(String pdesc) {
		this.pdesc = pdesc;
	}
	public Perm() {
		super();
	}
	@Override
	public String toString() {
		return "Perm [pid=" + pid + ", purl=" + purl + ", ptoken=" + ptoken + ", pdesc=" + pdesc + "]";
	}
	private String checked;
	public String getChecked() {
		return checked;
	}
	public void setChecked(String checked) {
		this.checked = checked;
	}
	
}
