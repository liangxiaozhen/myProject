<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
<title>Insert title here</title>
</head>
<body>
	<c:forEach items="${list }" var="item">
		<c:if test="${item.registertype eq 1 and item.isuse eq 1}">
			<a href="nameReg.action?promoCode=${promoCode }">用户名注册</a>
		</c:if>
		<c:if test="${item.registertype eq 2 and item.isuse eq 1}">
			<a href="userReg.action?promoCode=${promoCode }">用户名+手机号注册</a>
		</c:if>
		<c:if test="${item.registertype eq 3 and item.isuse eq 1}">
			<a href="mobileReg.action?promoCode=${promoCode }">手机号注册</a>
		</c:if>
		<c:if test="${item.registertype eq 4 and item.isuse eq 1}">
			<a href="emailReg.action?promoCode=${promoCode }">邮箱注册</a>
		</c:if>
	</c:forEach>
</body>
</html>