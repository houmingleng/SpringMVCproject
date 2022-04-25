package com.uplooking.pojo;

import java.io.Serializable;
import java.util.List;

public class Role implements Serializable {
	private String rid;
	private String rname;
	private String rdesc;
	public String getRid() {
		return rid;
	}
	public void setRid(String rid) {
		this.rid = rid;
	}
	public String getRname() {
		return rname;
	}
	public void setRname(String rname) {
		this.rname = rname;
	}
	public String getRdesc() {
		return rdesc;
	}
	public void setRdesc(String rdesc) {
		this.rdesc = rdesc;
	}
	public Role() {
		super();
	}
	@Override
	public String toString() {
		return "Role [rid=" + rid + ", rname=" + rname + ", rdesc=" + rdesc + "]";
	}
	private List<Perm> perms;
	public List<Perm> getPerms() {
		return perms;
	}
	public void setPerms(List<Perm> perms) {
		this.perms = perms;
	}
	
	private String checked;
	public String getChecked() {
		return checked;
	}
	public void setChecked(String checked) {
		this.checked = checked;
	}
	
}
