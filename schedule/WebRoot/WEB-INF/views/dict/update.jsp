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
	<script>
	
    </script>
</head>
<body>
	<c:if test="${msg!=null}">
		<script>
			alert('${msg}');
		</script>
	</c:if>
    <div class="alert alert-info">当前位置<b class="tip"></b>课程管理<b class="tip"></b>修改</div>
<form action="<%=basePath %>dict/update.html" method="post">
    <table class="table table-striped table-bordered table-condensed list">
        <thead>
            <tr>
                <td colspan="4"><b>修改</b></td>
            </tr>
        </thead>
        <tbody>
            <tr>
                <td >课程名称<font color="FF0000">*</font></td>
                <td>
                    <input type="text" name="type" id="type" value="${dict.type}" />
                     <input type="hidden" name="id" id="id" value="${dict.id}" />
                </td>
            </tr>
            <tr>
                <td >介绍<font color="FF0000">*</font></td>
                <td>
                    <input type="text" name="desc" id="desc" value="${dict.desc}" />
                </td>
            </tr>
            <tr>
                <td >开课起始周<font color="FF0000">*</font></td>
                <td>
                    <input type="number" name="beginweek" id="beginweek" value="${dict.beginweek}" />
                </td>
            </tr>
            <tr>
                <td >开课结束周<font color="FF0000">*</font></td>
                <td>
                    <input type="number" name="endweek" id="endweek" value="${dict.endweek}" />
                </td>
            </tr>
            <tr>
                <td >课时<font color="FF0000">*</font></td>
                <td>
                    <input type="number" name="period" id="period" value="${dict.period}" />
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

