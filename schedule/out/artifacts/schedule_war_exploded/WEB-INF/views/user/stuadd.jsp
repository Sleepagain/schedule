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
			$.ajax({//
				type:'post', 
				async:false,
				url:"<%=basePath%>major/getMajorBycollege.html",
				data: {"college":value},
				dataType:'text',
				success: function(data){
					var dataObj = eval("("+data+")");;
					var major = document.getElementById("major");
					if(dataObj != null){
						major.options.length=0;  
						for(var i = 0;i<dataObj.length;i++){
							var item=dataObj[i];
							major.options.add(new Option(item.name,item.id));
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
    <div class="alert alert-info">当前位置<b class="tip"></b>学生管理<b class="tip"></b>添加</div>
<form action="<%=basePath %>user/add.html" method="post">
    <table class="table table-striped table-bordered table-condensed list">
        <thead>
            <tr>
                <td colspan="4"><b>添加</b></td>
            </tr>
        </thead>
        <tbody>
            <tr>
                <td width="80px">用户名<font color="FF0000">*</font></td>
                <td>
                    <input type="text" name="username" id="username" value="" />
                    <input type="hidden" name="isadmin" id="isadmin" value="2" />
                </td>
            </tr>
            <tr>
                <td>密码<font color="FF0000">*</font></td>
                <td>
                    <input type="password" name="password" id="password" value="" />
                </td>
            </tr>
             <tr>
                <td >学号<font color="FF0000">*</font></td>
                <td>
                    <input type="text" name="no" id="no" value="" />
                </td>
            </tr>
            <tr>
                <td >姓名<font color="FF0000">*</font></td>
                <td>
                    <input type="text" name="name" id="name" value="" />
                </td>
            </tr>
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
                    <select name="major" id="major" >
                    	<option value="" >请先选择学院</option>
                    </select>
                </td>
            </tr>
            <tr>
                <td >电话<font color="FF0000">*</font></td>
                <td>
                    <input type="text" name="tel" id="tel" value="" />
                </td>
            </tr>
            <tr>
                <td>邮箱<font color="FF0000">*</font></td>
                <td>
                	<input type="text" name="email" id="email" value="" />
                </td>
            </tr>
            <tr>
                <td>生日<font color="FF0000">*</font></td>
                <td>
                	<input class="Wdate" type="text" name="birthday" id="birthday" onclick="WdatePicker({isShowClear:false,readOnly:true})"/>
                </td>
            </tr>
       		
            <tr>
                <td>性别<font color="FF0000">*</font></td>
                <td>
                	<select id="sex" name="sex" >
                		<option value="男">男</option>
                		<option value="女">女</option>
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

