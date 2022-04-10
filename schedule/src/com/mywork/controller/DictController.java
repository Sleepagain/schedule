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
	/**
	 * 添加到数据库
	 */
	@RequestMapping(value="add")
	public ModelAndView add(HttpServletRequest request, Dict dict){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("type", dict.getType());
		if(isForWeek(dict)&&(dict.getBeginweek()<0||dict.getEndweek()>23||dict.getBeginweek()>dict.getEndweek())){
			map.put("msg", "请检查课程时间设置(课程时间应该在0-22周)");
			map.put("type", "");
			List<Dict> list = DictService.getList(map);
			map.put("list", list);
			return jsp("dict/list", map, request);
		}
		if((dict.getEndweek()!=null&&dict.getBeginweek()==null)||(dict.getEndweek()==null&&dict.getBeginweek()!=null)){
			map.put("msg", "请检查课程时间设置,时间必须全部设置，或者不设置开课时间");
			map.put("type", "");
			List<Dict> list = DictService.getList(map);
			map.put("list", list);
			return jsp("dict/list", map, request);
		}

		List<Dict> list1 = DictService.getList(map);
		if(list1.size() != 0){
			map.put("msg", "课程名称不允许重复");
		}else{
			boolean issuc = DictService.insert(dict);
			if(issuc){
				if(isForWeek(dict)){
					map.put("msg", "新增成功");
				}else if(dict.getEndweek()==null && dict.getBeginweek()==null){
					map.put("msg", "新增成功(课程将随机分配时间)");
				}
			}else{
				map.put("msg", "新增失败，请重新操作");
			}
		}
		map.put("type", "");
		List<Dict> list = DictService.getList(map);
		map.put("list", list);
		return jsp("dict/list", map, request);
	}

	private boolean isForWeek(Dict dict){
		if(dict.getEndweek()!=null && dict.getBeginweek()!=null){
			return true;
		}
		return false;
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