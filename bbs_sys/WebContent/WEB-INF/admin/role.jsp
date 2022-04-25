<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="root" value="${pageContext.request.contextPath}"></c:set>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>角色列表</title>
<link rel="stylesheet" type="text/css" href="${root}/static/css/comm.css"  />
<style type="text/css">
	#tblrole{
		border-collapse: collapse;
		font-size: 15px;
	}
</style>
<script type="text/javascript" src="${root}/static/js/jquery-1.8.2.js"></script>
<script type="text/javascript">
    $(function(){
      
    });
</script>
</head>
<body>
	<table id="tblrole" border="1">
		<tr>
			<th>编号</th>
			<th>名称</th>
			<th>说明</th>
			<th>权限</th>
		</tr>
		<c:forEach var="item" items="${requestScope.roles}">
		<tr>
			<td>${item.rid}</td>
			<td>${item.rname}</td>
			<td>${item.rdesc}</td>
			<td>
				<a href="${root}/admin/roleperm.do?id=${item.rid}">管理</a>
			</td>
		</tr>	
		</c:forEach>
	</table>
</body>
</html>