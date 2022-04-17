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
			$('#pagerNum').val(pager);
			document.getElementById("form1").submit();
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
    	</font>
    </div>
   <form action="<%=basePath %>timetable/checklist.html" method="post" id="form1" name="form1">
	    <table>
	    	<tr>
	    		<td>教室：</td>
	    		<td>
	    			<select name="roomid" id="roomid">
                    	<option value="" >请选择</option>
                    	<c:forEach var="room" items="${roomlist}" varStatus="status" >
				           <option value="${room.id}" <c:if test="${ room.id ==roomid}"> selected="selected"</c:if>>${room.name}</option>
				        </c:forEach>
                    </select>
	    		</td>
	    		<td>专业：</td>
	    		<td>
	    			<select name="majorid" id="majorid">
                    	<option value="" >请选择</option>
                    	<c:forEach var="major" items="${majorlist}" varStatus="status" >
				           <option value="${major.id}" <c:if test="${ major.id ==majorid}"> selected="selected"</c:if>>${major.name}</option>
				        </c:forEach>
                    </select>
	    		</td>
	    		<td>教师：</td>
	    		<td>
	    			<select name="teacherid" id="teacherid">
                    	<option value="" >请选择</option>
                    	<c:forEach var="teacher" items="${teacherlist}" varStatus="status" >
				           <option value="${teacher.id}" <c:if test="${ teacher.id ==teacherid}"> selected="selected"</c:if>>${teacher.name}</option>
				        </c:forEach>
                    </select>
	    		</td>
	    		<td>课程：</td>
	    		<td>
	    			<input name="classes" value="${classes}"/>
	    			<input type="hidden" name="pagerNum" id="pagerNum"/>
	    		</td>
	    		<td><a href="javascript:document.form1.submit();" class="btn btn-mini btn-primary add">查询</a></td>
	    	</tr>
	    </table>
	  </form>
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
                <td>起始周</td>
                <td>结束周</td>
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
                <td>${timetable.start}</td>
                <td>${timetable.end}</td>
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
