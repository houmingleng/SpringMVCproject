package com.uplooking.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.omg.PortableInterceptor.USER_EXCEPTION;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONObject;
import com.uplooking.pojo.Art;
import com.uplooking.pojo.User;
import com.uplooking.service.ArtService;
import com.uplooking.service.UserService;

@Controller
public class TieController {

	@Autowired
	@Qualifier("userService")
	private UserService userService;
	
	@Autowired
	@Qualifier("artService")
	private ArtService artService;

	@RequestMapping(value="/user/test1.do",method=RequestMethod.GET)
	public String method0(HttpServletRequest request){
		try {
			return "";
		} catch (Exception e) {
			e.printStackTrace();
			return "user/error";
		}
	}
	
	@RequestMapping(value="/user/keyword.do",method=RequestMethod.POST,
			produces={"application/json;charset=utf-8"})
	@ResponseBody
	public String method1(@RequestParam("key")String key){
		try {
			return JSONObject.toJSONString(artService.getWordList(key));
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@RequestMapping(value="/user/artlist.do",method=RequestMethod.POST,
			produces={"application/json;charset=utf-8"})
	@ResponseBody
	public String method11(@RequestParam("key")String key,
			@RequestParam(value="size",defaultValue="20")int size){
		try {
			String url = "";
			return JSONObject.toJSONString(artService.getArtList(key, size, url));
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@RequestMapping(value="/user/artpager.do",method=RequestMethod.POST,
			produces={"application/json;charset=utf-8"})
	@ResponseBody
	public String method13(@RequestParam("key")String key,
			@RequestParam(value="index",defaultValue="1")int index,
			@RequestParam(value="size",defaultValue="20")int size){
		try {
			String url = "";
			return JSONObject.toJSONString(artService.getArtList(key,index,size, url));
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@RequestMapping(value="/tie/upload.do",method=RequestMethod.GET)
	public String method21(HttpServletRequest request){
		try {
			Map<String, Object> map = userService.dictList();
			request.setAttribute("dtypeList", map.get("dtypeList"));
			return "tie/upload";
		} catch (Exception e) {
			e.printStackTrace();
			return "user/error";
		}
	}
	
	@RequestMapping(value="/tie/upload.do",method=RequestMethod.POST)
	public String method22(Art art,@RequestParam("files") MultipartFile[] files,
			HttpServletRequest request){
		try {
			User user = (User) request.getSession().getAttribute("user");
			art.setAuid(user.getUid());
			request.setAttribute("message", artService.artUpload(art, files).get("message"));
			Map<String, Object> map = userService.dictList();
			request.setAttribute("dtypeList", map.get("dtypeList"));
			return "tie/upload";
		} catch (Exception e) {
			e.printStackTrace();
			return "user/error";
		}
	}
	
	@RequestMapping(value="/tie/info.do",method=RequestMethod.GET)
	public String method31(@RequestParam("id")String id,HttpServletRequest request){
		try {
			Map<String, Object> map = artService.getArt(id);
			request.setAttribute("art", map.get("art"));
			request.setAttribute("exts", map.get("exts"));
			request.setAttribute("rpts", map.get("rpts"));
			return "tie/info";
		} catch (Exception e) {
			e.printStackTrace();
			return "user/error";
		}
	}
	
	@RequestMapping(value="/tie/report.do",method=RequestMethod.POST)
	public String method31(@RequestParam("aid")String aid,@RequestParam("msg")String msg,
			HttpServletRequest request){
		try {
			User user = (User) request.getSession().getAttribute("user");
			request.setAttribute("message", artService.addRpt(aid, user.getUid(), msg));
			Map<String, Object> map = artService.getArt(aid);
			request.setAttribute("art", map.get("art"));
			request.setAttribute("exts", map.get("exts"));
			request.setAttribute("rpts", map.get("rpts"));
			return "tie/info";
		} catch (Exception e) {
			e.printStackTrace();
			return "user/error";
		}
	}
	
	@RequestMapping(value="/tie/pagereport.do",method=RequestMethod.POST,
			produces={"application/json;charset=utf-8"})
	@ResponseBody
	public String method32(@RequestParam("aid")String aid,
			@RequestParam(value="index",defaultValue="1")int index,
			@RequestParam(value="size",defaultValue="10")int size){
		try {
			String url = "";
			return JSONObject.toJSONString(artService.getRptList(aid, index, size, url));
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
}
