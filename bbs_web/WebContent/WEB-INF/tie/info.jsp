<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="root" value="${pageContext.request.contextPath}"></c:set>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>帖子详情</title>
<link rel="stylesheet" type="text/css"
	href="${root}/static/css/comm.css" />
<style type="text/css">
#tblart, #tblrpt, #p1 {
	border-collapse: collapse;
	font-size: 15px;
	width: 60%;
	margin: auto;
}

.img_photo {
	vertical-align: middle;
	width: 40px;
	height: 40px;
	cursor: pointer;
}

#divEmpty {
	top: 0px;
	width: 99%;
	height: 99%;
	position: absolute;
	z-index: 1;
	display: none;
	background-color: #ccc;
	opacity: 0.5;
}

th{
	text-align: left;
}

#divRpt {
	position: absolute;
	display: none;
	z-index: 2;
	top: 30%;
	left: 40%;
}
</style>
<script type="text/javascript" src="${root}/static/js/jquery-1.8.2.js"></script>
<script type="text/javascript">
	$(function() {
		$("#a_rpt").click(function() {
			$("#divEmpty").fadeIn(500);
			$("#divRpt").fadeIn(1000);
		});
		$("#btn_close").click(function() {
			$("#divRpt").fadeOut(500);
			$("#divEmpty").fadeOut(500);
		});
		$("#formrpt").submit(function(){
			var msg = $("#msg").val();
			if(msg.length<10){
				alert("禁止灌水[回复字数至少十个字符]");
				return false;
			}
			return true;
		});
		
		var index = 1;
		
		function fun_rptpager(){
			var url = "${root}/tie/pagereport.do";
			var data = {"aid":$("#aid").val(),"index":index};
			$.post(url,data,function(result){
				$("#tblrpt").find("tbody").empty();
				$(result.items).each(function(){
					var row = $("<tr></tr>");
					row.append("<td>"+this.ofid+"楼</td>");
					row.append("<td><img class='img_photo' src='${root}/user/photo.do?id="+this.ouid+"'/>"+this.user.uname+"</td>");
					row.append("<td>"+this.omessage+"</td>");
					row.append("<td>"+this.odate+"</td>");
					row.appendTo($("#tblrpt").find("tbody"));
				});
				
			},"json");
		}
		
		$("#a_first").click(function(){
			index = 1;
			$("#span_index").html(index);
			fun_rptpager();
		});
		
		$("#a_previous").click(function(){
			if(index==1){
				alert("已经是第一页");
				return;
			}
			index--;
			$("#span_index").html(index);
			fun_rptpager();
		});
		
		$("#a_next").click(function(){
			if(index==$("#span_page").html()){
				alert("已经是最后一页");
				return;
			}
			index++;
			$("#span_index").html(index);
			fun_rptpager();
		});
		
		$("#a_last").click(function(){
			index = $("#span_page").html();
			$("#span_index").html(index);
			fun_rptpager();
		});
	});
</script>
</head>
<body>
	<table id="tblart">
		<tr>
			<td>标题：${requestScope.art.atitle}</td>
			<td>访问次数：${requestScope.art.aclick}</td>
			<td><a id="a_rpt" href="javascript:void(0);">回复</a></td>
		</tr>
		<tr>
			<td>分类：${requestScope.art.dtype.dvalue}</td>
			<td>发布人： <img class="img_photo"
				src="${root}/user/photo.do?id=${requestScope.art.auid}" />
				${requestScope.art.user.uname}
			</td>
			<td>发布时间：${requestScope.art.adate}</td>
		</tr>
		<tr>
			<td>内容：</td>
			<td colspan="2">${requestScope.art.acontext}</td>
		</tr>
		<tr>
			<td>附件：</td>
			<td colspan="2"><c:if test="${requestScope.exts.size()==0}">
					没有任何附件
				</c:if> <c:if test="${requestScope.exts.size()>0}">
					<c:forEach var="item" items="${requestScope.exts}">
						<a href="${root}/tie/download.do?id=${item.eid}"> <img
							title="${item.info}" class="img_photo"
							src="${root}/static/images/download.png">
						</a>
					</c:forEach>
				</c:if></td>
		</tr>
	</table>
	<hr />
	<c:if test="${requestScope.rpts.count==0}">
		<p id="p1">没有任何回复信息</p>
	</c:if>
	<c:if test="${requestScope.rpts.count>0}">
		<table id="tblrpt" border="1">
			<thead>
				<tr>
					<th>楼层</th>
					<th>用户</th>
					<th>回复</th>
					<th>时间</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="item" items="${requestScope.rpts.items}">
					<tr>
						<td>${item.ofid}楼</td>
						<td><img class="img_photo"
							src="${root}/user/photo.do?id=${item.ouid}" />
							${item.user.uname}</td>
						<td>${item.omessage}</td>
						<td>${item.odate}</td>
					</tr>
				</c:forEach>
			</tbody>
			<tfoot>
				<tr>
					<td colspan="2">
						共有${requestScope.rpts.count}条记录
						每页显示${requestScope.rpts.size}行
						第<span id="span_index">${requestScope.rpts.index}</span>页 / 
						共<span id="span_page">${requestScope.rpts.page}</span>页
					</td>
					<td colspan="2" style="text-align: right;">
						<a id="a_first" href="javascript:void(0)">首页</a>
						<a id="a_previous" href="javascript:void(0)">上页</a>
						<a id="a_next" href="javascript:void(0)">下页</a>
						<a id="a_last" href="javascript:void(0)">末页</a>
					</td>
				</tr>
			</tfoot>
		</table>
	</c:if>
	<div id="divEmpty"></div>
	<div id="divRpt">
		<form id="formrpt" action="${root}/tie/report.do" method="post">
			<table id="tbl_msg">
				<tr>
					<td><textarea id="msg" name="msg" rows="6" cols="40"></textarea></td>
				</tr>
				<tr>
					<td style="text-align: right;">
						<input type="hidden" id="aid" name="aid" value="${requestScope.art.aid}" />
						<input type="submit" value="确定" />
						<input type="button" id="btn_close" value="关闭" />
					</td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>