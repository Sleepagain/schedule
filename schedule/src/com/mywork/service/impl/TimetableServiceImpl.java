package com.mywork.service.impl;


import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mywork.bean.Timetable;
import com.mywork.mapper.TimetableMapper;
import com.mywork.service.TimetableService;

@Service("TimetableService")
public class TimetableServiceImpl implements TimetableService{
	
	@Autowired//使用在字段上用于根据类型依赖注入
	private TimetableMapper TimetableMapper;

	/**
	 * 根据ID查找时间表对象
	 */
	public Timetable getTimetableById(String id) {
		return TimetableMapper.getById(id);
	}

	/**
	 * 根据ID删除时间表对象
	 */
	public boolean delete(String id) {
		TimetableMapper.delete(id);
		return true;
	}

	/**
	 * 添加时间表对象 返回时候添加成果
	 */
	public boolean insert(Timetable Timetable) {
		TimetableMapper.insert(Timetable);
		return true;
	}

	/**
	 * 修改时间表对象
	 */
	public boolean update(Timetable Timetable) {
		TimetableMapper.update(Timetable);
		return true;
	}
	/**
	 *根据map添加的要求查询时间表对象
	 */
	public List<Timetable> getList(Map<String, Object> map) {
		return TimetableMapper.getList(map);
	}

	/**
	 *删除时间表对象
	 */
	public void deleteall(String id) {
		TimetableMapper.deleteall(id);
		
	}

	
	
}

