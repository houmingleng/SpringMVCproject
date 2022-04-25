package com.uplooking.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.uplooking.pojo.Role;

public interface RoleMapper {
	List<Role> findAll();
	int delete(@Param("uid")String uid);
	int insert(@Param("uid")String uid,@Param("rid")String rid);
	Role getRolePerm(@Param("id")String id);
}
