package com.mywork.service;


import java.util.List;
import java.util.Map;

import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.mywork.bean.Room;

/**
 *教室业务层接口
 */

@Transactional(rollbackFor = { Exception.class })
public interface RoomService {

	/**
	 * 根据map进行条件查询，返回教室集合
	 */
	public List<Room> getList(Map<String, Object> map);

	
	/**
	 根据ID查询教室对象
	 */
	@Transactional(isolation = Isolation.READ_COMMITTED, readOnly = true)
	public Room getRoomById(String id);

	/**
	 添加教室对象
	 */
	@Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED)
	public boolean insert(Room Room);

	/**
	 修改教室对象 返回是否成功
	 */
	@Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED)
	public boolean update(Room Room);

	/**
	 删除教室对象 返回是否成功
	 */
	@Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED)
	public boolean delete(String id);
	
}
