package com.uplooking.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.uplooking.pojo.Perm;


public interface PermMapper {
	List<Perm> findAll();
	int delete(@Param("rid")String rid);
	int insert(@Param("rid")String rid,@Param("pid")String pid);
}
