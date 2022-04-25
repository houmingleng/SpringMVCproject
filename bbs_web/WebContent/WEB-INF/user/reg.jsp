<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="root" value="${pageContext.request.contextPath}"></c:set>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>会员注册</title>
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
<script type="text/javascript" src="${root}/static/js/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript">
    $(function(){
    	$("#img_authcode").click(function(){
    		$(this).attr("src","${root}/user/authcode.do?"+Math.random());
    	});
    	
    	$("#formreg").submit(function(){
    		
    		var flag= false;
    		
    		var uname = $("#uname").val();
    		var reg_uname = /^\w{6,30}$/;
    		if(reg_uname.test(uname)){
    			$.ajax({
	    			async:false,
	    			type:"POST",
	    			dataType:"json",
	    			url:"${root}/user/exist.do",
	    			data:{"name":uname},
	    			success:function(result){
	    				if(200==result.code){
	    					$("#span_uname").removeClass("error").addClass("ok").html(result.message);
	    					flag = true;
	    				}else{
	    					$("#span_uname").removeClass("ok").addClass("error").html(result.message);
	    					flag = false;
	    				}
	    			}
	    		});
    		}else{
    			$("#span_uname").html("用户名必须是六至三十位有效字符[数字字母组合]");
    			return false;
    		}
    		
    		if(!flag){
    			return false;
    		}
  
    		var upwd = $("#upwd").val();
    		if(upwd.length<6){
    			$("#span_upwd").html("密码不能少于六位");
    			return false;
    		}else{
    			$("#span_upwd").html("");
    		}
    		
    		var apwd = $("#apwd").val();
    		if(apwd.length<6){
    			$("#span_apwd").html("密码不能少于六位");
    			return false;
    		}else{
    			if(apwd==upwd){
    				$("#span_apwd").html("");
    			}else{
    				$("#span_apwd").html("两次密码输入不一致");
        			return false;
    			}
    		}
    		
    		if($(this).find("input:radio:checked").length==0){
    			$("#span_usex").html("性别不能为空");
    			return false;
    		}else{
    			$("#span_usex").html("");
    		}
    		
    		if($("#ublood").val().length==0){
    			$("#span_ublood").html("血型不能为空");
    			return false;
    		}else{
    			$("#span_ublood").html("");
    		}
    		
    		if($(this).find("input:checkbox:checked").length==0){
    			$("#span_uhobby").html("爱好不能为空");
    			return false;
    		}else{
    			$("#span_uhobby").html("");
    		}
    		
    		if($("#ubirth").val().length==0){
    			$("#span_ubirth").html("生日不能为空");
    			return false;
    		}else{
    			$("#span_ubirth").html("");
    		}
    		
    		if($("#photo").val().length==0){
    			$("#span_photo").html("头像不能为空");
    			return false;
    		}else{
    			if($("#photo")[0].files[0].size/(1024*1024)>10){
    				$("#span_photo").html("图片大小上限为10MB");
        			return false;
    			}else{
    				$("#span_photo").html("");
    			}
    		}
    		
    		var code = $("#authcode").val();
    			
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
	<form id="formreg" action="${root}/user/reg.do" method="post" enctype="multipart/form-data">
	<table border="1" id="tbluser">
		<tr>
			<td style="text-align: right;">用户名：</td>
			<td><input type="text" id="uname" name="uname" maxlength="30" /></td>
			<td><span id="span_uname" class="error"></span></td>
		</tr>
		<tr>
			<td style="text-align: right;">密码：</td>
			<td><input type="password" id="upwd" name="upwd" maxlength="30" /></td>
			<td><span id="span_upwd" class="error"></span></td>
		</tr>
		<tr>
			<td style="text-align: right;">确认密码：</td>
			<td><input type="password" id="apwd" name="apwd" maxlength="30" /></td>
			<td><span id="span_apwd" class="error"></span></td>
		</tr>
		<tr>
			<td style="text-align: right;">性别：</td>
			<td>
				<c:forEach var="item" items="${requestScope.dsexList}">
					<input type="radio" name="usex" value="${item.dkey}" />${item.dvalue}
				</c:forEach>
			</td>
			<td><span id="span_usex" class="error"></span></td>
		</tr>
		<tr>
			<td style="text-align: right;">血型：</td>
			<td>
				<select id="ublood" name="ublood"> 
					<option value="">请选择</option>
					<c:forEach var="item" items="${requestScope.dbloodList}">
						<option value="${item.dkey}">${item.dvalue}</option>
					</c:forEach>
				</select>
			</td>
			<td><span id="span_ublood" class="error"></span></td>
		</tr>
		<tr>
			<td style="text-align: right;">爱好：</td>
			<td>
				<c:forEach var="item" items="${requestScope.dhobbyList}">
					<input type="checkbox" name="uhobby" value="${item.dkey}" />${item.dvalue}
				</c:forEach>
			</td>
			<td><span id="span_uhobby" class="error"></span></td>
		</tr>
		<tr>
			<td style="text-align: right;">生日：</td>
			<td><input type="text" id="ubirth" name="ubirth" readonly="readonly" onclick="WdatePicker()" /></td>
			<td><span id="span_ubirth" class="error"></span></td>
		</tr>
		<tr>
			<td style="text-align: right;">头像：</td>
			<td><input type="file" id="photo" name="photo" accept="image/bmp,image/png,image/jpg,image/jpeg" /></td>
			<td><span id="span_photo" class="error"></span></td>
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