<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="com.mywork.bean.User"%>
<%@page import="com.mywork.common.SessionKeys" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
User user = (User)request.getSession().getAttribute(SessionKeys.LOGIN_USER);
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title></title>
    <link rel="stylesheet" type="text/css" href="<%=path %>/resources/css/admin-all.css" />
    <link rel="stylesheet" type="text/css" href="<%=path %>/resources/css/base.css" />
    <link rel="stylesheet" type="text/css" href="<%=path %>/resources/css/bootstrap.min.css" />
    <link rel="stylesheet" type="text/css" href="<%=path %>/resources/css/ui-lightness/jquery-ui-1.8.22.custom.css" />
    <script type="text/javascript" src="<%=path %>/resources/js/jquery-1.7.2.js"></script>
    <script type="text/javascript" src="<%=path %>/resources/js/jquery-ui-1.8.22.custom.min.js"></script>
    <script type="text/javascript" src="<%=path %>/resources/js/index.js"></script>
    <script type="text/javascript">
        function check(){
        	var userpsd = '<%=user.getPassword()%>';
        	var oldpassword = document.getElementById("oldpassword").value;
        	var password = document.getElementById("password").value;
        	var repassword = document.getElementById("repassword").value;
        	if(oldpassword == "" || userpsd != oldpassword){
        		alert("原密码输入错误");
				return false;
        	}
        	if(password != repassword ){
        		alert("两次输入的密码不一致");
				return false;
        	}
        }
    </script>
</head>
<body>
	<c:if test="${msg!=null}">
		<script>
			alert('${msg}');
		</script>
	</c:if>
    <div class="alert alert-info">当前位置<b class="tip"></b>修改密码</div>
<form action="<%=basePath %>user/changepsd.html" method="post" onsubmit="return check();">
    <table class="table table-striped table-bordered table-condensed list">
        <thead>
            <tr>
                <td colspan="4"><b>修改密码</b></td>
            </tr>
        </thead>
        <tbody>
            <tr>
                <td width="100px">原密码<font color="FF0000">*</font></td>
                <td width="500">
                    <input type="password" name="oldpassword" id="oldpassword" value="" />
                </td>
            </tr>
            <tr>
                <td>密码<font color="FF0000">*</font></td>
                <td>
                    <input type="password" id="password" name="password" value="" />
                </td>
            </tr>
            <tr>
                <td>重复密码<font color="FF0000">*</font></td>
                <td>
                    <input type="password" id="repassword" name="repassword" value="" />
                </td>
            </tr>
        </tbody>
        <tfoot>
            <tr>
                <td colspan="4" align="center">
                    <input class="btn btn-inverse" id="find" type="submit" value="保存" />
                </td>
            </tr>
        </tfoot>
    </table>
</form>
</body>
</html>

