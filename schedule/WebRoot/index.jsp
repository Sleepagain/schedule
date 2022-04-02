<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <title>正在跳转</title>
	<script type="text/javascript">
		window.location.href = 'user/login.html';
	</script>
  </head>
  
  <body>
  	 正在跳转，请稍后...
  </body>
</html>
