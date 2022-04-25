package com.uplooking.controller;

import java.io.OutputStream;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.uplooking.pojo.Perm;
import com.uplooking.pojo.Role;
import com.uplooking.pojo.User;
import com.uplooking.service.AdminService;
import com.uplooking.util.AuthCode;

@Controller
public class AdminController {
	
	@Autowired
	@Qualifier("adminService")
	private AdminService adminService;

	@RequestMapping(value="/user/test.do",method=RequestMethod.GET)
	public String method0(HttpServletRequest request){
		try {
			return "";
		} catch (Exception e) {
			e.printStackTrace();
			return "user/error";
		}
	}
	
	@RequestMapping(value="/user/login.do",method=RequestMethod.GET)
	public String method1(@RequestParam(value="op",defaultValue="0")int op,
			HttpServletRequest request){
		try {
			if(op==1){
				request.setAttribute("message", "请使用管理员身份登录");
			}
			return "user/login";
		} catch (Exception e) {
			e.printStackTrace();
			return "user/error";
		}
	}
	
	@RequestMapping(value="/user/login.do",method=RequestMethod.POST)
	public String method13(@RequestParam("uname") String name,@RequestParam("upwd") String pwd,
			HttpServletRequest request,HttpServletResponse response){
		try {
			Map<String, Object> map = adminService.adminLogin(name, pwd);
			if(200==Integer.parseInt(map.get("code").toString())){
				request.getSession().setAttribute("user", map.get("user"));
				response.sendRedirect(request.getContextPath()+"/admin/index.do");
			}else{
				request.setAttribute("message", map.get("message"));
			}
			return "user/login";
		} catch (Exception e) {
			e.printStackTrace();
			return "user/error";
		}
	}
	
	@RequestMapping(value="/user/authcode.do",method=RequestMethod.GET)
	public void method11(HttpServletRequest request,HttpServletResponse response){
		try {
			int length=4;
			String code = AuthCode.getAuthCodeString(length);
			request.getSession().setAttribute("auth", code);
			int width = 100;
			int height =25;
			OutputStream output = response.getOutputStream();
			AuthCode.getAuthCodeImage(width, height, output, code);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value="/user/check.do",method=RequestMethod.POST,
			produces={"application/json;charset=utf-8"})
	@ResponseBody
	public String method12(@RequestParam("code")String code,HttpServletRequest request){
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			String auth = request.getSession().getAttribute("auth").toString();
			if(code.equalsIgnoreCase(auth)){
				map.put("code", 200);
				map.put("message", "验证码输入正确");
			}else{
				map.put("code", 501);
				map.put("message", "验证码输入错误");
			}
			return JSONObject.toJSONString(map);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@RequestMapping(value="/admin/index.do",method=RequestMethod.GET)
	public String method2(){
		try {
			return "admin/index";
		} catch (Exception e) {
			e.printStackTrace();
			return "user/error";
		}
	}
	@RequestMapping(value="/admin/photo.do",method=RequestMethod.GET)
	public void method21(@RequestParam("id")String id,HttpServletResponse response){
		try {
			OutputStream out = response.getOutputStream();
			out.write(adminService.userPhoto(id));
			out.flush();
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@RequestMapping(value="/admin/tree.do",method=RequestMethod.GET,
			produces={"application/json;charset=utf-8"})
	@ResponseBody
	public String method22(@RequestParam("fid")String fid){
		try {
			String json = JSONObject.toJSONString(adminService.getTreeList(fid));
			//System.out.println(json);
			return json;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@RequestMapping(value="/admin/user.do",method=RequestMethod.GET)
	public String method3(@RequestParam(value="index",defaultValue="1")int index,
			@RequestParam(value="size",defaultValue="5")int size,HttpServletRequest request){
		try {
			String url = request.getContextPath()+"/admin/user.do";
			String key ="";
			if(request.getSession().getAttribute("key")!=null){
				key = request.getSession().getAttribute("key").toString();
			}
			request.setAttribute("pager", adminService.getUserList(key, index, size, url));
			return "admin/user";
		} catch (Exception e) {
			e.printStackTrace();
			return "user/error";
		}
	}
	
	@RequestMapping(value="/admin/user.do",method=RequestMethod.POST)
	public String method31(@RequestParam(value="key",defaultValue="")String key,
			@RequestParam(value="index",defaultValue="1")int index,
			@RequestParam(value="size",defaultValue="5")int size,HttpServletRequest request){
		try {
			String url = request.getContextPath()+"/admin/user.do";
			request.getSession().setAttribute("key", key);
			request.setAttribute("pager", adminService.getUserList(key, index, size, url));
			return "admin/user";
		} catch (Exception e) {
			e.printStackTrace();
			return "user/error";
		}
	}
	
	@RequestMapping(value="/admin/lock.do",method=RequestMethod.GET)
	public String method32(@RequestParam(value="id")String id,
			@RequestParam(value="index",defaultValue="1")int index,
			@RequestParam(value="size",defaultValue="5")int size,HttpServletRequest request){
		try {
			request.setAttribute("message", adminService.userLock(id).get("message"));
			String url = request.getContextPath()+"/admin/user.do";
			String key ="";
			if(request.getSession().getAttribute("key")!=null){
				key = request.getSession().getAttribute("key").toString();
			}
			request.setAttribute("pager", adminService.getUserList(key, index, size, url));
			return "admin/user";
		} catch (Exception e) {
			e.printStackTrace();
			return "user/error";
		}
	}
	
	@RequestMapping(value="/admin/userrole.do",method=RequestMethod.GET)
	public String method4(@RequestParam(value="id")String id,HttpServletRequest request){
		try {
			User userrole = adminService.getUserRole(id);
			List<Role> roles = adminService.getRoleList();
			request.setAttribute("userrole", userrole);
			for(Role role:roles){
				for(Role r:userrole.getRoles()){
					if(r.getRid().equals(role.getRid())){
						role.setChecked("checked");
					}
				}
			}
			request.setAttribute("roles", roles);
			return "admin/userrole";
		} catch (Exception e) {
			e.printStackTrace();
			return "user/error";
		}
	}
	
	@RequestMapping(value="/admin/userrole.do",method=RequestMethod.POST)
	public String method41(@RequestParam(value="uid")String uid,
			@RequestParam(value="rids")String[] rids,HttpServletRequest request){
		try {			
			adminService.udpUserRole(uid, rids);
			User userrole = adminService.getUserRole(uid);
			List<Role> roles = adminService.getRoleList();
			request.setAttribute("userrole", userrole);
			for(Role role:roles){
				for(Role r:userrole.getRoles()){
					if(r.getRid().equals(role.getRid())){
						role.setChecked("checked");
					}
				}
			}
			request.setAttribute("roles", roles);
			return "admin/userrole";
		} catch (Exception e) {
			e.printStackTrace();
			return "user/error";
		}
	}
	
	@RequestMapping(value="/admin/role.do",method=RequestMethod.GET)
	public String method5(HttpServletRequest request){
		try {
			request.setAttribute("roles", adminService.getRoleList());
			return "admin/role";
		} catch (Exception e) {
			e.printStackTrace();
			return "user/error";
		}
	}
	
	@RequestMapping(value="/admin/roleperm.do",method=RequestMethod.GET)
	public String method51(@RequestParam(value="id")String id,HttpServletRequest request){
		try {
			Role roleperm = adminService.getRolePerm(id);
			List<Perm> perms = adminService.getPermList();
			request.setAttribute("roleperm", roleperm);
			for(Perm perm:perms){
				for(Perm p:roleperm.getPerms()){
					if(p.getPid().equals(perm.getPid())){
						perm.setChecked("checked");
					}
				}
			}
			request.setAttribute("perms", perms);
			return "admin/roleperm";
		} catch (Exception e) {
			e.printStackTrace();
			return "user/error";
		}
	}
	
	@RequestMapping(value="/admin/roleperm.do",method=RequestMethod.POST)
	public String method52(@RequestParam(value="rid")String rid,
			@RequestParam(value="pids")String[] pids,HttpServletRequest request){
		try {			
			adminService.udpRolePerm(rid, pids);
			Role roleperm = adminService.getRolePerm(rid);
			List<Perm> perms = adminService.getPermList();
			request.setAttribute("roleperm", roleperm);
			for(Perm perm:perms){
				for(Perm p:roleperm.getPerms()){
					if(p.getPid().equals(perm.getPid())){
						perm.setChecked("checked");
					}
				}
			}
			request.setAttribute("perms", perms);
			return "admin/roleperm";
		} catch (Exception e) {
			e.printStackTrace();
			return "user/error";
		}
	}
}
