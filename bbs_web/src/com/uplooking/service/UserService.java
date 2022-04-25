package com.uplooking.service;

import java.util.Map;

import com.uplooking.pojo.User;

public interface UserService {
	Map<String,Object> dictList() throws Exception;
	Map<String,Object> userLogin(String name,String pwd) throws Exception;
	Map<String,Object> userRegist(User user) throws Exception;
	byte[] userPhoto(String id) throws Exception;
	boolean existName(String name) throws Exception;
	boolean isPass(String name,String url) throws Exception;
}
