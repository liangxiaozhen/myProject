<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>乾多多用户提现页面</title>
</head>
<body onload="javascript:document.forms[0].submit()">
	<form name="form"
		action="http://test.moneymoremore.com:88/main/loan/toloanwithdraws.action"
		method="post">
		<c:forEach items="${map}" var="item">
			<c:if test="${!empty item.value }">
				<input type="hidden" name="${item.key }" value="${item.value}" />
			</c:if>
		</c:forEach>
	</form>
</body>
</html>