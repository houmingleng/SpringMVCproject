<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="root" value="${pageContext.request.contextPath}"></c:set>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>会员登录</title>
<link rel="stylesheet" type="text/css" href="${root}/static/css/comm.css"  />
<style type="text/css">
	#tbluser{
		border-collapse: collapse;
		font-size: 15px;
		margin-top: 100px;
		margin-left: 400px;
	}
	#authcode{
		width: 50px;
	}
	#img_authcode{
		vertical-align: middle;
		cursor: pointer;
	}
</style>
<script type="text/javascript" src="${root}/static/js/jquery-1.8.2.js"></script>
<script type="text/javascript">
    $(function(){
    	$("#img_authcode").click(function(){
    		$(this).attr("src","${root}/user/authcode.do?"+Math.random());
    	});
    	
    	$("#formlogin").submit(function(){
    		var uname = $("#uname").val();
    		var reg_uname = /^\w{6,30}$/;
    		if(reg_uname.test(uname)){
    			$("#span_uname").html("");
    		}else{
    			$("#span_uname").html("用户名必须是六至三十位有效字符[数字字母组合]");
    			return false;
    		}
    		
    		
    		var upwd = $("#upwd").val();
    		if(upwd.length<6){
    			$("#span_upwd").html("密码不能少于六位");
    			return false;
    		}else{
    			$("#span_upwd").html("");
    		}
    		
    		var code = $("#authcode").val();
    		var flag= false;	
    		if(code.length==4){
	    		$.ajax({
	    			async:false,
	    			type:"POST",
	    			dataType:"json",
	    			url:"${root}/user/check.do",
	    			data:{"code":code},
	    			success:function(result){
	    				if(200==result.code){
	    					$("#span_authcode").removeClass("error").addClass("ok").html(result.message);
	    					flag = true;
	    				}else{
	    					$("#span_authcode").removeClass("ok").addClass("error").html(result.message);
	    					flag = false;
	    				}
	    			}
	    		});
    		}else{
    			$("#span_authcode").html("验证码必须是四位有效字符[数字字母组合]");
    			return false;
    		}
    		
    		return flag;
    	});
    });
</script>
</head>
<body>
	<form id="formlogin" action="${root}/user/login.do" method="post">
	<table border="1" id="tbluser">
		<tr>
			<td style="text-align: right;">用户名：</td>
			<td><input type="text" id="uname" name="uname" value="zhangsan" maxlength="30" /></td>
			<td><span id="span_uname" class="error"></span></td>
		</tr>
		<tr>
			<td style="text-align: right;">密码：</td>
			<td><input type="password" id="upwd" name="upwd" value="a123123" maxlength="30" /></td>
			<td><span id="span_upwd" class="error"></span></td>
		</tr>
		<tr>
			<td style="text-align: right;">验证码：</td>
			<td>
				<input type="text" id="authcode" maxlength="4" />
				<img id="img_authcode" title="看不清楚换一张" src="${root}/user/authcode.do" />
			</td>
			<td><span id="span_authcode" class="error"></span></td>
		</tr>
		<tr>
			<td style="text-align: right;">&nbsp;</td>
			<td>
				<input type="submit" value="登录" />
				<a href="${root}/user/index.do">返回首页</a>
				<span id="span" class="error">${requestScope.message}</span>
			</td>
			<td>&nbsp;</td>
		</tr>
	</table>
	</form>
</body>
</html>