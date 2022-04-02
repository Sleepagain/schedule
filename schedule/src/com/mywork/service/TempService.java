package com.mywork.service;


import java.util.List;
import java.util.Map;

import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.mywork.bean.Temp;

/**
 *排课结果对象业务层接口
 */
@Transactional(rollbackFor = { Exception.class })
public interface TempService {
	
	public List<Temp> getList(Map<String, Object> map);

	
	/**
	 根据ID进行查询
	 */
	@Transactional(isolation = Isolation.READ_COMMITTED, readOnly = true)
	public Temp getTempById(String id);

	/**
	 根据ID进行查询
	 */
	@Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED)
	public boolean insert(Temp Temp);

	/**
	 * 修改
	 */
	@Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED)
	public boolean update(Temp Temp);

	/**
	 * 删除
	 */
	@Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED)
	public boolean delete(String id);
	
}
