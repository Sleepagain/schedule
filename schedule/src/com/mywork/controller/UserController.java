package com.mywork.controller;
//用户控制层，主要负责于前端页面的交互


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.mywork.bean.User;
import com.mywork.common.SessionKeys;
import com.mywork.service.DictService;
import com.mywork.service.MajorService;
import com.mywork.service.UserService;
import com.mywork.util.CommonUtil;
import com.mywork.util.PagerUtil;

@Controller

@RequestMapping(value="user")
public class UserController extends BaseController{
	@Inject
	private UserService userService;
	@Inject
	private MajorService majorService;
	@Inject
	private DictService dictService;


	//	跳转到登录界面
	@RequestMapping(value="login")
	public ModelAndView login(HttpServletRequest request){
		Map<String,Object> map = new HashMap<String,Object>();//新建map对象存放jsp添加的session信息
		
		map.put("yzm", CommonUtil.getYzm());
		return jsp("login", map, request);//将结果返回jsp页面
	}

	//	登录
	@RequestMapping(value="main")
	public ModelAndView main(HttpServletRequest request){
		Map<String,Object> map = new HashMap<String,Object>();//创建一个map对象存放登录信息
		//前端把需要输入的信息属性封装到request传给后端
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String isadmin = request.getParameter("isadmin");
		String yanzhengma1 = request.getParameter("yanzhengma1");
		String yzm = request.getParameter("yzm").toUpperCase();
		if(yzm.equals(yanzhengma1)){//验证码正确
			User user = new User();//创建一个用户对象
			//判断用户信息
			user.setUsername(username);
			user.setPassword(password);
			user.setIsadmin(isadmin);
			user = userService.getUserByNameAndPassword(user);//把个人信息创建为一个session在服务器段
			if(user==null){//
				map.put("msg", "用户名或密码错误!");
				map.put("yzm", CommonUtil.getYzm());
				return jsp("login", map, request);
			}
			request.getSession().setAttribute(SessionKeys.LOGIN_USER, user);
			map.put("user", user);
			return jsp("main", map, request);
		}else{
			map.put("msg", "验证码填写错误!");
			map.put("yzm", CommonUtil.getYzm());
			return jsp("login", map, request);
		}
		
	}

	