package com.uplooking.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.uplooking.pojo.Tree;

public interface TreeMapper {
	List<Tree> findByFid(@Param("fid")String fid);
}
