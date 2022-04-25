package com.uplooking.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.sun.corba.se.spi.orbutil.fsm.Guard.Result;
import com.uplooking.dao.DictMapper;
import com.uplooking.dao.PermMapper;
import com.uplooking.dao.RoleMapper;
import com.uplooking.dao.TreeMapper;
import com.uplooking.dao.UserMapper;
import com.uplooking.pojo.Dict;
import com.uplooking.pojo.Perm;
import com.uplooking.pojo.Role;
import com.uplooking.pojo.Tree;
import com.uplooking.pojo.User;
import com.uplooking.service.AdminService;
import com.uplooking.util.Pager;

@Service("adminService")
public class AdminServiceImpl implements AdminService {

	private Map<String, Object> result =null;

	@Autowired
	@Qualifier("dictMapper")
	private DictMapper dictMapper;
	
	@Autowired
	@Qualifier("treeMapper")
	private TreeMapper treeMapper;
	
	@Autowired
	@Qualifier("userMapper")
	private UserMapper userMapper;
	
	@Autowired
	@Qualifier("roleMapper")
	private RoleMapper roleMapper;
	
	@Autowired
	@Qualifier("permMapper")
	private PermMapper permMapper;
	
	@Override
	@Transactional(propagation=Propagation.SUPPORTS)
	public Map<String, Object> getDictList() throws Exception {
		result = new HashMap<String, Object>();
		result.put("dsexList", dictMapper.findByType("101"));
		result.put("dbloodList", dictMapper.findByType("102"));
		result.put("dhobbyList", dictMapper.findByType("103"));
		result.put("dstatusList", dictMapper.findByType("104"));
		result.put("dtypeList", dictMapper.findByType("105"));
		return result;
	}
	
	@Override
	@Transactional(propagation=Propagation.SUPPORTS)
	public List<Tree> getTreeList(String fid) throws Exception {
		return treeMapper.findByFid(fid);
	}
	
	private void setDict(User user){
		user.setDsex(dictMapper.findByKey(user.getUsex()));
		user.setDblood(dictMapper.findByKey(user.getUblood()));
		user.setDstatus(dictMapper.findByKey(user.getUstatus()));
		List<Dict> hobbys = new ArrayList<>();
		for(String str:user.getUhobby().split(",")){
			hobbys.add(dictMapper.findByKey(str));
		}
		user.setDhobby(hobbys);
	}

	@Override
	@Transactional(propagation=Propagation.SUPPORTS)
	public Map<String, Object> adminLogin(String name, String pwd) throws Exception {
		result = new HashMap<String, Object>();
		User user = userMapper.findByName(name);
		if(user!=null && "1043".equals(user.getUstatus()) && pwd.equals(user.getUpwd())){
			result.put("code", 200);
			result.put("message", "管理员登录成功");
			setDict(user);
			result.put("user", user);
		}else{
			result.put("code", 301);
			result.put("message", "管理员登录失败");
		}
		return result;
	}
	
	@Override
	@Transactional(propagation=Propagation.SUPPORTS)
	public byte[] userPhoto(String id) throws Exception {
		return userMapper.getPhoto(id).getUphoto();
	}

	@Override
	@Transactional(propagation=Propagation.SUPPORTS)
	public Pager getUserList(String key, int index, int size, String url) throws Exception {
		List<User> items = userMapper.find(key, (index-1)*size, size);
		for(User user:items){
			setDict(user);
		}
		return new Pager(index, userMapper.count(key), size, url, items);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public Map<String, Object> userLock(String id) throws Exception {
		result = new HashMap<String, Object>();
		if("1041".equals(userMapper.getStatus(id).getUstatus())){
			//锁定
			if(userMapper.lock(id, "1042")==1){
				result.put("code", 200);
				result.put("message", "锁定用户成功");
			}else{
				result.put("code", 302);
				result.put("message", "锁定用户失败");
			}
		}else if("1042".equals(userMapper.getStatus(id).getUstatus())){
			//解锁
			if(userMapper.lock(id, "1041")==1){
				result.put("code", 200);
				result.put("message", "解锁用户成功");
			}else{
				result.put("code", 303);
				result.put("message", "解锁用户失败");
			}
		}
		return result;
	}

	@Override
	@Transactional(propagation=Propagation.SUPPORTS)
	public User getUserRole(String id) throws Exception {
		return userMapper.getUserRole(id);
	}

	@Override
	@Transactional(propagation=Propagation.SUPPORTS)
	public List<Role> getRoleList() throws Exception {
		return roleMapper.findAll();
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public void udpUserRole(String uid, String[] rids) throws Exception {
		//先删除关联角色 添加新角色 -> 
		roleMapper.delete(uid);
		for(String rid:rids){
			roleMapper.insert(uid, rid);
		}
	}

	@Override
	@Transactional(propagation=Propagation.SUPPORTS)
	public Role getRolePerm(String id) throws Exception {
		return roleMapper.getRolePerm(id);
	}

	@Override
	@Transactional(propagation=Propagation.SUPPORTS)
	public List<Perm> getPermList() throws Exception {
		return permMapper.findAll();
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public void udpRolePerm(String rid, String[] pids) throws Exception {
		permMapper.delete(rid);
		for(String pid:pids){
			permMapper.insert(rid, pid);
		}
	}

}
