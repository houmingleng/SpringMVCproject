package com.uplooking.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.uplooking.pojo.Role;

public interface RoleMapper {
	List<Role> findByUid(@Param("uid")String uid);
}
