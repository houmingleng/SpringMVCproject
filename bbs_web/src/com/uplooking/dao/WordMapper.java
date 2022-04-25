package com.uplooking.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.uplooking.pojo.Word;

public interface WordMapper {
	List<Word> find(@Param("key")String key);
	int exist(@Param("key")String key);
	int insert(@Param("key")String key);
	int update(@Param("key")String key);
}
