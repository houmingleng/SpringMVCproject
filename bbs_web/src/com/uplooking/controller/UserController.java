package com.uplooking.controller;

import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONObject;
import com.uplooking.pojo.User;
import com.uplooking.service.UserService;
import com.uplooking.util.AuthCode;

@Controller
public class UserController {
	
	@Autowired
	@Qualifier("userService")
	private UserService userService;

	@RequestMapping(value="/user/test.do",method=RequestMethod.GET)
	public String method0(HttpServletRequest request){
		try {
			return "";
		} catch (Exception e) {
			e.printStackTrace();
			return "user/error";
		}
	}
	
	@RequestMapping(value="/user/index.do",method=RequestMethod.GET)
	public String method1(HttpServletRequest request){
		try {
			return "user/index";
		} catch (Exception e) {
			e.printStackTrace();
			return "user/error";
		}
	}
	
	@RequestMapping(value="/user/login.do",method=RequestMethod.GET)
	public String method11(@RequestParam(value="op",defaultValue="0")int op,
			HttpServletRequest request){
		try {
			if(op==1){
				request.setAttribute("message", "请先登录");
			}else if(op==2){
				request.setAttribute("message", "权限不足");
			}
			return "user/login";
		} catch (Exception e) {
			e.printStackTrace();
			return "user/error";
		}
	}
	
	@RequestMapping(value="/user/login.do",method=RequestMethod.POST)
	public String method12(@RequestParam("uname") String name,@RequestParam("upwd") String pwd,
			HttpServletRequest request,HttpServletResponse response){
		try {
			Map<String, Object> map = userService.userLogin(name, pwd);
			if(200==Integer.parseInt(map.get("code").toString())){
				request.getSession().setAttribute("user", map.get("user"));
				response.sendRedirect(request.getContextPath()+"/user/index.do");
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
	public void method13(HttpServletRequest request,HttpServletResponse response){
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
	public String method14(@RequestParam("code")String code,HttpServletRequest request){
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
	
	@RequestMapping(value="/user/logout.do",method=RequestMethod.GET)
	public void method15(HttpServletRequest request,HttpServletResponse response){
		try {
			request.getSession().invalidate();
			response.sendRedirect(request.getContextPath()+"/user/index.do");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value="/user/photo.do",method=RequestMethod.GET)
	public void method16(@RequestParam("id")String id,HttpServletResponse response){
		try {
			OutputStream out = response.getOutputStream();
			out.write(userService.userPhoto(id));
			out.flush();
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value="/user/reg.do",method=RequestMethod.GET)
	public String method2(HttpServletRequest request){
		try {
			Map<String, Object> map = userService.dictList();
			request.setAttribute("dsexList", map.get("dsexList"));
			request.setAttribute("dbloodList", map.get("dbloodList"));
			request.setAttribute("dhobbyList", map.get("dhobbyList"));
			return "user/reg";
		} catch (Exception e) {
			e.printStackTrace();
			return "user/error";
		}
	}
	
	@RequestMapping(value="/user/reg.do",method=RequestMethod.POST)
	public String method21(User user,@RequestParam("photo")MultipartFile photo,
			HttpServletRequest request,HttpServletResponse response){
		try {
			user.setUphoto(photo.getBytes());
			Map<String, Object> result = userService.userRegist(user);
			if(200==Integer.parseInt(result.get("code").toString())){
				request.getSession().setAttribute("user", result.get("user"));
				response.sendRedirect(request.getContextPath()+"/user/index.do");
			}else{
				request.setAttribute("message", result.get("message"));
			}
			
			Map<String, Object> map = userService.dictList();
			request.setAttribute("dsexList", map.get("dsexList"));
			request.setAttribute("dbloodList", map.get("dbloodList"));
			request.setAttribute("dhobbyList", map.get("dhobbyList"));
			return "user/reg";
		} catch (Exception e) {
			e.printStackTrace();
			return "user/error";
		}
	}
	
	@RequestMapping(value="/user/exist.do",method=RequestMethod.POST,
			produces={"application/json;charset=utf-8"})
	@ResponseBody
	public String method22(@RequestParam("name")String name){
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			if(!userService.existName(name)){
				map.put("code", 200);
				map.put("message", "该用户名可以注册");
			}else{
				map.put("code", 502);
				map.put("message", "该用户名已被使用");
			}
			return JSONObject.toJSONString(map);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
