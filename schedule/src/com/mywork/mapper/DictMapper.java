package com.mywork.mapper;//mybatis mapper interface以面向对象思想操作数据库的增删改查
//课程mapper对象，相关sql映射于mybatis的dict.xml
import java.util.List;
import java.util.Map;

import com.mywork.bean.Dict;


public interface DictMapper extends SqlMapper{
	
	public List<Dict> getList(Map<String, Object> map);

	/**
	 * 根据Id查找
	 */
	public Dict getById(String id);
	/**
	 * 添加
	 */
	public void insert(Dict Dict);
	/**
	 * 修改
	 */
	public void update(Dict Dict);
	/**
	 * 删除
	 */
	public void delete(String id);
}
