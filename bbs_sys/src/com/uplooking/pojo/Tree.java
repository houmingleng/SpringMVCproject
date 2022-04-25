package com.uplooking.pojo;

import java.io.Serializable;

public class Tree implements Serializable {
	private String tid;
	private String ttitle;
	private String turl;
	private String tdesc;
	private String tfid;
	private Integer tlvl;
	public String getTid() {
		return tid;
	}
	public void setTid(String tid) {
		this.tid = tid;
	}
	public String getTtitle() {
		return ttitle;
	}
	public void setTtitle(String ttitle) {
		this.ttitle = ttitle;
	}
	public String getTurl() {
		return turl;
	}
	public void setTurl(String turl) {
		this.turl = turl;
	}
	public String getTdesc() {
		return tdesc;
	}
	public void setTdesc(String tdesc) {
		this.tdesc = tdesc;
	}
	public String getTfid() {
		return tfid;
	}
	public void setTfid(String tfid) {
		this.tfid = tfid;
	}
	public Integer getTlvl() {
		return tlvl;
	}
	public void setTlvl(Integer tlvl) {
		this.tlvl = tlvl;
	}
	public Tree() {
		super();
	}
	@Override
	public String toString() {
		return "Tree [tid=" + tid + ", ttitle=" + ttitle + ", turl=" + turl + ", tdesc=" + tdesc + ", tfid=" + tfid
				+ ", tlvl=" + tlvl + "]";
	}
	
}
