package com.mywork.service;
/**
 * 专业业务层接口
 */

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Select;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.mywork.bean.Major;


@Transactional(rollbackFor = { Exception.class })
public interface MajorService {

	/**
	 * 根据map进行条件查询，返回专业集合
	 */
	public List<Major> getList(Map<String, Object> map);

	
	/**
	 *根据ID查询专业对象
	 *
	 */
	@Transactional(isolation = Isolation.READ_COMMITTED, readOnly = true)
	public Major getMajorById(String id);

	/**
	 插入专业对象 返回是否成功
	 */
	@Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED)
	public boolean insert(Major Major);

	/**
	 修改专业对象 返回是否成功
	 */
	@Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED)
	public boolean update(Major Major);

	/**
	 删除专业对象 返回是否成功
	 */
	@Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED)
	public boolean delete(String id);

	@Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED)
	public List<String> getMajorBycollege(String college);
}
