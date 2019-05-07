<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<!--[if IE 8 ]> <html lang="en" class="ie8"> <![endif]-->
<!--[if IE 9 ]> <html lang="en" class="ie9"> <![endif]-->
<!--[if (gt IE 9)|!(IE)]><!-->
<html lang="en">
<!--<![endif]-->

<head>

<!-- <meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"> -->


<!--[if lt IE 9]>
<script src="js/html5shiv.js"></script>
<![endif]-->

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


<script type="text/javascript">
	$(function() {
		var loginNameReg = /^[^@\/\'\\\"#$%&\^\*]+$/;
		var url = "verifyLoginName.action";
		//判断手机号是否为空
		$("#loginName").blur(function() {
			var loginName = $("#loginName").val();
			if (loginName == null || loginName == '') {
				$("#loginNameInfos").html("用户名不能为空");
				return;
			} else {
				if (!loginNameReg.test(loginName.trim())) {
					$("#loginNameInfos").html("用户名包含特殊字符");
					return;
				}/*
				var reg = /[\u4E00-\u9FA5\uF900-\uFA2D]/;
				if (reg.test(loginName)) {
					$("#loginNameInfos").html("用户名不允许包含中文");
					return;
				}*/
				$.post(url, {
					loginname : loginName
				}, function(data) {
					if (data != null && data.indexOf("exist") >= 0) {
						$("#loginNameInfos").html("该用户名已经存在");
					} else {
						$("#loginNameInfos").html("");
					}
				});
			}
		});
		var msg = "${msg}";
		if (msg != null && msg != '') {
			if (msg == 'pwd') {
				$("#pass").focus();
				$("#passwordInfos").html("密码不能为空");
			}
			if (msg == "picVerifyCode") {
				$("#picVerifyCode").focus();
				$("#picVerifyCodeInfos").html("图形验证码有误");
			}
			if (msg == "userNameLength") {
				$("#loginName").focus();
				$("#loginNameInfos").html(
						"用户名长度必须是" + "${minLength}"+"~"+"${maxLength}"+"位");
			}
			if (msg == "isChinese") {
				$("#loginName").focus();
				$("#loginNameInfos").html("用户名不允许包含中文");
			}
		}
	});
	//验证图形验证码是否为空
	function checkPicVerifyCode() {
		var picVerifyCode = $("#picVerifyCode").val();
		if (picVerifyCode == null || picVerifyCode == '') {
			$("#picVerifyCodeInfos").html("图形验证码不能为空");
			return false;
		} else {
			$("#picVerifyCodeInfos").html("");
			return true;
		}
	}
	//校验用户名
	function checkLoginName() {
		var loginName = $("#loginName").val();
		loginName = trim(loginName);
		if (loginName == null || loginName == '') {
			$("#loginNameInfos").html("用户名不能为空");
			return false;
		}
		var pattern = /^[^@\/\'\\\"#$%&\^\*]+$/;
		chkFlag = pattern.test(loginName);
		if (!chkFlag) {
			$("#loginNameInfos").html("用户名包含特殊字符");
			return false;
		}/*
		var reg = /[\u4E00-\u9FA5\uF900-\uFA2D]/;
		if (reg.test(loginName)) {
			$("#loginNameInfos").html("用户名不允许包含中文");
			return false;
		}*/
		return true;
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

	//马上注册
	function dis() {
		var protocol = $("#protocol").is(":checked");
		//判断手机号
		if (!checkLoginName()) {
			return false;
		}
		//判断密码
		if (!checkPass()) {
			return false;
		}
		//判断图形验证码
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

<title>用户注册页面</title>
</head>
<body>
	<div class="row">

		<div class="eightcol last">

			<!-- Begin Form -->

			<form class="form-horizontal" action="nameRegister.action"
				method="post" role="form" id="my-form">
				<div class="form-group">
					<label class="col-sm-3 control-label">
						<h3>用户注册</h3>
					</label>
				</div>
				<div class="form-group">
					<label for="loginName" class="col-sm-3 control-label">用户名：</label>
					<div class="col-sm-5">
						<input type="text" class="form-control" id="loginName"
							maxlength="15" name="loginname" placeholder="英文字母开头，4-20位字符"
							value="${loginName }">
					</div>
					<span style="color: red" id="loginNameInfos"></span>
				</div>
				<div class="form-group">
					<label for="password" class="col-sm-3 control-label">密码：</label>
					<div class="col-sm-5">
						<input type="password" class="form-control" id="pass"
							name="password" value="" maxlength="20" onblur="checkPass()"
							placeholder="请输入密码">
					</div>
					<span style="color: red" id="passwordInfos"></span>
				</div>
				<div class="form-group">
					<label for="picVerifyCode" class="col-sm-3 control-label">图形验证码：</label>
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
							name="promoCode" maxlength="6" placeholder="请输入推广码"
							value="${promoCode }">
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
					</div>
				</div>
			</form>
			<!-- End Form -->
		</div>
	</div>
</body>
</html>