package com.mywork.inteceptor;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.mywork.bean.User;
import com.mywork.common.SessionKeys;
//
/**
 * springMVC拦截器：springMVC拦截器 在进行相关方法 或者页面的访问的时候 必须要经过该拦截器， 在拦截器前 和拦截器后 都可以执行 指定方法
 */
public class SessionInteceptor implements HandlerInterceptor{
	
	Log log=LogFactory.getLog(SessionInteceptor.class);
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object arg2, Exception arg3)
			throws Exception {
	}

	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object arg2, ModelAndView arg3)
			throws Exception {

	}
	/**
	 * 在到达action之前执行
	 */
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object arg2) throws Exception {
		//log.debug("=====================执行顺序： 1 preHandle================================================");
		//设置session超时时间1分钟。
		//request.getSession().setMaxInactiveInterval(60);
		// noFilters内的action拦截器不拦截
        String[] noFilters = new String[] {"download.html","tudizhenghe.html","xinyichaye.html","filmfestival.html", "yingshizhizuo.html", "dongmanzhizuo.html", "yingshixueyuan.html", "login.html", "main.html", "logout.html","index.html","filmdetail.html","video.html","yirendetail.html","aboutus.html","shiye.html","xinwen.html","xinwendetail.html","zuopin.html","shipin.html","biandao.html","biandaodetail.html","yiren.html","contact.html", };
		String url=request.getServletPath();

		//是否进入拦截器
		boolean beFilter = true;
		//log.debug("===================请求路径为："+url);
		for (String s : noFilters) {
            if (url.indexOf(s) != -1) {
                beFilter = false;
                break;
            }
        }
		if(beFilter){
			User sessionuser = (User)request.getSession().getAttribute(SessionKeys.LOGIN_USER);
			if(null == sessionuser){
				PrintWriter out = response.getWriter();
		        StringBuilder builder = new StringBuilder();
		        builder.append("<script type=\"text/javascript\" charset=\"GBK\">");
		        builder.append("alert(\"Login Timeout, please login again\");");
		        builder.append("window.top.location.href=\"");
		        builder.append("/film/login.html\";</script>");
		        out.print(builder.toString());
		        out.close();
		        return false;
			}
		}
		return true;
	}

}
