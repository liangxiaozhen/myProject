<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":"
			+ request.getServerPort() + path + "/";
%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>莫邪科技</title>
</head>
<body>
	<div style="margin-left: 150px;">
		<input type="hidden" value="${unrm.id }" id="del-id"> 填写要求： <span
			style="color: red"><label><c:if
					test="${unrm.needtype eq 1 }">必填</c:if> <c:if
					test="${unrm.needtype eq 2 }">不填</c:if> </label></span> <br> 规则内容：<span
			style="color: red"><label>${unrm.setruledetail }</label></span>
		<c:if test="${!empty unrm.namerule }">
			<br>内容组成：<span style="color: red"><label>${unrm.namecontent }</label></span>
		</c:if>
		<c:if test="${!empty unrm.presetstr }">
			<br>预设字符：<span style="color: red"><label>${unrm.presetstr }</label></span>
		</c:if>
		<c:if test="${!empty unrm.randomlength }">
			<br>随机位数：<span style="color: red"><label>${unrm.randomlength }</label></span>
		</c:if>
	</div>
</body>
</html>