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

<link href="./bootstrap/3.3.5/css/bootstrap.min.css" rel="stylesheet">
<script src="./jquery/1.11.3/jquery-1.11.3.min.js"></script>
<script src="./bootstrap/3.3.5/js/bootstrap.min.js"></script>
<script src="js/util.js"></script>

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
		$("#onSubmitID").click(function() {
			var action = "register/userBaseInfo/register.action";
			var mobilephone = $("#yourMobile").val();
			var smsMoblieCode = $("#moblieVerifyCode").val();
			var password = $("#password1").val();
			var params = {
				"mobilephone" : mobilephone,
				"smsMoblieCode" : smsMoblieCode,
				"password" : password
			};
			// 		$("#person_form").serialize()
			// 		window.location.href = action+"?"+"mobilephone="+mobilephone+"&smsMoblieCode="+smsMoblieCode+"&password="+password;
			// 		$.post(action, params);

		});

		/* function savePerson_success_callback(returnData) {
			//alert(returnData);
			var obj = $.parseJSON(returnData);
			//alert(obj.username);
			$("#person").empty();
			var str = "<b>保存成功！</b><br /><br />";
			var htmlStr = "" + "<b>id:</b>" + obj.id + ";<br />"
					+ "<b>name:</b>" + obj.name + ";<br />" + "<b>age:</b>"
					+ obj.age + ";<br />" + "<b>saveDate:</b>"
					+ datetimeFormat(obj.birthday) + ";<br />";
			$("#person").append(str + htmlStr);
		}; */

		var mobileReg = /^1[3|4|5|7|8][0-9]\d{8}$/;
		var url = "register/userBaseInfo/verifyLoginName.action";
		//判断手机号是否为空
		$("#mobilephone").blur(function() {
			var mobilephone = $("#mobilephone").val();
			//alert(mobilephone);
			if (mobilephone == null || mobilephone == '') {
				$("#mobileInfos").html("手机号不能为空");
			} else {
				if (!mobileReg.test(mobilephone.trim())) {
					$("#mobileInfos").html("格式错误");
					return;
				}
				$.post(url, {mobilephone : mobilephone}, function(data) {
					if (data != null && data.indexOf("exist") >= 0) {
						$("#mobileInfos").html("该手机号已经存在");
					} else {
						$("#mobileInfos").html("");
					}
				});
			}
		});
	});

	//验证手机的正确性
	function checkMob() {
		var moblie = $("#mobilephone").val();
		moblie = trim(moblie);
		var pattern = /^(13[0-9]|14[0-9]|15[0-9]|17[0-9]|18[0-9])\d{8}$/;
		chkFlag = pattern.test(moblie);
		if (chkFlag) {
			$("#mobileInfos").html("");
			return true;
		} else {
			$("#mobileInfos").html("手机号码不正确");
			return false;
		}
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

	/* function getValue(inputName, divName, innerName)
	{
		var username = document.getElementById(inputName).value;
		if (username == "")
		{
			document.getElementById(divName).innerHTML = innerName;
			return false;
		}

		document.getElementById(divName).innerHTML = "";
		return true;
	}
	//密码校验
	function check(inputName, divName, innerName)
	{
		return getValue(inputName, divName, innerName);
	} */
	//马上注册
	function dis() {
		var mobilephone = $("#mobilephone").val();
		var password = $("#pass").val();
		var protocol = $("#protocol").is(":checked");
		//判断手机号
		if (!checkMob()) {
			return false;
		}
		//判断密码
		if (!checkPass()) {
			return false;
		}
		//判断复选框
		if (!protocol) {
			alert("请勾选同意服务协议");
			return false;
		}
		$("#yourMobile1").html(mobilephone);
		$("#yourMobile").val(mobilephone);
		$("#password1").val(password);
		//弹出模态框
		$("#myModal").modal('show');
	}

	//点击切换验证码  
	function changeVerifyCode() {
		var random = Math.random();
		$("#Vcode").attr("src", "Kaptcha.jpg?r=" + random);
	}

	//发送短信验证码
	function SMSVerifyCode() {
		timer(30);
		var action = "register/userBaseInfo/smsSend.action";
		var mobilephone = $("#yourMobile").val();
		var picVerifyCode = $("#picVerifyCode").val();
		var params = {
			"mobilephone" : mobilephone,
			"picVerifyCode" : picVerifyCode
		};
		$.post(action, params);
	}

	//发送验证码后，控制无法重复点击时间
	function timer(time) {
		var btn = $("#sendsmsID");
		btn.attr("disabled", true); //按钮禁止点击
		btn.html((time <= 0) ? "发送短信验证码" : ("" + (time) + "秒后可再发送"));
		var hander = setInterval(function() {
			if (time <= 0) {
				clearInterval(hander); //清除倒计时
				//document.getElementById("sendsmsID").style.background="";
				btn.html("发送短信验证码");
				btn.attr("disabled", false);
				return false;
			} else {
				btn.html("" + (time--) + "秒后可再发送");
			}
		}, 1000);

	}

	/* function onSubmit() {
		var action = "register/save.action";
		var mobile = $("#mobilephone").val();
		var smsMoblieCode = $("#moblieVerifyCode").val();
		var password = $("#pass").val();
		var params = {
			"mobilephone" : mobilephone,
			"smsMoblieCode" : smsMoblieCode,
			"password" : password
		};
		//$("#person_form").serialize()
		$.post(action, params);
	} */
</script>

<title>手机注册页面</title>
</head>
<body>
	<div class="row">

		<div class="eightcol last">

			<!-- Begin Form -->

			<form class="form-horizontal" role="form" id="my-form">
				<div class="form-group">
					<label class="col-sm-3 control-label">
						<h3>用户注册</h3>
					</label>
				</div>
				<div class="form-group">
					<label for="mobilephone" class="col-sm-3 control-label">手机：</label>
					<div class="col-sm-5">
						<input type="text" class="form-control" id="mobilephone" value=""
							maxlength="15" placeholder="请输手机号">
					</div>
					<span style="color: red" id="mobileInfos"></span>
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
					<label class="col-sm-3 control-label">&nbsp;</label>
					<div class="col-sm-5">
						<input type="checkbox" id="protocol"> 同意XXX服务协议
					</div>

				</div>
				<div class="form-group">
					<div class="col-xs-6 col-md-offset-1">
						<button type="button" class="btn btn-primary" onclick="dis()">马上注册</button>
						<!-- <a href="#myModal" role="button" class="btn" data-toggle="modal">马上注册</a>  data-toggle="modal" data-target="#myModal"-->
						<a href="emailReg.jsp">使用邮箱注册</a>
					</div>
				</div>
			</form>
			<!-- End Form -->
		</div>
	</div>

	<!-- 模态框（Modal） -->
	<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<form role="form" id="submitForm"
					action="register/userBaseInfo/register.action" method="post">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-hidden="true">&times;</button>
						<h4 class="modal-title" id="myModalLabel">验证手机信息</h4>
					</div>
					<div class="modal-body" style="width: 600px; height: 200px;">


						<div class="row">
							<div class="form-group"
								style="width: 600px; height: 40px; border: 0px solid blue;">
								<div class="col-md-3">
									<label for="yourMobile">手机号：</label>
								</div>
								<div class="col-md-4">
									<label id="yourMobile1"></label> <input type="hidden"
										id="yourMobile" name="yourMobile" /> <input type="hidden"
										id="password1" name="password1" />
								</div>
							</div>
						</div>
						<div class="row">
							<div class="form-group">
								<label for="promoCode" class="col-sm-3 control-label">推广码：</label>
								<div class="col-sm-5">
									<input type="text" id="promoCode" name="promoCode"
										maxlength="6" placeholder="请输入推广码">
								</div>
							</div>
						</div>
						<div class="row">
							<div class="form-group"
								style="width: 600px; height: 40px; border: 0px solid blue;">
								<div class="col-md-3"
									style="height: 30px; border: 0px solid blue;">
									<label for="picVerifyCode">图形验证码：</label>
								</div>
								<div class="col-md-4"
									style="height: 30px; border: 0px solid red;">
									<input id="picVerifyCode" name="picVerifyCode" />
								</div>
								<div class="col-md-3"
									style="height: 30px; border: 0px solid orange;">
									<img title="点击获取校验码" id="Vcode" onclick="changeVerifyCode()"
										alt="点击获取校验码" src="Kaptcha.jpg">
								</div>
							</div>
						</div>

						<div class="row">
							<div class="form-group"
								style="width: 600px; height: 40px; border: 0px solid blue;">
								<div class="col-md-3">
									<label for="moblieVerifyCode">手机验证码：</label>
								</div>
								<div class="col-md-4">
									<input id="moblieVerifyCode" name="moblieVerifyCode" />
								</div>
								<div class="col-md-5">
									<button type="button" id="sendsmsID" onclick="SMSVerifyCode()">发送短信验证码</button>
								</div>
							</div>
						</div>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
						<button type="submit" class="btn btn-primary" id="onSubmitID">提交</button>
					</div>
				</form>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal -->
	</div>


</body>
</html>