package com.mywork.mapper;

import java.util.List;
import java.util.Map;

import com.mywork.bean.Room;

/**
 教室mapper对象，相关sql映射于mybatis的room.xml
 */
public interface RoomMapper extends SqlMapper{
	
	public List<Room> getList(Map<String, Object> map);

	/**
	 * 根据Id查找
	 */
	public Room getById(String id);
	/**
	 * add
	 */
	public void insert(Room Room);
	/**
	 * 修改
	 */
	public void update(Room Room);
	/**
	 * 删除
	 */
	public void delete(String id);
}
