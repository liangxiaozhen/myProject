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
<body onload="javascript:document.forms[0].submit()">
	<!--  <body> -->
	<form name="form"
		action="http://mertest.chinapnr.com/muser/publicRequests"
		method="post">
		<c:forEach items="${map}" var="item">
			<c:if test="${!empty item.value }">
				<input type="hidden" name="${item.key }" value="${item.value}" />
			</c:if>
		</c:forEach>
	</form>

	<%-- <form name="UsrFreezeBg" action="http://mertest.chinapnr.com/muser/publicRequests" method='post'>	
			<input  name='Version'   value="${Version}">
			<input  name='CmdId'   value="${CmdId}">
			<input  name='MerCustId'   value="${MerCustId}">
			<input  name='UsrCustId'   value="${UsrCustId}">
			<input  name='SubAcctType'   value="${SubAcctType}">
			<input  name='SubAcctId'   value="${SubAcctId}">
			<input  name='OrdId'   value="${OrdId}">
			<input  name='OrdDate'   value="${OrdDate}">
			<input  name='TransAmt'   value="${TransAmt}">
			<input  name='RetUrl'   value="${RetUrl}">
			<input  name='BgRetUrl'   value="${BgRetUrl}">
			<input  name='MerPriv'   value="${MerPriv}">
			<input  name='ChkValue'   value="${ChkValue}">
         <input type="submit" value="提交"/>
	</form> --%>
</body>
</html>
