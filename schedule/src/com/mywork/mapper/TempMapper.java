package com.mywork.mapper;

import java.util.List;
import java.util.Map;

import com.mywork.bean.Temp;

/**
 零时表结果mapper对象，相关sql映射于mybatis的temp.xml
 *
 */
public interface TempMapper extends SqlMapper{
	
	public List<Temp> getList(Map<String, Object> map);

	/**
	 * 根据Id查找
	 */
	public Temp getById(String id);
	/**
	 * add
	 */
	public void insert(Temp Temp);
	/**
	 * 修改
	 */
	public void update(Temp Temp);
	/**
	 * 删除
	 */
	public void delete(String id);
}
