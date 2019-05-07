<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@include file="/WEB-INF/jsp/common/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>莫邪网-用户绑定页面</title>
<style>
.red {
	corol: red;
}
</style>
<%@include file="/WEB-INF/jsp/common/public.jsp"%>
</head>
<body>
	<div class="container">
		<div class="row" style="margin-top: 80px;">
			<div class="col-md-6">
				<div class="title text-center">已注册用户?立即登录并绑定QQ</div>
				<div class="col-md-10 col-md-offset-2" style="margin-top: 20px;">
					<label class="col-sm-2 text-right">用户名:</label>
					<div class="col-sm-6">
						<input class="form-control" id="username" placeholder="请输入用户名..." />
					</div>
				</div>
				<div class="col-md-10 col-md-offset-2" style="margin-top: 20px;">
					<label class="col-sm-2 text-right">密码:</label>
					<div class="col-sm-6">
						<input type="password" class="form-control" id="password"
							placeholder="请输入密码..." />
					</div>
				</div>
				<div class="col-md-10 col-md-offset-2" style="margin-top: 20px;">
					<div class="col-sm-4 col-sm-offset-2">
						<input type="button" class="form-control" value="登录并绑定"
							onclick="ThirdBindUser.bindUser(this)" />
					</div>
					<span class="red" id="bindUserError"></span>
				</div>
			</div>

			<!-- 手机注册并绑定  start-->
			<div class="col-md-6" id="regPhone">
				<div class="title text-center">没有注册用户？请注册并绑定QQ</div>
				<div class="col-md-10 col-md-offset-2" style="margin-top: 20px;">
					<label class="col-sm-2 text-right">用户名:</label>
					<div class="col-sm-6">
						<input class="form-control" id="bind_phone_username"
							placeholder="请输入用户名..." />
					</div>
				</div>
				<div class="col-md-10 col-md-offset-2" style="margin-top: 20px;">
					<label class="col-sm-2 text-right">手机号:</label>
					<div class="col-sm-6">
						<input class="form-control" id="bind_phone_userphone"
							placeholder="请输入手机号..." /><a
							href="javascript:ThirdBindUser.sendPhondCode(this)">点我发送手机短信</a>
					</div>
				</div>
				<div class="col-md-10 col-md-offset-2" style="margin-top: 20px;">
					<label class="col-sm-2 text-right">密码:</label>
					<div class="col-sm-6">
						<input type="password" class="form-control"
							id="bind_phone_password" placeholder="请输入密码..." />
					</div>
				</div>
				<div class="col-md-10 col-md-offset-2" style="margin-top: 20px;">
					<label class="col-sm-2 text-right">再输入密码:</label>
					<div class="col-sm-6">
						<input type="password" class="form-control"
							id="bind_phone_password1" placeholder="请再输入密码..." />
					</div>
				</div>
				<div class="col-md-10 col-md-offset-2" style="margin-top: 20px;">
					<label class="col-sm-2 text-right">手机验证码:</label>
					<div class="col-sm-6">
						<input class="form-control" id="bind_phone_phonecode"
							placeholder="请输入手机验证码..." />
					</div>
				</div>
				<div class="col-md-10 col-md-offset-2" style="margin-top: 20px;">
					<label class="col-sm-2 text-right">验证码:</label>
					<div class="col-sm-6">
						<input class="form-control" id="bind_phone_code"
							placeholder="请输入验证码..." />
					</div>
					<div style="float: left;">
						<img style="height: 28px;" id="bind_phone_codeImg" alt="点击更换"
							title="点击更换" src="${basePath}/Kaptcha.jpg" />
					</div>
				</div>
				<div class="col-md-10 col-md-offset-2" style="margin-top: 20px;">
					<div class="col-sm-4 col-sm-offset-2">
						<a href="javascript:regEmail(this)">邮箱注册方式？</a> <input
							type="button" class="form-control" value="注册并绑定"
							onclick="ThirdBindUser.registerBindUserPassedPhone(this)" />
					</div>
					<span class="red" id="bind_phone_bindUserError"></span>
				</div>
			</div>
			<!-- 手机注册并绑定  end-->

			<!-- 邮箱注册并绑定  start-->
			<div class="col-md-6" id="regEmail" style="display: none;">
				<div class="title text-center">没有注册用户？请注册并绑定QQ</div>
				<div class="col-md-10 col-md-offset-2" style="margin-top: 20px;">
					<label class="col-sm-2 text-right">用户名:</label>
					<div class="col-sm-6">
						<input class="form-control" id="bind_email_username"
							placeholder="请输入用户名..." />
					</div>
				</div>
				<div class="col-md-10 col-md-offset-2" style="margin-top: 20px;">
					<label class="col-sm-2 text-right">邮箱账号:</label>
					<div class="col-sm-6">
						<input class="form-control" id="bind_email_userphone"
							placeholder="请输入邮箱账号..." /><a
							href="javascript:ThirdBindUser.sendEmailCode(this)">点我发送邮箱验证码</a>
					</div>
				</div>
				<div class="col-md-10 col-md-offset-2" style="margin-top: 20px;">
					<label class="col-sm-2 text-right">密码:</label>
					<div class="col-sm-6">
						<input type="password" class="form-control"
							id="bind_email_password" placeholder="请输入密码..." />
					</div>
				</div>
				<div class="col-md-10 col-md-offset-2" style="margin-top: 20px;">
					<label class="col-sm-2 text-right">再输入密码:</label>
					<div class="col-sm-6">
						<input type="password" class="form-control"
							id="bind_email_password1" placeholder="请再输入密码..." />
					</div>
				</div>
				<div class="col-md-10 col-md-offset-2" style="margin-top: 20px;">
					<label class="col-sm-2 text-right">邮箱验证码:</label>
					<div class="col-sm-6">
						<input class="form-control" id="bind_email_phonecode"
							placeholder="请输入邮箱验证码..." />
					</div>
				</div>
				<div class="col-md-10 col-md-offset-2" style="margin-top: 20px;">
					<label class="col-sm-2 text-right">验证码:</label>
					<div class="col-sm-6">
						<input class="form-control" id="bind_email_code"
							placeholder="请输入验证码..." />
					</div>
					<div style="float: left;">
						<img style="height: 28px;" id="bind_email_codeImg" alt="点击更换"
							title="点击更换" src="${basePath}/Kaptcha.jpg" />
					</div>
				</div>
				<div class="col-md-10 col-md-offset-2" style="margin-top: 20px;">
					<div class="col-sm-4 col-sm-offset-2">
						<a href="javascript:regPhone(this)">手机注册方式？</a> <input
							type="button" class="form-control" value="注册并绑定"
							onclick="ThirdBindUser.registerBindUserPassedEmail(this)" />
					</div>
					<span class="red" id="bind_email_bindUserError"></span>
				</div>
			</div>
			<!-- 邮箱注册并绑定  end-->
		</div>
	</div>
	<script type="text/javascript">
	$(function(){
		//验证码
 		$("#bind_phone_codeImg").click(function(){
			 $(this).attr("src",basePath+"/Kaptcha.jpg?d="+new Date().getTime());
		});
		
		$("#bind_email_codeImg").click(function(){
			 $(this).attr("src",basePath+"/Kaptcha.jpg?d="+new Date().getTime());
		});
 	});
   		var ThirdBindUser = {
 			//已注册用户绑定QQ
			bindUser:function(obj){
 				 var username = dom("username").value;
				 var password = dom("password").value;
				 if(isEmpty(username)){
					 dom("username").focus();
					 $("#bindUserError").text("请输入用户名");
					 return false;
				 }else{
					 $("#bindUserError").text("");
				 }
				 if(isEmpty(password)){
					 dom("password").focus();
					 $("#bindUserError").text("请输入密码");
					 return false;
				 }else{
					 $("#bindUserError").text("");
				 }
   				 var params = {"loginname":username,"password":password,"email":username,"mobilephone":username,}; 
  				 $.ajax({
 					 type:"post",
 					 url:basePath+"/qq/thirdBindUser.action",
 					 data:params,
 					 success:function(data){
  						 var obj = $.parseJSON(data);
  						 $("#bindUserError").text("");
 						 if(obj.result == "username_null"){
 							$("#bindUserError").text("请输入用户名");
 						 }else if(obj.result == "username_error"){
 							$("#bindUserError").text("您输入的用户名或密码有误！"); 
 						 }else if(obj.result == "risklevel_error"){
 							$("#bindUserError").text("您的账号存在安全隐患！请联系管理员解除安全隐患"); 
 						 }else if(obj.result == "password_error"){
 							$("#bindUserError").text("您输入的用户名或密码有误！"); 
 						 }else if(obj.result == "logout"){
 							 window.location.href=basePath+"/user/tologin.action";
 						 }else if(obj.result =="success"){
 							 alert("绑定成功！");
 							 window.location.href=basePath+"/user/tologin.action";
 						 }else if (obj.result =="fail"){
 							$("#bindUserError").text("绑定失败！");  
 						 }
 					 }
 				 });
 			},
 			//注册并绑定  手机号
 			registerBindUserPassedPhone:function(obj){
 				var loginname = $("#bind_phone_username").val();
 				var mobilephone = $("#bind_phone_userphone").val();
 				var password = $("#bind_phone_password").val();
 				var password1 = $("#bind_phone_password1").val();
 				var code = $("#bind_phone_code").val();
 				var pcode = $("#bind_phone_phonecode").val();
 				if(isEmpty(loginname)){
 					$("#bind_phone_bindUserError").text("请输入用户名...");
 					$("#bind_phone_username").focus();
 					return false;
 				}else{
 					$("#bind_phone_bindUserError").text("");
 				}
 				if(isEmpty(mobilephone)){
 					$("#bind_phone_bindUserError").text("请输入手机号码...");
 					$("#bind_phone_userphone").focus();
 					return false;
 				}else{
 					if(!is_phone(mobilephone)){
 						$("#bind_phone_bindUserError").text("请输入正确的手机号码格式");
 						$("#bind_phone_userphone").focus();
 						 return false;
 					}else{
 						$("#bind_phone_bindUserError").text("");
 					}
  				}
 				
 				if(isEmpty(password)){
 					$("#bind_phone_bindUserError").text("请输入密码...");
 					$("#bind_phone_password").focus();
 					return false;
 				}else{
 					if(!is_password(password)){
 						$("#bind_phone_bindUserError").text("密码格式错误！请输入6-18位的字母，数字组合！");
 						$("#bind_phone_password").focus();
 						return false;
 					}else{
  	 					$("#bind_phone_bindUserError").text("");
 					}
 				}
 				
 				if(isEmpty(password1)){
 					$("#bind_phone_bindUserError").text("请再输入密码...");
 					$("#bind_phone_password1").focus();
 					return false;
 				}else{
 					$("#bind_phone_bindUserError").text("");
 				}
 				
 				if(isNotEmpty(password) && isNotEmpty(password1)){
 					if(password != password1){
 						$("#bind_phone_bindUserError").text("密码不一致");
 						return false;
 					}else{
 						$("#bind_phone_bindUserError").text("");
 					}
 				}
 				if(isEmpty(pcode)){
 					$("#bind_phone_bindUserError").text("请输入手机验证码...");
 					$("#bind_phone_phonecode").focus();
 					return false;
 				}else{
 					$("#bind_phone_bindUserError").text("");
 				}
 				
 				if(isEmpty(code)){
 					$("#bind_phone_bindUserError").text("请输入验证码...");
 					$("#bind_phone_code").focus();
 					return false;
 				}else{
 					$("#bind_phone_bindUserError").text("");
 				}
  				var params = {"loginname":loginname,"mobilephone":mobilephone,"password":password,"code":code,"pcode":pcode};
 				$.ajax({
					 type:"post",
					 url:basePath+"/qq/thirdBindUserAndRegisterPassedPhone.action",
					 data:params,
					 success:function(data){
 						 var obj = $.parseJSON(data);
 						  $("#bind_phone_bindUserError").text("");
 						 if(obj.result == "username_null"){
 							$("#bind_phone_bindUserError").text("请输入用户名...");  
						 }else  if(obj.result == "username_error"){
							 $("#bind_phone_bindUserError").text("您输入的用户名已经注册过了");   
						 }else  if(obj.result == "password_null"){
							 $("#bind_phone_bindUserError").text("请输入密码");  
						 }else  if(obj.result == "mobilephone_null"){
							 $("#bind_phone_bindUserError").text("请输入手机号码");   
						 }else  if(obj.result == "code_null"){
							 $("#bind_phone_bindUserError").text("请输入验证码");  
						 }else  if(obj.result == "pcode_null"){
							 $("#bind_phone_bindUserError").text("请输入手机验证码");   
						 }else  if(obj.result == "mobilephone_error"){
							 $("#bind_phone_bindUserError").text("请输入正确的手机号码");    
						 }else  if(obj.result == "mobilephone_fail"){
							 $("#bind_phone_bindUserError").text("手机号码已经注册过了");    
						 }else  if(obj.result == "code_error"){
							 $("#bind_phone_bindUserError").text("请输入正确的验证码");  
						 }else if(obj.result == "pcode2_null"){
							 $("#bind_phone_bindUserError").text("请点击短信发送按钮！");   
						 }else if(obj.result == "pcode_error"){
							 $("#bind_phone_bindUserError").text("手机短信验证码错误！");  
						 }else if(obj.result == "success"){
							 alert("注册用户并绑定QQ成功");  
							 window.location.href=basePath+"/user/tologin.action";
						 }else if(obj.result == "fail"){
							 $("#bind_phone_bindUserError").text("注册用户并绑定QQ失败");  
						 }else if(obj.result == "logout"){
							 alert("session失效！请重新登录");
							 window.location.href=basePath+"/user/tologin.action";
						 }
					 }
				 });
 			},
 			//根据邮箱注册用户并绑定微博
 			registerBindUserPassedEmail:function(){
 				var username =$("#bind_email_username").val();
 				var email =$("#bind_email_userphone").val();
 				var password =$("#bind_email_password").val();
 				var password1 =$("#bind_email_password1").val();
 				var ecode =$("#bind_email_phonecode").val();
 				var code =$("#bind_email_code").val();
 			 	if(isEmpty(username)){
 			 		$("#bind_email_bindUserError").text("请输入用户名...");
 			 		$("#bind_email_username").focus();
  			 		return false;
 			 	}else{
 			 		$("#bind_email_bindUserError").text("");
 			 	}
 			 	
 			 	if(isEmpty(email)){
 			 		$("#bind_email_bindUserError").text("请输入邮箱账号...");
 			 		$("#bind_email_userphone").focus();
  			 		return false;
 			 	}else{
 			 		if(!is_email(email)){
 			 			 $("#bind_email_bindUserError").text("请输入正确的邮箱格式！");
 	 			 		 $("#bind_email_userphone").focus();
  			 			 return false;
 			 		}else{
 	 			 		$("#bind_email_bindUserError").text("");
 			 		}
 			 	}
 			 	if(isEmpty(password)){
 			 		$("#bind_email_bindUserError").text("请输入用户密码...");
 			 		$("#bind_email_password").focus();
  			 		return false;
 			 	}else{
 			 		if(!is_password(password)){
			 			 $("#bind_email_bindUserError").text("请输入6-18位的数字，字母组合");
	 			 		 $("#bind_email_password").focus();
 			 			 return false;
			 		}else{
	 			 		$("#bind_email_bindUserError").text("");
			 		}
  			 	}
 			 	if(isEmpty(password1)){
 			 		$("#bind_email_bindUserError").text("请再输入用户密码...");
 			 		$("#bind_email_password1").focus();
  			 		return false;
 			 	}else{
 			 		$("#bind_email_bindUserError").text("");
 			 	}
 			 	if(isNotEmpty(password) && isNotEmpty(password1)){
 			 		if(password != password1){
 			 			$("#bind_email_bindUserError").text("用户密码不一致...");
  			 			return false;
 			 		}else{
 			 			$("#bind_email_bindUserError").text("");
 			 		}
 			 	}
 			 	if(isEmpty(ecode)){
 			 		$("#bind_email_bindUserError").text("请输入邮箱验证码...");
 			 		$("#bind_email_phonecode").focus();
  			 		return false;
 			 	}else{
 			 		$("#bind_email_bindUserError").text("");
 			 	}
 			 	if(isEmpty(code)){
 			 		$("#bind_email_bindUserError").text("请输入验证码...");
 			 		$("#bind_email_code").focus();
  			 		return false;
 			 	}else{
 			 		$("#bind_email_bindUserError").text("");
 			 	}
   				var params = {"username":username,"email":email,"password":password,"code":code,"ecode":ecode};
 	 				$.ajax({
 						 type:"post",
 						 url:basePath+"/qq/thirdBindUserAndRegisterPassedEmail.action",
 						 data:params,
 						 success:function(data){
 	 						 var obj = $.parseJSON(data);
  							 if(obj.result == "email_null"){
 								 alert("请输入邮箱");  
 							 }else  if(obj.result == "email_error"){
 								 alert("邮箱格式错误");   
 							 }else  if(obj.result == "code_null"){
 								 alert("请输入验证码");   
 							 }else  if(obj.result == "ecode_error"){
 								 alert("请输入正确的验证码");   
 							 }else  if(obj.result == "ecode_null"){
 								 alert("请输入邮箱验证码");   
 							 }else  if(obj.result == "password_null"){
 								 alert("请输入密码");    
 							 }else  if(obj.result == "ecode_error"){
 								 alert("请输入正确的邮箱验证码");     
 							 }else  if(obj.result == "email_fail"){
 								 alert("邮箱已经注册过了");     
 							 }else  if(obj.result == "username_fail"){
 								 alert("用户名已经注册过了");     
 							 }else  if(obj.result == "username_null"){
 								 alert("请输入用户名");     
 							 }else  if(obj.result == "user_null"){
 								 alert("session失效！请重新登录！"); 
 								 window.location.href=basePath+"/user/tologin.action";
  							 }else  if(obj.result == "success"){
 								 alert("注册并绑定QQ成功"); 
 								 window.location.href=basePath+"/user/tologin.action";
  							 }else  if(obj.result == "fail"){
 								 alert("注册并绑定QQ失败"); 
   							 }
   						 }
 					 });
 			},
 			//发送手机短信
 			sendPhondCode:function(){
 				var mobilephone = $("#bind_phone_userphone").val();
 				if(isEmpty(mobilephone)){
 					alert("请输入手机号码");
 					return false;
 				}
 				var params = {"mobilephone":mobilephone};
 				$.ajax({
					 type:"post",
					 url:basePath+"/qq/sianPhoneCode.action",
					 data:params,
					 success:function(data){
						 var obj = $.parseJSON(data);
 						 if(obj.result == "phone_null"){
							 alert("请输入手机号码"); 
						 }else  if(obj.result == "phone_error"){
							 alert("请输入正确的手机号码");  
						 }else  if(obj.result == "success"){
							 alert("手机短信已发送");  
						 }else  if(obj.result == "phone_exit"){
							 alert("手机号已经注册过了！");  
						 }   
					 }
				 });
 			},
 			//发送邮箱验证码
 			sendEmailCode:function(){
 				var email = $("#bind_email_userphone").val();
 				if(isEmpty(email)){
 					$("#bind_email_userphone").focus();
 					$("#bind_email_bindUserError").text("请输入邮箱账号...");
 					return false;
 				}else{
 					if(!is_email(email)){
 						$("#bind_email_userphone").focus();
 						$("#bind_email_bindUserError").text("请输入正确的邮箱格式...");
 						return false
 					}else{
 	 					$("#bind_email_bindUserError").text("");
 					}
 				}
  				var params = {"email":email};
 				$.ajax({
					 type:"post",
					 url:basePath+"/qq/sendEmailCode.action",
					 data:params,
					 success:function(data){
						 var obj = $.parseJSON(data);
  						 if(obj.result == "email_null"){
							 alert("请输入邮箱账号"); 
						 }else  if(obj.result == "email_error"){
							 alert("请输入正确的邮箱账号");  
						 }else  if(obj.result == "success"){
							 alert("邮箱验证码已发送");  
						 }else  if(obj.result == "fail"){
							 alert("邮箱验证码发送失败");  
						 }else  if(obj.result == "email_fail"){
							 alert("邮箱已经注册过了"); 
						 }   
					 }
				 });
 			}
		};
 		
 		function dom(id){
 			return document.getElementById(id);
 		};
 		//注册方式切换
 		function regEmail(){
 			$("#regPhone").hide();
 			$("#regEmail").show();
 		};
 		function regPhone(){
 			$("#regPhone").show();
 			$("#regEmail").hide();
 		};
 </script>
</body>
</html>