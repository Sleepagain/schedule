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
			window.location.href="<%=basePath%>timetable/list.html?pagerNum="+pager;
 		}
    </script>
</head>
<body>
	<c:if test="${msg!=null}">
		<script>
			alert('${msg}');
		</script>
	</c:if>
    <div class="alert alert-info">当前位置<b class="tip"></b>排课管理<b class="tip"></b>列表
    	<font style="float: right;margin-top: -5px;">
    		<input class="btn top-next" id="find" onclick="javascript:window.location.href='<%=basePath%>timetable/toadd.html?isadmin=1';" type="button" value="手动排课" />
    		<input class="btn top-next" id="find" onclick="javascript:window.location.href='<%=basePath%>timetable/auto.html';" type="button" value="自动排课" />
    	</font>
    </div>
   
    <table class="table table-striped table-bordered table-condensed" id="list">
        <thead>
            <tr class="tr_detail">
                <td width="40px;">序号 </td>
                <td>院系</td>
                <td>专业</td>
                <td>课程</td>
                <td>授课教师</td>
                <td>教师电话</td>
                <td>教室</td>
                <td>教室地址</td>
                <td>课程时间</td>
                <td width="80px;">操作</td>
            </tr>
        </thead>
        <tbody>
        <c:forEach var="timetable" items="${list}" varStatus="status">
            <tr>
                <td>${status.index+1}</td>
                <td>${timetable.major.collegename}</td>
                <td>${timetable.major.name}</td>
                <td>${timetable.classes}</td>
                <td>${timetable.teacher.name}</td>
                <td>${timetable.teacher.tel}</td>
                <td>${timetable.room.name}</td>
                <td>${timetable.room.address}</td>
                <td>${timetable.week}第${timetable.time}节课</td>
                <td>
                	<a href="javascript:window.location.href='<%=basePath%>timetable/del.html?id=${timetable.id}&isadmin=1';" class="btn btn-mini btn-danger del">删除</a>
                </td>
            </tr>
        </c:forEach>
        	
        </tbody>
    </table>
    <jsp:include page="../pager.jsp"></jsp:include>
</body>
</html>
