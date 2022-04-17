package com.mywork.controller;
//专业控制层，主要负责于前端页面的交互

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.mywork.bean.Major;
import com.mywork.service.DictService;
import com.mywork.service.MajorService;


@Controller
@RequestMapping(value="major")
public class MajorController extends BaseController{
	@Inject
	private MajorService MajorService;
	@Inject
	private DictService dictService;
	

	//	学院列表查询
	@RequestMapping(value="list")
	public ModelAndView list(HttpServletRequest request){
		Map<String,Object> map = new HashMap<String,Object>();//新建map集合存放查询结果
		map.put("type", "college");//TODO
		// 根据两信息查询
		List<Major> list = MajorService.getList(map);//调用MajorServicee接口中的getlist（map为查询的限制条件）方法进行信息查询, 查询结果封装到list
		map.put("list", list);//封装查询结果的list封装到map中
		return jsp("major/list", map, request);//将查询结果map传回前端页面
	}


	//	到添加页面
	@RequestMapping(value="toadd")
	public ModelAndView toadd(HttpServletRequest request){
		Map<String,Object> map = new HashMap<String,Object>();//新建map对象存放jsp添加的session信息
		return jsp("major/add", map, request);
	}


	//	添加到数据库
	@RequestMapping(value="add")
	public ModelAndView add(HttpServletRequest request){
		Map<String,Object> map = new HashMap<String,Object>();//创建一个hashmap对象存放添加的课程
		//前端把major的属性封装到request传给后端
		String name = request.getParameter("name");//学院名称
		String content = request.getParameter("content");//学院介绍

		Major major = new Major();//新建1个major对象
		major.setName(name);//添加学院名称
		major.setContent(content);//学院介绍
		major.setType("college");//包含课程
		
		boolean issuc = MajorService.insert(major);//调MajorService中插入方法插入 isuuc=1插入成功、issuc=0插入失败
		if(issuc){
			map.put("msg", "新增成功");
		}else{
			map.put("msg", "新增失败，请重新操作");
		}
		map.put("type", "college");
		List<Major> list = MajorService.getList(map);//将添加后的结果封装进map对象返回到前端
		map.put("list", list);
		return jsp("major/list", map, request);
	}


	//跳转到修改页面
	@RequestMapping(value="toupdate")
	public ModelAndView toupdate(HttpServletRequest request){
		Map<String,Object> map = new HashMap<String,Object>();//新建map对象存放jsp添加的session信息
		Major Major = MajorService.getMajorById(request.getParameter("id"));
		map.put("major", Major);
		return jsp("major/update", map, request);
	}


	//	修改到数据库
	@RequestMapping(value="update")
	public ModelAndView update(HttpServletRequest request){
		Map<String,Object> map = new HashMap<String,Object>();//创建一个hashmap对象存放添加的课程
		
		String name = request.getParameter("name");//学院名称
		String content = request.getParameter("content");//学院介绍
		//前端把major的属性封装到request传给后端
		Major major = MajorService.getMajorById(request.getParameter("id"));//
		major.setName(name);//设置名称
		major.setContent(content);//设置介绍
		
		boolean issuc = MajorService.update(major);//调MajorService中修改方法修改 isuuc=1修改成功、issuc=0修改失败
		if(issuc){
			map.put("msg", "修改成功");
		}else{
			map.put("msg", "修改失败，请重新操作");
		}
		map.put("type", "college");
		List<Major> list = MajorService.getList(map);//将修改后的结果封装进map对象返回到前端
		map.put("list", list);
		return jsp("major/list", map, request);
	}
	

	//	删除专业信息
	@RequestMapping(value="del")
	public ModelAndView del(HttpServletRequest request){
		Map<String,Object> map = new HashMap<String,Object>();//创建一个hashmap对象存放删除后的课程信息
		
		String id = request.getParameter("id");
		
		boolean issuc = MajorService.delete(id);//调MajorService中删除方法 isuuc=1删除成功、issuc=0删除失败
		if(issuc){
			map.put("msg", "删除成功");
		}else{
			map.put("msg", "删除失败，请重新操作");
		}
		map.put("type", "college");
		List<Major> list = MajorService.getList(map);//将删除后的结果封装进map对象返回到前端
		map.put("list", list);
		return jsp("major/list", map, request);
	}
	//===================================以下是专业===========================================

	//	专业列表
	@RequestMapping(value="majorlist")
	public ModelAndView majorlist(HttpServletRequest request){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("type", "major");
		List<Major> list = MajorService.getList(map);
		map.put("list", list);
		return jsp("major/majorlist", map, request);
	}


	//	到添加专业页面
	@RequestMapping(value="tomajoradd")
	public ModelAndView tomajoradd(HttpServletRequest request){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("classeslist", dictService.getList(map));
		map.put("type", "college");
		List<Major> list = MajorService.getList(map);
		map.put("list", list);
		return jsp("major/majoradd", map, request);
	}


	//	添加到数据库
	@RequestMapping(value="majoradd")
	public ModelAndView majoradd(HttpServletRequest request){
		Map<String,Object> map = new HashMap<String,Object>();
		//前端把major的属性封装到request传给后端
		String pid = request.getParameter("pid");
		String name = request.getParameter("name");
		String content = request.getParameter("content");
		String[] kc= request.getParameterValues("kc");
		String classes = null;
		for(String class1 : kc){
			if(classes == null){
				classes = class1;
			}else{
				classes += ","+class1;
			}
		}

		Major major = new Major();
		major.setPid(Integer.parseInt(pid));
		major.setName(name);
		major.setContent(content);
		major.setType("major");
		major.setClasses(classes);
		boolean issuc = MajorService.insert(major);
		if(issuc){
			map.put("msg", "新增成功");
		}else{
			map.put("msg", "新增失败，请重新操作");
		}
		map.put("type", "major");
		List<Major> list = MajorService.getList(map);
		map.put("list", list);
		return jsp("major/majorlist", map, request);
	}


	//	到修改页面
	@RequestMapping(value="tomajorupdate")
	public ModelAndView tomajorupdate(HttpServletRequest request){
		Map<String,Object> map = new HashMap<String,Object>();
		Major Major = MajorService.getMajorById(request.getParameter("id"));
		map.put("classeslist", dictService.getList(map));
		map.put("major", Major);
		map.put("type", "college");
		List<Major> list = MajorService.getList(map);
		map.put("list", list);
		return jsp("major/majorupdate", map, request);
	}


	//	修改到数据库
	@RequestMapping(value="majorupdate")
	public ModelAndView majorupdate(HttpServletRequest request){
		Map<String,Object> map = new HashMap<String,Object>();
		
		String pid = request.getParameter("pid");
		String name = request.getParameter("name");
		String content = request.getParameter("content");
		String[] kc=request.getParameterValues("kc");
		String classes = null;
		for(String class1 : kc){
			if(classes == null){
				classes = class1;
			}else{
				classes += ","+class1;
			}
		}
		
		Major major = MajorService.getMajorById(request.getParameter("id"));
		major.setPid(Integer.parseInt(pid));
		major.setName(name);
		major.setContent(content);
		major.setClasses(classes);
		
		boolean issuc = MajorService.update(major);
		if(issuc){
			map.put("msg", "修改成功");
		}else{
			map.put("msg", "修改失败，请重新操作");
		}
		map.put("type", "major");
		List<Major> list = MajorService.getList(map);
		map.put("list", list);
		return jsp("major/majorlist", map, request);
	}
	

	//	删除
	@RequestMapping(value="majordel")
	public ModelAndView majordel(HttpServletRequest request){
		Map<String,Object> map = new HashMap<String,Object>();
		
		String id = request.getParameter("id");
		
		boolean issuc = MajorService.delete(id);
		if(issuc){
			map.put("msg", "删除成功");
		}else{
			map.put("msg", "删除失败，请重新操作");
		}
		map.put("type", "major");
		List<Major> list = MajorService.getList(map);
		map.put("list", list);
		return jsp("major/majorlist", map, request);
	}

	@RequestMapping(value="/getMajorBycollege")
	public List<String> getMajorBycollege(HttpServletRequest request,String college){
		List<String> list=MajorService.getMajorBycollege(college);
		return list;
	}

}
//