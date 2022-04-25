package com.uplooking.test;

import static org.junit.Assert.*;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.uplooking.dao.UserMapper;
import com.uplooking.pojo.Role;
import com.uplooking.pojo.User;
import com.uplooking.service.AdminService;

public class MyTest {

	@Test
	public void test() throws Exception {
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		AdminService adminService = (AdminService) context.getBean("adminService");
		//System.out.println(adminService.getDictList());
		//System.out.println(adminService.getTreeList("1"));
		/*String name="administrator";
		String pwd="Welcome_1";
		System.out.println(adminService.adminLogin(name, pwd));*/
		/*String key="";
		int index=1;
		int size=5;
		String url="";
		System.out.println(adminService.getUserList(key, index, size, url));*/
		//System.out.println(adminService.userLock("2"));
		/*String id = "41";
		User user = adminService.getUserRole(id);
		System.out.println(user.getUname());
		System.out.println(user.getRoles());
		
		System.out.println(adminService.getRoleList());
		String[] rids = {"1","2"};
		adminService.udpUserRole(id,rids);
		
		user = adminService.getUserRole(id);
		System.out.println(user.getUname());
		System.out.println(user.getRoles());*/
		
		String id = "1";
		Role role = adminService.getRolePerm(id);
		System.out.println(role.getRname());
		System.out.println(role.getPerms());
		String[] pids = {"1","4","5"};
		adminService.udpRolePerm(id, pids);
		role = adminService.getRolePerm(id);
		System.out.println(role.getRname());
		System.out.println(role.getPerms());
		
	}

}
