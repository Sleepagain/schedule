package com.mywork.service.impl;
/**
 *教室业务层实现
 */

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mywork.bean.Room;
import com.mywork.mapper.RoomMapper;
import com.mywork.service.RoomService;

@Service("RoomService")//将ServiceBean加入到Spring 容器中，以后使用的时候在进行注入
public class RoomServiceImpl implements RoomService{

	//创建Mapper对象， 将Spring中的同名bean注入到其中
	@Autowired
	private RoomMapper RoomMapper;

	/**
	 根据ID查询教室对象
	 */
	public Room getRoomById(String id) {
		return RoomMapper.getById(id);
	}

	/**
	 * 删除教室ID进行阐述 返回是否成功
	 */
	public boolean delete(String id) {
		RoomMapper.delete(id);
		return true;
	}

	/**
	 *  添加教室对象 返回是否成功
	 */
	public boolean insert(Room Room) {
		RoomMapper.insert(Room);
		return true;
	}

	/**
	 * 修改返回是否成功
	 */
	public boolean update(Room Room) {
		RoomMapper.update(Room);
		return true;
	}
	/**
	 * 根据map进行条件查询，返回教室集合
	 */
	public List<Room> getList(Map<String, Object> map) {
		return RoomMapper.getList(map);
	}

	
	
}

