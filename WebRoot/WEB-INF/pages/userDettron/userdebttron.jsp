<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>发起请求</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
</head>
<!-- <body onload="javascript:document.forms[0].submit()"> -->
<body onload="javascript:document.forms[0].submit()">
	<form name="form"
		 action="https://onlineuat.cupdata.com:50005/dbesbsit/app/to/autoDebtCession"
		method="post">
		<c:forEach items="${reqMap}" var="item">
			<c:if test="${!empty item.value}">
				<input type="hidden" name="${item.key}" value="${item.value}" />
			</c:if>
		</c:forEach>
	</form>
</body>
</html>
