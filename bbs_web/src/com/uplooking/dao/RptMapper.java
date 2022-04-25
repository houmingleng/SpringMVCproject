package com.uplooking.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.uplooking.pojo.Rpt;

public interface RptMapper {
	int count(@Param("aid")String aid);
	List<Rpt> find(@Param("aid")String aid,@Param("start")int start,@Param("size")int size);
	int maxFloor(@Param("aid")String aid);
	int insert(Rpt rpt);
}
