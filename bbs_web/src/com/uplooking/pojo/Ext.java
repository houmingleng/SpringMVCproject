package com.uplooking.pojo;

import java.io.Serializable;

public class Ext implements Serializable {
	private String eid;
	private String eaid;
	private String ename;
	private Long esize;
	private String etype;
	private String esuffix;
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
	public String getEsuffix() {
		return esuffix;
	}
	public void setEsuffix(String esuffix) {
		this.esuffix = esuffix;
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
				+ ", esuffix=" + esuffix + ", epath=" + epath + "]";
	}
	
	private String info;
	public String getInfo() {
		StringBuffer strb = new StringBuffer();
		strb.append("名称:"+ename+"\r\n");
		strb.append("类型:"+etype+"\r\n");
		strb.append("大小:"+(esize/1024)+"K"+"\r\n");
		return strb.toString();
	}
	public void setInfo(String info) {
		this.info = info;
	}
	
}
