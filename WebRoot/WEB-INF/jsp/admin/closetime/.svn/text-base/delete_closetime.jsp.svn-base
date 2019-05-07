<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>莫邪科技</title>
</head>
<body>
	<div style="margin-left: 150px;">
		<label>时间名称：<font color="red">${cTime.timename}</font>
		</label><br> <label>开始时间：<font color="red">${cTime.btimeStr}</font></label><br>
		<label>结束时间：<font color="red">${cTime.etimeStr}</font></label> <input
			type="hidden" value="${cTime.id}" id="del-cTime-id">
	</div>
</body>
</html>