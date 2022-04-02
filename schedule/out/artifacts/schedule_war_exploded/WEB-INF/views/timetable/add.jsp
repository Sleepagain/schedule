<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
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
    <link rel="stylesheet" href="<%=path %>/resources/kindeditor/themes/default/default.css" />
	<script charset="utf-8" src="<%=path %>/resources/kindeditor/kindeditor-min.js"></script>
	<script charset="utf-8" src="<%=path %>/resources/kindeditor/lang/zh_CN.js"></script>
	<script type="text/javascript" src="<%=basePath%>resources/My97DatePicker/WdatePicker.js" defer="defer"></script>	
	<link href="<%=basePath%>resources/My97DatePicker/skin/WdatePicker.css" type="text/css" rel="stylesheet" />
	<script>
		function changecollege(value){
			if(null==value){
				value = $('#college').val();
			}
			$.ajax({//
				type:'post', 
				async:false,
				url:"<%=basePath%>major/getMajorBycollege.html",
				data: {"college":value},
				dataType:'text',
				success: function(data){
					var dataObj = eval("("+data+")");;
					var major = document.getElementById("majorid");
					
					if(dataObj != null){
						major.options.length=0;  
						major.options.add(new Option("请选择",""));
						for(var i = 0;i<dataObj.length;i++){
							var item=dataObj[i];
							major.options.add(new Option(item.name,item.id));
						}
					}
				}
	   		})
		}
		function changemajor(value){
			if(null==value){
				value = $('#majorid').val();
			}
			$.ajax({//
				type:'post', 
				async:false,
				url:"<%=basePath%>major/getClassesBymajor.html",
				data: {"majorid":value},
				dataType:'text',
				success: function(data){
					var dataObj = eval("("+data+")");;
					var classes = document.getElementById("classes");
					if(dataObj != null){
						classes.options.length=0;  
						classes.options.add(new Option("请选择",""));
						for(var i = 0;i<dataObj.length;i++){
							var item=dataObj[i];
							classes.options.add(new Option(item,item));
						}
					}
				}
	   		})
		}
		function changeclasses(value){
			if(null==value){
				value = $('#classes').val();
			}
			$.ajax({//
				type:'post', 
				async:false,
				url:"<%=basePath%>user/getTeaByClasses.html",
				data: {"classes":value},
				dataType:'text',
				success: function(data){
					var dataObj = eval("("+data+")");;
					var teacher = document.getElementById("teacherid");
					
					if(dataObj != null){
						teacher.options.length=0;  
						teacher.options.add(new Option("请选择",""));
						for(var i = 0;i<dataObj.length;i++){
							var item=dataObj[i];
							teacher.options.add(new Option(item.name,item.id));
						}
					}
				}
	   		})
		}
    </script>
</head>
<body>
	<c:if test="${msg!=null}">
		<script>
			alert('${msg}');
		</script>
	</c:if>
    <div class="alert alert-info">当前位置<b class="tip"></b>排课管理<b class="tip"></b>新增</div>
<form action="<%=basePath %>timetable/add.html" method="post">
    <table class="table table-striped table-bordered table-condensed list">
        <thead>
            <tr>
                <td colspan="4"><b>新增</b></td>
            </tr>
        </thead>
        <tbody>
            <tr>
                <td >学院<font color="FF0000">*</font></td>
                <td>
                    <select name="college" id="college" onchange="changecollege(this.value)">
                    	<option value="">请选择</option>
                    	<c:forEach var="college" items="${collegelist}" varStatus="status">
                    		<option value="${college.id}" <c:if test="${college.id == user.college}"> selected="selected"</c:if>>${college.name}</option>
                    	</c:forEach>
                    </select>
                </td>
            </tr>
            <tr>
                <td >专业<font color="FF0000">*</font></td>
                <td>
                    <select name="majorid" id="majorid" onchange="changemajor(this.value)">
                    	<option value="" >请先选择学院</option>
                    </select>
                </td>
            </tr>
            <tr>
                <td >课程<font color="FF0000">*</font></td>
                <td>
                    <select name="classes" id="classes" onchange="changeclasses(this.value)">
                    	<option value="" >请先选择专业</option>
                    </select>
                </td>
            </tr>
            <tr>
                <td >教师<font color="FF0000">*</font></td>
                <td>
                    <select name="teacherid" id="teacherid" >
                    	<option value="" >请先选择课程</option>
                    </select>
                </td>
            </tr>
            <tr>
                <td >教室<font color="FF0000">*</font></td>
                <td>
                    <select name="roomid" id="roomid">
                    	<option value="">请选择</option>
                    	<c:forEach var="room" items="${roomlist}" varStatus="status">
                    		<option value="${room.id}" >${room.name}</option>
                    	</c:forEach>
                    </select>
                </td>
            </tr>
            <tr>
                <td >周<font color="FF0000">*</font></td>
                <td>
                    <select name="week" id="week">
                    	<option value="">请选择</option>
                    	<option value="周一">周一</option>
                    	<option value="周二">周二</option>
                    	<option value="周三">周三</option>
                    	<option value="周四">周四</option>
                    	<option value="周五">周五</option>
                    </select>
                </td>
            </tr>
            <tr>
                <td >上课时间<font color="FF0000">*</font></td>
                <td>
                    <select name="time" id="time">
                    	<option value="">请选择</option>
                    	<option value="1">第一节</option>
                    	<option value="2">第二节</option>
                    	<option value="3">第三节</option>
                    	<option value="4">第四节</option>
                    </select>
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

