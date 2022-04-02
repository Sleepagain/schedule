package com.mywork.service.impl;//service层 采用接口+impl ：是为了应对可能不同情形下，会存在多套业务逻辑。在调用的时候，根据实际情况去调用对应的serviceImpl


import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mywork.bean.Dict;
import com.mywork.mapper.DictMapper;
import com.mywork.service.DictService;
/**
 *课程业务层实现
 */

@Service("dictService")//service层实例化bean
public class DictServiceImpl implements DictService{//DICTdao接口对应的实现
	//创建Mapper对象， 将Spring中的同名bean注入到其中
	@Autowired//相当于spring配置文件中bean用property注入；按照数据类型从spring容器中进行匹配
	private DictMapper DictMapper;

	/**
	 * 根据ID查找课程对象
	 */
	public Dict getDictById(String id) {
		return DictMapper.getById(id);
	}
	/**
	 * 删除课程对象
	 */
	public boolean delete(String id) {
		DictMapper.delete(id);
		return true;
	}
	/**
	 * 增加课程对象
	 */
	public boolean insert(Dict Dict) {
		DictMapper.insert(Dict);
		return true;
	}
	/**
	 * 修改课程对象
	 */
	public boolean update(Dict Dict) {
		DictMapper.update(Dict);
		return true;
	}
	/**
	 * 根据map中的要求 查询所有符合要求的课程对象
	 */
	public List<Dict> getList(Map<String, Object> map) {
		return DictMapper.getList(map);
	}

	
	
}

