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
      
    </script>
    <style type="">
    	td {
			　　vertical-align:middle;
			　　text-align:center;
		　　}
    </style>
</head>
<body>
	<c:if test="${msg!=null}">
		<script>
			alert('${msg}');
		</script>
	</c:if>
    <div class="alert alert-info">当前位置<b class="tip"></b>我的课程表
    	<font style="float: right;margin-top: -5px;">
    	</font>
    </div>
   
    <table class="table table-striped table-bordered table-condensed" id="list"  style="text-align: center;">
        <thead>
            <tr class="tr_detail">
                <td> </td>
                <td>周一</td>
                <td>周二</td>
                <td>周三</td>
                <td>周四</td>
                <td>周五</td>
            </tr>
        </thead>
        <tbody>
            <tr>
                <td style="width:90px; text-align: center;" >第12节<br/>（上午）</td>
                <td style="text-align: center;">
	                <c:forEach var="timetable" items="${list}" varStatus="status">
	                	<c:if test="${timetable.week=='周一' && timetable.time==1}">
	                		${timetable.classes}<br/>
	                		${timetable.room.name}<br/>
	                		${timetable.room.address}<br/>
	                		${timetable.teacher.name}<br/>
	                		${timetable.week}第${timetable.time}节课<br/>
	                		${timetable.weekcounts}
	                	</c:if>
	                </c:forEach>
                </td>
                <td style="text-align: center;">
	                <c:forEach var="timetable" items="${list}" varStatus="status">
	                	<c:if test="${timetable.week=='周二' && timetable.time==1}">
	                		${timetable.classes}<br/>
	                		${timetable.room.name}<br/>
	                		${timetable.room.address}<br/>
	                		${timetable.teacher.name}<br/>
	                		${timetable.week}第${timetable.time}节课<br/>
	                		${timetable.weekcounts}
	                	</c:if>
	                </c:forEach>
                </td>
                <td style="text-align: center;">
	                <c:forEach var="timetable" items="${list}" varStatus="status">
	                	<c:if test="${timetable.week=='周三' && timetable.time==1}">
	                		${timetable.classes}<br/>
	                		${timetable.room.name}<br/>
	                		${timetable.room.address}<br/>
	                		${timetable.teacher.name}<br/>
	                		${timetable.week}第${timetable.time}节课<br/>
	                		${timetable.weekcounts}
	                	</c:if>
	                </c:forEach>
                </td>
                <td style="text-align: center;">
	                <c:forEach var="timetable" items="${list}" varStatus="status">
	                	<c:if test="${timetable.week=='周四' && timetable.time==1}">
	                		${timetable.classes}<br/>
	                		${timetable.room.name}<br/>
	                		${timetable.room.address}<br/>
	                		${timetable.teacher.name}<br/>
	                		${timetable.week}第${timetable.time}节课<br/>
	                		${timetable.weekcounts}
	                	</c:if>
	                </c:forEach>
                </td>
                <td style="text-align: center;">
	                <c:forEach var="timetable" items="${list}" varStatus="status">
	                	<c:if test="${timetable.week=='周五' && timetable.time==1}">
	                		${timetable.classes}<br/>
	                		${timetable.room.name}<br/>
	                		${timetable.room.address}<br/>
	                		${timetable.teacher.name}<br/>
	                		${timetable.week}第${timetable.time}节课<br/>
	                		${timetable.weekcounts}
	                	</c:if>
	                </c:forEach>
                </td>
            </tr>
        	<tr>
                <td style="text-align: center;">第34节<br/>（上午）</td>
                <td style="text-align: center;">
	                <c:forEach var="timetable" items="${list}" varStatus="status">
	                	<c:if test="${timetable.week=='周一' && timetable.time==2}">
	                		${timetable.classes}<br/>
	                		${timetable.room.name}<br/>
	                		${timetable.room.address}<br/>
	                		${timetable.teacher.name}<br/>
	                		${timetable.week}第${timetable.time}节课<br/>
	                		${timetable.weekcounts}
	                	</c:if>
	                </c:forEach>
                </td>
                <td style="text-align: center;">
	                <c:forEach var="timetable" items="${list}" varStatus="status">
	                	<c:if test="${timetable.week=='周二' && timetable.time==2}">
	                		${timetable.classes}<br/>
	                		${timetable.room.name}<br/>
	                		${timetable.room.address}<br/>
	                		${timetable.teacher.name}<br/>
	                		${timetable.week}第${timetable.time}节课<br/>
	                		${timetable.weekcounts}
	                	</c:if>
	                </c:forEach>
                </td>
                <td style="text-align: center;">
	                <c:forEach var="timetable" items="${list}" varStatus="status">
	                	<c:if test="${timetable.week=='周三' && timetable.time==2}">
	                		${timetable.classes}<br/>
	                		${timetable.room.name}<br/>
	                		${timetable.room.address}<br/>
	                		${timetable.teacher.name}<br/>
	                		${timetable.week}第${timetable.time}节课<br/>
	                		${timetable.weekcounts}
	                	</c:if>
	                </c:forEach>
                </td>
                <td style="text-align: center;">
	                <c:forEach var="timetable" items="${list}" varStatus="status">
	                	<c:if test="${timetable.week=='周四' && timetable.time==2}">
	                		${timetable.classes}<br/>
	                		${timetable.room.name}<br/>
	                		${timetable.room.address}<br/>
	                		${timetable.teacher.name}<br/>
	                		${timetable.week}第${timetable.time}节课<br/>
	                		${timetable.weekcounts}
	                	</c:if>
	                </c:forEach>
                </td>
                <td style="text-align: center;">
	                <c:forEach var="timetable" items="${list}" varStatus="status">
	                	<c:if test="${timetable.week=='周五' && timetable.time==2}">
	                		${timetable.classes}<br/>
	                		${timetable.room.name}<br/>
	                		${timetable.room.address}<br/>
	                		${timetable.teacher.name}<br/>
	                		${timetable.week}第${timetable.time}节课<br/>
	                		${timetable.weekcounts}
	                	</c:if>
	                </c:forEach>
                </td>
            </tr>
            <tr>
                <td style="text-align: center;">第56节<br/>（下午）</td>
                <td style="text-align: center;">
	                <c:forEach var="timetable" items="${list}" varStatus="status">
	                	<c:if test="${timetable.week=='周一' && timetable.time==3}">
	                		${timetable.classes}<br/>
	                		${timetable.room.name}<br/>
	                		${timetable.room.address}<br/>
	                		${timetable.teacher.name}<br/>
	                		${timetable.week}第${timetable.time}节课<br/>
	                		${timetable.weekcounts}
	                	</c:if>
	                </c:forEach>
                </td>
                <td style="text-align: center;">
	                <c:forEach var="timetable" items="${list}" varStatus="status">
	                	<c:if test="${timetable.week=='周二' && timetable.time==3}">
	                		${timetable.classes}<br/>
	                		${timetable.room.name}<br/>
	                		${timetable.room.address}<br/>
	                		${timetable.teacher.name}<br/>
	                		${timetable.week}第${timetable.time}节课<br/>
	                		${timetable.weekcounts}
	                	</c:if>
	                </c:forEach>
                </td>
                <td style="text-align: center;">
	                <c:forEach var="timetable" items="${list}" varStatus="status">
	                	<c:if test="${timetable.week=='周三' && timetable.time==3}">
	                		${timetable.classes}<br/>
	                		${timetable.room.name}<br/>
	                		${timetable.room.address}<br/>
	                		${timetable.teacher.name}<br/>
	                		${timetable.week}第${timetable.time}节课<br/>
	                		${timetable.weekcounts}
	                	</c:if>
	                </c:forEach>
                </td>
                <td style="text-align: center;">
	                <c:forEach var="timetable" items="${list}" varStatus="status">
	                	<c:if test="${timetable.week=='周四' && timetable.time==3}">
	                		${timetable.classes}<br/>
	                		${timetable.room.name}<br/>
	                		${timetable.room.address}<br/>
	                		${timetable.teacher.name}<br/>
	                		${timetable.week}第${timetable.time}节课<br/>
	                		${timetable.weekcounts}
	                	</c:if>
	                </c:forEach>
                </td>
                <td style="text-align: center;">
	                <c:forEach var="timetable" items="${list}" varStatus="status">
	                	<c:if test="${timetable.week=='周五' && timetable.time==3}">
	                		${timetable.classes}<br/>
	                		${timetable.room.name}<br/>
	                		${timetable.room.address}<br/>
	                		${timetable.teacher.name}<br/>
	                		${timetable.week}第${timetable.time}节课<br/>
	                		${timetable.weekcounts}
	                	</c:if>
	                </c:forEach>
                </td>
            </tr>
            <tr>
                <td style="text-align: center;">第78节<br/>（下午）</td>
                <td style="text-align: center;">
	                <c:forEach var="timetable" items="${list}" varStatus="status">
	                	<c:if test="${timetable.week=='周一' && timetable.time==4}">
	                		${timetable.classes}<br/>
	                		${timetable.room.name}<br/>
	                		${timetable.room.address}<br/>
	                		${timetable.teacher.name}<br/>
	                		${timetable.week}第${timetable.time}节课<br/>
	                		${timetable.weekcounts}
	                	</c:if>
	                </c:forEach>
                </td>
                <td style="text-align: center;">
	                <c:forEach var="timetable" items="${list}" varStatus="status">
	                	<c:if test="${timetable.week=='周二' && timetable.time==4}">
	                		${timetable.classes}<br/>
	                		${timetable.room.name}<br/>
	                		${timetable.room.address}<br/>
	                		${timetable.teacher.name}<br/>
	                		${timetable.week}第${timetable.time}节课<br/>
	                		${timetable.weekcounts}
	                	</c:if>
	                </c:forEach>
                </td>
                <td style="text-align: center;">
	                <c:forEach var="timetable" items="${list}" varStatus="status">
	                	<c:if test="${timetable.week=='周三' && timetable.time==4}">
	                		${timetable.classes}<br/>
	                		${timetable.room.name}<br/>
	                		${timetable.room.address}<br/>
	                		${timetable.teacher.name}<br/>
	                		${timetable.week}第${timetable.time}节课<br/>
	                		${timetable.weekcounts}
	                	</c:if>
	                </c:forEach>
                </td>
                <td style="text-align: center;">
	                <c:forEach var="timetable" items="${list}" varStatus="status">
	                	<c:if test="${timetable.week=='周四' && timetable.time==4}">
	                		${timetable.classes}<br/>
	                		${timetable.room.name}<br/>
	                		${timetable.room.address}<br/>
	                		${timetable.teacher.name}<br/>
	                		${timetable.week}第${timetable.time}节课<br/>
	                		${timetable.weekcounts}
	                	</c:if>
	                </c:forEach>
                </td>
                <td style="text-align: center;">
	                <c:forEach var="timetable" items="${list}" varStatus="status">
	                	<c:if test="${timetable.week=='周五' && timetable.time==4}">
	                		${timetable.classes}<br/>
	                		${timetable.room.name}<br/>
	                		${timetable.room.address}<br/>
	                		${timetable.teacher.name}<br/>
	                		${timetable.week}第${timetable.time}节课<br/>
	                		${timetable.weekcounts}
	                	</c:if>
	                </c:forEach>
                </td>
            </tr>
        </tbody>
    </table>
</body>
</html>
