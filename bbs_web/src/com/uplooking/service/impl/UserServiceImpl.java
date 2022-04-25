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

import com.uplooking.dao.DictMapper;
import com.uplooking.dao.RoleMapper;
import com.uplooking.dao.UserMapper;
import com.uplooking.pojo.Dict;
import com.uplooking.pojo.User;
import com.uplooking.service.UserService;

@Service("userService")
public class UserServiceImpl implements UserService {

	private Map<String, Object> result =null;

	@Autowired
	@Qualifier("dictMapper")
	private DictMapper dictMapper;
	
	@Autowired
	@Qualifier("userMapper")
	private UserMapper userMapper;
	
	@Autowired
	@Qualifier("roleMapper")
	private RoleMapper roleMapper;
	
	@Override
	@Transactional(propagation=Propagation.SUPPORTS)
	public Map<String, Object> dictList() throws Exception {
		result = new HashMap<String, Object>();
		result.put("dsexList", dictMapper.findByType("101"));
		result.put("dbloodList", dictMapper.findByType("102"));
		result.put("dhobbyList", dictMapper.findByType("103"));
		result.put("dstatusList", dictMapper.findByType("104"));
		result.put("dtypeList", dictMapper.findByType("105"));
		return result;
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
		user.setRoles(roleMapper.findByUid(user.getUid()));
	}

	@Override
	@Transactional(propagation=Propagation.SUPPORTS)
	public Map<String, Object> userLogin(String name, String pwd) throws Exception {
		result = new HashMap<String, Object>();
		User user = userMapper.findByName(name);
		if(user!=null && pwd.equals(user.getUpwd())){
			if("1042".equals(user.getUstatus())){
				result.put("code", 402);
				result.put("message", "用户名已被锁定");
			}else{
				result.put("code", 200);
				result.put("message", "登录成功");
				setDict(user);
				result.put("user", user);
			}	
		}else{
			result.put("code", 401);
			result.put("message", "用户名或密码失败");
		}
		return result;
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public Map<String, Object> userRegist(User user) throws Exception {
		result = new HashMap<String, Object>();
		if(userMapper.findByName(user.getUname())==null){
			//注册后的状态 1041
			user.setUstatus("1041");
			if(userMapper.insert(user)==1){
				result.put("code", 200);
				result.put("message", "用户注册成功");
				//注册后角色默认为vip1
				if(userMapper.addRole(user.getUid())==1){
					//自动登录
					setDict(user);
					result.put("user", user);
				}else{
					result.put("code", 405);
					result.put("message", "用户注册授权失败");
				}
			}else{
				result.put("code", 404);
				result.put("message", "用户注册失败");
			}
		}else{
			result.put("code", 403);
			result.put("message", "用户名已被使用");
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
	public boolean existName(String name) throws Exception {
		return userMapper.findByName(name)!=null;
	}

	@Override
	@Transactional(propagation=Propagation.SUPPORTS)
	public boolean isPass(String name, String url) throws Exception {
		return userMapper.isPass(name, url)>0;
	}

}
