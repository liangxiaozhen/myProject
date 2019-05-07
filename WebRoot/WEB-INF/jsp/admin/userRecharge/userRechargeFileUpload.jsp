<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
<link rel="stylesheet"
	href="${pageContext.request.contextPath }/bootstrap/3.3.5/css/bootstrap-theme.min.css">
<script type="text/javascript"
	src="${pageContext.request.contextPath }/jquery/1.11.3/jquery-1.11.3.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/bootstrap/3.3.5/js/bootstrap.min.js"></script>
</head>
<script type="text/javascript">
	function checkUpload() {
		var name = $("#file").val();
		if (name != "") {
			if (name.substring(name.lastIndexOf(".") + 1) == 'txt') {
				return true;
			}
		}
		alert("提示：请选择上传文件，文件类型为文本类型 txt");
		return false;
	}
</script>
<body style="font-family: 微软雅黑">
	<div style="margin: 70px;">
		<label>上传取现记录文本文件，进行人工对账(文件大小不得超过10M)</label>
		<form onsubmit="return checkUpload()"
			action="${pageContext.request.contextPath }/admin/userRecharge/upload.action"
			enctype="multipart/form-data" method="post">
			<br> <br> <input id="file" type="file" name="file">
			<br> <br>
			<button class="btn btn-primary">上传</button>
		</form>
	</div>
</body>
</html>