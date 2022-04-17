package com.mywork.mapper;

import java.util.List;
import java.util.Map;

import com.mywork.bean.Major;
//
/**
 专业mapper对象，相关sql映射于mybatis的major.xml
 */
public interface MajorMapper extends SqlMapper{
	
	public List<Major> getList(Map<String, Object> map);

	/**
	 * 根据Id查找
	 */
	public Major getById(String id);
	/**
	 * 添加
	 */
	public void insert(Major Major);
	/**
	 * 修改
	 */
	public void update(Major Major);
	/**
	 * 删除
	 */
	public void delete(String id);

    List<String> getMajorBycollege(String college);
}
