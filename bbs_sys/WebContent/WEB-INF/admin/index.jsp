<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="root" value="${pageContext.request.contextPath}"></c:set>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>后台管理</title>
<link rel="stylesheet" type="text/css" href="${root}/static/css/comm.css"  />
<style type="text/css">
	#divMain{
        width: 90%;
        height: 600px;
        margin: 5px auto;
        border: solid 1px #ccc;
    }
    #divTop{
        width: 100%;
        height: 60px;
        border-bottom: solid 1px #ccc;
    }
    #divNav{
        width: 25%;
        float: left;
        height: 450px;
        border-right: solid 1px #ccc;
        font-size: 15px;
        padding-top: 30px;
    }
    #divNav dl{
        margin-top: 40px;
        margin-left: 60px;
    }
    #divContext{
        width: 74%;
        float: left;
        height: 480px;
    }
    #divCopy{
        clear: left;
        width: 100%;
        height: 60px;
        border-top: solid 1px #ccc;
    }
    #target{
        width: 100%;
        height: 100%;
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
	
	function fun_tree(fdiv,fid){
		var url = "${root}/admin/tree.do?fid="+fid;
		var step = 30;
      	$.getJSON(url,function(result){
      		$(result).each(function(){
      			var row = $("<div style='margin-left:50px;'></div>");
      			row.append("<a href='javascript:void(0);' lang='${root}"+this.turl+"'>"+this.ttitle+"</a>");
      			//注册事件
      			row.find("a").click(function(){
                    $("#target").attr("src",($(this).attr("lang")));
                });
      			row.appendTo(fdiv);
      			fun_tree(row,this.tid);
      		});
      	});
	}

    $(function(){
    	fun_tree($("#divNav"),0);
    });
</script>
</head>
<body>
	<div id="divMain">
        <div id="divTop">
        	<div style="margin-top: 10px; margin-left: 40px;">
	        	<img title="${sessionScope.user.info}" id="img_photo" src="${root}/admin/photo.do?id=${sessionScope.user.uid}" />
	        	<span>${sessionScope.user.uname}</span>
        	</div>
        </div>
        <div id="divNav">
        </div>
        <div id="divContext">
            <iframe id="target" frameborder="0" src="${root}/static/images/error-default.jpg"></iframe>
        </div>
        <div id="divCopy"></div>
    </div>
</body>
</html>