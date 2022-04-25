<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="root" value="${pageContext.request.contextPath}"></c:set>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>角色权限管理</title>
<link rel="stylesheet" type="text/css" href="${root}/static/css/comm.css"  />
<style type="text/css">
	p{
		font-size: 15px;
		font-weight: bolder;
	}
</style>
<script type="text/javascript" src="${root}/static/js/jquery-1.8.2.js"></script>
<script type="text/javascript">
    $(function(){
    	$("#formroleperm").submit(function(){
    		if($(this).find("input:checkbox:checked").length==0){
    			alert("每个角色至少保留一个权限");
    			return false;
    		}
    		return true;
    	});
    });
</script>
</head>
<body>
	<p>角色名：${requestScope.roleperm.rname}</p>
	<p>权限：<br/><c:forEach var="item" items="${roleperm.perms}">${item.pdesc}&nbsp;${item.purl}<br/></c:forEach></p>
	<hr/>
	<form id="formroleperm" action="${root}/admin/roleperm.do" method="post">
	<input type="hidden" name="rid" value="${requestScope.roleperm.rid}" />
	<c:forEach var="item" items="${perms}">
		<input type="checkbox" name="pids" value="${item.pid}" ${item.checked} />${item.pdesc}&nbsp;${item.purl}<br/>
	</c:forEach>
	<input type="submit" value="更新" />
	</form>
</body>
</html>