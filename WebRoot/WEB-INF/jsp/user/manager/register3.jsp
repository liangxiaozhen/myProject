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
					<ul class="active">
						<li class="sz"><span>3</span></li>
						<li class="sm">注册成功</li>
					</ul>
				</div>
				<div class="content3">
					<div class="line1">
						<i class="ico_all size48 img_true48"></i><span class="fs_24">${uname }，
							恭喜您注册成功！</span>
					</div>
					<div class="line2">
						<span class="fs_14"><a href="${pageContext.request.contextPath }/user/tologin.action"
							class="blue">跳转登录</a></span>
					</div>
				</div>
			</div>
		</div>
		<div class="fs_12 fc_6 Numfont txt_center pt_15">Copyright © 2016
		干将贷（test.ganjiangps.com） 版权所有；杜绝借款犯罪，倡导合法借贷，信守借款合约</div>
	</div>
	
</body>
</html>