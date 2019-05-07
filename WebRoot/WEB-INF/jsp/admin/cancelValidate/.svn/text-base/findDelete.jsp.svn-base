<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>屏蔽删除用户</title>
</head>
<body>
	<div style="text-align: center">
		您确定要删除 取消验证类型为
		<label><font style="color: red">
			<td><c:choose>
					<c:when test="${cancelValidate.canceltype ==1}">登录验证码</c:when>
					<c:when test="${cancelValidate.canceltype ==2}">注册验证码</c:when>
					<c:when test="${cancelValidate.canceltype ==3}">密码控件</c:when>
					<c:when test="${cancelValidate.canceltype ==4}">登录U盾</c:when>
				</c:choose>
			</td></font></label> 
			中的
			<lable><font style="color: red">${cancelValidate.username}</font></lable>
			用户吗?
			<input type = "hidden" value="${cancelValidate.id}" id="del-name-id">
		</div>
</body>
</html>