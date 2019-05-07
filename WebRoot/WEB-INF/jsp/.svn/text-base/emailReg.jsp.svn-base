<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<!--[if IE 8 ]> <html lang="en" class="ie8"> <![endif]-->
<!--[if IE 9 ]> <html lang="en" class="ie9"> <![endif]-->
<!--[if (gt IE 9)|!(IE)]><!-->
<html lang="en">
<!--<![endif]-->

<head>

<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!--[if lt IE 9]>
<script src="js/html5shiv.js"></script>
<![endif]-->

<!-- <link href="css/normalize.css" rel="stylesheet" />
<link href="css/jquery-ui.css" rel="stylesheet" />
<link href="css/jquery.idealforms.min.css" rel="stylesheet"
	media="screen" /> -->

<link
	href="${pageContext.request.contextPath }/bootstrap/3.3.5/css/bootstrap.min.css"
	rel="stylesheet">
<script
	src="${pageContext.request.contextPath }/jquery/1.11.3/jquery-1.11.3.min.js"></script>
<script
	src="${pageContext.request.contextPath }/bootstrap/3.3.5/js/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath }/js/util.js"></script>


<style type="text/css">
body {
	font: normal 15px/1.5 Arial, Helvetica, Free Sans, sans-serif;
	color: #222;
	background: url(pattern.png);
	overflow-y: scroll;
	padding: 60px 0 0 0;
}

#my-form {
	width: 755px;
	margin: 0 auto;
	border: 1px solid #ccc;
	padding: 3em;
	border-radius: 3px;
	box-shadow: 0 0 2px rgba(0, 0, 0, .2);
}

#comments {
	width: 350px;
	height: 100px;
}
</style>
<!-- <script type="text/javascript" src="js/jquery-1.8.2.min.js"></script>
<script type="text/javascript" src="js/jquery-ui.min.js"></script>
<script type="text/javascript" src="js/jquery.idealforms.js"></script> -->
<script type="text/javascript">
	$(function() {
		var emailReg = /^([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+@([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+\.[a-zA-Z]{2,3}$/;
		var url = "verifyLoginName.action";
		//判断邮箱是否为空
		$("#email").blur(function() {
			var email = $("#email").val();
			if (email == null || email == '') {
				$("#emailInfos").html("邮箱不能为空");
				return;
			} else {
				if (!emailReg.test(email.trim())) {
					$("#emailInfos").html("格式错误");
					return;
				}
				$.post(url, {
					email : email
				}, function(data) {
					if (data != null && data.indexOf("exist") >= 0) {
						$("#emailInfos").html("该邮箱已经存在");
					} else {
						$("#emailInfos").html("");
					}
				});
			}
		});
		var msg="${msg}";
		if(msg!=null&&msg!=''){
			mobileFlag=true;
			if(msg=='pwd'){
				$("#pass").focus();
				$("#passwordInfos").html("密码不能为空");
			}
			if(msg=="picVerifyCode"){
				$("#picVerifyCode").focus();
				$("#picVerifyCodeInfos").html("图形验证码有误");
			}
		}
	});

	//验证邮箱的正确性
	function checkEmail() {
		var email = $("#email").val();
		email = trim(email);
		if (email == null || email == '') {
			$("#emailInfos").html("邮箱不能为空");
			return false;
		} else if (email != null && email != '') {
			$("#emailInfos").html("");
			return true;
		}
		//else{
		//	$("#emailInfos").html("邮箱格式不正确");
		//	return false;
		//}
	}

	//校验密码的正确性   passwordInfos
	function checkPass() {
		var password = $("#pass").val();
		password = trim(password);
		if (password == null || password == "") {
			$("#passwordInfos").html("密码不能为空");
			return false;
		} else if (password != null && password != "") {
			if (password.length > 0 && password.length < 6) {
				$("#passwordInfos").html("密码长度小于6位!");
				return false;
			} else if (password.length >= 6 && password.length <= 16) {
				//判断是否为中文
				var reg = new RegExp("[\\u4E00-\\u9FFF]+", "g");

				//\d表示数字,*表示匹配多个数字
				//var re = /\d*/i; 
				//r = password.match(re); 
				//if(r==password){
				//	$("#passwordInfos").html("输入为纯数字,请使用6-16位的数字+字母的组合");
				//	return false
				//}
				//判断是否为纯字母或纯数字
				//var Regx = /^[A-Za-z]*$/;
				//var Regy = /^[0-9]*$/; 

				var regz = /^(?=.*[a-zA-Z])(?=.*\d)|(?=.*[~!@#$%^&*()_+`\-={}:\";\'<>?,.\/]).{6,16}$/;
				if (reg.test(password)) {
					$("#passwordInfos").html("输入含有中文字符,请使用6-16位的数字+字母或字符的组合");
					return false
				}
				if (!regz.test(password)) {
					$("#passwordInfos").html("输入为纯数字或纯字母,请使用6-16位的数字+字母或字符的组合");
					return false
				}

				$("#passwordInfos").html("");
				return true;
			}
		}
	}
	//验证码
	function checkPicVerifyCode() {
		var passCode = $("#picVerifyCode").val();
		passCode = trim(passCode);
		if (passCode == null || passCode == "") {
			$("#picVerifyCodeInfos").html("请输入验证码");
			return false;
		} else {
			$("#picVerifyCodeInfos").html("");
			return true;
		}
	}
	//马上注册
	function dis() {
		var protocol = $("#protocol").is(":checked");
		//判断邮箱
		if (!checkEmail()) {
			return false;
		}
		//判断密码
		if (!checkPass()) {
			return false;
		}
		//判断验证码
		if (!checkPicVerifyCode()) {
			return false;
		}
		//判断复选框
		if (!protocol) {
			alert("请勾选同意服务协议");
			return false;
		}
		$("#my-form").submit();

	}

	//点击切换验证码  
	function changeVerifyCode() {
		var random = Math.random();
		$("#Vcode").attr("src",
				"${pageContext.request.contextPath }/Kaptcha.jpg?r=" + random);
	}
</script>

<style type="text/css">
label {
	width: 150px;
}
</style>
<title>邮箱注册页面</title>
</head>
<body>
	<div class="row">
		<div class="eightcol last">
			<form class="form-horizontal" role="form" id="my-form"
				action="registerEmail.action" method="post">
				<div class="form-group">
					<label class="col-sm-3 control-label">
						<h3>用户注册</h3>
					</label>
				</div>
				<div class="form-group">
					<label for="email" class="col-sm-3 control-label">电子邮箱：</label>
					<div class="col-sm-5">
						<input type="text" class="form-control" id="email" name="email"
							value="${email }" placeholder="请输入邮箱">
					</div>
					<span style="color: red" id="emailInfos"></span>
				</div>
				<div class="form-group">
					<label for="password" class="col-sm-3 control-label">登录密码：</label>
					<div class="col-sm-5">
						<input type="password" class="form-control" id="pass"
							name="password" value="" onblur="checkPass()" placeholder="请输入密码">
					</div>
					<span style="color: red" id="passwordInfos"></span>
				</div>

				<div class="form-group">
					<label for="passCode" class="col-sm-3 control-label">图形验证码：</label>
					<div class="col-md-3">
						<input id="picVerifyCode" class="form-control"
							name="picVerifyCode" onblur="checkPicVerifyCode()"
							placeholder="请输入验证码" />
					</div>
					<div class="col-md-3"
						style="height: 30px; border: 0px solid orange;">
						<img title="点击获取校验码" id="Vcode" onclick="changeVerifyCode()"
							alt="点击获取校验码"
							src="${pageContext.request.contextPath }/Kaptcha.jpg">
					</div>
					<span style="color: red" id="picVerifyCodeInfos"></span>
				</div>
				<div class="form-group">
					<label for="promoCode" class="col-sm-3 control-label">推广码（选填）：</label>
					<div class="col-md-3">
						<input type="text" id="promoCode" class="form-control"
							name="promoCode" id="promoCode" maxlength="6"
							placeholder="请输入推广码" value="${promoCode }">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-3 control-label">&nbsp;</label>
					<div class="col-sm-5">
						<input type="checkbox" id="protocol"> 同意XXX服务协议
					</div>
				</div>
				<div class="form-group">
					<div class="col-xs-6 col-md-offset-1">
						<button type="button" class="btn btn-primary" onclick="dis()">马上注册</button>
						<!-- <a href="#myModal" role="button" class="btn" data-toggle="modal">马上注册</a>  -->
					</div>
				</div>
			</form>
		</div>
	</div>
</body>
</html>