<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="root" value="${pageContext.request.contextPath}"></c:set>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>帖子上传</title>
<link rel="stylesheet" type="text/css" href="${root}/static/css/comm.css"  />
<style type="text/css">
	#tblart{
		border-collapse: collapse;
		font-size: 15px;
		margin-top: 50px;
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
    	
    	var count =1;
    	
    	$("#btn_add").click(function(){
    		if(count==5){
    			alert("一个帖子最多关联五个附件");
    			return;
    		}
    		var row = $("<div></div>");
    		row.append("<input type='file' class='files' name='files' />&nbsp;");
    		row.append("<input type='button' value='移除' />");
    		row.appendTo($(this).parent());
    		count++;
    		
    		//注册事件
    		row.find("input:button").click(function(){
    			if(confirm('是否确定删除该附件?')){
    				$(this).parent().empty();
    				count--;
    			}
    		});
    	});
    	
    	$("#formupload").submit(function(){
    		var atitle = $("#atitle").val();
    		if(atitle.length==0){
    			$("#span_atitle").html("标题不能为空");
    			return false;
    		}else{
    			$("#span_atitle").html("");
    		}
    		
    		var adid = $("#adid").val();
    		if(adid.length==0){
    			$("#span_adid").html("分类不能为空");
    			return false;
    		}else{
    			$("#span_adid").html("");
    		}
    		
    		var acontext = $("#acontext").val();
    		if(acontext.length==0){
    			$("#span_acontext").html("内容不能为空");
    			return false;
    		}else{
    			$("#span_acontext").html("");
    		}
    		
    		var size  = 0;
    		for(var i=0;i<$(".files").length;i++){
    			if($(".files")[i].value.length>0){
    				size+=$(".files")[i].files[0].size;
    			}
    		}
    		
    		if(size/1024/1024>10){
    			$("#span_files").html("所有附件上传上限为10MB");
    			return false;
    		}else{
    			$("#span_files").html("");
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
	<form id="formupload" action="${root}/tie/upload.do" method="post" enctype="multipart/form-data">
	<table border="1" id="tblart">
		<tr>
			<td style="text-align: right;">标题：</td>
			<td><input type="text" id="atitle" name="atitle" maxlength="50" /></td>
			<td><span id="span_atitle" class="error"></span></td>
		</tr>
		<tr>
			<td style="text-align: right;">分类：</td>
			<td>
				<select id="adid" name="adid"> 
					<option value="">请选择</option>
					<c:forEach var="item" items="${requestScope.dtypeList}">
						<option value="${item.dkey}">${item.dvalue}</option>
					</c:forEach>
				</select>
			</td>
			<td><span id="span_adid" class="error"></span></td>
		</tr>
		<tr>
			<td style="text-align: right;">内容：</td>
			<td>
				<textarea id="acontext" name="acontext" rows="8" cols="50"></textarea>
			</td>
			<td><span id="span_acontext" class="error"></span></td>
		</tr>
		<tr>
			<td style="text-align: right;">附件：</td>
			<td>
				<input type="file" class="files" name="files" />
				<input type="button" id="btn_add" value="添加" />
			</td>
			<td><span id="span_files" class="error"></span></td>
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
				<input type="submit" value="上传" />
				<a href="${root}/user/index.do">返回首页</a>
				<span id="span" class="error">${requestScope.message}</span>
			</td>
			<td>&nbsp;</td>
		</tr>
	</table>
	</form>
</body>
</html>