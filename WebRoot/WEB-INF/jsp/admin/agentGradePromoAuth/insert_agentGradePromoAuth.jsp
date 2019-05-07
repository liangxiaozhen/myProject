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
		$("#insert-thirdpartyname-text").change(function() {
			$("#insert-thirdpartyname-lb").html("");
		});
		$("#insert-thirdpartycode-text").change(function() {
			$("#insert-thirdpartycode-lb").html("");
		});
	});
	function validateInsert() {
		var name = $("#insert-thirdpartyname-text").val();
		if (name == "") {
			$("#insert-thirdpartyname-lb").html("* 填写第三方公司名称");
			return false;
		}
		var code = $("#insert-thirdpartycode-text").val();
		if (code == "") {
			$("#insert-thirdpartycode-lb").html("* 填写第三方公司编码");
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
				<font color="red"><b>第三方推广设置</b></font>
			</div>
			<hr>
		</div>
		<div class="row">
			<div class="col-md-11 col-md-offset-1">
				第三方公司名称： <input type="text" name="thirdPartyName"
					id="insert-thirdpartyname-text"
					style="text-align: center; line-height: 18px;"
					placeholder="填写第三方公司名称" /> <span style="color: red;"><label
					id="insert-thirdpartyname-lb"></label></span>
			</div>
		</div>
		<hr>
		<div class="row">
			<div class="col-md-11 col-md-offset-1">
				第三方公司地址： <input type="text" name="thirdPartyCode"
					id="insert-thirdpartycode-text"
					style="text-align: center; line-height: 18px;"
					placeholder="填写第三方公司地址" /> <span style="color: red;"><label
					id="insert-thirdpartycode-lb"></label></span>
			</div>
		</div>
	</form>
</body>
</html>