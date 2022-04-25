package com.uplooking.pojo;

import java.io.Serializable;

public class Ext implements Serializable {
	private String eid;
	private String eaid;
	private String ename;
	private Long esize;
	private String etype;
	private String suffix;
	private String epath;
	public String getEid() {
		return eid;
	}
	public void setEid(String eid) {
		this.eid = eid;
	}
	public String getEaid() {
		return eaid;
	}
	public void setEaid(String eaid) {
		this.eaid = eaid;
	}
	public String getEname() {
		return ename;
	}
	public void setEname(String ename) {
		this.ename = ename;
	}
	public Long getEsize() {
		return esize;
	}
	public void setEsize(Long esize) {
		this.esize = esize;
	}
	public String getEtype() {
		return etype;
	}
	public void setEtype(String etype) {
		this.etype = etype;
	}
	public String getSuffix() {
		return suffix;
	}
	public void setSuffix(String suffix) {
		this.suffix = suffix;
	}
	public String getEpath() {
		return epath;
	}
	public void setEpath(String epath) {
		this.epath = epath;
	}
	public Ext() {
		super();
	}
	@Override
	public String toString() {
		return "Ext [eid=" + eid + ", eaid=" + eaid + ", ename=" + ename + ", esize=" + esize + ", etype=" + etype
				+ ", suffix=" + suffix + ", epath=" + epath + "]";
	}
	
}
