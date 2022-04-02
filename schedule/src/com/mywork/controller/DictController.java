package com.mywork.controller;
/**
 * 课程控制层，主要负责于前端页面的交互
 */

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.mywork.bean.Dict;
import com.mywork.service.DictService;

@Controller//web层实例化bean
@RequestMapping(value="dict")//@requestmapping将请求和处理请求的控制器方法关联起来，建立映射关系。映射url路径 value:指定请求的实际地址
public class DictController extends BaseController{
	@Inject//注入spring容器中产生的对象（该方法成员需要依赖注入）
	private DictService DictService;//业务层dict接口


	//课程的查询
	@RequestMapping(value="list")
	public ModelAndView list(HttpServletRequest request){//HttpServletRequest中包含了客户端HTTP请求的所有信息，request（response）是后端根据前端请求返回到前端的信息包
		Map<String,Object> map = new HashMap<String,Object>();//
		// 创建一个Map存放查询信息
		List<Dict> list = DictService.getList(map);//调用DictService接口中的getlist（调用的是dict.xml中的sql）（map为查询的限制条件）方法进行信息查询，查询结果封装到list
		map.put("list", list);//查询结果封装成list集合放入map返回前端
		return jsp("dict/list", map, request);//将查询结果返回jsp页面
	}


	//添加到页面（调用jsp）
	@RequestMapping(value="toadd")
	public ModelAndView toadd(HttpServletRequest request){
		Map<String,Object> map = new HashMap<String,Object>();//新建map对象存放jsp添加的session信息
		return jsp("dict/add", map, request);
	}

	//添加到数据库
	@RequestMapping(value="add")
	public ModelAndView add(HttpServletRequest request, Dict dict ){
		Map<String,Object> map = new HashMap<String,Object>();//创建一个hashmap对象存放添加的课程
		map.put("type", dict.getType());//课程类型放入map
		List<Dict> list1 = DictService.getList(map);//list1里存放课程对象存放getList的查询结果
		if(list1.size() != 0){//如果list1的长度不为0
			map.put("msg", "课程名称不允许重复");
		}else{
			boolean issuc = DictService.insert(dict);//调用DIctService中插入方法插入（xml中的sql） isuuc=1插入成功、issuc=0插入失败
			if(issuc){//如果插入成功
				map.put("msg", "新增成功");
			}else{
				map.put("msg", "新增失败，请重新操作");
			}
		
		}
		map.put("type", "");
		List<Dict> list = DictService.getList(map);//将添加后的结果封装进map对象返回到前端
		map.put("list", list);
		return jsp("dict/list", map, request);
	}


	//	到修改页面
	@RequestMapping(value="toupdate")
	public ModelAndView toupdate(HttpServletRequest request){
		Map<String,Object> map = new HashMap<String,Object>();
		Dict Dict = DictService.getDictById(request.getParameter("id"));//通过id查询课程，并将课程名称取出
	map.put("dict", Dict);
		return jsp("dict/update", map, request);
	}


	//	修改到数据库
	@RequestMapping(value="update")
	public ModelAndView update(HttpServletRequest request,Dict dict){
		Map<String,Object> map = new HashMap<String,Object>();//新建一个map对存放修改结果
		boolean issuc = DictService.update(dict);//issuc为是否修改成功的结果
		if(issuc){
			map.put("msg", "修改成功");
		}else{
			map.put("msg", "修改失败，请重新操作");
		}
		List<Dict> list = DictService.getList(map);//将修改后的结果封装进map对象返回到前端
		map.put("list", list);
		return jsp("dict/list", map, request);
	}
	

	//	删除
	@RequestMapping(value="del")
	public ModelAndView del(HttpServletRequest request){
		Map<String,Object> map = new HashMap<String,Object>();//新建一个map对存放修改结果
		
		String id = request.getParameter("id");//根据id返回要删除的课程名称
		
		boolean issuc = DictService.delete(id);//同上
		if(issuc){
			map.put("msg", "删除成功");
		}else{
			map.put("msg", "删除失败，请重新操作");
		}
		List<Dict> list = DictService.getList(map);//将修改后的结果封装进map对象返回到前端
		map.put("list", list);
		return jsp("dict/list", map,request);
	}

}
//