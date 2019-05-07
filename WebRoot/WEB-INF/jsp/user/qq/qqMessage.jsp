<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link
	href="${pageContext.request.contextPath }/bootstrap/3.3.5/css/bootstrap.min.css"
	rel="stylesheet" type="text/css">
<script type="text/javascript"
	src="${pageContext.request.contextPath }/jquery/1.11.3/jquery-1.11.3.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/bootstrap/3.3.5/js/bootstrap.min.js"></script>
<title>莫邪网-用户绑定页面</title>
<script type="text/javascript">
	$(function() {
		var isCustom = "${isCustom}";
		if (parseInt(isCustom) == 1) {
			$("#btn-isCustom").show();
			$("#btn-normal").show();
		}
		$("#username-text").change(function(){
			$("#loginNameInfos").html("");
		});
	});
	function ok() {
		var action = "confirm.action";
		var callback = function(data) {
			if (data == "success") {
				alert("提示：注册成功。");
				location.href = "${pageContext.request.contextPath }/user/tologin.action";
			}
			if (data = "fail") {
				alert("提示：用户名已存在。");
			}
			if (data = "tologin") {
				location.href = "${pageContext.request.contextPath }/user/tologin.action";
			}
		};
		if (validateName()) {
			$.post(action, {
				"name" : $("#username-text").val()
			}, callback, 'json');
		}
	}
	function btnNormal() {
		$("#username-text").attr("readonly", true);
		$("#username-text").val("${userName}");
	}
	function isCustom() {
		$("#username-text").attr("readonly", false);
		$("#username-text").val("");
		$("#username-text").focus();
	}
	function validateName() {
		var name = $("#username-text").val();
		var loginNameReg = /^[^@\/\'\\\"#$%&\^\*]+$/;
		if (name == "" || name == null) {
			$("#loginNameInfos").html("请输入用户名");
			return false;
		}
		if (!loginNameReg.test(name.trim())) {
			$("#loginNameInfos").html("用户名包含特殊字符");
			return false;
		}
		if(name.length<4||name.length>11){
			$("#loginNameInfos").html("用户名长度为4-11位");
			return false;
		}
		return true;
	}
</script>
</head>
<body style="padding: 20px">
	<div class="container">
		<div class="col-md-12 column" align="center">
			用户名:<input type="text" value="${userName }" readonly="readonly"
				id="username-text" />
			<button style="display: none;" type="button" id="btn-isCustom"
				class="btn btn-default" onclick="isCustom()">自定义</button>
			<button style="display: none;" type="button" id="btn-normal"
				class="btn btn-default" onclick="btnNormal()">默认</button>
			<br> <span style="color: red;"><label id="loginNameInfos"></label></span>
			<br>
			<button class="btn btn-default" onclick="ok()">确定注册</button>
			&nbsp;&nbsp;&nbsp;&nbsp;
			<button class="btn btn-default" onclick="window.close()">关闭返回</button>
		</div>
	</div>
</body>
</html>