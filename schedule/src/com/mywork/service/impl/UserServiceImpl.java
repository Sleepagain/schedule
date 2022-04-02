package com.mywork.service.impl;
/**
 * 用户业务层实现
 */

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mywork.bean.User;
import com.mywork.mapper.UserMapper;
import com.mywork.service.UserService;

@Service("loginService")
public class UserServiceImpl implements UserService{
	
	@Autowired//
	private UserMapper userMapper;

	/**
	 *用户登录信息的验证
	 */
	public User getUserByNameAndPassword(User user){
		return userMapper.getUserByNameAndPassword(user);
	}

	/**
	 根据用户id根据ID查找用户
	 */
	public User getUserById(String id) {
		return userMapper.getById(id);
	}

	/**
	 * 根据用户id删除用户
	 */
	public boolean delete(String id) {
		userMapper.delete(id);
		return true;
	}

	/**
	 * 根据用户id增加用户
	 */
	public boolean insert(User user) {
		userMapper.insert(user);
		return true;
	}
	/**
	 * 根据用户id修改用户
	 */
	public boolean update(User user) {
		userMapper.update(user);
		return true;
	}

	/**
	 * 批量查询
	 */
	public List<User> getList(Map<String, Object> map) {
		return userMapper.getList(map);
	}

	
	
}

