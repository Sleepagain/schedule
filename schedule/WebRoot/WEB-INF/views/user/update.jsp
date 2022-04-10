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
	<script type="text/javascript" src="<%=basePath%>resources/js/WdatePicker.js" defer="defer"></script>
	<link href="<%=basePath%>resources/css/WdatePicker.css" type="text/css" rel="stylesheet" />
	<script>
	
    </script>
</head>
<body>
	<c:if test="${msg!=null}">
		<script>
			alert('${msg}');
		</script>
	</c:if>
    <div class="alert alert-info">当前位置<b class="tip"></b>教师管理<b class="tip"></b>修改</div>
<form action="<%=basePath %>user/update.html" method="post">
    <table class="table table-striped table-bordered table-condensed list">
        <thead>
            <tr>
                <td colspan="4"><b>修改</b></td>
            </tr>
        </thead>
        <tbody>
            <tr>
                <td width="100px">用户名<font color="FF0000">*</font></td>
                <td>
                    <input type="text" name="username" id="username" value="${user.username}" />
                    <input type="hidden" name="id" id="id" value="${user.id}" />
                    <input type="hidden" name="isadmin" id="isadmin" value="1" />
                </td>
            </tr>
            <tr>
                <td>密码<font color="FF0000">*</font></td>
                <td>
                    <input type="password" name="password" id="password" value="${user.password}" />
                </td>
            </tr>
            <tr>
                <td >姓名<font color="FF0000">*</font></td>
                <td>
                    <input type="text" name="name" id="name" value="${user.name}" />
                </td>
            </tr>
            <tr>
                <td >教书课程<font color="FF0000">*</font></td>
                <td>
                    <select name="classes" id="classes" >
                    	<c:forEach var="classes" items="${classeslist}" varStatus="status">
                    		<option value="${classes.type}" <c:if test="${classes.type == user.classes}"> selected="selected"</c:if>>${classes.type}</option>
                    	</c:forEach>
                    </select>
                </td>
            </tr>
            <tr>
                <td>职务<font color="FF0000">*</font></td>
                <td>
                	<input type="text" name="post" id="post" value="${user.post}" />
                </td>
            </tr>
            <tr>
                <td >电话<font color="FF0000">*</font></td>
                <td>
                    <input type="text" name="tel" id="tel" value="${user.tel}" />
                </td>
            </tr>
            <tr>
                <td>邮箱<font color="FF0000">*</font></td>
                <td>
                	<input type="text" name="email" id="email" value="${user.email}" />
                </td>
            </tr>
            <tr>
                <td>生日<font color="FF0000">*</font></td>
                <td>
                	<input class="Wdate" type="text" name="birthday" id="birthday" value="${user.birthday}" onclick="WdatePicker({isShowClear:false,readOnly:true})"/>
                </td>
            </tr>
            <tr>
                <td>性别<font color="FF0000">*</font></td>
                <td>
                	<select id="sex" name="sex" >
                		<option value="男" <c:if test="${'男' == user.sex}"> selected="selected"</c:if>>男</option>
                		<option value="女" <c:if test="${'女' == user.sex}"> selected="selected"</c:if>>女</option>
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

