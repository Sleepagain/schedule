package com.mywork.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.web.servlet.ModelAndView;

import com.mywork.bean.User;
import com.mywork.common.SessionKeys;

/**
 * 公共的控制类
 */
public class BaseController {//jsp
	/**
	 * 处理以文本的形式做 Ajax 相应
	 */
	public void ajax(HttpServletResponse response,String text) throws IOException{
		PrintWriter out = response.getWriter();
		out.print(text);
		out.close();
	}
	/**
	 * 获取session用户
	 */
	public User getSessionUser(HttpServletRequest request){
		return (User) request.getSession().getAttribute(SessionKeys.LOGIN_USER);
	}
	
	/**
	 * 页面跳转 --
	 * @param url 返回的页面的地址
	 * @param obj 要传递的页面信息,
	 * @param request 预定义，得到request的session,并将session的id放在map作为ModelAndView方法的一个参数
	 * @return
	 */
	public ModelAndView jsp(String url,Map<String,Object> map,HttpServletRequest request){
		HttpSession session=request.getSession();
		if(map == null){
			map = new HashMap<String,Object>();
		}
		map.put("sessionId", session.getId());
		return new ModelAndView(url,map);
	}
}//
