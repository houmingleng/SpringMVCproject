package com.uplooking.service;

import java.util.List;
import java.util.Map;

import com.uplooking.pojo.Dict;
import com.uplooking.pojo.Perm;
import com.uplooking.pojo.Role;
import com.uplooking.pojo.Tree;
import com.uplooking.pojo.User;
import com.uplooking.util.Pager;

public interface AdminService {
	Map<String, Object> getDictList() throws Exception;
	List<Tree> getTreeList(String fid) throws Exception;
	Map<String,Object> adminLogin(String name,String pwd) throws Exception;
	byte[] userPhoto(String id) throws Exception;
	Pager getUserList(String key,int index,int size,String url) throws Exception;
	Map<String,Object> userLock(String id) throws Exception;
	User getUserRole(String id) throws Exception;
	List<Role> getRoleList() throws Exception;
	void udpUserRole(String uid,String[] rids) throws Exception;
	Role getRolePerm(String id) throws Exception;
	List<Perm> getPermList() throws Exception;
	void udpRolePerm(String rid,String[] pids) throws Exception;
}
