package com.uplooking.test;

import static org.junit.Assert.*;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.uplooking.pojo.User;
import com.uplooking.service.ArtService;
import com.uplooking.service.UserService;

public class MyTest {

	@Test
	public void test1() throws Exception {
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		ArtService artService = (ArtService) context.getBean("artService");
		String key ="Spring";
		int size=5;
		String url="";
		int index=1;
		//System.out.println(artService.getWordList("Spring"));
		//System.out.println(artService.getArtList(key, size, url));
		//System.out.println(artService.getArtList(key, index, size, url));
		String aid ="1";
		//System.out.println(artService.getArt(aid));
		//System.out.println(artService.addRpt(aid, "2", "ADD"));
		//System.out.println(artService.getRptList(aid, index, size, url));
		System.out.println(artService.extDownload("5"));
	}
	
	public void test() throws Exception {
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		//System.out.println(context.getBean("userService"));
		UserService userService = (UserService) context.getBean("userService");
		System.out.println(userService.dictList());
		String name="zhangsan";
		String pwd = "a123123";
		//System.out.println(userService.existName(name));
		//System.out.println(userService.userLogin(name, pwd));
		User user = (User) userService.userLogin(name, pwd).get("user");
		System.out.println(user.getInfo());
	}

}
