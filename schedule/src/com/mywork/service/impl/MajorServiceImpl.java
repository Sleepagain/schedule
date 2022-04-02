package com.mywork.service.impl;//impl对接口函数进行实现
/**
 * 专业业务层实现
 */

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mywork.bean.Major;
import com.mywork.mapper.MajorMapper;
import com.mywork.service.MajorService;

@Service("MajorService")//将ServiceBean加入到Spring 容器中，以后使用的时候在进行注入
public class MajorServiceImpl implements MajorService{
	//创建Mapper对象， 将Spring中的同名bean注入到其中
	@Autowired
	private MajorMapper MajorMapper;

	/**
	 * 根据ID查找专业对象
	 */
	public Major getMajorById(String id) {
		return MajorMapper.getById(id);
	}

	/**
	 * 根据ID删除专业对象 返回是否成功
	 */
	public boolean delete(String id) {
		MajorMapper.delete(id);
		return true;
	}

	/**
	 * 增加专业对象 返回是否成功
	 */
	public boolean insert(Major Major) {
		MajorMapper.insert(Major);
		return true;
	}

	/**
	 * 修改专业对象 返回是否成功
	 */
	public boolean update(Major Major) {
		MajorMapper.update(Major);
		return true;
	}

	/**
	 * 根据map进行条件查询，返回专业集合
	 */
	public List<Major> getList(Map<String, Object> map) {
		return MajorMapper.getList(map);
	}

	
	
}

