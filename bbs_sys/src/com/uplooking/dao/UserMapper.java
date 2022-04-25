package com.uplooking.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.uplooking.pojo.User;

public interface UserMapper {
	User findByName(@Param("name")String name);
	User getPhoto(@Param("id")String id);
	int count(@Param("key")String key);
	List<User> find(@Param("key")String key,@Param("start")int start,@Param("size")int size);
	User getStatus(@Param("id")String id);
	int lock(@Param("id")String id,@Param("status")String status);
	User getUserRole(@Param("id")String id);
}
