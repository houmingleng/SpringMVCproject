package com.uplooking.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.uplooking.pojo.Art;

public interface ArtMapper {
	int count(@Param("key")String key);
	List<Art> find(@Param("key")String key,@Param("start")int start,@Param("size")int size);
	Art findById(@Param("id")String id);
	int update(@Param("id")String id);
	int insert(Art art);
}
