package com.mywork.service;


import java.util.List;
import java.util.Map;

import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.mywork.bean.Timetable;

/**
 * 时间表业务层接口
 */

@Transactional(rollbackFor = { Exception.class })
public interface TimetableService {
	
	public List<Timetable> getList(Map<String, Object> map);

	
	/**
	 * 根据ID查询时间表对象
	 */
	@Transactional(isolation = Isolation.READ_COMMITTED, readOnly = true)
	public Timetable getTimetableById(String id);

	/**
	 * 添加时间表对象
	 */
	@Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED)
	public boolean insert(Timetable Timetable);

	/**
	 * 修改时间表对象
	 */
	@Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED)
	public boolean update(Timetable Timetable);

	/**
	 * 根据ID删除时间表对象
	 */
	@Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED)
	public boolean delete(String id);

	/**
	 * 删除时间表对象
	 */
	@Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED)
	public void deleteall(String id);
	
}
