<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div style="text-align: center">
		<c:if test="${!empty modelName }">
			<label><span style="color: red;">${modelName}</span> 存在引用。</label>
		</c:if>
		<c:if test="${empty modelName }">
		确定删除 <label><font color="red">${nametype}</font></label> 中的 <label><font
				color="red">${name}</font></label> 吗？ <input type="hidden" value="${nameno}"
				id="delNameNo">
		</c:if>
	</div>
</body>
</html>