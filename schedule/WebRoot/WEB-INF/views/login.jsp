<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=no">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=emulateIE7" />
<link rel="stylesheet" type="text/css" href="<%=basePath %>resources/css/style.css" />
<link rel="stylesheet" type="text/css" href="<%=basePath %>resources/css/login.css" />
<script type="text/javascript" src="<%=basePath %>resources/js/login/jquery.js"></script>
<script type="text/javascript" src="<%=basePath %>resources/js/login/jquery.select.js"></script>
<title>登录-自动排课系统</title>
	<script type="text/javascript">
		function  check(){
			var username = document.getElementById("username").value;
			var password = document.getElementById("password").value;
			var yzm = document.getElementById("yzm").value;
			if(username == "" || password == ""){
				alert("用户名或密码不能为空");
				return false;
			}
			if(yzm == "" ){
				alert("验证码不能为空");
				return false;
			}
		}
		function regedit(){
			window.location="<%=basePath %>user/toregedit.html";
		}
	</script>
</head>

<body>
<c:if test="${msg!=null}">
	<script>
		alert('${msg}');
	</script>
</c:if>
<div id="container">
    <div id="bd">
    	<div id="main">
    	<form action="<%=basePath %>user/main.html" method="post" id="form1" onsubmit="return check();">
        	<div class="login-box">
                <div id="logo"></div>
                <h1></h1>
                <div class="input username" >
                    <label for="userName">用户名</label>
                    <span></span>
                    <input type="text" name="username" id="username" />
                </div>
                <div class="input psw" >
                    <label for="password">密&nbsp;&nbsp;&nbsp;&nbsp;码</label>
                    <span></span>
                    <input type="password" name="password" id="password" />
                </div>
                <div class="input validate" id="validate">
                    <label for="valiDate">验证码</label>
                    <input type="text" id="valiDate" name="yzm" id="yzm"/>
                    <div class="value" onselectstart="return false">${yzm}</div>
                </div>
                <div class="styleArea">
                    <div class="styleWrap">
                        <select name="isadmin">
                        	<option value="0">管理员</option>
                            <option value="1">教师</option>
                            <option value="2">学生</option>
                        </select>
                    </div><br/>
                    
                </div>
                <div id="btn" class="loginButton">
                	<input type="hidden" value="${yzm}" id="yanzhengma1" name="yanzhengma1"/>
                    <input type="submit" class="button" value="登录"  />
                </div>
            </div>
        </form>
        </div>
        <div id="ft">CopyRight&nbsp;2022&nbsp;&nbsp;版权所有&nbsp;&nbsp;自动排课系统 &nbsp;&nbsp;</div>
    </div>
   
</div>

</body>
<script type="text/javascript">
	var height = $(window).height() > 445 ? $(window).height() : 445;
	$("#container").height(height);
	var bdheight = ($(window).height() - $('#bd').height()) / 2 - 20;
	$('#bd').css('padding-top', bdheight);
	$(window).resize(function(e) {
        var height = $(window).height() > 445 ? $(window).height() : 445;
		$("#container").height(height);
		var bdheight = ($(window).height() - $('#bd').height()) / 2 - 20;
		$('#bd').css('padding-top', bdheight);
    });
	$('select').select();
</script>

</html>

