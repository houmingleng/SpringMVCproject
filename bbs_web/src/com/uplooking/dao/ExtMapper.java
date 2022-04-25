package com.uplooking.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.uplooking.pojo.Ext;

public interface ExtMapper {
	List<Ext> findByAid(@Param("aid")String aid);
	Ext findById(@Param("id")String id);
	int insert(Ext ext);
}
