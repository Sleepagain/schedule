package com.mywork.controller;

//教室控制层，主要负责于前端页面的交互
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.mywork.bean.Room;
import com.mywork.service.RoomService;

@Controller
@RequestMapping(value="room")
public class RoomController extends BaseController{
	@Inject
	private RoomService roomService;



	//	教室信息查询
	@RequestMapping(value="list")
	public ModelAndView list(HttpServletRequest request){
		Map<String,Object> map = new HashMap<String,Object>();// 创建一个Map存放查询信息
		List<Room> list = roomService.getList(map);//调用RoomService接口中的getlist（调用的是room.xml中的sql）（map为查询的限制条件）方法进行信息查询，查询结果封装到list
		map.put("list", list);
		return jsp("room/list", map, request);//将查询结果返回jsp页面
	}

	//	到添加页面
	@RequestMapping(value="toadd")
	public ModelAndView toadd(HttpServletRequest request){
		Map<String,Object> map = new HashMap<String,Object>();//新建map对象存放jsp添加的session信息
		return jsp("room/add", map, request);//将结果返回jsp页面
	}

	//	添加到数据库
	@RequestMapping(value="add")
	public ModelAndView add(HttpServletRequest request){
		Map<String,Object> map = new HashMap<String,Object>();//创建一个map对象存放添加的教室
		//前端把需要设置的room的属性封装到request传给后端
		String name = request.getParameter("name");//名称
		String address = request.getParameter("address");
		//设置room的属性
		Room room = new Room();//生成一个room对象
		room.setName(name);//设置名称
		room.setAddress(address);//设置地质
		
		boolean issuc = roomService.insert(room);//调用RoomService中插入方法插入 isuuc=1插入成功、issuc=0插入失败
		if(issuc){
			map.put("msg", "新增成功");
		}else{
			map.put("msg", "新增失败，请重新操作");
		}
	
		List<Room> list = roomService.getList(map);
		map.put("list", list);
		return jsp("room/list", map, request);//将结果返回jsp页面
	}

	//	 到修改页面
	@RequestMapping(value="toupdate")
	public ModelAndView toupdate(HttpServletRequest request){
		Map<String,Object> map = new HashMap<String,Object>();//新建map对象存放jsp添加的session信息
		Room Room = roomService.getRoomById(request.getParameter("id"));
		map.put("room", Room);
		return jsp("room/update", map, request);//将结果返回jsp页面
	}

	//	修改到数据库
	@RequestMapping(value="update")
	public ModelAndView update(HttpServletRequest request){
		Map<String,Object> map = new HashMap<String,Object>();//新建一个map对存放修改结果
		//前端把需要设置的room的属性封装到request传给后端
		String name = request.getParameter("name");
		String address = request.getParameter("address");
//		设置room属性
		Room room = roomService.getRoomById(request.getParameter("id"));
		room.setName(name);
		room.setAddress(address);
		
		boolean issuc = roomService.update(room);//调用RoomService中修改方法修改（xml中的sql） isuuc=1插入成功、issuc=0插入失败
		if(issuc){
			map.put("msg", "修改成功");
		}else{
			map.put("msg", "修改失败，请重新操作");
		}
		List<Room> list = roomService.getList(map);
		map.put("list", list);
		return jsp("room/list", map, request);//将结果返回jsp页面
	}
	

	//	删除
	@RequestMapping(value="del")
	public ModelAndView del(HttpServletRequest request){
		Map<String,Object> map = new HashMap<String,Object>();// 创建一个Map存放查询信息
		//前端把需要删除的room的属性封装到request传给后端
		String id = request.getParameter("id");
		
		boolean issuc = roomService.delete(id);//调用RoomService中删除方法删除（xml中的sql） isuuc=1插入成功、issuc=0插入失败
		if(issuc){
			map.put("msg", "删除成功");
		}else{
			map.put("msg", "删除失败，请重新操作");
		}
		List<Room> list = roomService.getList(map);
		map.put("list", list);
		return jsp("room/list", map, request);//将结果返回jsp页面
	}

}
