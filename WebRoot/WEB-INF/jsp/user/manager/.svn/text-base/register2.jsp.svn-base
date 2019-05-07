<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户注册 - 干将网贷</title>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/resources/resource/Scripts/visitorapi-1.2.1-min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/resources/resource/Scripts/appmeasurement-1.2.1-min.js"></script>
<link
	href="${pageContext.request.contextPath }/resources/resource/Css/common.css"
	rel="stylesheet" type="text/css">
<link
	href="${pageContext.request.contextPath }/resources/resource/Css/sea.css"
	rel="stylesheet" type="text/css">
<link
	href="${pageContext.request.contextPath }/resources/resource/Css/style.css"
	rel="stylesheet" type="text/css">
<script type="text/javascript"
	src="${pageContext.request.contextPath }/jquery/1.11.3/jquery-1.11.3.min.js"></script>

<style type="text/css">
a:active {
	star: expression(this.onFocus = this.blur ())
}

a:focus, button:focus {
	outline: 0;
	-moz-outline: 0
}

:focus {
	outline: 0
}

body, div, dl, dt, dd, ul, ol, li, h1, h2, h3, h4, h5, h6, pre, form,
	fieldset, input, textarea, p, blockquote, th, td, img, area, param {
	margin: 0;
	padding: 0
}

html, body {
	height: 100%
}

select {
	*behavior: url("select.htc")
}

ol, ul {
	padding-left: 0;
	list-style-type: none;
	list-style: none
}

img {
	border: 0;
	display: inline-block;
	vertical-align: middle
}

th {
	font-style: normal;
	font-weight: normal
}

h1, h2, h3, h4, h5, h6 {
	font-size: 100%;
	font-weight: normal
}

address, caption, cite, code, dfn, em, th, var, strong {
	font-style: normal;
	font-weight: normal
}

input {
	display: inline-block;
	vertical-align: middle
}

fieldset {
	border: 0
}

legend {
	display: none
}

body {
	font-family: \5FAE\8F6F\96C5\9ED1, arial, sans-serif;
	color: #333;
	-webkit-text-size-adjust: none;
	font-size: 14px;
	line-height: 20px
}

.title-1 {
	line-height: 30px;
	font-size: 20px;
	text-align: center;
	margin-bottom: 20px
}

.title-2 {
	line-height: 30px;
	font-size: 18px;
	font-weight: 600
}

.number {
	height: 24px;
	text-align: right;
	padding-right: 10%;
	margin-bottom: 30px
}
</style>
<script type="text/javascript">
	$(function() {
		/*
		$(".tuiarrow").click(function() {
			$(".code_box").toggle();
		});
		 */
	});
</script>
</head>
<body>
	<!--head-->

	<div class="clearfix simpleHead01">
		<div class="fl">
			<a href="http://test.ganjiangps.com"><img
				src="${pageContext.request.contextPath }/resources/Images/logo.png"
				width="173" height="57" alt="干将网贷" /></a><span>注册</span>
		</div>
		<div class="fr">
			<span class="fc_6">已有帐号？<a href="${pageContext.request.contextPath }/user/tologin.action"
				class="blue">立即登录</a></span>
		</div>
	</div>
	<!--head end-->

	<div class="main page147">
		<div class="clearfix fluid">
			<div class="module padding">
				<div class="stepX stepX_3">
					<ul class="active">
						<li class="sz"><span>1</span></li>
						<li class="sm">填写帐户信息</li>
					</ul>
					<ul class="active">
						<li class="sz"><span>2</span></li>
						<li class="sm">手机验证</li>
					</ul>
					<ul>
						<li class="sz"><span>3</span></li>
						<li class="sm">注册成功</li>
					</ul>
				</div>
				<div class="content2">
					<form action="registerThirdSteps.action" method="post" class="nwd-formUi"
						id="registerThirdStepsInit">
						<div class="baocuo">
							<input type="hidden" value="${uname }" id="regUname"> <input
								type="hidden" value="1" id="messSendType"> <input
								type="hidden" value="${mobile}" id="regMobile"> <input
								type="hidden" value="${messSendTime }" id="messSendTime">
							<input type="hidden" value="${regSdMsgCodeRst}" id="regSdMsgCodeRst"> <input
								type="hidden" name="num" id="num" value="${num }"/> <span id="ckCodeMSG"><i
								class="ico_all size15 img_icon s_dui"></i> 验证码已发送，有效期30分钟，请查收</span>
						</div>
						<div class="baocuo hidden">
							<i class="ico_all size15 img_icon s_cuo"></i> <span>验证未成功，点击语音验证码，你我贷通过400-7910-888播报</span>
						</div>
						<table class="iphone1">
							<tbody>
								<tr class="h">
									<th>手机号</th>
									<td><span>${mobile }</span> <!-- <a href="javascript:;"
										class="blue pl_20" onclick="openWid();">修改手机号</a></td>
								</tr>-->
								<tr class="h">
									<th>验证码</th>
									<td><input type="text" class="input_b mr_5"
										style="width: 130px;" id="checkCode" maxlength="6"> <input
										type="button" class="yzm" id="yybtn" value="重新获取"
										style="display: none"> <!--<input type="button" class="yzm" id="yybtn2" value="语音验证码" style="display:none">-->
										<input type="button" class="yzm" id="dtmbtn" value="点击获取"
										style="display: none"> <input type="button"
										class="yzm2" id="countDown" style=""><span
										id="fucodeMSG" style="display: none"></span></td>
								</tr>
								<tr>
									<th></th>
									<td><input type="button" value="验证"
										class="btn btnSize_1 btn_orange b" id="regChkBtn"></td>
								</tr>
							</tbody>
						</table>
					</form>
				</div>
			</div>
		</div>
		<div class="fs_12 fc_6 Numfont txt_center pt_15">Copyright © 2016
		干将贷（test.ganjiangps.com） 版权所有；杜绝借款犯罪，倡导合法借贷，信守借款合约</div>
	</div>

	<script type="text/javascript"
		src="${pageContext.request.contextPath }/resources/resource/Scripts/register2.js"></script>
</body>
</html>