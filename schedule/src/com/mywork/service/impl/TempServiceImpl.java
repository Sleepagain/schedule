package com.mywork.service.impl;
/**
 * 排课结果（零时表）对象业务层实现
 */

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mywork.bean.Temp;
import com.mywork.mapper.TempMapper;
import com.mywork.service.TempService;

@Service("TempService")
public class TempServiceImpl implements TempService{
	
	@Autowired
	private TempMapper TempMapper;

	/**
	 * 根据ID查找
	 */
	public Temp getTempById(String id) {
		return TempMapper.getById(id);
	}
	/**
	 * 删除
	 */
	public boolean delete(String id) {
		TempMapper.delete(id);
		return true;
	}
	/**
	 * 增加
	 */
	public boolean insert(Temp Temp) {
		TempMapper.insert(Temp);
		return true;
	}
	/**
	 * 修改
	 */
	public boolean update(Temp Temp) {
		TempMapper.update(Temp);
		return true;
	}
	/**
	 * 查询集合
	 */
	public List<Temp> getList(Map<String, Object> map) {
		return TempMapper.getList(map);
	}

	
	
}

