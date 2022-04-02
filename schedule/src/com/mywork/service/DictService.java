package com.mywork.service;
/**
 * 课程业务层接口
 */

import java.util.List;
import java.util.Map;

import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.mywork.bean.Dict;

/**
 * 用户
 *
 */
@Transactional(rollbackFor = { Exception.class })
public interface DictService {

	/**
	 * 根据map中的要求，查询课程集合
	 */
	public List<Dict> getList(Map<String, Object> map);

	
	/**
	 * 根据ID查询返回课程对象
	 */
	@Transactional(isolation = Isolation.READ_COMMITTED, readOnly = true)
	public Dict getDictById(String id);

	/**
	 添加课程对象 返回添加是否成功
	 */
	@Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED)
	public boolean insert(Dict Dict);

	/**
	 修改课程对象 返回修改是否成功
	 */
	@Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED)
	public boolean update(Dict Dict);

	/**
	 删除课程对象 返回删除是否成功
	 */
	@Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED)
	public boolean delete(String id);
	
}
