package com.mywork.service;


import java.util.List;
import java.util.Map;

import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.mywork.bean.User;

/**
 * 用户业务层接口
 * 主要用于对于用户的查询 添加 删除 修改  以及 用户的账号密码校验
 */


@Transactional(rollbackFor = { Exception.class })
public interface UserService {




	/**
	 * 登录验证，校验账号密码
	 */
	@Transactional(isolation = Isolation.READ_COMMITTED, readOnly = true)
	public User getUserByNameAndPassword(User user);

	/**
	 *根据条件批量查询用户信息
	 */
	public List<User> getList(Map<String, Object> map);

	/**
	 * 根据ID查询用户
	 */
	@Transactional(isolation = Isolation.READ_COMMITTED, readOnly = true)
	public User getUserById(String id);

	/**
	 * 用户信息的注册
	 */
	@Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED)
	public boolean insert(User user);

	/**
	 * 根据用户ID修改用户信息
	 */
	@Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED)
	public boolean update(User user);

	/**
	 * 根据用户ID删除用户信息
	 */
	@Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED)
	public boolean delete(String id);
	
}
