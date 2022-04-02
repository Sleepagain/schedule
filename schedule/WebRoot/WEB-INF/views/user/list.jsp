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
 
        function indexs(id,index){
        	window.location.href="<%=basePath%>user/index?id="+id+"&index="+index;
        }
		function goPager(pager){
        	var maxPager= ${maxPager};
			if(pager==-1){
				pager = document.getElementById("newPage").value;
			}
			if(pager==""){
				alert("页码超出范围");
				return false;
			}
			if(pager==0){
				alert("页码超出范围");
				return false;
			}
			if(pager>maxPager){
				alert("页码超出范围");
				return false;
			}
			window.location.href="<%=basePath%>user/list.html?isadmin=1&pagerNum="+pager;
 		}
    </script>
</head>
<body>
	<c:if test="${msg!=null}">
		<script>
			alert('${msg}');
		</script>
	</c:if>
    <div class="alert alert-info">当前位置<b class="tip"></b>教师管理<b class="tip"></b>列表
    	<font style="float: right;margin-top: -5px;"><input class="btn top-next" id="find" onclick="javascript:window.location.href='<%=basePath%>user/toadd.html?isadmin=1';" type="button" value="新增" /></font>
    </div>
   
    <table class="table table-striped table-bordered table-condensed" id="list">
        <thead>
            <tr class="tr_detail">
                <td width="40px;">序号 </td>
                <td>用户名</td>
                <td>姓名</td>
                <td>教授课程</td>
                <td>职务</td>
                <td>电话</td>
                <td>邮箱</td>
                <td>生日</td>
                <td>性别</td>
                <td width="80px;">操作</td>
            </tr>
        </thead>
        <tbody>
        <c:forEach var="user" items="${list}" varStatus="status">
            <tr>
                <td>${status.index+1}</td>
                <td>${user.username}</td>
                <td>${user.name}</td>
                <td>${user.classes}</td>
                <td>${user.post}</td>
                <td>${user.tel}</td>
                <td>${user.email}</td>
                <td>${user.birthday}</td>
                <td>${user.sex}</td>
                <td>
                	<a href="javascript:window.location.href='<%=basePath%>user/toupdate.html?id=${user.id}';" class="btn btn-mini btn-primary add">修改</a>
                	<a href="javascript:window.location.href='<%=basePath%>user/del.html?id=${user.id}&isadmin=1';" class="btn btn-mini btn-danger del">删除</a>
                </td>
            </tr>
        </c:forEach>
        	
        </tbody>
    </table>
    <jsp:include page="../pager.jsp"></jsp:include>
</body>
</html>
