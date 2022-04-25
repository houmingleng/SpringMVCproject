<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="root" value="${pageContext.request.contextPath}"></c:set>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户列表</title>
<link rel="stylesheet" type="text/css" href="${root}/static/css/comm.css"  />
<style type="text/css">
	#tbluser{
		border-collapse: collapse;
	}
	#img_photo{
    	vertical-align: middle;
    	width: 40px;
    	height: 40px;
    	cursor: pointer;
    }
</style>
<script type="text/javascript" src="${root}/static/js/jquery-1.8.2.js"></script>
<script type="text/javascript">
    $(function(){
      
    });
</script>
</head>
<body>
	<form action="${root}/admin/user.do" method="post">
		<input type="text" name="key" placeholder="请输入关键字" />
		<input type="submit" value="搜索" />
		<span class="error">${requestScope.message}</span>
	</form>
	<hr/>
	<c:if test="${not empty requestScope.pager}">
		<c:if test="${requestScope.pager.count==0}">
			<p>没有匹配用户信息</p>
		</c:if>
		<c:if test="${requestScope.pager.count>0}">
		<table id="tbluser" border="1">
			<thead>
				<tr>
					<th>编号</th>
					<th>头像</th>
					<th>账户</th>
					<th>性别</th>
					<th>血型</th>
					<th>爱好</th>
					<th>生日</th>
					<th>状态</th>
					<th>操作</th>
					<th>角色</th>
				</tr>
			</thead>
			<tbody>
			<c:forEach var="item" items="${requestScope.pager.items}">
				<tr>
					<td>${item.uid}</td>
					<td>
						<img id="img_photo" src="${root}/admin/photo.do?id=${item.uid}" />
					</td>
					<td>${item.uname}</td>
					<td>${item.dsex.dvalue}</td>
					<td>${item.dblood.dvalue}</td>
					<td>[
						<c:forEach var="hobby" items="${item.dhobby}">
							${hobby.dvalue}&nbsp;	
						</c:forEach>
						]
					</td>
					<td>${item.ubirth}</td>
					<td>${item.dstatus.dvalue}</td>
					<td>
						<c:if test="${item.ustatus=='1041'}">
							<a href="${root}/admin/lock.do?id=${item.uid}&index=${requestScope.pager.index}" 
								onclick="return confirm('是否确定锁定该用户?')">锁定</a>
						</c:if>
						<c:if test="${item.ustatus=='1042'}">
							<a href="${root}/admin/lock.do?id=${item.uid}&index=${requestScope.pager.index}" 
								onclick="return confirm('是否确定解锁该用户?')">解锁</a>
						</c:if>
					</td>
					<td>
						<a href="${root}/admin/userrole.do?id=${item.uid}">管理</a>
					</td>
				</tr>
			</c:forEach>
			</tbody>
			<tfoot>
				<tr>
					<td colspan="5">
						共有${requestScope.pager.count}条记录
						每页显示${requestScope.pager.size}行
						第${requestScope.pager.index}页 / 
						共${requestScope.pager.page}页
					</td>
					<td colspan="5">
						<a href="${requestScope.pager.url}?index=1">首页</a>
						<c:if test="${requestScope.pager.index>1}">
							<a href="${requestScope.pager.url}?index=${requestScope.pager.index-1}">上页</a>
						</c:if>
						<c:if test="${requestScope.pager.index<requestScope.pager.page}">
							<a href="${requestScope.pager.url}?index=${requestScope.pager.index+1}">下页</a>
						</c:if>
						<a href="${requestScope.pager.url}?index=${requestScope.pager.page}">末页</a>
					</td>
				</tr>
			</tfoot>
		</table>
		</c:if>
	</c:if>
	
	
</body>
</html>