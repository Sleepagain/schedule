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
		//main
		map.put("yzm", CommonUtil.getYzm());//使用工具类获取验证码。封装后通过jsp传给前端
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
			request.getSession().setAttribute(SessionKeys.LOGIN_USER, user);//把用户信息存到session、再使用用户信息从session里取。页面拦截，必须有session的信息才能继续进行。
			map.put("user", user);
			return jsp("main", map, request);
		}else{
			map.put("msg", "验证码填写错误!");
			map.put("yzm", CommonUtil.getYzm());
			return jsp("login", map, request);
		}
		
	}

	//	登出
	@RequestMapping(value="logout")
	public ModelAndView logout(HttpServletRequest request){
		request.getSession().setAttribute(SessionKeys.LOGIN_USER, null);
		Map<String,Object> map = new HashMap<String,Object>();
		
		map.put("yzm", CommonUtil.getYzm());
		return jsp("login", map, request);
	}

	//	跳转到修改个人信息界面
	@RequestMapping(value="tochangemsg")
	public ModelAndView tochangemsg(HttpServletRequest request){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("user", userService.getUserById(getSessionUser(request).getId().toString()));//通过id得到个人信息传递给前端
		return jsp("changemsg", map, request);
	}

	//	修改个人信息界面
	@RequestMapping(value="changemsg")
	public ModelAndView changemsg(HttpServletRequest request){
		Map<String,Object> map = new HashMap<String,Object>();
		
		String name = request.getParameter("name");
		String tel = request.getParameter("tel");
		String email = request.getParameter("email");
		String birthday = request.getParameter("birthday");
		String post = request.getParameter("post");
		
		User user = userService.getUserById(request.getParameter("id"));
		user.setName(name);
		user.setTel(tel);
		user.setEmail(email);
		user.setBirthday(birthday);
		user.setPost(post);
		
		userService.update(user);
		map.put("user", user);
		return jsp("changemsg", map, request);
	}

	//	跳转到修改密码界面
	@RequestMapping(value="tochangepsd")
	public ModelAndView tochangepsd(HttpServletRequest request){

		return jsp("changepsd", null, request);
	}

	//	跳转到修改密码界面
	@RequestMapping(value="changepsd")
	public ModelAndView changepsd(HttpServletRequest request){
		Map<String,Object> map = new HashMap<String,Object>();
		String password = request.getParameter("password");
		User user = getSessionUser(request);//使用getsession方法获取user信息
		user.setPassword(password);//把新密码放到user
		boolean issuc = userService.update(user);
		if(issuc){
			map.put("msg", "修改密码成功");
		}else{
			map.put("msg", "修改密码失败，请重新操作");
		}
		return jsp("changepsd", map, request);
	}

	//用户列表
	@RequestMapping(value="list")
	public ModelAndView list(HttpServletRequest request){
		Map<String,Object> map = new HashMap<String,Object>();
		String pagerNum = request.getParameter("pagerNum");
		String isadmin = request.getParameter("isadmin");
		map.put("isadmin", isadmin);//判断是否为管理员
		if(pagerNum==null){
			pagerNum = "1";
		}
		List<User> list = userService.getList(map);
		map.put("count", list.size());
		map.put("maxPager", list.size()/PagerUtil.getPagerSize()+1);
		list = (List<User>) PagerUtil.getPager(list, Integer.parseInt(pagerNum));
		
		map.put("pagerNum", pagerNum);
		map.put("list", list);
		if("1".equals(isadmin)){
			return jsp("user/list", map, request);
		}else{
			return jsp("user/stulist", map, request);
		}
		
		
	}

	//	到添加页面
	@RequestMapping(value="toadd")
	public ModelAndView toadd(HttpServletRequest request){
		Map<String,Object> map = new HashMap<String,Object>();
		String isadmin = request.getParameter("isadmin");
		if("1".equals(isadmin)){//判断是否为老师
			map.put("classeslist", dictService.getList(map));//是老师、获取课程信息列表封装到map传给前端。
			return jsp("user/add", map, request);
		}else{// 否则，把这些信息封装到map传递到前端
			map.put("type", "major");
			map.put("majorlist", majorService.getList(map));
			map.put("type", "college");
			map.put("collegelist", majorService.getList(map));
			return jsp("user/stuadd", map, request);
		}
	}

	//	添加到数据库
	@RequestMapping(value="add")
	public ModelAndView add(HttpServletRequest request){
		Map<String,Object> map = new HashMap<String,Object>();
		
		String no = request.getParameter("no");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String name = request.getParameter("name");
		String tel = request.getParameter("tel");
		String email = request.getParameter("email");
		String birthday = request.getParameter("birthday");
		String sex = request.getParameter("sex");
		String post = request.getParameter("post");
		String isadmin = request.getParameter("isadmin");
		String status = request.getParameter("status");
		String college = request.getParameter("college");
		String major = request.getParameter("major");
		String classes = request.getParameter("classes");
		
		//设置用户属性
		User user = new User();
		user.setUsername(username);
		user.setPassword(password);
		user.setName(name);
		user.setTel(tel);
		user.setEmail(email);
		user.setIsadmin(isadmin);
		user.setNo(no);
		user.setSex(sex);
		user.setBirthday(birthday);
		user.setPost(post);
		user.setStatus(status);
		user.setCollege(college);
		user.setMajor(major);
		user.setClasses(classes);
		
		//添加成功用户以后、刷新页面
		boolean issuc = userService.insert(user);
		if(issuc){
			map.put("msg", "新增成功");
		}else{
			map.put("msg", "新增失败，请重新操作");
		}
	
		String pagerNum = request.getParameter("pagerNum");
		map.put("isadmin", isadmin);
		if(pagerNum==null){
			pagerNum = "1";
		}
		List<User> list = userService.getList(map);
		map.put("count", list.size());
		map.put("maxPager", list.size()/PagerUtil.getPagerSize()+1);
		list = (List<User>) PagerUtil.getPager(list, Integer.parseInt(pagerNum));
		
		map.put("pagerNum", pagerNum);
		map.put("list", list);
		if("1".equals(isadmin)){
			return jsp("user/list", map, request);
		}else{
			return jsp("user/stulist", map, request);
		}
	}

	//	到修改页面
	@RequestMapping(value="toupdate")
	public ModelAndView toupdate(HttpServletRequest request){
		Map<String,Object> map = new HashMap<String,Object>();
		User user = userService.getUserById(request.getParameter("id"));
		map.put("user", user);
		if("1".equals(user.getIsadmin())){
			map.put("classeslist", dictService.getList(map));
			return jsp("user/update", map, request);
		}else{
			map.put("type", "major");
			map.put("majorlist", majorService.getList(map));
			map.put("type", "college");
			map.put("collegelist", majorService.getList(map));
			
			return jsp("user/stuupdate", map, request);
		}
	}

	//	修改到数据库
	@RequestMapping(value="update")
	public ModelAndView update(HttpServletRequest request){
		Map<String,Object> map = new HashMap<String,Object>();
		//需要设置的属性
		String no = request.getParameter("no");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String name = request.getParameter("name");
		String tel = request.getParameter("tel");
		String email = request.getParameter("email");
		String birthday = request.getParameter("birthday");
		String sex = request.getParameter("sex");
		String post = request.getParameter("post");
		String isadmin = request.getParameter("isadmin");
		String status = request.getParameter("status");
		String college = request.getParameter("college");
		String major = request.getParameter("major");
		String classes = request.getParameter("classes");
		
		User user = userService.getUserById(request.getParameter("id"));
		user.setUsername(username);
		user.setPassword(password);
		user.setName(name);
		user.setTel(tel);
		user.setEmail(email);
		user.setIsadmin(isadmin);
		user.setNo(no);
		user.setSex(sex);
		user.setBirthday(birthday);
		user.setPost(post);
		user.setStatus(status);
		user.setCollege(college);
		user.setMajor(major);
		user.setClasses(classes);
		
		boolean issuc = userService.update(user);
		if(issuc){
			map.put("msg", "修改成功");
		}else{
			map.put("msg", "修改失败，请重新操作");
		}
		String pagerNum = request.getParameter("pagerNum");
		map.put("isadmin", user.getIsadmin());
		if(pagerNum==null){
			pagerNum = "1";
		}
		List<User> list = userService.getList(map);
		map.put("count", list.size());
		map.put("maxPager", list.size()/PagerUtil.getPagerSize()+1);
		list = (List<User>) PagerUtil.getPager(list, Integer.parseInt(pagerNum));
		
		map.put("pagerNum", pagerNum);
		map.put("list", list);
		if("1".equals(user.getIsadmin())){
			return jsp("user/list", map, request);
		}else{
			return jsp("user/stulist", map, request);
		}
		
	}
	


	//	删除
	@RequestMapping(value="del")
	public ModelAndView del(HttpServletRequest request){
		Map<String,Object> map = new HashMap<String,Object>();
		
		String id = request.getParameter("id");
		
		boolean issuc = userService.delete(id);
		if(issuc){
			map.put("msg", "删除成功");
		}else{
			map.put("msg", "删除失败，请重新操作");
		}
		String pagerNum = request.getParameter("pagerNum");
		String isadmin = request.getParameter("isadmin");
		map.put("isadmin", isadmin);
		if(pagerNum==null){
			pagerNum = "1";
		}
		List<User> list = userService.getList(map);
		map.put("count", list.size());
		map.put("maxPager", list.size()/PagerUtil.getPagerSize()+1);
		list = (List<User>) PagerUtil.getPager(list, Integer.parseInt(pagerNum));
		
		map.put("pagerNum", pagerNum);
		map.put("list", list);
		if("1".equals(isadmin)){
			return jsp("user/list", map, request);
		}else{
			return jsp("user/stulist", map, request);
		}
		
	}

}
