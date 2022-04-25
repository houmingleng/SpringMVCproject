package com.uplooking.pojo;

import java.io.Serializable;

public class Dict implements Serializable {
	private String did;
	private String dtid;
	private String dkey;
	private String dvalue;
	private String dis;
	public String getDid() {
		return did;
	}
	public void setDid(String did) {
		this.did = did;
	}
	public String getDtid() {
		return dtid;
	}
	public void setDtid(String dtid) {
		this.dtid = dtid;
	}
	public String getDkey() {
		return dkey;
	}
	public void setDkey(String dkey) {
		this.dkey = dkey;
	}
	public String getDvalue() {
		return dvalue;
	}
	public void setDvalue(String dvalue) {
		this.dvalue = dvalue;
	}
	public String getDis() {
		return dis;
	}
	public void setDis(String dis) {
		this.dis = dis;
	}
	public Dict() {
		super();
	}
	@Override
	public String toString() {
		return "Dict [did=" + did + ", dtid=" + dtid + ", dkey=" + dkey + ", dvalue=" + dvalue + ", dis=" + dis + "]";
	}
	
}
