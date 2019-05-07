<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
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
</head>
<body>
	<div align="center">
		<c:if test="${!empty smsChannel}">
			<input type="hidden" id="del-smschannel-id" value="${smsChannel.id }">
		短信号码：<span style="color: red;"><label><c:if
						test="${empty smsChannel.key }">默认</c:if>
					<c:if test="${!empty smsChannel.key }">${smsChannel.key }</c:if></label></span>
			<br>
		通道公司：<span style="color: red;"><label>${smsChannel.smsccompany }</label></span>
		</c:if>
		<c:if test="${fn:length(list)>0}">
			<span style="color: red;"> <c:forEach items="${list }"
					var="item">
					<label> ${item.bizname } </label>
				</c:forEach></span>
			
			存在引用，无法删除。
		</c:if>
	</div>
</body>
</html>