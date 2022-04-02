<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@page import="com.mywork.bean.User"%>
<%@page import="com.mywork.common.SessionKeys" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
User user = (User)request.getSession().getAttribute(SessionKeys.LOGIN_USER);
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8" />
    <title>自动排课系统</title>
    <link rel="stylesheet" type="text/css" href="<%=path %>/resources/css/admin-all.css" />
    <link rel="stylesheet" type="text/css" href="<%=path %>/resources/css/base.css" />
    <link rel="stylesheet" type="text/css" href="<%=path %>/resources/css/bootstrap.min.css" />
    <link rel="stylesheet" type="text/css" href="<%=path %>/resources/css/ui-lightness/jquery-ui-1.8.22.custom.css" />
    <script type="text/javascript" src="<%=path %>/resources/js/jquery-1.7.2.js"></script>
    <script type="text/javascript" src="<%=path %>/resources/js/jquery-ui-1.8.22.custom.min.js"></script>
    <script type="text/javascript" src="<%=path %>/resources/js/index.js"></script>
    <script type="text/javascript">
    	function logout(){
    		if(confirm("确认退出？")){
    			window.location.href = "<%=basePath%>user/logout.html";
    		}
    	}
    </script>
</head>
<body>
    <div id="warp" class="warp">
        <!--头部开始-->
        <div class="top_c">
            <div class="top-menu">
            </div>
            
            <div class="top-nav">
            	&nbsp;&nbsp;&nbsp;&nbsp;欢迎您，<%=user.getName() %>！&nbsp;&nbsp;
            	<a target="Conframe" href="<%=basePath %>user/tochangemsg.html">个人信息查看</a> | 
            	<a target="Conframe" href="<%=basePath %>user/tochangepsd.html">修改密码</a> | <a href="javascript:logout();">安全退出</a>
            </div>
        </div>
        <!--头部结束-->
        <!--左边菜单开始-->
        <div class="left_c left">
            <h1>系统操作菜单</h1>
            <div class="acc">
               <!-- 管理员 -->
	            <c:if test="${user.isadmin == 0}">
	            	<div>
	                    <a id="one1" class="one">课程管理</a>
	                    <ul class="kid">
	                    	<li><b class="tip"></b><a target="Conframe" href="<%=basePath %>dict/list.html">课程管理</a></li>
	                    </ul>
	                </div>
	                <div>
	                    <a id="one1" class="one">学院管理</a>
	                    <ul class="kid">
	                    	<li><b class="tip"></b><a target="Conframe" href="<%=basePath %>major/list.html">学院管理</a></li>
	                    </ul>
	                </div>
	                <div>
	                    <a id="one1" class="one">专业管理</a>
	                    <ul class="kid">
	                    	<li><b class="tip"></b><a target="Conframe" href="<%=basePath %>major/majorlist.html">专业管理</a></li>
	                    </ul>
	                </div>
	                <div>
	                    <a id="one1" class="one">教师管理</a>
	                    <ul class="kid">
	                    	<li><b class="tip"></b><a target="Conframe" href="<%=basePath %>user/list.html?isadmin=1">教师管理</a></li>
	                    </ul>
	                </div>
	                <div>
	                    <a id="one1" class="one">学生管理</a>
	                    <ul class="kid">
	                    	<li><b class="tip"></b><a target="Conframe" href="<%=basePath %>user/list.html?isadmin=2">学生管理</a></li>
	                    </ul>
	                </div>
	                <div>
	                    <a id="one1" class="one">教室管理</a>
	                    <ul class="kid">
	                    	<li><b class="tip"></b><a target="Conframe" href="<%=basePath %>room/list.html">教室管理</a></li>
	                    </ul>
	                </div>
	                <div>
	                    <a id="one1" class="one">排课管理</a>
	                    <ul class="kid">
	                    	<li><b class="tip"></b><a target="Conframe" href="<%=basePath %>timetable/list.html">排课管理</a></li>
	                    	<li><b class="tip"></b><a target="Conframe" href="<%=basePath %>timetable/checklist.html">排课查询</a></li>
	                    </ul>
	                </div>
	                
	             </c:if>
	             <c:if test="${user.isadmin == 1}">
	             	<div>
	                    <a id="one1" class="one">课程表查询</a>
	                    <ul class="kid">
	                    	<li><b class="tip"></b><a target="Conframe" href="<%=basePath %>timetable/tealist.html">课程表查询</a></li>
	                    </ul>
	                </div>
	                <div>
	                    <a id="one1" class="one">考试时间查询</a>
	                    <ul class="kid">
	                    	<li><b class="tip"></b><a target="Conframe" href="<%=basePath %>timetable/teaexamlist.html">考试时间查询</a></li>
	                    </ul>
	                </div>
	             </c:if>
	             <c:if test="${user.isadmin == 2}">
	             	<div>
	                    <a id="one1" class="one">课程表查询</a>
	                    <ul class="kid">
	                    	<li><b class="tip"></b><a target="Conframe" href="<%=basePath %>timetable/stulist.html">课程表查询</a></li>
	                    </ul>
	                </div>
	                <div>
	                    <a id="one1" class="one">考试时间查询</a>
	                    <ul class="kid">
	                    	<li><b class="tip"></b><a target="Conframe" href="<%=basePath %>timetable/stuexamlist.html">考试时间查询</a></li>
	                    </ul>
	                </div>
	             </c:if>
	             <div>
                    <a id="one1" class="one">个人信息修改</a>
                    <ul class="kid">
                    	<li><b class="tip"></b><a target="Conframe" href="<%=basePath %>user/tochangemsg.html">个人信息修改</a></li>
                    </ul>
                </div>
	             <div>
                    <a id="one1" class="one">密码修改</a>
                    <ul class="kid">
                    	<li><b class="tip"></b><a target="Conframe" href="<%=basePath %>user/tochangepsd.html">密码修改</a></li>
                    </ul>
                </div>
                <div>
                    <a id="one1" class="one">安全退出</a>
                    <ul class="kid">
                    	<li><b class="tip"></b><a href="javascript:logout();">安全退出</a></li>
                    </ul>
                </div>
            </div>
        </div>
        <!--左边菜单结束-->
        <!--右边框架开始-->
        <div class="right_c">
            <div class="nav-tip" onclick="javascript:void(0)">&nbsp;</div>
        </div>
        <div class="Conframe">
            <iframe name="Conframe" id="Conframe" ></iframe>
        </div>
        <!--右边框架结束-->

        <!--底部开始-->
        <div id="bottom_c" class="bottom_c">Copyright &copy;2022自动排课系统</div>
        <!--底部结束-->
    </div>
</body>
</html>
