package com.uplooking.pojo;

import java.io.Serializable;

public class Art implements Serializable {
	private String aid;
	private String adid;
	private String atitle;
	private String acontext;
	private String auid;
	private String adate;
	private Integer aclick;
	public String getAid() {
		return aid;
	}
	public void setAid(String aid) {
		this.aid = aid;
	}
	public String getAdid() {
		return adid;
	}
	public void setAdid(String adid) {
		this.adid = adid;
	}
	public String getAtitle() {
		return atitle;
	}
	public void setAtitle(String atitle) {
		this.atitle = atitle;
	}
	public String getAcontext() {
		return acontext;
	}
	public void setAcontext(String acontext) {
		this.acontext = acontext;
	}
	public String getAuid() {
		return auid;
	}
	public void setAuid(String auid) {
		this.auid = auid;
	}
	public String getAdate() {
		return adate;
	}
	public void setAdate(String adate) {
		this.adate = adate;
	}
	public Integer getAclick() {
		return aclick;
	}
	public void setAclick(Integer aclick) {
		this.aclick = aclick;
	}
	public Art() {
		super();
	}
	@Override
	public String toString() {
		return "Art [aid=" + aid + ", adid=" + adid + ", atitle=" + atitle + ", acontext=" + acontext + ", auid=" + auid
				+ ", adate=" + adate + ", aclick=" + aclick + "]";
	}
	
}
