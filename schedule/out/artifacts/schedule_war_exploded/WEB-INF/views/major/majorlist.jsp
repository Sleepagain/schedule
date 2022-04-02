<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title></title>
    <link rel="stylesheet" type="text/css" href="<%=path %>/resources/css/bootstrap.min.css" />
    <link rel="stylesheet" type="text/css" href="<%=path %>/resources/css/admin-all.css" />
    <script type="text/javascript" src="<%=path %>/resources/js/jquery-1.7.2.js"></script>
    <script type="text/javascript" src="<%=path %>/resources/js/jquery-ui-1.8.22.custom.min.js"></script>
    <link rel="stylesheet" type="text/css" href="<%=path %>/resources/css/ui-lightness/jquery-ui-1.8.22.custom.css" />
    <script type="text/javascript">
        $(function () {
             $('#list').show();
        })
 
	
    </script>
</head>
<body>
	<c:if test="${msg!=null}">
		<script>
			alert('${msg}');
		</script>
	</c:if>
    <div class="alert alert-info">当前位置<b class="tip"></b>专业管理<b class="tip"></b>列表
    	<font style="float: right;margin-top: -5px;"><input class="btn top-next" id="find" onclick="javascript:window.location.href='<%=basePath%>major/tomajoradd.html';" type="button" value="新增" /></font>
    </div>
   
    <table class="table table-striped table-bordered table-condensed" id="list">
        <thead>
            <tr class="tr_detail">
                <td width="40px;">序号 </td>
                <td>学院</td>
                <td>专业名称</td>
                <td>专业简介</td>
                <td>专业课程</td>
                <td width="180px;">操作</td>
            </tr>
        </thead>
        <tbody>
        <c:forEach var="major" items="${list}" varStatus="status">
            <tr>
                <td>${status.index+1}</td>
                <td>${major.collegename}</td>
                <td>${major.name}</td>
                <td>${major.content}</td>
                <td>${major.classes}</td>
                <td>
                	<a href="javascript:window.location.href='<%=basePath%>major/tomajorupdate.html?id=${major.id}';" class="btn btn-mini btn-primary add">修改</a>
                	<a href="javascript:window.location.href='<%=basePath%>major/majordel.html?id=${major.id}&type=major';" class="btn btn-mini btn-danger del">删除</a>
                </td>
            </tr>
        </c:forEach>
        	
        </tbody>
    </table>
</body>
</html>
