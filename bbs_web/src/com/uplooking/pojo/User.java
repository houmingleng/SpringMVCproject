package com.uplooking.pojo;

import java.io.Serializable;
import java.util.List;

public class User implements Serializable {
	private String uid;
	private String uname;
	private String upwd;
	private String usex;
	private String ublood;
	private String uhobby;
	private String ubirth;
	private byte[] uphoto;
	private String ustatus;
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public String getUname() {
		return uname;
	}
	public void setUname(String uname) {
		this.uname = uname;
	}
	public String getUpwd() {
		return upwd;
	}
	public void setUpwd(String upwd) {
		this.upwd = upwd;
	}
	public String getUsex() {
		return usex;
	}
	public void setUsex(String usex) {
		this.usex = usex;
	}
	public String getUblood() {
		return ublood;
	}
	public void setUblood(String ublood) {
		this.ublood = ublood;
	}
	public String getUhobby() {
		return uhobby;
	}
	public void setUhobby(String uhobby) {
		this.uhobby = uhobby;
	}
	public String getUbirth() {
		return ubirth;
	}
	public void setUbirth(String ubirth) {
		this.ubirth = ubirth;
	}
	public byte[] getUphoto() {
		return uphoto;
	}
	public void setUphoto(byte[] uphoto) {
		this.uphoto = uphoto;
	}
	public String getUstatus() {
		return ustatus;
	}
	public void setUstatus(String ustatus) {
		this.ustatus = ustatus;
	}
	public User() {
		super();
	}
	@Override
	public String toString() {
		return "User [uid=" + uid + ", uname=" + uname + ", upwd=" + upwd + ", usex=" + usex + ", ublood=" + ublood
				+ ", uhobby=" + uhobby + ", ubirth=" + ubirth + ", ustatus=" + ustatus + "]";
	}

	private Dict dsex;
	private Dict dblood;
	private List<Dict> dhobby;
	private Dict dstatus;
	public Dict getDsex() {
		return dsex;
	}
	public void setDsex(Dict dsex) {
		this.dsex = dsex;
	}
	public Dict getDblood() {
		return dblood;
	}
	public void setDblood(Dict dblood) {
		this.dblood = dblood;
	}
	public List<Dict> getDhobby() {
		return dhobby;
	}
	public void setDhobby(List<Dict> dhobby) {
		this.dhobby = dhobby;
	}
	public Dict getDstatus() {
		return dstatus;
	}
	public void setDstatus(Dict dstatus) {
		this.dstatus = dstatus;
	}
	
	private List<Role> roles;
	public List<Role> getRoles() {
		return roles;
	}
	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}
	
	private String info;
	public String getInfo() {
		StringBuffer strb = new StringBuffer();
		strb.append("账户："+uname+"\r\n");
		if(dsex!=null)strb.append("性别："+dsex.getDvalue()+"\r\n");
		if(dblood!=null)strb.append("血型："+dblood.getDvalue()+"\r\n");
		if(dhobby!=null && dhobby.size()>0){
			strb.append("爱好：[");
			for(Dict dict:dhobby){
				strb.append(dict.getDvalue()+" ");
			}
			strb.append("]\r\n");
		}
		strb.append("生日："+ubirth+"\r\n");
		if(dstatus!=null)strb.append("状态："+dstatus.getDvalue()+"\r\n");
		if(roles!=null&&roles.size()>0){
			strb.append("角色：[");
			for(Role role:roles){
				strb.append(role.getRname()+" ");
			}
			strb.append("]\r\n");
		}
		return strb.toString();
	}
	public void setInfo(String info) {
		this.info = info;
	}
}
