<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
</head>
<body>
<!-- 
 	<div class="pager">
 		每页10条;共&nbsp;${count }&nbsp;条;共&nbsp;${maxPager }&nbsp;页;当前第&nbsp;${pagerNum }&nbsp;页&nbsp;
 		<a href="javascript:goPager(1)">&lt;&lt;首页</a>
 		<a href="javascript:goPager(${pagerNum }-1)">&lt;&lt;上一页</a>
 		<a href="javascript:goPager(${pagerNum }+1)">下一页 &gt; </a>
 		<a href="javascript:goPager(${maxPager });">尾页 &gt;&gt; </a>
 		<input type="text" style="width: 30px;" name="newPage" id="newPage"></input>
 		<input type="button" value="GO" onclick="goPager(-1)">
 	</div>
 -->
 	<table width="100%">
	 	<tr class="pager">
	        <th colspan="100">当前第${pagerNum }页/共${maxPager }页&nbsp;&nbsp;每页10条&nbsp;&nbsp;共${count }条记录&nbsp;&nbsp;
	        	<a href="javascript:goPager(1);">首页</a>&nbsp;
	        	<a href="javascript:goPager(${pagerNum }-1);">上一页</a>&nbsp;
	            <a href="javascript:goPager(${pagerNum }+1);">下一页</a>&nbsp;
	            <a href="javascript:goPager(${maxPager });">尾页</a>
	            <input type="text" style="width: 30px;" name="newPage" id="newPage"></input>
 				<input type="button" value="GO" onclick="goPager(-1);">
	        </th>
	    </tr>
    </table>
</body>
</html>