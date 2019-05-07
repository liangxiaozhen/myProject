<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE HTML>
<html>
<head>
<title>发起投标申请</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
</head>

<body onload="javascript:document.forms[0].submit()">
	<form name="InitiativeTender"
		action="https://onlineuat.cupdata.com:50005/dbesbsit/app/to/bidApplySign"
		method='post'>
		<c:if test="${!empty reqMap}">
			<c:forEach items="${reqMap}" var="p">
				<input type="hidden" name="${p.key}" value="${p.value}"/>
			</c:forEach>
		</c:if>
	</form>
</body>
</html>
