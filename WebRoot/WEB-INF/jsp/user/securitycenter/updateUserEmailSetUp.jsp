<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@include file="/WEB-INF/jsp/common/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>普通用户 安全管理中心</title>
<link href="${basePath}/js/css/security_list.css" type="text/css"
	rel="stylesheet">
<script type="text/javascript">var basePath = "${basePath}"</script>
<script type="text/javascript"
	src="${basePath}/jquery/1.11.3/jquery-1.11.3.js"></script>
<style type="text/css">
body {
	font-size: 12px;
	font-family: "微软雅黑";
}

body, ul, li, i, a, p, span, h1, h2, h3, h4, h5, h6, br, img, select,
	input, div, option, b {
	margin: 0;
	padding: 0;
}
/* 盒子*/
.box {
	width: 100%;
	height: 100%;
}

.verification-box {
	margin: 100px auto;
	width: 680px;
}

.verification-box .verification-from {
	padding-left: 120px;
}

.verification-box .verification-from .item {
	height: 43px;
	overflow: hidden;
	padding-bottom: 20px;
	line-height: 43px;
	font-size: 14px;
}

.verification-box .verification-from .item .red-star {
	color: red;
}

.verification-box .verification-from .item .submit {
	height: 40px;
	display: block;
	float: left;
	background: #ffa019;
	border-radius: 3px;
	-webkit-border-radius: 3px;
	-moz-border-radius: 3px;
	line-height: 40px;
	padding: 0 22px;
	color: #fff;
	text-decoration: none !important;
}

.verification-box .verification-from .item .verification-1 {
	float: left;
	width: 119px;
	display: inline-block;
	padding-right: 15px;
	text-align: right;
	line-height: 33px;
	height: 33px;
	color: #ccc;
}

.verification-box .verification-from .item .verification-2 {
	float: left;
	display: inline-block;
	text-align: left;
	line-height: 33px;
}

.verification-box .verification-from .item .verification-3 {
	float: left;
	display: inline-block;
	text-align: left;
	line-height: 33px;
	margin-left: 15px;
	padding-top: 1px;
}

.verification-box .verification-from .item .verification-1 span {
	color: #666;
}

.clearfloat:after {
	display: block;
	clear: both;
	content: "";
	visibility: hidden;
	height: 0;
}
/* 盒子 结束*/
</style>
</head>
<body>
	<div class="box">
		<div class="verification-box">
			<div class="verification-from" id="inser_UserEmail_submit_bind1">
				<div class="item clearfloat">
					<span class="verification-1"> <b class="red-star">*</b> 邮箱:
					</span> <span class="verification-2"> <input type="text"
						class="input-text-2" id="inser_UserEmail_submit_email"
						placeholder="请输入邮箱账号...">
					</span> <span class="verification-3" style="color: red;"
						id="inser_UserEmail_submit_email_error"></span>
				</div>
				<div class="item clearfloat">
					<span class="verification-1"></span> <span class="verification-2">
						<a href="javascript:void(0)" class="submit"
						id="inser_UserEmail_submit" onclick="sendEamil(this)">发送验证邮箱</a>
					</span> <span class="verification-3" style="color: red;"
						id="inser_UserEmail_submitErr"></span>
				</div>
			</div>

			<div class="verification-from" id="inser_UserEmail_submit_bind2"
				style="display: none;">
				<div class="item clearfloat">
					<span class="verification-1"> </span> <span class="verification-2">
						邮件已发送成功！请点击邮件链接完成邮箱重置 </span> <span class="verification-3"
						style="color: red;" id="inser_UserEmail_submit_email_error"></span>
				</div>
				<div class="item clearfloat">
					<span class="verification-1"></span> <span class="verification-2">
						<a href="https://mail.qq.com/" class="submit" target="_blank">查看邮件</a>
					</span> <span class="verification-3" style="color: red;"
						id="inser_UserEmail_submitErr"></span> <a
						href="${basePath}/user/tologin.action" class="submit"
						target="_blank">返回首页</a>
				</div>
			</div>
		</div>
	</div>
	<script type="text/javascript">
	 
	function sendEamil(obj){
		var email = document.getElementById("inser_UserEmail_submit_email").value;
		if(isEmpty(email)){
			document.getElementById("inser_UserEmail_submitErr").innerHTML = "请输入邮箱！!";
			document.getElementById("inser_UserEmail_submit_email").focus();
			return false;
		}else{
			document.getElementById("inser_UserEmail_submitErr").innerHTML = "";
		}
		if(!is_email(email)){
 			 document.getElementById("inser_UserEmail_submitErr").innerHTML = "请输入正确的邮箱！";
			 document.getElementById("inser_UserEmail_submit_email").focus();
			 return false;
		}else{
			 document.getElementById("inser_UserEmail_submitErr").innerHTML = "";
		}
 		$.ajax({
			  type:"post",
			  url:basePath+"/user/securitycenter/UpdateUserEmailSendResetEamil.action",
			  data:{"email":email},
			  success:Update_UserEmail_SendResetEamil_Callback
		 });
		
	}
	
	//19用户邮箱修改  发送邮件重置链接 回调函数
	 function Update_UserEmail_SendResetEamil_Callback(data){
	 	 $("#inser_UserEmail_submitErr").text("");
		 var obj = $.parseJSON(data);
		 if(obj.result=="logout"){
			 window.location.href=basePath+"/user/tologin.action";
		 }else if(obj.result =="email_null"){
			 $("#inser_UserEmail_submitErr").text("请输入邮箱账号...");
		 }else if(obj.result == "success"){
			 alert("邮件发送成功！请打开邮箱点击邮件链接完成重置邮箱！");
			 $("#inser_UserEmail_submit_bind1").hide();
			 $("#inser_UserEmail_submit_bind2").show(); 
		 }else if(obj.result == "email_nonull"){
			 $("#inser_UserEmail_submitErr").text("邮箱账号已注册了...");
		 }
	 };
	 
	 function is_email(str){ 
	     var regExp = /^([\w\.])+@\w+\.([\w\.])+$/;
	     return regExp.test(str);
	 };
	 
	 function isEmpty(val) {
			val = $.trim(val);
			if (val == null)
				return true;
			if (val == undefined || val == 'undefined')
				return true;
			if (val == "")
				return true;
			if (val.length == 0)
				return true;
			if (!/[^(^\s*)|(\s*$)]/.test(val))
				return true;
			return false;
		};
</script>
</body>
</html>