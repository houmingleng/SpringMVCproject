<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="root" value="${pageContext.request.contextPath}"></c:set>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>社区论坛</title>
<link rel="stylesheet" type="text/css" href="${root}/static/css/comm.css"  />
<style type="text/css">
	#img_photo{
    	width: 40px;
    	height: 40px;
    	cursor: pointer;
	}
	#txtKey{
        width: 300px;
        margin-left: 300px;
    }
    #divKey{
        margin-left: 300px;
        width: 302px;
        height: 200px;
        overflow-y: scroll;
        border: solid 1px #ccc;
        display: none;
        font-size: 13px;
        position: absolute;
        z-index: auto;
        background-color: aliceblue;
    }
    .divRow{
        clear: both;
        cursor: pointer;
        height: 20px;
    }
    .divRow:hover{
        background-color: yellow;
    }
    .divLeft{
        width: 60%;
        float: left;
    }
    .divRight{
        width: 40%;
        float: right;
    }
    #tblArt{
		border-collapse: collapse;
		font-size: 15px;
		width:80%;
		margin: auto;
		
	}
	td{
		text-align: center;
	}
</style>
<script type="text/javascript" src="${root}/static/js/jquery-1.8.2.js"></script>
<script type="text/javascript">
    $(function(){
    	//帖子分页
    	var key = '';
    	var index = 1;
    	var page = 0;
    	function fun_artpager(){
    		var url = "${root}/user/artpager.do";
    		var data = {"key":key,"index":index};
    		$.post(url,data,function(result){
    			page = result.page;
   				$(result.items).each(function(){
   					var row  = $("<tr></tr>");
   					row.append("<td>"+this.dtype.dvalue+"</td>");
   					row.append("<td>"+this.atitle+"</td>");
   					row.append("<td>"+this.user.uname+"</td>");
   					row.append("<td>"+this.adate+"</td>");
   					row.append("<td>"+this.aclick+"</td>");
   					row.append("<td><a href='${root}/tie/info.do?id="+this.aid+"'>详情</a></td>");
   					row.appendTo($("#tblArt").find("tbody"));
   				});
    		},"json");
    	}
    	//开始就加载数据
    	fun_artpager();
    	//加载更多数据
    	$("#aMore").click(function(){
    		if(index<page){
    			index++;
    			fun_artpager();
    		}else{
    			alert("数据已经加载完毕");
    		}
    	});
    	//滚动条加载更多数据
    	$(window).scroll(function(){
            if($(window).height()+$(window).scrollTop()==$(document).height()){
            	if(index<page){
        			index++;
        			fun_artpager();
        		}else{
        			alert("数据已经加载完毕");
        		}
            }
        });
    	//自动提示关键字列表
    	$("#txtKey").keyup(function(){
    		var key = $(this).val();
    		if(key.length==0){
    			return;
    		}
    		$("#divKey").empty();
    		var url = "${root}/user/keyword.do";
    		var data = {"key":key};
    		$.post(url,data,function(result){
    			$(result).each(function(){
    				var row = $("<div class='divRow'></div>");
    				row.append("<div class='divLeft'>"+this.kname+"</div>");
    				row.append("<div class='divRight'>被搜索"+this.kclick+"次</div>");
    				row.appendTo($("#divKey"));
    				row.click(function(){
    		            $("#txtKey").val($(this).find(".divLeft").html());
    		        });
    			});
    		},"json");
            $("#divKey").slideDown(1000);
        }).blur(function(){
            $("#divKey").slideUp(1000);
        });
    	//点击搜索
    	$("#txtSearch").click(function(){
    		key = $("#txtKey").val();
    		if(key.length==0){
    			alert("搜索关键字不能为空");
    			return;
    		}
    		var url = "${root}/user/artlist.do";
    		var data = {"key":key};
    		$.post(url,data,function(result){
    			page = result.page;
    			if(result.count==0){
    				alert("没有任何匹配的信息");
    			}else{
    				$("#tblArt").find("tbody").empty();
    				$(result.items).each(function(){
    					var row  = $("<tr></tr>");
    					row.append("<td>"+this.dtype.dvalue+"</td>");
    					row.append("<td>"+this.atitle+"</td>");
    					row.append("<td>"+this.user.uname+"</td>");
    					row.append("<td>"+this.adate+"</td>");
    					row.append("<td>"+this.aclick+"</td>");
    					row.append("<td><a href='${root}/tie/info.do?id="+this.aid+"'>详情</a></td>");
    					row.appendTo($("#tblArt").find("tbody"));
    				});
    			}
    		},"json");
    	});
    });
</script>
</head>
<body>
    <input type="text" id="txtKey" placeholder="请输入关键字" />
    <input type="button" id="txtSearch" value="搜索" />
    <div id="divKey"></div>
    <span>&nbsp;&nbsp;&nbsp;&nbsp;</span>
    <c:if test="${not empty sessionScope.user}">
		<img title="${sessionScope.user.info}" id="img_photo" src="${root}/user/photo.do?id=${sessionScope.user.uid}" />
     		<span>${sessionScope.user.uname}</span>
     		<a href="${root}/tie/upload.do">上传</a>
     		<a href="${root}/user/logout.do">注销</a>
	</c:if>
   	<c:if test="${empty sessionScope.user}">
   		<a href="${root}/user/login.do">请登录</a>
   		<a href="${root}/user/reg.do">会员注册</a>
   	</c:if>
    <hr/>
    <table id="tblArt" border="1">
    	<thead>
    		<tr>
    			<th>类型</th>
    			<th>标题</th>
    			<th>发帖人</th>
    			<th>发布时间</th>
    			<th>访问量</th>
    			<th>操作</th>
    		</tr>
    	</thead>
    	<tbody>
    	</tbody>
    	<tfoot>
    		<tr>
    			<td colspan="6">
    				<a id="aMore" href="javascript:void(0);">加载更多</a>
    			</td>
    		</tr>
    	</tfoot>
    </table>
</body>
</html>