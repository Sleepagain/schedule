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
		var major = '${user.major}';
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
	   		$('#major').val(major);
		}
    </script>
</head>
<body onload="changecollege(null);">
	<c:if test="${msg!=null}">
		<script>
			alert('${msg}');
		</script>
	</c:if>
    <div class="alert alert-info">????????????<b class="tip"></b>????????????<b class="tip"></b>??????</div>
<form action="<%=basePath %>user/update.html" method="post">
    <table class="table table-striped table-bordered table-condensed list">
        <thead>
            <tr>
                <td colspan="4"><b>??????</b></td>
            </tr>
        </thead>
        <tbody>
            <tr>
                <td width="80px">?????????<font color="FF0000">*</font></td>
                <td>
                    <input type="text" name="username" id="username" value="${user.username}" />
                    <input type="hidden" name="id" id="id" value="${user.id}" />
                    <input type="hidden" name="isadmin" id="isadmin" value="2" />
                </td>
            </tr>
            <tr>
                <td>??????<font color="FF0000">*</font></td>
                <td>
                    <input type="password" name="password" id="password" value="${user.password}" />
                </td>
            </tr>
             <tr>
                <td >??????<font color="FF0000">*</font></td>
                <td>
                    <input type="text" name="no" id="no" value="${user.no}" />
                </td>
            </tr>
            <tr>
                <td >??????<font color="FF0000">*</font></td>
                <td>
                    <input type="text" name="name" id="name" value="${user.name}" />
                </td>
            </tr>
            <tr>
                <td >??????<font color="FF0000">*</font></td>
                <td>
                    <select name="college" id="college" onchange="changecollege(this.value)">
                    	<c:forEach var="college" items="${collegelist}" varStatus="status">
                    		<option value="${college.id}" <c:if test="${college.id == user.college}"> selected="selected"</c:if>>${college.name}</option>
                    	</c:forEach>
                    </select>
                </td>
            </tr>
            <tr>
                <td >??????<font color="FF0000">*</font></td>
                <td>
                    <select name="major" id="major" >
                    	<option value="" >??????????????????</option>
                    </select>
                </td>
            </tr>
            <tr>
                <td >??????<font color="FF0000">*</font></td>
                <td>
                    <input type="text" name="tel" id="tel" value="${user.tel}" />
                </td>
            </tr>
            <tr>
                <td>??????<font color="FF0000">*</font></td>
                <td>
                	<input type="text" name="email" id="email" value="${user.email}" />
                </td>
            </tr>
            <tr>
                <td>??????<font color="FF0000">*</font></td>
                <td>
                	<input class="Wdate" type="text" name="birthday" id="birthday" value="${user.birthday}" onclick="WdatePicker({isShowClear:false,readOnly:true})"/>
                </td>
            </tr>
            <tr>
                <td>??????<font color="FF0000">*</font></td>
                <td>
                	<select id="sex" name="sex" >
                		<option value="???" <c:if test="${'???' == user.sex}"> selected="selected"</c:if>>???</option>
                		<option value="???" <c:if test="${'???' == user.sex}"> selected="selected"</c:if>>???</option>
                	</select>
                </td>
            </tr>
        </tbody>
        <tfoot>
            <tr>
                <td colspan="4" align="center">
                    <input class="btn btn-inverse" id="find" type="submit" value="??????" />
                </td>
            </tr>
        </tfoot>
    </table>
</form>
</body>
</html>

