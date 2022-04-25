package com.uplooking.pojo;

import java.io.Serializable;

public class Rpt implements Serializable {
	private String oid;
	private String ofid;
	private String oaid;
	private String ouid;
	private String omessage;
	private String odate;
	public String getOid() {
		return oid;
	}
	public void setOid(String oid) {
		this.oid = oid;
	}
	public String getOfid() {
		return ofid;
	}
	public void setOfid(String ofid) {
		this.ofid = ofid;
	}
	public String getOaid() {
		return oaid;
	}
	public void setOaid(String oaid) {
		this.oaid = oaid;
	}
	public String getOuid() {
		return ouid;
	}
	public void setOuid(String ouid) {
		this.ouid = ouid;
	}
	public String getOmessage() {
		return omessage;
	}
	public void setOmessage(String omessage) {
		this.omessage = omessage;
	}
	public String getOdate() {
		return odate;
	}
	public void setOdate(String odate) {
		this.odate = odate;
	}
	public Rpt() {
		super();
	}
	@Override
	public String toString() {
		return "Rpt [oid=" + oid + ", ofid=" + ofid + ", oaid=" + oaid + ", ouid=" + ouid + ", omessage=" + omessage
				+ ", odate=" + odate + "]";
	}
	private User user;
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
}
