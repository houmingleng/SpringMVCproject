<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="root" value="${pageContext.request.contextPath}"></c:set>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户角色管理</title>
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
    	$("#formuserrole").submit(function(){
    		if($(this).find("input:checkbox:checked").length==0){
    			alert("每个用户至少保留一个角色");
    			return false;
    		}
    		return true;
    	});
    });
</script>
</head>
<body>
	<p>用户名：${requestScope.userrole.uname}</p>
	<p>角色：[<c:forEach var="item" items="${userrole.roles}">${item.rname}&nbsp;</c:forEach>]</p>
	<hr/>
	<form id="formuserrole" action="${root}/admin/userrole.do" method="post">
	<input type="hidden" name="uid" value="${requestScope.userrole.uid}" />
	<c:forEach var="item" items="${roles}">
		<input type="checkbox" name="rids" value="${item.rid}" ${item.checked} />${item.rname}<br/>
	</c:forEach>
	<input type="submit" value="更新" />
	</form>
</body>
</html>