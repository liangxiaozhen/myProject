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
		$("#update-thirdpartyname-text").change(function() {
			$("#update-thirdpartyname-lb").html("");
		});
		$("#update-thirdpartycode-text").change(function() {
			$("#update-thirdpartycode-lb").html("");
		});
	});
	function validateUpdate() {
		var name = $("#update-thirdpartyname-text").val();
		if (name == "") {
			$("#update-thirdpartyname-lb").html("* 填写第三方公司名称");
			return false;
		}
		var code = $("#update-thirdpartycode-text").val();
		if (code == "") {
			$("#update-thirdpartycode-lb").html("* 填写第三方公司编码");
			return false;
		}
		return true;
	}
</script>
</head>
<body>
	<form id="update-form">
		<input type="hidden" name="id" value="${agpa.id }"> <input
			type="hidden" name="name" value="${agpa.thirdpartyname }">
		<div class="row" style="line-height: 0px;">
			<div class="col-md-4 col-md-offset-1" style="padding-left: 30px;">
				<font color="red"><b>第三方推广设置</b></font>
			</div>
			<hr>
		</div>
		<div class="row">
			<div class="col-md-11 col-md-offset-1">
				第三方链接地址： <input type="text" name="thirdpartycode"
					id="update-thirdpartycode-text"
					style="text-align: center; line-height: 18px;"
					value="${agpa.thirdpartycode }" placeholder="填写第三方链接地址" /> <span
					style="color: red;"><label id="update-thirdpartycode-lb"></label></span>
			</div>
		</div>
		<hr>
		<div class="row">
			<div class="col-md-11 col-md-offset-1">
				第三方公司名称： <input type="text" name="thirdpartyname"
					id="update-thirdpartyname-text"
					style="text-align: center; line-height: 18px;"
					value="${agpa.thirdpartyname }" placeholder="填写第三方公司名称" /> <span
					style="color: red;"><label id="update-thirdpartyname-lb"></label></span>
			</div>
		</div>

	</form>
</body>
</html>