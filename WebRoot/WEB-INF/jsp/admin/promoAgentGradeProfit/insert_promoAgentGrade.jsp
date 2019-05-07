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
<title>莫邪科技</title>
<script type="text/javascript">
	$(function() {
		$("#insert-proxyGradeName-text").change(function() {
			$("#insert-proxyGradeName-lb").html("");
		});
		$("#insert-proxyGrade-text").change(function() {
			$("#insert-proxyGrade-lb").html("");
		});
	})

	function validateForm() {
		var proxyGrade = $("#insert-proxyGrade-text").val();
		if (proxyGrade == "") {
			$("#insert-proxyGrade-lb").html("* 请输入推理级别");
			$("#insert-proxyGrade-text").focus();
			return false;
		}
		if (proxyGrade == 0) {
			$("#insert-proxyGrade-lb").html("* 该级别为默认设置，无法编辑");
			$("#insert-proxyGrade-text").focus();
			return false;
		}
		var proxyGradeName = $("#insert-proxyGradeName-text").val();
		if (proxyGradeName == "") {
			$("#insert-proxyGradeName-lb").html("* 请输入推广资质");
			$("#insert-proxyGradeName-text").focus();
			return false;
		}
		return true;
	}
</script>
</head>
<body>
	<form id="insert-form">
		<div class="row" style="line-height: 0px;">
			<div class="col-md-4 col-md-offset-1" style="padding-left: 30px;">
				<font color="red"><b>推广级别设置</b></font>
			</div>
			<hr>
		</div>
		<div class="row">
			<div class="col-md-11 col-md-offset-1">
				推广级别： <input type="text" name="proxygrade"
					id="insert-proxyGrade-text" onblur="checkNum(this)"
					onkeyup="clearNoNum(event,this)"
					style="width: 100px; text-align: center; line-height: 18px;"
					placeholder="请输入推广级别" /> <span style="color: red;"><label
					id="insert-proxyGrade-lb"></label></span>
			</div>
		</div>
		<hr>
		<div class="row">
			<div class="col-md-11 col-md-offset-1">
				推广资质： <input type="text" name="proxygradename"
					id="insert-proxyGradeName-text"
					style="width: 100px; text-align: center; line-height: 18px;"
					placeholder="请输入推广资质" /> <span style="color: red;"><label
					id="insert-proxyGradeName-lb"></label></span>
			</div>
		</div>
	</form>
</body>
</html>