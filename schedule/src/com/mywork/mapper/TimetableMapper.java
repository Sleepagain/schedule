package com.mywork.mapper;

import java.util.List;
import java.util.Map;

import com.mywork.bean.Timetable;
//
/**
 时间表mapper对象，相关sql映射于mybatis的timetable.xml
 */
public interface TimetableMapper extends SqlMapper{
	
	public List<Timetable> getList(Map<String, Object> map);

	/**
	 * 根据Id查找
	 */
	public Timetable getById(String id);
	/**
	 * add
	 */
	public void insert(Timetable Timetable);
	/**
	 * 修改
	 */
	public void update(Timetable Timetable);
	/**
	 * 删除

	 */
	public void delete(String id);
	public void deleteall(String id);
}
