package com.uplooking.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.uplooking.pojo.User;

public interface UserMapper {
	User findByName(@Param("name")String name);
	User getPhoto(@Param("id")String id);
	int insert(User user);
	int isPass(@Param("name")String name,@Param("url")String url);
	int addRole(@Param("id")String id);
}
