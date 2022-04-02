package com.mywork.mapper;

import java.util.List;
import java.util.Map;

import com.mywork.bean.User;

/**
 用户mapper对象，相关sql映射于mybatis的user.xml
 */
public interface UserMapper extends SqlMapper{
	
	
	public List<User> getList(Map<String, Object> map);
	/**
	 * 用户登录时验证用户名密码
	 */
	public User getUserByNameAndPassword(User user);
	
	/**
	 * 根据Id查找
	 */
	public User getById(String id);
	/**
	 * add 用户
	 */
	public void insert(User user);
	/**
	 * 修改
	 */
	public void update(User user);
	/**
	 * 删除
	 */
	public void delete(String id);
}
